package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.m0;
import java.util.Map;

/* loaded from: classes9.dex */
public class d0<T extends m0> {
    public static final String b = "MemoryCache";
    public static final int c = 128;

    /* renamed from: a, reason: collision with root package name */
    public final LruCache<String, T> f5209a = new LruCache<>(128);

    public Map<String, T> b() {
        return this.f5209a.snapshot();
    }

    public T b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f5209a.get(str);
    }

    public boolean a(String str, T t) {
        if (y.b(t) || TextUtils.isEmpty(str)) {
            return false;
        }
        this.f5209a.put(str, t);
        return true;
    }

    public boolean a(String str) {
        Logger.v(b, "[DNS Memory Cache]remove one record，host: %s", str);
        this.f5209a.remove(str);
        return true;
    }

    public void a() {
        this.f5209a.evictAll();
    }
}
