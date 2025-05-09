package com.huawei.health.device.wifi.interfaces;

import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public abstract class BaseCallback<T> implements BaseCallbackInterface {
    private static final String TAG = "BaseCallback";
    WeakReference<T> mWeakReference;

    public abstract void onFailure(T t, int i);

    public abstract void onStatus(T t, int i);

    public abstract void onSuccess(T t, Object obj);

    public BaseCallback(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
    public void onSuccess(Object obj) {
        T t = this.mWeakReference.get();
        if (t == null) {
            LogUtil.h(TAG, " onSuccess: return obj == null");
        } else {
            onSuccess(t, obj);
        }
    }

    @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
    public void onFailure(int i) {
        T t = this.mWeakReference.get();
        if (t == null) {
            LogUtil.h(TAG, " onFailure: return obj == null");
        } else {
            onFailure(t, i);
        }
    }

    @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
    public void onStatus(int i) {
        T t = this.mWeakReference.get();
        if (t == null) {
            LogUtil.h(TAG, " onStatus: return obj == null");
        } else {
            onStatus(t, i);
        }
    }
}
