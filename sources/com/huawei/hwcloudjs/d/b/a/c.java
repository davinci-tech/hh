package com.huawei.hwcloudjs.d.b.a;

import java.util.Map;

/* loaded from: classes9.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private int f6211a;
    private int b = -1;

    public abstract void a(String str, Map<String, String> map);

    public int c() {
        return this.f6211a;
    }

    public void b(int i) {
        this.f6211a = i;
    }

    public int b() {
        return this.b;
    }

    public String[] a() {
        return new String[0];
    }

    public void a(int i) {
        this.b = i;
    }
}
