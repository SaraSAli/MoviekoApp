package com.example.movieapplication.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.databinding.ViewPagerItemLayoutBinding;

public class NowPlayingMoviesViewHolder extends RecyclerView.ViewHolder {

    ViewPagerItemLayoutBinding binding;

    public NowPlayingMoviesViewHolder(ViewPagerItemLayoutBinding b) {
        super(b.getRoot());
        binding = b;
    }
}
