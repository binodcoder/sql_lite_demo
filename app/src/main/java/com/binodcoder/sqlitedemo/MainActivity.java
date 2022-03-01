package com.binodcoder.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    EditText name, age;
    Button insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.et_name);
        age=(EditText)findViewById(R.id.et_age);
        insert=(Button)findViewById(R.id.btn_insert);



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();
                int userAge=Integer.parseInt(age.getText().toString());
                insertUserInfo(userName, userAge);
            }
        });
      }

    public void insertUserInfo(String username, int userage){

        try{
            SQLiteDatabase myDatabase=this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Kirsten', 21)");
            myDatabase.execSQL("INSERT INTO users(name, age)VALUES("+username+","+userage+")");
            Cursor c=myDatabase.rawQuery("SELECT * FROM users", null);
            int nameIndex=c.getColumnIndex("name");
            int ageIndex=c.getColumnIndex("age");
            c.moveToFirst();
            while(c!=null) {
                String userName=c.getString(nameIndex);
                String userAge=Integer.toString(c.getInt(ageIndex));
                Log.i("user-name", userName);
                Log.i("user-age", userAge);
                c.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}