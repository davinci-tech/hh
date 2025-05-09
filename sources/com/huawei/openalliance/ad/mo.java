package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.media.InteractionType;

/* loaded from: classes5.dex */
public enum mo {
    CLICK("click"),
    INVITATION_ACCEPTED("invitationAccept");

    private static boolean d;
    String c;

    @Override // java.lang.Enum
    public String toString() {
        return this.c;
    }

    public static boolean a() {
        return d;
    }

    public static InteractionType a(mo moVar) {
        if (!d) {
            return null;
        }
        int i = AnonymousClass1.f7268a[moVar.ordinal()];
        if (i == 1) {
            return InteractionType.CLICK;
        }
        if (i != 2) {
            return null;
        }
        return InteractionType.INVITATION_ACCEPTED;
    }

    /* renamed from: com.huawei.openalliance.ad.mo$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7268a;

        static {
            int[] iArr = new int[mo.values().length];
            f7268a = iArr;
            try {
                iArr[mo.CLICK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7268a[mo.INVITATION_ACCEPTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    mo(String str) {
        this.c = str;
    }

    static {
        d = false;
        d = ma.a("com.iab.omid.library.huawei.adsession.media.InteractionType");
    }
}
