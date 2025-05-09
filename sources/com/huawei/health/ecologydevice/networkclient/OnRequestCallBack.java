package com.huawei.health.ecologydevice.networkclient;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public abstract class OnRequestCallBack<T> {
    public static final int DEFAULT_ERR_CODE = -1;
    private Type mGenericType;

    protected abstract void onFail(int i, Throwable th);

    public abstract void onSuccess(T t);

    public OnRequestCallBack() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.mGenericType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            this.mGenericType = Object.class;
        }
    }

    public Type getGenericType() {
        return this.mGenericType;
    }
}
