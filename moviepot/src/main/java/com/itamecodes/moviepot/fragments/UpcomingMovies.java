package com.itamecodes.moviepot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.etsy.android.grid.StaggeredGridView;
import com.itamecodes.moviepot.R;
import com.itamecodes.moviepot.adapters.ImageTextAdapter;
import com.itamecodes.moviepot.data.model.Movie;
import com.itamecodes.moviepot.data.model.MovieList;
import com.itamecodes.moviepot.moviapi.MoviApi;
import com.itamecodes.moviepot.utils.EndlessScrollListener;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by ananya on 3/22/14.
 */
public class UpcomingMovies extends Fragment implements Observer<MovieList> {
    ImageView movieBackdrop;
    TextView movieName,movieDesc;
    private AQuery mAquery;
    private TextView mMovieDescription,mMoviedesc2;
    private LinearLayout mDetailLayout;
    private View mBlankView;
    private RelativeLayout mMovieDescContainer;
    private ProgressBar mProgressBar;
    ImageTextAdapter upAd;
    private int total_pages;
    private int current_page=0;

    public UpcomingMovies() {
    }



    public static UpcomingMovies newInstance(){
        return new UpcomingMovies();
    }

    public void loadData(){
        MoviApi.getMovieListService().getListOfMovies("now_playing", "b1a2d97608f210126674df510c71ab52", current_page+1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAquery=new AQuery(getActivity());
        loadData();
       // upAd=new ImageTextAdapter(getActivity(), R.layout.thegridelement,getResources().getDimensionPixelSize(R.dimen.iconwidth));
        upAd=new ImageTextAdapter(getActivity(), R.layout.dynamicgridelement,getResources().getDimensionPixelSize(R.dimen.iconwidth));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View movieDetailView=inflater.inflate(R.layout.thegrid, container, false);
        //final GridView gv = (GridView)movieDetailView.findViewById(R.id.thegridview);
        final StaggeredGridView gv = (StaggeredGridView)movieDetailView.findViewById(R.id.grid_view);
        gv.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if(current_page<total_pages) {
                    loadData();
                }
            }
        });
        gv.setAdapter(upAd);
        return movieDetailView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCompleted() {
        Timber.tag("MovieFragment");
        Timber.v("oncomplete called");
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(MovieList movieList) {
        List<Movie> movie=movieList.results;
        total_pages=movieList.total_pages;
        current_page=movieList.page;
        upAd.addAll(movie);
        upAd.notifyDataSetChanged();
    }
}
