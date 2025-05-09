package com.huawei.agconnect.config.impl;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private final String f1706a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final int f;
    private int g;

    public int h() {
        return this.g;
    }

    public int g() {
        return this.f;
    }

    public String f() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public String d() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public String b() {
        return this.f1706a;
    }

    public boolean a() {
        return (TextUtils.isEmpty(this.f1706a) || TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d) || this.f1706a.length() != this.b.length() || this.b.length() != this.c.length() || this.c.length() != this.g * 2 || this.f < 0 || TextUtils.isEmpty(this.e)) ? false : true;
    }

    public d(String str, String str2, String str3, String str4, String str5, int i) {
        this.g = 0;
        this.f1706a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = i;
        if (str != null) {
            this.g = str.length() / 2;
        }
    }
}
