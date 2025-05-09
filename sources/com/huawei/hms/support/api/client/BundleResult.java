package com.huawei.hms.support.api.client;

import android.os.Bundle;

/* loaded from: classes9.dex */
public class BundleResult {

    /* renamed from: a, reason: collision with root package name */
    private int f5955a;
    private Bundle b;

    public BundleResult(int i, Bundle bundle) {
        this.f5955a = i;
        this.b = bundle;
    }

    public int getResultCode() {
        return this.f5955a;
    }

    public Bundle getRspBody() {
        return this.b;
    }

    public void setResultCode(int i) {
        this.f5955a = i;
    }

    public void setRspBody(Bundle bundle) {
        this.b = bundle;
    }
}
