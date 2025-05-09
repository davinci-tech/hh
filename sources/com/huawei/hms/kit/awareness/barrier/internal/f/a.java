package com.huawei.hms.kit.awareness.barrier.internal.f;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4881a = "HashUtils";
    private static final String b = "SHA-256";

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4881a, "Generate hashValue Error:" + e.getMessage(), new Object[0]);
            return null;
        }
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static int a(Collection<com.huawei.hms.kit.awareness.a.a.b> collection) {
        Iterator<com.huawei.hms.kit.awareness.a.a.b> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().e() ? 1 : 0;
        }
        return i;
    }
}
