package com.example.adapterview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected AdapterStudent adapter;
    protected ArrayList<Student> arrayList;
    protected EditText txtName;
    protected FloatingActionButton button;
    protected ListView lstStudent;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("Id");
        String name = b.getString("Name");
        String phone = b.getString("Phone");
        Student st = new Student(id,"inae",name,phone);
        if(requestCode==150){
            arrayList.add(st);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=findViewById(R.id.editTextText);
        button=findViewById(R.id.floatingActionButton2);
        lstStudent=findViewById(R.id.listview);

        arrayList=new ArrayList();
        arrayList.add(new Student(1,"img1","Chiến Trấn","048934934"));
        arrayList.add(new Student(2,"img2","Chiến Tran","545434334"));
        arrayList.add(new Student(3,"img3","Chiến Trần","987686455"));

        adapter = new AdapterStudent(arrayList,this);

        lstStudent.setAdapter(adapter);

        button.setOnClickListener(v->{
            arrayList.add(new Student(1,"img1",txtName.getText().toString(),"845398543"));
            adapter.notifyDataSetChanged();
            txtName.setText("");

            //tao intent de mo subactivity
            Intent intent = new Intent(MainActivity.this,SubActivity.class);
            //truyen du lieu sang subac bang bundle neu can
            //startactivity hoac startactivityforresult
            startActivityForResult(intent,150);
        });

        lstStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "title", Toast.LENGTH_SHORT).show();
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}