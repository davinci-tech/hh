package com.huawei.hms.ads.uiengineloader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes4.dex */
public abstract class ai {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4373a = "Sha256Util";

    public static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            af.d(f4373a, "sha256 NoSuchAlgorithmException");
            return new byte[0];
        }
    }
}
