package com.example.movieapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.movieapplication.databinding.MovieListItemBinding;
import com.example.movieapplication.model.MovieModel;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    List<MovieModel> mMovies;
    private Context mContext;
    private OnMovieListener onMovieListener;


    public MovieRecyclerViewAdapter(Context mContext, OnMovieListener onMovieListener) {
        this.mContext = mContext;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
                , onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.movieItemTitle.setText(mMovies.get(position).getTitle());
        holder.binding.movieItemReleaseDate.setText(mMovies.get(position).getRelease_date());
        holder.binding.movieItemRatingsText.setText(String.valueOf(mMovies.get(position).getVote_average()));
        holder.binding.movieItemRatings.setRating(mMovies.get(position).getVote_average() / 2);

        //ImageView
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
                .transform(new CenterCrop(), new RoundedCorners(28))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.movieItemPoster);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null)
            return mMovies.size();
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}
