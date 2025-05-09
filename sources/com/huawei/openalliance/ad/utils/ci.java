package com.huawei.openalliance.ad.utils;

import java.security.SecureRandom;
import java.util.Random;

/* loaded from: classes5.dex */
public class ci {
    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static int a(int i, int i2) {
        if (i == i2) {
            return i2;
        }
        if (i2 > i) {
            i2 = i;
            i = i2;
        }
        return new SecureRandom().nextInt((i - i2) + 1) + i2;
    }
}
