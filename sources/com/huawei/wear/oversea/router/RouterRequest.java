package com.huawei.wear.oversea.router;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class RouterRequest {
    private Map<String, String> c;
    private Map<String, Object> d;
    private Context e;

    /* renamed from: a, reason: collision with root package name */
    private String f11212a = null;
    private String h = null;
    private String b = null;

    public RouterRequest(Context context) {
        this.d = null;
        this.c = null;
        this.e = context;
        this.d = new HashMap();
        this.c = new HashMap();
    }

    public Context e() {
        return this.e;
    }

    public String b() {
        return this.b;
    }

    public RouterRequest b(String str) {
        this.b = str;
        return this;
    }

    public Map<String, Object> c() {
        return this.d;
    }

    public String a() {
        return this.f11212a + "_" + this.h;
    }

    public RouterRequest e(String str) {
        this.f11212a = str;
        return this;
    }

    public RouterRequest d(String str) {
        this.h = str;
        return this;
    }

    public String d() {
        return a() + ":" + b();
    }
}
