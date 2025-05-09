package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jlh {

    /* renamed from: a, reason: collision with root package name */
    private String f13930a;
    private String b;
    private String e = "";
    private String f = "";
    private int g = 0;
    private int j = 0;
    private int d = 0;
    private int c = 0;

    public void a(String str) {
        this.e = str;
    }

    public void b(String str) {
        this.f = d(str);
    }

    private String d(String str) {
        return (str == null || !str.contains("t_vfc_xian")) ? str : str.concat("|t_vfc_changan");
    }

    public void a(int i) {
        this.g = i;
    }

    public void d(int i) {
        this.j = i;
    }

    public void b(int i) {
        this.c = i;
    }

    public void e(int i) {
        this.d = i;
    }

    public void c(String str) {
        this.f13930a = str;
    }

    public void e(String str) {
        this.b = str;
    }

    public String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("supportBusiness", this.e);
            jSONObject.put("supportIssuers", this.f);
            jSONObject.put("supportDefautcard", this.g);
            jSONObject.put("supportDecouple", this.j);
            jSONObject.put("pictureWidth", this.d);
            jSONObject.put("pictureHeight", this.c);
            String str = this.f13930a;
            if (str != null) {
                jSONObject.put("supportBlankCardSak", str);
            }
            String str2 = this.b;
            if (str2 != null) {
                jSONObject.put("payVersion", str2);
            }
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }
}
