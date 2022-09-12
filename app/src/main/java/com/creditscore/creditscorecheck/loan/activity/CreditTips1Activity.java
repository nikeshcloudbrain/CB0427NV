package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityCreditTips1Binding;
import com.creditscore.creditscorecheck.loan.model.Tips;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

import java.util.ArrayList;

public class CreditTips1Activity extends AppCompatActivity {

    ActivityCreditTips1Binding binding;
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
        binding = ActivityCreditTips1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.include2.toolbarText.setText("Credit Score Tips");

        binding.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips.clear();
                String[] title = {"What is Credit Score?", "Steps To Get Your Free Credit Score?", "Why BankBazaar is Asking me for my PAN and Phone Details for the Credit Score?"};
                String[] des = {"A credit score is a measure of an individual's ability to pay back the borrowed amount. It is the numerical representation of their creditworthiness. A credit score is a 3 digit number that falls in the range of 300-900, 900 being the highest. You should always work towards reaching a credit score that is close to 900. A higher credit score offers you several benefits and helps you at the time of getting a loan or a credit card. Having a low credit score suggests you have not been a responsible borrower and have been slacking off repaying the borrowed sum. Credit scores are calculated by the credit bureaus in the country after taking into consideration several factors like the length of your credit history, repayment records, credit inquiries, among others.", "->step 1 Visit Bankbazaar.com and click on the \"Check your free Experian credit score\n\n-> Step 2 You can also visit (https://bit.ly\n\n-> step 3: Select your gender\n\n-> step 4 Mention your age\n\n->step 5: Select the city you currently live\n\n-> step 6: Mention your occupation(salaried/ self-employed). If you are a salaried individual, specify the name of your score\n\n-> step 7: Specify your fixed net monthly >step 8: Add your personal details like first name and last name salary\n\n-> step 9: Mention your contact details such as mobile number and email. (The mobile number is used to verify if you are the right owner of your credit information)\n\n->step 10: You will now have to verify your mobilenumber with an OTP\n\n-> step 11: Specify your PAN number\n\n-> step 12: You will now get your free Experian credit score", "Why BankBazaar is Asking me for my PAN and Phone Details for the Credit Score?\", \"We need your PAN and phone details to extract your Credit Score from Experian. We assure you that your personal information is secure with us. We will not use your PAN or phone details for any purpose other than to extract your Credit Score. We will not share your details with anyone else. Pinky swear!"};
                for (int i = 0; i < title.length; i++) {
                    tips.add(new Tips(title[i], des[i]));


                }

