package com.xyl.fighting;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * @author xyl on 2019/3/7.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(final Activity activity) {
                if (!activity.getIntent().getBooleanExtra("isInitToolbar", false)) {
                    //由于加强框架的兼容性,故将 setContentView 放到 onActivityCreated 之后,onActivityStarted 之前执行
                    //而 findViewById 必须在 Activity setContentView() 后才有效,所以将以下代码从之前的 onActivityCreated 中移动到 onActivityStarted 中执行
                    activity.getIntent().putExtra("isInitToolbar", true);
                    //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
                    if (activity.findViewById(R.id.toolbar) != null) {
                        if (activity instanceof AppCompatActivity) {
                            ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                            ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                                activity.getActionBar().setDisplayShowTitleEnabled(false);
                            }
                        }
                    }
                    if (activity.findViewById(R.id.toolbar_title) != null) {
                        ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
                    }
                    if (activity.findViewById(R.id.toolbar_back) != null) {
                        activity.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.onBackPressed();
                            }
                        });
                    }
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //横竖屏切换或配置改变时, Activity 会被重新创建实例, 但 Bundle 中的基础数据会被保存下来,移除该数据是为了保证重新创建的实例可以正常工作
                activity.getIntent().removeExtra("isInitToolbar");
            }
        });
    }

}