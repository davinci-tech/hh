package com.huawei.hms.a.a.a;

import com.huawei.hms.common.internal.bean.AbstractCpPickerClientInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a extends AbstractCpPickerClientInfo {
    public static a a(String str) throws JSONException {
        return new a().a(new JSONObject(str));
    }

    private a a(JSONObject jSONObject) {
        this.f4464a = jSONObject.optString("appId", null);
        this.b = jSONObject.optString("packageName", null);
        this.c = jSONObject.optLong("hmsSdkVersion");
        this.d = jSONObject.optString("subAppId", null);
        return this;
    }

    @Override // com.huawei.hms.common.internal.bean.AbstractCpPickerClientInfo
    public String toString() {
        return "CpClientInfo{appId='" + this.f4464a + "', packageName='" + this.b + "', hmsSdkVersion=" + this.c + "', subAppId=" + this.d + '}';
    }
}
