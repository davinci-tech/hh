package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jlf implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("background_file_name")
    private String f13927a;
    private String b;
    private int c;

    @SerializedName("background_file_time")
    private long d;
    private int e;
    private int f;
    private String g;
    private String h;
    private String i;
    private String j;
    private boolean k;
    private String l;
    private String m;

    @SerializedName("Rf_file_name")
    private String n;
    private String o;
    private String p;

    @SerializedName("Rf_File_time")
    private long q;
    private long s;

    public jlf() {
    }

    public jlf(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            c(jSONObject);
            d(jSONObject);
        } catch (JSONException e) {
            LogUtil.b("TACardInfo", "TACardInfo error : ", e.getMessage());
        }
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String n() {
        return this.l;
    }

    public void g(String str) {
        this.l = str;
    }

    public String l() {
        return this.o;
    }

    public void h(String str) {
        this.o = str;
    }

    public int a() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public boolean s() {
        return this.k;
    }

    public void d(boolean z) {
        this.k = z;
    }

    public String g() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String f() {
        return this.g;
    }

    public void i(String str) {
        this.g = str;
    }

    public String j() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public String h() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public int e() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public long t() {
        return this.s;
    }

    public void b(long j) {
        this.s = j;
    }

    public int i() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public String o() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public String k() {
        return this.n;
    }

    public void j(String str) {
        this.n = str;
    }

    public long m() {
        return this.q;
    }

    public void c(long j) {
        this.q = j;
    }

    public String b() {
        return this.f13927a;
    }

    public void d(String str) {
        this.f13927a = str;
    }

    public long d() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public String r() {
        return this.p;
    }

    public void l(String str) {
        this.p = str;
    }

    private void d(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("card_type")) {
            this.f = jSONObject.getInt("card_type");
        }
        if (jSONObject.has("status_update_time")) {
            this.s = jSONObject.getLong("status_update_time");
        }
        if (jSONObject.has("name")) {
            this.m = jSONObject.getString("name");
        }
        if (jSONObject.has("Rf_File_time")) {
            this.q = jSONObject.getLong("Rf_File_time");
        }
        if (jSONObject.has("Rf_file_name")) {
            this.n = jSONObject.getString("Rf_file_name");
        }
        if (jSONObject.has("background_file_time")) {
            this.d = jSONObject.getLong("background_file_time");
        }
        if (jSONObject.has("background_file_name")) {
            this.f13927a = jSONObject.getString("background_file_name");
        }
        if (jSONObject.has("userid")) {
            this.p = jSONObject.getString("userid");
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("aid")) {
            this.b = jSONObject.getString("aid");
        }
        if (jSONObject.has("issuerId")) {
            this.o = jSONObject.getString("issuerId");
        }
        if (jSONObject.has("productId")) {
            this.l = jSONObject.getString("productId");
        }
        if (jSONObject.has("card_group_type")) {
            this.e = jSONObject.getInt("card_group_type");
        }
        if (jSONObject.has("fpan_digest")) {
            this.h = jSONObject.getString("fpan_digest");
        }
        if (jSONObject.has("is_default_card")) {
            this.k = jSONObject.getBoolean("is_default_card");
        }
        if (jSONObject.has("fpan_four")) {
            this.g = jSONObject.getString("fpan_four");
        }
        if (jSONObject.has("dpan_four")) {
            this.i = jSONObject.getString("dpan_four");
        }
        if (jSONObject.has("dpan_digest")) {
            this.j = jSONObject.getString("dpan_digest");
        }
        if (jSONObject.has("card_status")) {
            this.c = jSONObject.getInt("card_status");
        }
    }

    public String p() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("aid", this.b);
            jSONObject.put("productId", this.l);
            jSONObject.put("issuerId", this.o);
            jSONObject.put("card_group_type", this.e);
            jSONObject.put("is_default_card", this.k);
            jSONObject.put("fpan_digest", this.h);
            jSONObject.put("fpan_four", this.g);
            jSONObject.put("dpan_digest", this.j);
            jSONObject.put("dpan_four", this.i);
            jSONObject.put("card_status", this.c);
            jSONObject.put("status_update_time", this.s);
            jSONObject.put("card_type", this.f);
            jSONObject.put("name", this.m);
            jSONObject.put("Rf_file_name", this.n);
            jSONObject.put("Rf_File_time", this.q);
            jSONObject.put("background_file_name", this.f13927a);
            jSONObject.put("background_file_time", this.d);
            jSONObject.put("userid", this.p);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append(this.e).append(System.lineSeparator());
        stringBuffer.append(this.k).append(System.lineSeparator());
        stringBuffer.append(this.h).append(System.lineSeparator());
        stringBuffer.append(this.g).append(System.lineSeparator());
        stringBuffer.append(this.j).append(System.lineSeparator());
        stringBuffer.append(this.i).append(System.lineSeparator());
        stringBuffer.append(this.b).append(System.lineSeparator());
        stringBuffer.append(this.l).append(System.lineSeparator());
        stringBuffer.append(this.o).append(System.lineSeparator());
        stringBuffer.append(this.c).append(System.lineSeparator());
        stringBuffer.append(this.s).append(System.lineSeparator());
        stringBuffer.append(this.f).append(System.lineSeparator());
        stringBuffer.append(this.m).append(System.lineSeparator());
        stringBuffer.append(this.n).append(System.lineSeparator());
        stringBuffer.append(this.q).append(System.lineSeparator());
        stringBuffer.append(this.f13927a).append(System.lineSeparator());
        stringBuffer.append(this.d).append(System.lineSeparator());
        stringBuffer.append(this.p).append(System.lineSeparator());
        return stringBuffer.toString();
    }

    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            LogUtil.b("TaCardInfo", "clone CloneNotSupportedException");
            jlf jlfVar = new jlf();
            jlfVar.b = this.b;
            jlfVar.f13927a = this.f13927a;
            jlfVar.d = this.d;
            jlfVar.e = this.e;
            jlfVar.c = this.c;
            jlfVar.f = this.f;
            jlfVar.j = this.j;
            jlfVar.i = this.i;
            jlfVar.h = this.h;
            jlfVar.g = this.g;
            jlfVar.k = this.k;
            jlfVar.o = this.o;
            jlfVar.m = this.m;
            jlfVar.l = this.l;
            jlfVar.n = this.n;
            jlfVar.q = this.q;
            jlfVar.s = this.s;
            jlfVar.p = this.p;
            return jlfVar;
        }
    }
}
