package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.creditscore.creditscorecheck.loan.R;
import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityHomeScreenBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

import java.util.Objects;

public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        new LargeNativeAds().showNativeAds(this, null);
    new MiniNativeAds().showNativeAds(this, null);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InterAds().showInterAds(HomeScreen.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(HomeScreen.this, MainActivity.class));

                       
                    }
                });


            }
        });

        binding.btnRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });

        binding.btnPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String packageName = "com.android.chrome";
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    builder.setToolbarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorMain));
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.intent.setPackage(packageName);
                    customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                    customTabsIntent.launchUrl(getApplicationContext(), Uri.parse(PowerPreference.getDefaultFile().getString(Constant.POLICY, "https://1064.win.qureka.com/")));
                } catch (Exception e) {
                    Log.e("TAG", e.toString());
                }
            }
        });

        binding.btnShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareApp();

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (PowerPreference.getDefaultFile().getBoolean(Constant.CustomAdsOnOff, true))
            new BackInterAds().showInterAds(HomeScreen.this, new BackInterAds.OnAdClosedListener() {
                @Override
                public void onAdClosed() {
                   finish();
                }
            });
        else
            showRateDialog(this);
    }

    public void showRateDialog(Activity activity) {
        try {
            final AlertDialog.Builder alert = new AlertDialog.Builder(HomeScreen.this);
            View mView = getLayoutInflater().inflate(R.layout.exit_dialog_layout, null);
            Button btn_no = (Button) mView.findViewById(R.id.btnno);
            Button btn_rate = (Button) mView.findViewById(R.id.btnRateus);
            Button btn_yes = (Button) mView.findViewById(R.id.btnyes);

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

            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                }
            });
            btn_rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    rateus();
                }
            });

            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (PowerPreference.getDefaultFile().getBoolean(Constant.GoogleExitSplashInterOnOff, true)) {
                        PowerPreference.getDefaultFile().putInt(Constant.APP_INTERVAL_COUNT, 0);
                        new InterAds().showInterAds(activity, new InterAds.OnAdClosedListener() {
                            @Override
                            public void onAdClosed() {
                                Intent intent = new Intent(activity, ExitActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                activity.startActivity(intent);
                                activity.finish();
                            }
                        });
                    } else {
                        Intent intent = new Intent(activity, ExitActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        activity.startActivity(intent);
                        activity.finish();
                    }


                }
            });
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    new LargeNativeAds().showNativeAds(HomeScreen.this, alertDialog);
                }
            });
            alertDialog.show();

        } catch (Exception e) {
            Log.w("Catch", Objects.requireNonNull(e.getMessage()));
        }
    }


    public void rateus() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void shareApp() {
        try {

            String shareMsg = "Download " + getString(R.string.app_name) + " App : " + Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());

            Intent i = new Intent();
            i.setAction("android.intent.action.SEND");
            i.setType("text_bg/plain");
            i.putExtra("android.intent.extra.SUBJECT", getString(R.string.app_name));
            i.putExtra("android.intent.extra.TEXT", shareMsg);
            startActivity(Intent.createChooser(i, "Share Application"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}