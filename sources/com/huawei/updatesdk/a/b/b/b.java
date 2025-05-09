package com.huawei.updatesdk.a.b.b;

import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes7.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private Intent f10814a;

    public boolean d() {
        return this.f10814a != null;
    }

    public Intent c() {
        return this.f10814a;
    }

    public Bundle b() {
        if (d()) {
            return this.f10814a.getExtras();
        }
        return null;
    }

    public long b(String str, int i) {
        if (d()) {
            try {
                return this.f10814a.getLongExtra(str, i);
            } catch (Throwable unused) {
                com.huawei.updatesdk.a.a.a.a("SecureIntent", "getIntExtra exception!");
            }
        }
        return i;
    }

    public boolean a(String str, boolean z) {
        if (d()) {
            try {
                return this.f10814a.getBooleanExtra(str, z);
            } catch (Throwable unused) {
                com.huawei.updatesdk.a.a.a.a("SecureIntent", "getBooleanExtra exception!");
            }
        }
        return z;
    }

    public String a(String str) {
        if (!d()) {
            return "";
        }
        try {
            return this.f10814a.getStringExtra(str);
        } catch (Throwable unused) {
            com.huawei.updatesdk.a.a.a.a("SecureIntent", "getStringExtra exception!");
            return "";
        }
    }

    public String a() {
        String action;
        return (!d() || (action = this.f10814a.getAction()) == null) ? "" : action;
    }

    public int a(String str, int i) {
        if (d()) {
            try {
                return this.f10814a.getIntExtra(str, i);
            } catch (Throwable unused) {
                com.huawei.updatesdk.a.a.a.a("SecureIntent", "getIntExtra exception!");
            }
        }
        return i;
    }

    public static b a(Intent intent) {
        return new b(intent);
    }

    private b(Intent intent) {
        this.f10814a = intent;
    }
}
