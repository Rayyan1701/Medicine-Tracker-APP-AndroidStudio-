package com.example.android.project;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class mystorageclass
{
    public String date;
    ArrayList<String> Name1, day_sun1,day_mon1,day_tue1,day_wed1,day_thu1,day_fri1,day_sat1,time1,quantity1, medicationType1;

    mystorageclass()
    {
        this.date=null;
    }


    public mystorageclass load()
    {
        try {

            String filepath="C:\\Users\\mahab\\Desktop\\Newfolder\\aaRayyan\\5th_sem\\MAD\\jj.txt";
            File file = new File(filepath);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String currentDateandTime = sdf.format(new Date());

           // filepath=filepath.concat(currentDateandTime);
            //filepath=filepath.concat(".txt");

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);


            mystorageclass o =(mystorageclass) objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return o;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    private mystorageclass getMystorageclass() {
        return this;
    }

    public void save()
    {

        try {
            String filepath="C:\\Users\\mahab\\Desktop\\Newfolder\\aaRayyan\\5th_sem\\MAD\\jj1.txt";
            File file = new File(filepath);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String currentDateandTime = sdf.format(new Date());

           // filepath=filepath.concat(currentDateandTime);
            //filepath=filepath.concat(".txt");
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(getMystorageclass());
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
