package com.itamecodes.moviepot.data.model;

/**
 * Created by ananya on 3/8/14.
 */
public class Movie {
    public boolean adult;
    public String backdrop_path;
    public String homepage;
    public String id;

    public String original_title;
    public String overview;

    public String release_date;
    public String status;
    public String tagline;
    public String poster_path;
    public String popularity;
    public String title;
    public float vote_average;
    public int vote_count;

    public Movie(boolean adult, String backdrop_path, String id,
                 String original_title, String release_date, String poster_path
            , String popularity, String title, float vote_average, int vote_count, String homepage, String overview, String status, String tagline){
        this.adult=adult;
        this.backdrop_path=backdrop_path;
        this.id=id;
        this.original_title=original_title;
        this.release_date=release_date;
        this.poster_path=poster_path;
        this.popularity=popularity;
        this.title=title;
        this.vote_average=vote_average;
        this.vote_count=vote_count;
        this.homepage=homepage;
        this.overview=overview;
        this.status=status;
        this.tagline=tagline;
    }
}
