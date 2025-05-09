package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.FriendlyObstructionPurpose;

/* loaded from: classes5.dex */
public enum mh {
    VIDEO_CONTROLS,
    CLOSE_AD,
    NOT_VISIBLE,
    OTHER;

    private static boolean e;

    public static boolean a() {
        return e;
    }

    public static FriendlyObstructionPurpose a(mh mhVar) {
        if (!e) {
            return null;
        }
        int i = AnonymousClass1.f7261a[mhVar.ordinal()];
        if (i == 1) {
            return FriendlyObstructionPurpose.VIDEO_CONTROLS;
        }
        if (i == 2) {
            return FriendlyObstructionPurpose.CLOSE_AD;
        }
        if (i == 3) {
            return FriendlyObstructionPurpose.NOT_VISIBLE;
        }
        if (i != 4) {
            return null;
        }
        return FriendlyObstructionPurpose.OTHER;
    }

    /* renamed from: com.huawei.openalliance.ad.mh$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7261a;

        static {
            int[] iArr = new int[mh.values().length];
            f7261a = iArr;
            try {
                iArr[mh.VIDEO_CONTROLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7261a[mh.CLOSE_AD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7261a[mh.NOT_VISIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7261a[mh.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        e = false;
        e = ma.a("com.iab.omid.library.huawei.adsession.FriendlyObstructionPurpose");
    }
}
