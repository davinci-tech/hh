package com.huawei.watchface.utils;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public abstract class SafeHandler<T> extends Handler {
    WeakReference<T> mObj;

    public abstract void handlerMessageAction(Message message);

    public SafeHandler(T t) {
        this.mObj = new WeakReference<>(t);
    }

    public T getObj() {
        return this.mObj.get();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (isObjectExist()) {
            handlerMessageAction(message);
        }
    }

    public boolean isObjectExist() {
        WeakReference<T> weakReference = this.mObj;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }
}
