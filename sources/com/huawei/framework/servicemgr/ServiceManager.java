package com.huawei.framework.servicemgr;

import defpackage.bwb;

/* loaded from: classes.dex */
public interface ServiceManager {
    <T> Lazy<T> get(String str, Class<T> cls, String str2);

    static ServiceManager getSingleton() {
        return bwb.a.f534a;
    }

    default <T> Lazy<T> get(String str, Class<T> cls) {
        return get(str, cls, null);
    }

    default <T> T getInstance(String str, Class<T> cls) {
        return (T) getInstance(str, cls, null);
    }

    default <T> T getInstance(String str, Class<T> cls, String str2) {
        try {
            Lazy<T> lazy = get(str, cls, str2);
            if (lazy.isPresent()) {
                return lazy.get();
            }
            return null;
        } catch (IllegalStateException unused) {
            return null;
        }
    }
}
