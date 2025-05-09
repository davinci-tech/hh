package com.alipay.apmobilesecuritysdk.b;

import defpackage.jd;
import defpackage.mq;

/* loaded from: classes7.dex */
public final class a {
    public static a b = new a();

    /* renamed from: a, reason: collision with root package name */
    public int f838a = 0;

    public final String c() {
        String str;
        String b2 = jd.b();
        if (mq.b(b2)) {
            return b2;
        }
        int i = this.f838a;
        if (i == 1) {
            str = "://mobilegw.stable.alipay.net/mgw.htm";
        } else {
            if (i == 2) {
                return "https://mobilegwpre.alipay.com/mgw.htm";
            }
            if (i == 3) {
                str = "://mobilegw-1-64.test.alipay.net/mgw.htm";
            } else {
                if (i != 4) {
                    return "https://mobilegw.alipay.com/mgw.htm";
                }
                str = "://mobilegw.aaa.alipay.net/mgw.htm";
            }
        }
        return a("http", str);
    }

    public final int b() {
        return this.f838a;
    }

    public final void a(int i) {
        this.f838a = i;
    }

    public static String a(String str, String str2) {
        return str + str2;
    }

    public static a a() {
        return b;
    }
}
