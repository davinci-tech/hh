package com.huawei.hianalytics;

import java.util.Map;

/* loaded from: classes4.dex */
public class a1 {

    /* renamed from: a, reason: collision with root package name */
    public int f3833a;

    /* renamed from: a, reason: collision with other field name */
    public b1 f5a;

    /* renamed from: a, reason: collision with other field name */
    public Boolean f6a;

    /* renamed from: a, reason: collision with other field name */
    public String f7a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f8a;

    /* renamed from: a, reason: collision with other field name */
    public boolean f9a;
    public int b;

    /* renamed from: b, reason: collision with other field name */
    public Boolean f10b;

    /* renamed from: b, reason: collision with other field name */
    public String f11b;

    /* renamed from: b, reason: collision with other field name */
    public boolean f12b;
    public int c;

    /* renamed from: c, reason: collision with other field name */
    public Boolean f13c;

    /* renamed from: c, reason: collision with other field name */
    public String f14c;

    /* renamed from: c, reason: collision with other field name */
    public boolean f15c;
    public int d;

    /* renamed from: d, reason: collision with other field name */
    public String f16d;

    /* renamed from: d, reason: collision with other field name */
    public boolean f17d;
    public int e;

    /* renamed from: e, reason: collision with other field name */
    public String f18e;
    public int f;

    /* renamed from: f, reason: collision with other field name */
    public String f19f;
    public String g;
    public String h;
    public String i;

    public void h(String str) {
        this.f18e = str;
    }

    public void g(String str) {
        this.f19f = str;
    }

    public void f(String str) {
        this.f16d = str;
    }

    public void f(int i) {
        this.c = i;
    }

    public void e(String str) {
        this.h = str;
    }

    public void e(int i) {
        this.f3833a = i;
    }

    public void d(boolean z) {
        this.f15c = z;
    }

    public void d(String str) {
        this.i = str;
    }

    public void d(int i) {
        this.d = i;
    }

    public void c(boolean z) {
        this.f17d = z;
    }

    public void c(String str) {
        this.f14c = str;
    }

    public void c(Boolean bool) {
        this.f13c = bool;
    }

    public void c(int i) {
        this.f = i;
    }

    public int c() {
        return this.d;
    }

    public void b(boolean z) {
        this.f12b = z;
    }

    public void b(String str) {
        this.f11b = str;
    }

    public void b(Boolean bool) {
        this.f10b = bool;
    }

    public void b(int i) {
        this.e = i;
    }

    public int b() {
        return this.f;
    }

    public void a(boolean z) {
        this.f9a = z;
    }

    public void a(Map<String, String> map) {
        this.f8a = map;
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(Boolean bool) {
        this.f6a = bool;
    }

    public void a(int i) {
        this.b = i;
    }

    /* renamed from: a, reason: collision with other method in class */
    public b1 m544a() {
        b1 b1Var = this.f5a;
        return b1Var == null ? new b1() : b1Var;
    }

    public int a() {
        return this.e;
    }

    public a1(a1 a1Var) {
        this.f3833a = 30;
        this.b = 7;
        this.f15c = false;
        this.f5a = new b1();
        this.h = "";
        this.c = 1;
        this.f5a = a1Var.f5a;
        a(a1Var.f9a);
        b(a1Var.f11b);
        c(a1Var.f14c);
        f(a1Var.f16d);
        h(a1Var.f18e);
        e(a1Var.h);
        g(a1Var.f19f);
        b(a1Var.f12b);
        e(a1Var.f3833a);
        a(a1Var.b);
        d(a1Var.f15c);
        a(a1Var.f8a);
        a(a1Var.g);
        a(a1Var.f6a);
        b(a1Var.f10b);
        c(a1Var.f13c);
        f(a1Var.c);
        d(a1Var.i);
        d(a1Var.c());
        b(a1Var.a());
        c(a1Var.b());
        c(a1Var.f17d);
    }

    public a1() {
        this.f3833a = 30;
        this.b = 7;
        this.f15c = false;
        this.f5a = new b1();
        this.h = "";
        this.c = 1;
    }
}
