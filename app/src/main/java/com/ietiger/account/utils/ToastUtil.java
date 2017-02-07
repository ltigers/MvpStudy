package com.ietiger.account.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Tiger on 16-7-2.
 */
public class ToastUtil {
    private static Toast toast;
    public static void showToast(Context context,String tip){
        if (toast == null) {
            toast = Toast.makeText(context,tip,Toast.LENGTH_SHORT);
        } else {
            toast.setText(tip);
        }

        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showToast(Context context,@StringRes int resId){
        if (toast == null) {
            toast = Toast.makeText(context,resId,Toast.LENGTH_SHORT);
        } else {
            toast.setText(resId);
        }
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showLongToast(Context context,String tip){
        if (toast == null) {
            toast = Toast.makeText(context,tip,Toast.LENGTH_LONG);
        } else {
            toast.setText(tip);
        }

        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showLongToast(Context context,@StringRes int resId){
        if (toast == null) {
            toast = Toast.makeText(context,resId,Toast.LENGTH_LONG);
        } else {
            toast.setText(resId);
        }
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
