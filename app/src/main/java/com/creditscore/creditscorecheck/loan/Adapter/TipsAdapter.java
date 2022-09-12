package com.creditscore.creditscorecheck.loan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creditscore.creditscorecheck.loan.ads.ListNativeAds;
import com.creditscore.creditscorecheck.loan.databinding.ItemTipsBinding;
import com.creditscore.creditscorecheck.loan.databinding.NativeAdBannerBinding;
import com.creditscore.creditscorecheck.loan.databinding.NativeAdNormalSmallBinding;
import com.creditscore.creditscorecheck.loan.model.Tips;
import com.creditscore.creditscorecheck.loan.util.Constant;
import com.creditscore.creditscorecheck.loan.viewHolder.LargeAdViewHolder;
import com.creditscore.creditscorecheck.loan.viewHolder.SmallAdViewHolder;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TipsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    // List to store all the contact details
    private List<Tips> contactsList;
    private Context mContext;
    private Activity mactivity;
    private final int STORE_TYPE = 0;
    public final int AD_TYPE_GOOGLE = 91;
    public int AD_DISPLAY_COUNT = 4;

    // Counstructor for the Class
    public TipsAdapter(List<Tips> contactsList, Context context, Activity mactivity) {
        this.contactsList = contactsList;
        this.mContext = context;
        this.mactivity = mactivity;
        setAds(true);
    }

    public void setAds(boolean isCheck) {
        AD_DISPLAY_COUNT = PowerPreference.getDefaultFile().getInt(Constant.ListNativeAfterCount, 4);
        contactsList.removeAll(Collections.singleton(null));

        ArrayList<Tips> tempArr = new ArrayList<>();


        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.size() > AD_DISPLAY_COUNT) {
                if (i != 0) {
                    if (i % AD_DISPLAY_COUNT == 0) {
                        tempArr.add(null);
                    }
                }
                tempArr.add(contactsList.get(i));
            } else {
                tempArr.add(contactsList.get(i));
            }
        }
        if (contactsList.size() > 0) {
            if (contactsList.size() % AD_DISPLAY_COUNT == 0) {
                tempArr.add(null);
            }
        }

        this.contactsList = tempArr;

        if (isCheck) {
            notifyDataSetChanged();
        }
    }

    public class AdHolder extends RecyclerView.ViewHolder {
        NativeAdNormalSmallBinding binding;

        AdHolder(NativeAdNormalSmallBinding view) {
            super(view.getRoot());
            binding = view;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTipsBinding binding;

        public ViewHolder(@NonNull ItemTipsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    /*@Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.item_tips, parent, false);
        return new ContactHolder(view);
    }
*/
 /*   @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == AD_TYPE_GOOGLE) {
            return new AdHolder(NativeAdNormalSmallBinding.inflate(LayoutInflater.from(mContext), parent, false));
        } else {
            return new ViewHolder(ItemTipsBinding.inflate(LayoutInflater.from(mContext), parent, false));
        }
    }*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ViewHolder(ItemTipsBinding.inflate(LayoutInflater.from(mactivity), parent, false));
        } else {
            if (PowerPreference.getDefaultFile().getInt(Constant.ListNativeWhichOne, 1) == 1)
                return new LargeAdViewHolder(NativeAdNormalSmallBinding.inflate(LayoutInflater.from(mactivity), parent, false));
            else
                return new SmallAdViewHolder(NativeAdBannerBinding.inflate(LayoutInflater.from(mactivity), parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (contactsList.get(position) != null)
            return 0;
        else return AD_TYPE_GOOGLE;
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == AD_TYPE_GOOGLE) {
            if (PowerPreference.getDefaultFile().getInt(Constant.ListNativeWhichOne, 1) == 1) {
                LargeAdViewHolder holder1 = (LargeAdViewHolder) holder;
                new ListNativeAds().showListNativeAds(mactivity, holder1.binding.nativeAd, holder1.binding.adSpace);
            } else {
                SmallAdViewHolder holder1 = (SmallAdViewHolder) holder;
                new ListNativeAds().showListNativeAds(mactivity, holder1.binding.nativeAdBanner, holder1.binding.adSpaceBanner);
            }
        } else {
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.binding.tipstitle.setText(contactsList.get(position).title);
            holder1.binding.tipsdescription.setText(contactsList.get(position).description);
        }

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}