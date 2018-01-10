package com.example.myidoldemo;

import android.util.Log;

/**
 * Created by Han on 2018/1/8.
 */

public class L {
    private static boolean debug=true;
    private static final String TAG="MyIdolDemo";
    public static void e(String msg){
        if(debug){
            Log.e(TAG,msg);
        }
    }
}
