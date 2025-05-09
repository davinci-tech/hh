package com.huawei.hianalytics;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hianalytics.framework.listener.IHAEventListener;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class c1 implements IHAEventListener {
    @Override // com.huawei.hianalytics.framework.listener.IHAEventListener
    public void metricReport(String str) {
        u uVar;
        if (w.a().f97c.contains(str) && (uVar = t.b) != null && t.b() && !j.g()) {
            uVar.removeMessages(5);
            uVar.sendEmptyMessage(5);
        }
    }

    @Override // com.huawei.hianalytics.framework.listener.IHAEventListener
    public void reportAppEvent() {
        u uVar = t.f3899a;
        if (uVar == null) {
            return;
        }
        if (System.currentTimeMillis() - j.a("global_v2", "lastReportTime", 0L) < s.a().f78c) {
            return;
        }
        uVar.removeMessages(4);
        uVar.sendEmptyMessage(4);
    }

    @Override // com.huawei.hianalytics.framework.listener.IHAEventListener
    public void onReport(String str) {
        if ("ha_default_collection".equals(str)) {
            return;
        }
        try {
            if (System.currentTimeMillis() - j.a("global_v2", a(str, "report_ha_event_time"), 0L) < 43200000) {
                HiLog.d("HAEL", "report ha event interval is greater than 12 hours. tag:" + str);
            } else {
                HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
                if (instanceByTag == null) {
                    return;
                }
                instanceByTag.onReport(1);
                j.m558a("global_v2", a(str, "report_ha_event_time"), System.currentTimeMillis());
            }
        } catch (Exception unused) {
            HiLog.w("HAEL", "fail to report ha event");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003e A[Catch: Exception -> 0x0051, TRY_LEAVE, TryCatch #0 {Exception -> 0x0051, blocks: (B:9:0x0010, B:19:0x003a, B:21:0x003e, B:23:0x0020, B:26:0x002a), top: B:8:0x0010 }] */
    @Override // com.huawei.hianalytics.framework.listener.IHAEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onEvent(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.String r0 = "ha_default_collection"
            boolean r1 = r0.equals(r5)
            if (r1 == 0) goto L9
            return
        L9:
            com.huawei.hianalytics.process.HiAnalyticsInstance r0 = com.huawei.hianalytics.process.HiAnalyticsManager.getInstanceByTag(r0)
            if (r0 != 0) goto L10
            return
        L10:
            int r1 = r6.hashCode()     // Catch: java.lang.Exception -> L51
            r2 = -1403792647(0xffffffffac53d2f9, float:-3.0102017E-12)
            r3 = 1
            if (r1 == r2) goto L2a
            r2 = 1274936771(0x4bfdfdc3, float:3.3291142E7)
            if (r1 == r2) goto L20
            goto L34
        L20:
            java.lang.String r1 = "$discard_list"
            boolean r1 = r6.equals(r1)     // Catch: java.lang.Exception -> L51
            if (r1 == 0) goto L34
            r1 = 0
            goto L35
        L2a:
            java.lang.String r1 = "$retry_list"
            boolean r1 = r6.equals(r1)     // Catch: java.lang.Exception -> L51
            if (r1 == 0) goto L34
            r1 = r3
            goto L35
        L34:
            r1 = -1
        L35:
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L3a
            goto L58
        L3a:
            r4.a(r0, r5, r6)     // Catch: java.lang.Exception -> L51
            goto L58
        L3e:
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap     // Catch: java.lang.Exception -> L51
            r1.<init>()     // Catch: java.lang.Exception -> L51
            java.lang.String r2 = "$content"
            r1.put(r2, r7)     // Catch: java.lang.Exception -> L51
            java.lang.String r7 = "$tag"
            r1.put(r7, r5)     // Catch: java.lang.Exception -> L51
            r0.onEvent(r3, r6, r1)     // Catch: java.lang.Exception -> L51
            goto L58
        L51:
            java.lang.String r5 = "HAEL"
            java.lang.String r6 = "fail to on ha event"
            com.huawei.hianalytics.core.log.HiLog.w(r5, r6)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.c1.onEvent(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public final void a(HiAnalyticsInstance hiAnalyticsInstance, String str, String str2) {
        String a2;
        StringBuilder sb;
        String str3;
        long currentTimeMillis = System.currentTimeMillis();
        String a3 = j.a("global_v2", a(str, "send_retry_info"), "");
        if (TextUtils.isEmpty(a3)) {
            a2 = a(str, "send_retry_info");
            sb = new StringBuilder();
            sb.append(currentTimeMillis);
            str3 = ",1";
        } else {
            String[] split = a3.split(",");
            long parseStringToLong = Utils.parseStringToLong(split[0]);
            String str4 = split[1];
            if (currentTimeMillis - parseStringToLong <= 43200000) {
                long parseStringToLong2 = Utils.parseStringToLong(str4);
                a2 = a(str, "send_retry_info");
                sb = new StringBuilder();
                sb.append(parseStringToLong);
                sb.append(",");
                sb.append(parseStringToLong2 + 1);
                j.m559a("global_v2", a2, sb.toString());
            }
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("$content", str4);
            linkedHashMap.put("$tag", str);
            hiAnalyticsInstance.onEvent(1, str2, linkedHashMap);
            a2 = a(str, "send_retry_info");
            sb = new StringBuilder();
            sb.append(currentTimeMillis);
            str3 = ",0";
        }
        sb.append(str3);
        j.m559a("global_v2", a2, sb.toString());
    }

    public static String a(String str, String str2) {
        return str + "_" + str2;
    }
}
