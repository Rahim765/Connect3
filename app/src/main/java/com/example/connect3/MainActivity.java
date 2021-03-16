package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    String status="";
    Button button;
    CheckBox checkBox;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button = findViewById(R.id.button2);
       button2=findViewById(R.id.button);
       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (checkBox.isChecked())
               {
                   status= "Single";
               }

               Intent intent = new Intent(MainActivity.this,Connect4.class);
               if (checkBox.isChecked()) {
                   intent.putExtra("Single", status);
               }
               else {
                   status=null;
               }
               startActivity(intent);
           }
       });
         checkBox = findViewById(R.id.checkBox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked())
                {
                    status= "Single";
                }

                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                if (checkBox.isChecked()) {
                    intent.putExtra("Single", status);
                }
                else {
                    status=null;
                }
                startActivity(intent);
            }
        });


    }
}