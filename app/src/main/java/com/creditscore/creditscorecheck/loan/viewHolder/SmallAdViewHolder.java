package com.creditscore.creditscorecheck.loan.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creditscore.creditscorecheck.loan.databinding.NativeAdBannerBinding;


public class SmallAdViewHolder extends RecyclerView.ViewHolder {
    public NativeAdBannerBinding binding;

    public SmallAdViewHolder(@NonNull NativeAdBannerBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }
}