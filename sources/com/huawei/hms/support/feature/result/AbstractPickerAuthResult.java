package com.huawei.hms.support.feature.result;

import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class AbstractPickerAuthResult extends Result {
    public boolean isSuccess() {
        return getStatus().isSuccess();
    }

    protected void jsonToSuper(Map<Integer, Integer> map, JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("status");
        if (optJSONObject == null) {
            return;
        }
        int optInt = optJSONObject.optInt("statusCode");
        if (map.get(Integer.valueOf(optInt)) != null) {
            optInt = map.get(Integer.valueOf(optInt)).intValue();
        }
        setStatus(new Status(optInt, optJSONObject.optString(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, null)));
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
