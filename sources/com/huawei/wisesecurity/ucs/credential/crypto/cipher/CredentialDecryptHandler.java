package com.huawei.wisesecurity.ucs.credential.crypto.cipher;

import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.codec.Encoder;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.SkDkEntity;
import com.huawei.wisesecurity.ucs_credential.k;
import defpackage.tsz;
import defpackage.ttm;
import defpackage.ttp;
import defpackage.tvv;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;
import defpackage.txd;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public class CredentialDecryptHandler implements DecryptHandler {
    private CredentialCipherText cipherText;
    private Credential credential;
    private CredentialClient credentialClient;

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public String toRawString() throws tvv {
        return to(Encoder.RAW);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public String toHex() throws tvv {
        return to(Encoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public String toBase64() throws tvv {
        return to(Encoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public byte[] to() throws tvv {
        doDecrypt();
        return this.cipherText.getPlainBytes();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public CredentialDecryptHandler fromHex(String str) throws tvv {
        return from(str, Decoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public CredentialDecryptHandler fromBase64Url(String str) throws tvv {
        return from(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public CredentialDecryptHandler fromBase64(String str) throws tvv {
        return from(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public CredentialDecryptHandler from(byte[] bArr) throws tvv {
        if (bArr == null) {
            throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, "cipherBytes cannot null..");
        }
        this.cipherText.setCipherBytes(bArr);
        return this;
    }

    private String to(Encoder encoder) throws tvv {
        try {
            return encoder.encode(to());
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to encode plain text: ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private CredentialDecryptHandler from(String str, Decoder decoder) throws tvv {
        try {
            from(decoder.decode(str));
            return this;
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to decode cipher text: ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private void doDecrypt() throws tvv {
        txd txdVar = (txd) new txd().c().setApiName("appAuth.decrypt").setCallTime();
        try {
            try {
                this.cipherText.checkParam(false);
                this.cipherText.setPlainBytes(new tsz.c().e(new SecretKeySpec(SkDkEntity.from(this.credential.getDataKeyBytes()).decryptSkDk(k.a(this.credential)), "AES")).a(CipherAlg.AES_GCM).a(this.cipherText.getIv()).b().getDecryptHandler().from(this.cipherText.getCipherBytes()).to());
                txdVar.setStatusCode(0);
            } catch (ttp e) {
                e = e;
                String str = "Fail to decrypt, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str);
                throw new tvv(1003L, str);
            } catch (tvz e2) {
                String str2 = "Fail to decrypt, errorMessage : " + e2.getMessage();
                txdVar.setStatusCode(1001).setErrorMsg(str2);
                throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, str2);
            } catch (twc e3) {
                e = e3;
                String str3 = "Fail to decrypt, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str3);
                throw new tvv(1003L, str3);
            }
        } finally {
            this.credentialClient.reportLogs(txdVar);
        }
    }

    public CredentialDecryptHandler(Credential credential, CredentialCipherText credentialCipherText, CredentialClient credentialClient) {
        this.credential = credential;
        this.cipherText = credentialCipherText;
        this.credentialClient = credentialClient;
    }
}
