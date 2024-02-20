package com.example.adapterview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterStudent extends BaseAdapter{
    private ArrayList<Student> data;
    private ArrayList<Student> backup;
    private Activity context;
    private LayoutInflater inflater;
    public AdapterStudent(){
        super();
    }
    public AdapterStudent(ArrayList<Student> data, Activity context) {
        super();
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    
    
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null)
            v=inflater.inflate(R.layout.student,null);
        ImageView img=v.findViewById(R.id.imageView);
        TextView txtName=v.findViewById(R.id.editTextName);
        TextView txtPhone=v.findViewById(R.id.editTextPhone);
        txtName.setText(data.get(position).getName());
        txtPhone.setText(data.get(position).getPhone());
        return v;
    }
}
