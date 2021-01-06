package com.binzee.server.module;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

/**
 * 返回结果
 *
 * @author tong.xw
 * 2021/01/04 18:38
 */
public class ResultBean<T> {
    private final Gson gson = new Gson();
    private int resultCode; // 0:OK, other:Error
    private String errorMsg;    //错误信息
    private T data; //数据

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJson() {
        return toString();
    }

    @NonNull
    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
