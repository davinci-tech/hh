package com.alipay.sdk.app;

import android.os.Bundle;
import defpackage.ma;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public final class OpenAuthTask {
    public static final Map<String, Callback> c = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    public static long f856a = -1;

    /* loaded from: classes8.dex */
    public enum BizType {
        Invoice("20000920"),
        AccountAuth("20000067"),
        Deduct("60000157");

        public String appId;

        BizType(String str) {
            this.appId = str;
        }
    }

    public interface Callback {
        void onResult(int i, String str, Bundle bundle);
    }

    public static void aN_(String str, int i, String str2, Bundle bundle) {
        Callback remove = c.remove(str);
        if (remove != null) {
            try {
                remove.onResult(i, str2, bundle);
            } catch (Throwable th) {
                ma.c(th);
            }
        }
    }
}
