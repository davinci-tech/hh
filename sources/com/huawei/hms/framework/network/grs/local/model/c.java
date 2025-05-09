package com.huawei.hms.framework.network.grs.local.model;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private String f4559a;
    private String b;
    private final Map<String, d> c = new ConcurrentHashMap(16);
    private List<b> d = new ArrayList(16);

    public void c(String str) {
        this.b = str;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.f4559a);
        jSONObject.put("routeBy", this.b);
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = this.c.keySet().iterator();
        while (it.hasNext()) {
            d dVar = this.c.get(it.next());
            if (dVar != null) {
                jSONArray.put(dVar.c());
            }
        }
        jSONObject.put("servings", jSONArray);
        if (this.d != null) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<b> it2 = this.d.iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().c());
            }
            jSONObject.put("countryGroups", jSONArray2);
        }
        return jSONObject;
    }

    public void b(String str) {
        this.f4559a = str;
    }

    public String b() {
        return this.b;
    }

    public void a(List<b> list) {
        this.d = list;
    }

    public void a(String str, d dVar) {
        if (TextUtils.isEmpty(str) || dVar == null) {
            return;
        }
        this.c.put(str, dVar);
    }

    public List<b> a() {
        return this.d;
    }

    public d a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.c.get(str);
        }
        Logger.w("Service", "In servings.getServing(String groupId), the groupId is Empty or null");
        return null;
    }
}
