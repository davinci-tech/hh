package com.huawei.hms.support.hwid.common;

import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.feature.result.AccountPickerCommonConstant;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class IntraAuthResult extends Result {
    public boolean isSuccess() {
        return getStatus().isSuccess();
    }

    protected void jsonToSuper(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("status");
        if (optJSONObject == null) {
            return;
        }
        setStatus(new Status(optJSONObject.optInt("statusCode"), optJSONObject.optString(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, null)));
    }
}
