package com.huawei.wisesecurity.ucs.credential.entity;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import defpackage.ttr;
import defpackage.tue;
import defpackage.tvz;
import defpackage.twc;
import defpackage.twf;

/* loaded from: classes7.dex */
public class EcKeyPair {
    public byte[] privateKey;
    public byte[] publicKey;

    public static final class Builder {

        @KfsNotNull
        private byte[] privateKey;

        @KfsNotNull
        private byte[] publicKey;

        public Builder publicKey(byte[] bArr) {
            this.publicKey = bArr;
            return this;
        }

        public Builder privateKey(byte[] bArr) {
            this.privateKey = bArr;
            return this;
        }

        public EcKeyPair build() throws twc {
            try {
                tue.d(this);
                return new EcKeyPair(this);
            } catch (ttr e) {
                StringBuilder e2 = twf.e("EcKeyPair build check param error : ");
                e2.append(e.getMessage());
                throw new tvz(e2.toString());
            }
        }

        private Builder() {
        }
    }

    public byte[] getPublicKey() {
        return this.publicKey;
    }

    public byte[] getPrivateKey() {
        return this.privateKey;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private EcKeyPair(Builder builder) {
        this.publicKey = builder.publicKey;
        this.privateKey = builder.privateKey;
    }
}
