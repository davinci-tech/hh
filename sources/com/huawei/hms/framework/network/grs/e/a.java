package com.huawei.hms.framework.network.grs.e;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.g.d;
import com.huawei.hms.framework.network.grs.g.g;
import com.huawei.hms.framework.network.grs.h.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class a {
    private static final String e = "a";
    private static final Map<String, Map<String, Map<String, String>>> f = new ConcurrentHashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Long> f4533a = new ConcurrentHashMap(16);
    private final c b;
    private final c c;
    private final g d;

    public c c() {
        return this.c;
    }

    public void b(GrsBaseInfo grsBaseInfo, Context context) {
        long j;
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        String a2 = this.b.a(grsParasKey, "");
        String a3 = this.b.a(grsParasKey + "time", "0");
        if (!TextUtils.isEmpty(a3) && a3.matches("\\d+")) {
            try {
                j = Long.parseLong(a3);
            } catch (NumberFormatException e2) {
                Logger.w(e, "convert urlParamKey from String to Long catch NumberFormatException.", e2);
            }
            Map<String, Map<String, Map<String, String>>> map = f;
            map.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(a2));
            Logger.d(e, "Cache size is: " + map.size());
            this.f4533a.put(grsParasKey, Long.valueOf(j));
            a(grsBaseInfo, grsParasKey, context);
        }
        j = 0;
        Map<String, Map<String, Map<String, String>>> map2 = f;
        map2.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(a2));
        Logger.d(e, "Cache size is: " + map2.size());
        this.f4533a.put(grsParasKey, Long.valueOf(j));
        a(grsBaseInfo, grsParasKey, context);
    }

    public g b() {
        return this.d;
    }

    public void a(GrsBaseInfo grsBaseInfo, d dVar, Context context, com.huawei.hms.framework.network.grs.g.j.c cVar) {
        if (dVar.f() == 2) {
            Logger.w(e, "update cache from server failed");
            return;
        }
        if (cVar.d().size() == 0) {
            String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
            if (dVar.m()) {
                f.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(this.b.a(grsParasKey, "")));
            } else {
                this.b.b(grsParasKey, dVar.j());
                f.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(dVar.j()));
            }
            if (!TextUtils.isEmpty(dVar.e())) {
                this.b.b(grsParasKey + "ETag", dVar.e());
            }
            this.b.b(grsParasKey + "time", dVar.a());
            this.f4533a.put(grsParasKey, Long.valueOf(Long.parseLong(dVar.a())));
        } else {
            this.b.b("geoipCountryCode", dVar.j());
            this.b.b("geoipCountryCodetime", dVar.a());
        }
        Logger.d(e, "Cache size is: " + f.size());
    }

    public void a(GrsBaseInfo grsBaseInfo, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        this.b.b(grsParasKey + "time", "0");
        this.f4533a.remove(grsParasKey + "time");
        Map<String, Map<String, Map<String, String>>> map = f;
        map.remove(grsParasKey);
        Logger.d(e, "Cache size is: " + map.size());
        this.d.a(grsParasKey);
    }

    public Map<String, String> a(GrsBaseInfo grsBaseInfo, String str, b bVar, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        Map<String, Map<String, Map<String, String>>> map = f;
        Map<String, Map<String, String>> map2 = map.get(grsParasKey);
        if (map2 != null && !map2.isEmpty()) {
            a(grsBaseInfo, bVar, context, str);
            return map2.get(str);
        }
        Logger.d(e, "Cache size is: " + map.size());
        return new HashMap();
    }

    public c a() {
        return this.b;
    }

    private void a(GrsBaseInfo grsBaseInfo, String str, Context context) {
        if (e.a(this.f4533a.get(str), 300000L)) {
            this.d.a(new com.huawei.hms.framework.network.grs.g.j.c(grsBaseInfo, context), null, null, this.c, -1);
        }
    }

    private void a(GrsBaseInfo grsBaseInfo, b bVar, Context context, String str) {
        Long l = this.f4533a.get(grsBaseInfo.getGrsParasKey(true, true, context));
        if (e.a(l)) {
            bVar.a(2);
            return;
        }
        if (e.a(l, 300000L)) {
            this.d.a(new com.huawei.hms.framework.network.grs.g.j.c(grsBaseInfo, context), null, str, this.c, -1);
        }
        bVar.a(1);
    }

    public a(c cVar, c cVar2, g gVar) {
        this.c = cVar2;
        this.b = cVar;
        this.d = gVar;
        gVar.a(this);
    }
}
