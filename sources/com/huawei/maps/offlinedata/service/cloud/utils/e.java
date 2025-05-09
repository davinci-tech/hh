package com.huawei.maps.offlinedata.service.cloud.utils;

import com.huawei.maps.offlinedata.utils.g;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* loaded from: classes5.dex */
public class e {
    public static SecureRandom a() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException unused) {
            g.d("SecureRandomFactory", "getInstanceStrong  NoSuchAlgorithmException");
            return new SecureRandom();
        }
    }
}
