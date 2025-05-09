package com.huawei.hms.support.api.account;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountGetTokenOptions {

    /* renamed from: a, reason: collision with root package name */
    private String f5954a;
    private boolean b;

    public AccountGetTokenOptions(String str, boolean z) {
        this.f5954a = str;
        this.b = z;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.f5954a)) {
            jSONObject.put("accountName", this.f5954a);
        }
        jSONObject.put("fromGetToken", this.b);
        return jSONObject;
    }

    public static AccountGetTokenOptions fromJsonObject(JSONObject jSONObject) throws JSONException {
        return new AccountGetTokenOptions(jSONObject.optString("accountName"), jSONObject.optBoolean("fromGetToken", false));
    }

    public String getAccountName() {
        return this.f5954a;
    }

    public void setAccountName(String str) {
        this.f5954a = str;
    }

    public boolean isFromGetToken() {
        return this.b;
    }

    public void setFromGetToken(boolean z) {
        this.b = z;
    }
}
