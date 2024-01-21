package com.example.movieapplication.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.databinding.MovieListItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder{

    MovieListItemBinding binding;
    public MovieViewHolder(MovieListItemBinding b) {
        super(b.getRoot());
        binding = b;
    }
}
