package com.huawei.hms.kit.awareness.barrier.internal.type;

/* loaded from: classes9.dex */
public enum g {
    ENTERING,
    EXITING,
    IN;

    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 4;

    public static g a(int i) {
        return i != 1 ? i != 2 ? IN : EXITING : ENTERING;
    }

    public static int a(g gVar) {
        int i = AnonymousClass1.f4892a[gVar.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 4;
            }
        }
        return i2;
    }

    /* renamed from: com.huawei.hms.kit.awareness.barrier.internal.type.g$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4892a;

        static {
            int[] iArr = new int[g.values().length];
            f4892a = iArr;
            try {
                iArr[g.ENTERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4892a[g.EXITING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
