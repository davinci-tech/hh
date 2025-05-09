package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.hianalytics.core.storage.Event;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b1 implements o1 {

    /* renamed from: a, reason: collision with root package name */
    private String f4582a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public void f(String str) {
        this.e = str;
    }

    public void e(String str) {
        this.f = str;
    }

    public void d(String str) {
        this.f4582a = str;
    }

    public JSONObject d() {
        JSONObject a2 = a();
        a2.put(Event.EventConstants.PROPERTIES, n.b(this.c, o0.d().a()));
        return a2;
    }

    public void c(String str) {
        this.d = str;
    }

    public String c() {
        return this.f4582a;
    }

    public void b(String str) {
        this.b = str;
    }

    public String b() {
        return this.d;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.b = jSONObject.optString("event");
        this.c = n.a(jSONObject.optString(Event.EventConstants.PROPERTIES), o0.d().a());
        this.f4582a = jSONObject.optString("type");
        this.d = jSONObject.optString(Event.EventConstants.RECORD_TIME);
        this.e = jSONObject.optString(Event.EventConstants.SESSION_NAME);
        this.f = jSONObject.optString(Event.EventConstants.IS_FIRST_SESSION);
    }

    public void a(String str) {
        this.c = str;
    }

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.f4582a);
        jSONObject.put(Event.EventConstants.RECORD_TIME, this.d);
        jSONObject.put("event", this.b);
        jSONObject.put(Event.EventConstants.SESSION_NAME, this.e);
        jSONObject.put(Event.EventConstants.IS_FIRST_SESSION, this.f);
        if (TextUtils.isEmpty(this.c)) {
            return null;
        }
        jSONObject.put(Event.EventConstants.PROPERTIES, new JSONObject(this.c));
        return jSONObject;
    }
}
