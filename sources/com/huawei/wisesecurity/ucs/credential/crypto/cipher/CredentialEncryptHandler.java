package com.huawei.wisesecurity.ucs.credential.crypto.cipher;

import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.codec.Encoder;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.SkDkEntity;
import com.huawei.wisesecurity.ucs_credential.k;
import defpackage.tsz;
import defpackage.ttm;
import defpackage.ttp;
import defpackage.tty;
import defpackage.tvv;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;
import defpackage.txd;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public class CredentialEncryptHandler implements EncryptHandler {
    private CredentialCipherText cipherText;
    private Credential credential;
    private CredentialClient credentialClient;

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public String toHex() throws tvv {
        return to(Encoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public String toBase64Url() throws tvv {
        return to(Encoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public String toBase64() throws tvv {
        return to(Encoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public byte[] to() throws tvv {
        doEncrypt();
        return this.cipherText.getCipherBytes();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public CredentialEncryptHandler fromHex(String str) throws tvv {
        return from(str, Decoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public CredentialEncryptHandler fromBase64Url(String str) throws tvv {
        return from(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public CredentialEncryptHandler fromBase64(String str) throws tvv {
        return from(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public CredentialEncryptHandler from(byte[] bArr) throws tvv {
        if (bArr == null) {
            throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, "plainBytes cannot null..");
        }
        this.cipherText.setPlainBytes(tty.d(bArr));
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public CredentialEncryptHandler from(String str) throws tvv {
        if (TextUtils.isEmpty(str)) {
            throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, "plainText cannot empty..");
        }
        return from(str.getBytes(StandardCharsets.UTF_8));
    }

    private String to(Encoder encoder) throws tvv {
        try {
            doEncrypt();
            return encoder.encode(this.cipherText.getCipherBytes());
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to encode cipher bytes: ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private CredentialEncryptHandler from(String str, Decoder decoder) throws tvv {
        try {
            from(decoder.decode(str));
            return this;
        } catch (ttm e) {
            StringBuilder e2 = twf.e("Fail to decode plain text : ");
            e2.append(e.getMessage());
            throw new tvv(1003L, e2.toString());
        }
    }

    private void doEncrypt() throws tvv {
        txd txdVar = (txd) new txd().c().setApiName("appAuth.encrypt").setCallTime();
        try {
            try {
                this.cipherText.checkParam(true);
                this.cipherText.setCipherBytes(new tsz.c().e(new SecretKeySpec(SkDkEntity.from(this.credential.getDataKeyBytes()).decryptSkDk(k.a(this.credential)), "AES")).a(CipherAlg.AES_GCM).a(this.cipherText.getIv()).b().getEncryptHandler().from(this.cipherText.getPlainBytes()).to());
                txdVar.setStatusCode(0);
            } catch (ttp e) {
                e = e;
                String str = "Fail to encrypt, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str);
                throw new tvv(1003L, str);
            } catch (tvz e2) {
                String str2 = "Fail to encrypt, errorMessage : " + e2.getMessage();
                txdVar.setStatusCode(1001).setErrorMsg(str2);
                throw new tvv(Const.RawDataType.HEALTH_EVENT_RECORD, str2);
            } catch (twc e3) {
                e = e3;
                String str3 = "Fail to encrypt, errorMessage : " + e.getMessage();
                txdVar.setStatusCode(1003).setErrorMsg(str3);
                throw new tvv(1003L, str3);
            }
        } finally {
            this.credentialClient.reportLogs(txdVar);
        }
    }

    public CredentialEncryptHandler(Credential credential, CredentialCipherText credentialCipherText, CredentialClient credentialClient) {
        this.credential = credential;
        this.cipherText = credentialCipherText;
        this.credentialClient = credentialClient;
    }
}
