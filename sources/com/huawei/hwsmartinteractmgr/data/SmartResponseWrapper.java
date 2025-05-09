package com.huawei.hwsmartinteractmgr.data;

/* loaded from: classes5.dex */
public class SmartResponseWrapper<T> {
    private int mContentType;
    private T mResponse;
    private int mResponseCode;
    private String mResponseDesc;

    public SmartResponseWrapper(int i, String str, int i2) {
        this.mResponseCode = i;
        this.mResponseDesc = str;
        this.mContentType = i2;
    }

    public T getResponse() {
        return this.mResponse;
    }

    public void setResponse(T t) {
        this.mResponse = t;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public void setResponseCode(int i) {
        this.mResponseCode = i;
    }

    public String getResponseDesc() {
        return this.mResponseDesc;
    }

    public void setResponseDesc(String str) {
        this.mResponseDesc = str;
    }

    public String toString() {
        return "SmartResponseWrapper{mResponseCode = " + this.mResponseCode + "mResponseDesc = " + this.mResponseDesc + "mContentType = " + this.mContentType + "}";
    }
}
