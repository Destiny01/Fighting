package com.xyl.app.image.loadPolicy;

import com.xyl.app.image.request.BitmapRequest;

/**
 * 先进先加载
 *
 * @author xyl on 2019/4/4.
 */
public class SerialPolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request1.getSerialNo() - request2.getSerialNo();
    }
}
