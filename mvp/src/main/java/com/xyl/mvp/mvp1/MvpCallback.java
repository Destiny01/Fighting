package com.xyl.mvp.mvp1;

import com.xyl.mvp.mvp1.base.Callback;

/**
 * @author xyl on 2019/4/9.
 */
public interface MvpCallback<T> extends Callback<T> {

    void onSuccess(T data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
