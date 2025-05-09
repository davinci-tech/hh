package com.huawei.hms.support.picker.result;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.feature.result.AbstractPickerAuthResult;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountPickerResult extends AbstractPickerAuthResult {
    private static Map<Integer, Integer> errorCodeMap;
    private AuthAccountPicker authHuaweiId;

    static {
        HashMap hashMap = new HashMap();
        errorCodeMap = hashMap;
        hashMap.put(6, 2012);
        errorCodeMap.put(404, 2015);
    }

    public AuthAccountPicker getAuthAccountPicker() {
        return this.authHuaweiId;
    }

    public void setAuthAccountPicker(AuthAccountPicker authAccountPicker) {
        this.authHuaweiId = authAccountPicker;
    }

    public AccountPickerResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        JSONObject jSONObject = new JSONObject(str);
        jsonToSuper(errorCodeMap, jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID);
        if (optJSONObject != null) {
            this.authHuaweiId = AuthAccountPicker.fromJson(optJSONObject);
        }
        return this;
    }

    public String toJson() throws JSONException {
        JSONObject superToJson = superToJson();
        AuthAccountPicker authAccountPicker = this.authHuaweiId;
        if (authAccountPicker != null) {
            superToJson.put(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID, authAccountPicker.toJsonObject());
        }
        return superToJson.toString();
    }
}
