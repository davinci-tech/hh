package com.tencent.open.b;

import com.tencent.open.utils.m;

/* loaded from: classes7.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    protected static e f11341a;

    public void a(int i, String str, String str2, String str3, String str4, Long l, int i2, int i3, String str5) {
    }

    protected e() {
    }

    public static e a() {
        e eVar;
        synchronized (e.class) {
            if (f11341a == null) {
                f11341a = new e();
            }
            eVar = f11341a;
        }
        return eVar;
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        h.a().a(m.a(str, str3, str4, str5, str2, str6), str2, true);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        h.a().a(m.a(str, str4, str5, str3, str2, str6, "", str7, str8, "", "", ""), str2, false);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        h.a().a(m.a(str, str4, str5, str3, str2, str6, str7, "", "", str8, str9, str10), str2, false);
    }
}
