package com.huawei.hihealthservice.hihealthkit.cpcheck;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public abstract class OnRequestCallBack<T> {
    public static final int DEFAULT_ERR_CODE = -1;
    private Type genericType;

    public abstract void onFail(int i, Throwable th);

    public abstract void onSuccess(T t);

    public OnRequestCallBack() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.genericType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            this.genericType = Object.class;
        }
    }

    public Type getGenericType() {
        return this.genericType;
    }
}
