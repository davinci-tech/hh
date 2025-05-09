package com.huawei.ads.adsrec;

import android.content.Context;
import com.huawei.ads.adsrec.db.table.AdTraceRecord;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.uw;
import defpackage.vj;
import defpackage.vp;
import defpackage.vs;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class n {
    private final e e;
    private int d = 0;
    private int b = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f1679a = 0;
    private int c = 0;
    private int f = 0;
    private StringBuilder h = new StringBuilder();
    private StringBuilder j = new StringBuilder();

    public void c(boolean z) {
        ICABackFlowCallback e = uw.e();
        if (e == null) {
            return;
        }
        IUtilCallback d = uw.d();
        if (d == null) {
            HiAdLog.e("CABackFlowProcessor", "utilCallback is null, return");
            return;
        }
        if (!z) {
            if (!vs.e()) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long lastBackFlowCATime = e.getLastBackFlowCATime();
            String config = d.getConfig("maxIntervalTriggerBackFlow");
            String config2 = d.getConfig("maxCountTriggerBackFlow");
            long queryCount = this.e.queryCount("AdTraceRecord");
            if (currentTimeMillis - lastBackFlowCATime <= StringUtils.parseIntOrDefault(config, 24) * 3600000 && queryCount < StringUtils.parseIntOrDefault(config2, 15)) {
                return;
            }
        }
        String a2 = a(d);
        if (StringUtils.isBlank(a2)) {
            HiAdLog.i("CABackFlowProcessor", "content is blank, return");
            return;
        }
        boolean backFlow2CA = e.backFlow2CA(a2);
        e.setLastBackFlowCATime(System.currentTimeMillis());
        this.e.d();
        d(backFlow2CA);
    }

    private void d(boolean z) {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.f1679a);
        sb.append(", ");
        sb.append(this.b);
        sb.append(", ");
        sb.append(this.f);
        sb.append(", ");
        sb.append(this.c);
        j.a(this.d, sb.toString(), this.j.toString(), this.h.toString(), z ? "1" : "0");
    }

    private JSONArray e(IUtilCallback iUtilCallback) {
        JSONArray jSONArray = new JSONArray();
        Map<String, Double> userCost = iUtilCallback.getUserCost(0);
        Map<String, Double> userCost2 = iUtilCallback.getUserCost(1);
        Long costEndTime = iUtilCallback.getCostEndTime(0);
        Long costEndTime2 = iUtilCallback.getCostEndTime(1);
        if (!vp.a(userCost)) {
            for (Map.Entry<String, Double> entry : userCost.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                JSONObject jSONObject = new JSONObject();
                vj.a(jSONObject, "secondIndustryId", key);
                Map<String, Double> map = userCost2;
                vj.c(jSONObject, "cost7d", value.doubleValue());
                vj.c(jSONObject, "endTime", costEndTime.longValue());
                jSONArray.put(jSONObject);
                if (!StringUtils.isBlank(key)) {
                    if (this.j.length() > 0) {
                        this.j.append(",");
                    }
                    this.j.append(key);
                }
                if (Math.abs(value.doubleValue()) < 1.0E-8d) {
                    this.f1679a++;
                    userCost2 = map;
                } else {
                    this.b++;
                    userCost2 = map;
                }
            }
        }
        Map<String, Double> map2 = userCost2;
        if (!vp.a(map2)) {
            for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                String key2 = entry2.getKey();
                double doubleValue = entry2.getValue().doubleValue();
                JSONObject jSONObject2 = new JSONObject();
                vj.a(jSONObject2, "dspId", key2);
                vj.c(jSONObject2, "cost7d", doubleValue);
                vj.c(jSONObject2, "endTime", costEndTime2.longValue());
                jSONArray.put(jSONObject2);
                if (!StringUtils.isBlank(key2)) {
                    if (this.h.length() > 0) {
                        this.h.append(",");
                    }
                    this.h.append(key2);
                }
                if (Math.abs(doubleValue) < 1.0E-8d) {
                    this.f++;
                } else {
                    this.c++;
                }
            }
        }
        return jSONArray;
    }

    private JSONArray a() {
        List<AdTraceRecord> query = this.e.query(AdTraceRecord.class, null, null, null, null, null);
        query.size();
        JSONArray jSONArray = new JSONArray();
        if (ListUtil.isEmpty(query)) {
            return jSONArray;
        }
        for (AdTraceRecord adTraceRecord : query) {
            try {
                String c = adTraceRecord.c();
                String f = adTraceRecord.f();
                String a2 = adTraceRecord.a();
                String d = adTraceRecord.d();
                String e = adTraceRecord.e();
                boolean g = adTraceRecord.g();
                JSONObject jSONObject = new JSONObject();
                vj.a(jSONObject, "contentId", c);
                vj.a(jSONObject, "uniqueId", f);
                vj.a(jSONObject, "adCreativeMaterialUrl", a2);
                if (!StringUtils.isBlank(d)) {
                    vj.b(jSONObject, "exposure", vj.e(d));
                }
                if (!StringUtils.isBlank(e)) {
                    vj.b(jSONObject, "click", vj.e(e));
                }
                vj.a(jSONObject, "tradeMode", adTraceRecord.b());
                vj.b(jSONObject, "sendToMedia", Boolean.valueOf(g));
                jSONArray.put(jSONObject);
                this.d++;
            } catch (Exception e2) {
                HiAdLog.printSafeStackTrace(3, e2);
                HiAdLog.e("CABackFlowProcessor", "build adsInfo  error");
            }
        }
        return jSONArray;
    }

    private String a(IUtilCallback iUtilCallback) {
        JSONArray a2 = a();
        if (this.d == 0) {
            return "";
        }
        JSONArray e = e(iUtilCallback);
        JSONObject jSONObject = new JSONObject();
        vj.b(jSONObject, "adsInfo", a2);
        vj.b(jSONObject, "cost", e);
        jSONObject.toString();
        return jSONObject.toString();
    }

    public n(Context context) {
        this.e = new e(context);
    }
}
