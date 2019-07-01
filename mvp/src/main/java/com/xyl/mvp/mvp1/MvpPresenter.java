package com.xyl.mvp.mvp1;

import com.xyl.mvp.mvp1.base.BasePresenter;
import com.xyl.mvp.mvp1.base.Callback;
import com.xyl.mvp.mvp1.base.DataModel;
import com.xyl.mvp.mvp1.base.Token;

/**
 * @author xyl on 2019/4/9.
 */
public class MvpPresenter extends BasePresenter<MvpView> {


    public void getData(String params) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        DataModel.request(Token.API_USER_DATA)
                .execute(new Callback() {
                    @Override
                    public void onSuccess(Object data) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        MvpModel.getNetData(params, new MvpCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached())
                    getView().showData(data);
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached())
                    getView().showToast(msg);
            }

            @Override
            public void onError() {
                if (isViewAttached())
                    getView().showErr();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    getView().hideLoading();
            }
        });
    }
}
