package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.Constants;
import com.iab.omid.library.huawei.adsession.Owner;

/* loaded from: classes5.dex */
public enum mk {
    NATIVE(Constants.NATIVE_CACHE),
    JAVASCRIPT("javascript"),
    NONE("none");

    private static boolean d;
    private final String e;

    @Override // java.lang.Enum
    public String toString() {
        return this.e;
    }

    public static boolean a() {
        return d;
    }

    public static Owner a(mk mkVar) {
        if (!d) {
            return null;
        }
        int i = AnonymousClass1.f7265a[mkVar.ordinal()];
        if (i == 1) {
            return Owner.NATIVE;
        }
        if (i == 2) {
            return Owner.JAVASCRIPT;
        }
        if (i != 3) {
            return null;
        }
        return Owner.NONE;
    }

    /* renamed from: com.huawei.openalliance.ad.mk$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7265a;

        static {
            int[] iArr = new int[mk.values().length];
            f7265a = iArr;
            try {
                iArr[mk.NATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7265a[mk.JAVASCRIPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7265a[mk.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    mk(String str) {
        this.e = str;
    }

    static {
        d = false;
        d = ma.a("com.iab.omid.library.huawei.adsession.Owner");
    }
}
