package com.xyl.mvp.mvp1.base;

import java.util.Map;

/**
 * @author xyl on 2019/4/9.
 */
public abstract class BaseModel<T> {
    protected String[] params;

    public BaseModel(String... args) {
        this.params = args;
    }

    public abstract void execute(Callback<T> callback);

    protected void requestGetAPI(String url, Callback<T> callback) {

    }

    protected void requestPostAPI(String url, Map params, Callback<T> callback) {

    }
}
