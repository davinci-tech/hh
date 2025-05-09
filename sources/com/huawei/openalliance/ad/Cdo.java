package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* renamed from: com.huawei.openalliance.ad.do, reason: invalid class name */
/* loaded from: classes5.dex */
public abstract class Cdo {

    /* renamed from: a, reason: collision with root package name */
    private Context f6709a;
    private a b;

    /* renamed from: com.huawei.openalliance.ad.do$a */
    public interface a {
        void a(AppInfo appInfo);

        void b(AppInfo appInfo);

        void c(AppInfo appInfo);
    }

    public abstract void a(AppInfo appInfo, ContentRecord contentRecord, long j);

    protected void c(AppInfo appInfo) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.c(appInfo);
        }
    }

    protected void b(AppInfo appInfo) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.b(appInfo);
        }
    }

    protected void a(AppInfo appInfo) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(appInfo);
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public Context a() {
        return this.f6709a;
    }

    public Cdo(Context context) {
        this.f6709a = context;
    }
}
