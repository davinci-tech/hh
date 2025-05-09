package com.huawei.hms.network.file.a.k.b.j;

import com.huawei.hms.network.embedded.y1;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import java.io.IOException;

/* loaded from: classes4.dex */
public class b {
    public static byte[] b(byte[] bArr) {
        byte[] encrypt = AesGcm.encrypt(bArr, com.huawei.hms.network.file.a.k.b.j.a.a(true));
        if (encrypt == null || encrypt.length == 0) {
            throw new a("Encrypt body failed");
        }
        return encrypt;
    }

    public static class a extends IOException {
        a(String str) {
            super(str);
        }
    }

    /* renamed from: com.huawei.hms.network.file.a.k.b.j.b$b, reason: collision with other inner class name */
    public static class C0146b extends IOException {
        public C0146b(String str) {
            super(str);
        }
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new a("Decrypt body is empty");
        }
        try {
            byte[] decrypt = AesGcm.decrypt(bArr, com.huawei.hms.network.file.a.k.b.j.a.a(false));
            if (decrypt == null || decrypt.length == 0) {
                throw new a("Decrypt body failed");
            }
            return decrypt;
        } catch (IndexOutOfBoundsException unused) {
            throw new a("The cached file is tampered, throw a indexOutOfBoundsException");
        }
    }

    public static void a() {
        try {
            a(b("encodeString".getBytes("UTF-8")));
        } catch (IOException e) {
            FLogger.w(y1.f5579a, "initAesGcm fail", e);
        }
    }
}
