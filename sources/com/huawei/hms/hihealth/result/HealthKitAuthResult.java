package com.huawei.hms.hihealth.result;

import android.text.TextUtils;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.feature.result.AccountPickerCommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HealthKitAuthResult extends Result {
    private AuthAccount aab;

    public HealthKitAuthResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        JSONObject jSONObject = new JSONObject(str);
        JSONObject optJSONObject = jSONObject.optJSONObject("status");
        if (optJSONObject != null) {
            setStatus(new Status(optJSONObject.optInt("statusCode"), optJSONObject.optString(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, "")));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("HuaweiId");
        if (optJSONObject2 != null) {
            this.aab = AuthAccount.fromJson(optJSONObject2);
        }
        return this;
    }

    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (getStatus() != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("statusCode", getStatus().getStatusCode());
            if (getStatus().getStatusMessage() != null) {
                jSONObject2.put(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, getStatus().getStatusMessage());
            }
            jSONObject.put("status", jSONObject2);
        }
        AuthAccount authAccount = this.aab;
        if (authAccount != null) {
            jSONObject.put("HuaweiId", authAccount.toJsonObject());
        }
        return jSONObject.toString();
    }

    public void setAuthAccount(AuthAccount authAccount) {
        this.aab = authAccount;
    }

    public boolean isSuccess() {
        if (getStatus() != null) {
            return getStatus().isSuccess();
        }
        return true;
    }

    public int getErrorCode() {
        if (getStatus() != null) {
            return getStatus().getStatusCode();
        }
        return 0;
    }

    public AuthAccount getAuthAccount() {
        return this.aab;
    }
}
