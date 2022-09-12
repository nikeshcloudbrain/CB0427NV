package com.creditscore.creditscorecheck.loan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityCheckOfflineBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class CheckCreditOfflineActivity extends AppCompatActivity {
    ActivityCheckOfflineBinding binding;
    int y, c1, c2, c3, c4, c5, c6, a, a2, h1, h2;

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
        binding = ActivityCheckOfflineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.include3.toolbarText.setText("Check Score Offline");
        y = PowerPreference.getDefaultFile().getInt("year", 0);
        c1 = PowerPreference.getDefaultFile().getInt("c1", 0);
        c2 = PowerPreference.getDefaultFile().getInt("c2", 0);
        c3 = PowerPreference.getDefaultFile().getInt("c3", 0);
        c4 = PowerPreference.getDefaultFile().getInt("c4", 0);
        c5 = PowerPreference.getDefaultFile().getInt("c5", 0);
        c6 = PowerPreference.getDefaultFile().getInt("c6", 0);
        a = PowerPreference.getDefaultFile().getInt("a", 0);
        a2 = PowerPreference.getDefaultFile().getInt("a2", 0);
        h1 = PowerPreference.getDefaultFile().getInt("h1", 0);
        h2 = PowerPreference.getDefaultFile().getInt("h2", 0);

        int s = this.y * 60;

        binding.text.setText(String.valueOf(s));

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InterAds().showInterAds(CheckCreditOfflineActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), FinalScoreActivity.class);
                        intent.putExtra("Score", s);
                        startActivity(intent);
                    }
                });

            }
        });

        binding.include3.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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