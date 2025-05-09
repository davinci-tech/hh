package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    public static final int f5551a = 600000;
    public static final int b = 60000;
    public static final int c = 86400000;
    public static final int d = 128;
    public static final int e = 5;
    public static final int f = -1;
    public static int g = 2;
    public static int h = 0;
    public static long i = 600000;
    public static final String j = "Default";
    public static final String k = "LocalDns";
    public static final String l = "DNKeeper";
    public static final String m = "HttpDns";

    /* loaded from: classes2.dex */
    public @interface a {
        public static final int k = -1;
        public static final int l = 0;
        public static final int m = 1;
    }

    /* loaded from: classes2.dex */
    public @interface b {
        public static final int n = 0;
        public static final int o = 1;
    }

    /* loaded from: classes2.dex */
    public @interface c {
        public static final int p = 0;
        public static final int q = 1;
        public static final int r = 2;
    }

    public static int c() {
        return g;
    }

    public static void b(int i2) {
        g = i2;
    }

    public static int b() {
        return h;
    }

    public static void a(long j2) {
        i = j2;
    }

    public static void a(int i2) {
        h = i2;
    }

    public static long a() {
        return i;
    }
}
