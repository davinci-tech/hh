package com.huawei.agconnect.common.api;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.security.SecureRandom;

/* loaded from: classes2.dex */
public class RandomWrapper {
    public static byte[] generateSecureRandom(int i) {
        EncryptUtil.setBouncycastleFlag(true);
        byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(i);
        if (generateSecureRandom.length != 0) {
            return generateSecureRandom;
        }
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}
