package com.ietiger.account.main;

import com.ietiger.account.mvp.Bean.Result;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-2-7 下午12:07
 */

public interface MainService {

    @GET("v031/card/themes")
    Observable<Result<List<Template>>> getThemeList(@Query("page") int page);
}
