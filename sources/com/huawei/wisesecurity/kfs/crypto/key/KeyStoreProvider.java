package com.huawei.wisesecurity.kfs.crypto.key;

/* loaded from: classes7.dex */
public enum KeyStoreProvider {
    ANDROID_KEYSTORE("AndroidKeyStore", "AndroidKeyStore"),
    HUAWEI_KEYSTORE("HwKeystore", "HwUniversalKeyStoreProvider");

    private final String name;
    private final String providerName;

    KeyStoreProvider(String str, String str2) {
        this.name = str;
        this.providerName = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getProviderName() {
        return this.providerName;
    }
}
