package com.huawei.hms.network.embedded;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public abstract class m {

    /* renamed from: a, reason: collision with root package name */
    public l f5371a;
    public Map<String, Object> b;

    public abstract q b();

    public boolean d() {
        return this.b.isEmpty();
    }

    public l c() {
        return this.f5371a;
    }

    public void a(Map<String, Object> map) {
        this.b.putAll(map);
    }

    public void a(String str, Object obj) {
        if (str == null) {
            return;
        }
        this.b.put(str.toLowerCase(Locale.ENGLISH), obj);
    }

    public void a(l lVar) {
        this.f5371a = lVar;
    }

    public Map<String, Object> a() {
        return this.b;
    }

    public Object a(String str) {
        if (str == null) {
            return null;
        }
        return this.b.get(str);
    }

    public m(l lVar) {
        this.f5371a = lVar;
        this.b = new ConcurrentHashMap();
    }

    public m() {
        this(null);
    }
}
