package com.huawei.watchface;

import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class ef extends BaseEvent<ef> {

    /* renamed from: a, reason: collision with root package name */
    private LinkedHashMap<String, String> f11005a;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return j();
    }

    private LinkedHashMap<String, String> j() {
        if (this.f11005a == null) {
            this.f11005a = new LinkedHashMap<>();
        }
        this.f11005a.put("startts", String.valueOf(m()));
        this.f11005a.put("endts", String.valueOf(n()));
        this.f11005a.put("totalTime", String.valueOf(o()));
        this.f11005a.put("errorCode", p());
        this.f11005a.put(BaseEvent.KEY_DESCINFO, d());
        this.f11005a.put("protocal", e());
        this.f11005a.put("url", f());
        this.f11005a.put("pagePosition", h());
        this.f11005a.put("pageName", i());
        return this.f11005a;
    }

    @Override // com.huawei.watchface.utils.analytice.data.BaseEvent
    public void a_() {
        super.a_();
    }

    public String d() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public String e() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public String f() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String g() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public String h() {
        return this.l;
    }

    public String i() {
        return this.m;
    }

    public void a(int i, String str) {
        a(str);
        d(String.valueOf(i));
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_LOAD_H5";
    }
}
