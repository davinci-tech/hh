package com.huawei.hms.support.api.hwid;

import android.text.TextUtils;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.feature.result.AccountPickerCommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class RevokeAccessResult extends Result {
    private static final String TAG = "[HUAWEIIDSDK]RevokeAccessResult";

    public boolean isSuccess() {
        return getStatus().isSuccess();
    }

    public RevokeAccessResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        jsonToSuper(new JSONObject(str));
        return this;
    }

    private void jsonToSuper(JSONObject jSONObject) {
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
