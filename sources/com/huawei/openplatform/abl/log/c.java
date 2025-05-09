package com.huawei.openplatform.abl.log;

import android.content.Context;
import com.huawei.openplatform.abl.log.util.g;

/* loaded from: classes5.dex */
public class c extends b {
    @Override // com.huawei.openplatform.abl.log.b
    public boolean d() {
        return this.b.d(5);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void d(String str, String str2, Object... objArr) {
        if (!d() || str2 == null) {
            return;
        }
        d(str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void d(String str, String str2) {
        this.b.e(5, str, str2);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public boolean c() {
        return this.b.d(4);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void c(String str, String str2, Object... objArr) {
        if (!c() || str2 == null) {
            return;
        }
        c(str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void c(String str, String str2) {
        this.b.e(4, str, str2);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public boolean b() {
        return this.b.d(6);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void b(String str, String str2, Object... objArr) {
        if (!b() || str2 == null) {
            return;
        }
        b(str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void b(String str, String str2) {
        this.b.e(6, str, str2);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public boolean a() {
        return this.b.d(3);
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void a(String str, String str2, Object... objArr) {
        if (!a() || str2 == null) {
            return;
        }
        a(str, g.a(str2, objArr));
    }

    @Override // com.huawei.openplatform.abl.log.b
    public void a(String str, String str2) {
        this.b.e(3, str, str2);
    }

    public c(Context context) {
        this.f8221a = context;
    }
}
