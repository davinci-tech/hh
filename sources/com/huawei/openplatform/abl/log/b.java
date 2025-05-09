package com.huawei.openplatform.abl.log;

import android.content.Context;
import android.os.Build;
import com.huawei.openplatform.abl.log.util.d;
import com.huawei.openplatform.abl.log.util.f;
import defpackage.lsq;
import defpackage.lsv;
import defpackage.lsx;
import defpackage.lsz;

/* loaded from: classes5.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    Context f8221a;
    lsx b = new lsx();

    abstract void a(String str, String str2);

    abstract void a(String str, String str2, Object... objArr);

    abstract boolean a();

    abstract void b(String str, String str2);

    abstract void b(String str, String str2, Object... objArr);

    abstract boolean b();

    abstract void c(String str, String str2);

    abstract void c(String str, String str2, Object... objArr);

    abstract boolean c();

    abstract void d(String str, String str2);

    abstract void d(String str, String str2, Object... objArr);

    abstract boolean d();

    protected void b(lsq lsqVar) {
        if (lsqVar == null) {
            return;
        }
        if ((lsqVar.h() || lsqVar.f()) && d.a("com.huawei.hms.support.log.KitLog")) {
            lsv.e().d(this.f8221a, lsqVar.e(), lsqVar.c());
        }
    }

    public void a(lsq lsqVar) {
        if (lsqVar == null) {
            return;
        }
        b(lsqVar);
        f.a(this.f8221a);
        this.b.d(lsqVar);
        this.b.e(lsqVar.c(), "\n============================================================================\n====== " + lsqVar.a() + "\n====== Brand: " + Build.BRAND + " Model: " + lsz.d() + " Release: " + Build.VERSION.RELEASE + " API: " + Build.VERSION.SDK_INT + "\n============================================================================");
    }

    public void a(int i, Throwable th) {
        this.b.c(i, "", th);
    }

    public void a(int i, String str, Throwable th) {
        this.b.c(i, str, th);
    }

    public void a(int i, String str, String str2, Throwable th) {
        this.b.e(i, str, str2, th);
    }
}
