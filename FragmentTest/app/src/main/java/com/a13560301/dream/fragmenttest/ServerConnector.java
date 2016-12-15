package com.a13560301.dream.fragmenttest;

import android.os.StrictMode;

import com.a13560301.dream.fragmenttest.Model.EntityModel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by dream on 12/13/2016.
 */

public class ServerConnector {
    public final static String http = "http://";
    public final static String ip = "192.168.2.179";
    public final static String dir = "letseat";
    public final static String seturl = http+ip+"/"+dir;
    // http://172.24.1.54/148

    public ServerConnector()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String connect(String page,boolean response)
    {
        //1.Create variable of StringBuilder for response streaming data
        StringBuilder result = new StringBuilder();

        //2.Create variable for seturl+page :: http://172.24.1.197/148/index.php
        String url = seturl+"/"+page;

        //3.Create variable for URL data
        try {
            URL urlcon = new URL(url);

            //4.Create variable for HttpURLConnect
            HttpURLConnection connection = (HttpURLConnection) urlcon.openConnection();

            //5.Setting HttpURLConnect
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("charset","utf-8");

            //6.Create DataOutputStream
            DataOutputStream outstream = new DataOutputStream(connection.getOutputStream());

            //7.Condition  of response
            if (response == true)
            {
                //7.1 Create variable of String for line
                String line;

                //7.2 Create variable of BufferReader
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

                //7.3 Create Loop for read
                while ((line = br.readLine())!=null)
                {
                    result.append(line);//เอาข้อมูลจากline ไปก็บไว้ทุกๆบรรทัด
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //8.return data from StringBuilder
        return result.toString();
    }

    ///connect adding EntityModel parameter for response streaming data
    public String connect(String page, boolean response, EntityModel entity)
    {
        //1.Create variable of StringBuilder for response streaming data
        StringBuilder result = new StringBuilder();

        //2.Create variable for seturl+page :: http://172.24.1.54/148/index.php
        String url = seturl+"/"+page;

        //3.Create variable for URL data
        try {
            URL urlcon = new URL(url);

            //4.Create variable for HttpURLConnect
            HttpURLConnection connection = (HttpURLConnection) urlcon.openConnection();

            //5.Setting HttpURLConnect
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("charset","utf-8");

            //6.Create DataOutputStream
            DataOutputStream outstream = new DataOutputStream(connection.getOutputStream());

            ///6.1 Additional data
            outstream.write(entity.getDataEncode());

            //7.Condition  of response
            if (response == true)
            {
                //7.1 Create variable of String for line
                String line;

                //7.2 Create variable of BufferReader
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

                //7.3 Create Loop for read
                while ((line = br.readLine())!=null)
                {
                    result.append(line);//เอาข้อมูลจากline ไปก็บไว้ทุกๆบรรทัด
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //8.return data from StringBuilder
        return result.toString();
    }
}
