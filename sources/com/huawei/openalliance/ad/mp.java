package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.media.Position;

/* loaded from: classes5.dex */
public enum mp {
    PREROLL("preroll"),
    MIDROLL("midroll"),
    POSTROLL("postroll"),
    STANDALONE("standalone");

    private static boolean e;
    private final String f;

    @Override // java.lang.Enum
    public String toString() {
        return this.f;
    }

    public static Position a(mp mpVar) {
        if (!e) {
            return null;
        }
        int i = AnonymousClass1.f7270a[mpVar.ordinal()];
        if (i == 1 || i == 2) {
            return Position.PREROLL;
        }
        if (i == 3) {
            return Position.POSTROLL;
        }
        if (i != 4) {
            return null;
        }
        return Position.STANDALONE;
    }

    /* renamed from: com.huawei.openalliance.ad.mp$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7270a;

        static {
            int[] iArr = new int[mp.values().length];
            f7270a = iArr;
            try {
                iArr[mp.PREROLL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7270a[mp.MIDROLL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7270a[mp.POSTROLL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7270a[mp.STANDALONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    mp(String str) {
        this.f = str;
    }

    static {
        e = false;
        e = ma.a("com.iab.omid.library.huawei.adsession.media.Position");
    }
}
