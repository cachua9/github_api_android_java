package com.example.github_api.library;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class MyResponse {
    private int code = 0;
    private String data = "-1";

    public MyResponse(HttpURLConnection httpConn) throws IOException {
        int code = httpConn.getResponseCode();
        this.code = code;
        switch (code){
            case 200:{
                InputStream responseStream = httpConn.getInputStream();
                Scanner s = new Scanner(responseStream).useDelimiter("\\A");
                String data = s.hasNext() ? s.next() : "";
                this.data = data;
                break;
            }
            case 400:{

                break;
            }
            case  500:{
                this.data = "Object not found";
                break;
            }
            default:{

            }
        }

//        InputStream responseStream = code / 100 == 2
//                ? httpConn.getInputStream()
//                : httpConn.getErrorStream();
//        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
//        String data = s.hasNext() ? s.next() : "";
//        this.data = data;

        Log.d("MyResponse", String.valueOf(this.code));
        Log.d("MyResponse", this.data);

    }

    public MyResponse(int code, String data){
        this.code = code;
        this.data = data;
    }

    public int getCode(){
        return this.code;
    }

    public String getData(){
        return this.data;
    }
}
