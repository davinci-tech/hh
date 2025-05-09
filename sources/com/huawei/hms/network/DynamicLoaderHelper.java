package com.huawei.hms.network;

/* loaded from: classes4.dex */
public class DynamicLoaderHelper {
    private static final DynamicLoaderHelper b = new DynamicLoaderHelper();

    /* renamed from: a, reason: collision with root package name */
    private boolean f5105a = false;

    public void setDynamicStatus(boolean z) {
        synchronized (this) {
            this.f5105a = z;
        }
    }

    public boolean getDynamicStatus() {
        boolean z;
        synchronized (this) {
            z = this.f5105a;
        }
        return z;
    }

    public static DynamicLoaderHelper getInstance() {
        return b;
    }

    private DynamicLoaderHelper() {
    }
}
