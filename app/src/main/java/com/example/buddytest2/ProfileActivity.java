package com.example.buddytest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ProfileActivity extends AppCompatActivity {

    ConstraintLayout profile_layout;
    float x;
    ListView plv;
    CustomAdapterProfile padapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String type = "getProfile";
        BackgroundWorker bg = new BackgroundWorker(this);
        bg.execute(type);

        plv = (ListView) findViewById(R.id.profilelv);
        padapter = new CustomAdapterProfile(this,0,0);
        plv.setAdapter(padapter);

        profile_layout = (ConstraintLayout) findViewById(R.id.profile_layout);
        profile_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x = motionEvent.getX();
                if((view.getWidth()/2)<x){
                    onSwipeRight();
                }
                return true;
            }
        });
    }


    private void onSwipeRight() {
        Intent cal = new Intent(this,CalActivity.class);
        startActivity(cal);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
