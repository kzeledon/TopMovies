package com.example.karizp.topmovies;

import android.graphics.Bitmap;

/**
 * Created by karizp on 23/03/2018.
 */

public class Movie {
    String title;
    String stars;
    String metaScore;
    Bitmap moviePoster;
    int imageResourse;

    public Movie(String title, String stars, String metaScore, Bitmap moviePoster) {
        this.title = title;
        this.stars = stars;
        this.metaScore = metaScore;
        this.moviePoster = moviePoster;
    }

    public Movie(String title, String stars, String metaScore, int imageResourse) {
        this.title = title;
        this.stars = stars;
        this.metaScore = metaScore;
        this.imageResourse = imageResourse;
        this.moviePoster = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(String metaScore) {
        this.metaScore = metaScore;
    }

    public Bitmap getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(Bitmap moviePoster) {
        this.moviePoster = moviePoster;
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public void setImageResourse(int imageResourse) {
        this.imageResourse = imageResourse;
    }
}
