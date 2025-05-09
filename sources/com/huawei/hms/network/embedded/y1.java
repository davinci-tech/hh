package com.huawei.hms.network.embedded;

import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import java.io.IOException;

/* loaded from: classes9.dex */
public class y1 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5579a = "Secure";

    public static byte[] encryptBody(byte[] bArr) throws IOException {
        byte[] encrypt = AesGcm.encrypt(bArr, w1.a(true));
        if (encrypt == null || encrypt.length == 0) {
            throw new a("Encrypt body failed");
        }
        return encrypt;
    }

    public static class a extends IOException {
        public a(String str) {
            super(str);
        }
    }

    public static class b extends IOException {
        public b(String str) {
            super(str);
        }
    }

    public static byte[] decryptBody(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            throw new a("Decrypt body is empty");
        }
        try {
            byte[] decrypt = AesGcm.decrypt(bArr, w1.a(false));
            if (decrypt == null || decrypt.length == 0) {
                throw new a("Decrypt body failed");
            }
            return decrypt;
        } catch (IndexOutOfBoundsException unused) {
            throw new a("The cached file is tampered, throw a indexOutOfBoundsException");
        }
    }
}
