package com.huawei.haf.handler;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public abstract class BaseHandlerCallback<T> implements Handler.Callback {
    private ExtendHandler mExtendHandler;
    private final WeakReference<T> mWeakReference;

    protected abstract boolean handleMessageWhenReferenceNotNull(T t, Message message);

    public BaseHandlerCallback(T t) {
        BaseHandler.checkStaticInnerClass(getClass(), false);
        this.mWeakReference = new WeakReference<>(t);
    }

    void setExtentHandler(ExtendHandler extendHandler) {
        this.mExtendHandler = extendHandler;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        T t = this.mWeakReference.get();
        if (!BaseHandler.needHandleMessage(t, message)) {
            ExtendHandler extendHandler = this.mExtendHandler;
            if (extendHandler == null) {
                return true;
            }
            extendHandler.removeTasksAndMessages();
            return true;
        }
        return handleMessageWhenReferenceNotNull(t, message);
    }
}
