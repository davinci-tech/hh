package com.huawei.hms.framework.common;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* loaded from: classes9.dex */
public class SecurityRandomHelper {
    public static volatile SecurityRandomHelper instance;

    public static SecurityRandomHelper getInstance() {
        if (instance == null) {
            synchronized (SecurityRandomHelper.class) {
                if (instance == null) {
                    EncryptUtil.setBouncycastleFlag(true);
                    instance = new SecurityRandomHelper();
                }
            }
        }
        return instance;
    }

    private SecurityRandomHelper() {
    }

    public byte[] generateSecureRandom(int i) {
        return EncryptUtil.generateSecureRandom(i);
    }

    public String generateSecureRandomStr(int i) {
        return EncryptUtil.generateSecureRandomStr(i);
    }
}
