package com.creditscore.creditscorecheck.loan.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.creditscore.creditscorecheck.loan.BuildConfig;
import com.creditscore.creditscorecheck.loan.MyApplication;
import com.creditscore.creditscorecheck.loan.R;
import com.creditscore.creditscorecheck.loan.RenCheckService;
import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.ads.NewOpenAds;
import com.creditscore.creditscorecheck.loan.ads.OpenAds;
import com.creditscore.creditscorecheck.loan.apiinterface.RetrofitClient;
import com.creditscore.creditscorecheck.loan.databinding.ActivitySplashBinding;
import com.creditscore.creditscorecheck.loan.databinding.DialogInternetBinding;
import com.creditscore.creditscorecheck.loan.encrypt.DecryptEncrypt;
import com.creditscore.creditscorecheck.loan.model.Update;
import com.creditscore.creditscorecheck.loan.module.ViewAnimator.AnimationListener;
import com.creditscore.creditscorecheck.loan.module.ViewAnimator.ViewAnimator;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onesignal.OneSignal;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unified.vpn.sdk.AuthMethod;
import unified.vpn.sdk.ClientInfo;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransport;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.SdkNotificationConfig;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.UnifiedSdkConfig;
import unified.vpn.sdk.User;
import unified.vpn.sdk.VpnException;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 4000;
    private static final String CHANNEL_ID = "vpn";
    int VERSION = 0;
    ActivitySplashBinding binding;
    Dialog dialog;


    private boolean isMyServiceRunning(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                Log.e("ServiceStatus", "Running");
                return true;
            }
        }
        Log.e("ServiceStatus", "Not running");
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constant.stopVpn();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {
            Log.d("Encry", DecryptEncrypt.encryptKey("Is3De29YZnkgqkzt"));
            Log.d("Encry", DecryptEncrypt.encryptKey("3H1VeZM7GIpe1GlO"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isMyServiceRunning(RenCheckService.class))
                    startService(new Intent(SplashActivity.this, RenCheckService.class));

                PowerPreference.getDefaultFile().putBoolean("running", true);
            }
        }, 2000);

        ViewAnimator.animate(binding.icon)
                .scale(0f, 1f)
                .startDelay(1000)
                .duration(1000)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        PackageManager manager = getPackageManager();
                        PackageInfo info = null;

                        try {
                            info = manager.getPackageInfo(getPackageName(), 0);
                            VERSION = info.versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                            Constant.Log(e.toString());
                        }

                        startSplash();
                    }
                }).start();


    }


    public void startSplash() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;

        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            VERSION = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Constant.Log(e.toString());
            VERSION = BuildConfig.VERSION_CODE;
        }

        callUpdateApi();
    }

    public DialogInternetBinding network_dialog(String text) {
        dialog = new Dialog(this);
        DialogInternetBinding binding = DialogInternetBinding.inflate(getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        binding.txtError.setText(text);
        return binding;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sample VPN";
            String description = "VPN notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void callUpdateApi() {

        if (Constant.checkInternet(this)) {

            @SuppressLint("HardwareIds") String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            JsonObject object = new JsonObject();
            object.addProperty("VersionCode", VERSION);
            object.addProperty("PkgName", getPackageName());
            object.addProperty("AndroidId", deviceId);

            RetrofitClient.getInstance().getApi().getUpdates(object)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            try {
                                Log.d("data", response.body() + " hel;l");

                                final Update appData = new GsonBuilder().create().fromJson((DecryptEncrypt.DecryptStr(response.body())), Update.class);
                                Log.d("appdata", DecryptEncrypt.DecryptStr(response.body()));

                                Gson gson = new Gson();
                                String adsString = gson.toJson(appData);


                                PowerPreference.getDefaultFile().putString(Constant.mAds, adsString);

                                PowerPreference.getDefaultFile().putBoolean(Constant.VpnOnOff, appData.getVpnOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.VpnSkipOnOff, appData.getVpnSkipOnOff());
                                PowerPreference.getDefaultFile().putString(Constant.VpnCountry, appData.getVpnCountry());

                                PowerPreference.getDefaultFile().putInt(Constant.SERVER_INTERVAL_COUNT, appData.getGoogleIntervalCount());
                                PowerPreference.getDefaultFile().putInt(Constant.APP_INTERVAL_COUNT, 0);

                                PowerPreference.getDefaultFile().putInt(Constant.SERVER_BACK_COUNT, appData.getGoogleBackInterIntervalCount());
                                PowerPreference.getDefaultFile().putInt(Constant.APP_BACK_COUNT, 0);

                                PowerPreference.getDefaultFile().putBoolean(Constant.CustomAdsOnOff, appData.getCustomAdsOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.FULL_SCREEN, appData.getFullScreenOnOff());

                                PowerPreference.getDefaultFile().putBoolean(Constant.PolicyOnOff, appData.getPolicyOnOff());

                                PowerPreference.getDefaultFile().putBoolean(Constant.BACK_ADS, appData.getGoogleBackInterOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleSplashOpenAdsOnOff, appData.getGoogleSplashOpenAdsOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleExitSplashInterOnOff, appData.getGoogleExitSplashInterOnOff());

                                PowerPreference.getDefaultFile().putString(Constant.QUREKA_ADS, appData.getQurekaLink());
                                PowerPreference.getDefaultFile().putString(Constant.POLICY, appData.getPolicyLink());

                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleMiniNativeOnOff, appData.getGoogleMiniNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleLargeNativeOnOff, appData.getGoogleLargeNativeOnOff());

                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleListNativeOnOff, appData.getGoogleListNativeOnOff());

                                PowerPreference.getDefaultFile().putInt(Constant.ListNativeWhichOne, appData.getListNativeWhichOne());
                                PowerPreference.getDefaultFile().putInt(Constant.ListNativeAfterCount, appData.getListNativeAfterCount());

                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaAppOpenOnOff, appData.getQurekaAppOpenOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaInterOnOff, appData.getQurekaInterOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaBackInterOnOff, appData.getQurekaBackInterOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaMiniNativeOnOff, appData.getQurekaMiniNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaLargeNativeOnOff, appData.getQurekaLargeNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaListNativeOnOff, appData.getQurekaListNativeOnOff());


                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaInterCloseOnOff, appData.getQurekaInterCloseOnOff());
                                PowerPreference.getDefaultFile().putString(Constant.GoogleNativeText, appData.getGoogleNativeText());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleNativeTextOnOff, appData.getGoogleNativeTextOnOff());

                                PowerPreference.getDefaultFile().putString(Constant.NativeBackgroundColor, appData.getNativeBackgroundColor());
                                PowerPreference.getDefaultFile().putInt(Constant.AllPagesNativeBackgroundCount, appData.getAllPagesNativeBackgroundCount());

                                PowerPreference.getDefaultFile().putBoolean(Constant.HomeNativeBackgroundColorOnOff, appData.getHomeNativeBackgroundColorOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.AllPagesNativeBackgroundOnOff, appData.getAllPagesNativeBackgroundOnOff());

                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaOnOff, appData.getQurekaOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.AdsOnOff, appData.getAdsOnOff());

                                PowerPreference.getDefaultFile().putString(Constant.OneSignalAppId, appData.getOneSignalAppId());


                                PowerPreference.getDefaultFile().putBoolean(Constant.ShowDialogBeforeAds, appData.getShowDialogBeforeAds());
                                PowerPreference.getDefaultFile().putDouble(Constant.DialogTimeInSec, appData.getDialogTimeInSec());

                                PowerPreference.getDefaultFile().putString(Constant.INTERID, appData.getGoogleInterAds());
                                PowerPreference.getDefaultFile().putString(Constant.NATIVEID, appData.getGoogleNativeAds());
                                PowerPreference.getDefaultFile().putString(Constant.OPENAD, appData.getGoogleAppOpenAds());
                                PowerPreference.getDefaultFile().putString(Constant.GoogleAppIdAds, appData.getGoogleAppIdAds());

                                MobileAds.initialize(SplashActivity.this, appData.getGoogleAppIdAds());
                                OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

                                OneSignal.initWithContext(MyApplication.getInstance());
                                OneSignal.setAppId(PowerPreference.getDefaultFile().getString(Constant.OneSignalAppId, ""));


                                new InterAds().loadInterAds(SplashActivity.this);
                                new BackInterAds().loadInterAds(SplashActivity.this);
                                new MiniNativeAds().loadNativeAds(SplashActivity.this, null);
                                new LargeNativeAds().loadNativeAds(SplashActivity.this, null);
                                new NewOpenAds().loadOpenAd(SplashActivity.this);
                                new OpenAds().loadOpenAd();


                                ArrayList<String> arrayList = new ArrayList<>();

                                arrayList.add(0, HomeScreen.class.getName());
                                arrayList.add(1, MainActivity.class.getName());
                                arrayList.add(2, CalculaterActivity.class.getName());
                                arrayList.add(3, CalculatedEmiActivity.class.getName());


                                arrayList.add(4, ScoreOfflineActivity.class.getName());
                                arrayList.add(5, CheckCreditOfflineActivity.class.getName());
                                arrayList.add(6, CreditScoreTipsActivity.class.getName());
                                arrayList.add(7, CreditTips1Activity.class.getName());
                                arrayList.add(8, FinalScoreActivity.class.getName());


                                arrayList.add(9, OptionActivity.class.getName());
                                arrayList.add(10, ScoreOffline2Activity.class.getName());

                                arrayList.add(11, ScoreOffline3Activity.class.getName());
                                arrayList.add(12, TipsTextActivity.class.getName());


                                HashMap<String, String> hashMap = new HashMap<>();
                                int total = PowerPreference.getDefaultFile().getInt(Constant.AllPagesNativeBackgroundCount, 0);
                                if (PowerPreference.getDefaultFile().getBoolean(Constant.AllPagesNativeBackgroundOnOff, true)) {
                                    if (total < arrayList.size()) {
                                        Collections.shuffle(arrayList);
                                        for (int i = 0; i < total; i++) {
                                            if (!hashMap.containsKey(arrayList.get(i)))
                                                hashMap.put(arrayList.get(i), arrayList.get(i));
                                        }
                                    } else {
                                        for (int i = 0; i < arrayList.size(); i++) {
                                            hashMap.put(arrayList.get(i), arrayList.get(i));
                                        }
                                    }
                                }
                                PowerPreference.getDefaultFile().putMap(Constant.NativeTrans, hashMap);

                                createNotificationChannel();

                                ClientInfo clientInfo = ClientInfo.newBuilder()
                                        .addUrl(appData.getVpnUrl())
                                        .carrierId(appData.getVpnCarrierId())
                                        .build();

                                List<TransportConfig> transportConfigList = new ArrayList<>();
                                transportConfigList.add(HydraTransportConfig.create());
                                transportConfigList.add(OpenVpnTransportConfig.tcp());
                                transportConfigList.add(OpenVpnTransportConfig.udp());
                                UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);

                                SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder()
                                        .title(getResources().getString(R.string.app_name))
                                        .channelId(CHANNEL_ID)
                                        .build();

                                UnifiedSdk.update(notificationConfig);

                                UnifiedSdkConfig config = UnifiedSdkConfig.newBuilder().build();
                                UnifiedSdk.getInstance(clientInfo, config);


                                if (!appData.getTitle().equals("")) {
                                    binding.txtName.setText(appData.getTitle());
                                    binding.txtName.setVisibility(View.VISIBLE);
                                }

                                if (!appData.getDescription().equals("")) {
                                    binding.txtDes.setText(appData.getDescription());
                                    binding.txtDes.setVisibility(View.VISIBLE);
                                }

                                if (!appData.getButtonName().equals("")) {
                                    binding.btnUpdate.setText(appData.getButtonName());
                                }

                                if (!appData.getButtonSkip().equals("")) {
                                    binding.btnSkip.setText(appData.getButtonSkip());
                                }

                                String flag = appData.getFlag();
                                boolean flagCheck = true;

                                if (flag.equals("NORMAL")) {
                                    Log.e("TAG", "onResponse: " + "nor");
                                    binding.cvUpdate.setVisibility(View.GONE);
                                    flagCheck = true;
                                } else if (flag.equals("SKIP")) {

                                    if (VERSION != appData.getVersion()) {
                                        binding.btnUpdate.setVisibility(View.VISIBLE);
                                        binding.cvUpdate.setVisibility(View.VISIBLE);
                                        binding.btnSkip.setVisibility(View.VISIBLE);
                                        flagCheck = false;
                                    } else {
                                        binding.cvUpdate.setVisibility(View.GONE);
                                        flagCheck = true;
                                    }
                                } else if (flag.equals("MOVE")) {
                                    binding.btnUpdate.setVisibility(View.VISIBLE);
                                    binding.btnSkip.setVisibility(View.GONE);
                                    binding.cvUpdate.setVisibility(View.VISIBLE);
                                    flagCheck = false;

                                } else if (flag.equals("FORCE")) {
                                    if (VERSION != appData.getVersion()) {
                                        binding.btnUpdate.setVisibility(View.VISIBLE);
                                        binding.btnSkip.setVisibility(View.GONE);
                                        binding.cvUpdate.setVisibility(View.VISIBLE);
                                        flagCheck = false;
                                    } else {
                                        binding.cvUpdate.setVisibility(View.GONE);
                                    }
                                }

                                binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (flag.equals("MOVE")) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appData.getLink())));
                                        } else {
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                                            } catch (ActivityNotFoundException e) {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                                            }
                                        }
                                    }
                                });

                                binding.btnSkip.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        binding.cvUpdate.setVisibility(View.GONE);
                                 /*       if (PowerPreference.getDefaultFile().getBoolean(Constant.VpnOnOff, false) && !PowerPreference.getDefaultFile().getBoolean(Constant.Vpnaccept, false)) {
                                            vpnDialog();
                                        } else
                                            gotoSkip();*/

                                        if (PowerPreference.getDefaultFile().getBoolean(Constant.VpnOnOff, false)) {
                                            if (PowerPreference.getDefaultFile().getBoolean(Constant.Vpnaccept,false)){
                                                login();
                                            }else {
                                                vpnDialog();
                                            }
                                        } else
                                            gotoSkip();
                                    }
                                });

                                if (flagCheck) {

                                 /*   if (PowerPreference.getDefaultFile().getBoolean(Constant.VpnOnOff, false) && !PowerPreference.getDefaultFile().getBoolean(Constant.Vpnaccept, false)) {
                                        Log.d("TAG", "onResponse: " + "googg");
                                        vpnDialog();
                                    } else {

                                        gotoSkip();
                                        Log.d("TAG", "onResponse: " + "gohrjhytj6yuj6uogg");

                                    }*/

                                    if (PowerPreference.getDefaultFile().getBoolean(Constant.VpnOnOff, false)) {
                                        if (PowerPreference.getDefaultFile().getBoolean(Constant.Vpnaccept,false)){
                                            login();
                                        }else {
                                            vpnDialog();
                                        }
                                    } else
                                        gotoSkip();
                                }

                            } catch (Exception e) {
                                Constant.Log(e.toString());
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call
                                , Throwable t) {
                            network_dialog(t.getMessage()).btnRetry.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    if (Constant.checkInternet(SplashActivity.this)) {
                                        callUpdateApi();
                                    } else dialog.show();
                                }
                            });
                        }
                    });
        } else {
            network_dialog(getResources().getString(R.string.network_error)).btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (Constant.checkInternet(SplashActivity.this)) {
                        callUpdateApi();
                    } else dialog.show();
                }
            });
        }
    }






    public void gotoSkip() {
        Log.e("TAG", "gotoSkip: ");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (PowerPreference.getDefaultFile().getBoolean(Constant.GoogleSplashOpenAdsOnOff)) {
                    new NewOpenAds().showOpenAd(SplashActivity.this, new NewOpenAds.OnAdClosedListener() {
                        @Override
                        public void onAdClosed() {
                            if (PowerPreference.getDefaultFile().getBoolean(Constant.PolicyOnOff, true) && !PowerPreference.getDefaultFile().getBoolean(Constant.POLICY_ACCEPT, false))
                                startActivity(new Intent(SplashActivity.this, PrivacyPolicyActivity.class));
                            else if (PowerPreference.getDefaultFile().getBoolean(Constant.CustomAdsOnOff, true))
                                startActivity(new Intent(SplashActivity.this, AdAppActivity.class));
                            else {

                                startActivity(new Intent(SplashActivity.this, HomeScreen.class));

                            }
                        }
                    });
                } else {
                    if (PowerPreference.getDefaultFile().getBoolean(Constant.PolicyOnOff, true) && !PowerPreference.getDefaultFile().getBoolean(Constant.POLICY_ACCEPT, false))
                        startActivity(new Intent(SplashActivity.this, PrivacyPolicyActivity.class));
                    else if (PowerPreference.getDefaultFile().getBoolean(Constant.CustomAdsOnOff, true))
                        startActivity(new Intent(SplashActivity.this, AdAppActivity.class));
                    else {

                        startActivity(new Intent(SplashActivity.this, HomeScreen.class));

                    }

                }
            }
        }, 4000);
    }


    public void login() {
        AuthMethod authMethod = AuthMethod.anonymous();
        UnifiedSdk.getInstance().getBackend().login(authMethod, new unified.vpn.sdk.Callback<User>() {
            @Override
            public void success(@NonNull User user) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startVpn();
                    }
                }, 4000);
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Toast.makeText(SplashActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                gotoSkip();
            }
        });
    }


    public void startVpn() {
        List<String> fallbackOrder = new ArrayList<>();
        fallbackOrder.add(HydraTransport.TRANSPORT_ID);
        fallbackOrder.add(OpenVpnTransportConfig.tcp().getName());
        fallbackOrder.add(OpenVpnTransportConfig.udp().getName());

        UnifiedSdk.getInstance().getVpn().start(new SessionConfig.Builder()
                .withTransport(OpenVpnTransportConfig.tcp().getName())
                .withTransportFallback(fallbackOrder)
                .withVirtualLocation(PowerPreference.getDefaultFile().getString(Constant.VpnCountry, "us"))
                .build(), new CompletableCallback() {
            @Override
            public void complete() {
                gotoSkip();
            }

            @Override
            public void error(@NonNull VpnException e) {
                Log.e("TAG2", e.toTrackerName() + " error");
                Toast.makeText(SplashActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                gotoSkip();
            }
        });
    }

    public void vpnDialog() {
        try {
            final AlertDialog.Builder alert = new AlertDialog.Builder(SplashActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_vpn, null);
            TextView btn_con = (TextView) mView.findViewById(R.id.vcontinue);
            TextView vskip = (TextView) mView.findViewById(R.id.vskip);
            if (!PowerPreference.getDefaultFile().getBoolean(Constant.VpnSkipOnOff, true)) {
                vskip.setVisibility(View.GONE);
            }
            alert.setView(mView);
            final AlertDialog alertDialog = alert.create();
            if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {

                int ui_flags =
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

                alertDialog.getWindow().
                        setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                alertDialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
            }
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            alertDialog.setCanceledOnTouchOutside(false);

            btn_con.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.dismiss();
                    PowerPreference.getDefaultFile().putBoolean(Constant.Vpnaccept,true);
                    login();
                }
            });
            vskip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    gotoSkip();
                }
            });

            alertDialog.show();

        } catch (Exception e) {
            Log.w("Catch", Objects.requireNonNull(e.getMessage()));
        }
    }

}