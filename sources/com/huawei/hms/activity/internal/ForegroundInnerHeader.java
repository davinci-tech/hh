package com.huawei.hms.activity.internal;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ForegroundInnerHeader {

    /* renamed from: a, reason: collision with root package name */
    private int f4261a;
    private String b;
    private String c;

    public void fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f4261a = JsonUtil.getIntValue(jSONObject, "apkVersion");
            this.b = JsonUtil.getStringValue(jSONObject, "action");
            this.c = JsonUtil.getStringValue(jSONObject, "responseCallbackKey");
        } catch (JSONException e) {
            HMSLog.e("ForegroundInnerHeader", "fromJson failed: " + e.getMessage());
        }
    }

    public String getAction() {
        return this.b;
    }

    public int getApkVersion() {
        return this.f4261a;
    }

    public String getResponseCallbackKey() {
        return this.c;
    }

    public void setAction(String str) {
        this.b = str;
    }

    public void setApkVersion(int i) {
        this.f4261a = i;
    }

    public void setResponseCallbackKey(String str) {
        this.c = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("apkVersion", this.f4261a);
            jSONObject.put("action", this.b);
            jSONObject.put("responseCallbackKey", this.c);
        } catch (JSONException e) {
            HMSLog.e("ForegroundInnerHeader", "ForegroundInnerHeader toJson failed: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "apkVersion:" + this.f4261a + ", action:" + this.b + ", responseCallbackKey:" + this.c;
    }
}
