package com.example.week3work.utils.presenter;

import com.example.week3work.utils.callback.MyCallBack;
import com.example.week3work.utils.model.IModelImpl;
import com.example.week3work.utils.view.IView;

import java.util.Map;

public class IPresenterImpl implements IPresenter {

    private IModelImpl model;
    private IView iView;
    public IPresenterImpl(IView iView) {
        this.iView = iView;
        model = new IModelImpl();
    }
    @Override
    public void startRequestGet(String url, String params, Class clazz) {
        model.requestDataGet(url, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.getDataSuccess(data);
            }

            @Override
            public void onFail(String error) {
                iView.getDataFail(error);
            }
        });
    }

    @Override
    public void startRequestPost(String url, Map<String, String> params, Class clazz) {
        model.requestDataPost(url, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.getDataSuccess(data);
            }

            @Override
            public void onFail(String error) {
                iView.getDataFail(error);
            }
        });
    }

    @Override
    public void startRequestPut(String url, Map<String, String> params, Class clazz) {
        model.requestDataPut(url, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.getDataSuccess(data);
            }

            @Override
            public void onFail(String error) {
                iView.getDataFail(error);
            }
        });
    }

    /**
     * 解绑，解决内存泄漏
     */
    public void onDetach() {
        if (model != null) {
            model = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
