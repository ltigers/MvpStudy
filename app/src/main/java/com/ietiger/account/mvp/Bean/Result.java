package com.ietiger.account.mvp.Bean;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-1-13 下午2:28
 */

public class Result<T> {

    public boolean success;
    public String error_message;
    public int error_code;
    public int count;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}