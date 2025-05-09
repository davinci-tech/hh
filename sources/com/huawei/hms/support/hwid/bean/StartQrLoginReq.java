package com.huawei.hms.support.hwid.bean;

import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class StartQrLoginReq {
    private static final String PACKAGE_NAME = "packageName";
    private static final String QR_SCENE = "qrScene";
    private static final String QUERY_TIMEOUT = "queryTimeout";
    private static final String SIGN_IN_PARAMS = "signInParams";
    private int queryTimeout = 180;
    private String qrScene = CommonConstant.StartQrLoginQrValue.QRSCENE_DEFAULT;
    private String packageName = "";
    private String signInParams = "";

    public void setQueryTimeout(int i) {
        this.queryTimeout = i;
    }

    public void setQrScene(String str) {
        this.qrScene = str;
    }

    public String getQrScene() {
        return this.qrScene;
    }

    public int getQueryTimeout() {
        return this.queryTimeout;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getSignInParams() {
        return this.signInParams;
    }

    public void setSignInParams(String str) {
        this.signInParams = str;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(QUERY_TIMEOUT, this.queryTimeout);
        jSONObject.put(QR_SCENE, this.qrScene);
        if (!TextUtils.isEmpty(this.packageName)) {
            jSONObject.put("packageName", this.packageName);
        }
        if (!TextUtils.isEmpty(this.signInParams)) {
            jSONObject.put("signInParams", this.signInParams);
        }
        return jSONObject;
    }
}
