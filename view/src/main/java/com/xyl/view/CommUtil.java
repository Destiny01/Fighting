package com.xyl.view;

import android.content.Context;

/**
 * @author xyl on 2019/6/4.
 */
public class CommUtil {
    private static CommUtil instance;
    private Context context;

    private CommUtil(Context context) {
        this.context = context;
    }

    public static CommUtil getInstance(Context context) {
        if (instance == null) {
            instance = new CommUtil(context);
        }
        return instance;
    }
}
