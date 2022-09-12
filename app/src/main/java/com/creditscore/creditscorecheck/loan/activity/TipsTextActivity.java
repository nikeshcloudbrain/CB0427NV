package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.creditscore.creditscorecheck.loan.Adapter.TipsAdapter;
import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityTipsTextBinding;
import com.creditscore.creditscorecheck.loan.model.Tips;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

import java.util.ArrayList;

public class TipsTextActivity extends AppCompatActivity {

    ActivityTipsTextBinding binding;

    ArrayList<Tips> tips = new ArrayList<Tips>();

    @Override
    protected void onResume() {
        super.onResume();
        if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
      //  new NativeAds().showNativeAds(this, null, Constant.ADS_NORMAL);
            new MiniNativeAds().showNativeAds(this, null);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTipsTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tips = this.getIntent().getExtras().getParcelableArrayList("Birds");

        Log.e("tips", tips.get(1).title);

        binding.rvTips.setLayoutManager(new LinearLayoutManager(this));

        TipsAdapter tipsAdapter = new TipsAdapter(tips, getApplicationContext(),this);
        binding.rvTips.setAdapter(tipsAdapter);


        binding.include2.toolbarText.setText(getIntent().getExtras().getString("toolbartext"));
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