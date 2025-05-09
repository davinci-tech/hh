package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sur extends sum {
    private static final String e = "GetPassTypeIdInfoRequest";
    private String b;

    public String b() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public JSONObject b(JSONObject jSONObject) {
        if (jSONObject == null) {
            stq.e(e, "GetPassTypeIdInfoRequest createRequestData params error.", false);
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            if (!swe.b((CharSequence) b(), true)) {
                jSONObject2.put("passTypeId", b());
            }
            return jSONObject2;
        } catch (JSONException unused) {
            stq.e(e, "GetPassTypeIdInfoRequest createRequestData, JSONException");
            return null;
        }
    }
}
