package com.huawei.hms.support.hwid.result;

import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.IntraAuthResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class StartQrLoginResult extends IntraAuthResult {
    private SignInQrInfo signInQrInfo;

    public SignInQrInfo getSignInQrInfo() {
        return this.signInQrInfo;
    }

    public void setSignInQrInfo(SignInQrInfo signInQrInfo) {
        this.signInQrInfo = signInQrInfo;
    }

    public StartQrLoginResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        JSONObject jSONObject = new JSONObject(str);
        jsonToSuper(jSONObject);
        this.signInQrInfo = SignInQrInfo.fromJson(jSONObject);
        return this;
    }
}
