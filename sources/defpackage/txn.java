package defpackage;

import android.content.Context;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.EcKeyPair;
import com.huawei.wisesecurity.ucs.credential.entity.ErrorBody;
import com.huawei.wisesecurity.ucs.credential.entity.UcsKeyStoreProvider;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkResponse;
import com.huawei.wisesecurity.ucs_credential.c;
import com.huawei.wisesecurity.ucs_credential.n0;
import java.security.cert.Certificate;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class txn extends c {
    @Override // com.huawei.wisesecurity.ucs_credential.c
    public void a() throws twc {
        Context context = this.b;
        if (!twk.b() || twi.c("ucs_ec_keystore_sp_key_t", -1, context) == 0) {
            throw two.e("UcsECKeyStoreHandler", "keyStoreCertificateChain is off. not support android keyStore EC.", new Object[0], 1022L, "keyStoreCertificateChain is off. not support android keyStore EC.");
        }
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public String b() throws twc {
        twz.c(UcsKeyStoreProvider.ANDROID_KEYSTORE);
        n0 n0Var = twz.d;
        twz twzVar = (twz) n0Var;
        twzVar.b("ucs_ec_alias_rootKey");
        Certificate[] e = twzVar.e("ucs_ec_alias_rootKey");
        if (twj.a(e)) {
            twk.e(this.b);
            throw new twc(2001L, "android keystore EC no support software attestation root.");
        }
        txi txiVar = new txi();
        txiVar.i = "ucs_ec_alias_rootKey";
        txiVar.o = "ED256";
        txiVar.j = n0Var;
        txiVar.k = e;
        txiVar.n = "AndroidKS";
        List<String> pkgNameCertFP = UcsLib.getPkgNameCertFP(this.b);
        txiVar.c = 1;
        txiVar.d = this.e;
        txiVar.e = this.d;
        txiVar.f = 1;
        txiVar.g = pkgNameCertFP.get(0);
        txiVar.h = pkgNameCertFP.get(1);
        EcKeyPair a2 = txa.a(this.b);
        txiVar.m = twe.a(a2.getPublicKey(), 2);
        txa.a(a2);
        return txiVar.b();
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
        e.append("; error code : ");
        e.append(fromString.getErrorCode());
        String sb = e.toString();
        twb.e("UcsECKeyStoreHandler", sb, new Object[0]);
        if (c.b(fromString.getErrorCode())) {
            twk.e(this.b);
            twb.a("UcsECKeyStoreHandler", "turn off android keystore EC CertificateChain", new Object[0]);
        }
        throw new twc(1024L, sb);
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public Credential a(String str, String str2, String str3, String str4, twr twrVar) throws twc {
        try {
            twb.a("UcsECKeyStoreHandler", "applyCredential use KeyStoreEcHandler.", new Object[0]);
            return a(str, str2, str3, str4);
        } catch (Throwable th) {
            StringBuilder e = twf.e("applyCredential use KeyStoreEcHandler get exception: ");
            e.append(th.getMessage());
            twb.e("UcsECKeyStoreHandler", e.toString(), new Object[0]);
            return twrVar.a(3, str, str2, str3, str4, twrVar);
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

    public txn(CredentialClient credentialClient, Context context, NetworkCapability networkCapability) throws twc {
        super(credentialClient, context, networkCapability);
    }
}
