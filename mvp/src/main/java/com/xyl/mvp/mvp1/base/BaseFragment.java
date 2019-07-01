package com.xyl.mvp.mvp1.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author xyl on 2019/4/9.
 */
public abstract class BaseFragment extends Fragment implements BaseView {
    public abstract int getContentViewId();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected Context mContext;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        initAllMembersView(savedInstanceState);
        return rootView;
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseActivity) mContext).showLoading();
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseActivity) mContext).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        ((BaseActivity) mContext).showToast(msg);
    }

    @Override
    public void showErr() {
        checkActivityAttached();
        ((BaseActivity) mContext).showErr();
    }

    protected boolean isAttachedContext() {
        return getActivity() != null;
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
