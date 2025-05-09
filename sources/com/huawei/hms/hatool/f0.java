package com.huawei.hms.hatool;

import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f0 extends k0 {
    private String g = "";

    public void g(String str) {
        this.g = str;
    }

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("protocol_version", "3");
        jSONObject.put("compress_mode", "1");
        jSONObject.put("serviceid", this.d);
        jSONObject.put("appid", this.f4598a);
        jSONObject.put("hmac", this.g);
        jSONObject.put("chifer", this.f);
        jSONObject.put("timestamp", this.b);
        jSONObject.put("servicetag", this.c);
        jSONObject.put("requestid", this.e);
        return jSONObject;
    }
}
