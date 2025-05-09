package com.huawei.wisesecurity.kfs.crypto.digest;

/* loaded from: classes9.dex */
public enum DigestAlg {
    SHA256("sha-256");

    private final String alg;

    DigestAlg(String str) {
        this.alg = str;
    }

    public String getTransformation() {
        return this.alg;
    }
}
