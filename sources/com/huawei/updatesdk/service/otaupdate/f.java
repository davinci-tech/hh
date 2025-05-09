package com.huawei.updatesdk.service.otaupdate;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class f {
    private static f d = new f();

    /* renamed from: a, reason: collision with root package name */
    private String f10877a;
    private String b;
    private String c;

    public boolean d() {
        String str = this.f10877a;
        if (str != null) {
            return str.equals(this.b);
        }
        return true;
    }

    public void c(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.f10877a = str;
    }

    public String b() {
        return this.f10877a;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        return !TextUtils.isEmpty(this.f10877a) ? this.f10877a : this.b;
    }

    public static f e() {
        return d;
    }

    private f() {
    }
}
