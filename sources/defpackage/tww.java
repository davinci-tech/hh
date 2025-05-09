package defpackage;

import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class tww {
    public Certificate[] d;

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("alg", "PS256");
            jSONObject.put("cty", "AndroidKS");
            Certificate[] certificateArr = this.d;
            int i = 3;
            if (certificateArr.length <= 3) {
                i = certificateArr.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                jSONArray.put(twe.a(this.d[i2].getEncoded(), 2));
            }
            jSONObject.put("x5c", jSONArray);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("len", 32);
            jSONObject.put("kid", twe.a(jSONObject2.toString().getBytes(StandardCharsets.UTF_8), 10));
            return twe.a(jSONObject.toString().getBytes(StandardCharsets.UTF_8), 10);
        } catch (CertificateEncodingException | JSONException | twc e) {
            twb.e("CredentialJws", "generate TAHeader exception: {0}", e.getMessage());
            return "";
        }
    }

    public tww(String str, Certificate[] certificateArr, String str2) {
        this.d = certificateArr;
    }
}
