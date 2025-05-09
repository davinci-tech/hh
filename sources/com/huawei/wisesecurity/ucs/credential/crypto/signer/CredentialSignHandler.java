package com.huawei.wisesecurity.ucs.credential.crypto.signer;

import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.codec.Encoder;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.SignHandler;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.SkDkEntity;
import com.huawei.wisesecurity.ucs_credential.k;
import defpackage.ttl;
import defpackage.ttm;
import defpackage.ttp;
import defpackage.tty;
import defpackage.tvv;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;
import defpackage.txd;
import java.nio.charset.StandardCharsets;

/* loaded from: classes7.dex */
public class CredentialSignHandler implements SignHandler {
    private Credential credential;
    private CredentialClient credentialClient;
    private CredentialSignText signText;

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public String signHex() throws tvv {
        return sign(Encoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public String signBase64Url() throws tvv {
        return sign(Encoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public String signBase64() throws tvv {
        return sign(Encoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public byte[] sign() throws tvv {
        doSign();
        return this.signText.getSignature();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public CredentialSignHandler fromHex(String str) throws tvv {
        return from(str, Decoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public CredentialSignHandler fromBase64Url(String str) throws tvv {
        return from(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public CredentialSignHandler fromBase64(String str) throws tvv {
        return from(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public CredentialSignHandler from(byte[] bArr) {
        this.signText.setDataBytes(tty.d(bArr));
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public CredentialSignHandler from(String str) throws tvv {
        if (TextUtils.isEmpty(str)) {
            throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, "dataString cannot empty..");
        }
        return from(str.getBytes(StandardCharsets.UTF_8));
    }

    private String sign(Encoder encoder) throws tvv {
        try {
            doSign();
            return encoder.encode(this.signText.getSignature());
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to encode signature bytes: ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private CredentialSignHandler from(String str, Decoder decoder) throws tvv {
        try {
            from(decoder.decode(str));
            return this;
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to decode plain text : ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private void doSign() throws tvv {
        txd txdVar = (txd) new txd().c().setApiName("appAuth.sign").setCallTime();
        try {
            try {
                this.signText.checkParam(true);
                this.signText.setSignature(new ttl.d().b(SkDkEntity.from(this.credential.getSecretKeyBytes()).decryptSkDk(k.a(this.credential))).c(SignAlg.HMAC_SHA256).e().getSignHandler().from(this.signText.getDataBytes()).sign());
                txdVar.setStatusCode(0);
            } catch (ttp e) {
                e = e;
                String str = "Fail to sign, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str);
                throw new tvv(1003L, str);
            } catch (tvz e2) {
                String str2 = "Fail to sign, errorMessage : " + e2.getMessage();
                txdVar.setStatusCode(1001).setErrorMsg(str2);
                throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, str2);
            } catch (twc e3) {
                e = e3;
                String str3 = "Fail to sign, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str3);
                throw new tvv(1003L, str3);
            }
        } finally {
            this.credentialClient.reportLogs(txdVar);
        }
    }

    public CredentialSignHandler(Credential credential, CredentialSignText credentialSignText, CredentialClient credentialClient) {
        this.credential = credential;
        this.signText = credentialSignText;
        this.credentialClient = credentialClient;
    }
}
