package com.huawei.hms.scankit.p;

import android.util.Log;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.hms.scankit.util.LoadOpencvJNIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class n1 {

    /* renamed from: a, reason: collision with root package name */
    private p4 f5836a;
    private p b;
    private p c;
    private p d;
    private boolean e;
    private float g;
    private float f = 0.0f;
    private float h = 0.0f;
    private float j = 1.778f;
    private int k = 0;
    private int l = 0;
    public m4 i = new m4();

    n1(p4 p4Var) {
        this.e = false;
        this.g = 0.0f;
        this.f5836a = p4Var;
        this.b = new p(new q3(this.f5836a));
        this.c = new p(new e4(this.f5836a));
        this.e = false;
        this.g = 0.0f;
    }

    static s6 a(List<i2> list, n1 n1Var) {
        for (i2 i2Var : list) {
            if (r3.b || i2Var.h() > 0.4d) {
                int j = (int) i2Var.j();
                int k = (int) i2Var.k();
                if (j > n1Var.f5836a.c() / 3 && j < (n1Var.f5836a.c() * 2) / 3 && k > n1Var.f5836a.a() / 3 && k < (n1Var.f5836a.a() * 2) / 3) {
                    float c = n1Var.c(n1Var.d);
                    s6 s6Var = new s6(1.0f);
                    s6Var.a(c);
                    s6Var.a(i2Var);
                    return s6Var;
                }
            }
        }
        return null;
    }

    public s6 b(List<BarcodeFormat> list, i2 i2Var) {
        s6 s6Var;
        a5 a5Var = new a5();
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        try {
            s6Var = i2Var != null ? a5Var.b(this.b, this.c, this.d, hashMap, this.i, i2Var) : a5Var.b(this.b, this.c, null, hashMap, this.i, null);
        } catch (a unused) {
            s6Var = null;
        }
        try {
            if (!r3.c && s6Var != null && s6Var.k() == null && s6Var.j() != null && s6Var.j().length >= 3) {
                float b = o8.b(this.f5836a.c(), this.f5836a.a(), s6Var.j());
                if (Math.abs(1.0f - b) > 0.001d) {
                    this.h = b;
                    this.e = true;
                }
            }
        } catch (a unused2) {
            Log.e("DecodeProcessor", "decode2d AIScanException");
            return s6Var;
        }
        return s6Var;
    }

    public s6 c(List<BarcodeFormat> list, i2 i2Var) {
        a5 a5Var = new a5();
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        s6 s6Var = null;
        if (i2Var != null) {
            try {
                Log.i("DecodeProcessor", "decodeHarm start.");
                s6Var = a5Var.a(this.b, this.c, this.d, hashMap, this.i, i2Var);
            } catch (a unused) {
                Log.e("DecodeProcessor", "decodeHarm AIScanException");
            }
        }
        if (!r3.c && s6Var != null && s6Var.k() == null && s6Var.j() != null && s6Var.j().length >= 3) {
            float b = o8.b(this.f5836a.c(), this.f5836a.a(), new u6[]{new u6(i2Var.d(), i2Var.e()), new u6(i2Var.d() + i2Var.f(), i2Var.e()), new u6(i2Var.d(), i2Var.e() + i2Var.c())});
            if (Math.abs(1.0f - b) > 0.001d) {
                this.h = b;
                this.e = true;
            }
            if (this.e) {
                Log.i("DecodeProcessor", "decodeHarm need zoom");
            }
        }
        return s6Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00f0 A[Catch: a -> 0x00f8, TRY_ENTER, TRY_LEAVE, TryCatch #3 {a -> 0x00f8, blocks: (B:11:0x0039, B:16:0x00f0, B:27:0x005d, B:29:0x006b, B:35:0x0085, B:37:0x008f, B:39:0x00a0, B:42:0x0098, B:44:0x0058, B:23:0x0047, B:25:0x004b), top: B:10:0x0039, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008f A[Catch: a -> 0x00f8, TryCatch #3 {a -> 0x00f8, blocks: (B:11:0x0039, B:16:0x00f0, B:27:0x005d, B:29:0x006b, B:35:0x0085, B:37:0x008f, B:39:0x00a0, B:42:0x0098, B:44:0x0058, B:23:0x0047, B:25:0x004b), top: B:10:0x0039, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.scankit.p.s6 d(java.util.List<com.huawei.hms.scankit.aiscan.common.BarcodeFormat> r28, com.huawei.hms.scankit.p.i2 r29) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.n1.d(java.util.List, com.huawei.hms.scankit.p.i2):com.huawei.hms.scankit.p.s6");
    }

    public s6 e(List<BarcodeFormat> list, i2 i2Var) {
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        if (i2Var == null) {
            s6 a2 = a(hashMap);
            if (a2 != null && a2.k() == null && r3.q) {
                r3.m = true;
                a2 = a(hashMap);
                r3.m = false;
            }
            s6 s6Var = a2;
            if (s6Var == null || s6Var.k() != null || !r3.r) {
                return s6Var;
            }
            r3.n = true;
            s6 a3 = a(hashMap);
            r3.n = false;
            return a3;
        }
        r3.l = true;
        s6 a4 = a(hashMap, i2Var);
        r3.l = false;
        if (a4 != null && a4.k() == null && r3.p) {
            r3.m = true;
            a4 = g(list, i2Var);
            r3.m = false;
        }
        if (a4 != null && a4.k() == null && r3.q) {
            r3.n = true;
            a4 = a(hashMap, i2Var);
            r3.n = false;
        }
        if ((a4 != null && a4.k() != null) || !r3.r) {
            return a4;
        }
        r3.o = true;
        s6 a5 = a(hashMap, i2Var);
        r3.o = false;
        return a5;
    }

    public s6 f(List<BarcodeFormat> list, i2 i2Var) {
        float f;
        p pVar;
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        try {
            p4 c = i2Var != null ? this.d.a().c() : this.f5836a;
            if (!r3.f5870a || (this.b.e() <= 800 && this.b.c() <= 800)) {
                f = 1.0f;
            } else {
                f = (this.b.e() > this.b.c() ? this.b.e() : this.b.c()) / 800.0f;
                c = this.i.h(new p(new q3(c)), f).a().c();
            }
            p4 p4Var = c;
            float f2 = f;
            if (p4Var == null) {
                throw a.a();
            }
            if (!r3.f5870a || r3.b) {
                pVar = new p(new q3(p4Var));
            } else {
                s a2 = a(p4Var.b(), p4Var.c(), p4Var.a(), false);
                pVar = new p(new q3(p4Var));
                pVar.a(a2);
            }
            a5 a5Var = new a5();
            try {
                s6 a3 = a5Var.a(pVar, hashMap);
                if (a3 == null || a3.k() == null) {
                    throw a.a();
                }
                k2.a(a3.j(), f2, i2Var);
                return a3;
            } catch (a unused) {
                return a(a5Var, p4Var, pVar, hashMap, f2, i2Var);
            }
        } catch (a unused2) {
            Log.e("DecodeProcessor", "decodeQRMulti AIScanException");
            return null;
        }
    }

    public s6 g(List<BarcodeFormat> list, i2 i2Var) {
        p pVar;
        p pVar2;
        s6 s6Var;
        a5 a5Var = new a5();
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        float f = 1.0f;
        if (i2Var == null) {
            if (!r3.f5870a || (this.b.e() <= 500 && this.b.c() <= 500)) {
                pVar = this.b;
            } else {
                f = (this.b.e() > this.b.c() ? this.b.e() : this.b.c()) / 500.0f;
                pVar = this.i.c(this.b, f);
            }
        } else if (!r3.f5870a || (pVar2 = this.d) == null || (pVar2.e() <= 500 && this.d.c() <= 500)) {
            pVar = this.d;
        } else {
            f = (this.d.e() > this.d.c() ? this.d.e() : this.d.c()) / 500.0f;
            pVar = this.i.g(this.d, f);
        }
        try {
            s6Var = a5Var.a(pVar, hashMap);
            if (s6Var != null) {
                try {
                    if (s6Var.k() != null) {
                        k2.a(s6Var.j(), f, i2Var);
                        return s6Var;
                    }
                } catch (a unused) {
                    Log.e("DecodeProcessor", "decodeQRSimple AIScanException");
                    return s6Var;
                }
            }
        } catch (a unused2) {
            s6Var = null;
        }
        return s6Var;
    }

    public s6 h(List<BarcodeFormat> list, i2 i2Var) {
        a5 a5Var = new a5();
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        s6 s6Var = null;
        if (i2Var != null) {
            try {
                s6Var = a5Var.c(this.d, this.i, hashMap, i2Var);
            } catch (a unused) {
                Log.e("DecodeProcessor", "decode2d AIScanException");
            }
        }
        if (!r3.c && s6Var != null && s6Var.k() == null && s6Var.j() != null && s6Var.j().length >= 3) {
            float b = o8.b(this.f5836a.c(), this.f5836a.a(), s6Var.j());
            if (Math.abs(1.0f - b) > 0.001d) {
                this.h = b;
                this.e = true;
            }
        }
        return s6Var;
    }

    public p a() {
        return this.b;
    }

    static s6 a(n1 n1Var) {
        float c = n1Var.c(n1Var.b);
        s6 s6Var = new s6(1.0f);
        s6Var.b(c);
        s6Var.b(new i2(false, 0.0f, 0.0f, n1Var.b.e(), n1Var.b.e(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return s6Var;
    }

    private p b(p pVar) {
        int e = pVar.e();
        int c = pVar.c();
        if (e < c) {
            if (c / e <= 1.2d) {
                return pVar;
            }
            int i = (int) (e * 1.2d);
            int i2 = (c - i) / 2;
            this.l = i2;
            return pVar.a(0, i2, e, i);
        }
        if (e / c <= 1.2d) {
            return pVar;
        }
        int i3 = (int) (c * 1.2d);
        int i4 = (e - i3) / 2;
        this.k = i4;
        return pVar.a(i4, 0, i3, c);
    }

    public static boolean a(List<i2> list, boolean z) {
        if (!z && !r3.b) {
            float[] a2 = a(list.get(0));
            float a3 = a(a2);
            r3.y = a2;
            if (a3 >= 0.6f) {
                r3.z++;
            } else {
                r3.z = 1;
            }
            o4.d("DecodeProcessor", "iou: " + a3 + " focusAreaFrameCount: " + r3.z);
            if (r3.z < 8) {
                return false;
            }
            o4.d("DecodeProcessor", "need area focus");
            r3.z = 1;
            return true;
        }
        r3.y = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        r3.z = 0;
        return false;
    }

    public float c(p pVar) {
        if (((pVar == null || (pVar.a() == null && pVar.a().c() == null)) ? null : pVar.a().c().b()) == null) {
            return 1.0f;
        }
        int e = pVar.e();
        int c = pVar.c();
        long j = 0;
        for (int i = c / 4; i < (c * 3) / 4; i++) {
            for (int i2 = e / 4; i2 < (e * 3) / 4; i2++) {
                j += r0[(i * e) + i2] & 255;
            }
        }
        return (j / r0.length) * 4;
    }

    public float e() {
        return this.g;
    }

    public void b(i2 i2Var) {
        try {
            if (r3.f5870a) {
                k2.a(r3.b, this.b, i2Var);
                this.d = i2Var.l;
            }
        } catch (a unused) {
            Log.e("DecodeProcessor", "cropAndRotate AIScanException");
        }
    }

    public boolean b() {
        return this.e;
    }

    public s6 a(Map<l1, Object> map, i2 i2Var) {
        p pVar;
        float f;
        s6 s6Var;
        p pVar2;
        s6 s6Var2;
        a5 a5Var = new a5();
        p pVar3 = this.d;
        if (pVar3 == null) {
            return null;
        }
        r3.h = true;
        int e = pVar3.e() > this.d.c() ? this.d.e() : this.d.c();
        if (r3.f5870a && e > 500) {
            float f2 = e / 500.0f;
            if (f2 < 1.0f) {
                f2 = 1.0f;
            }
            pVar = this.i.g(this.d, f2);
            f = f2;
        } else {
            pVar = this.d;
            f = 1.0f;
        }
        try {
            if (r3.o) {
                pVar = new p(new q3(j7.b(pVar.a().c())));
            }
            s6 a2 = a5Var.a(pVar, map);
            if (a2 != null) {
                try {
                    if (a2.k() == null && a2.j() != null && a2.j().length >= 3) {
                        s6Var = new s6(null, null, a2.j(), BarcodeFormat.QR_CODE);
                        try {
                            a2 = a(pVar, map, a2, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d});
                        } catch (a unused) {
                            pVar2 = pVar;
                            s6Var2 = a2;
                            s6 a3 = a(pVar2, s6Var2, s6Var, a5Var, map, f, i2Var);
                            if ((a3 == null || a3.k() == null) && !r3.c && a3 != null && a3.j() != null && a3.j().length >= 3) {
                                k2.a(a3.j(), f, i2Var);
                                float a4 = o8.a(this.f5836a.c(), this.f5836a.a(), a3.j());
                                if (Math.abs(1.0f - a4) > 0.001d) {
                                    this.f = a4;
                                    this.e = true;
                                }
                            }
                            return a3;
                        }
                    }
                } catch (a unused2) {
                    s6Var = null;
                }
            }
            if (a2 != null && a2.k() != null) {
                k2.a(a2.j(), f, i2Var);
                return a2;
            }
            throw a.a();
        } catch (a unused3) {
            s6Var = null;
            pVar2 = pVar;
            s6Var2 = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b0, code lost:
    
        if (a(r12.b, r0) != false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b2, code lost:
    
        r1 = com.huawei.hms.scankit.p.r3.k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b5, code lost:
    
        if (r1 <= 4) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b8, code lost:
    
        com.huawei.hms.scankit.p.r3.k = r1 + 2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(java.util.List<com.huawei.hms.scankit.p.i2> r13) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.n1.b(java.util.List):boolean");
    }

    public float c() {
        return this.f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e0, code lost:
    
        if (a(r14.b, r0) != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e2, code lost:
    
        r1 = com.huawei.hms.scankit.p.r3.k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00e5, code lost:
    
        if (r1 <= 4) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e8, code lost:
    
        com.huawei.hms.scankit.p.r3.k = r1 + 2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(java.util.List<com.huawei.hms.scankit.p.i2> r15) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.n1.c(java.util.List):boolean");
    }

    public float d() {
        return this.h;
    }

    static boolean b(p pVar, i2 i2Var) {
        if (r3.f5870a && !r3.b) {
            float d = i2Var.d();
            float e = i2Var.e();
            float f = i2Var.f();
            float c = i2Var.c();
            float f2 = d - ((f * 0.2f) / 2.0f);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            float f3 = e - ((0.2f * c) / 2.0f);
            float f4 = f3 >= 0.0f ? f3 : 0.0f;
            float f5 = (f * 1.2f) + f2;
            if (f5 > pVar.e()) {
                f5 = pVar.e();
            }
            float f6 = (c * 1.2f) + f4;
            if (f6 > pVar.c()) {
                f6 = pVar.c();
            }
            List<i2> a2 = k2.a(r3.b, pVar.a((int) f2, (int) f4, (int) (f5 - f2), (int) (f6 - f4)), 0, true);
            if (!a2.isEmpty() && a2.get(0).g() == 6.0f) {
                return true;
            }
        }
        return false;
    }

    public static float a(float[] fArr) {
        float[] fArr2 = r3.y;
        float f = fArr2[2];
        float f2 = fArr2[0];
        float f3 = fArr2[3];
        float f4 = fArr2[1];
        float f5 = (f - f2) * (f3 - f4);
        if (f5 == 0.0d) {
            return 0.0f;
        }
        float f6 = fArr[2];
        float f7 = fArr[0];
        float f8 = fArr[3];
        float f9 = fArr[1];
        float max = Math.max(f4, f9);
        float min = Math.min(r3.y[3], fArr[3]);
        float max2 = Math.max(r3.y[0], fArr[0]);
        float min2 = Math.min(r3.y[2], fArr[2]);
        if (max >= min || max2 >= min2) {
            return 0.0f;
        }
        float f10 = (min - max) * (min2 - max2);
        return (f10 / ((f5 + ((f6 - f7) * (f8 - f9))) - f10)) * 1.0f;
    }

    public static float[] a(i2 i2Var) {
        return new float[]{i2Var.s, i2Var.r, r0 + i2Var.q, r2 + i2Var.p};
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x015d A[Catch: a -> 0x01ec, TRY_LEAVE, TryCatch #6 {a -> 0x01ec, blocks: (B:44:0x0113, B:46:0x015d, B:62:0x01b4, B:64:0x01c1, B:66:0x01c7, B:68:0x01cd, B:70:0x01e3, B:72:0x01e7, B:73:0x01eb, B:49:0x017d, B:51:0x0183, B:53:0x0189, B:55:0x018e, B:57:0x01a8, B:59:0x01ac, B:60:0x01b0), top: B:43:0x0113, inners: #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.scankit.p.s6 a(com.huawei.hms.scankit.p.p r22, com.huawei.hms.scankit.p.s6 r23, com.huawei.hms.scankit.p.s6 r24, com.huawei.hms.scankit.p.a5 r25, java.util.Map<com.huawei.hms.scankit.p.l1, java.lang.Object> r26, float r27, com.huawei.hms.scankit.p.i2 r28) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.n1.a(com.huawei.hms.scankit.p.p, com.huawei.hms.scankit.p.s6, com.huawei.hms.scankit.p.s6, com.huawei.hms.scankit.p.a5, java.util.Map, float, com.huawei.hms.scankit.p.i2):com.huawei.hms.scankit.p.s6");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.scankit.p.s6 a(java.util.Map<com.huawei.hms.scankit.p.l1, java.lang.Object> r14) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.n1.a(java.util.Map):com.huawei.hms.scankit.p.s6");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.scankit.p.s6 a(com.huawei.hms.scankit.p.a5 r15, com.huawei.hms.scankit.p.p4 r16, com.huawei.hms.scankit.p.s6 r17, java.util.Map<com.huawei.hms.scankit.p.l1, java.lang.Object> r18, float r19, int r20, int r21) {
        /*
            r14 = this;
            r7 = r14
            r4 = r17
            r5 = r18
            r6 = r19
            r0 = r20
            r1 = r21
            java.lang.String r2 = "decodeQRUseFullImgTryHard AIScanException"
            java.lang.String r3 = "DecodeProcessor"
            boolean r8 = com.huawei.hms.scankit.p.r3.f5870a
            r9 = 0
            if (r8 == 0) goto L91
            r8 = 3
            com.huawei.hms.scankit.p.q3 r10 = new com.huawei.hms.scankit.p.q3     // Catch: com.huawei.hms.scankit.p.a -> L4f
            r11 = r16
            r10.<init>(r11)     // Catch: com.huawei.hms.scankit.p.a -> L4d
            com.huawei.hms.scankit.p.p r12 = new com.huawei.hms.scankit.p.p     // Catch: com.huawei.hms.scankit.p.a -> L4d
            r12.<init>(r10)     // Catch: com.huawei.hms.scankit.p.a -> L4d
            r10 = r15
            com.huawei.hms.scankit.p.s6 r9 = r15.a(r12, r5)     // Catch: com.huawei.hms.scankit.p.a -> L4b
            if (r9 == 0) goto L33
            java.lang.String r13 = r9.k()     // Catch: com.huawei.hms.scankit.p.a -> L4b
            if (r13 == 0) goto L33
            com.huawei.hms.scankit.p.s6 r0 = r14.a(r9, r6, r0, r1)     // Catch: com.huawei.hms.scankit.p.a -> L4b
            return r0
        L33:
            if (r9 == 0) goto L49
            com.huawei.hms.scankit.p.u6[] r13 = r9.j()     // Catch: com.huawei.hms.scankit.p.a -> L4b
            if (r13 == 0) goto L49
            com.huawei.hms.scankit.p.u6[] r13 = r9.j()     // Catch: com.huawei.hms.scankit.p.a -> L4b
            int r13 = r13.length     // Catch: com.huawei.hms.scankit.p.a -> L4b
            if (r13 < r8) goto L49
            com.huawei.hms.scankit.p.u6[] r9 = r9.j()     // Catch: com.huawei.hms.scankit.p.a -> L4b
            r4.b(r9)     // Catch: com.huawei.hms.scankit.p.a -> L4b
        L49:
            r9 = r12
            goto L55
        L4b:
            r9 = r12
            goto L52
        L4d:
            r10 = r15
            goto L52
        L4f:
            r10 = r15
            r11 = r16
        L52:
            android.util.Log.e(r3, r2)
        L55:
            if (r4 == 0) goto L82
            com.huawei.hms.scankit.p.u6[] r12 = r17.j()     // Catch: com.huawei.hms.scankit.p.a -> L7f
            if (r12 == 0) goto L82
            com.huawei.hms.scankit.p.u6[] r12 = r17.j()     // Catch: com.huawei.hms.scankit.p.a -> L7f
            int r12 = r12.length     // Catch: com.huawei.hms.scankit.p.a -> L7f
            if (r12 < r8) goto L82
            boolean r8 = com.huawei.hms.scankit.p.r3.m     // Catch: com.huawei.hms.scankit.p.a -> L7f
            if (r8 != 0) goto L82
            r8 = 6
            double[] r8 = new double[r8]     // Catch: com.huawei.hms.scankit.p.a -> L7f
            r8 = {x0092: FILL_ARRAY_DATA , data: [0, 0, 0, 0, 0, 4607182418800017408} // fill-array     // Catch: com.huawei.hms.scankit.p.a -> L7f
            com.huawei.hms.scankit.p.s6 r8 = r14.a(r9, r5, r4, r8)     // Catch: com.huawei.hms.scankit.p.a -> L7f
            if (r8 == 0) goto L82
            java.lang.String r12 = r8.k()     // Catch: com.huawei.hms.scankit.p.a -> L7f
            if (r12 == 0) goto L82
            com.huawei.hms.scankit.p.s6 r0 = r14.a(r8, r6, r0, r1)     // Catch: com.huawei.hms.scankit.p.a -> L7f
            return r0
        L7f:
            android.util.Log.e(r3, r2)
        L82:
            r0 = r14
            r1 = r9
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            com.huawei.hms.scankit.p.s6 r9 = r0.a(r1, r2, r3, r4, r5, r6)
        L91:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.n1.a(com.huawei.hms.scankit.p.a5, com.huawei.hms.scankit.p.p4, com.huawei.hms.scankit.p.s6, java.util.Map, float, int, int):com.huawei.hms.scankit.p.s6");
    }

    private s6 a(p pVar, a5 a5Var, p4 p4Var, s6 s6Var, Map<l1, Object> map, float f) {
        s6 s6Var2 = null;
        try {
            j6.a(this.f5836a, s6Var);
            if (r3.c && r3.v[1]) {
                r3.s = true;
                s6Var2 = a5Var.a(this.b, map);
                r3.s = false;
                if (s6Var2 != null && s6Var2.k() != null) {
                    return a(s6Var2, f, 0, 0);
                }
            }
        } catch (a unused) {
            r3.s = false;
        }
        float e = pVar.e() / pVar.c();
        if (e < 1.0f) {
            e = 1.0f / e;
        }
        if (!r3.m && !r3.n) {
            double d = e;
            if (d > 1.27d && d < 1.272d) {
                r3.u = true;
                try {
                    s6Var2 = a5Var.a(new p(new e4(p4Var)), map);
                    if (s6Var2 != null && s6Var2.k() != null) {
                        return a(s6Var2, f, 0, 0);
                    }
                } catch (a unused2) {
                    Log.e("DecodeProcessor", "decodeQRUseFullImgTryHardSpecialCase AIScanException");
                }
                r3.u = false;
            }
        }
        return s6Var2;
    }

    private s6 a(a5 a5Var, p4 p4Var, p pVar, Map<l1, Object> map, float f, i2 i2Var) throws a {
        p pVar2;
        s6 a2;
        p pVar3;
        s6 s6Var = null;
        if (r3.f5870a && !r3.b) {
            try {
                pVar3 = new p(new q3(p4Var));
                try {
                    s6 a3 = a5Var.a(pVar3, map);
                    if (a3 != null) {
                        try {
                            if (a3.k() != null) {
                                k2.a(a3.j(), f, i2Var);
                                return a3;
                            }
                        } catch (a unused) {
                            s6Var = a3;
                            pVar = pVar3;
                            Log.e("DecodeProcessor", "decodeQRMultiHard AIScanException");
                            pVar3 = pVar;
                            if (s6Var != null) {
                                try {
                                    s6Var = a(pVar3, map, s6Var, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d});
                                    if (s6Var != null) {
                                        k2.a(s6Var.j(), f, i2Var);
                                        return s6Var;
                                    }
                                } catch (a unused2) {
                                    Log.e("DecodeProcessor", "decodeQRMultiHard AIScanException");
                                }
                            }
                            pVar2 = new p(new e4(p4Var));
                            a2 = a5Var.a(pVar2, map);
                            if (a2 == null) {
                            }
                            throw a.a();
                        }
                    }
                    s6Var = a3;
                } catch (a unused3) {
                }
            } catch (a unused4) {
            }
            if (s6Var != null && s6Var.j() != null && s6Var.j().length >= 3) {
                s6Var = a(pVar3, map, s6Var, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d});
                if (s6Var != null && s6Var.k() != null) {
                    k2.a(s6Var.j(), f, i2Var);
                    return s6Var;
                }
            }
        }
        pVar2 = new p(new e4(p4Var));
        try {
            a2 = a5Var.a(pVar2, map);
            if (a2 == null && a2.k() != null) {
                k2.a(a2.j(), f, i2Var);
                return a2;
            }
            throw a.a();
        } catch (a unused5) {
            if (r3.f5870a && !r3.b && s6Var != null && s6Var.j() != null && s6Var.j().length >= 3 && (s6Var = a(pVar2, map, s6Var, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d})) != null && s6Var.k() != null) {
                k2.a(s6Var.j(), f, i2Var);
            }
            return s6Var;
        }
    }

    public s6 a(List<BarcodeFormat> list, i2 i2Var) {
        s6 s6Var;
        a5 a5Var = new a5();
        HashMap hashMap = new HashMap();
        hashMap.put(l1.POSSIBLE_FORMATS, list);
        if (r3.c) {
            hashMap.put(l1.PHOTO_MODE, Boolean.valueOf(r3.c));
        }
        try {
            if (i2Var != null) {
                s6Var = a5Var.a(this.b, this.d, hashMap, this.i, i2Var);
            } else {
                s6Var = a5Var.a(this.b, (p) null, hashMap, this.i, (i2) null);
            }
        } catch (a unused) {
            Log.e("DecodeProcessor", "decode1d AIScanException");
            s6Var = null;
        }
        s6 s6Var2 = s6Var;
        if (s6Var2 != null || r3.b || i2Var == null || !r3.c || i2Var.h() >= 0.8d) {
            return s6Var2;
        }
        float i = i2Var.i() % 180.0f;
        boolean z = false;
        boolean z2 = ((double) i2Var.c()) > ((double) this.b.c()) * 0.97d && ((i < 5.0f && i > -5.0f) || i < -175.0f || i > 175.0f);
        if (i2Var.b() > this.b.e() * 0.97d && ((i < 95.0f && i > 85.0f) || (i < -85.0f && i > -95.0f))) {
            z = true;
        }
        if (!z2 && !z) {
            return s6Var2;
        }
        this.i.a();
        try {
            return a5Var.a(this.b, (p) null, hashMap, this.i, (i2) null);
        } catch (a unused2) {
            Log.e("DecodeProcessor", "decode1d AIScanException");
            return s6Var2;
        }
    }

    public List<i2> a(int i, boolean z) {
        List<i2> a2;
        ArrayList arrayList = new ArrayList();
        if (!r3.f5870a) {
            return arrayList;
        }
        boolean z2 = r3.b;
        if (!z2) {
            byte[] c = y4.c();
            byte[] a3 = y4.a();
            byte[] b = y4.b();
            LoadOpencvJNIUtil.setModel(c, c.length, a3, a3.length, b, b.length);
            p pVar = this.b;
            long currentTimeMillis = System.currentTimeMillis() % 10;
            boolean z3 = currentTimeMillis % 2 == 0;
            boolean z4 = currentTimeMillis % 3 == 0;
            if (i == 0 && !r3.c && z3) {
                pVar = b(this.b);
            } else if (i == 0 && !r3.c && z4) {
                pVar = a(b(this.b));
            }
            a2 = k2.a(r3.b, pVar, i, z);
        } else {
            a2 = k2.a(z2, this.b, i, z);
        }
        List<i2> list = a2;
        a(list);
        return list;
    }

    private p a(p pVar) {
        int e = pVar.e();
        int c = pVar.c();
        int i = (int) (e * 0.75d);
        int i2 = (int) (c * 0.75d);
        int i3 = (e - i) / 2;
        this.k += i3;
        int i4 = (c - i2) / 2;
        this.l += i4;
        return pVar.a(i3, i4, i, i2);
    }

    private void a(List<i2> list) {
        for (i2 i2Var : list) {
            i2Var.a(this.f5836a.c(), this.f5836a.a(), this.k, this.l);
            float min = Math.min(Math.abs(i2Var.i() % 90.0f), 90.0f - Math.abs(i2Var.i() % 90.0f));
            if (i2Var.c() * i2Var.f() > this.f5836a.a() * 0.9f * this.f5836a.c() && min < 5.0f) {
                i2Var.b(this.f5836a.c(), this.f5836a.a());
            }
        }
    }

    private s6 a(p pVar, Map<l1, Object> map, s6 s6Var, double[] dArr) throws a {
        if (pVar == null) {
            return null;
        }
        a5 a5Var = new a5();
        int[] iArr = {0, 0};
        byte[] a2 = k7.a(pVar, map, s6Var, iArr, dArr);
        int i = iArr[0];
        int i2 = iArr[1];
        e6 e6Var = new e6(a2, i, i2, 0, 0, i, i2, false);
        try {
            s6 a3 = a5Var.a(new p(new q3(e6Var)), map);
            if (a3 != null && a3.k() != null) {
                u6[] a4 = k7.a(a3.j(), pVar.e(), pVar.c(), dArr);
                a3.a();
                a3.b(a4);
                return a3;
            }
            throw a.a();
        } catch (a unused) {
            p pVar2 = new p(new e4(e6Var));
            try {
                s6 a5 = a5Var.a(pVar2, map);
                if (a5 != null && a5.k() != null) {
                    u6[] a6 = k7.a(a5.j(), pVar.e(), pVar.c(), dArr);
                    a5.a();
                    a5.b(a6);
                    return a5;
                }
                throw a.a();
            } catch (a unused2) {
                pVar2.a(a(e6Var.b(), e6Var.c(), e6Var.a(), false));
                try {
                    s6 a7 = a5Var.a(pVar2, map);
                    if (a7 != null && a7.k() != null) {
                        u6[] a8 = k7.a(a7.j(), pVar.e(), pVar.c(), dArr);
                        a7.a();
                        a7.b(a8);
                        return a7;
                    }
                    throw a.a();
                } catch (a unused3) {
                    Log.e("DecodeProcessor", "rotatedQRBinarizer  AIScanException");
                    return s6Var;
                }
            }
        }
    }

    private s6 a(s6 s6Var, float f, int i, int i2) {
        if (s6Var != null && s6Var.j().length == 4 && (Math.abs(f - 1.0f) >= 1.0E-6f || i != 0 || i2 != 0)) {
            u6[] u6VarArr = new u6[4];
            for (int i3 = 0; i3 < 4; i3++) {
                u6VarArr[i3] = new u6((s6Var.j()[i3].b() * f) + i, (s6Var.j()[i3].c() * f) + i2);
            }
            s6Var.a();
            s6Var.a(u6VarArr);
        }
        return s6Var;
    }

    public static s a(byte[] bArr, int i, int i2, boolean z) throws a {
        int i3 = i / 11;
        byte[] adaptivebinary = LoadOpencvJNIUtil.adaptivebinary(bArr, i2, i, (i3 + (i3 % 2)) - 1, z);
        if (adaptivebinary != null) {
            s sVar = new s(i, i2);
            for (int i4 = 0; i4 < i2; i4++) {
                for (int i5 = 0; i5 < i; i5++) {
                    if (adaptivebinary[(i4 * i) + i5] == 0) {
                        sVar.c(i5, i4);
                    }
                }
            }
            return sVar;
        }
        throw a.a();
    }

    public static p a(byte[] bArr, int i, int i2) throws a {
        byte[] sharpen = LoadOpencvJNIUtil.sharpen(bArr, i2, i);
        if (sharpen != null) {
            return new p(new e4(new e6(sharpen, i, i2, 0, 0, i, i2, false)));
        }
        throw a.a();
    }

    private static boolean a(p pVar, i2 i2Var) {
        if (r3.f5870a && !r3.b) {
            float d = i2Var.d();
            float e = i2Var.e();
            float f = i2Var.f();
            float c = i2Var.c();
            float f2 = d - ((f * 0.2f) / 2.0f);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            float f3 = e - ((0.2f * c) / 2.0f);
            float f4 = f3 >= 0.0f ? f3 : 0.0f;
            float f5 = (f * 1.2f) + f2;
            if (f5 > pVar.e()) {
                f5 = pVar.e();
            }
            float f6 = (c * 1.2f) + f4;
            if (f6 > pVar.c()) {
                f6 = pVar.c();
            }
            float f7 = f5 - f2;
            float f8 = f6 - f4;
            if (f7 < pVar.e() / 2.0f && f8 < pVar.c() / 2.0f) {
                for (i2 i2Var2 : k2.a(r3.b, pVar.a((int) f2, (int) f4, (int) f7, (int) f8), 0, true)) {
                    boolean z = i2Var2.g() == 1.0f && ((double) i2Var2.h()) > 0.5d;
                    boolean z2 = i2Var.g() == 2.0f && i2Var2.g() == 2.0f && ((double) i2Var2.h()) > 0.7d;
                    boolean z3 = i2Var.g() == 3.0f && i2Var2.g() == 3.0f && ((double) i2Var2.h()) > 0.7d;
                    boolean z4 = i2Var.g() == 7.0f && i2Var2.g() == 7.0f && ((double) i2Var2.h()) > 0.7d;
                    boolean z5 = i2Var.g() == 6.0f && i2Var2.g() == 6.0f && ((double) i2Var2.h()) > 0.7d;
                    if (z || z2 || z3 || z4 || z5) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }
}
