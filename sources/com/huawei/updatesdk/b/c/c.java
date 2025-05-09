package com.huawei.updatesdk.b.c;

import android.os.Build;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes7.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private String f10830a;
    private String b;

    abstract String a();

    public abstract String b();

    abstract String c();

    public abstract List<String> f();

    public String e() {
        if (!TextUtils.isEmpty(this.f10830a)) {
            return this.f10830a;
        }
        String a2 = a();
        this.f10830a = a2;
        if (TextUtils.isEmpty(a2) || TextUtils.equals(this.f10830a, "unknown")) {
            this.f10830a = Build.MODEL;
        }
        return this.f10830a;
    }

    public String d() {
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        String c = c();
        this.b = c;
        if (TextUtils.isEmpty(c)) {
            this.b = Build.MANUFACTURER;
        }
        return this.b;
    }
}
