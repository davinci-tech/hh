package com.huawei.wisesecurity.ucs.credential.crypto.cipher;

/* loaded from: classes9.dex */
public enum CredentialCipherAlg {
    AES_GCM(1);

    private int id;

    public int getId() {
        return this.id;
    }

    CredentialCipherAlg(int i) {
        this.id = i;
    }
}
