package com.xyl.app.image.request;

import com.xyl.app.image.loader.Loader;
import com.xyl.app.image.loader.LoaderManager;

import java.util.concurrent.BlockingQueue;

/**
 * 请求转发器
 *
 * @author xyl on 2019/4/4.
 */
public class RequestDispatcher extends Thread {
    public final static String TAG = "RequestDispatcher";
    //请求队列
    private BlockingQueue<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                //阻塞式函数
                BitmapRequest request = mRequestQueue.take();
                /**
                 * 处理请求对象
                 */
                String schema = parseSchema(request.getImageUrl());
                //获取加载器
                Loader loader = LoaderManager.getInstance().getLoader(schema);
                loader.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String parseSchema(String imageUrl) {
        if (imageUrl.contains("://")) {
            return imageUrl.split("://")[0];
        }

        return imageUrl;
    }

}
