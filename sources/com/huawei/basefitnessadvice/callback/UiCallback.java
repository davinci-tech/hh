package com.huawei.basefitnessadvice.callback;

import android.os.Handler;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public abstract class UiCallback<T> {
    private static final String TAG = "UiCallback";

    public boolean isCanceled() {
        return false;
    }

    public abstract void onFailure(int i, String str);

    public void onFailure(int i, String str, Object obj) {
    }

    public void onProgress(long j, long j2) {
    }

    public abstract void onSuccess(T t);

    public void onFailure(Handler handler, final int i, final String str) {
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.huawei.basefitnessadvice.callback.UiCallback.5
                @Override // java.lang.Runnable
                public void run() {
                    UiCallback.this.onFailure(i, str);
                }
            });
        } else {
            LogUtil.h(TAG, "onFailure handler == null");
        }
    }

    public void onFailure(Handler handler, final int i, final String str, final Object obj) {
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.huawei.basefitnessadvice.callback.UiCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    UiCallback.this.onFailure(i, str, obj);
                }
            });
        } else {
            LogUtil.h(TAG, "onFailure handler == null");
        }
    }

    public void onSuccess(Handler handler, final T t) {
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.huawei.basefitnessadvice.callback.UiCallback.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    UiCallback.this.onSuccess(t);
                }
            });
        } else {
            LogUtil.h(TAG, "onSuccess handler == null");
        }
    }

    public void onProgress(Handler handler, final long j, final long j2) {
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.huawei.basefitnessadvice.callback.UiCallback.4
                @Override // java.lang.Runnable
                public void run() {
                    UiCallback.this.onProgress(j, j2);
                }
            });
        } else {
            LogUtil.h(TAG, "onProgress handler == null");
        }
    }
}
