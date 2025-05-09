package com.huawei.hms.support.hwid.result;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.feature.result.AbstractAuthResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HuaweiIdAuthResult extends AbstractAuthResult {
    private AuthHuaweiId authHuaweiId;

    public AuthHuaweiId getHuaweiId() {
        return this.authHuaweiId;
    }

    public void setAuthHuaweiId(AuthHuaweiId authHuaweiId) {
        this.authHuaweiId = authHuaweiId;
    }

    public HuaweiIdAuthResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        JSONObject jSONObject = new JSONObject(str);
        jsonToSuper(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID);
        if (optJSONObject != null) {
            this.authHuaweiId = AuthHuaweiId.fromJson(optJSONObject);
        }
        return this;
    }

    public String toJson() throws JSONException {
        return toJsonObject().toString();
    }

    protected JSONObject toJsonObject() throws JSONException {
        JSONObject superToJson = superToJson();
        AuthHuaweiId authHuaweiId = this.authHuaweiId;
        if (authHuaweiId != null) {
            superToJson.put(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID, authHuaweiId.toJsonObject());
        }
        return superToJson;
    }
}
