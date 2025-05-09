package com.huawei.hms.framework.network.grs.local.model;

import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private String f4558a;
    private String b;
    private Set<String> c;
    private String d;

    public void c(String str) {
        this.b = str;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.f4558a);
        jSONObject.put("name", this.b);
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = this.c.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        jSONObject.put("countries", jSONArray);
        jSONObject.put("description", this.d);
        return jSONObject;
    }

    public void b(String str) {
        this.f4558a = str;
    }

    public String b() {
        return this.f4558a;
    }

    public void a(Set<String> set) {
        this.c = set;
    }

    public void a(String str) {
        this.d = str;
    }

    public Set<String> a() {
        return this.c;
    }
}
