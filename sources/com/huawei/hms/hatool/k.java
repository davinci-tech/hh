package com.huawei.hms.hatool;

import android.util.Pair;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class k extends u0 {
    public static Map<String, String> b(String str, String str2, String str3) {
        Map<String, String> c = u0.c(str, str3);
        Map<String, String> i = a1.i(str, str2);
        if (i == null) {
            return c;
        }
        c.putAll(i);
        return c;
    }

    protected static y0 a(String str, String str2, String str3) {
        y0 a2 = u0.a(str, str2, str3);
        Pair<String, String> e = j.a().e(str2, str);
        a2.f((String) e.first);
        a2.g((String) e.second);
        a2.h(o.b());
        a2.d(j.a().d(str2, str));
        return a2;
    }

    protected static l a(String str, String str2) {
        l a2 = u0.a(str, str2);
        i c = j.a().c(str, str2);
        a2.g(j.a().a(a1.c(str, str2)));
        a2.f(a1.o(str, str2));
        a2.c(j.a().f(str, str2));
        int i = a.f4597a[c.a().ordinal()];
        if (i == 1) {
            a2.d(c.b());
        } else if (i == 2) {
            a2.b(c.b());
        } else if (i == 3) {
            a2.e(c.b());
        }
        return a2;
    }

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4597a;

        static {
            int[] iArr = new int[d0.values().length];
            f4597a = iArr;
            try {
                iArr[d0.SN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4597a[d0.IMEI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4597a[d0.UDID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static h1 a(List<b1> list, String str, String str2, String str3, String str4) {
        h1 b = u0.b(str, str2);
        if (b == null) {
            return null;
        }
        b.a(a(m1.d().a(), str, str2, str3));
        b.a(a(str, str2));
        b.a(a(str2, str, str4));
        b.a(a1.g(str, str2));
        b.a(list);
        return b;
    }

    protected static f0 a(String str, String str2, String str3, String str4) {
        f0 a2 = u0.a(str, str2, str3, str4);
        String a3 = j.a().a(a1.c(str2, str3));
        long currentTimeMillis = System.currentTimeMillis();
        String sha256Encrypt = SHA.sha256Encrypt(q0.f() + a3 + currentTimeMillis);
        a2.f(String.valueOf(currentTimeMillis));
        a2.g(sha256Encrypt);
        return a2;
    }
}
