package com.huawei.openplatform.abl.log;

import android.content.Context;
import android.util.Log;
import com.huawei.openplatform.abl.log.util.g;
import defpackage.lsq;
import defpackage.lsv;

/* loaded from: classes5.dex */
public class l extends b {
    private boolean d;

    @Override // com.huawei.openplatform.abl.log.b
    public boolean d() {
        return (this.d && lsv.e().b(5)) || this.b.d(5);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void d(String str, String str2, Object... objArr) {
        if (!d() || str2 == null) {
            return;
        }
        lsv.e().c(str, str2, objArr);
        if (this.d) {
            return;
        }
        this.b.e(5, str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void d(String str, String str2) {
        StringBuilder sb;
        try {
            lsv.e().b(str, str2);
            if (this.d) {
                return;
            }
            this.b.e(5, str, str2);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("log w ");
            sb.append(e.getClass().getSimpleName());
            Log.w("TVLogger", sb.toString());
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("log w ex: ");
            sb.append(e.getClass().getSimpleName());
            Log.w("TVLogger", sb.toString());
        }
    }

    @Override // com.huawei.openplatform.abl.log.b
    public boolean c() {
        return (this.d && lsv.e().b(4)) || this.b.d(4);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void c(String str, String str2, Object... objArr) {
        if (!c() || str2 == null) {
            return;
        }
        lsv.e().e(str, str2, objArr);
        if (this.d) {
            return;
        }
        this.b.e(4, str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void c(String str, String str2) {
        lsv.e().a(str, str2);
        if (this.d) {
            return;
        }
        this.b.e(4, str, str2);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public boolean b() {
        return (this.d && lsv.e().b(6)) || this.b.d(6);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void b(String str, String str2, Object... objArr) {
        if (!b() || str2 == null) {
            return;
        }
        lsv.e().b(str, str2, objArr);
        if (this.d) {
            return;
        }
        this.b.e(6, str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void b(String str, String str2) {
        lsv.e().d(str, str2);
        if (this.d) {
            return;
        }
        this.b.e(6, str, str2);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public boolean a() {
        return (this.d && lsv.e().b(3)) || this.b.d(3);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void a(String str, String str2, Object... objArr) {
        if (!a() || str2 == null) {
            return;
        }
        lsv.e().a(str, str2, objArr);
        if (this.d) {
            return;
        }
        this.b.e(3, str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void a(String str, String str2) {
        lsv.e().c(str, str2);
        if (this.d) {
            return;
        }
        this.b.e(3, str, str2);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void a(lsq lsqVar) {
        if (this.d) {
            b(lsqVar);
        } else {
            super.a(lsqVar);
        }
    }

    public l b(boolean z) {
        this.d = z;
        return this;
    }

    public l(Context context) {
        this.f8221a = context;
    }
}
