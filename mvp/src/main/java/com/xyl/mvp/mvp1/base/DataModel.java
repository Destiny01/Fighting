package com.xyl.mvp.mvp1.base;

/**
 * @author xyl on 2019/4/9.
 */
public class DataModel {
    public static BaseModel request(String token) {
        BaseModel model = null;
        try {
            model = (BaseModel) Class.forName(token).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return model;
    }
}
