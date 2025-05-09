package com.huawei.openalliance.ad.ipc;

/* loaded from: classes5.dex */
public class CallResult<T> {
    private int code = -1;
    private T data;
    private String msg;

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public int getCode() {
        return this.code;
    }
}
