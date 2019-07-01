package com.xyl.mvp.mvp1.base;

/**
 * @author xyl on 2019/4/9.
 */
public interface Callback<T> {

    void onSuccess(T data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
