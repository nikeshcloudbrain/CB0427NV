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
import com.creditscore.creditscorecheck.loan.databinding.ActivityScoreOfflineBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class ScoreOfflineActivity extends AppCompatActivity {

    ActivityScoreOfflineBinding binding;

    int y, c1, c2, c3, c4, c5, c6;

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
        binding = ActivityScoreOfflineBinding.inflate(getLayoutInflater());
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
                binding.text11.setText(i + "+ Years");
                y = i;
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
                binding.text2.setText(i + "+");
                c1 = i;
            }
        });
        binding.seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {

                binding.text3.setText(i + "+");
                c2 = i;
            }
        });
        binding.seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {

                binding.text4.setText(i + "+");
                c3 = i;
            }
        });
        binding.seek5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                binding.text5.setText(i + "+");
                c4 = i;
            }
        });
        binding.seek6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {

                binding.text6.setText(i + "+");
                c5 = i;
            }
        });
        binding.seek7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {

                binding.text7.setText(i + "+");
                c6 = i;
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // intent.putExtra("YOUR_DATA_KEY", data);

                PowerPreference.getDefaultFile().putInt("year", y);
                PowerPreference.getDefaultFile().putInt("c1", c1);
                PowerPreference.getDefaultFile().putInt("c2", c2);
                PowerPreference.getDefaultFile().putInt("c3", c3);
                PowerPreference.getDefaultFile().putInt("c4", c4);
                PowerPreference.getDefaultFile().putInt("c5", c5);
                PowerPreference.getDefaultFile().putInt("c6", c6);

                new InterAds().showInterAds(ScoreOfflineActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), ScoreOffline2Activity.class);
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