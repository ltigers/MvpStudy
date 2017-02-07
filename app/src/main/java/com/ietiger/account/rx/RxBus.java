package com.ietiger.account.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 16-11-29 上午9:45
 */
public class RxBus {

    private final Subject<Object, Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    private static class SingletonHolder{
        private static final RxBus INSTANCE = new RxBus();
    }

    public static RxBus getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 发送一个新的事件
     * RxBus.getInstance().post(new StudentEvent("007","小明"));
     */
    public void post (Object o) {
        bus.onNext(o);
    }

    /**
     * rxSbscription=RxBus.getInstance().toObserverable(StudentEvent.class)
                                        .subscribe(new Action1<StudentEvent>() {
                                          @Override
                                            public void call(StudentEvent studentEvent) {
                                            textView.setText("id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName());
                                        }});
         注销事件
         @Override
         protected void onDestroy() {
             if (!rxSbscription.isUnsubscribed()){
                rxSbscription.unsubscribe();
            }
            super.onDestroy();
         }
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable (Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
