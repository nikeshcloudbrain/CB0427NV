package com.creditscore.creditscorecheck.loan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creditscore.creditscorecheck.loan.Adapter.CustomAdapter;
import com.creditscore.creditscorecheck.loan.R;
import com.creditscore.creditscorecheck.loan.ads.InterAds;
import com.creditscore.creditscorecheck.loan.ads.LargeNativeAds;
import com.creditscore.creditscorecheck.loan.model.Ads;
import com.creditscore.creditscorecheck.loan.model.Update;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.google.gson.GsonBuilder;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.List;

public class AdAppActivity extends AppCompatActivity {
    String data;
    private RecyclerView recycler;
    private CustomAdapter customAdapter;
    Button btncontinue;
    List<Ads> arrayList = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
        if (PowerPreference.getDefaultFile().getBoolean(Constant.FULL_SCREEN, true)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        new LargeNativeAds().showNativeAds(this, null);
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_app);

        recycler = findViewById(R.id.rvad);
        recycler.setLayoutManager(new GridLayoutManager(this, 3));

        data = PowerPreference.getDefaultFile().getString("key");

        //http request
        Update appData = new GsonBuilder().create().fromJson(PowerPreference.getDefaultFile().getString(Constant.mAds), Update.class);
        this.arrayList.addAll(appData.getCustomadsData());

        customAdapter = new CustomAdapter(arrayList, getApplicationContext());
        recycler.setAdapter(customAdapter);


        btncontinue = (Button) findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InterAds().showInterAds(AdAppActivity.this, new InterAds.OnAdClosedListener() {
                    @Override
                    public void onAdClosed() {
                        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(i);
                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        showDialog(AdAppActivity.this);

    }

    public void showDialog(Activity activity) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(AdAppActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.exit_dialog_layout, null);
        Button btn_no = (Button) mView.findViewById(R.id.btnno);
        Button btn_rate = (Button) mView.findViewById(R.id.btnRateus);
        Button btn_yes = (Button) mView.findViewById(R.id.btnyes);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(false);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                rateus();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (PowerPreference.getDefaultFile().getBoolean(Constant.GoogleExitSplashInterOnOff, true)) {
                    PowerPreference.getDefaultFile().putInt(Constant.APP_INTERVAL_COUNT, 0);
                    new InterAds().showInterAds(activity, new InterAds.OnAdClosedListener() {
                        @Override
                        public void onAdClosed() {
                            Intent intent = new Intent(activity, ExitActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    });
                } else {
                    Intent intent = new Intent(activity, ExitActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    activity.startActivity(intent);
                    activity.finish();
                }


            }
        });
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                new LargeNativeAds().showNativeAds(AdAppActivity.this, alertDialog);
            }
        });
        alertDialog.show();
    }

    public void rateus() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

}