package com.itamecodes.moviepot.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.itamecodes.moviepot.R;
import com.itamecodes.moviepot.data.model.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Random;

/**
 * the imagetext adapter
 */
public class ImageTextAdapter extends ArrayAdapter<Movie>{
    LayoutInflater inflater;
    private int resId;
    Context c;
    int reqWidth,reqHeight;
    private Transformation transformation;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private final Random mRandom;
    Typeface fontStyle;


    public ImageTextAdapter(Context context, int gridElementResourceId,int width) {
        super(context, gridElementResourceId);
        c=context;
        inflater=LayoutInflater.from(context);
        this.resId=gridElementResourceId;
        mRandom=new Random();
        fontStyle= Typeface.createFromAsset(context.getAssets(), "Cinzel/Cinzel-Regular.ttf");


    }

    private void getTransformation(final ImageView iv){
        transformation = new Transformation() {

            @Override public Bitmap transform(Bitmap source) {
                int targetWidth = iv.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override public String key() {
                return "transformation" + " desiredWidth";
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container)  {
         View v=convertView;
        if(v==null){
            v=inflater.inflate(resId,null);
          /*  v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:{
                           / Log.v("awesomen","2");
                            ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotationY",0,10);
                            animator.setDuration(300);
                            animator.start();
                            return true;
                        }
                        case MotionEvent.ACTION_UP:{
                            Log.v("awesomen","1");
                            ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotationY",10,0);
                            animator.setDuration(300);
                            animator.start();
                            return true;
                        }
                        case MotionEvent.ACTION_SCROLL:{
                            Log.v("awesomen","3");
                            ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotationY",10,0);
                            animator.setDuration(300);
                            animator.start();
                            return true;
                        }
                        default:
                            return true;
                    }

                   // return true;
                }
            });*/
        }
        TextView tv=(TextView) v.findViewById(R.id.thetext);
        tv.setTypeface(fontStyle);
        DynamicHeightImageView iv=(DynamicHeightImageView)v.findViewById(R.id.thedynamicimageview);
        double positionHeight = getPositionRatio(position);
        iv.setHeightRatio(positionHeight);
        getTransformation(iv);
        Movie mov=getItem(position);
        tv.setText(mov.title);
        String url=mov.poster_path;
        String theurl="http://cf2.imgobject.com/t/p/w154"+url;

        Picasso pic=Picasso.with(c);
        pic.setDebugging(true);
        pic.load(theurl).transform(transformation).into(iv);


        return v;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
           // Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }
    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }

}