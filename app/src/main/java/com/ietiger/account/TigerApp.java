package com.ietiger.account;

import android.support.multidex.MultiDexApplication;

import com.ietiger.account.http.HttpManager;
/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-2-7 上午11:50
 */

public class TigerApp extends MultiDexApplication {

    public static TigerApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        HttpManager.setDev(false);
    }


}
