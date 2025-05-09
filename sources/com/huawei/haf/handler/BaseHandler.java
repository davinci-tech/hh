package com.huawei.haf.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.fragment.app.Fragment;
import com.huawei.haf.common.exception.HafRuntimeException;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
public abstract class BaseHandler<T> extends Handler {
    private static final String TAG = "HAF_BaseHandler";
    private final WeakReference<T> mWeakReference;

    protected abstract void handleMessageWhenReferenceNotNull(T t, Message message);

    public BaseHandler(Looper looper, T t) {
        super(looper);
        checkStaticInnerClass(getClass(), true);
        this.mWeakReference = new WeakReference<>(t);
    }

    public BaseHandler(T t) {
        checkStaticInnerClass(getClass(), true);
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        super.handleMessage(message);
        T t = this.mWeakReference.get();
        if (!needHandleMessage(t, message)) {
            removeCallbacksAndMessages(null);
        } else {
            handleMessageWhenReferenceNotNull(t, message);
        }
    }

    static void checkStaticInnerClass(Class<?> cls, boolean z) {
        if (Modifier.isStatic(cls.getModifiers()) || cls.getName().indexOf(36) <= 0) {
            return;
        }
        if (z) {
            LogUtil.a(TAG, "handler not static class '", cls.getName(), "'");
            return;
        }
        throw new HafRuntimeException("handler not static class '" + cls.getName() + "'");
    }

    static <T> boolean needHandleMessage(T t, Message message) {
        if (needHandleMessage(t)) {
            return true;
        }
        LogUtil.c(TAG, "handleMessage skip the message and clear all messages, msg.what=", Integer.valueOf(message.what));
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> boolean needHandleMessage(T t) {
        if (t == 0) {
            return false;
        }
        if (t instanceof Activity) {
            Activity activity = (Activity) t;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return false;
            }
        }
        return !(t instanceof Fragment) || ((Fragment) t).isAdded();
    }
}
