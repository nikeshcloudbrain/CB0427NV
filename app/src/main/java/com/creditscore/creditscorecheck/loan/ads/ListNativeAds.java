package com.creditscore.creditscorecheck.loan.ads;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class ListNativeAds {

    public void showListNativeAds(Activity activity, FrameLayout nativeAd, TextView adSpace) {
        if (PowerPreference.getDefaultFile().getBoolean(Constant.AdsOnOff, true)) {
            if (PowerPreference.getDefaultFile().getInt(Constant.ListNativeWhichOne, 1) == 1) {
                new LargeNativeAds().showListNativeAds(activity, nativeAd, adSpace);
            } else {
                new MiniNativeAds().showListNativeAds(activity, nativeAd, adSpace);
            }
        } else {
            nativeAd.setVisibility(View.GONE);
            adSpace.setVisibility(View.GONE);
        }
    }
}
