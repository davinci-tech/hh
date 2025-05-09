package com.huawei.hms.hwid;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ag {

    /* renamed from: a, reason: collision with root package name */
    private Integer f4640a;
    private String b;

    public Integer a() {
        return this.f4640a;
    }

    public void a(Integer num) {
        this.f4640a = num;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public static ag b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ag();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Integer valueOf = jSONObject.has("errorCode") ? Integer.valueOf(jSONObject.getInt("errorCode")) : null;
            String string = jSONObject.has("errorMsg") ? jSONObject.getString("errorMsg") : null;
            ag agVar = new ag();
            agVar.a(valueOf);
            agVar.a(string);
            return agVar;
        } catch (JSONException unused) {
            as.d("ReadSmsOutputBean", "ReadSmsOutputBean json parse falied", true);
            return new ag();
        }
    }
}
