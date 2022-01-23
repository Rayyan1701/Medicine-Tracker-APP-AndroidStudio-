package com.example.android.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.project.views.MtDatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodayInfoActivity extends AppCompatActivity {

    View c1,c2,c3;

    CustomAdapterToday customAdapter;
    RecyclerView recyclerView;

    mystorageclass obj;
    SimpleDateFormat sdf;
    String currentDateandTime;

    Button btest;

    MtDatabaseHelper mydb;
    ArrayList<String> Name, day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_info);








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
                Class<checkScheduleActivity> temp = checkScheduleActivity.class;
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



        mydb= new MtDatabaseHelper(this);
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

        sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        obj=new mystorageclass();

        storeTodaysDataInArrays();
           try {
               obj.date=currentDateandTime;
               obj.Name1=Name;
               obj.day_sun1=day_sun;
               obj.day_mon1=day_mon;
               obj.day_tue1=day_tue;
               obj.day_wed1=day_wed;
               obj.day_thu1=day_thu;
               obj.day_fri1=day_fri;
               obj.day_sat1=day_sat;
               obj.time1=time;
               obj.quantity1=quantity;
               obj.medicationType1=medicationType;
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           obj.save();





        obj=obj.load();

        if(obj==null)
        {
            Log.d("todayerror", "onCreate: 000000000");
        }
/*
        if(obj==null)
        {
            String filepath="C:\\Users\\mahab\\AndroidStudioProjects\\project\\dailydata\\";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String currentDateandTime = sdf.format(new Date());

            filepath=filepath.concat(currentDateandTime);
            filepath=filepath.concat(".txt");
            Log.d("todayyyyyinfoactivity", "emptyfile 1111111 created ");

            try {
                File file = new File(filepath);
                file.createNewFile();
                Log.d("todayyyyyinfoactivity", "emptyfile 000000000 created ");
            } catch (IOException e) {
                e.printStackTrace();
            }

            obj=obj.load();
        }
*/
        if( obj.date ==null || !obj.date.equals(currentDateandTime))
        {
            storeTodaysDataInArrays();
            obj.date=currentDateandTime;
            obj.Name1=Name;
            obj.day_sun1=day_sun;
            obj.day_mon1=day_mon;
            obj.day_tue1=day_tue;
            obj.day_wed1=day_wed;
            obj.day_thu1=day_thu;
            obj.day_fri1=day_fri;
            obj.day_sat1=day_sat;
            obj.time1=time;
            obj.quantity1=quantity;
            obj.medicationType1=medicationType;

            obj.save();
        }
        else
        {
            currentDateandTime= obj.date;
            Name=obj.Name1;
            day_sun=obj.day_sun1;
            day_mon=obj.day_mon1;
            day_tue=obj.day_tue1;
            day_wed=obj.day_wed1;
            day_thu=obj.day_thu1;
            day_fri=obj.day_fri1;
            day_sat=obj.day_sat1;
            time=obj.time1;
            quantity=obj.quantity1;
            medicationType=obj.medicationType1;

        }



        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_today);

        customAdapter = new CustomAdapterToday(TodayInfoActivity.this,Name,day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType,mydb,this,obj);
        recyclerView.setAdapter(customAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(TodayInfoActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);





    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }


    public void myfunc(Class temp){
        Intent intent = new Intent(this, temp);
        startActivity(intent);
    }

    void storeTodaysDataInArrays(){
        Cursor cursor = mydb.readTodaysAllData();
         TextView e=(TextView)findViewById(R.id.textView4);

        if(cursor.getCount() == 0){

             e.setVisibility(View.VISIBLE);



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
              e.setVisibility(View.GONE);


        }
    }


}




