package com.huawei.openalliance.ad.utils;

import android.util.LruCache;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.PermissionEntity;
import java.lang.ref.SoftReference;
import java.util.List;

/* loaded from: classes5.dex */
public class cc {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7652a = "cc";
    private static final byte[] b = new byte[0];
    private static cc c;
    private SoftReference<LruCache<String, List<PermissionEntity>>> d;

    public void a(String str, List<PermissionEntity> list) {
        try {
            b().put(str, list);
        } catch (Throwable th) {
            ho.c(f7652a, "put cache encounter: " + th.getClass().getSimpleName());
        }
    }

    public List<PermissionEntity> a(String str) {
        try {
            return b().get(str);
        } catch (Throwable th) {
            ho.c(f7652a, "get cache encounter: " + th.getClass().getSimpleName());
            return null;
        }
    }

    private LruCache<String, List<PermissionEntity>> b() {
        SoftReference<LruCache<String, List<PermissionEntity>>> softReference = this.d;
        LruCache<String, List<PermissionEntity>> lruCache = softReference != null ? softReference.get() : null;
        if (lruCache != null) {
            return lruCache;
        }
        LruCache<String, List<PermissionEntity>> lruCache2 = new LruCache<>(5);
        this.d = new SoftReference<>(lruCache2);
        return lruCache2;
    }

    public static cc a() {
        cc ccVar;
        synchronized (b) {
            if (c == null) {
                c = new cc();
            }
            ccVar = c;
        }
        return ccVar;
    }
}
