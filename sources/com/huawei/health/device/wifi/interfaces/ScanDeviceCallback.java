package com.huawei.health.device.wifi.interfaces;

import defpackage.ctn;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class ScanDeviceCallback<T> implements ScanDeviceCallbackInterface {
    private WeakReference<T> mWeakReference;

    public abstract void onDeviceDiscovered(T t, List<ctn> list);

    public abstract void onDeviceDiscoveryFinished(T t);

    public abstract void onFailure(T t, Object obj);

    public ScanDeviceCallback(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallbackInterface
    public void onDeviceDiscovered(List<ctn> list) {
        WeakReference<T> weakReference = this.mWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        onDeviceDiscovered(this.mWeakReference.get(), list);
    }

    @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallbackInterface
    public void onDeviceDiscoveryFinished() {
        WeakReference<T> weakReference = this.mWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        onDeviceDiscoveryFinished(this.mWeakReference.get());
    }

    @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallbackInterface
    public void onFailure(Object obj) {
        WeakReference<T> weakReference = this.mWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        onFailure(this.mWeakReference.get(), obj);
    }
}
