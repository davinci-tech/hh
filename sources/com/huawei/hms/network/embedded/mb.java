package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public final class mb extends hb {
    public final MessageDigest b;
    public final Mac c;

    @Override // com.huawei.hms.network.embedded.hb, com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j) throws IOException {
        long read = super.read(bbVar, j);
        if (read != -1) {
            long j2 = bbVar.b;
            long j3 = j2 - read;
            vb vbVar = bbVar.f5191a;
            while (j2 > j3) {
                vbVar = vbVar.g;
                j2 -= vbVar.c - vbVar.b;
            }
            while (j2 < bbVar.b) {
                int i = (int) ((vbVar.b + j3) - j2);
                MessageDigest messageDigest = this.b;
                if (messageDigest != null) {
                    messageDigest.update(vbVar.f5550a, i, vbVar.c - i);
                } else {
                    this.c.update(vbVar.f5550a, i, vbVar.c - i);
                }
                j3 = (vbVar.c - vbVar.b) + j2;
                vbVar = vbVar.f;
                j2 = j3;
            }
        }
        return read;
    }

    public final eb c() {
        MessageDigest messageDigest = this.b;
        return eb.e(messageDigest != null ? messageDigest.digest() : this.c.doFinal());
    }

    public static mb c(zb zbVar) {
        return new mb(zbVar, "SHA-256");
    }

    public static mb b(zb zbVar, eb ebVar) {
        return new mb(zbVar, ebVar, "HmacSHA256");
    }

    public static mb b(zb zbVar) {
        return new mb(zbVar, "SHA-1");
    }

    public static mb a(zb zbVar, eb ebVar) {
        return new mb(zbVar, ebVar, "HmacSHA1");
    }

    public static mb a(zb zbVar) {
        return new mb(zbVar, "MD5");
    }

    public mb(zb zbVar, String str) {
        super(zbVar);
        try {
            this.b = MessageDigest.getInstance(str);
            this.c = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public mb(zb zbVar, eb ebVar, String str) {
        super(zbVar);
        try {
            Mac mac = Mac.getInstance(str);
            this.c = mac;
            mac.init(new SecretKeySpec(ebVar.m(), str));
            this.b = null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }
}
