package com.example.movieapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MovieModel implements Parcelable {

    /**
     * Model Class
     **/

    private int movie_id;
    private String title;
    private String poster_path;
    private String release_date;
    private String movie_overview;
    private float vote_average;

    public MovieModel(int movie_id, String title, String poster_path, String release_date, String movie_overview, float vote_average) {
        this.movie_id = movie_id;
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_overview = movie_overview;
        this.vote_average = vote_average;
    }

    protected MovieModel(Parcel in) {
        movie_id = in.readInt();
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_overview = in.readString();
        vote_average = in.readFloat();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getMovie_id() {
        return movie_id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public float getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(movie_id);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(movie_overview);
        dest.writeFloat(vote_average);
    }
}
