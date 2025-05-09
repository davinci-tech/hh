package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a0 implements g {

    /* renamed from: a, reason: collision with root package name */
    private Context f4577a = q0.i();
    private String b;
    private JSONObject c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Boolean h;

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
        v.a("hmsSdk", "Begin to run EventRecordTask...");
        int h = q0.h();
        int k = a1.k(this.d, this.e);
        if (c0.a(this.f4577a, "stat_v2_1", h * 1048576)) {
            v.c("hmsSdk", "stat sp file reach max limited size, delete it");
            d.a(this.f4577a, "stat_v2_1", new String[0]);
            return;
        }
        b1 b1Var = new b1();
        b1Var.b(this.b);
        b1Var.a(this.c.toString());
        b1Var.d(this.e);
        b1Var.c(this.f);
        b1Var.f(this.g);
        Boolean bool = this.h;
        b1Var.e(bool == null ? null : String.valueOf(bool));
        try {
            JSONObject d = b1Var.d();
            String a2 = n1.a(this.d, this.e);
            String a3 = d.a(this.f4577a, "stat_v2_1", a2, "");
            try {
                jSONArray = !TextUtils.isEmpty(a3) ? new JSONArray(a3) : new JSONArray();
            } catch (JSONException unused) {
                v.d("hmsSdk", "Cached data corrupted: stat_v2_1");
                jSONArray = new JSONArray();
            }
            jSONArray.put(d);
            d.b(this.f4577a, "stat_v2_1", a2, jSONArray.toString());
            if (jSONArray.toString().length() > k * 1024) {
                e.a().a(this.d, this.e);
            }
        } catch (JSONException unused2) {
            v.e("hmsSdk", "eventRecord toJson error! The record failed.");
        }
    }

    public a0(String str, JSONObject jSONObject, String str2, String str3, long j) {
        this.b = str;
        this.c = jSONObject;
        this.d = str2;
        this.e = str3;
        this.f = String.valueOf(j);
        if (z.i(str2, "oper")) {
            p0 a2 = y.a().a(str2, j);
            this.g = a2.a();
            this.h = Boolean.valueOf(a2.b());
        }
    }
}
