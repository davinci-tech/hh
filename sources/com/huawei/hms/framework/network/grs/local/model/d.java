package com.huawei.hms.framework.network.grs.local.model;

import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private String f4560a;
    private Map<String, String> b;

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("countryGroup", this.f4560a);
        JSONObject jSONObject2 = new JSONObject();
        for (String str : this.b.keySet()) {
            jSONObject2.put(str, this.b.get(str));
        }
        jSONObject.put("addresses", jSONObject2);
        return jSONObject;
    }

    public String b() {
        return this.f4560a;
    }

    public void a(Map<String, String> map) {
        this.b = map;
    }

    public void a(String str) {
        this.f4560a = str;
    }

    public Map<String, String> a() {
        return this.b;
    }
}
