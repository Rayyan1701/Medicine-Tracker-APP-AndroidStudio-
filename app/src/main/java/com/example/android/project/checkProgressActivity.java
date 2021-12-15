package com.example.android.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class checkProgressActivity extends AppCompatActivity {

    Button c1,c2,c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_progress);

        c1=(Button)findViewById(R.id.add_med_btn);
        c2=(Button)findViewById(R.id.med_map_btn);
        c3=(Button)findViewById(R.id.user_info_btn);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <addMedicineActivity> temp = addMedicineActivity.class;
                myfunc(temp);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <searchMedicalshopActivity> temp = searchMedicalshopActivity.class;
                myfunc(temp);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <UserInfoActivity> temp = UserInfoActivity.class;
                myfunc(temp);
            }
        });
    }

    public void myfunc(Class temp){
        Intent intent = new Intent(this, temp);
        startActivity(intent);
    }
}