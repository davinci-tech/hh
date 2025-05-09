package com.huawei.hianalytics;

import org.json.JSONObject;

/* loaded from: classes4.dex */
public class y {

    /* renamed from: a, reason: collision with root package name */
    public long f3963a;

    /* renamed from: a, reason: collision with other field name */
    public String f108a;
    public String b;
    public String c;
    public String d;

    public y(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.f108a = jSONObject.optString("_id", "");
        this.b = jSONObject.optString("item_id", "");
        this.c = jSONObject.optString("metric_tag", "");
        this.f3963a = jSONObject.optLong("event_time", 0L);
        this.d = jSONObject.optString("content_code", "");
    }
}
