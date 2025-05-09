package defpackage;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* loaded from: classes7.dex */
public class svy {
    public static SecureRandom e() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException unused) {
            stq.c("SecureCommonUtil", "can't get strong secureRandom", false);
            try {
                return SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException unused2) {
                stq.c("SecureCommonUtil", "can't get SHA1PRNG secureRandom", false);
                return new SecureRandom();
            }
        }
    }
}
