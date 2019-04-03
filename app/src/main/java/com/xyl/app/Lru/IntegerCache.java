package com.xyl.app.Lru;

import android.util.LruCache;

/**
 * 可见, 每次的 get 和 put 操作, 都会造成序列中的重排序,
 * 最近使用的元素在末尾, 最近最少使用的元素在头部,
 * 当容量超过限制时会移出最近最少使用的元素.
 *
 * @author xyl on 2019/4/3.
 */
public class IntegerCache extends LruCache<String, Integer> {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public IntegerCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Integer value) {
        return Integer.SIZE;
    }
}
