package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes9.dex */
public final class a5 implements o6 {
    public static boolean a(s6 s6Var, float f, float f2) {
        double abs = Math.abs(s6Var.j()[0].b() - s6Var.j()[1].b()) / f;
        return (abs < 0.55d && ((double) f2) > 1.5d) || abs < 0.3d;
    }

    public s6 b(p pVar, p pVar2, p pVar3, Map<l1, ?> map, m4 m4Var, i2 i2Var) throws a {
        return pVar3 != null ? b(pVar3, m4Var, map, i2Var) : a(pVar, pVar2, m4Var, map);
    }

    public s6 c(p pVar, m4 m4Var, Map<l1, ?> map, i2 i2Var) throws a {
        s6 s6Var;
        int e = pVar.e();
        int c = pVar.c();
        if (e >= c) {
            e = c;
        }
        float f = (e * 1.0f) / 500.0f;
        p g = m4Var.g(pVar, f);
        try {
            s6Var = a(g, a(map), map);
            if (s6Var != null) {
                try {
                    if (s6Var.k() != null) {
                        k2.a(s6Var.j(), f, i2Var);
                        return s6Var;
                    }
                } catch (a unused) {
                    try {
                        g.a(n1.a(g.d(), g.e(), g.c(), true));
                        s6 a2 = a(g, a(map), map);
                        if (a2 == null || a2.k() == null) {
                            throw a.a();
                        }
                        k2.a(a2.j(), f, i2Var);
                        return a2;
                    } catch (a unused2) {
                        if (s6Var != null) {
                            return s6Var;
                        }
                        throw a.a();
                    }
                }
            }
            throw a.a();
        } catch (a unused3) {
            s6Var = null;
        }
    }

    @Override // com.huawei.hms.scankit.p.o6
    public s6 a(p pVar, Map<l1, ?> map) throws a {
        return a(pVar, a(map), map);
    }

