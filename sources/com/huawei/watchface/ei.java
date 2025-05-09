package com.huawei.watchface;

import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class ei extends BaseEvent<ei> {

    /* renamed from: a, reason: collision with root package name */
    private LinkedHashMap<String, String> f11008a;
    private String h;

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return e();
    }

    private LinkedHashMap<String, String> e() {
        if (this.f11008a == null) {
            this.f11008a = new LinkedHashMap<>();
        }
        this.f11008a.put("startts", String.valueOf(m()));
        this.f11008a.put("endts", String.valueOf(n()));
        this.f11008a.put("totalTime", String.valueOf(o()));
        this.f11008a.put("url", am.b(d()));
        return this.f11008a;
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

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_PAGE_START";
    }
}
