package com.huawei.hms.support.account.result;

import android.text.TextUtils;
import com.huawei.hms.support.feature.result.AbstractAuthResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountAuthResult extends AbstractAuthResult {
    private static final String TAG_ENTITY = "signInHuaweiId";
    private AuthAccount authAccount;

    public AuthAccount getAccount() {
        return this.authAccount;
    }

    public void setAuthAccount(AuthAccount authAccount) {
        this.authAccount = authAccount;
    }

    public AccountAuthResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        JSONObject jSONObject = new JSONObject(str);
        jsonToSuper(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("signInHuaweiId");
        if (optJSONObject != null) {
            this.authAccount = AuthAccount.fromJson(optJSONObject);
        }
        return this;
    }

    public String toJson() throws JSONException {
        return toJsonObject().toString();
    }

    protected JSONObject toJsonObject() throws JSONException {
        JSONObject superToJson = superToJson();
        AuthAccount authAccount = this.authAccount;
        if (authAccount != null) {
            superToJson.put("signInHuaweiId", authAccount.toJsonObject());
        }
        return superToJson;
    }
}
