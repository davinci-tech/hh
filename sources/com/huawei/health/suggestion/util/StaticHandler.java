package com.huawei.health.suggestion.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

/* loaded from: classes4.dex */
public abstract class StaticHandler<T> extends Handler {
    private WeakReference<T> mReference;

    public abstract void handleMessage(T t, Message message);

    public StaticHandler(T t) {
        this.mReference = new WeakReference<>(t);
        checkStatic();
    }

    public StaticHandler(T t, Looper looper) {
        super(looper);
        this.mReference = new WeakReference<>(t);
        checkStatic();
    }

    private void checkStatic() {
        Class<?> cls = getClass();
        if (!Modifier.isStatic(cls.getModifiers()) && cls.getName().indexOf(36) > 0) {
            throw new RuntimeException("handler not static");
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        super.handleMessage(message);
        handleMessage(this.mReference.get(), message);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }
}
