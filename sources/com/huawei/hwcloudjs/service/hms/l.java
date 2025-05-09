package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hwcloudjs.service.hms.k;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HuaweiApiAvailability f6250a;
    final /* synthetic */ int b;
    final /* synthetic */ k.b c;

    @Override // java.lang.Runnable
    public void run() {
        WeakReference weakReference;
        weakReference = this.c.c;
        Activity activity = (Activity) weakReference.get();
        if (activity != null) {
            this.f6250a.resolveError(activity, this.b, 9004);
        }
    }

    l(k.b bVar, HuaweiApiAvailability huaweiApiAvailability, int i) {
        this.c = bVar;
        this.f6250a = huaweiApiAvailability;
        this.b = i;
    }
}
