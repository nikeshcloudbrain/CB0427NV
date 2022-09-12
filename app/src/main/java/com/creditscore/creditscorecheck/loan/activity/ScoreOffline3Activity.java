package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityScoreOffline3Binding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class ScoreOffline3Activity extends AppCompatActivity {
    ActivityScoreOffline3Binding binding;
    int h1, h2;

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
        binding = ActivityScoreOffline3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.include2.toolbarText.setText("Check Score Offline");

        binding.seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                binding.text1.setText("₹" + i);
                h1 = i;
            }
        });
        binding.seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                binding.text2.setText("₹" + i);
                h2 = i;
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PowerPreference.getDefaultFile().putInt("h1", h1);
                PowerPreference.getDefaultFile().putInt("h2", h2);


                new InterAds().showInterAds(ScoreOffline3Activity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CheckCreditOfflineActivity.class);
                        // intent.putExtra("YOUR_DATA_KEY", data);
                        startActivity(intent);

                    }
                });
            }
        });

        binding.include2.ivBack.setOnClickListener(new View.OnClickListener() {
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