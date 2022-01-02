package com.example.android.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.android.project.views.MtDatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity {

    View c1,c2,c3;

    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    MtDatabaseHelper mydb;
    ArrayList<String> Name, day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        c1=(View)findViewById(R.id.add_med_btn);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <addMedicineActivity> temp = addMedicineActivity.class;
                myfunc(temp);
            }
        });

        c2=(View)findViewById(R.id.check_prog_btn);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<checkProgressActivity> temp = checkProgressActivity.class;
                myfunc(temp);
            }
        });

        c3=(View)findViewById(R.id.med_map_btn);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <searchMedicalshopActivity> temp = searchMedicalshopActivity.class;
                myfunc(temp);
            }
        });

        BottomNavigationView bottomNavigation=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        View v=bottomNavigation.findViewById(R.id.today_button);
        v.performClick();



        mydb= new MtDatabaseHelper(UserInfoActivity.this);
        Name=new ArrayList<>();
        day_sun =new ArrayList<>();
        day_mon=new ArrayList<>();
        day_tue=new ArrayList<>();
        day_wed =new ArrayList<>();
        day_thu=new ArrayList<>();
        day_fri=new ArrayList<>();
        day_sat=new ArrayList<>();
        time=new ArrayList<>();
        quantity=new ArrayList<>();
        medicationType=new ArrayList<>();

        storeTodaysDataInArrays();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        customAdapter = new CustomAdapter(UserInfoActivity.this,Name,day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType,mydb,this);
        recyclerView.setAdapter(customAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(UserInfoActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);




    }


    public void myfunc(Class temp){
        Intent intent = new Intent(this, temp);
        startActivity(intent);
    }

    void storeTodaysDataInArrays(){
        Cursor cursor = mydb.readTodaysAllData();
        // TextView e=(TextView)findViewById(R.id.nodatatextview);
        if(cursor.getCount() == 0){

            // e.setVisibility(View.VISIBLE);

        }else{
            while (cursor.moveToNext())
            {
                Name.add(cursor.getString(1));
                day_sun.add(cursor.getString(2));
                day_mon.add(cursor.getString(3));
                day_tue.add(cursor.getString(4));
                day_wed.add(cursor.getString(5));
                day_thu.add(cursor.getString(6));
                day_fri.add(cursor.getString(7));
                day_sat.add(cursor.getString(8));
                time.add(cursor.getString(9));
                quantity.add(cursor.getString(10));
                medicationType.add(cursor.getString(11));


            }
            //  e.setVisibility(View.GONE);

        }
    }


}