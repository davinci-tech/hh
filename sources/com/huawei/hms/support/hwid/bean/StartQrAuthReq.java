package com.huawei.hms.support.hwid.bean;

import android.text.TextUtils;
import com.huawei.hms.activity.ForegroundBusDelegate;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class StartQrAuthReq {
    private String mAppId;
    private String mExtraInfo;
    private String mPackageName;
    private int mQueryTimeOut;
    private String mReqBody;
    private String mReqHeader;
    private List<String> mRequestScopes;

    @Deprecated
    public StartQrAuthReq(List<String> list, String str, int i) {
        this.mRequestScopes = list;
        this.mExtraInfo = str;
        this.mQueryTimeOut = i;
    }

    public StartQrAuthReq(String str, String str2, String str3) {
        this.mAppId = str;
        this.mPackageName = str2;
        this.mRequestScopes = getRequestScopes(str3);
    }

    public StartQrAuthReq() {
    }

    private List<String> getRequestScopes(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        return Arrays.asList(str.split(" +"));
    }

    public List<String> getmRequestScopes() {
        return this.mRequestScopes;
    }

    public void setmRequestScopes(List<String> list) {
        this.mRequestScopes = list;
    }

    public String getmExtraInfo() {
        return this.mExtraInfo;
    }

    public void setmExtraInfo(String str) {
        this.mExtraInfo = str;
    }

    public int getmQueryTimeOut() {
        return this.mQueryTimeOut;
    }

    public void setmQueryTimeOut(int i) {
        this.mQueryTimeOut = i;
    }

    public String getReqBody() {
        return this.mReqBody;
    }

    public void setReqBody(String str) {
        this.mReqBody = str;
    }

    public String getReqHeader() {
        return this.mReqHeader;
    }

    public void setReqHeader(String str) {
        this.mReqHeader = str;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.mRequestScopes != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = this.mRequestScopes.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put(AuthInternalPickerConstant.SignInReqKey.SCOP_ARRAYLIST, jSONArray);
        }
        if (!TextUtils.isEmpty(this.mExtraInfo)) {
            JSONObject jSONObject2 = new JSONObject(this.mExtraInfo);
            jSONObject.put("appId", jSONObject2.optString("appId"));
            jSONObject.put("packageName", jSONObject2.optString("packageName"));
        } else {
            jSONObject.put("appId", this.mAppId);
            jSONObject.put("packageName", this.mPackageName);
        }
        jSONObject.put("queryTimeOut", this.mQueryTimeOut);
        jSONObject.put(ForegroundBusDelegate.HMS_FOREGROUND_REQ_HEADER, this.mReqHeader);
        jSONObject.put(ForegroundBusDelegate.HMS_FOREGROUND_REQ_BODY, this.mReqBody);
        return jSONObject;
    }
}
