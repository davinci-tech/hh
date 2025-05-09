package com.alipay.sdk.m.u;

/* loaded from: classes7.dex */
public enum g {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NETWORK_TYPE_20(20, "5G"),
    NONE(-1, "none");


    /* renamed from: a, reason: collision with root package name */
    public int f872a;
    public String b;

    g(int i, String str) {
        this.f872a = i;
        this.b = str;
    }

    public final int a() {
        return this.f872a;
    }

    public final String b() {
        return this.b;
    }

    public static g a(int i) {
        for (g gVar : values()) {
            if (gVar.a() == i) {
                return gVar;
            }
        }
        return NONE;
    }
}
