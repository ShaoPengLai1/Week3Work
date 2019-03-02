package com.example.week3work.utils.presenter;

import java.util.Map;

public interface IPresenter {
    void startRequestGet(String url, String params, Class clazz);
    void startRequestPost(String url, Map<String, String> params, Class clazz);
    void startRequestPut(String url, Map<String, String> params, Class clazz);
}
