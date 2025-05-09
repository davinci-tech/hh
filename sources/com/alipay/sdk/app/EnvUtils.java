package com.alipay.sdk.app;

/* loaded from: classes7.dex */
public class EnvUtils {

    /* renamed from: a, reason: collision with root package name */
    public static EnvEnum f854a = EnvEnum.ONLINE;

    public enum EnvEnum {
        ONLINE,
        PRE_SANDBOX,
        SANDBOX
    }

    public static boolean b() {
        return d() || e();
    }

    public static boolean d() {
        return f854a == EnvEnum.PRE_SANDBOX;
    }

    public static boolean e() {
        return f854a == EnvEnum.SANDBOX;
    }
}
