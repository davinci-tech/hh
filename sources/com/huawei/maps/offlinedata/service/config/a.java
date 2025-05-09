package com.huawei.maps.offlinedata.service.config;

import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.maps.offlinedata.service.cloud.d;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6472a;
    private final Set<String> b = new HashSet();
    private String c;
    private String d;
    private String[] e;

    private a() {
    }

    public static a a() {
        if (f6472a == null) {
            synchronized (a.class) {
                if (f6472a == null) {
                    f6472a = new a();
                }
            }
        }
        return f6472a;
    }

    public String[] b() {
        return this.e;
    }

    public void a(String[] strArr) {
        this.e = strArr;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public void b(String str) {
        this.b.add(str);
    }

    public void d() {
        this.b.clear();
    }

    public String e() {
        return com.huawei.maps.offlinedata.service.cloud.utils.a.a(com.huawei.maps.offlinedata.utils.a.a(), AgConnectInfo.AgConnectKey.API_KEY);
    }

    public String f() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String g() {
        return com.huawei.maps.offlinedata.service.cloud.a.a().c();
    }

    public Set<String> h() {
        return this.b;
    }

    public String i() {
        return d.a().b();
    }
}
