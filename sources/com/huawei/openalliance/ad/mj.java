package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.ImpressionType;

/* loaded from: classes5.dex */
public enum mj {
    DEFINED_BY_JAVASCRIPT("definedByJavaScript"),
    UNSPECIFIED("unspecified"),
    LOADED("loaded"),
    BEGIN_TO_RENDER("beginToRender"),
    ONE_PIXEL("onePixel"),
    VIEWABLE("viewable"),
    AUDIBLE("audible"),
    OTHER("other");

    private static boolean i;
    private final String j;

    @Override // java.lang.Enum
    public String toString() {
        return this.j;
    }

    public static boolean a() {
        return i;
    }

    public static ImpressionType a(mj mjVar) {
        if (!i) {
            return null;
        }
        switch (AnonymousClass1.f7263a[mjVar.ordinal()]) {
            case 1:
                return ImpressionType.DEFINED_BY_JAVASCRIPT;
            case 2:
                return ImpressionType.UNSPECIFIED;
            case 3:
                return ImpressionType.LOADED;
            case 4:
                return ImpressionType.BEGIN_TO_RENDER;
            case 5:
                return ImpressionType.ONE_PIXEL;
            case 6:
                return ImpressionType.VIEWABLE;
            case 7:
                return ImpressionType.AUDIBLE;
            case 8:
                return ImpressionType.OTHER;
            default:
                return null;
        }
    }

    /* renamed from: com.huawei.openalliance.ad.mj$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7263a;

        static {
            int[] iArr = new int[mj.values().length];
            f7263a = iArr;
            try {
                iArr[mj.DEFINED_BY_JAVASCRIPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7263a[mj.UNSPECIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7263a[mj.LOADED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7263a[mj.BEGIN_TO_RENDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7263a[mj.ONE_PIXEL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7263a[mj.VIEWABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7263a[mj.AUDIBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7263a[mj.OTHER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    mj(String str) {
        this.j = str;
    }

    static {
        i = false;
        i = ma.a("com.iab.omid.library.huawei.adsession.ImpressionType");
    }
}
