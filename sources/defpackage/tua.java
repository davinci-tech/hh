package defpackage;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.security.SecureRandom;

/* loaded from: classes7.dex */
public final class tua {
    public static SecureRandom a() {
        EncryptUtil.setBouncycastleFlag(true);
        return EncryptUtil.genSecureRandom();
    }

    public static byte[] e(int i) {
        EncryptUtil.setBouncycastleFlag(true);
        return EncryptUtil.generateSecureRandom(i);
    }
}
