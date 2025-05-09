package com.huawei.appgallery.serviceverifykit.api;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import defpackage.ahb;
import defpackage.ahe;
import defpackage.ahi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ServiceVerifyKit {

    public static class Builder {
        private String e;
        private int k;
        private Context l;
        private Intent m;
        private String p;
        private ComponentType q;
        private String s;
        private String d = "AppGallery Verification";
        private String b = "Huawei CBG Cloud Security Signer";
        private String c = "com.huawei.appgallery.fingerprint_signature";

        /* renamed from: a, reason: collision with root package name */
        private String f1886a = "com.huawei.appgallery.sign_certchain";
        private Map<String, String[]> h = new HashMap();
        private Map<String, String> g = new HashMap();
        private Map<String, Integer> i = new HashMap();
        private List<String> f = new ArrayList();
        private List<c> j = new ArrayList();
        private int n = 0;
        private int o = 0;
        private String r = "verify_match_property";

        public enum ComponentType {
            ACTIVITY,
            SERVICE,
            BROADCAST
        }

        public Builder hN_(Intent intent, ComponentType componentType) {
            if (intent == null) {
                ahi.e.c("ServiceVerifyKit", "error input intent");
            } else {
                this.m = intent;
            }
            if (componentType == null) {
                ahi.e.c("ServiceVerifyKit", "error input type");
            } else {
                this.q = componentType;
            }
            return this;
        }

        public Builder a(List<String> list) {
            if (list.isEmpty()) {
                ahi.e.c("ServiceVerifyKit", "error input preferred package name");
            } else {
                this.f = list;
            }
            return this;
        }

        public Builder b(Context context) {
            this.l = context.getApplicationContext();
            return this;
        }

        public Builder e(String str) {
            if (TextUtils.isEmpty(str)) {
                ahi.e.c("ServiceVerifyKit", "error input signer key");
            } else {
                this.c = str;
            }
            return this;
        }

        public Builder a(String str) {
            if (TextUtils.isEmpty(str)) {
                ahi.e.c("ServiceVerifyKit", "error input chain key");
            } else {
                this.f1886a = str;
            }
            return this;
        }

        public String d() {
            ServiceVerifyKit serviceVerifyKit = new ServiceVerifyKit();
            com.huawei.appgallery.serviceverifykit.c.a aVar = new com.huawei.appgallery.serviceverifykit.c.a(this.l);
            this.g.put(this.c, this.f1886a);
            aVar.a(this.e, this.d, this.b, this.h, this.i, this.k, this.f, this.j, this.o, this.r, this.s, this.m, this.q, this.g, this.p);
            return serviceVerifyKit.c(aVar);
        }

        public Builder a(String str, String str2) {
            this.h.put(str, ServiceVerifyKit.d(this.h.get(str), str2));
            this.i.put(str, Integer.valueOf(this.n));
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] d(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        int length = strArr.length;
        for (String str2 : strArr) {
            if (TextUtils.equals(str2, str)) {
                return strArr;
            }
        }
        String[] strArr2 = new String[length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        strArr2[length] = str;
        return strArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(com.huawei.appgallery.serviceverifykit.c.a aVar) {
        List<ahb> d = aVar.d();
        if (d == null || d.isEmpty()) {
            return null;
        }
        return new ahe().c(d);
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f1888a;
        private String c;

        public String a() {
            return this.c;
        }

        public String b() {
            return this.f1888a;
        }
    }

    /* loaded from: classes8.dex */
    public static final class PkgVerifyBuilder {
        private Context b;

        /* renamed from: a, reason: collision with root package name */
        private String f1887a = "AppGallery Verification";
        private String e = "Huawei CBG Cloud Security Signer";
        private String c = "com.huawei.appgallery.fingerprint_signature";
        private String d = "com.huawei.appgallery.sign_certchain";
        private final Map<String, String[]> j = new HashMap();

        public PkgVerifyBuilder(Context context) {
            this.b = context;
        }
    }

    private ServiceVerifyKit() {
    }
}
