package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public abstract class dh {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Long> f7698a = new ConcurrentHashMap();

    public static boolean a(String str, int i) {
        if (str == null) {
            return true;
        }
        Map<String, Long> map = f7698a;
        Long l = map.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (l != null) {
            r0 = currentTimeMillis - l.longValue() > ((long) i);
            if (!r0) {
                if (ho.a()) {
                    ho.a("TimeIntervalControl", "tag: %s isExpired %s", str, Boolean.valueOf(r0));
                }
                return r0;
            }
        }
        if (ho.a()) {
            ho.a("TimeIntervalControl", "tag: %s isExpired %s", str, Boolean.valueOf(r0));
        }
        map.put(str, Long.valueOf(currentTimeMillis));
        return r0;
    }

    public static boolean a(String str) {
        return a(str, 60000);
    }
}
