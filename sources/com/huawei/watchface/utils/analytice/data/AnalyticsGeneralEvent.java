package com.huawei.watchface.utils.analytice.data;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.watchface.dj;
import com.huawei.watchface.utils.CommonUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class AnalyticsGeneralEvent extends BaseEvent<AnalyticsGeneralEvent> {

    /* renamed from: a, reason: collision with root package name */
    LinkedHashMap<String, String> f11200a = new LinkedHashMap<>();
    private String h;

    @Override // com.huawei.watchface.em
    public String a() {
        return this.h;
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        if (this.f11200a == null) {
            this.f11200a = new LinkedHashMap<>();
        }
        this.f11200a.put("startts", String.valueOf(m()));
        this.f11200a.put("endts", String.valueOf(n()));
        this.f11200a.put("totalTime", String.valueOf(n() - m()));
        this.f11200a.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, String.valueOf(dj.c()));
        this.f11200a.put("hmsVer", CommonUtils.v());
        return this.f11200a;
    }

    private void a(String str) {
        this.h = str;
    }

    public void sendEvent(String str, HashMap<String, String> hashMap) {
        AnalyticsGeneralEvent analyticsGeneralEvent = new AnalyticsGeneralEvent();
        analyticsGeneralEvent.a(str);
        analyticsGeneralEvent.f11200a.putAll(hashMap);
        analyticsGeneralEvent.a_();
    }
}
