package com.example.android.project.views;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import android.animation.TimeAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDatabase2Helper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "MedicineDetails.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_progress_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEDNAME = "medicine_name";
    private static final String COLUMN_DAY_SUN = "sunday";
    private static final String COLUMN_DAY_MON = "monday";
    private static final String COLUMN_DAY_TUE = "tuesday";
    private static final String COLUMN_DAY_WED = "wednesday";
    private static final String COLUMN_DAY_THU = "thursday";
    private static final String COLUMN_DAY_FRI = "friday";
    private static final String COLUMN_DAY_SAT = "satday";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_QUANTITY = "Quantity";
    private static final String COLUMN_TYPE="type";

    public MyDatabase2Helper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEDNAME + " TEXT, " +
                COLUMN_DAY_SUN + " TEXT, " +
                COLUMN_DAY_MON + " TEXT, " +
                COLUMN_DAY_TUE + " TEXT, " +
                COLUMN_DAY_WED + " TEXT, " +
                COLUMN_DAY_THU + " TEXT, " +
                COLUMN_DAY_FRI + " TEXT, " +
                COLUMN_DAY_SAT + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_QUANTITY + " TEXT, " +
                COLUMN_TYPE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
