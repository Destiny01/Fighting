package com.xyl.app.image.loadPolicy;

import com.xyl.app.image.request.BitmapRequest;

/**
 * 后进先加载
 *
 * @author xyl on 2019/4/4.
 */
public class ReversePolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request2.getSerialNo() - request1.getSerialNo();
    }
}
