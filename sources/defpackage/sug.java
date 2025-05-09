package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sug implements Serializable {
    private static final long serialVersionUID = -217188557650814492L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("displayOverview")
    private String f17235a;

    @SerializedName("codeMsg")
    private String b;

    @SerializedName("hwReturnCode")
    private String d;

    @SerializedName("displayDetail")
    private String e;
    private String g;

    @SerializedName("supportRetry")
    private boolean h;

    @SerializedName("suggestion")
    private String i;

    @SerializedName("originalCode")
    private String j;
    private String f = null;
    private int c = 0;

    public void c(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.f17235a = str;
    }

    public void i(String str) {
        this.i = str;
    }

    public void d(String str) {
        this.j = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void j(String str) {
        this.g = str;
    }

    public void b(int i) {
        this.c = i;
    }

    public static sug a(JSONObject jSONObject) throws JSONException {
        sug sugVar = new sug();
        if (jSONObject.has("codeMsg")) {
            sugVar.c(jSONObject.getString("codeMsg"));
        }
        if (jSONObject.has("displayDetail")) {
            sugVar.e(jSONObject.getString("displayDetail"));
        }
        if (jSONObject.has("displayOverview")) {
            sugVar.a(jSONObject.getString("displayOverview"));
        }
        if (jSONObject.has("originalCode")) {
            sugVar.d(jSONObject.getString("originalCode"));
        }
        if (jSONObject.has("suggestion")) {
            sugVar.i(jSONObject.getString("suggestion"));
        }
        if (jSONObject.has("supportRetry")) {
            sugVar.b(jSONObject.getBoolean("supportRetry"));
        }
        if (jSONObject.has("tempAccessSec")) {
            sugVar.j(jSONObject.getString("tempAccessSec"));
        }
        if (jSONObject.has("deal")) {
            sugVar.b(jSONObject.getInt("deal"));
        }
        if (jSONObject.has("hwReturnCode")) {
            sugVar.b(jSONObject.getString("hwReturnCode"));
        }
        return sugVar;
    }

    public String toString() {
        return "ErrorInfo{codeMsg='" + this.b + "', displayDetail='" + this.e + "', displayOverview='" + this.f17235a + "', suggestion='" + this.i + "', originalCode='" + this.j + "', hwReturnCode='" + this.d + "', supportRetry=" + this.h + ", tempAccessSec=" + this.g + '}';
    }
}
