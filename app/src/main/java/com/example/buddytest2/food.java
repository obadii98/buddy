package com.example.buddytest2;

import android.widget.Button;

public class food {
    private String name;
    private int quan;
    private String unit;

    public food(String name, int quan, String unit){
        this.name=name;
        this.quan=quan;
        this.unit=unit;
    }
    public food(String unit){
        this.unit=unit;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getQuan(){
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
