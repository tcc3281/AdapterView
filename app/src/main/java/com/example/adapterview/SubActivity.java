package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {
    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextPhone;
    private ImageView imageView;
    private Button btnOk;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        editTextID=findViewById(R.id.editViewId);
        editTextName=findViewById(R.id.editTextName);
        editTextPhone=findViewById(R.id.editTextPhone);
        btnCancel=findViewById(R.id.buttonCancel);
        btnOk=findViewById(R.id.buttonOK);
        imageView=findViewById(R.id.imageView2);

        Intent intent= getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            int id=bundle.getInt("Id");
            editTextID.setText(""+id);
            String name=bundle.getString("Name");
            editTextName.setText(name);
            String phone=bundle.getString("Phone");
            editTextPhone.setText(phone);
        }


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editTextID.getText().toString());
                String name= editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",id);
                bundle.putString("Name",name);
                bundle.putString("Phone",phone);
                intent.putExtras(bundle);
                setResult(150,intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(151);
                finish();
            }
        });
    }
}