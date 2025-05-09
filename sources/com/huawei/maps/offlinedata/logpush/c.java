package com.huawei.maps.offlinedata.logpush;

import com.huawei.maps.offlinedata.utils.g;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class c extends com.huawei.maps.offlinedata.logpush.a {
    private c() {
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final c f6450a = new c();
    }

    private static c d() {
        return a.f6450a;
    }

    public static void a(com.huawei.maps.offlinedata.logpush.dto.b bVar) {
        d().a(bVar, com.huawei.maps.offlinedata.logpush.utils.c.a(bVar.c()));
    }

    protected void a(com.huawei.maps.offlinedata.logpush.dto.b bVar, String str) {
        if (bVar == null) {
            g.a("ErrorTraceLogPusher", "log list is empty, do not need push.");
            return;
        }
        LinkedHashMap<String, String> a2 = a((com.huawei.maps.offlinedata.logpush.dto.c) bVar);
        a2.put(ParsedFieldTag.KAKA_TASK_SCENARIO, bVar.a());
        a2.put("message", bVar.b());
        a2.put("logTime", String.valueOf(System.currentTimeMillis()));
        a2.put("errorStack", str);
        a("map_offlinedata_errorlog_report", a2);
        a();
    }

    @Override // com.huawei.maps.offlinedata.logpush.a
    protected String c() {
        return "ErrorTraceLogPusher";
    }
}
