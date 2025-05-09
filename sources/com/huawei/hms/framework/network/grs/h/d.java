package com.huawei.hms.framework.network.grs.h;

import android.os.SystemClock;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, a> f4553a = new ConcurrentHashMap(16);

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final long f4554a;
        private final long b;

        public boolean a() {
            return SystemClock.elapsedRealtime() - this.b <= this.f4554a;
        }

        public a(long j, long j2) {
            this.f4554a = j;
            this.b = j2;
        }
    }

    public static void a(String str, a aVar) {
        StringBuilder sb = new StringBuilder("map size of put is before: ");
        Map<String, a> map = f4553a;
        sb.append(map.size());
        Logger.v("RequestUtil", sb.toString());
        map.put(str, aVar);
        Logger.v("RequestUtil", "map size of put is after: " + map.size());
    }

    public static a a(String str) {
        StringBuilder sb = new StringBuilder("map size of get is before: ");
        Map<String, a> map = f4553a;
        sb.append(map.size());
        Logger.v("RequestUtil", sb.toString());
        a aVar = map.get(str);
        Logger.v("RequestUtil", "map size of get is after: " + map.size());
        return aVar;
    }
}
