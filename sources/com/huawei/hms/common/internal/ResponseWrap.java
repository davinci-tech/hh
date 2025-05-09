package com.huawei.hms.common.internal;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ResponseWrap {

    /* renamed from: a, reason: collision with root package name */
    private String f4461a;
    private ResponseHeader b;

    public ResponseWrap(ResponseHeader responseHeader) {
        this.b = responseHeader;
    }

    public boolean fromJson(String str) {
        if (this.b == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.b.setStatusCode(JsonUtil.getIntValue(jSONObject, "status_code"));
            this.b.setErrorCode(JsonUtil.getIntValue(jSONObject, "error_code"));
            this.b.setErrorReason(JsonUtil.getStringValue(jSONObject, "error_reason"));
            this.b.setSrvName(JsonUtil.getStringValue(jSONObject, "srv_name"));
            this.b.setApiName(JsonUtil.getStringValue(jSONObject, "api_name"));
            this.b.setAppID(JsonUtil.getStringValue(jSONObject, "app_id"));
            this.b.setPkgName(JsonUtil.getStringValue(jSONObject, Constants.PARAM_PKG_NAME));
            this.b.setSessionId(JsonUtil.getStringValue(jSONObject, "session_id"));
            this.b.setTransactionId(JsonUtil.getStringValue(jSONObject, "transaction_id"));
            this.b.setResolution(JsonUtil.getStringValue(jSONObject, "resolution"));
            this.f4461a = JsonUtil.getStringValue(jSONObject, "body");
            return true;
        } catch (JSONException e) {
            HMSLog.e("ResponseWrap", "fromJson failed: " + e.getMessage());
            return false;
        }
    }

    public String getBody() {
        if (TextUtils.isEmpty(this.f4461a)) {
            this.f4461a = new JSONObject().toString();
        }
        return this.f4461a;
    }

    public ResponseHeader getResponseHeader() {
        return this.b;
    }

    public void setBody(String str) {
        this.f4461a = str;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.b = responseHeader;
    }

    public String toJson() {
        if (this.b == null) {
            return "{}";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status_code", this.b.getStatusCode());
            jSONObject.put("error_code", this.b.getErrorCode());
            jSONObject.put("error_reason", this.b.getErrorReason());
            jSONObject.put("srv_name", this.b.getSrvName());
            jSONObject.put("api_name", this.b.getApiName());
            jSONObject.put("app_id", this.b.getAppID());
            jSONObject.put(Constants.PARAM_PKG_NAME, this.b.getPkgName());
            jSONObject.put("transaction_id", this.b.getTransactionId());
            jSONObject.put("resolution", this.b.getResolution());
            String sessionId = this.b.getSessionId();
            if (!TextUtils.isEmpty(sessionId)) {
                jSONObject.put("session_id", sessionId);
            }
            if (!TextUtils.isEmpty(this.f4461a)) {
                jSONObject.put("body", this.f4461a);
            }
        } catch (JSONException e) {
            HMSLog.e("ResponseWrap", "toJson failed: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "ResponseWrap{body='" + this.f4461a + "', responseHeader=" + this.b + '}';
    }
}
