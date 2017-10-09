package com.veryworks.iyeongjun.shakestarterbasic;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by iyeongjun on 2017. 10. 9..
 */

public class ReactiveDisplayEventDriver {
    private static ReactiveDisplayEventDriver mInstance;
    private PublishSubject<String> mSubject;

    private ReactiveDisplayEventDriver() { mSubject = PublishSubject.create(); }

    public static ReactiveDisplayEventDriver getInstance() {
        if (mInstance == null) {
            mInstance = new ReactiveDisplayEventDriver();
        }
        return mInstance;
    }

    public void sendEvent(String str) {
        mSubject.onNext(str);
    }
    public Observable<String> getObservable() {
        return mSubject;
    }
}
