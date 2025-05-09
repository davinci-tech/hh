package com.amap.api.col.p0003sl;

import android.os.SystemClock;
import com.amap.api.col.p0003sl.li;
import java.util.List;

/* loaded from: classes2.dex */
public final class lf extends le {
    public lf() {
        super(2048);
    }

    public final byte[] a(mo moVar, li.a aVar, long j, List<mp> list) {
        super.a();
        try {
            int a2 = a(moVar);
            int i = -1;
            int a3 = (aVar == null || aVar.f == null || aVar.f.size() <= 0) ? -1 : a(aVar);
            if (list != null && list.size() > 0) {
                i = a(j, list);
            }
            ln.a(this.f1322a);
            ln.a(this.f1322a, a2);
            if (a3 > 0) {
                ln.c(this.f1322a, a3);
            }
            if (i > 0) {
                ln.b(this.f1322a, i);
            }
            this.f1322a.c(ln.b(this.f1322a));
            return this.f1322a.c();
        } catch (Throwable th) {
            ms.a(th);
            return null;
        }
    }

    private int a(mo moVar) {
        return lu.a(this.f1322a, moVar.c, moVar.k, (int) (moVar.e * 1000000.0d), (int) (moVar.d * 1000000.0d), (int) moVar.f, (int) moVar.i, (int) moVar.g, (short) moVar.h, moVar.l);
    }

    private int a(li.a aVar) {
        int i;
        int i2;
        int i3;
        byte b;
        int i4;
        int a2;
        int a3;
        a(aVar.f);
        int size = aVar.f.size();
        int[] iArr = new int[size];
        int i5 = 0;
        while (true) {
            byte b2 = 2;
            if (i5 < size) {
                mi miVar = aVar.f.get(i5);
                if (miVar instanceof mk) {
                    mk mkVar = (mk) miVar;
                    if (!mkVar.i) {
                        a3 = lv.a(this.f1322a, mkVar.j, mkVar.k, mkVar.c, mkVar.l);
                    } else {
                        a3 = lv.a(this.f1322a, mkVar.b(), mkVar.c(), mkVar.j, mkVar.k, mkVar.c, mkVar.m, mkVar.n, mkVar.d, mkVar.l);
                    }
                    i4 = a3;
                    i3 = -1;
                    b = 1;
                } else {
                    if (miVar instanceof ml) {
                        ml mlVar = (ml) miVar;
                        a2 = lw.a(this.f1322a, mlVar.b(), mlVar.c(), mlVar.j, mlVar.k, mlVar.l, mlVar.c, mlVar.m, mlVar.d);
                        b2 = 3;
                    } else if (miVar instanceof mj) {
                        mj mjVar = (mj) miVar;
                        if (!mjVar.i) {
                            a2 = lp.a(this.f1322a, mjVar.j, mjVar.k, mjVar.l, mjVar.m, mjVar.n, mjVar.c);
                        } else {
                            a2 = lp.a(this.f1322a, mjVar.j, mjVar.k, mjVar.l, mjVar.m, mjVar.n, mjVar.c, mjVar.d);
                        }
                    } else if (miVar instanceof mm) {
                        mm mmVar = (mm) miVar;
                        a2 = lz.a(this.f1322a, mmVar.b(), mmVar.c(), mmVar.j, mmVar.k, mmVar.l, mmVar.c, mmVar.m, mmVar.d);
                        b2 = 4;
                    } else {
                        i3 = -1;
                        b = 0;
                        i4 = -1;
                    }
                    i4 = a2;
                    b = b2;
                    i3 = -1;
                }
                if (i4 == i3) {
                    return i3;
                }
                iArr[i5] = ls.a(this.f1322a, miVar.h ? (byte) 1 : (byte) 0, miVar.i ? (byte) 1 : (byte) 0, (short) miVar.g, b, i4);
                i5++;
            } else {
                int a4 = this.f1322a.a(aVar.b);
                int a5 = lq.a(this.f1322a, iArr);
                int size2 = aVar.g.size();
                int[] iArr2 = new int[size2];
                for (int i6 = 0; i6 < size2; i6++) {
                    mi miVar2 = aVar.g.get(i6);
                    long elapsedRealtime = (SystemClock.elapsedRealtime() - miVar2.e) / 1000;
                    if (elapsedRealtime > 32767 || elapsedRealtime < 0) {
                        elapsedRealtime = 32767;
                    }
                    if (miVar2 instanceof mk) {
                        mk mkVar2 = (mk) miVar2;
                        i = ly.a(this.f1322a, mkVar2.j, mkVar2.k, (short) elapsedRealtime);
                    } else if (miVar2 instanceof ml) {
                        ml mlVar2 = (ml) miVar2;
                        i = ly.a(this.f1322a, mlVar2.j, mlVar2.k, (short) elapsedRealtime);
                    } else {
                        if (miVar2 instanceof mj) {
                            mj mjVar2 = (mj) miVar2;
                            i = lx.a(this.f1322a, mjVar2.j, mjVar2.k, mjVar2.l, (short) elapsedRealtime);
                            i2 = 2;
                        } else if (miVar2 instanceof mm) {
                            mm mmVar2 = (mm) miVar2;
                            i = ly.a(this.f1322a, mmVar2.j, mmVar2.k, (short) elapsedRealtime);
                        } else {
                            i = 0;
                            i2 = 0;
                        }
                        iArr2[i6] = lr.a(this.f1322a, (byte) i2, i);
                    }
                    i2 = 1;
                    iArr2[i6] = lr.a(this.f1322a, (byte) i2, i);
                }
                return lq.a(this.f1322a, a4, aVar.f1324a, a5, lq.b(this.f1322a, iArr2));
            }
        }
    }

    private int a(long j, List<mp> list) {
        b(list);
        int size = list.size();
        if (size <= 0) {
            return -1;
        }
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            mp mpVar = list.get(i);
            iArr[i] = mb.a(this.f1322a, mpVar.f1338a == j && mpVar.f1338a != -1, mpVar.f1338a, (short) mpVar.c, this.f1322a.a(mpVar.b), mpVar.g, (short) mpVar.d);
        }
        return ma.a(this.f1322a, ma.a(this.f1322a, iArr));
    }

    private static void a(List<mi> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (mi miVar : list) {
            if (miVar instanceof mk) {
                mk mkVar = (mk) miVar;
                miVar.g = me.a(me.a(mkVar.j, mkVar.k));
            } else if (miVar instanceof ml) {
                ml mlVar = (ml) miVar;
                miVar.g = me.a(me.a(mlVar.j, mlVar.k));
            } else if (miVar instanceof mm) {
                mm mmVar = (mm) miVar;
                miVar.g = me.a(me.a(mmVar.j, mmVar.k));
            } else if (miVar instanceof mj) {
                mj mjVar = (mj) miVar;
                miVar.g = me.a(me.a(mjVar.k, mjVar.l));
            }
        }
    }

    private static void b(List<mp> list) {
        for (mp mpVar : list) {
            mpVar.g = me.b(mpVar.f1338a);
        }
    }
}
