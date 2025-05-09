package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jla {
    private String b = "";
    private int d = 0;

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("blockData", this.b);
            jSONObject.put("errCode", this.d);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "BlockData{blockData='" + this.b + "', errCode='" + this.d + '}';
    }

    public void b(String str) {
        if (str == null) {
            return;
        }
        if (!"".equals(this.b)) {
            this.b += "@";
        }
        this.b += str;
    }

    public void e(int i) {
        this.d = i;
    }
}
