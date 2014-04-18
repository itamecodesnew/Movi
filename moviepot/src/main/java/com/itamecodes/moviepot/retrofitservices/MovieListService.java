package com.itamecodes.moviepot.retrofitservices;

import com.itamecodes.moviepot.data.model.MovieList;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Created by ananya on 3/8/14.
 */
public interface MovieListService {
    @GET("/{listofmovies}")
    Observable<MovieList> getListOfMovies(@Path("listofmovies") String listofmovies,@Query("api_key") String apiKey,@Query("page") int page);

}
