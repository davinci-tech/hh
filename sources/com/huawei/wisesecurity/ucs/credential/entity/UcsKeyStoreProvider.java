package com.huawei.wisesecurity.ucs.credential.entity;

/* loaded from: classes7.dex */
public enum UcsKeyStoreProvider {
    ANDROID_KEYSTORE("AndroidKeyStore"),
    HUAWEI_KEYSTORE("HwKeystore");

    private final String name;

    public String getName() {
        return this.name;
    }

    UcsKeyStoreProvider(String str) {
        this.name = str;
    }
}
