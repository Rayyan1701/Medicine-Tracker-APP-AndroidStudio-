package com.example.android.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView o=(TextView)findViewById(R.id.addmedicineviewid);
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Class <addMedicineActivity> temp = addMedicineActivity.class;
                myfunc(temp);
            }
        });

        TextView o1=(TextView)findViewById(R.id.checkprogressviewid);
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <checkProgressActivity> temp = checkProgressActivity.class;
                myfunc(temp);
            }
        });

        TextView o2=(TextView)findViewById(R.id.searchmedicalshopviewid);
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <searchMedicalshopActivity> temp = searchMedicalshopActivity.class;
                myfunc(temp);
            }
        });
    }

    public void myfunc(Class temp){
        Intent intent = new Intent(this, temp);
        startActivity(intent);
    }
}