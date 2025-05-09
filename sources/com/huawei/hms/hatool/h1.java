package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.hianalytics.core.storage.Event;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class h1 implements o1 {

    /* renamed from: a, reason: collision with root package name */
    private List<b1> f4590a;
    private k0 b;
    private t0 c;
    private o1 d;
    private String e = "";
    private String f;

    public void a(List<b1> list) {
        this.f4590a = list;
    }

    public void a(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    public void a(t0 t0Var) {
        this.c = t0Var;
    }

    public void a(l lVar) {
        this.d = lVar;
    }

    public void a(k0 k0Var) {
        this.b = k0Var;
    }

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        String str;
        List<b1> list = this.f4590a;
        if (list == null || list.size() == 0) {
            str = "Not have actionEvent to send";
        } else if (this.b == null || this.c == null || this.d == null) {
            str = "model in wrong format";
        } else {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("header", this.b.a());
            JSONObject jSONObject2 = new JSONObject();
            JSONObject a2 = this.d.a();
            a2.put(Event.EventConstants.PROPERTIES, this.c.a());
            try {
                a2.put("events_global_properties", new JSONObject(this.e));
            } catch (JSONException unused) {
                a2.put("events_global_properties", this.e);
            }
            jSONObject2.put("events_common", a2);
            JSONArray jSONArray = new JSONArray();
            Iterator<b1> it = this.f4590a.iterator();
            while (it.hasNext()) {
                JSONObject a3 = it.next().a();
                if (a3 != null) {
                    jSONArray.put(a3);
                } else {
                    v.e("hmsSdk", "custom event is empty,delete this event");
                }
            }
            jSONObject2.put(Event.EventConstants.EVENTS, jSONArray);
            try {
                String a4 = n.a(k1.a(jSONObject2.toString().getBytes("UTF-8")), this.f);
                if (TextUtils.isEmpty(a4)) {
                    v.e("hmsSdk", "eventInfo encrypt failed,report over!");
                    return null;
                }
                jSONObject.put("event", a4);
                return jSONObject;
            } catch (UnsupportedEncodingException unused2) {
                str = "getBitZip(): Unsupported coding : utf-8";
            }
        }
        v.e("hmsSdk", str);
        return null;
    }

    public h1(String str) {
        this.f = str;
    }
}
