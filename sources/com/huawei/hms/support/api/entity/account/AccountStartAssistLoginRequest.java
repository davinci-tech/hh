package com.huawei.hms.support.api.entity.account;

import com.huawei.hms.support.feature.result.CommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AccountStartAssistLoginRequest {

    /* renamed from: a, reason: collision with root package name */
    private String f5957a;

    public AccountStartAssistLoginRequest() {
    }

    public AccountStartAssistLoginRequest(String str) {
        this.f5957a = str;
    }

    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str = this.f5957a;
        if (str != null) {
            jSONObject.put(CommonConstant.KEY_ASSIST_TOKEN, str);
        }
        return jSONObject.toString();
    }

    public static AccountStartAssistLoginRequest fromJson(String str) throws JSONException {
        if (str == null) {
            return null;
        }
        return new AccountStartAssistLoginRequest().objectFromJson(new JSONObject(str));
    }

    protected AccountStartAssistLoginRequest objectFromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            this.f5957a = jSONObject.getString(CommonConstant.KEY_ASSIST_TOKEN);
        }
        return this;
    }
}
