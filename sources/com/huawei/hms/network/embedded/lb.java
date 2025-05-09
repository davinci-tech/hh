package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public final class lb extends gb {

    @Nullable
    public final MessageDigest b;

    @Nullable
    public final Mac c;

    @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) throws IOException {
        cc.a(bbVar.b, 0L, j);
        vb vbVar = bbVar.f5191a;
        long j2 = 0;
        while (j2 < j) {
            int min = (int) Math.min(j - j2, vbVar.c - vbVar.b);
            MessageDigest messageDigest = this.b;
            if (messageDigest != null) {
                messageDigest.update(vbVar.f5550a, vbVar.b, min);
            } else {
                this.c.update(vbVar.f5550a, vbVar.b, min);
            }
            j2 += min;
            vbVar = vbVar.f;
        }
        super.write(bbVar, j);
    }

    public final eb f() {
        MessageDigest messageDigest = this.b;
        return eb.e(messageDigest != null ? messageDigest.digest() : this.c.doFinal());
    }

    public static lb d(yb ybVar) {
        return new lb(ybVar, "SHA-512");
    }

    public static lb c(yb ybVar, eb ebVar) {
        return new lb(ybVar, ebVar, "HmacSHA512");
    }

    public static lb c(yb ybVar) {
        return new lb(ybVar, "SHA-256");
    }

    public static lb b(yb ybVar, eb ebVar) {
        return new lb(ybVar, ebVar, "HmacSHA256");
    }

    public static lb b(yb ybVar) {
        return new lb(ybVar, "SHA-1");
    }

    public static lb a(yb ybVar, eb ebVar) {
        return new lb(ybVar, ebVar, "HmacSHA1");
    }

    public static lb a(yb ybVar) {
        return new lb(ybVar, "MD5");
    }

    public lb(yb ybVar, String str) {
        super(ybVar);
        try {
            this.b = MessageDigest.getInstance(str);
            this.c = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public lb(yb ybVar, eb ebVar, String str) {
        super(ybVar);
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