                new InterAds().showInterAds(CreditTips1Activity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), TipsTextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Birds", tips);
                        intent.putExtra("toolbartext", "Tips 1");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        });

        binding.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips.clear();
                String[] title = {"Credit Score Range and What It Means?", "Who Computes Credit Score?", "Why Should I check my Credit Score?", "Why is Bank Bazaar Giving me my Credit Score for Free?"};
                String[] des = {"A credit score ranges between 300-900. The closer your credit score is to 900, higher the chances are for you to get a good deal for loan as well as credit card Let's take a look at the different credit score range:\n\n In order to calculate your credit score, you need to have a credit history. If you have no credit history, your credit report will mention that your credit score is NA/NH. 300-550 A credit score in this range is considered as a poor credit score.", "Your Credit Score is computed by Credit Information Companies There are four companies in Indian which do the job - CIBIL TransUnion, Experian, Equifax and High Mark Let's unveil the mystery around how these companies compute your score. When you make a transaction the one that is relevant to determine your score banks send details about it to all four credit bureaus. To send details to all credit agencies i is a mandate by the RBI. Essentially, banks keep Credit Information Companies up-to-date about your monetary habits. If a bank needs to check your Credit Score, they can approach any one of the bureaus. It doesn't matter which one because all will have the same score for you all four are equally authoritative and on par with each other. After receiving information from the bank, credit bureaus get down to the task of collecting more information about your financial habits from other banks and financial institutions. ", "It is very important that you keep a close eye on your Credit Score. It is the best way to gauge your chances to get a line of credit. Another reason why you should track your score is to know if it dips, or if an error has been made by credit agencies while calculating your score. This will help you make timely amends.", "BankBazaar feels that you should always be in complete command of your personal finances. In order to elp this goal, we have made provisions for you to check your Credit Score for free. Knowing your Score before applying for a loan can help greatly."};
                for (int i = 0; i < title.length; i++) {
                    tips.add(new Tips(title[i], des[i]));


                }
                new InterAds().showInterAds(CreditTips1Activity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), TipsTextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Birds", tips);
                        intent.putExtra("toolbartext", "Tips 2");

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        });

        binding.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips.clear();
                String[] title = {"Does my Credit Score Get Impacted if I Enquire About it?", "What Makes Your Credit Score Go Down?", "Change in Credit Score - How Often Does it Happen?", "How Do Big Fluctuations Happen?"};
                String[] des = {"It depends on the kind of enquiry being made. There are two types of enquiries hard and soft enquiry, Hard enquiries send your Credit Score down by a few points, while soft enquiries do not impact your Credit Score. An enquiry made by an individual is called a soft enquiry. BankBazaar will make a soft enquiry on your behalf when getting your Credit Score from Experian. Hence, this will not impact your Credit Score in any manner. Moreover, checking your Credit Score on our website is free!", "It is understood that having high balances on your credit cards can significantly reduce your credit score. Apart from that, there are several other factors that can hurt your credit score.", "When you work on improving your credit, you should be very patient, so as to not get discouraged. Credit scores are calculated from your credit report. When you request for the score from multiple credit reporting you may see a slight variance in the figures. This is fine, as long as the difference is not massive.", "Most of the changes in your credit score happen incrementally. Although you would not see changes instantly, over a period of time this can add up to a considerable amount.\n\nthere are certain factors that could instantly have a huge negative impact on your score. This includes a delinquency, i.e., a significantly late payment such as a 30-day delay on a credit."};
                for (int i = 0; i < title.length; i++) {
                    tips.add(new Tips(title[i], des[i]));


                }
                new InterAds().showInterAds(CreditTips1Activity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), TipsTextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Birds", tips);
                        intent.putExtra("toolbartext", "Tips 3");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        });

        binding.t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips.clear();
                String[] title = {"Calculation of Credit Score", "Monitoring Your Credit Score", "Soft vs. Hard Credit Inquiry"};
                String[] des = {"Credit bureaus in the country compute credit scores after considering several factors such as credit history and repayment behaviour. There are total of four credit bureaus in the\n\ncountry viz - TransUnion CIBIL, Experian, Equifax, and CRIF High Mark. All these credit bureaus are licenced by the Reserve Bank of India (RBI). The financial institutions in the country send credit details of an individual on a monthly basis to credit bureaus. Each credit bureau has their own algorithm\n\nnand method of calculating credit scores. Below are five factors that are taken into consideration at the time of calculating the credit score.", "The credit score is updated on a monthly basis based on the relevant information provided by financial institutions. It is advisable to keep an eye on the credit score to determine your financial credibility while: applying for a loan or a credit card. It will help you in avoiding the situation where your credit application are getting rejected due to a poor score. By monitoring the score on a regular will help in identifying mistakes and correcting errors before they are too late.\n\n", "When you are obtaining your credit score or report, it is considered to be a soft inquiry and it doesn't have any adverse impact on your score. When the bank or lender inquiries for a credit report, it is referred to as hard inquiry and it can reduce your score. You can be rest assured that your credit score won't get impacted due to soft inquiries."};
                for (int i = 0; i < title.length; i++) {
                    tips.add(new Tips(title[i], des[i]));


                }

                new InterAds().showInterAds(CreditTips1Activity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), TipsTextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Birds", tips);
                        intent.putExtra("toolbartext", "Tips 4");

                        intent.putExtras(bundle);
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