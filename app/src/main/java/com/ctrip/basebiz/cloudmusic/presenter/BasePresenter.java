package com.ctrip.basebiz.cloudmusic.presenter;


import com.ctrip.basebiz.cloudmusic.contract.IView;

/**
 * Created by zhoujian on 2017/1/18.
 */

public class BasePresenter<T extends IView> {
    protected T mView;

    public BasePresenter(T view) {
        this.mView = view;
    }
}
