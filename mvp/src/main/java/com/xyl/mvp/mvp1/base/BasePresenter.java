package com.xyl.mvp.mvp1.base;

/**
 * @author xyl on 2019/4/9.
 */
public class BasePresenter<V extends BaseView> {
    private V view;

    public void attachView(V baseView) {
        this.view = baseView;
    }

    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }
}
