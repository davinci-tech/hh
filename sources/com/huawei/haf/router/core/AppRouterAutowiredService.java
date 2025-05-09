package com.huawei.haf.router.core;

import android.util.LruCache;
import com.huawei.haf.router.facade.template.SyringeHandler;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
final class AppRouterAutowiredService {
    private final Set<String> e = new HashSet();
    private final Set<String> c = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private final LruCache<String, SyringeHandler> f2140a = new LruCache<>(10);

    AppRouterAutowiredService() {
    }

    void a(String[] strArr) {
        if (strArr.length > 0) {
            this.e.addAll(Arrays.asList(strArr));
        }
    }
}