    public o6[] a(Map<l1, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(l1.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.UPC_E) || collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.CODABAR) || collection.contains(BarcodeFormat.CODE_39) || collection.contains(BarcodeFormat.CODE_93) || collection.contains(BarcodeFormat.CODE_128) || collection.contains(BarcodeFormat.ITF)) {
                arrayList.add(new z4(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new j6());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new h1());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new h());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new r5());
            }
            if (collection.contains(BarcodeFormat.HARMONY_CODE)) {
                arrayList.add(new z3());
            }
            if (collection.contains(BarcodeFormat.WXCODE)) {
                arrayList.add(new m8());
            }
        }
        return (o6[]) arrayList.toArray(new o6[arrayList.size()]);
    }

    public s6 b(p pVar, m4 m4Var, Map<l1, ?> map, i2 i2Var) throws a {
        int e = pVar.e();
        int c = pVar.c();
        int i = e < c ? e : c;
        float f = i * 1.0f;
        float f2 = f / 128.0f;
        if (f2 < 1.0f && r3.c) {
            pVar = m4Var.e(pVar, f2);
        }
        p pVar2 = pVar;
        float f3 = f / 500.0f;
        float f4 = f3 >= 1.0f ? f3 : 1.0f;
        try {
            s6 a2 = a(m4Var.g(pVar2, f4), a(map), map);
            if (a2 != null && a2.k() != null) {
                k2.a(a2.j(), f4, i2Var);
                return a2;
            }
            if (!r3.c && a2 != null && a2.k() == null && a2.j().length >= 3) {
            }
            throw a.a();
        } catch (a unused) {
            s6 a3 = a(i, pVar2, m4Var, map, i2Var);
            if (a3 == null) {
                throw a.a();
            }
            if (0 != 0) {
                a3.a();
                a3.b((u6[]) null);
                k2.a(a3.j(), f4, i2Var);
            }
            return a3;
        }
    }

    private s6 a(p pVar, o6[] o6VarArr, Map<l1, ?> map) throws a {
        if (o6VarArr != null) {
            for (o6 o6Var : o6VarArr) {
                try {
                    s6 a2 = o6Var.a(pVar, map);
                    if (a2 != null && a2.j() != null) {
                        int i = 0;
                        for (u6 u6Var : a2.j()) {
                            if (u6Var != null) {
                                i++;
                            }
                        }
                        if (i == 0 && a2.c() == BarcodeFormat.PDF_417) {
                            throw a.a();
                        }
                    }
                    return a2;
                } catch (a unused) {
                }
            }
        }
        throw a.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.scankit.p.s6 a(com.huawei.hms.scankit.p.p r10, com.huawei.hms.scankit.p.p r11, java.util.Map<com.huawei.hms.scankit.p.l1, java.lang.Object> r12, com.huawei.hms.scankit.p.m4 r13, com.huawei.hms.scankit.p.i2 r14) throws com.huawei.hms.scankit.p.a {
        /*
            r9 = this;
            com.huawei.hms.scankit.p.l1 r0 = com.huawei.hms.scankit.p.l1.PHOTO_MODE
            boolean r0 = r12.containsKey(r0)
            r1 = 2
            float[] r6 = new float[r1]
            r6 = {x0066: FILL_ARRAY_DATA , data: [1065353216, 0} // fill-array
            r1 = 1
            r8 = 0
            if (r11 == 0) goto L1a
            r2 = r9
            r3 = r11
            r4 = r13
            r5 = r12
            r7 = r14
            com.huawei.hms.scankit.p.s6 r11 = r2.a(r3, r4, r5, r6, r7)
            goto L31
        L1a:
            if (r0 != 0) goto L23
            boolean r11 = com.huawei.hms.scankit.p.r3.f5870a
            if (r11 != 0) goto L21
            goto L23
        L21:
            r11 = 0
            goto L31
        L23:
            com.huawei.hms.scankit.p.s6 r11 = r9.a(r10, r13, r12, r6)
            r12 = r6[r1]
            r13 = 1065353216(0x3f800000, float:1.0)
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 <= 0) goto L31
            r12 = r1
            goto L32
        L31:
            r12 = r8
        L32:
            if (r11 == 0) goto L61
            if (r12 == 0) goto L60
            com.huawei.hms.scankit.p.u6[] r12 = r11.j()
            if (r12 == 0) goto L60
        L3c:
            int r13 = r12.length
            if (r8 >= r13) goto L60
            r13 = r12[r8]
            if (r13 == 0) goto L5d
            com.huawei.hms.scankit.p.u6 r13 = new com.huawei.hms.scankit.p.u6
            r14 = r12[r8]
            float r14 = r14.c()
            int r0 = r10.c()
            int r0 = r0 - r1
            float r0 = (float) r0
            r2 = r12[r8]
            float r2 = r2.b()
            float r0 = r0 - r2
            r13.<init>(r14, r0)
            r12[r8] = r13
        L5d:
            int r8 = r8 + 1
            goto L3c
        L60:
            return r11
        L61:
            com.huawei.hms.scankit.p.a r10 = com.huawei.hms.scankit.p.a.a()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.a5.a(com.huawei.hms.scankit.p.p, com.huawei.hms.scankit.p.p, java.util.Map, com.huawei.hms.scankit.p.m4, com.huawei.hms.scankit.p.i2):com.huawei.hms.scankit.p.s6");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.scankit.p.s6 a(com.huawei.hms.scankit.p.p r10, com.huawei.hms.scankit.p.m4 r11, java.util.Map<com.huawei.hms.scankit.p.l1, java.lang.Object> r12, float[] r13, com.huawei.hms.scankit.p.i2 r14) throws com.huawei.hms.scankit.p.a {
        /*
            r9 = this;
            float r0 = r14.n()
            int r1 = r10.e()
            int r2 = r10.c()
            if (r1 >= r2) goto L13
            int r1 = r10.e()
            goto L17
        L13:
            int r1 = r10.c()
        L17:
            float r1 = (float) r1
            r2 = 1140457472(0x43fa0000, float:500.0)
            float r2 = r1 / r2
            r3 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r4 >= 0) goto L23
            r2 = r3
        L23:
            com.huawei.hms.scankit.p.p r4 = r11.g(r10, r2)
            com.huawei.hms.scankit.p.o6[] r5 = r9.a(r12)
            float r6 = r14.a()
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L3e
            float r6 = r14.b()
            float r7 = r14.a()
            float r6 = r6 / r7
            goto L3f
        L3e:
            r6 = r3
        L3f:
            com.huawei.hms.scankit.p.s6 r7 = r9.a(r4, r5, r12)     // Catch: com.huawei.hms.scankit.p.a -> L51
            float r8 = r0 / r2
            boolean r8 = a(r7, r8, r6)     // Catch: com.huawei.hms.scankit.p.a -> L52
            if (r8 != 0) goto L4c
            goto La4
        L4c:
            com.huawei.hms.scankit.p.a r7 = com.huawei.hms.scankit.p.a.a()     // Catch: com.huawei.hms.scankit.p.a -> L51
            throw r7     // Catch: com.huawei.hms.scankit.p.a -> L51
        L51:
            r7 = 0
        L52:
            boolean r8 = com.huawei.hms.scankit.p.r3.p
            if (r8 == 0) goto La4
            r2 = 1132068864(0x437a0000, float:250.0)
            float r1 = r1 / r2
            int r2 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r2 >= 0) goto L5e
            goto L5f
        L5e:
            r3 = r1
        L5f:
            com.huawei.hms.scankit.p.p r4 = r11.f(r10, r3)
            com.huawei.hms.scankit.p.l1 r10 = com.huawei.hms.scankit.p.l1.PHOTO_MODE_NUM     // Catch: com.huawei.hms.scankit.p.a -> L83
            r1 = 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: com.huawei.hms.scankit.p.a -> L83
            r12.put(r10, r1)     // Catch: com.huawei.hms.scankit.p.a -> L83
            com.huawei.hms.scankit.p.p r10 = r11.e(r4)     // Catch: com.huawei.hms.scankit.p.a -> L83
            com.huawei.hms.scankit.p.s6 r10 = r9.a(r10, r5, r12)     // Catch: com.huawei.hms.scankit.p.a -> L83
            float r1 = r0 / r3
            boolean r1 = a(r10, r1, r6)     // Catch: com.huawei.hms.scankit.p.a -> L83
            if (r1 != 0) goto L7e
            goto L9c
        L7e:
            com.huawei.hms.scankit.p.a r10 = com.huawei.hms.scankit.p.a.a()     // Catch: com.huawei.hms.scankit.p.a -> L83
            throw r10     // Catch: com.huawei.hms.scankit.p.a -> L83
        L83:
            com.huawei.hms.scankit.p.l1 r10 = com.huawei.hms.scankit.p.l1.PHOTO_MODE_NUM
            r1 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r12.put(r10, r1)
            com.huawei.hms.scankit.p.p r10 = r11.f(r4)
            com.huawei.hms.scankit.p.s6 r10 = r9.a(r10, r5, r12)
            float r0 = r0 / r3
            boolean r11 = a(r10, r0, r6)
            if (r11 != 0) goto L9f
        L9c:
            r7 = r10
            r2 = r3
            goto La4
        L9f:
            com.huawei.hms.scankit.p.a r10 = com.huawei.hms.scankit.p.a.a()
            throw r10
        La4:
            r10 = 0
            r13[r10] = r2
            if (r7 == 0) goto Lb0
            com.huawei.hms.scankit.p.s r10 = r4.b()
            com.huawei.hms.scankit.p.k2.a(r10, r7, r2, r14)
        Lb0:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.a5.a(com.huawei.hms.scankit.p.p, com.huawei.hms.scankit.p.m4, java.util.Map, float[], com.huawei.hms.scankit.p.i2):com.huawei.hms.scankit.p.s6");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
    
        r10 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.huawei.hms.scankit.p.m4] */
    /* JADX WARN: Type inference failed for: r10v10, types: [com.huawei.hms.scankit.p.s6] */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v7, types: [com.huawei.hms.scankit.p.s6] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.scankit.p.s6 a(com.huawei.hms.scankit.p.p r9, com.huawei.hms.scankit.p.m4 r10, java.util.Map<com.huawei.hms.scankit.p.l1, java.lang.Object> r11, float[] r12) throws com.huawei.hms.scankit.p.a {
        /*
            r8 = this;
            int r0 = r9.c()
            int r1 = r9.e()
            int r0 = java.lang.Math.min(r0, r1)
            float r0 = (float) r0
            r1 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 * r1
            r2 = 1149698048(0x44870000, float:1080.0)
            float r0 = r0 / r2
            boolean r2 = com.huawei.hms.scankit.p.r3.f5870a
            if (r2 == 0) goto L1c
            com.huawei.hms.scankit.p.p r9 = r10.a(r9, r0)
            goto L28
        L1c:
            r2 = 1069547520(0x3fc00000, float:1.5)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L23
            r1 = r0
        L23:
            com.huawei.hms.scankit.p.p r9 = r10.a(r9, r1)
            r0 = r1
        L28:
            com.huawei.hms.scankit.p.o6[] r1 = r8.a(r11)
            boolean r2 = com.huawei.hms.scankit.p.r3.b
            r3 = 0
            r4 = 0
            if (r2 != 0) goto L44
            boolean r2 = com.huawei.hms.scankit.p.r3.f5870a
            if (r2 == 0) goto L44
            com.huawei.hms.scankit.p.l1 r10 = com.huawei.hms.scankit.p.l1.PHOTO_MODE_NUM
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r11.put(r10, r2)
            com.huawei.hms.scankit.p.s6 r10 = r8.a(r9, r1, r11)
            goto L80
        L44:
            r2 = r3
            r5 = r4
        L46:
            r6 = 2
            if (r5 >= r6) goto L7f
            r6 = 1
            if (r5 != r6) goto L5d
            com.huawei.hms.scankit.p.p r7 = com.huawei.hms.scankit.p.l4.a(r9)
            com.huawei.hms.scankit.p.p r7 = r10.c(r7)     // Catch: com.huawei.hms.scankit.p.a -> L7c
            com.huawei.hms.scankit.p.s6 r2 = r8.a(r7, r1, r11)     // Catch: com.huawei.hms.scankit.p.a -> L7c
            r7 = 1073741824(0x40000000, float:2.0)
            r12[r6] = r7     // Catch: com.huawei.hms.scankit.p.a -> L7c
            goto L7f
        L5d:
            com.huawei.hms.scankit.p.p r7 = r10.b(r9)     // Catch: com.huawei.hms.scankit.p.a -> L66
            com.huawei.hms.scankit.p.s6 r10 = r8.a(r7, r1, r11)     // Catch: com.huawei.hms.scankit.p.a -> L66
            goto L80
        L66:
            boolean r7 = com.huawei.hms.scankit.p.r3.p     // Catch: com.huawei.hms.scankit.p.a -> L7c
            if (r7 == 0) goto L7c
            com.huawei.hms.scankit.p.l1 r7 = com.huawei.hms.scankit.p.l1.PHOTO_MODE_NUM     // Catch: com.huawei.hms.scankit.p.a -> L7c
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: com.huawei.hms.scankit.p.a -> L7c
            r11.put(r7, r6)     // Catch: com.huawei.hms.scankit.p.a -> L7c
            com.huawei.hms.scankit.p.p r6 = r10.d(r9)     // Catch: com.huawei.hms.scankit.p.a -> L7c
            com.huawei.hms.scankit.p.s6 r10 = r8.a(r6, r1, r11)     // Catch: com.huawei.hms.scankit.p.a -> L7c
            goto L80
        L7c:
            int r5 = r5 + 1
            goto L46
        L7f:
            r10 = r2
        L80:
            if (r10 == 0) goto L89
            com.huawei.hms.scankit.p.s r9 = r9.b()
            com.huawei.hms.scankit.p.k2.a(r9, r10, r0, r3)
        L89:
            r12[r4] = r0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.a5.a(com.huawei.hms.scankit.p.p, com.huawei.hms.scankit.p.m4, java.util.Map, float[]):com.huawei.hms.scankit.p.s6");
    }

    public s6 a(p pVar, p pVar2, p pVar3, Map<l1, ?> map, m4 m4Var, i2 i2Var) throws a {
        s6 a2 = pVar3 != null ? a(pVar3, m4Var, map, i2Var) : null;
        if (a2 != null) {
            return a2;
        }
        throw a.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.huawei.hms.scankit.p.u6[]] */
    private s6 a(p pVar, m4 m4Var, Map<l1, ?> map, i2 i2Var) throws a {
        boolean z;
        u6[] u6VarArr;
        s6 a2;
        int e = pVar.e();
        int c = pVar.c();
        if (e >= c) {
            e = c;
        }
        float f = (e * 1.0f) / 900.0f;
        p i = m4Var.i(pVar, f);
        s6 s6Var = null;
        try {
            s6 a3 = a(i, a(map), map);
            if (a3 != null) {
                try {
                    if (a3.k() != null) {
                        k2.a(a3.j(), f, i2Var);
                        return a3;
                    }
                } catch (a unused) {
                    z = false;
                    u6VarArr = s6Var;
                    s6Var = a3;
                    try {
                        i.a(n1.a(i.d(), i.e(), i.c(), false));
                        a2 = a(i, a(map), map);
                        if (a2 == null && a2.k() != null) {
                            k2.a(a2.j(), f, i2Var);
                            return a2;
                        }
                        throw a.a();
                    } catch (a unused2) {
                        if (s6Var == null) {
                            throw a.a();
                        }
                        if (z) {
                            s6Var.a();
                            s6Var.b(u6VarArr);
                            k2.a(s6Var.j(), f, i2Var);
                        }
                        return s6Var;
                    }
                }
            }
            if (r3.c || a3 == null || a3.k() != null || a3.j().length < 3) {
                z = false;
            } else {
                z = true;
                try {
                    s6Var = (u6[]) a3.j().clone();
                } catch (a unused3) {
                    u6VarArr = s6Var;
                    s6Var = a3;
                    i.a(n1.a(i.d(), i.e(), i.c(), false));
                    a2 = a(i, a(map), map);
                    if (a2 == null) {
                    }
                    throw a.a();
                }
            }
            throw a.a();
        } catch (a unused4) {
            z = false;
            u6VarArr = null;
        }
    }

    private s6 a(int i, p pVar, m4 m4Var, Map<l1, ?> map, i2 i2Var) throws a {
        float f = (i * 1.0f) / 250.0f;
        if (f < 1.0f) {
            f = 1.0f;
        }
        p f2 = m4Var.f(pVar, f);
        o6[] a2 = a(map);
        try {
            try {
                s6 a3 = a(m4Var.e(f2), a2, map);
                if (a3 != null && a3.k() != null) {
                    k2.a(a3.j(), f, i2Var);
                    return a3;
                }
                throw a.a();
            } catch (a unused) {
                s6 a4 = a(new p(new e4(pVar.a().c())), a2, map);
                if (a4 != null && a4.k() != null) {
                    k2.a(a4.j(), 1.0f, i2Var);
                }
                return a4;
            }
        } catch (a unused2) {
            s6 a5 = a(m4Var.f(f2), a2, map);
            if (a5 != null && a5.k() != null) {
                k2.a(a5.j(), f, i2Var);
                return a5;
            }
            throw a.a();
        }
    }

    public s6 a(p pVar, p pVar2, m4 m4Var, Map<l1, ?> map) throws a {
        p a2;
        int e = pVar.e();
        int c = pVar.c();
        int i = e < c ? e : c;
        float f = (i * 1.0f) / 1080.0f;
        if (f <= 1.0f) {
            f = 1.0f;
        }
        if (r3.f5870a) {
            a2 = m4Var.a(pVar, f);
        } else {
            float f2 = f > 1.5f ? f : 1.0f;
            float f3 = f2;
            a2 = m4Var.a(pVar, f2);
            f = f3;
        }
        try {
            s6 a3 = a(a2, a(map), map);
            if (a3 != null && a3.k() != null) {
                k2.a(a3.j(), f, (i2) null);
                return a3;
            }
            if (!r3.c && a3 != null && a3.k() == null && a3.j().length >= 3) {
            }
            throw a.a();
        } catch (a unused) {
            s6 a4 = a(i, m4Var, pVar, pVar2, map);
            if (a4 == null) {
                throw a.a();
            }
            if (0 != 0) {
                a4.a();
                a4.b((u6[]) null);
            }
            return a4;
        }
    }

    private s6 a(int i, m4 m4Var, p pVar, p pVar2, Map<l1, ?> map) throws a {
        o6[] a2 = a(map);
        try {
            if (r3.f5870a) {
                float f = (i * 1.0f) / 500.0f;
                if (f <= 1.0f) {
                    f = 1.0f;
                }
                s6 a3 = a(m4Var.g(m4Var.g(pVar, f)), a2, map);
                if (a3 != null && a3.k() != null) {
                    k2.a(a3.j(), f, (i2) null);
                    return a3;
                }
            }
            throw a.a();
        } catch (a unused) {
            float f2 = (i * 1.0f) / 1080.0f;
            float f3 = f2 > 1.0f ? f2 : 1.0f;
            s6 a4 = a(m4Var.b(pVar2, f3), a2, map);
            if (a4 != null && a4.k() != null) {
                k2.a(a4.j(), f3, (i2) null);
            }
            return a4;
        }
    }
}
