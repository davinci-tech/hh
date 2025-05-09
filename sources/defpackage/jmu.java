package defpackage;

import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jmu {

    /* renamed from: a, reason: collision with root package name */
    private String f13964a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String b() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String a() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String e() {
        return this.f13964a;
    }

    public void b(String str) {
        this.f13964a = str;
    }

    public String d() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public static jmu c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        jmu jmuVar = new jmu();
        try {
            jmuVar.e = jnn.e(jSONObject, "apduNo");
            jmuVar.d = jnn.e(jSONObject, "apduContent");
            jmuVar.c = jnn.e(jSONObject, "apduStatus");
            jmuVar.f13964a = jnn.e(jSONObject, AwarenessRequest.Field.COMMAND);
            jmuVar.b = jnn.e(jSONObject, "checker");
            return jmuVar;
        } catch (JSONException e) {
            LogUtil.a("ServerAccessApdu", "buildFromJson,JSONException exception:", e.getMessage());
            return null;
        }
    }

    public String toString() {
        return "ServerAccessApdu{apduId='" + this.e + "', apduContent='" + this.d + "', apduStatus='" + this.c + "', command='" + this.f13964a + "', checker='" + this.b + "'}";
    }
}
