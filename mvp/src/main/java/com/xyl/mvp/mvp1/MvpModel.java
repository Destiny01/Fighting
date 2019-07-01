package com.xyl.mvp.mvp1;


import android.os.Handler;

/**
 * @author xyl on 2019/4/9.
 */
public class MvpModel {

    public static void getNetData(final String param, final MvpCallback<String> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param) {
                    case "normal":
                        callback.onSuccess("根据参数" + param + "的请求网络数据成功");
                        break;
                    case "failure":
                        callback.onFailure("请求失败：参数有误");
                        break;
                    case "error":
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }
        }, 2000);
    }
}
