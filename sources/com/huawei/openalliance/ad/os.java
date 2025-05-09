package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public abstract class os {
    public static int z(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 24);
        if (a2 != null) {
            return a2.intValue();
        }
        return 0;
    }

    public static boolean y(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 23);
        return a2 != null && a2.intValue() == 2;
    }

    public static boolean x(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 23);
        return a2 != null && a2.intValue() == 1;
    }

    public static boolean w(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 23);
        return a2 == null || a2.intValue() == 0;
    }

    public static boolean v(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 22);
        return a2 != null && 1 == a2.intValue();
    }

    public static boolean u(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 21);
        return a2 != null && a2.intValue() == 0;
    }

    public static int t(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 20);
        if (a2 != null) {
            return a2.intValue();
        }
        return 1;
    }

    public static boolean s(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 19);
        return a2 != null && 1 == a2.intValue();
    }

    public static boolean r(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 18);
        return a2 != null && 1 == a2.intValue();
    }

    public static boolean q(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 16);
        return a2 != null && 2 == a2.intValue();
    }

    public static boolean p(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 16);
        return a2 != null && a2.intValue() == 0;
    }

    public static boolean o(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 16);
        return a2 == null || 1 == a2.intValue();
    }

    public static int n(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 15);
        if (a2 != null) {
            return a2.intValue();
        }
        return 2;
    }

    public static boolean m(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 14);
        return a2 != null && 1 == a2.intValue();
    }

    public static boolean l(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 13);
        return a2 == null || 1 == a2.intValue();
    }

    public static boolean k(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 12);
        return a2 != null && 1 == a2.intValue();
    }

    public static int j(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 11);
        if (a2 != null) {
            return a2.intValue();
        }
        return 2;
    }

    public static boolean i(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 10);
        return a2 != null && 1 == a2.intValue();
    }

    public static boolean h(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 9);
        return a2 == null || 1 == a2.intValue();
    }

    public static boolean g(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 7);
        return a2 != null && 1 == a2.intValue();
    }

    public static int f(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 17);
        if (a2 == null) {
            return 2;
        }
        return a2.intValue();
    }

    public static boolean e(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 5);
        return a2 == null || 1 == a2.intValue();
    }

    public static boolean d(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 3);
        return (a2 == null || a2.intValue() == 0) ? false : true;
    }

    public static boolean c(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 0);
        return a2 == null || 1 == a2.intValue();
    }

    public static boolean b(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 2);
        return (a2 == null || a2.intValue() == 0) ? false : true;
    }

    public static boolean a(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 1);
        return a2 == null || 1 == a2.intValue();
    }

    public static boolean I(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 2, 2);
        return a2 == null || a2.intValue() == 1;
    }

    public static boolean H(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 7, 1);
        return a2 != null && a2.intValue() == 1;
    }

    public static boolean G(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 6, 1);
        return a2 != null && a2.intValue() == 1;
    }

    public static boolean F(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 4, 1);
        return a2 != null && a2.intValue() == 1;
    }

    public static int E(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 3, 1);
        if (a2 == null) {
            return 0;
        }
        return a2.intValue();
    }

    public static boolean D(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 2, 1);
        return a2 != null && a2.intValue() == 1;
    }

    public static int C(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 1, 1);
        if (a2 != null) {
            return a2.intValue();
        }
        return 0;
    }

    public static int B(String str) {
        if (v(str)) {
            return 3;
        }
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 0, 2);
        if (a2 == null || !(a2.intValue() == 1 || a2.intValue() == 2)) {
            return 0;
        }
        return a2.intValue();
    }

    public static boolean A(String str) {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(str, 0, 2);
        return a2 != null && 1 == a2.intValue();
    }
}
