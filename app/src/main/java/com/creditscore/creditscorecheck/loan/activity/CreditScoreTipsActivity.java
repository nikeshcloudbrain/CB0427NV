package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityCreditScoreTipsBinding;
import com.creditscore.creditscorecheck.loan.model.Tips;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

import java.util.ArrayList;

public class CreditScoreTipsActivity extends AppCompatActivity {

    ActivityCreditScoreTipsBinding binding;
    ArrayList<Tips> tips = new ArrayList<Tips>();

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
        binding = ActivityCreditScoreTipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.include.toolbarText.setText("Credit Score Tips");

        binding.tips1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InterAds().showInterAds(CreditScoreTipsActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditTips1Activity.class);
                        // intent.putExtra("YOUR_DATA_KEY", data);
                        startActivity(intent);
                    }
                });

            }
        });

        binding.tips2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips.clear();
                String[] title = {"Get The Best Credit Card", "Quick Loan Approval", "Better Interest Rate", "Loans Made More Affordable"};
                String[] des = {"A Good Credit Score May Get you the best of Credit Cards.Get a Feature-loaded card and reap the benefits. if you have a good credit Score,you can avail loans and credit cards faster and with ease.", "A good Credit Score works like an expressway for your loan application. Banks may approve your application quickly and readily.", "With the backing of a good Credit Score, you can bargain for a lower rate of interest on loans and Credit Cards.", "Loans come saddled with processing fees and many other charges. You can bargain your way out of some of these charges with a good Credit Score. Check your Credit Score right away and see if you are eligible for all these benefits. You can check your score on BankBazaar at zero cost."};
                for (int i = 0; i < title.length; i++) {
                    tips.add(new Tips(title[i], des[i]));


                }

                new InterAds().showInterAds(CreditScoreTipsActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), TipsTextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Birds", tips);
                        intent.putExtra("toolbartext", "Benefits Of Credit Score");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });



            }
        });

        binding.tips3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tips.clear();
                String[] title = {"Credit Score ? Credit Report", "Checking Your Score Will Not Hurt It\n", "Credit Score Math", "Keep An Eye On Fraud"};
                String[] des = {"Credit Score is one of the most\nmisunderstood topics in the financial book Here are four secrets to help you understand your Credit Score better. Your Credit Score is calculated based on information present in your credit report. Your credit report presents details about your credit accounts, credit application and debt repayment, among others.", "When you or a company enquires about your Credit Score, it's called a soft enquiry and it does not hurt your credit score.", "There are five prime factors that go towards deciding your Credit Score. They are - debt repayment, credit utilisation ratio, average credit age, type of credit account (secured / unsecured) and Credit Score enquiries made.", "You did nothing wrong and yet your Credit Score is low? Please go through your Credit Report thoroughly and immediately report any unauthorized activities to your bank to correct your score.\n\nIt's important that you check your Credit Score regularly. BankBazaar has partnered with Experian and we will fetch your Credit Score at no cost. It's just a matter of a few minutes."};
                for (int i = 0; i < title.length; i++) {
                    tips.add(new Tips(title[i], des[i]));


                }

                new InterAds().showInterAds(CreditScoreTipsActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), TipsTextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Birds", tips);
                        intent.putExtra("toolbartext", "Secrets Of Credit Score");

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });


            }
        });

        binding.include.ivBack.setOnClickListener(new View.OnClickListener() {
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