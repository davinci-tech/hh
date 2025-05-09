package com.huawei.phoneservice.faq.base.util;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class FaqHandler extends Handler {
    private WeakReference<CallBack> b;

    @FunctionalInterface
    public interface CallBack {
        void handleMessage(int i, Message message);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        CallBack callBack;
        super.handleMessage(message);
        WeakReference<CallBack> weakReference = this.b;
        if (weakReference == null || (callBack = weakReference.get()) == null) {
            return;
        }
        callBack.handleMessage(message.what, message);
    }

    public FaqHandler(CallBack callBack) {
        if (callBack != null) {
            this.b = new WeakReference<>(callBack);
        }
    }
}
