package com.example.week3work.utils.callback;

/**
 * @author Peng
 */
public interface MyCallBack<T> {
    void onSuccess(T data);
    void onFail(String error);
}
