package com.ietiger.account.mvp.presenter;

import com.ietiger.account.rx.TigerCompositeSubscription;

import rx.Subscription;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 17-1-13 下午2:19
 */
public class BasePresenter<V> {

    protected V mvpView;

    private TigerCompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        onUnSubscribe();
        this.mvpView = null;
    }


    public void onUnSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            if(!mCompositeSubscription.isUnsubscribed()){
                mCompositeSubscription.unsubscribe();
            }
        }
    }
    public boolean isUnSubscribed(){
        if(mCompositeSubscription == null){
            return true;
        }else if(mCompositeSubscription.hasSubscriptions()){
            if(mCompositeSubscription.isUnsubscribed()){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    public void removeSubscription(Subscription subscription){
        if(mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.remove(subscription);
        }
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new TigerCompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
