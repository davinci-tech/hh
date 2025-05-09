package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.ErrorBody;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkResponse;
import com.huawei.wisesecurity.ucs_credential.c;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class txr extends c {
    @Override // com.huawei.wisesecurity.ucs_credential.c
    public void a() throws twc {
        Context context = this.b;
        if (!twk.b() || twi.c("ucs_keystore_sp_key_t", -1, context) == 0) {
            throw two.e("KeyStoreHandler", "keyStoreCertificateChain is off. not support keyStore RSA.", new Object[0], 1022L, "keyStoreCertificateChain is off. not support keyStore RSA.");
        }
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public String b() throws twc {
        String str;
        if (txg.f17402a == null) {
            txg.c.e(null);
        }
        txg txgVar = txg.c;
        txgVar.b("ucs_alias_rootKey");
        Certificate[] a2 = txgVar.a("ucs_alias_rootKey");
        if (twj.a(a2)) {
            twk.c(this.b);
            throw new twc(2001L, "android keystore RSA no support software attestation root.");
        }
        String twwVar = new tww("PS256", a2, "AndroidKS").toString();
        List<String> pkgNameCertFP = UcsLib.getPkgNameCertFP(this.b);
        String str2 = this.e;
        String str3 = this.d;
        String str4 = pkgNameCertFP.get(0);
        String str5 = pkgNameCertFP.get(1);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("alg", 2);
            jSONObject.put("kekAlg", 1);
            jSONObject.put("packageName", str2);
            jSONObject.put("appId", str3);
            jSONObject.put("akskVersion", 1);
            jSONObject.put("appPkgName", str4);
            jSONObject.put("appCertFP", str5);
            str = twe.a(jSONObject.toString().getBytes(StandardCharsets.UTF_8), 10);
        } catch (JSONException | twc e) {
            twb.e("CredentialJws", "generate payload exception: {0}", e.getMessage());
            str = "";
        }
        if (TextUtils.isEmpty(twwVar) || TextUtils.isEmpty(str)) {
            throw new twc(1006L, "Get signStr error");
        }
        String a3 = twe.a(txgVar.a("ucs_alias_rootKey", twwVar + "." + str), 10);
        if (TextUtils.isEmpty(twwVar) || TextUtils.isEmpty(str) || TextUtils.isEmpty(a3)) {
            throw new twc(1006L, "get credential JWS is empty...");
        }
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(twwVar) || TextUtils.isEmpty(str)) {
            throw new twc(1006L, "Get signStr error");
        }
        sb.append(twwVar + "." + str);
        sb.append(".");
        sb.append(a3);
        return sb.toString();
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public String a(NetworkResponse networkResponse) throws twc {
        boolean isSuccessful = networkResponse.isSuccessful();
        String body = networkResponse.getBody();
        if (isSuccessful) {
            return body;
        }
        ErrorBody fromString = ErrorBody.fromString(body);
        StringBuilder e = twf.e("tsms service error, ");
        e.append(fromString.getErrorMessage());
        String sb = e.toString();
        twb.e("KeyStoreHandler", sb, new Object[0]);
        if (c.b(fromString.getErrorCode())) {
            twk.c(this.b);
            twb.a("KeyStoreHandler", "turn off android keystore CertificateChain", new Object[0]);
        }
        throw new twc(1024L, sb);
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public Credential a(String str, String str2, String str3, String str4, twr twrVar) throws twc {
        try {
            twb.a("KeyStoreHandler", "applyCredential use KeyStoreHandler.", new Object[0]);
            return a(str, str2, str3, str4);
        } catch (Throwable th) {
            StringBuilder e = twf.e("applyCredential use KeyStoreHandler get exception: ");
            e.append(th.getMessage());
            twb.e("KeyStoreHandler", e.toString(), new Object[0]);
            return twrVar.a(0, str, str2, str3, str4, twrVar);
        }
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public Credential a(String str) throws twc {
        try {
            if (Integer.parseInt(new JSONObject(str).getString("expire")) == 0) {
                return this.g.genCredentialFromString(str);
            }
            throw new twc(1017L, "unenable expire.");
        } catch (NumberFormatException e) {
            StringBuilder e2 = twf.e("parse TSMS resp expire error : ");
            e2.append(e.getMessage());
            throw new twc(2001L, e2.toString());
        } catch (JSONException e3) {
            StringBuilder e4 = twf.e("parse TSMS resp get json error : ");
            e4.append(e3.getMessage());
            throw new twc(Const.RawDataType.HEALTH_SLEEP_RECORD, e4.toString());
        }
    }

    public txr(CredentialClient credentialClient, Context context, NetworkCapability networkCapability) throws twc {
        super(credentialClient, context, networkCapability);
    }
}
