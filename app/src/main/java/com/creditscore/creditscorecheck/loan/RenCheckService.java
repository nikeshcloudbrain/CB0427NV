package com.creditscore.creditscorecheck.loan;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class RenCheckService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        PowerPreference.getDefaultFile().putBoolean("running", false);
        Constant.stopVpn();
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
        PowerPreference.getDefaultFile().putBoolean("running", false);
        Constant.stopVpn();
        super.onDestroy();
    }
}