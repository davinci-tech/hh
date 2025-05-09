package com.huawei.health.h5pro.scalable;

import com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService;

/* loaded from: classes3.dex */
public class H5ProScalableServiceManager {

    /* renamed from: a, reason: collision with root package name */
    public static H5ProScalableServiceManager f2448a;
    public IH5ProAppLoadStorageService b;

    public void registerService(Object obj) {
        if (obj instanceof IH5ProAppLoadStorageService) {
            this.b = (IH5ProAppLoadStorageService) obj;
        }
    }

    public IH5ProAppLoadStorageService getH5ProAppLoadStorageService() {
        return this.b;
    }

    public static H5ProScalableServiceManager getInstance() {
        if (f2448a == null) {
            synchronized (H5ProScalableServiceManager.class) {
                if (f2448a == null) {
                    f2448a = new H5ProScalableServiceManager();
                }
            }
        }
        return f2448a;
    }
}
