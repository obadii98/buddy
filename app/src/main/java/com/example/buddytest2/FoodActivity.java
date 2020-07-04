package com.example.buddytest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.buddytest2.CalActivity.fadapter;
import static com.example.buddytest2.CalActivity.flv;
import static com.example.buddytest2.CalActivity.foo;

public class FoodActivity extends AppCompatActivity {

    Button gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        flv = (ListView) findViewById(R.id.foodlv);
        fadapter = new CustomAdapter(this,foo);
        flv.setAdapter(fadapter);
        fadapter.notifyDataSetChanged();


        gen = (Button) findViewById(R.id.backbtn);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(FoodActivity.this,CalActivity.class);
                startActivity(cal);
            }
        });
    }
}
