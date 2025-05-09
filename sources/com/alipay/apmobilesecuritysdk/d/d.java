package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import defpackage.C0337if;
import defpackage.ie;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class d {
    public static Map<String, String> a(Context context) {
        HashMap hashMap;
        synchronized (d.class) {
            C0337if.c();
            ie.e(APSecuritySdk.getInstance(context));
            hashMap = new HashMap();
            hashMap.put("AE1", C0337if.e());
            StringBuilder sb = new StringBuilder();
            sb.append(C0337if.d() ? "1" : "0");
            hashMap.put("AE2", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(C0337if.a() ? "1" : "0");
            hashMap.put("AE3", sb2.toString());
            hashMap.put("AE4", C0337if.b());
            hashMap.put("AE5", C0337if.h());
            hashMap.put("AE6", C0337if.f());
            hashMap.put("AE7", C0337if.i());
            hashMap.put("AE8", C0337if.j());
            hashMap.put("AE9", C0337if.g());
            hashMap.put("AE10", C0337if.o());
            hashMap.put("AE11", C0337if.k());
            hashMap.put("AE12", C0337if.n());
            hashMap.put("AE13", C0337if.l());
            hashMap.put("AE14", C0337if.m());
            hashMap.put("AE15", C0337if.p());
            hashMap.put("AE21", ie.a());
        }
        return hashMap;
    }

    public static Map<String, String> a() {
        HashMap hashMap;
        synchronized (d.class) {
            hashMap = new HashMap();
            try {
                new com.alipay.apmobilesecuritysdk.c.b();
                hashMap.put("AE16", "");
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }
}
