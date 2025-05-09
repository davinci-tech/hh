package com.huawei.hms.scankit.p;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes9.dex */
abstract class g1 {

    /* renamed from: a, reason: collision with root package name */
    public static final g1 f5780a;
    public static final g1 b;
    public static final g1 c;
    public static final g1 d;
    public static final g1 e;
    public static final g1 f;
    public static final g1 g;
    public static final g1 h;
    private static final /* synthetic */ g1[] i;

    enum a extends g1 {
        a(String str, int i) {
            super(str, i, null);
        }

        @Override // com.huawei.hms.scankit.p.g1
        boolean a(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }
    }

    static {
        a aVar = new a("DATA_MASK_000", 0);
        f5780a = aVar;
        g1 g1Var = new g1("DATA_MASK_001", 1) { // from class: com.huawei.hms.scankit.p.g1.b
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return (i2 & 1) == 0;
            }
        };
        b = g1Var;
        g1 g1Var2 = new g1("DATA_MASK_010", 2) { // from class: com.huawei.hms.scankit.p.g1.c
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return i3 % 3 == 0;
            }
        };
        c = g1Var2;
        g1 g1Var3 = new g1("DATA_MASK_011", 3) { // from class: com.huawei.hms.scankit.p.g1.d
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return (i2 + i3) % 3 == 0;
            }
        };
        d = g1Var3;
        g1 g1Var4 = new g1("DATA_MASK_100", 4) { // from class: com.huawei.hms.scankit.p.g1.e
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return (((i2 / 2) + (i3 / 3)) & 1) == 0;
            }
        };
        e = g1Var4;
        g1 g1Var5 = new g1("DATA_MASK_101", 5) { // from class: com.huawei.hms.scankit.p.g1.f
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return (i2 * i3) % 6 == 0;
            }
        };
        f = g1Var5;
        g1 g1Var6 = new g1("DATA_MASK_110", 6) { // from class: com.huawei.hms.scankit.p.g1.g
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return (i2 * i3) % 6 < 3;
            }
        };
        g = g1Var6;
        g1 g1Var7 = new g1("DATA_MASK_111", 7) { // from class: com.huawei.hms.scankit.p.g1.h
            {
                a aVar2 = null;
            }

            @Override // com.huawei.hms.scankit.p.g1
            boolean a(int i2, int i3) {
                return (((i2 + i3) + ((i2 * i3) % 3)) & 1) == 0;
            }
        };
        h = g1Var7;
        i = new g1[]{aVar, g1Var, g1Var2, g1Var3, g1Var4, g1Var5, g1Var6, g1Var7};
    }

    private g1(String str, int i2) {
    }

    public static g1 valueOf(String str) {
        return (g1) Enum.valueOf(g1.class, str);
    }

    public static g1[] values() {
        return (g1[]) i.clone();
    }

    final void a(s sVar, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (a(i3, i4)) {
                    sVar.a(i4, i3);
                }
            }
        }
    }

    abstract boolean a(int i2, int i3);

    /* synthetic */ g1(String str, int i2, a aVar) {
        this(str, i2);
    }
}
