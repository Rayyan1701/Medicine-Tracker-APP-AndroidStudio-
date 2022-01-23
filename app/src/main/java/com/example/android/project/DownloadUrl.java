package com.example.android.project;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl
{
    public String ReadTheURL(String placeurl) throws IOException
    {
        String data="";
        InputStream inputstream=null;
        HttpURLConnection httpURLConnection=null;

        try
        {
            URL url=new URL(placeurl);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputstream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputstream));
            StringBuffer stringBuffer = new StringBuffer();

            String line = "";

            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            data=stringBuffer.toString();
            bufferedReader.close();


        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            inputstream.close();
            httpURLConnection.disconnect();
        }


        Log.d("downloadurl","k=11"+"   "+data+"   222"+placeurl);

        return data;
    }
}
