package com.creditscore.creditscorecheck.loan.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityCalculaterBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class CalculaterActivity extends AppCompatActivity {
    ActivityCalculaterBinding binding;



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
        binding = ActivityCalculaterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String obj = binding.etAmount.getText().toString();
                String obj2 = binding.interest.getText().toString();
                String obj3 = binding.month.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(CalculaterActivity.this, "Enter Loan Amount", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(obj2)) {
                    Toast.makeText(CalculaterActivity.this, "Enter Interest Rate", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(obj3)) {
                    Toast.makeText(CalculaterActivity.this, "Enter Loan Period", Toast.LENGTH_SHORT).show();
                } else {
                    Double valueOf = Double.valueOf(Double.parseDouble(obj));
                    Double valueOf2 = Double.valueOf(Double.parseDouble(obj2));
                    Double valueOf3 = Double.valueOf(Double.parseDouble(obj3));
                    Double valueOf4 = Double.valueOf(((valueOf.doubleValue() * valueOf2.doubleValue()) / 100.0d) * (valueOf3.doubleValue()/12));
                    Double valueOf5 = Double.valueOf(valueOf.doubleValue() + valueOf4.doubleValue());
                    Double valueOf6 = Double.valueOf(valueOf5.doubleValue() / valueOf3.doubleValue());
                    long tint = Math.round(valueOf4.doubleValue());
                    long tpymnt = Math.round(valueOf5.doubleValue());
                    long memi = Math.round(valueOf6.doubleValue());

                    Intent intent = new Intent(getBaseContext(), CalculatedEmiActivity.class);
                    intent.putExtra("month_emi", memi);
                    intent.putExtra("total_in", tint);
                    intent.putExtra("total_pay", tpymnt);
                    startActivity(intent);

                }


            }
        });


        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.month.setText("");
                binding.etAmount.setText("");
                binding.interest.setText("");
            }
        });

        binding.include2.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.include2.toolbarText.setText("EMI Calculator");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
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