package com.example.android.project;

import android.app.TimePickerDialog;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.project.views.DayViewCheckBox;
import com.example.android.project.views.MtDatabaseHelper;
import com.example.android.project.views.RobotoBoldTextView;

import java.util.Calendar;
import java.util.Locale;

public class AddMedicineDetailsForm extends AppCompatActivity  {

    static String valueSelected=null;
    String Name;
    String medicationType;
    String time;
    String day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat;
    String quantity;



    Spinner spin;
    RobotoBoldTextView medicinetime;

    int hour,minute;

    EditText n;
    private View rootView;


    AppCompatCheckBox everyday;

    public boolean[] dayOfWeekList = new boolean[7];







    Button submit;
    String[] medicationTypes={"Enter Type","Pill(s)","spray","capsule(s)","injection(s)","unit(s)"};


    @Override
    public void updateServiceGroup(ServiceConnection conn, int group, int importance) {
        super.updateServiceGroup(conn, group, importance);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_details_form);



        everyday=(AppCompatCheckBox)findViewById(R.id.every_day);
        medicinetime=(RobotoBoldTextView)findViewById(R.id.tv_medicine_time);

        medicinetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });


        everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DayViewCheckBox t1=(DayViewCheckBox)findViewById(R.id.dv_sunday);
                DayViewCheckBox t2=(DayViewCheckBox)findViewById(R.id.dv_monday);
                DayViewCheckBox t3=(DayViewCheckBox)findViewById(R.id.dv_tuesday);
                DayViewCheckBox t4=(DayViewCheckBox)findViewById(R.id.dv_wednesday);
                DayViewCheckBox t5=(DayViewCheckBox)findViewById(R.id.dv_thursday);
                DayViewCheckBox t6=(DayViewCheckBox)findViewById(R.id.dv_friday);
                DayViewCheckBox t7=(DayViewCheckBox)findViewById(R.id.dv_saturday);
                if(everyday.isChecked())
                {
                    Toast.makeText(AddMedicineDetailsForm.this,"Clicked",Toast.LENGTH_LONG).show();


                    t1.setChecked(true);
                    t2.setChecked(true);
                    t3.setChecked(true);
                    t4.setChecked(true);
                    t5.setChecked(true);
                    t6.setChecked(true);
                    t7.setChecked(true);

                    //t1.setEnabled(true);
                }
                else
                {
                    Toast.makeText(AddMedicineDetailsForm.this,"Un-Clicked",Toast.LENGTH_LONG).show();
                    t1.setChecked(false);
                    t2.setChecked(false);
                    t3.setChecked(false);
                    t4.setChecked(false);
                    t5.setChecked(false);
                    t6.setChecked(false);
                    t7.setChecked(false);
                }
            }
        });



        //Button code

        submit=(Button)findViewById(R.id.formsubmitbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddMedicineDetailsForm.this,"Hello",Toast.LENGTH_LONG).show();

                EditText n=(EditText)findViewById(R.id.edit_med_name);
                Name=n.getText().toString();

                DayViewCheckBox t1=(DayViewCheckBox)findViewById(R.id.dv_sunday);
                DayViewCheckBox t2=(DayViewCheckBox)findViewById(R.id.dv_monday);
                DayViewCheckBox t3=(DayViewCheckBox)findViewById(R.id.dv_tuesday);
                DayViewCheckBox t4=(DayViewCheckBox)findViewById(R.id.dv_wednesday);
                DayViewCheckBox t5=(DayViewCheckBox)findViewById(R.id.dv_thursday);
                DayViewCheckBox t6=(DayViewCheckBox)findViewById(R.id.dv_friday);
                DayViewCheckBox t7=(DayViewCheckBox)findViewById(R.id.dv_saturday);

                if(t1.isChecked())
                {
                    day_sun="yes";
                }
                else
                {
                    day_sun="no";
                }

                if(t2.isChecked())
                {
                    day_mon="yes";
                }
                else
                {
                    day_mon="no";
                }

                if(t3.isChecked())
                {
                    day_tue="yes";
                }
                else
                {
                    day_tue="no";
                }

                if(t4.isChecked())
                {
                    day_wed="yes";
                }
                else
                {
                    day_wed="no";
                }

                if(t5.isChecked())
                {
                    day_thu="yes";
                }
                else
                {
                    day_thu="no";
                }

                if(t6.isChecked())
                {
                    day_fri="yes";
                }
                else
                {
                    day_fri="no";
                }

                if(t7.isChecked())
                {
                    day_sat="yes";
                }
                else
                {
                    day_sat="no";
                }

                EditText q=(EditText)findViewById(R.id.tv_dose_quantity);
                quantity=q.getText().toString();

                AppCompatSpinner temp=(AppCompatSpinner)findViewById(R.id.spinner_dose_units);
                medicationType=temp.getSelectedItem().toString();




                MtDatabaseHelper myDB = new MtDatabaseHelper(AddMedicineDetailsForm.this);
                myDB.addMEDinDatabase(Name, day_sun,day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,time,quantity, medicationType);


            }
        });



    }

    private void showTimePicker() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddMedicineDetailsForm.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                medicinetime.setText(String.format(Locale.getDefault(), "%d:%d", selectedHour, selectedMinute));

                time=medicinetime.getText().toString();

            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();





    }

}