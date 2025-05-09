package com.huawei.openalliance.ad;

import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import com.iab.omid.library.huawei.adsession.CreativeType;

/* loaded from: classes5.dex */
public enum mg {
    DEFINED_BY_JAVASCRIPT("definedByJavaScript"),
    HTML_DISPLAY("htmlDisplay"),
    NATIVE_DISPLAY("nativeDisplay"),
    VIDEO("video"),
    AUDIO(PresenterUtils.AUDIO);

    private static boolean f;
    private final String g;

    @Override // java.lang.Enum
    public String toString() {
        return this.g;
    }

    public static boolean a() {
        return f;
    }

    public static CreativeType a(mg mgVar) {
        if (!f) {
            return null;
        }
        int i = AnonymousClass1.f7259a[mgVar.ordinal()];
        if (i == 1) {
            return CreativeType.DEFINED_BY_JAVASCRIPT;
        }
        if (i == 2) {
            return CreativeType.HTML_DISPLAY;
        }
        if (i == 3) {
            return CreativeType.NATIVE_DISPLAY;
        }
        if (i == 4) {
            return CreativeType.VIDEO;
        }
        if (i != 5) {
            return null;
        }
        return CreativeType.AUDIO;
    }

    /* renamed from: com.huawei.openalliance.ad.mg$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7259a;

        static {
            int[] iArr = new int[mg.values().length];
            f7259a = iArr;
            try {
                iArr[mg.DEFINED_BY_JAVASCRIPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7259a[mg.HTML_DISPLAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7259a[mg.NATIVE_DISPLAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7259a[mg.VIDEO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7259a[mg.AUDIO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    mg(String str) {
        this.g = str;
    }

    static {
        f = false;
        f = ma.a("com.iab.omid.library.huawei.adsession.CreativeType");
    }
}
