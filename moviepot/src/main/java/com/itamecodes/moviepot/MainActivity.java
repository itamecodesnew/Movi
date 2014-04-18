package com.itamecodes.moviepot;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.itamecodes.moviepot.customviews.ResideMenu;
import com.itamecodes.moviepot.customviews.ResideMenuItem;
import com.itamecodes.moviepot.fragments.MovieDetailFragment;
import com.itamecodes.moviepot.fragments.UpcomingMovies;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private ResideMenu mResideMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        mResideMenu = new ResideMenu(this);
        mResideMenu.setBackground(getResources().getColor(R.color.lightpinkbackgrounddark));
        mResideMenu.attachToActivity(this);
        mResideMenu.setMenuListener(menuListener);

        // create menu items;
        String titles[] = { "Upcoming", "Latest", "Trending", "Great" };
       // int icon[] = { R.drawable.icon_home, R.drawable.icon_profile, R.drawable.icon_calendar, R.drawable.icon_settings };

        for (int i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, titles[i]);
            item.setOnClickListener(this);
            mResideMenu.addMenuItem(item);
        }

        UpcomingMovies revfrag=UpcomingMovies.newInstance();
        ft.replace(R.id.moviedetail, revfrag);
        ft.commit();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mResideMenu.onInterceptTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(MainActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(MainActivity.this, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        
        // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("residetest","12");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:{
                Log.v("residetest","1");
                mResideMenu.openMenu();
                break;
                //return true;
            }
            case R.id.action_settings:{
                mResideMenu.openMenu();
                break;
                //return true;
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        mResideMenu.closeMenu();
    }
    public ResideMenu getResideMenu(){
        return mResideMenu;
    }
}
