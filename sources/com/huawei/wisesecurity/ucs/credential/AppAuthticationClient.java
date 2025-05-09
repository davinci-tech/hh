package com.huawei.wisesecurity.ucs.credential;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import com.huawei.wisesecurity.ucs.credential.crypto.signer.CredentialSignAlg;
import com.huawei.wisesecurity.ucs.credential.entity.SkDkEntity;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs_credential.k;
import defpackage.ttl;
import defpackage.ttr;
import defpackage.tue;
import defpackage.tvv;
import defpackage.tvz;
import defpackage.twb;
import defpackage.twc;
import defpackage.twe;
import defpackage.twf;
import defpackage.twn;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes9.dex */
public class AppAuthticationClient {
    private CredentialSignAlg alg;
    private Context context;
    private Credential credential;
    private String extra;

    @Deprecated
    public String getAppAuthtication() throws twc {
        String str;
        String str2;
        String str3;
        String str4 = "";
        try {
            twn twnVar = new twn();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("alg", "HS256");
                str = twe.a(jSONObject.toString().getBytes(StandardCharsets.UTF_8), 10);
            } catch (JSONException | twc e) {
                twb.e("AppAuthticationJws", "generate Header exception: {0}", e.getMessage());
                str = "";
            }
            twnVar.b = str;
            if (this.credential.getAkskVersion() < 1) {
                List<String> pkgNameCertFP = UcsLib.getPkgNameCertFP(this.context);
                str3 = pkgNameCertFP.get(0);
                str2 = pkgNameCertFP.get(1);
            } else {
                str2 = "";
                str3 = str2;
            }
            String str5 = this.extra;
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(SdkCfgSha256Record.PKGNAME, str3);
                jSONObject2.put("certSig", str2);
                if (!TextUtils.isEmpty(str5)) {
                    jSONObject2.put(KnitFragment.KEY_EXTRA, str5);
                }
                str4 = twe.a(jSONObject2.toString().getBytes(StandardCharsets.UTF_8), 10);
            } catch (JSONException | twc e2) {
                twb.e("AppAuthticationJws", "generate PayLoad exception: {0}", e2.getMessage());
            }
            twnVar.c = str4;
            twnVar.d = twe.a(new ttl.d().b(SkDkEntity.from(this.credential.getSecretKeyBytes()).decryptSkDk(k.a(this.credential))).c(SignAlg.HMAC_SHA256).e().getSignHandler().from(twnVar.e()).sign(), 10);
            return twnVar.a();
        } catch (UnsupportedOperationException unused) {
            throw new twc(2001L, "new String UnsupportedOperationException..");
        } catch (tvv e3) {
            throw new twc(1022L, e3.getMessage());
        } catch (Exception e4) {
            StringBuilder e5 = twf.e("app info auth Exception : ");
            e5.append(e4.getMessage());
            throw new twc(2001L, e5.toString());
        }
    }

    @Deprecated
    public static class Builder {

        @KfsNotNull
        private CredentialSignAlg alg = CredentialSignAlg.HMAC_SHA256;

        @KfsNotNull
        private Context context;

        @KfsNotNull
        private Credential credential;
        private String extra;

        public Builder extra(String str) {
            this.extra = str;
            return this;
        }

        public Builder credential(Credential credential) {
            this.credential = credential;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public AppAuthticationClient build() throws twc {
            try {
                tue.d(this);
                return new AppAuthticationClient(this.context, this.credential, this.extra, this.alg);
            } catch (ttr e) {
                StringBuilder e2 = twf.e("AppAuthticationClient check param error : ");
                e2.append(e.getMessage());
                throw new tvz(e2.toString());
            }
        }

        public Builder alg(CredentialSignAlg credentialSignAlg) {
            this.alg = credentialSignAlg;
            return this;
        }
    }

    private AppAuthticationClient(Context context, Credential credential, String str, CredentialSignAlg credentialSignAlg) {
        this.context = context;
        this.credential = credential;
        this.extra = str;
        this.alg = credentialSignAlg;
    }
}
