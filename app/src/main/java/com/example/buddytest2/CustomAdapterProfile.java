package com.example.buddytest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapterProfile extends BaseAdapter {
    Context context;
    float weight;
    float calories;

    public CustomAdapterProfile(Context cont, float w, float c){
        this.context=cont;
        this.weight=w;
        this.calories=c;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.profile_listview, viewGroup,false);
        }

        try {
            TextView tvprofweight = (TextView) view.findViewById(R.id.tvprofweight);
            TextView tvprofcal = (TextView) view.findViewById(R.id.tvprofcal);

            tvprofweight.setText("65");
            tvprofcal.setText("4000");
        }catch (Exception e){}

        return view;
    }
}
