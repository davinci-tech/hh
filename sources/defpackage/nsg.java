package defpackage;

import health.compact.a.ReleaseLogUtil;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* loaded from: classes.dex */
public class nsg {
    public static SecureRandom b() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            ReleaseLogUtil.c("R_SecureRandomUtils", "getSecureRandom exception ", e);
            return new SecureRandom();
        }
    }

    public static int b(int i) {
        if (i <= 0) {
            ReleaseLogUtil.a("R_SecureRandomUtils", "getSecureRandomForInt bound ", Integer.valueOf(i));
            return 0;
        }
        return b().nextInt(i);
    }
}
