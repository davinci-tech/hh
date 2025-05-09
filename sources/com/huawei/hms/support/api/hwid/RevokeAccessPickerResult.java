package com.huawei.hms.support.api.hwid;

import android.text.TextUtils;
import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.feature.result.AccountPickerCommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class RevokeAccessPickerResult extends Result {

    @Packed
    private Integer mErrorCode;

    @Packed
    private String mErrorMsg;

    public RevokeAccessPickerResult() {
    }

    public RevokeAccessPickerResult(Integer num, String str) {
        this.mErrorCode = num;
        this.mErrorMsg = str;
    }

    public Integer getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(Integer num) {
        this.mErrorCode = num;
    }

    public String getErrorMsg() {
        return this.mErrorMsg;
    }

    public void setErrorMsg(String str) {
        this.mErrorMsg = str;
    }

    public RevokeAccessPickerResult(Status status) {
        setStatus(status);
    }

    public boolean isSuccess() {
        return getStatus().isSuccess();
    }

    public RevokeAccessPickerResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        jsonToSuper(new JSONObject(str));
        return this;
    }

    private void jsonToSuper(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("status");
        if (optJSONObject != null) {
            setStatus(new Status(optJSONObject.optInt("statusCode"), optJSONObject.optString(AccountPickerCommonConstant.KEY_STATUS_MESSAGE, null)));
        }
    }

    public RevokeAccessPickerResult getBeanFromJsonStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return new RevokeAccessPickerResult();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new RevokeAccessPickerResult(jSONObject.has("errorCode") ? Integer.valueOf(jSONObject.getInt("errorCode")) : null, jSONObject.getString("errorMsg"));
        } catch (JSONException unused) {
            return new RevokeAccessPickerResult();
        }
    }
}
