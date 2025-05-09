package defpackage;

import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.ucs_credential.n0;
import com.huawei.wisesecurity.ucs_credential.r;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class txi extends r {
    public String i;
    public n0 j;
    public Certificate[] k;
    public int l = 5;
    public String m;
    public String n;
    public String o;

    public String b() throws twc {
        try {
            this.b.put("alg", this.l);
            this.b.put("pbk", this.m);
            this.f11254a.put("alg", this.o);
            this.f11254a.put("cty", this.n);
            int min = Math.min(this.k.length, 3);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < min; i++) {
                jSONArray.put(twe.a(this.k[i].getEncoded(), 2));
            }
            this.f11254a.put("x5c", jSONArray);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("len", 32);
            this.f11254a.put("kid", twe.a(jSONObject.toString().getBytes(StandardCharsets.UTF_8), 10));
            try {
                this.b.put("kekAlg", this.c);
                this.b.put("packageName", this.d);
                this.b.put("appId", this.e);
                this.b.put("akskVersion", this.f);
                this.b.put("appPkgName", this.g);
                this.b.put("appCertFP", this.h);
                String str = twe.a(this.f11254a.toString().getBytes(StandardCharsets.UTF_8), 10) + "." + twe.a(this.b.toString().getBytes(StandardCharsets.UTF_8), 10);
                n0 n0Var = this.j;
                if (n0Var == null) {
                    throw new twc(1022L, "UcsKeyStore must no null");
                }
                return str + "." + twe.a(n0Var.a(this.i, str), 10);
            } catch (JSONException e) {
                twb.e("JwsKeystoreCredentialReqGenerator", "generate payload exception: {0}", e.getMessage());
                StringBuilder e2 = twf.e("build payload json error: ");
                e2.append(e.getMessage());
                throw new twc(Const.RawDataType.HEALTH_SLEEP_RECORD, e2.toString());
            }
        } catch (CertificateEncodingException | JSONException e3) {
            StringBuilder e4 = twf.e("put json error: ");
            e4.append(e3.getMessage());
            twb.e("JwsKeystoreECCredentialReqGenerator", e4.toString(), new Object[0]);
            StringBuilder e5 = twf.e("put json error: ");
            e5.append(e3.getMessage());
            throw new twc(Const.RawDataType.HEALTH_SLEEP_RECORD, e5.toString());
        }
    }
}
