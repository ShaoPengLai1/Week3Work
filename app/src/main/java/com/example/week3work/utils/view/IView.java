package com.example.week3work.utils.view;

/**
 * @author Peng
 */
public interface IView<T> {
    void getDataSuccess(T data);
    void getDataFail(String error);
}
