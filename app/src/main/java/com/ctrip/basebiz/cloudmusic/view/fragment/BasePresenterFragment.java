package com.ctrip.basebiz.cloudmusic.view.fragment;

import android.common.lib.core.BaseFragment;
import android.common.lib.wrap.logger.L;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ctrip.basebiz.cloudmusic.contract.IPresenter;


/**
 * Created by zhoujian on 2017/2/14.
 */

public abstract class BasePresenterFragment<T extends IPresenter> extends BaseFragment {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("enter fragment onCreate method; name = " + getClass().getSimpleName());
        mPresenter = createPresenter();
    }

    protected abstract T createPresenter();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        L.d("enter setUserVisibleHint method, class name = " + this.getClass().getName() + ", isVisibleToUser = " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.d("enter onHiddenChanged method, class name = " + this.getClass().getName() + ", hidden = " + hidden);
    }


}
