package com.example.buddytest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener  {

    Button homeBtn,calBtn,proBtn,nearBtn;
    private ConstraintLayout home_layout;
    float x;
    Dialog popupSignupChoose,popupSigninChoose , popupSignupFormP, popupSigninFormP,popupSigninFormC,popupSignupFormC;
    TextView title_popup;
    Button canPopUpChoose,canPopInChoose,signup;
    Button signin, playerbtnsignin, playerbtnsignup, coachbtnsignin, coachbtnsignup;
    EditText etPlayerUser, etPlayerPass, etCoachUser, etCoachPass;
    String playerUser, playerPass, coachUser, coachPass, type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeBtn = findViewById(R.id.homebtn);
        calBtn = findViewById(R.id.calbtn);
        proBtn = findViewById(R.id.probtn);
        nearBtn = findViewById(R.id.nearbtn);

        homeBtn.setOnClickListener(this);
        calBtn.setOnClickListener(this);
        proBtn.setOnClickListener(this);
        nearBtn.setOnClickListener(this);



        //pop-up sign-up choose
        popupSignupChoose = new Dialog(this);
        canPopUpChoose=popupSignupChoose.findViewById(R.id.dismis_popupchoose);
        popupSignupChoose.setContentView(R.layout.first_signup_popup);
        signup = (Button) findViewById(R.id.signupbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSignupChoose.findViewById(R.id.popup_title);
                title_popup.setText("Sign-up");
                popupSignupChoose.show();
            }
        });

        //pop-up sign-in choose
        popupSigninChoose = new Dialog(this);
        canPopInChoose=popupSigninChoose.findViewById(R.id.dismis_popupchoose);popupSigninChoose.setContentView(R.layout.first_signin_popup);
        signin = (Button) findViewById(R.id.signinbtn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSigninChoose.findViewById(R.id.popup_title);
                title_popup.setText("Sign-in");
                popupSigninChoose.show();
            }
        });

        //pop-up sign-in for COACH and his user and pass
        popupSigninFormC = new Dialog(this);
        popupSigninFormC.setContentView(R.layout.coach_signin_popup);
        etCoachUser = (EditText)  popupSigninFormC.findViewById(R.id.etcoachusername);
        etCoachPass = (EditText) popupSigninFormC.findViewById(R.id.etcoachpassword);
        //OPEN THE SIGN-IN FOR COACH
        coachbtnsignin = (Button) popupSigninChoose.findViewById(R.id.coachbtn);
        coachbtnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSigninFormC.findViewById(R.id.popup_title);
                title_popup.setText("Sign-in as Coach");
                popupSigninChoose.dismiss();
                popupSigninFormC.show();
            }
        });

        //pop-up sign-in for player and his user and pass
        popupSigninFormP = new Dialog(this);
        popupSigninFormP.setContentView(R.layout.player_signin_popup);
        etPlayerUser = (EditText)  popupSigninFormP.findViewById(R.id.etplayerusername);
        etPlayerPass = (EditText) popupSigninFormP.findViewById(R.id.etplayerpassword);
        //OPEN THE SIGN-IN FOR PLAYER
        playerbtnsignin = (Button) popupSigninChoose.findViewById(R.id.playerbtn);
        playerbtnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSigninFormP.findViewById(R.id.popup_title);
                title_popup.setText("Sign-in as Player");
                popupSigninChoose.dismiss();
                popupSigninFormP.show();
            }
        });

        //pop-up sign-up for COACH
        popupSignupFormC = new Dialog(this);
        popupSignupFormC.setContentView(R.layout.coach_signup_popup);
        //OPEN THE SIGN-UP FOR PLAYER
        coachbtnsignup = (Button) popupSignupChoose.findViewById(R.id.coachbtn);
        coachbtnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSignupFormC.findViewById(R.id.popup_title);
                title_popup.setText("Sign-up as Coach");
                popupSignupChoose.dismiss();
                popupSignupFormC.show();
            }
        });

        //pop-up sign-up for PLAYER
        popupSignupFormP = new Dialog(this);
        popupSignupFormP.setContentView(R.layout.player_signup_popup);
        //OPEN THE SIGN-UP FOR PLAYER
        playerbtnsignup = (Button) popupSignupChoose.findViewById(R.id.playerbtn);
        playerbtnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSignupFormP.findViewById(R.id.popup_title);
                title_popup.setText("Sign-up as Player");
                popupSignupChoose.dismiss();
                popupSignupFormP.show();
            }
        });





        home_layout = (ConstraintLayout) findViewById(R.id.homelayout);

        home_layout.setOnTouchListener(new View.OnTouchListener() {
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dismis_popupchoose:
                popupSigninChoose.dismiss();
                popupSignupChoose.dismiss();
                break;
            case R.id.coach_signup_back:
                popupSignupFormC.dismiss();
                popupSignupChoose.show();
                break;
            case R.id.coach_signin_back:
                popupSigninFormC.dismiss();
                popupSigninChoose.show();
                break;
            case R.id.player_signin_back:
                popupSigninFormP.dismiss();
                popupSigninChoose.show();
                break;
            case R.id.player_signup_back:
                popupSignupFormP.dismiss();
                popupSignupChoose.show();
                break;
        }
    }
    @Override
    public void finish() {
        this.finishAffinity();
    }

    public void signInAsplayer(View view){
        playerUser = etPlayerUser.getText().toString();
        playerPass = etPlayerPass.getText().toString();
        type = "SigninAsPlayer";
        BackgroundWorker bgw = new BackgroundWorker(this);
        bgw.execute(type,playerUser,playerPass);
        if(bgw.result.equals("Player")) {
            Intent coachPro = new Intent(this, ProfileActivity.class);
            startActivity(coachPro);
        }
    }

    public void signinAsCoach(View view){
        coachUser = etCoachUser.getText().toString();
        coachPass = etCoachPass.getText().toString();
        type = "SigninAsCoach";

        BackgroundWorker bgw = new BackgroundWorker(this);
        bgw.execute(type,coachUser,coachPass);
    }
}

