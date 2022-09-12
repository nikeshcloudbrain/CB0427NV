package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityMainBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        new LargeNativeAds().showNativeAds(this, null);
//            new MiniNativeAds().showNativeAds(this, null);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCheckCreditScoreOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InterAds().showInterAds(MainActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivity.this, OptionActivity.class));
                    }
                });

            }
        });

        binding.btnCheckCreditScoreOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InterAds().showInterAds(MainActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivity.this, ScoreOfflineActivity.class));
                    }
                });

            }
        });

        binding.btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InterAds().showInterAds(MainActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivity.this, CreditScoreTipsActivity.class));
                    }
                });

            }
        });

        binding.btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InterAds().showInterAds(MainActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivity.this, CalculaterActivity.class));
                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        new BackInterAds().showInterAds(this, new BackInterAds.OnAdClosedListener() {
            @Override
            public void onAdClosed() {
                finish();
            }
        });
    }
}