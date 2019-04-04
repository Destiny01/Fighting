package com.xyl.app.image.loader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xyl on 2019/4/4.
 */
public class LoaderManager {
    private Map<String, Loader> loaderMap = new HashMap<>();

    private static LoaderManager instance = new LoaderManager();

    private LoaderManager() {
        register("http", new UrlLoader());
        register("https", new UrlLoader());
        register("file", new LocalLoader());
    }

    public static LoaderManager getInstance() {
        return instance;
    }

    private void register(String schema, Loader loader) {
        loaderMap.put(schema, loader);
    }

    public Loader getLoader(String schema) {
        if (loaderMap.containsKey(schema)) {
            return loaderMap.get(schema);
        }
        return null;
    }

}
