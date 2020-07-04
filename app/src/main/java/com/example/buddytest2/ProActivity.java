package com.example.buddytest2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class ProActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button homeBtn, calBtn, proBtn, nearBtn;
    private GestureDetectorCompat mgestureDetector;
    private RelativeLayout pro_layout;
    float x;
    /////popup
    ImageButton img_fit, img_slim;
    ImageView popup_img;
    Dialog popup;
    TextView title_popup;
    Spinner kg_spinner, days_spinner;
    ArrayAdapter<String> adapter;
    Button cancel_popup,confirm_popup;
    String Spinner_Fiting_items[] = {
            "maintain my current weight",
            "Gian 0.75 Kg per week",
            "Gian 0.50 Kg per week",
            "Gian 0.25 Kg per week"};
    String Spinner_Slimming_items[] = {
            "maintain my current weight",
            "Lose 0.75 Kg per week",
            "Lose 0.50 Kg per week",
            "Lose 0.25 Kg per week"};
    String Days_per_week[] = {"1", "2", "3", "4", "5", "6", "7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);
        homeBtn = findViewById(R.id.homebtn);
        calBtn = findViewById(R.id.calbtn);
        proBtn = findViewById(R.id.probtn);
        nearBtn = findViewById(R.id.nearbtn);


        homeBtn.setOnClickListener(this);
        calBtn.setOnClickListener(this);
        proBtn.setOnClickListener(this);
        nearBtn.setOnClickListener(this);
        ///img_fit+slim
        img_fit = findViewById(R.id.img_fit);
        img_slim = findViewById(R.id.img_slim);
        popup_img = findViewById(R.id.popup_image);
        ////// dialog
        popup = new Dialog(this);
        popup.setContentView(R.layout.popup_layout);
        days_spinner = popup.findViewById(R.id.spinner_day);
        adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner, Days_per_week);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        days_spinner.setAdapter(adapter);
        cancel_popup=popup.findViewById(R.id.dismis_popup);
        confirm_popup=popup.findViewById(R.id.confirm_popup);


        mgestureDetector = new GestureDetectorCompat(this, new ProActivity.GestureListener());
        pro_layout = (RelativeLayout) findViewById(R.id.prolayout);

        pro_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x = motionEvent.getX();
                if ((view.getWidth() / 2) < x) {
                    onSwipeRight();
                } else
                    onSwipeLeft();
                return true;
            }
        });


        ///on click image


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float difX = e2.getX() - e1.getX();
            if (difX < 0) {
                onSwipeRight();
            } else
                onSwipeLeft();

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private void onSwipeLeft() {
        Intent cal = new Intent(this, CalActivity.class);
        startActivity(cal);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void onSwipeRight() {
        Intent near = new Intent(this, NearActivity.class);
        startActivity(near);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    @Override
    public void finish() {
        super.finish();
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mgestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homebtn: {
                //changing intent
                Intent home = new Intent(this, HomeActivity.class);
                startActivity(home);

                //color of navbar
                homeBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                nearBtn.setBackgroundColor(Color.LTGRAY);
                proBtn.setBackgroundColor(Color.LTGRAY);
                calBtn.setBackgroundColor(Color.LTGRAY);
                break;
            }
            case R.id.calbtn: {
                //changing intent
                Intent cal = new Intent(this, CalActivity.class);
                startActivity(cal);

                //color of navbar
                homeBtn.setBackgroundColor(Color.LTGRAY);
                calBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                proBtn.setBackgroundColor(Color.LTGRAY);
                nearBtn.setBackgroundColor(Color.LTGRAY);
                break;
            }
            case R.id.probtn: {
                //changing intent
                Intent pro = new Intent(this, ProActivity.class);
                startActivity(pro);

                //color of navbar
                homeBtn.setBackgroundColor(Color.LTGRAY);
                nearBtn.setBackgroundColor(Color.LTGRAY);
                proBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                calBtn.setBackgroundColor(Color.LTGRAY);
                break;
            }
            case R.id.nearbtn: {
                //changing intent
                Intent near = new Intent(this, NearActivity.class);
                startActivity(near);

                //color of navbar
                homeBtn.setBackgroundColor(Color.LTGRAY);
                calBtn.setBackgroundColor(Color.LTGRAY);
                proBtn.setBackgroundColor(Color.LTGRAY);
                nearBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                break;
            }
            case R.id.img_fit: {

                kg_spinner = popup.findViewById(R.id.spinner_kg);

                title_popup = popup.findViewById(R.id.popup_title);
                title_popup.setText("Gain weight");
                popup_img = popup.findViewById(R.id.popup_image);
                popup_img.setImageResource(R.drawable.fiting);
                adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner, Spinner_Fiting_items);
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
                kg_spinner.setAdapter(adapter);


                popup.show();


                break;
            }
            case R.id.img_slim: {
                kg_spinner = popup.findViewById(R.id.spinner_kg);
                title_popup = popup.findViewById(R.id.popup_title);
                title_popup.setText("Lose weight");
                popup_img = popup.findViewById(R.id.popup_image);
                popup_img.setImageResource(R.drawable.slimming);
                adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner, Spinner_Slimming_items);
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
                kg_spinner.setAdapter(adapter);


                popup.show();

                break;
            }
            case R.id.dismis_popup: {


                popup.dismiss();


                break;
            }

            case R.id.confirm_popup: {

                //code of the progrmas activity
                break;
            }


        }
    }


}
