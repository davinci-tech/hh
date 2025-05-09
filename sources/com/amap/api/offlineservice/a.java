package com.amap.api.offlineservice;

import android.view.View;
import android.widget.RelativeLayout;
import com.amap.api.maps.offlinemap.OfflineMapActivity;

/* loaded from: classes8.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    protected OfflineMapActivity f1464a = null;

    public abstract void a(View view);

    public abstract void b();

    public boolean c() {
        return true;
    }

    public abstract RelativeLayout d();

    public abstract void e();

    public final void a(OfflineMapActivity offlineMapActivity) {
        this.f1464a = offlineMapActivity;
    }

    public final void a() {
        this.f1464a.showScr();
    }

    public final int a(float f) {
        return this.f1464a != null ? (int) ((f * (r0.getResources().getDisplayMetrics().densityDpi / 160.0f)) + 0.5f) : (int) f;
    }
}
