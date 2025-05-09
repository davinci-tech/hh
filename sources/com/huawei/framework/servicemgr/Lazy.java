package com.huawei.framework.servicemgr;

import android.content.Context;

/* loaded from: classes.dex */
public interface Lazy<T> {
    T get() throws IllegalStateException;

    boolean isPresent();

    void load(Context context, Consumer<T> consumer, boolean z) throws IllegalStateException;

    default void load(Context context, boolean z) throws IllegalStateException {
        load(context, null, z);
    }
}
