package com.huawei.wisesecurity.ucs.credential.crypto.signer;

import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import defpackage.ttr;
import defpackage.tue;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;

/* loaded from: classes7.dex */
public class CredentialSigner implements KfsSigner {
    private Credential credential;
    private CredentialClient credentialClient;
    private CredentialSignText signText;

    public static class Builder {

        @KfsNotNull
        private Credential credential;

        @KfsNotNull
        private CredentialClient credentialClient;

        @KfsNotNull
        private CredentialSignAlg signAlg = CredentialSignAlg.HMAC_SHA256;

        public Builder withCredentialClient(CredentialClient credentialClient) {
            this.credentialClient = credentialClient;
            return this;
        }

        public Builder withCredential(Credential credential) {
            this.credential = credential;
            return this;
        }

        public Builder withAlg(CredentialSignAlg credentialSignAlg) {
            this.signAlg = credentialSignAlg;
            return this;
        }

        public CredentialSigner build() throws twc {
            try {
                tue.d(this);
                return new CredentialSigner(this.signAlg, this.credential, this.credentialClient);
            } catch (ttr e) {
                StringBuilder e2 = twf.e("CredentialCipher check param error : ");
                e2.append(e.getMessage());
                throw new tvz(e2.toString());
            }
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public CredentialVerifyHandler getVerifyHandler() {
        return new CredentialVerifyHandler(this.credential, this.signText, this.credentialClient);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public CredentialSignHandler getSignHandler() {
        return new CredentialSignHandler(this.credential, this.signText, this.credentialClient);
    }

    private CredentialSigner(CredentialSignAlg credentialSignAlg, Credential credential, CredentialClient credentialClient) {
        this.credential = credential;
        CredentialSignText credentialSignText = new CredentialSignText();
        this.signText = credentialSignText;
        credentialSignText.setAlgId(credentialSignAlg);
        this.credentialClient = credentialClient;
    }
}
