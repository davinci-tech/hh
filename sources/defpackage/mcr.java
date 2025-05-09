package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.security.SecureRandom;

/* loaded from: classes6.dex */
public final class mcr {

    /* renamed from: a, reason: collision with root package name */
    private static SecureRandom f14884a = EncryptUtil.genSecureRandom();

    public static String e(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            if (SequenceDetailFieldConfig.CHAR.equalsIgnoreCase(f14884a.nextInt(2) % 2 == 0 ? SequenceDetailFieldConfig.CHAR : "num")) {
                sb.append((char) (f14884a.nextInt(26) + (f14884a.nextInt(2) % 2 == 0 ? 65 : 97)));
            } else {
                sb.append(String.valueOf(f14884a.nextInt(10)));
            }
        }
        return sb.toString();
    }
}
