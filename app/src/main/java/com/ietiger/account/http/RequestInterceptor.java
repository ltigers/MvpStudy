package com.ietiger.account.http;

import com.ietiger.account.TigerApp;
import com.ietiger.account.utils.SPUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 16-12-8 上午10:12
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String token = (String) SPUtil.get(TigerApp.mApp,"token","");
        Request.Builder mBuilder = original.newBuilder()
                .addHeader("device","android");
        Response response = null;
        if(!token.equals("")){
            Request.Builder builder = mBuilder.header("token",token);
            if(original.method().equals("GET")){
                HttpUrl httpUrl = original.url();
                HttpUrl newHttp = httpUrl.newBuilder()
                        .build();
                Request request = builder.url(newHttp)
                        .method(original.method(), original.body())
                        .build();
                response = chain.proceed(request);
            }else{
                RequestBody requestBody = original.body();
                if(requestBody != null){
                    if(requestBody instanceof FormBody){
                        FormBody.Builder newFormBody = new FormBody.Builder();
                        FormBody oidFormBody = (FormBody) original.body();
                        for (int i = 0;i<oidFormBody.size();i++){
                            newFormBody.addEncoded(oidFormBody.encodedName(i),oidFormBody.encodedValue(i));
                        }
                        builder.method(original.method(),newFormBody.build());
                    }
                }else{
                    builder.method(original.method(), original.body());
                }
                Request request = builder.build();
                response = chain.proceed(request);
            }
        }else{
            Request request = mBuilder.method(original.method(), original.body())
                    .build();
            response = chain.proceed(request);
        }
        return response;
    }
}
