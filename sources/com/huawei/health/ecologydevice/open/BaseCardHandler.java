package com.huawei.health.ecologydevice.open;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.Fragment;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public abstract class BaseCardHandler<T> extends Handler {
    private WeakReference<T> mWeakReference;

    protected abstract void handleMessageWhenReferenceNotNull(T t, Message message);

    public BaseCardHandler(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        T t = this.mWeakReference.get();
        if (t == null) {
            removeCallbacksAndMessages(null);
            return;
        }
        boolean z = t instanceof Activity;
        if (z && ((Activity) t).isFinishing()) {
            removeCallbacksAndMessages(null);
            return;
        }
        if (z && ((Activity) t).isDestroyed()) {
            removeCallbacksAndMessages(null);
        } else if ((t instanceof Fragment) && !((Fragment) t).isAdded()) {
            removeCallbacksAndMessages(null);
        } else {
            handleMessageWhenReferenceNotNull(t, message);
        }
    }
}
