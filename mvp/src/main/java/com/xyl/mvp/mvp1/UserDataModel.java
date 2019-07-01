package com.xyl.mvp.mvp1;

import android.os.Handler;

import com.xyl.mvp.mvp1.base.BaseModel;
import com.xyl.mvp.mvp1.base.Callback;

/**
 * @author xyl on 2019/4/9.
 */
public class UserDataModel extends BaseModel<String> {
    @Override
    public void execute(final Callback<String> callback) {
        // 模拟网络请求耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mParams 是从父类得到的请求参数
                switch (params[0]) {
                    case "normal":
                        callback.onSuccess("根据参数" + params[0] + "的请求网络数据成功");
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
