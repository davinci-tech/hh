package com.huawei.health.device.wifi.interfaces;

import defpackage.cpw;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public abstract class CommBaseCallback<T> implements CommBaseCallbackInterface {
    private static final String TAG = "CommBaseCallbackInterface";
    private WeakReference<T> ref;

    public abstract void onResult(T t, int i, String str, Object obj);

    public CommBaseCallback(T t) {
        this.ref = new WeakReference<>(t);
    }

    @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface
    public void onResult(int i, String str, Object obj) {
        T t = this.ref.get();
        if (t == null) {
            cpw.e(false, TAG, "onResult: return var == null");
        } else {
            onResult(t, i, str, obj);
        }
    }
}
