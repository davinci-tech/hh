package com.huawei.hms.hwid;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class af {

    /* renamed from: a, reason: collision with root package name */
    private String f4639a;

    public af() {
    }

    public af(String str) {
        this.f4639a = str;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("packageName", this.f4639a);
            return jSONObject.toString();
        } catch (JSONException unused) {
            as.d("ReadSmsInputBean", "toJson failed", true);
            return null;
        }
    }
}
