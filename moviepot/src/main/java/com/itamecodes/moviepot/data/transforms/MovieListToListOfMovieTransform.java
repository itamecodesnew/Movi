package com.itamecodes.moviepot.data.transforms;

import com.itamecodes.moviepot.data.model.Movie;
import com.itamecodes.moviepot.data.model.MovieList;

import java.util.List;

import rx.util.functions.Func1;

/**
 * Created by ananya on 3/8/14.
 */
public class MovieListToListOfMovieTransform implements Func1<MovieList,List<Movie>> {
    @Override
    public List<Movie> call(MovieList movieList) {
        return movieList.results;
    }
}
