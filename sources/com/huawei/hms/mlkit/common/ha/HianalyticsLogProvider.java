package com.huawei.hms.mlkit.common.ha;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.huawei.hms.framework.common.SystemPropUtils;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.framework.network.grs.GrsApi;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

/* loaded from: classes4.dex */
public class HianalyticsLogProvider {
    public static volatile Map<String, Long> h = new HashMap();
    public static Map<String, Timer> i = new HashMap();
    public static volatile HianalyticsLogProvider j = new HianalyticsLogProvider();
    public Context d;
    public a e;
    public GrsBaseInfo f;

    /* renamed from: a, reason: collision with root package name */
    public volatile boolean f5044a = false;
    public volatile boolean b = true;
    public String c = null;
    public Map<String, Map<String, Long>> g = new HashMap();

    public static HianalyticsLogProvider getInstance() {
        return j;
    }

    public final boolean a(Context context) {
        if (a() || this.f5044a) {
            return true;
        }
        b.a("HaLogProvider", "initlizeHaSdk APK mode,BUILD_MODE=APK");
        if (this.f == null) {
            this.f = new GrsBaseInfo();
            String str = this.e.f;
            b.b("HaLogProvider", "initGrsBaseInfo CountryCode = " + str);
            if (str != null && !str.isEmpty() && !"UNKNOWN".equals(str)) {
                this.f.setSerCountry(str.toUpperCase(Locale.ENGLISH));
            }
        }
        GrsApi.grsSdkInit(context, this.f);
        String synGetGrsUrl = GrsApi.synGetGrsUrl("com.huawei.cloud.mlkithianalytics", "ROOT");
        b.a("HaLogProvider", "GrsApi.synGetGrsUrl=" + synGetGrsUrl + ",BuildConfig.HIA_MODE=com.huawei.cloud.mlkithianalytics");
        this.f5044a = true;
        if (synGetGrsUrl != null && !synGetGrsUrl.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(synGetGrsUrl);
            return c.f5047a.a(context, arrayList);
        }
        b.a("HaLogProvider", "grs get url is empty");
        if (this.f.getSerCountry() == null) {
            return false;
        }
        b.a("HaLogProvider", "grs get url is empty, countryCode = " + this.f.getSerCountry());
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0050 A[RETURN] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.String r0 = "HaLogProvider"
            android.content.pm.PackageManager r1 = r5.getPackageManager()     // Catch: java.lang.Exception -> L36
            android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Exception -> L36
            java.lang.String r3 = "com.huawei.hms.core.aidlservice"
            r2.<init>(r3)     // Catch: java.lang.Exception -> L36
            r3 = 128(0x80, float:1.8E-43)
            java.util.List r1 = r1.queryIntentServices(r2, r3)     // Catch: java.lang.Exception -> L36
            int r2 = r1.size()     // Catch: java.lang.Exception -> L36
            if (r2 != 0) goto L1a
            goto L4c
        L1a:
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L36
            boolean r2 = r1.hasNext()     // Catch: java.lang.Exception -> L36
            if (r2 == 0) goto L4c
            java.lang.Object r1 = r1.next()     // Catch: java.lang.Exception -> L36
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1     // Catch: java.lang.Exception -> L36
            android.util.Pair r2 = new android.util.Pair     // Catch: java.lang.Exception -> L36
            android.content.pm.ServiceInfo r3 = r1.serviceInfo     // Catch: java.lang.Exception -> L36
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo     // Catch: java.lang.Exception -> L36
            java.lang.String r3 = r3.packageName     // Catch: java.lang.Exception -> L36
            r2.<init>(r3, r1)     // Catch: java.lang.Exception -> L36
            goto L4d
        L36:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "RuntimeException "
            r2.<init>(r3)
            java.lang.String r1 = r1.getMessage()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.huawei.hms.mlkit.common.ha.b.a(r0, r1)
        L4c:
            r2 = 0
        L4d:
            r1 = 0
            if (r2 != 0) goto L51
            return r1
        L51:
            java.lang.Object r2 = r2.first
            java.lang.String r2 = (java.lang.String) r2
            if (r5 != 0) goto L5d
            java.lang.String r5 = "Context not initialized"
            com.huawei.hms.mlkit.common.ha.b.a(r0, r5)     // Catch: java.lang.Exception -> L6f
            goto L81
        L5d:
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.lang.Exception -> L6f
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r2, r1)     // Catch: java.lang.Exception -> L6f
            android.content.pm.ApplicationInfo r5 = r5.applicationInfo     // Catch: java.lang.Exception -> L6f
            int r5 = r5.flags     // Catch: java.lang.Exception -> L6f
            r0 = 1
            r5 = r5 & r0
            if (r5 == 0) goto L81
            r1 = r0
            goto L81
        L6f:
            r5 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isPackageInternal Exception e: "
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.huawei.hms.mlkit.common.ha.b.c(r0, r5)
        L81:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.mlkit.common.ha.HianalyticsLogProvider.b(android.content.Context):boolean");
    }

    public String getTransId() {
        String str = this.c;
        return str == null ? "" : str;
    }

    public void initTimer(String str) {
        if (this.c == null) {
            this.c = UUID.randomUUID().toString();
        }
        if (i.get(str) == null) {
            Timer timer = new Timer();
            timer.schedule(new e(), 0L, 5000L);
            i.put(str, timer);
            b.b("HaLogProvider", "init timer, timer=" + timer + ",moduleName=" + str);
        }
        if (this.g.containsKey(str)) {
            this.g.remove(str);
        }
    }

    public HianalyticsLog logBegin(Context context, Bundle bundle) {
        this.d = context.getApplicationContext();
        this.e = d.a(context, bundle);
        HianalyticsLog hianalyticsLog = new HianalyticsLog();
        if (a() || !a(context)) {
            return hianalyticsLog;
        }
        hianalyticsLog.d(String.valueOf(System.currentTimeMillis()));
        return hianalyticsLog;
    }

    public void logEnd(HianalyticsLog hianalyticsLog) {
        try {
            if (a()) {
                b.b("HaLogProvider", "in logEnd, HA is forbiddenHiLog");
                return;
            }
            if (hianalyticsLog == null) {
                b.a("HaLogProvider", "in logEnd, haLog is null");
                return;
            }
            if (hianalyticsLog.f() == null) {
                b.b("HaLogProvider", "in logEnd, initlizeHaSdk is not well, so HA is forbiddenHiLog");
                return;
            }
            a(hianalyticsLog);
            long currentTimeMillis = System.currentTimeMillis();
            String l = hianalyticsLog.l();
            if (h != null) {
                Long l2 = h.get(l);
                if (l2 == null) {
                    l2 = 0L;
                    h.put(l, l2);
                }
                if (this.b || currentTimeMillis - l2.longValue() > 1000) {
                    a(hianalyticsLog, currentTimeMillis - Long.parseLong(hianalyticsLog.f()));
                    LinkedHashMap<String, String> a2 = d.a(hianalyticsLog);
                    a(l, a2);
                    c.f5047a.a(this.d, 1, "60001", a2);
                    c.f5047a.a(this.d, 0, "60001", a2);
                    this.b = false;
                    b.a("HaLogProvider", a2.toString());
                    h.put(l, Long.valueOf(currentTimeMillis));
                    if (this.g.containsKey(l)) {
                        this.g.remove(l);
                    }
                }
            }
            Log.i("HaLogProvider", "success HA logEnd, module name is: " + l);
        } catch (Exception unused) {
            Log.e("HaLogProvider", "logEnd: GetNullException");
        }
    }

    public void postEvent(Context context, int i2, BaseInfoGatherEvent baseInfoGatherEvent) {
        this.d = context.getApplicationContext();
        if (this.c == null) {
            this.c = UUID.randomUUID().toString();
        }
        if (baseInfoGatherEvent == null) {
            return;
        }
        this.e = d.a(context, baseInfoGatherEvent.getAppInfo());
        if (a()) {
            b.b("HaLogProvider", "HA is forbidden!");
            return;
        }
        if (!a(context)) {
            b.a("HaLogProvider", "HA initializ fail!");
            return;
        }
        if ((i2 & 1) == 1) {
            c.f5047a.a(context, 1, baseInfoGatherEvent.getEventId(), baseInfoGatherEvent.getEventData(context));
        }
        if ((i2 & 2) == 2) {
            c.f5047a.a(context, 0, baseInfoGatherEvent.getEventId(), baseInfoGatherEvent.getEventData(context));
        }
        if ((i2 & 4) == 4) {
            c.f5047a.a(context, 2, baseInfoGatherEvent.getEventId(), baseInfoGatherEvent.getEventData(context));
        }
    }

    public void reportAndCancelTimer() {
    }

    public void reportAndCancelTimer(String str) {
        if (this.c != null) {
            this.c = null;
        }
        this.b = true;
        h.clear();
        Timer timer = i.get(str);
        if (timer != null) {
            if (!a()) {
                c.f5047a.a();
            }
            b.b("HaLogProvider", "cancel timer, timer=" + timer + ",moduleName=" + str);
            timer.cancel();
            i.remove(str);
        }
    }

    public boolean sdkForbiddenHiLog(Context context) {
        if (!b(context) || this.e == null) {
            Log.i("HaLogProvider", "hms core do not exist or appInfo is null, sdkForbiddenHiLog");
            return false;
        }
        b.a("HaLogProvider", "forbiddenHiLog openHa = " + this.e.e);
        return b();
    }

    public final boolean b() {
        String property;
        String str = "UNKNOWN";
        if ("HONOR".equals(Build.BRAND)) {
            property = SystemPropUtils.getProperty("get", CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP_HN, "android.os.SystemProperties", "UNKNOWN");
        } else {
            property = SystemPropUtils.getProperty("get", CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP, "android.os.SystemProperties", "UNKNOWN");
        }
        if (!"eu".equalsIgnoreCase(property) && !"la".equalsIgnoreCase(property)) {
            str = property;
        }
        b.a("HaLogProvider", "forbiddenHiLog.getVenderCountry=" + str);
        if ("CN".equalsIgnoreCase(str)) {
            Log.i("HaLogProvider", "no HaForbidden, ha data up directly");
            return false;
        }
        if (this.e != null) {
            b.a("HaLogProvider", "forbiddenHiLog openHa = " + this.e.e);
            return !this.e.e;
        }
        b.a("HaLogProvider", "forbiddenHiLog openHa is empty, appInfo is null");
        return true;
    }

    public final boolean a() {
        Log.i("HaLogProvider", "in common ha, BUILD_MODE is: APK");
        return b();
    }

    public final void a(HianalyticsLog hianalyticsLog, long j2) {
        hianalyticsLog.e(String.valueOf(j2));
        hianalyticsLog.m(this.e.f5045a);
        hianalyticsLog.c(this.e.b);
        hianalyticsLog.p(this.e.c);
        hianalyticsLog.b(this.e.d);
        hianalyticsLog.l(d.b(this.d));
        hianalyticsLog.h(Build.MODEL);
        hianalyticsLog.i(d.a());
        hianalyticsLog.a(Build.VERSION.RELEASE);
        hianalyticsLog.g("");
        hianalyticsLog.n("MLKit");
        hianalyticsLog.f(this.e.f);
        hianalyticsLog.j("");
        hianalyticsLog.o(this.c);
        hianalyticsLog.k(d.a(this.d));
    }

    public final void a(HianalyticsLog hianalyticsLog) {
        String l = hianalyticsLog.l();
        Map<String, Long> map = this.g.get(l);
        if (map == null) {
            map = new HashMap<>();
            map.put("allCnt", 0L);
            map.put("costTimeAll", 0L);
            this.g.put(l, map);
        }
        map.put("allCnt", Long.valueOf((map.get("allCnt") == null ? 0L : map.get("allCnt").longValue()) + 1));
        long longValue = map.get("failCnt") == null ? 0L : map.get("failCnt").longValue();
        if (!hianalyticsLog.u()) {
            map.put("failCnt", Long.valueOf(longValue + 1));
        }
        map.put("costTimeAll", Long.valueOf((map.get("costTimeAll") != null ? map.get("costTimeAll").longValue() : 0L) + (System.currentTimeMillis() - Long.parseLong(hianalyticsLog.f()))));
        this.g.put(l, map);
    }

    public final void a(String str, LinkedHashMap<String, String> linkedHashMap) {
        Map<String, Long> map = this.g.get(str);
        if (map != null) {
            long longValue = map.get("allCnt") == null ? 0L : map.get("allCnt").longValue();
            linkedHashMap.put("allCnt", String.valueOf(longValue));
            long longValue2 = map.get("failCnt") == null ? 0L : map.get("failCnt").longValue();
            linkedHashMap.put("failCnt", String.valueOf(longValue2));
            linkedHashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(longValue != 0 ? (map.get("costTimeAll") == null ? 0L : map.get("costTimeAll").longValue()) / longValue : 0L));
            linkedHashMap.put("lastCallTime", String.valueOf(System.currentTimeMillis()));
            linkedHashMap.put("result", String.format("{0:%s}", String.valueOf(longValue - longValue2)));
        }
    }
}
