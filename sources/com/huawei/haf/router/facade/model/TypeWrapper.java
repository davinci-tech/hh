package com.huawei.haf.router.facade.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class TypeWrapper<T> extends TypeWrapperBase<T> {
    private final Type mType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public Type getType() {
        return this.mType;
    }
}
