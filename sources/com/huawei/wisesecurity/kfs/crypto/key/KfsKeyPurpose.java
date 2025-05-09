package com.huawei.wisesecurity.kfs.crypto.key;

/* loaded from: classes7.dex */
public enum KfsKeyPurpose {
    PURPOSE_CRYPTO(3),
    PURPOSE_SIGN(12),
    PURPOSE_ALL(15);

    private final int value;

    KfsKeyPurpose(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static boolean containsPurpose(KfsKeyPurpose kfsKeyPurpose, KfsKeyPurpose kfsKeyPurpose2) {
        int i = kfsKeyPurpose.value;
        int i2 = kfsKeyPurpose2.value;
        return (i & i2) == i2;
    }
}
