package com.ietiger.account.http;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 16-11-26 下午3:22
 */
public class HttpManager {
    private static final String BASE_URL_DEV = "http://112.124.23.219/";
    private static final String BASE_URL = "https://www.pica.im/";
    public static String SHARE_URL = "https://www.pica.im/";

    private static final int DEFAULT_TIMEOUT = 30;
    private static boolean isDev = true;

    private Retrofit retrofit;

    private HttpManager() {
        this.retrofit = initRetrofit();
    }

    private Retrofit initRetrofit() {
        SHARE_URL = isDev ? BASE_URL_DEV : BASE_URL;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //.retryOnConnectionFailure(true)
                .addInterceptor(new RequestInterceptor())
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return  new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(SHARE_URL)
                .build();
    }

    private static class SingletonHolder{
        private static final HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void setDev(boolean isDev) {
        HttpManager.isDev = isDev;
        SHARE_URL = isDev ? BASE_URL_DEV : BASE_URL;
    }

    public static boolean isDev() {
        return isDev;
    }

    public <S> S createService(Class<S> serviceClass){
        return this.retrofit.create(serviceClass);
    }
}
