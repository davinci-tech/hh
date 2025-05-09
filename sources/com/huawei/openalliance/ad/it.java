package com.huawei.openalliance.ad;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class it {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, String> f7083a = new HashMap();

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.f7083a.put(b(str), str2);
    }

    public String a(String str) {
        if (str != null) {
            return this.f7083a.get(b(str));
        }
        return null;
    }

    public Iterable<? extends String> a() {
        return this.f7083a.keySet();
    }

    private String b(String str) {
        return str.toLowerCase(Locale.ENGLISH);
    }
}
