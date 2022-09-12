package com.creditscore.creditscorecheck.loan.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;



import com.creditscore.creditscorecheck.loan.R;
import com.preference.PowerPreference;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.VpnException;


public class Constant {


    public static String POLICY_ACCEPT="POLICY_ACCEPT";
    public boolean isEmpty(EditText editText) {
        return editText.getText().toString().length() > 0;
    }

    public static String ADS_NORMAL = "ADS_NORMAL";
    public static String ADS_TINY = "ADS_TINY";

    public static String DEF_VALUE = "#4d085bff";
    public static Integer[] adsQurekaInters = new Integer[]{R.drawable.qureka_inter1, R.drawable.qureka_inter2, R.drawable.qureka_inter3, R.drawable.qureka_inter4, R.drawable.qureka_inter5};
    public static Integer[] adsQurekaGifInters = new Integer[]{R.drawable.qureka_round1, R.drawable.qureka_round2, R.drawable.qureka_round3, R.drawable.qureka_round4, R.drawable.qureka_round5};


    public static final String QUREKA_ADS = "QurekaLink";
    public static final String POLICY = "PolicyLink";

    public static final String CustomAdsOnOff = "CustomAdsOnOff";
    public static final String FULL_SCREEN = "FullScreenOnOff";
    public static final String AdsOnOff = "AdsOnOff";
    public static final String PolicyOnOff = "PolicyOnOff";


    public static final String GoogleListNativeOnOff = "GoogleListNativeOnOff";

    public static final String GoogleMiniNativeOnOff = "GoogleMiniNativeOnOff";
    public static final String GoogleLargeNativeOnOff = "GoogleLargeNativeOnOff";

    public static final String SERVER_INTERVAL_COUNT = "GoogleIntervalCount";
    public static final String APP_INTERVAL_COUNT = "APP_INTERVAL_COUNT";


    public static final String BACK_ADS = "GoogleBackInterOnOff";
    public static final String SERVER_BACK_COUNT = "GoogleBackInterIntervalCount";
    public static final String APP_BACK_COUNT = "APP_BACK_COUNT";


    public static final String GoogleSplashOpenAdsOnOff = "GoogleSplashOpenAdsOnOff";
    public static final String GoogleExitSplashInterOnOff = "GoogleExitSplashInterOnOff";


    public static final String GoogleNativeTextOnOff = "GoogleNativeTextOnOff";
    public static final String GoogleNativeText = "GoogleNativeText";
    public static final String NativeTrans = "NativeTrans";
    public static final String NativeBackgroundColor = "NativeBackgroundColor";
    public static final String HomeNativeBackgroundColorOnOff = "HomeNativeBackgroundColorOnOff";
    public static final String AllPagesNativeBackgroundOnOff = "AllPagesNativeBackgroundOnOff";

    public static final String AllPagesNativeBackgroundCount = "AllPagesNativeBackgroundCount";

    public static final String ListNativeWhichOne = "ListNativeWhichOne";
    public static final String ListNativeAfterCount = "ListNativeAfterCount";


    public static final String QurekaOnOff = "QurekaOnOff";
    public static final String QurekaAppOpenOnOff = "QurekaAppOpenOnOff";
    public static final String QurekaInterOnOff = "QurekaInterOnOff";
    public static final String QurekaBackInterOnOff = "QurekaBackInterOnOff";
    public static final String QurekaInterCloseOnOff = "QurekaInterCloseOnOff";
    public static final String QurekaMiniNativeOnOff = "QurekaMiniNativeOnOff";
    public static final String QurekaLargeNativeOnOff = "QurekaLargeNativeOnOff";
    public static final String QurekaListNativeOnOff = "QurekaListNativeOnOff";


    public static final String ShowDialogBeforeAds = "ShowDialogBeforeAds";
    public static final String DialogTimeInSec = "DialogTimeInSec";

    public static final String VpnOnOff = "VpnOnOff";
    public static final String Vpnaccept = "Vpnaccept";

    public static final String VpnSkipOnOff = "VpnSkipOnOff";
    public static final String VpnCountry = "VpnCountry";

    public static final String OneSignalAppId = "OneSignalAppId";

    public static String OPENAD = "GoogleAppOpenAds";
    public static String INTERID = "GoogleInterAds";
    public static String NATIVEID = "GoogleNativeAds";
    public static String GoogleAppIdAds = "GoogleAppIdAds";

    public static String mAds = "mAds";
    public static String adsLog = "adsLog";
    public static String errorLog = "errorLog";

    public static  native String getMainApi();


    public static String loanType = "loanType";

    public static void Log(String message) {
        Log.e("errorLog", message);
    }


    public static boolean checkInternet(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }




    public static void gotoAds(Context context) {
        try {
            String packageName = "com.android.chrome";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorMain));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(context, Uri.parse(PowerPreference.getDefaultFile().getString(Constant.QUREKA_ADS, "https://1064.win.qureka.com/")));
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    public static void gotoUrl(Context context, Uri url) {
        try {
            String packageName = "com.android.chrome";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorMain));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(context, url);
        } catch (Exception e) {
            Log.e("Log", e.toString());
        }
    }

    public static boolean isConnected() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();

                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }

        return false;
    }

    public static void logout() {

        try {
            UnifiedSdk.getInstance().getBackend().logout(new CompletableCallback() {
                @Override
                public void complete() {

                }

                @Override
                public void error(@NonNull VpnException e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void stopVpn() {
        try {
            UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
                @Override
                public void complete() {
                    logout();
                }

                @Override
                public void error(@NonNull VpnException e) {
                    logout();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
