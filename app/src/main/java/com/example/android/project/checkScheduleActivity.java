package com.example.android.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project.views.MtDatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class checkScheduleActivity extends AppCompatActivity {

    View c1,c2,c3;
    DatePicker d;
    Button show_data_btn;

    TextView e;


    CustomAdapterFull customAdapter;
    RecyclerView recyclerView;



    MtDatabaseHelper mydb;
    ArrayList<String> Name, day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_schedule);

        e=(TextView)findViewById(R.id.textView_notif);

        d=(DatePicker)findViewById(R.id.datePickerSchedule);

        show_data_btn=(Button)findViewById(R.id.show_data_button);

        show_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //disp.setText("date :"+getCurrentDate());



                storeTodaysDataInArrays(getCurrentDate());

                recyclerView=(RecyclerView)findViewById(R.id.recyclerView_full);

                customAdapter = new CustomAdapterFull(checkScheduleActivity.this,Name,day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType,mydb);
                recyclerView.setAdapter(customAdapter);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(checkScheduleActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);



                //e.setVisibility(View.VISIBLE);

                //e.setText("date :"+getCurrentDate());


            }
        });




        c1=(View)findViewById(R.id.add_med_btn);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class <addMedicineActivity> temp = addMedicineActivity.class;
                myfunc(temp);
            }
        });


        c2=(View)findViewById(R.id.today_button);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<TodayInfoActivity> temp = TodayInfoActivity.class;
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
        View v=bottomNavigation.findViewById(R.id.check_prog_btn);
        v.performClick();










    }

    public void myfunc(Class temp){
        Intent intent = new Intent(this, temp);
        startActivity(intent);
    }


    void storeTodaysDataInArrays(String s){
        String m;
        Toast.makeText(this, "day :"+s, Toast.LENGTH_SHORT).show();
        if(s.equals("Sunday"))
        {
            m="sunday";
        }
        else if(s.equals("Monday"))
        {
            m="monday";
        }
        else if(s.equals("Tuesday"))
        {
            m="tuesday";
        }
        else if(s.equals("Wednesday"))
        {
            m="wednesday";
        }
        else if(s.equals("Thursday"))
        {
            m="thursday";
        }
        else if(s.equals("Friday"))
        {
            m="friday";
        }
        else
        {
            m="satday";
        }

        mydb= new MtDatabaseHelper(checkScheduleActivity.this);
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




        Cursor cursor = mydb.readAdaysAllData(m);

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



    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();



        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date date = new Date(d.getYear(), d.getMonth(), d.getDayOfMonth()-1);
        String dayOfWeek = simpledateformat.format(date);

        builder.append(dayOfWeek);

        return builder.toString();
    }


}