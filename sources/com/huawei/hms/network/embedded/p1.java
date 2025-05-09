package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public final class p1 {
    public static final int m = -1;
    public static final boolean n = false;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f5407a;
    public final boolean b;
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;

    public int sMaxAgeSeconds() {
        return this.d;
    }

    public boolean onlyIfCached() {
        return this.j;
    }

    public boolean noTransform() {
        return this.k;
    }

    public boolean noStore() {
        return this.b;
    }

    public boolean noCache() {
        return this.f5407a;
    }

    public boolean mustRevalidate() {
        return this.g;
    }

    public int minFreshSeconds() {
        return this.i;
    }

    public int maxStaleSeconds() {
        return this.h;
    }

    public int maxAgeSeconds() {
        return this.c;
    }

    public boolean isPublic() {
        return this.f;
    }

    public boolean isPrivate() {
        return this.e;
    }

    public boolean immutable() {
        return this.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.embedded.p1 parse(com.huawei.hms.network.base.common.Headers r31) {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.p1.parse(com.huawei.hms.network.base.common.Headers):com.huawei.hms.network.embedded.p1");
    }

    public p1(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8) {
        this.f5407a = z;
        this.b = z2;
        this.e = z3;
        this.c = i;
        this.d = i2;
        this.f = z4;
        this.k = z7;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.l = z8;
        this.j = z6;
    }
}
