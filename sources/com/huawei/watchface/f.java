package com.huawei.watchface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<String, String> f11027a = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();

    public void a(String str, String str2) {
        dg.c(this.f11027a, str, str2);
        dg.c(this.b, str2, str);
    }

    public void a() {
        ConcurrentHashMap<String, String> concurrentHashMap = this.f11027a;
        if (concurrentHashMap == null || this.b == null) {
            return;
        }
        concurrentHashMap.clear();
        this.b.clear();
    }

    public void a(String str) {
        dg.b(this.b, dg.a((Map<String, String>) this.f11027a, str));
        dg.b(this.f11027a, str);
    }

    public void a(String str, String str2, String str3) {
        dg.c(this.f11027a, str, str2);
        dg.c(this.b, str2, str3);
    }

    public boolean b(String str) {
        return dg.a((Map<?, ?>) this.f11027a, (Object) str);
    }

    public boolean c(String str) {
        return dg.a((Map<?, ?>) this.b, (Object) str);
    }

    public String d(String str) {
        return dg.a((Map<String, String>) this.f11027a, str);
    }

    public String e(String str) {
        return dg.a((Map<String, String>) this.b, str);
    }
}
