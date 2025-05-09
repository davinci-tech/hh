package com.huawei.hms.support.api.entity.account;

import android.text.TextUtils;
import com.huawei.hms.support.feature.result.CommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class GetAssistTokenRequest {

    /* renamed from: a, reason: collision with root package name */
    private String f5958a;
    private String b;
    private String c;

    public GetAssistTokenRequest(String str) {
        this.f5958a = str;
    }

    public String getUserIdentify() {
        return this.b;
    }

    public void setUserIdentify(String str) {
        this.b = str;
    }

    public String getSessionId() {
        return this.c;
    }

    public void setSessionId(String str) {
        this.c = str;
    }

    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.f5958a)) {
            jSONObject.putOpt("accessToken", this.f5958a);
        }
        if (!TextUtils.isEmpty(this.b)) {
            jSONObject.putOpt(CommonConstant.KEY_USER_IDENTIFY, this.b);
        }
        if (!TextUtils.isEmpty(this.c)) {
            jSONObject.putOpt("sessionId", this.c);
        }
        return jSONObject.toString();
    }
}
