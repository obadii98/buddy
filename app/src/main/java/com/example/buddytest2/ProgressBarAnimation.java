package com.example.buddytest2;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class ProgressBarAnimation extends Animation {
    private Context context;
    private float from;
    private float to;

    public ProgressBarAnimation(Context context, float from, float to){
        this.context=context;
        this.from=from;
        this.to=to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        if(value == to){
            context.startActivity(new Intent(context,HomeActivity.class));
        }
    }
}
