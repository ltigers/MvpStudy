package com.ietiger.account.mvp.callback;

import com.ietiger.account.mvp.Bean.Result;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-1-13 下午2:30
 */

public abstract class ModelCallback<T> extends Subscriber<Result<T>>{

    public abstract void onSuccess(T model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404 || code == 500) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(Result<T> result) {
        if(result == null){
            onFailure("数据加载失败");
        }else if(result.success){
            onSuccess(result.getData());
        }else{
            onFailure(result.error_message);
        }
    }
}
