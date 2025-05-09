package com.huawei.wisesecurity.ucs.credential.crypto.signer;

/* loaded from: classes7.dex */
public enum CredentialSignAlg {
    HMAC_SHA256(0);

    private int id;

    public int getId() {
        return this.id;
    }

    CredentialSignAlg(int i) {
        this.id = i;
    }
}
