package com.example.quizzsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText etName , etId , etGpa ;
Button btnInsert , btnUpdate , btnDelete , btnView ;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etGpa = findViewById(R.id.etGpa);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);
        DB = new DBHelper(this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = etName.getText().toString().trim();
                String idTXT = etId.getText().toString().trim();
                String gpaTXT = etGpa.getText().toString().trim();

                Boolean checkinsertdata = DB.insertstudentdata(nameTXT, idTXT, gpaTXT);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = etName.getText().toString().trim();
                String idTXT = etId.getText().toString().trim();
                String gpaTXT = etGpa.getText().toString().trim();

                Boolean checkupdatedata = DB.updatestudentdata(nameTXT, idTXT, gpaTXT);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = etName.getText().toString().trim();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Id :"+res.getString(1)+"\n");
                    buffer.append("Gpa :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}