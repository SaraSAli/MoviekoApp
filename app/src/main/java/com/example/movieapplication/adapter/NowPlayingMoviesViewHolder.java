package com.example.movieapplication.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.databinding.ViewPagerItemLayoutBinding;

public class NowPlayingMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ViewPagerItemLayoutBinding binding;
    OnMovieListener onMovieListener;


    public NowPlayingMoviesViewHolder(ViewPagerItemLayoutBinding b, OnMovieListener onMovieListener) {
        super(b.getRoot());
        binding = b;
        this.onMovieListener = onMovieListener;

        b.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
