package com.huawei.hms.support.api.hwid;

import android.text.TextUtils;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.feature.result.AccountPickerCommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class SignOutResult extends Result {
    public boolean isSuccess() {
        return getStatus().isSuccess();
    }

    public SignOutResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        jsonToSuper(new JSONObject(str));
        return this;
    }

    protected void jsonToSuper(JSONObject jSONObject) {
        if (jSONObject.has("errorCode")) {
            setStatus(new Status(jSONObject.optInt("errorCode"), jSONObject.has("errorMsg") ? jSONObject.optString("errorMsg") : ""));
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("status");
        if (optJSONObject != null) {
            setStatus(new Status(optJSONObject.optInt("statusCode"), optJSONObject.optString(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, null)));
        }
    }
}
