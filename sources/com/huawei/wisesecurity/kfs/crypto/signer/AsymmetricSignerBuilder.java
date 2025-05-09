package com.huawei.wisesecurity.kfs.crypto.signer;

import com.huawei.wisesecurity.kfs.crypto.AsymmetricBuilder;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;

/* loaded from: classes7.dex */
public abstract class AsymmetricSignerBuilder<T extends KfsSigner> extends AsymmetricBuilder<T> {
    protected SignAlg signAlg;

    public AsymmetricSignerBuilder(KeyStoreProvider keyStoreProvider) {
        super(keyStoreProvider);
    }

    public AsymmetricSignerBuilder<T> withAlg(SignAlg signAlg) {
        this.signAlg = signAlg;
        return this;
    }
}
