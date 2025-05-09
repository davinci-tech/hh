package com.huawei.hianalytics;

import com.huawei.hianalytics.core.log.HiLog;

/* loaded from: classes4.dex */
public class e1 {

    /* renamed from: a, reason: collision with root package name */
    public a1 f3850a;

    /* renamed from: a, reason: collision with other field name */
    public String f32a;
    public a1 b;

    /* renamed from: b, reason: collision with other field name */
    public String f33b;
    public String c;
    public String d;
    public String e;

    public a1 a(String str) {
        if ("oper".equals(str)) {
            return this.b;
        }
        if ("maint".equals(str)) {
            return this.f3850a;
        }
        HiLog.sw("HAID", "wrong type");
        return null;
    }
}
