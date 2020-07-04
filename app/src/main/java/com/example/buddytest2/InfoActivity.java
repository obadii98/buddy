package com.example.buddytest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    public void finish() {
        Intent cal = new Intent(this,CalActivity.class);
        startActivity(cal);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
        super.finish();
    }
}
