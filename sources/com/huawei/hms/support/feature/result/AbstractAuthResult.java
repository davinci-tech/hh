package com.huawei.hms.support.feature.result;

import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class AbstractAuthResult extends Result {
    public boolean isSuccess() {
        return getStatus().isSuccess();
    }

    protected void jsonToSuper(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("status");
        if (optJSONObject != null) {
            setStatus(new Status(optJSONObject.optInt("statusCode"), optJSONObject.optString(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, null)));
        }
    }

    protected JSONObject superToJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (getStatus() != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("statusCode", getStatus().getStatusCode());
            if (getStatus().getStatusMessage() != null) {
                jSONObject2.put(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, getStatus().getStatusMessage());
            }
            jSONObject.put("status", jSONObject2);
        }
        return jSONObject;
    }
}
