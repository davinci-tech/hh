package com.huawei.agconnect.abtest.a;

import com.huawei.agconnect.abtest.ABTestException;
import com.huawei.agconnect.common.api.Logger;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private String f1695a;
    private String b;
    private String c;
    private String d;
    private boolean e;

    JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("experimentId", this.f1695a);
            jSONObject.put("variantId", this.b);
            jSONObject.put("startTime", this.c);
            jSONObject.put("triggerEventId", this.d);
            jSONObject.put("isTriggerEventReport", this.e);
        } catch (JSONException e) {
            Logger.e("ABTest", "json error", e);
        }
        return jSONObject;
    }

    public boolean d() {
        return this.e;
    }

    String c() {
        return this.d;
    }

    String b() {
        return this.b;
    }

    public void a(boolean z) {
        this.e = z;
    }

    String a() {
        return this.f1695a;
    }

    static a a(JSONObject jSONObject) {
        String str;
        if (jSONObject.opt("experimentId") == null) {
            str = "not exist experimentId";
        } else if (jSONObject.opt("variantId") == null) {
            str = "not exist variantId";
        } else {
            if (jSONObject.opt("startTime") != null) {
                String optString = jSONObject.optString("experimentId");
                String optString2 = jSONObject.optString("variantId");
                String optString3 = jSONObject.optString("startTime");
                String optString4 = jSONObject.optString("triggerEventId");
                boolean optBoolean = jSONObject.optBoolean("isTriggerEventReport");
                a aVar = new a();
                aVar.f1695a = optString;
                aVar.b = optString2;
                aVar.c = optString3;
                aVar.d = optString4;
                aVar.e = optBoolean;
                return aVar;
            }
            str = "not exist startTime key or format error";
        }
        Logger.e("ABTest", str);
        return null;
    }

    static a a(Map<String, String> map) {
        String str = map.get("experimentId");
        if (str == null) {
            throw new ABTestException("not exist experimentId key", 2);
        }
        String str2 = map.get("variantId");
        if (str2 == null) {
            throw new ABTestException("not exist variantId key", 2);
        }
        String str3 = map.get("startTime");
        String str4 = map.get("triggerEventId");
        a aVar = new a();
        aVar.f1695a = str;
        aVar.b = str2;
        aVar.c = str3;
        aVar.d = str4;
        aVar.e = false;
        return aVar;
    }
}
