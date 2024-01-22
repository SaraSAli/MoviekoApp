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
import com.example.movieapplication.databinding.ViewPagerItemLayoutBinding;
import com.example.movieapplication.model.MovieModel;

import java.util.List;

public class NowPlayingMoviesAdapter extends RecyclerView.Adapter<NowPlayingMoviesViewHolder> {

    List<MovieModel> mMovieList;
    private Context mContext;


    public NowPlayingMoviesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NowPlayingMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NowPlayingMoviesViewHolder(
                ViewPagerItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingMoviesViewHolder holder, int position) {
        //ImageView
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500/" + mMovieList.get(position).getPoster_path())
                .transform(new CenterCrop(), new RoundedCorners(28))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.viewPagerMoviePoster);

        holder.binding.viewPagerMovieTitle.setText(mMovieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (mMovieList != null)
            return mMovieList.size();
        return 0;
    }

    //Getting the id of the movie Clicked
    public MovieModel getSelectedMovie(int position) {
        if (mMovieList.size() > 0) {
            return mMovieList.get(position);
        }
        return null;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNowPlayingMovies(List<MovieModel> mMovies) {
        this.mMovieList = mMovies;
        notifyDataSetChanged();
    }
}
