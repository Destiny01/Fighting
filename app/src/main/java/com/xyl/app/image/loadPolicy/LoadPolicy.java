package com.xyl.app.image.loadPolicy;

import com.xyl.app.image.request.BitmapRequest;

/**
 * 加载策略
 * 加载策略分为两种：先进先出和先进后出。例如，当ListView滑动到最底部时，用户最想看到的是划出来的条目。
 *
 * @author xyl on 2019/4/4.
 */
public interface LoadPolicy {
    /**
     * 两个BitmapRequest进行比较
     *
     * @param request1
     * @param request2
     * @return 小于0，request1 < request2； 大于0，request1 > request2；等于
     */
    int compareTo(BitmapRequest request1, BitmapRequest request2);
}
