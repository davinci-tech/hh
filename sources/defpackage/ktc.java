package defpackage;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.security.SecureRandom;
import java.util.UUID;

/* loaded from: classes5.dex */
public final class ktc {
    public static SecureRandom a() {
        ksy.b("SecureRandomFactory", "getSecureRandom", true);
        return EncryptUtil.genSecureRandom();
    }

    public static String c() {
        try {
            byte[] bArr = new byte[18];
            EncryptUtil.genSecureRandom().nextBytes(bArr);
            return ksl.a(bArr);
        } catch (Exception e) {
            ksy.c("SecureRandomFactory", "Exception:" + e.getClass().getSimpleName(), true);
            return UUID.randomUUID().toString();
        }
    }
}
