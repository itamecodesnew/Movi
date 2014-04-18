package com.itamecodes.moviepot;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;


public class SplashScreen extends ActionBarActivity {
    private ShimmerTextView mTextView;
    private Shimmer mShimmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the action bar and statusbar on jellybean and above
        View decorView=getWindow().getDecorView();
        //hide the status bar first
        int uiOptions=View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
        setContentView(R.layout.splash_screen);
        mShimmer = new Shimmer();

        Typeface fontStyleSubtitle= Typeface.createFromAsset(getAssets(), "Qwigley/Qwigley-Regular.ttf");
        Typeface fontStyletitle= Typeface.createFromAsset(getAssets(), "Cinzel_Decorative/CinzelDecorative-Regular.ttf");
        mTextView=(ShimmerTextView)findViewById(R.id.splashtextview);
        mTextView.setTypeface(fontStyletitle);

//        AlphaAnimation alphaIn=new AlphaAnimation(0.0f,1.0f);
//        alphaIn.setDuration(3000);

        mShimmer.setRepeatCount(3)
                .setDuration(3000)
                .setStartDelay(300)
                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
        mShimmer.start(mTextView);
       // mTextView.startAnimation(alphaIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }
        },8000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
