package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
        btnCancel=findViewById(R.id.buttonOK);
        btnOk=findViewById(R.id.buttonCancel);
        imageView=findViewById(R.id.imageView2);


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
    }
}