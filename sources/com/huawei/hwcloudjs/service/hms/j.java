package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.a;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
class j implements a.c {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WeakReference f6243a;
    final /* synthetic */ JsCallback b;
    final /* synthetic */ WeakReference c;
    final /* synthetic */ boolean d;
    final /* synthetic */ boolean e;
    final /* synthetic */ HmsCoreApi f;

    @Override // com.huawei.hwcloudjs.service.hms.a.c
    public void a(a.b bVar) {
        HmsCoreApi hmsCoreApi = this.f;
        WeakReference weakReference = this.f6243a;
        hmsCoreApi.a(bVar, (WeakReference<HuaweiApiClient>) weakReference, this.b, (WeakReference<Activity>) this.c, this.d, this.e);
    }

    j(HmsCoreApi hmsCoreApi, WeakReference weakReference, JsCallback jsCallback, WeakReference weakReference2, boolean z, boolean z2) {
        this.f = hmsCoreApi;
        this.f6243a = weakReference;
        this.b = jsCallback;
        this.c = weakReference2;
        this.d = z;
        this.e = z2;
    }
}
