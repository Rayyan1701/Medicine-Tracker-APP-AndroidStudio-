package com.example.android.project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.project.views.DayViewCheckBox;
import com.example.android.project.views.RobotoBoldTextView;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{

    private Context context;
    private Activity activity;
    private ArrayList<String> Name, day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType;

    CustomAdapter(Context context,
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
                  ArrayList medicationType
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


    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

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

       // holder.quantity_type.setText(String.valueOf(quantity.get(position))+" "+String.valueOf(medicationType.get(position)));

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        RobotoBoldTextView name,time,quantity_type;
        DayViewCheckBox sun,mon,tue,wed,thu,fri,sat;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_medicine_name);
            time=itemView.findViewById(R.id.tv_med_time);
            quantity_type=itemView.findViewById(R.id.tv_dose_details);
            sun=itemView.findViewById(R.id.tv_sunday);
            mon=itemView.findViewById(R.id.tv_monday);
            tue=itemView.findViewById(R.id.tv_tuesday);
            wed=itemView.findViewById(R.id.tv_wednesday);
            thu=itemView.findViewById(R.id.tv_thursday);
            fri=itemView.findViewById(R.id.tv_friday);
            sat=itemView.findViewById(R.id.tv_saturday);
        }
    }
}
