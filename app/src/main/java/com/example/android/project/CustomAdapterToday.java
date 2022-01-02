package com.example.android.project;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.project.views.DayViewCheckBox;
import com.example.android.project.views.MtDatabaseHelper;
import com.example.android.project.views.RobotoBoldTextView;

import java.util.ArrayList;

public class CustomAdapterToday extends RecyclerView.Adapter<CustomAdapterToday.MyViewHolder>
{
    private Context context;
    private Activity activity;
    private static ArrayList<String> Name;
    private ArrayList<String> day_sun;
    private ArrayList<String> day_mon;
    private ArrayList<String> day_tue;
    private ArrayList<String> day_wed;
    private ArrayList<String> day_thu;
    private ArrayList<String> day_fri;
    private ArrayList<String> day_sat;
    private ArrayList<String> time;
    private ArrayList<String> quantity;
    private ArrayList<String> medicationType;
    private ImageView remove_data;
    public static MtDatabaseHelper mydb;
    private static Activity activityy;

    CustomAdapterToday(Context context,
                  ArrayList Name,
                  ArrayList day_sun,
                  ArrayList day_mon,
                  ArrayList day_tue,
                  ArrayList day_wed,
                  ArrayList day_thu,
                  ArrayList day_fri,
                  ArrayList day_sat,
                  ArrayList time,
                  ArrayList quantity,
                  ArrayList medicationType,
                  MtDatabaseHelper myDb,
                  Activity i
    )
    {
        this.context=context;
        this.Name=Name;
        this.day_sun=day_sun;
        this.day_mon=day_mon;
        this.day_tue=day_tue;
        this.day_wed=day_wed;
        this.day_thu=day_thu;
        this.day_fri=day_fri;
        this.day_sat=day_sat;
        this.time=time;
        this.quantity=quantity;
        this.medicationType=medicationType;
        this.mydb=myDb;
        this.activityy=i;


    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_today_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(String.valueOf(Name.get(position)));

        holder.sun.setEnabled(false);
        holder.mon.setEnabled(false);
        holder.tue.setEnabled(false);
        holder.wed.setEnabled(false);
        holder.thu.setEnabled(false);
        holder.fri.setEnabled(false);
        holder.sat.setEnabled(false);

        if(String.valueOf(day_sun.get(position)).equals("yes"))
        {
            holder.sun.setChecked(true);
        }
        else
        {
            holder.sun.setChecked(false);
        }

        if(String.valueOf(day_mon.get(position)).equals("yes"))
        {
            holder.mon.setChecked(true);
        }
        else
        {
            holder.mon.setChecked(false);
        }

        if(String.valueOf(day_tue.get(position)).equals("yes"))
        {
            holder.tue.setChecked(true);
        }
        else
        {
            holder.tue.setChecked(false);
        }

        if(String.valueOf(day_wed.get(position)).equals("yes"))
        {
            holder.wed.setChecked(true);
        }
        else
        {
            holder.wed.setChecked(false);
        }

        if(String.valueOf(day_thu.get(position)).equals("yes"))
        {
            holder.thu.setChecked(true);
        }
        else
        {
            holder.thu.setChecked(false);
        }
        if(String.valueOf(day_fri.get(position)).equals("yes"))
        {
            holder.fri.setChecked(true);
        }
        else
        {
            holder.fri.setChecked(false);
        }

        if(String.valueOf(day_sat.get(position)).equals("yes"))
        {
            holder.sat.setChecked(true);
        }
        else
        {
            holder.sat.setChecked(false);
        }




        holder.time.setText(String.valueOf(time.get(position)));
        holder.quantity_type.setText(String.valueOf(medicationType.get(position)));

    }

    @Override
    public int getItemCount() {

            return Name.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {


        RobotoBoldTextView name,time,quantity_type;
        DayViewCheckBox sun,mon,tue,wed,thu,fri,sat;
        ImageView remove_row,done_row;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_medicine_name_today);
            time=itemView.findViewById(R.id.tv_med_time_today);
            quantity_type=itemView.findViewById(R.id.tv_dose_details_today);
            sun=itemView.findViewById(R.id.tv_sunday_today);
            mon=itemView.findViewById(R.id.tv_monday_today);
            tue=itemView.findViewById(R.id.tv_tuesday_today);
            wed=itemView.findViewById(R.id.tv_wednesday_today);
            thu=itemView.findViewById(R.id.tv_thursday_today);
            fri=itemView.findViewById(R.id.tv_friday_today);
            sat=itemView.findViewById(R.id.tv_saturday_today);
            remove_row=itemView.findViewById(R.id.iv_ignore_med_today);
            done_row=itemView.findViewById(R.id.iv_take_med_today);

            // if (addMedicineActivity.getcontext() == context)
            // {
            //      done_row.setVisibility(View.GONE);
            // }



            remove_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mydb.remove_row(Name.get(getLayoutPosition()));
                    activityy.recreate();
                    //Toast.makeText( view.getContext(), "position = " + getLayoutPosition() +"removed", Toast.LENGTH_SHORT).show();

                    //name.setVisibility(View.INVISIBLE);
                }
            });
        }
    }


}
