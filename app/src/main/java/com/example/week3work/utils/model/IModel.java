package com.example.week3work.utils.model;



import com.example.week3work.utils.callback.MyCallBack;

import java.util.Map;


public interface IModel {
    void requestDataGet(String url, String params, Class clazz, MyCallBack myCallBack);
    void requestDataPost(String url, Map<String, String> params, Class clazz, MyCallBack myCallBack);
    void requestDataPut(String url, Map<String, String> params, Class clazz, MyCallBack myCallBack);
}
