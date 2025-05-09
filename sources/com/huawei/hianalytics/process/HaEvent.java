package com.huawei.hianalytics.process;

import com.huawei.hianalytics.g1;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class HaEvent {

    /* renamed from: a, reason: collision with root package name */
    public final long f3893a;

    /* renamed from: a, reason: collision with other field name */
    public final String f64a;

    /* renamed from: a, reason: collision with other field name */
    public final JSONObject f65a;

    public static HaEvent create(String str, JSONObject jSONObject) {
        return new HaEvent(str, jSONObject, System.currentTimeMillis());
    }

    public static HaEvent create(String str, LinkedHashMap<String, String> linkedHashMap) {
        return new HaEvent(str, linkedHashMap == null ? new JSONObject() : g1.a(linkedHashMap), System.currentTimeMillis());
    }

    public HaEvent(String str, JSONObject jSONObject, long j) {
        this.f3893a = j;
        this.f64a = str;
        this.f65a = jSONObject;
    }
}
