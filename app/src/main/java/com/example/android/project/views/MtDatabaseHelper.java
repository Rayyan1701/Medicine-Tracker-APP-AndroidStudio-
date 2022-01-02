package com.example.android.project.views;
import android.animation.TimeAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MtDatabaseHelper extends SQLiteOpenHelper
{

    private Context context;
    private static final String DATABASE_NAME = "MedicineDetails.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_table";
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

    public MtDatabaseHelper(@Nullable Context context) {
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

    public void addMEDinDatabase(String name, String sun,String mon,String tue,String wed,String thu,String fri,String sat,String time,String quantity, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MEDNAME, name);
        cv.put(COLUMN_DAY_SUN, sun);
        cv.put(COLUMN_DAY_MON, mon);
        cv.put(COLUMN_DAY_TUE, tue);
        cv.put(COLUMN_DAY_WED, wed);
        cv.put(COLUMN_DAY_THU, thu);
        cv.put(COLUMN_DAY_FRI, fri);
        cv.put(COLUMN_DAY_SAT, sat);

        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_QUANTITY, quantity);

        cv.put(COLUMN_TYPE,type);


        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Cursor readTodaysAllData(){
        String query = "SELECT * FROM " + TABLE_NAME+" WHERE DATEPART(dw,GETDATE())='yes'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }




    public void remove_row(String name)
    {
       // String query = "DELETE FROM " + TABLE_NAME+" WHERE "+ COLUMN_ID +"="+ layoutPosition ;
        SQLiteDatabase db = this.getWritableDatabase();

       // Cursor cursor = null;
       // if(db != null){
       //     cursor = db.rawQuery(query, null);
       // }

        long res=db.delete(TABLE_NAME,"medicine_name=?",new String[]{name});
        if(res == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }




    }
}
