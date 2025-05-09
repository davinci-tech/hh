package com.huawei.wisesecurity.ucs.credential.util;

import defpackage.tua;

/* loaded from: classes9.dex */
public class SecureRandomUtil {
    private static final String TAG = "SecureRandomUtil";

    public static byte[] generateRandomBytes(int i) {
        return tua.e(i);
    }
}
