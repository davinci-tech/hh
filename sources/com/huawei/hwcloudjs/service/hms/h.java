package com.huawei.hwcloudjs.service.hms;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hwcloudjs.core.JsCallback;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
class h implements HuaweiApiClient.ConnectionCallbacks {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WeakReference f6241a;
    final /* synthetic */ JsCallback b;
    final /* synthetic */ WeakReference c;
    final /* synthetic */ boolean d;
    final /* synthetic */ boolean e;
    final /* synthetic */ HmsCoreApi f;

    @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
    }

    @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
    public void onConnected() {
        this.f.a(this.f6241a, this.b, this.c, this.d, this.e);
    }

    h(HmsCoreApi hmsCoreApi, WeakReference weakReference, JsCallback jsCallback, WeakReference weakReference2, boolean z, boolean z2) {
        this.f = hmsCoreApi;
        this.f6241a = weakReference;
        this.b = jsCallback;
        this.c = weakReference2;
        this.d = z;
        this.e = z2;
    }
}
