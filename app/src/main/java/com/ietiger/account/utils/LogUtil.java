package com.ietiger.account.utils;

import android.util.Log;

/**
 * Created by Tiger on 16-10-18.
 */

public class LogUtil {

    public static boolean debugEnable = true;

    public static void setDebugEnable(boolean debug) {
        LogUtil.debugEnable = debug;
    }

    public static void v(String TAG,String message){
        if(debugEnable){
            Log.v(TAG,message);
        }
    }

    public static void d(String TAG,String message){
        if(debugEnable){
            Log.d(TAG,message);
        }
    }

    public static void i(String TAG,String message){
        if(debugEnable){
            Log.i(TAG,message);
        }
    }

    public static void w(String TAG,String message){
        if(debugEnable){
            Log.w(TAG,message);
        }
    }

    public static void e(String TAG,String message){
        if(debugEnable){
            Log.e(TAG,message);
        }
    }
}
