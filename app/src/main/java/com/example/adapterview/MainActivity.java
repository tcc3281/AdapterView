package com.example.adapterview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    protected int SelectedId;
    private MyDB db;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==150){
            Bundle b = data.getExtras();
            int id = b.getInt("Id");
            String name = b.getString("Name");
            String phone = b.getString("Phone");
            Student st = new Student(id,"inae",name,phone);
            arrayList.add(st);
            adapter.notifyDataSetChanged();
        }else if(requestCode==200 && resultCode==150){
            Bundle b= data.getExtras();
            Student student=arrayList.get(SelectedId);
            student.setId(b.getInt("Id"));
            student.setName(b.getString("Name"));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDB(this,"StudentDB",null,1);

//        db.addStudent(new Student(1,"img1","Chiến Trấn","048934934"));
//        db.addStudent(new Student(2,"img2","Chiến Tran","545434334"));
//        db.addStudent(new Student(3,"img3","Chiến Trần","987686455"));

        txtName=findViewById(R.id.editTextText);
        button=findViewById(R.id.floatingActionButton2);
        lstStudent=findViewById(R.id.listview);

        arrayList=db.getAllStudents();
        txtName.setText(arrayList.size()+"");
        adapter = new AdapterStudent(arrayList,this);
        registerForContextMenu(lstStudent);
        lstStudent.setAdapter(adapter);

        button.setOnClickListener(v->{
//            arrayList.add(new Student(1,"img1",txtName.getText().toString(),"845398543"));
//            adapter.notifyDataSetChanged();
            txtName.setText("");

            //tao intent de mo subactivity
            Intent intent = new Intent(MainActivity.this,SubActivity.class);
            //truyen du lieu sang subac bang bundle neu can
            //startactivity hoac startactivityforresult
            startActivityForResult(intent,100);
        });

        lstStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "title", Toast.LENGTH_SHORT).show();
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        lstStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedId=position;
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.actionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.sortbyname){
            Toast.makeText(this,"Sort by name",Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.sortbyphone) {
            Toast.makeText(this,"Sort by phone",Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.boardcast){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuedit){
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            Student student=arrayList.get(SelectedId);
            Intent intent= new Intent(MainActivity.this,SubActivity.class);
            Bundle b=new Bundle();
            b.putInt("Id",student.getId());
            b.putString("Name",student.getName());
            b.putString("Phone",student.getPhone());
            intent.putExtras(b);
            startActivityForResult(intent,200);
        }
        else if(item.getItemId()==R.id.menudelete){
            arrayList.remove(SelectedId);
            adapter.notifyDataSetChanged();
        }
        else if(item.getItemId()==R.id.menucall){
            Student student= arrayList.get(SelectedId);
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+student.getPhone()));
            startActivity(intent);
        }else if(item.getItemId()==R.id.menusms){
            Student student= arrayList.get(SelectedId);
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms :"+student.getPhone()));
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }
}