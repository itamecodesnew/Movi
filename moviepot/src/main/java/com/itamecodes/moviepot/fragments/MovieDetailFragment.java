package com.itamecodes.moviepot.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.itamecodes.moviepot.R;
import com.itamecodes.moviepot.config.Config;
import com.itamecodes.moviepot.data.model.Movie;
import com.itamecodes.moviepot.moviapi.MoviApi;
import com.squareup.picasso.Picasso;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by ananya on 3/22/14.
 */
public class MovieDetailFragment extends Fragment implements Observer<Movie> {
    ImageView movieBackdrop;
    TextView movieName,movieDesc;
    private AQuery mAquery;
    private TextView mMovieDescription,mMoviedesc2;
    private LinearLayout mDetailLayout;
    private View mBlankView;
    private RelativeLayout mMovieDescContainer;
    private ProgressBar mProgressBar;
    public MovieDetailFragment() {
    }

    public static MovieDetailFragment newInstance(){
        return new MovieDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAquery=new AQuery(getActivity());
        Timber.tag("MovieFragment");
        Timber.v("oncreate called");
        MoviApi.getMovieDetailService().getMovieDetails("68722", Config.MDB.getKey(),"trailers").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View movieDetailView=inflater.inflate(R.layout.moviedetaillayoutnew,container,false);

        return movieDetailView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieBackdrop=(ImageView)view.findViewById(R.id.moviebackdrop);
        mProgressBar=(ProgressBar)view.findViewById(R.id.moviedetailprogress);
        mMovieDescContainer=(RelativeLayout)view.findViewById(R.id.moviedesccontainer);
        ViewTreeObserver viewTreeObserver=movieBackdrop.getViewTreeObserver();
//        if(viewTreeObserver.isAlive()){
//            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    movieBackdrop.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    mBlankView.getLayoutParams().height=(int)(movieBackdrop.getHeight()*(75.0/100.0));
//                    Timber.tag("Imageviewheight");
//
//                    Timber.v(movieBackdrop.getHeight()+"--->"+movieBackdrop.getHeight()*(float)(75.0/100.0));
//                }
//            });
//        }

      //  mDetailLayout=(LinearLayout)view.findViewById(R.id.thedetaillayout);
        mMovieDescription=(TextView)view.findViewById(R.id.moviedesc);
        Typeface fontStyleSubtitle= Typeface.createFromAsset(getActivity().getAssets(), "Redressed/Redressed.ttf");
        mMovieDescription.setTypeface(fontStyleSubtitle);
       // mBlankView=(View)view.findViewById(R.id.theblankview);
        if(movieBackdrop==null){
            Timber.d("movieBackdrop is null");
        }
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
    public void onNext(Movie movie) {
        Timber.tag("MovieFragment");
        Timber.v("onNext called"+movie.backdrop_path);
        getActivity().getActionBar().setTitle(movie.title);
        String url="http://image.tmdb.org/t/p/original"+movie.backdrop_path;
        //create a bitmap ajax callback object
       //BitmapAjaxCallback cb = new BitmapAjaxCallback();

//configure the callback
      //  cb.url(url).animation(AQuery.FADE_IN);

//invoke it with an image view
       // mAquery.id(movieBackdrop).image(cb);
        Picasso pic=Picasso.with(getActivity());
        pic.setDebugging(true);
        pic.load(url).fit().into(movieBackdrop);
        mMovieDescContainer.setBackgroundColor(getResources().getColor(R.color.lightpinkbackgrounddark));
        mProgressBar.setVisibility(View.GONE);
        Animation bottomUp = AnimationUtils.loadAnimation(getActivity(),
                R.anim.bottom_up);

       // mDetailLayout.startAnimation(bottomUp);
       // mDetailLayout.setVisibility(View.VISIBLE);
        mMovieDescription.setText(movie.overview);
        mMoviedesc2.setText(movie.overview);

    }
}
