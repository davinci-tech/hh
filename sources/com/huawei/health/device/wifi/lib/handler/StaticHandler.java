package com.huawei.health.device.wifi.lib.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

/* loaded from: classes3.dex */
public abstract class StaticHandler<T> extends Handler {
    private static final int MSG_SEND_DELAT = 1000;
    private static final String TAG = "StaticHandler";
    private WeakReference<T> mReferent;

    public abstract void handleMessage(T t, Message message);

    public StaticHandler(T t) {
        this.mReferent = new WeakReference<>(t);
        checkStatic();
    }

    public StaticHandler(T t, Handler.Callback callback) {
        super(callback);
        if (t != null) {
            this.mReferent = new WeakReference<>(t);
        }
        checkStatic();
    }

    public StaticHandler(T t, Looper looper) {
        super(looper);
        if (t != null) {
            this.mReferent = new WeakReference<>(t);
        }
        checkStatic();
    }

    public StaticHandler(T t, Looper looper, Handler.Callback callback) {
        super(looper, callback);
        if (t != null) {
            this.mReferent = new WeakReference<>(t);
        }
        checkStatic();
    }

    private void checkStatic() {
        Class<?> cls = getClass();
        if (Modifier.isStatic(cls.getModifiers()) || cls.getName().indexOf(36) <= 0) {
            return;
        }
        LogUtil.h(TAG, "checkStatic : handler not static");
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        WeakReference<T> weakReference = this.mReferent;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        handleMessage(this.mReferent.get(), message);
    }
}
