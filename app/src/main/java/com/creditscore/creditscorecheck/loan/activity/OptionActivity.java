package com.creditscore.creditscorecheck.loan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.creditscore.creditscorecheck.loan.ads.BackInterAds;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.ads.MiniNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ActivityOptionBinding;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.preference.PowerPreference;

public class OptionActivity extends AppCompatActivity {

    ActivityOptionBinding binding;

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
        binding = ActivityOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "What is CIBIL?\n\nAns-TransUnion CIBIL Limited (or CIBIL Credit Information Bureau India Limited CIBIL) is one of the foremost credit bureaus licensed to operate by the Reserve Bank of India (RBI), CIBIL gathers and preserves records of an individual's payments: pertaining to credit cards and loans.\n \n \n How does your CIBIL score/report matter?\n\nAns: CIBIL score or report plays a very important role in an individual's financial life. So much so that lenders totally rely on the reports to assess the credit worthiness. and as to how much credit they can give you. So it's always better to be on the higher side of the credit score because there will be brighter chances of getting credit approvals or else they would not trust you with their lending. A higher score will also help in getting some of the best interest rates.\n \n Who prepares the CIBIL score or report?\n\nAns:-The report is obviously prepared by Credit Information Bureau (India) Limited. The report is prepared based on the financial behavior of a customer. Hence, the bureau receives information from financial institutions or banks about your credit behavior patterns which will then decide the scores and those will help in creating credit report.\n\nHow do high and low credit score affect a credit profile?\n\nAns:-A profile which has a high credit score has brighter chances of loan approval whereas a low score reflects low credibility resulting in less credit approvals.";

                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        intent.putExtra("type", "FAQ");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });


            }
        });


        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Unlimited Access: You can check your CIBIL Score & Report every 24 hours within your subscription period (1 month, 6 months, 12 months)\n\nHere is what members get: Unlimited Access Loan Offers Credit, Monitoring, Dispute Resolution and more.\n";
                String copylink = "https://www.cibil.com/";
                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Home");
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });

            }
        });

        binding.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Introducing Score Simulator with your CIBIL Score subscription. Monitoring & maintaining your credit health now becomes easier.\n\nSubscription Plan : Premium 1200(1 Year) Standard 800(6 Month) Basic 550(1 Month)";
                String copylink = "https://www.cibil.com/creditscore/";

                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Buy");
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });



            }
        });

        binding.company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Introducing CIBIL RANK with your Company Credit Report at no additional cost.\n\nThe CIBIL RANK and Company Credit\n\nReport is an indication of your loaneligibility.";
                String copylink = "https://www.cibil.com/company-credit-report";

                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Company");
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });



            }
        });

        binding.cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Calculate or Estimate your CIBIL Score for Free. Great! Now that you are here, you would be keen to check your online Cibil score free of cost.\n\nWe have been helping friends like you get a free Cibil score with an accuracy of up to 93%.";
                String copylink = "https://cashkumar.com/free-online-cibil-score-check";

                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Calculate");
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                });

            }
        });

        binding.dispute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Do you want to correct information on your CIBIL Report, and don't know where to start?\n\nYou can now initiate a dispute online with CIBIL for inaccuracies, account ownership and duplication of information that reflect in your Credit Report.CIBIL does not charge its consumers for this service.";
                String copylink = "https://www.cibil.com/dispute-resolution";


                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Dispute");
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                });


            }
        });

        binding.center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "India Ratings and Research (Ind-Ra) is India's most respected credit rating agency committed to providing India's credit markets accurate, timely and prospective credit opinions";
                String copylink = "https://www.indiaratings.co.in/";

                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Media");
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                });


            }
        });

        binding.mantri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Check your FREE Credit Score and reportInstantly.\n\nHow It WorksGet a Credit Score for Free fromCreditMantri.\n\n• Flag off problem areas where you need expertise/help.\n• Improve your Credit Score and Build\nYour Credit profile.\n • Reduce EMI and borrowing costs andmore.\n";
                String copylink = "https://www.creditmantri.com/";

                new InterAds().showInterAds(OptionActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(getBaseContext(), CreditDescriptionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Birds", title);
                        bundle.putString("copylink", copylink);
                        intent.putExtra("type", "Mantri");
                        intent.putExtras(bundle);
                        startActivity(intent);


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