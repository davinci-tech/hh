package com.huawei.hms.common.internal.bean;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class AbstractCpPickerClientInfo {

    /* renamed from: a, reason: collision with root package name */
    protected String f4464a;
    protected String b;
    protected long c;
    protected String d;

    public String getAppId() {
        return this.f4464a;
    }

    public void setAppId(String str) {
        this.f4464a = str;
    }

    public String getPackageName() {
        return this.b;
    }

    public void setPackageName(String str) {
        this.b = str;
    }

    public long getHmsSdkVersion() {
        return this.c;
    }

    public void setHmsSdkVersion(long j) {
        this.c = j;
    }

    public String getSubAppId() {
        return this.d;
    }

    public void setSubAppId(String str) {
        this.d = str;
    }

    public String toJson() throws JSONException {
        return a().toString();
    }

    private JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("appId", this.f4464a);
        jSONObject.putOpt("packageName", this.b);
        jSONObject.put("hmsSdkVersion", this.c);
        jSONObject.putOpt("subAppId", this.d);
        return jSONObject;
    }

    public String toString() {
        return "CpClientInfo{appId='" + this.f4464a + "', packageName='" + this.b + "', hmsSdkVersion=" + this.c + "', subAppId=" + this.d + '}';
    }
}
