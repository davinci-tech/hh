package com.huawei.hianalytics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    public static i f3875a;

    /* renamed from: a, reason: collision with other field name */
    public static Map<String, e1> f40a = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with other field name */
    public l f41a = new l();

    public static i a() {
        if (f3875a == null) {
            synchronized (i.class) {
                if (f3875a == null) {
                    f3875a = new i();
                }
            }
        }
        return f3875a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public l m550a() {
        if (this.f41a == null) {
            this.f41a = new l();
        }
        return this.f41a;
    }

    public e1 a(String str) {
        return f40a.get(str);
    }
}
