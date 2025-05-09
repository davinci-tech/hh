package com.huawei.hms.scankit.p;

import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
final class y2 {

    /* renamed from: a, reason: collision with root package name */
    private final String f5925a;
    private e7 b;
    private l2 c;
    private l2 d;
    private final StringBuilder e;
    int f;
    private int g;
    private d7 h;
    private int i;

    y2(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            char c = (char) (bytes[i] & 255);
            if (c == '?' && str.charAt(i) != '?') {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            sb.append(c);
        }
        this.f5925a = sb.toString();
        this.b = e7.FORCE_NONE;
        this.e = new StringBuilder(str.length());
        this.g = -1;
    }

    private int h() {
        return this.f5925a.length() - this.i;
    }

    public void a(e7 e7Var) {
        this.b = e7Var;
    }

    public StringBuilder b() {
        return this.e;
    }

    public char c() {
        return this.f5925a.charAt(this.f);
    }

    public String d() {
        return this.f5925a;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return h() - this.f;
    }

    public d7 g() {
        return this.h;
    }

    public boolean i() {
        return this.f < h();
    }

    public void j() {
        this.g = -1;
    }

    public void k() {
        this.h = null;
    }

    public void l() {
        c(a());
    }

    public void a(l2 l2Var, l2 l2Var2) {
        this.c = l2Var;
        this.d = l2Var2;
    }

    public void b(int i) {
        this.g = i;
    }

    public void c(int i) {
        d7 d7Var = this.h;
        if (d7Var == null || i > d7Var.a()) {
            this.h = d7.a(i, this.b, this.c, this.d, true);
        }
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(String str) {
        this.e.append(str);
    }

    public void a(char c) {
        this.e.append(c);
    }

    public int a() {
        return this.e.length();
    }
}
