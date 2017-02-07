package com.ietiger.account.main;

import com.ietiger.account.http.HttpManager;
import com.ietiger.account.mvp.callback.ModelCallback;
import com.ietiger.account.mvp.presenter.BasePresenter;
import com.ietiger.account.utils.LogUtil;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-2-7 上午11:58
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }

    public void getData(int page){
        Subscription subscription = HttpManager.getInstance()
                .createService(MainService.class)
                .getThemeList(page)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ModelCallback<List<Template>>() {
                    @Override
                    public void onSuccess(List<Template> model) {
                        mvpView.showData(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        LogUtil.i("Test",msg);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
        addSubscription(subscription);
    }
}
