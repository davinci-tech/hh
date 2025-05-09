package com.huawei.hianalytics.visual;

import android.text.TextUtils;
import android.util.LruCache;

/* loaded from: classes4.dex */
public class v0 {
    public static v0 d;

    /* renamed from: a, reason: collision with root package name */
    public final LruCache<String, a> f3955a = new LruCache<>(128);
    public final LruCache<String, String> b = new LruCache<>(128);
    public final LruCache<String, String> c = new LruCache<>(128);

    public static class a {
    }

    public static v0 a() {
        v0 v0Var;
        synchronized (v0.class) {
            if (d == null) {
                d = new v0();
            }
            v0Var = d;
        }
        return v0Var;
    }

    public String a(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String valueOf = String.valueOf(cls.hashCode());
        String str = this.b.get(valueOf);
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String canonicalName = cls.getCanonicalName();
        if (TextUtils.isEmpty(canonicalName)) {
            canonicalName = "NA";
        }
        String str2 = canonicalName;
        this.b.put(valueOf, str2);
        return str2;
    }
}
