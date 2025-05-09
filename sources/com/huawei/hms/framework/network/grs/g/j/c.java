package com.huawei.hms.framework.network.grs.g.j;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final GrsBaseInfo f4549a;
    private final Context b;
    private final Set<String> c = new HashSet();

    public Set<String> d() {
        return this.c;
    }

    public String c() {
        return this.c.size() == 0 ? e() : f();
    }

    public GrsBaseInfo b() {
        return this.f4549a;
    }

    public void a(String str) {
        this.c.add(str);
    }

    public Context a() {
        return this.b;
    }

    private String f() {
        Logger.v("GrsRequestInfo", "getGeoipService enter");
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = this.c.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        try {
            jSONObject.put("services", jSONArray);
            Logger.v("GrsRequestInfo", "post query service list is:%s", jSONObject.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    private String e() {
        Set<String> b = com.huawei.hms.framework.network.grs.f.b.a(this.b.getPackageName()).b();
        if (b.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        try {
            jSONObject.put("services", jSONArray);
            Logger.d("GrsRequestInfo", "post service list is:%s", jSONObject.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public c(GrsBaseInfo grsBaseInfo, Context context) {
        this.f4549a = grsBaseInfo;
        this.b = context;
    }
}
