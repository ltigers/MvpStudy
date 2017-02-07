package com.ietiger.account.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.ietiger.account.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-2-7 上午11:35
 */

public abstract class MvpActivity<P extends BasePresenter> extends AppCompatActivity{
    protected P mvpPresenter;
    protected Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
