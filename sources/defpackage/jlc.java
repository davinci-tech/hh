package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jlc {
    private String e;
    private int h;
    private String g = "";
    private int j = 0;
    private int i = 0;
    private int f = 0;
    private String o = "";
    private String b = "";
    private String d = "";
    private int c = 0;

    /* renamed from: a, reason: collision with root package name */
    private String f13926a = "";

    public String c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("techlist", this.g);
            jSONObject.put("ntfType", this.j);
            jSONObject.put("supportMifareClassic", this.i);
            jSONObject.put("supprotIsodep", this.f);
            jSONObject.put("uid", this.o);
            jSONObject.put("atqa", this.b);
            jSONObject.put("sak", this.h);
            jSONObject.put("isodepHistoryBytes", this.d);
            jSONObject.put("errCode", this.c);
            jSONObject.put("ats", this.f13926a);
            String str = this.e;
            if (str != null) {
                jSONObject.put("newSak", str);
            }
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "NfcTagInfo{nfcType='" + this.j + "', supportMifareClassic=" + this.i + ", supprotIsodep=" + this.f + "', uid=" + a() + ", atqa=" + this.b + ", sak=" + this.h + ", isodepHistoryBytes=" + this.d + ", errCode=" + this.c + ", ats=" + this.f13926a + '}';
    }

    public void e(String str) {
        this.g = str;
    }

    public void b(int i) {
        this.j = i;
    }

    public void e(int i) {
        this.i = i;
    }

    public void c(int i) {
        this.f = i;
    }

    public void h(String str) {
        this.o = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public void d(String str) {
        this.e = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void d(int i) {
        this.c = i;
    }

    public void a(String str) {
        this.f13926a = str;
    }

    private String a() {
        String str = this.o;
        if (str == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        for (int i = 0; i < this.o.length(); i++) {
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }
}
