package com.huawei.wisesecurity.ucs.credential.crypto.cipher;

import com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import defpackage.ttr;
import defpackage.tty;
import defpackage.tue;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;

/* loaded from: classes9.dex */
public class CredentialCipher implements KfsCipher {
    public CredentialCipherText cipherText;
    private Credential credential;
    private CredentialClient credentialClient;

    public static class Builder {

        @KfsNotNull
        private CredentialCipherAlg cipherAlg = CredentialCipherAlg.AES_GCM;

        @KfsNotNull
        private Credential credential;

        @KfsNotNull
        private CredentialClient credentialClient;

        @KfsNotNull
        private byte[] iv;

        public Builder withIv(byte[] bArr) {
            this.iv = tty.d(bArr);
            return this;
        }

        public Builder withCredentialClient(CredentialClient credentialClient) {
            this.credentialClient = credentialClient;
            return this;
        }

        public Builder withCredential(Credential credential) {
            this.credential = credential;
            return this;
        }

        public Builder withAlg(CredentialCipherAlg credentialCipherAlg) {
            this.cipherAlg = credentialCipherAlg;
            return this;
        }

        public CredentialCipher build() throws twc {
            try {
                tue.d(this);
                return new CredentialCipher(this.cipherAlg, this.credential, this.iv, this.credentialClient);
            } catch (ttr e) {
                StringBuilder e2 = twf.e("CredentialCipher check param error : ");
                e2.append(e.getMessage());
                throw new tvz(e2.toString());
            }
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher
    public CredentialEncryptHandler getEncryptHandler() {
        return new CredentialEncryptHandler(this.credential, this.cipherText, this.credentialClient);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher
    public CredentialDecryptHandler getDecryptHandler() {
        return new CredentialDecryptHandler(this.credential, this.cipherText, this.credentialClient);
    }

    public CredentialCipher(CredentialCipherAlg credentialCipherAlg, Credential credential, byte[] bArr, CredentialClient credentialClient) {
        this.credential = credential;
        CredentialCipherText credentialCipherText = new CredentialCipherText();
        credentialCipherText.setAlgId(credentialCipherAlg);
        credentialCipherText.setIv(bArr);
        this.cipherText = credentialCipherText;
        this.credentialClient = credentialClient;
    }
}
