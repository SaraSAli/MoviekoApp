package com.example.movieapplication.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.databinding.MovieListItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    MovieListItemBinding binding;
    OnMovieListener onMovieListener;
    public MovieViewHolder(MovieListItemBinding b, OnMovieListener onMovieListener) {
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
