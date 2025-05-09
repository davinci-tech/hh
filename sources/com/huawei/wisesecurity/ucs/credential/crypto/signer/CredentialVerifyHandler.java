package com.huawei.wisesecurity.ucs.credential.crypto.signer;

import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.SkDkEntity;
import com.huawei.wisesecurity.ucs_credential.k;
import defpackage.ttl;
import defpackage.ttm;
import defpackage.ttp;
import defpackage.tvv;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;
import defpackage.txd;
import java.nio.charset.StandardCharsets;

/* loaded from: classes7.dex */
public class CredentialVerifyHandler implements VerifyHandler {
    private Credential credential;
    private CredentialClient credentialClient;
    private CredentialSignText signText;

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verifyHex(String str) throws tvv {
        return verify(str, Decoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verifyBase64Url(String str) throws tvv {
        return verify(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verifyBase64(String str) throws tvv {
        return verify(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verify(byte[] bArr) throws tvv {
        this.signText.setSignature(bArr);
        return doVerify();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verify(String str) throws tvv {
        if (TextUtils.isEmpty(str)) {
            throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, "signature cannot empty..");
        }
        return verify(str.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public CredentialVerifyHandler fromHexData(String str) throws tvv {
        return fromData(str, Decoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public CredentialVerifyHandler fromData(byte[] bArr) {
        this.signText.setDataBytes(bArr);
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public CredentialVerifyHandler fromData(String str) throws tvv {
        if (TextUtils.isEmpty(str)) {
            throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, "dataString cannot empty..");
        }
        return fromData(str.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public CredentialVerifyHandler fromBase64UrlData(String str) throws tvv {
        return fromData(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public CredentialVerifyHandler fromBase64Data(String str) throws tvv {
        return fromData(str, Decoder.BASE64);
    }

    private boolean verify(String str, Decoder decoder) throws tvv {
        try {
            return verify(decoder.decode(str));
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to decode signature : ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private CredentialVerifyHandler fromData(String str, Decoder decoder) throws tvv {
        try {
            fromData(decoder.decode(str));
            return this;
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to decode sign data: ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private boolean doVerify() throws tvv {
        txd txdVar = (txd) new txd().c().setApiName("appAuth.verify").setCallTime();
        try {
            try {
                this.signText.checkParam(false);
                boolean checkSignature = checkSignature(new ttl.d().b(SkDkEntity.from(this.credential.getSecretKeyBytes()).decryptSkDk(k.a(this.credential))).c(SignAlg.HMAC_SHA256).e().getSignHandler().from(this.signText.getDataBytes()).sign(), this.signText.getSignature());
                txdVar.setStatusCode(0);
                return checkSignature;
            } catch (ttp e) {
                e = e;
                String str = "Fail to verify, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str);
                throw new tvv(1003L, str);
            } catch (tvz e2) {
                String str2 = "Fail to verify, errorMessage : " + e2.getMessage();
                txdVar.setStatusCode(1001).setErrorMsg(str2);
                throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, str2);
            } catch (twc e3) {
                e = e3;
                String str3 = "Fail to verify, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str3);
                throw new tvv(1003L, str3);
            }
        } finally {
            this.credentialClient.reportLogs(txdVar);
        }
    }

    private boolean checkSignature(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public CredentialVerifyHandler(Credential credential, CredentialSignText credentialSignText, CredentialClient credentialClient) {
        this.credential = credential;
        this.signText = credentialSignText;
        this.credentialClient = credentialClient;
    }
}
