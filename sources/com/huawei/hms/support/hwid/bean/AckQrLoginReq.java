package com.huawei.hms.support.hwid.bean;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AckQrLoginReq {
    public static final String KEY_IS_FIRST_LOGIN = "isFirstLogin";
    public static final String KEY_IS_MAIN_ACCOUNT = "asMainAccount";
    public static final String KEY_SESSION_ID = "sessionId";
    public static final String KEY_VERIFY_RESULT = "verifyResult";
    private boolean isFirstLogin;
    private boolean isMainAccount;
    private String sessionId;
    private String verifyResult;

    public AckQrLoginReq(String str, String str2, boolean z) {
        this.sessionId = str;
        this.verifyResult = str2;
        this.isMainAccount = z;
    }

    public AckQrLoginReq(String str, String str2, boolean z, boolean z2) {
        this(str, str2, z);
        this.isFirstLogin = z2;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sessionId", this.sessionId);
        jSONObject.put("verifyResult", this.verifyResult);
        jSONObject.put(KEY_IS_MAIN_ACCOUNT, this.isMainAccount);
        jSONObject.put(KEY_IS_FIRST_LOGIN, this.isFirstLogin);
        return jSONObject;
    }
}
