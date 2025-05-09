package com.huawei.hms.scankit.p;

import java.security.SecureRandom;

/* loaded from: classes9.dex */
public class n6 {

    /* renamed from: a, reason: collision with root package name */
    private static final SecureRandom f5838a = new SecureRandom();

    public static int a(int i) {
        return f5838a.nextInt(i);
    }

    public static float a(float f) {
        return f5838a.nextFloat() * f;
    }
}
