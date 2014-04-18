package com.itamecodes.moviepot.moviapi;

import com.itamecodes.moviepot.retrofitservices.MovieDetailService;
import com.itamecodes.moviepot.retrofitservices.MovieListService;

import retrofit.RestAdapter;

/**
 * Created by vivek on 3/8/14.
 */
public class MoviApi {
    private static final String MOVI_API_URL="https://api.themoviedb.org/3/movie";
    private static RestAdapter restAdapterMovie;
    private static MovieDetailService mMovieDetailService;
    private static MovieListService mMovieListService;

    public static RestAdapter getMovieRestAdapter(){
        if(restAdapterMovie==null){
            restAdapterMovie=new RestAdapter.Builder().setEndpoint(MOVI_API_URL).build();
        }
        return restAdapterMovie;
    }

    public static MovieDetailService getMovieDetailService(){
        if(mMovieDetailService==null){
            mMovieDetailService=getMovieRestAdapter().create(MovieDetailService.class);
        }
        return mMovieDetailService;
    }

    public static MovieListService getMovieListService(){
        if(mMovieListService==null){
            mMovieListService=getMovieRestAdapter().create(MovieListService.class);
        }
        return mMovieListService;
    }







}
