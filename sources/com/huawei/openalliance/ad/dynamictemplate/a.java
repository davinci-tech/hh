package com.huawei.openalliance.ad.dynamictemplate;

import android.util.LruCache;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static a f6831a;
    private static final byte[] b = new byte[0];
    private IImageLoader c;
    private final LruCache<String, String> d = new LruCache<>(100);

    public void a(IImageLoader iImageLoader) {
        this.c = iImageLoader;
    }

    public static a a() {
        a aVar;
        synchronized (b) {
            if (f6831a == null) {
                f6831a = new a();
            }
            aVar = f6831a;
        }
        return aVar;
    }
}
