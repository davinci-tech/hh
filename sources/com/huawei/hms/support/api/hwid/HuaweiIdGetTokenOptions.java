package com.huawei.hms.support.api.hwid;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HuaweiIdGetTokenOptions {

    /* renamed from: a, reason: collision with root package name */
    private String f5963a;
    private boolean b;

    public HuaweiIdGetTokenOptions(String str, boolean z) {
        this.f5963a = str;
        this.b = z;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.f5963a)) {
            jSONObject.put("accountName", this.f5963a);
        }
        jSONObject.put("fromGetToken", this.b);
        return jSONObject;
    }

    public static HuaweiIdGetTokenOptions fromJsonObject(JSONObject jSONObject) throws JSONException {
        return new HuaweiIdGetTokenOptions(jSONObject.optString("accountName"), jSONObject.optBoolean("fromGetToken", false));
    }

    public void setAccountName(String str) {
        this.f5963a = str;
    }

    public String getAccountName() {
        return this.f5963a;
    }

    public void setFromGetToken(boolean z) {
        this.b = z;
    }

    public boolean isFromGetToken() {
        return this.b;
    }
}
