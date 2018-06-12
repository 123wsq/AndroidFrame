package com.example.wsq.library.utils;


import com.example.wsq.library.BuildConfig;

public class LogUtils {


    public static void d(String msg){

        if (BuildConfig.DEBUG){
            System.out.println(msg);
        }
    }
}
