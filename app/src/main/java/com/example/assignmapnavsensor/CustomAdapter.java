package com.example.assignmapnavsensor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;




public class CustomAdapter extends ArrayAdapter<Store> {
    private Activity context;
    private List<Store> storeList;

    public CustomAdapter(Activity context,List<Store> storeList) {
        super(context, R.layout.sample_layout, storeList);
        this.context = context;
        this.storeList = storeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);

        Store Store=storeList.get(position);

        TextView t1=view.findViewById(R.id.x_tv);
        TextView t2=view.findViewById(R.id.y_tv);
        TextView t3=view.findViewById(R.id.z_tv);
        t1.setText(Store.getX());
        t2.setText(Store.getY());
        t3.setText(Store.getZ());



        return view;
    }
}
