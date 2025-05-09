package com.huawei.maps.offlinedata.logpush;

import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.maps.offlinedata.utils.g;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class b extends com.huawei.maps.offlinedata.logpush.a {
    private b() {
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final b f6449a = new b();
    }

    public static b d() {
        return a.f6449a;
    }

    public static void a(com.huawei.maps.offlinedata.logpush.dto.a aVar) {
        aVar.a(System.currentTimeMillis());
        d().b(aVar);
    }

    protected void b(com.huawei.maps.offlinedata.logpush.dto.a aVar) {
        if (aVar == null) {
            g.a("AccessTraceLogPusher", "log list is empty, do not need push.");
            return;
        }
        LinkedHashMap<String, String> a2 = a((com.huawei.maps.offlinedata.logpush.dto.c) aVar);
        a2.put("requestId", aVar.a());
        a2.put("apiName", aVar.b());
        a2.put("errorCode", aVar.c());
        a2.put("startTime", String.valueOf(aVar.d()));
        a2.put("endTime", String.valueOf(aVar.e()));
        a2.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(a(aVar.d(), aVar.e())));
        a2.put("count", "1");
        a("map_offlinedata_access_trace", a2);
        a();
    }

    private int a(long j, long j2) {
        long j3 = j2 - j;
        if (j3 <= 2147483647L && j3 >= -2147483648L) {
            return (int) j3;
        }
        g.c(c(), "duration is out of integer range, using 0. start is " + j + ", end is " + j2);
        return 0;
    }

    @Override // com.huawei.maps.offlinedata.logpush.a
    protected String c() {
        return "AccessTraceLogPusher";
    }
}
