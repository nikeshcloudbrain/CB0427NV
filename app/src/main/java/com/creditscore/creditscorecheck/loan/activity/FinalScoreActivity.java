package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityFinalScoreBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class FinalScoreActivity extends AppCompatActivity {
    ActivityFinalScoreBinding binding;
    int y, c1, c2, c3, c4, c5, c6, a, a2, h1, h2, score;

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
        binding = ActivityFinalScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        score = getIntent().getIntExtra("Score", 0);
        binding.year.setText("When was the last negative item on you credit report? \n:-" + y + "year");
        binding.card.setText("Credit cards:-\t" + c1 + "+");
        binding.Mort.setText("Mortgages:-\t" + c2 + "+");
        binding.Retail.setText("Retail Finances:-\t" + c3 + "+");
        binding.Auto.setText("Auto Loans:-\t" + c4 + "+");
        binding.Student.setText("Student Loans:-\t" + c5 + "+");
        binding.other.setText("Other Loans:-\t" + c6 + "+");
        binding.creditlimit.setText("Add up all credit limits on your open credit cods: \n:-\t" + a + "+");
        binding.balance.setText("Add up all the most resent statement balances on your credit card accounts: \n:-\t" + a2 + "+");
        binding.amonth.setText("How many times have you applied for credit in the last 6 month? \n:-\t" + h1 + "+");
        binding.oldestactivity.setText("When did you first open your oldest activity credit or loan account? \n:-\t" + h2 + "+");
        binding.score.setText("Your Score:-\t" + score);


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