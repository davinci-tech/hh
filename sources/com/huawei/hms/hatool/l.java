package com.huawei.hms.hatool;

import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.operation.OpAnalyticsConstants;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class l extends t {
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    protected String f = "";
    private String g;

    public void g(String str) {
        this.f = str;
    }

    public void f(String str) {
        this.e = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.g = str;
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(OpAnalyticsConstants.ANDROID_ID, this.f4617a);
        jSONObject.put("oaid", this.g);
        jSONObject.put("uuid", this.f);
        jSONObject.put("upid", this.e);
        jSONObject.put(CommonUtil.IMEI, this.b);
        jSONObject.put("sn", this.c);
        jSONObject.put("udid", this.d);
        return jSONObject;
    }
}
