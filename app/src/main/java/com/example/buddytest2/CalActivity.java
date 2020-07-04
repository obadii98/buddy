package com.example.buddytest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CalActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout cal_layout;
    boolean isChanged = false;
    float x;
    TextView quan_tv;
    EditText etquan;
    String fname, funit;
    int fquan=0, caloriesPG, proteinPG,caloriesPU=0,proteinPU=0;
    public static int sumcal=0,sumpro=0;
    public static ArrayList<food> foo = new ArrayList<food>();
    CustomAdapterResult radapter;
    public static CustomAdapter fadapter;
    Button add, clr, show;
    FloatingActionButton infoBtn;
    public static ListView rlv,flv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        etquan = (EditText) findViewById(R.id.f_quan);
        etquan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {

                }catch (Exception e){

                }

            }
        });
        etquan.setCursorVisible(false);
    
        add = (Button) findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText f_quan = (EditText) findViewById(R.id.f_quan);
                try {
                    int fquan = Integer.parseInt(f_quan.getText().toString());    
                }catch (Exception e){
                    Toast.makeText(CalActivity.this, "PLease enter a number and a food", Toast.LENGTH_SHORT).show();
                }
                if(fquan!=0) {
                    foo.add(new food(fname, fquan, funit));
                }
                fadapter.notifyDataSetChanged();

                try {
                    fquan = Integer.parseInt(etquan.getText().toString());
                }catch (Exception e){
                    Toast.makeText(CalActivity.this, "Please enter a number and a food", Toast.LENGTH_SHORT).show();
                }
                caloriesPU=caloriesPG*fquan;
                proteinPU=proteinPG*fquan;

                sumcal=sumcal+caloriesPU;
                sumpro=sumpro+proteinPU;
                radapter.notifyDataSetChanged();
                if (fquan!=0 && fname =="") {
                    Toast.makeText(CalActivity.this, "Added " + fquan + " " + funit + " of " + fname, Toast.LENGTH_SHORT).show();
                }
            }
        });

        show = (Button) findViewById(R.id.showbtn);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food = new Intent(CalActivity.this,FoodActivity.class);
                startActivity(food);
            }
        });

        clr = (Button) findViewById(R.id.clrbtn);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foo.removeAll(foo);
                sumcal=0;
                sumpro=0;
                fadapter.notifyDataSetChanged();
                radapter.notifyDataSetChanged();
            }
        });

        infoBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(CalActivity.this,InfoActivity.class);
                startActivity(info);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
            }
        });

        rlv = (ListView) findViewById(R.id.reslv);
        radapter = new CustomAdapterResult(this,sumpro,sumcal);
        rlv.setAdapter(radapter);

        try {
            flv = (ListView) findViewById(R.id.foodlv);
            fadapter = new CustomAdapter(this,foo);
            flv.setAdapter(fadapter);

        }catch (Exception e){
        }

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.food_spinner, getResources().getStringArray(R.array.food));
        adapter.setDropDownViewResource(R.layout.food_spinner_dropdown);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quan_tv = (TextView) findViewById(R.id.quantv);
                switch (i){
                    case 0:
                        fname="";
                        quan_tv.setText("");
                        break;
                    case 1:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Indomie";
                        quan_tv.setText("Piece");
                        funit = quan_tv.getText().toString();
                        break;
                    case 2:
                        caloriesPG = 200;
                        proteinPG = 5;
                        fname = "Oat";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 3:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Tuna";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 4:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Egg";
                        quan_tv.setText("Piece");
                        funit = quan_tv.getText().toString();
                        break;
                    case 5:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Banana";
                        quan_tv.setText("Piece");
                        funit = quan_tv.getText().toString();
                        break;
                    case 6:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Rice";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 7:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Peanut";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 8:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Chocolate";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 9:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Peanut Butter";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 10:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Milk";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 11:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Protein";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                };
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cal_layout = (ConstraintLayout) findViewById(R.id.calacticity);
        cal_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x = motionEvent.getX();
                if(view.getWidth()/2<x){
                    onSwipeRight();
                }
                else
                    onSwipeLeft();
                return true;
            }
        });

    }

    private void onSwipeLeft() {
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    private void onSwipeRight() {
        Intent pro = new Intent(this,ProActivity.class);
        startActivity(pro);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View view){
        Intent info = new Intent(CalActivity.this, InfoActivity.class);
        startActivity(info);
        overridePendingTransition(R.anim.slide_out_top, R.anim.slide_in_bottom);
    }

    @Override
    public void finish() {
        super.finish();
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
