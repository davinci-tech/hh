package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class qgo extends cfe {
    private static final long serialVersionUID = 5590260758086929244L;

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public boolean isNewScaleType() {
        return true;
    }

    private static double af(double d) {
        return Math.round(d * 10.0d) / 10.0d;
    }

    private static dpv a(byte b, int i, double d, int i2, int i3) {
        dpv dpvVar = new dpv();
        dpvVar.a(b);
        dpvVar.c(i);
        dpvVar.d(d);
        dpvVar.d(1);
        dpvVar.b(i2);
        dpvVar.a(i3);
        return dpvVar;
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String getWeightScaleProductId() {
        int l = l();
        return (l == 94 || l == 95) ? "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4" : l != 106 ? l != 140 ? l != 385 ? l != 672 ? "" : "c943c933-442e-4c34-bcd0-66597f24aaed" : "b29df4e3-b1f7-4e40-960d-4cfb63ccca05" : "e835d102-af95-48a6-ae13-2983bc06f5c0" : "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4";
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0054  */
    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getFractionDigitByType(int r9) {
        /*
            r8 = this;
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)
            java.lang.String r1 = "getFractionDigitByType type = "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "HagridOrMiniViewBean"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 1
            if (r9 == 0) goto L27
            r2 = 7
            if (r9 == r2) goto L22
            java.lang.String r9 = "getFractionDigitByType get value fall into default "
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r9)
            r2 = 0
            r9 = r0
            goto L2c
        L22:
            double r2 = r8.i()
            goto L2b
        L27:
            double r2 = r8.ax()
        L2b:
            r9 = 2
        L2c:
            double r2 = health.compact.a.UnitUtil.a(r2)
            java.lang.String r4 = "getFractionDigitByType type is weight and showImperialUnit, "
            java.lang.Double r5 = java.lang.Double.valueOf(r2)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r4)
            r4 = 4621819117588971520(0x4024000000000000, double:10.0)
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = java.lang.Math.pow(r4, r6)
            double r4 = r4 * r2
            long r1 = java.lang.Math.round(r4)
            r3 = 10
            long r1 = r1 % r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L54
            goto L55
        L54:
            r0 = r9
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qgo.getFractionDigitByType(int):int");
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public double getDoubleOrIntLevelByType(int i) {
        int e = e();
        int t = t();
        byte an = an();
        double e2 = e(i, e, an);
        if (!doj.b(e2, -1.0d)) {
            return e2;
        }
        double a2 = a(i, e, an);
        if (!doj.b(a2, -1.0d)) {
            return a2;
        }
        double e3 = e(i, e, t, an);
        if (doj.b(e3, -1.0d)) {
            return -1.0d;
        }
        return e3;
    }

    private double e(int i, int i2, byte b) {
        int d;
        if (i == 1) {
            d = doj.d(b, i2, a(), 1);
        } else {
            if (i != 300) {
                if (i == 400) {
                    d = doj.a(new double[]{doj.a()[0], 2500.0d}, doj.b(b, i2, ax(), 1), UnitUtil.a(d(), 0), 1);
                } else if (i != 3) {
                    if (i == 4) {
                        d = doj.d(doj.b(b, i2, ax(), 1), af(d()));
                    } else {
                        switch (i) {
                            case 100:
                                d = doj.c(b, i2, a(), 1);
                                break;
                            case 101:
                                d = doj.a(b, i2, a(), 1);
                                break;
                            case 102:
                                return doj.b(b, i2, 1);
                            default:
                                return -1.0d;
                        }
                    }
                }
            }
            double ap = ap();
            if (!dph.t(ap)) {
                ap = al();
            }
            if (i == 3) {
                d = doj.d(doj.f(b, i2), af(ap));
            } else {
                d = doj.d(b, ap, i2);
            }
        }
        return d;
    }

    private double a(int i, int i2, byte b) {
        int f;
        if (i != 5) {
            if (i != 8) {
                if (i != 500) {
                    if (i != 800) {
                        return -1.0d;
                    }
                }
            }
            double ap = ap();
            if (!dph.t(ap)) {
                ap = al();
            }
            double d = doj.d(ax(), ap, a(), i(), getFractionDigitByType(0));
            if (i == 8) {
                f = doj.d(b, i2, d);
            } else {
                f = doj.b(b, i2, d);
            }
            return f;
        }
        if (i == 5) {
            f = doj.h(s());
        } else {
            f = doj.f(s());
        }
        return f;
    }

    private double e(int i, int i2, int i3, byte b) {
        int d;
        if (i == 6) {
            d = doj.d(doj.a(b, i2, i3, 1), af(z()));
        } else if (i == 7) {
            d = doj.b(a(b, i3, i(), getFractionDigitByType(7), i2));
        } else if (i == 10) {
            d = doj.d(doj.b(b, i2, i3, 1), af(aj()));
        } else if (i == 14 || i == 103) {
            double e = doj.e(a(), ax());
            if (i == 14) {
                d = doj.b(b, i2, i3, e, 1);
            } else {
                d = doj.c(b, i2, i3, e, 1);
            }
        } else if (i == 600) {
            d = doj.e(b, i2, t(), z(), 1);
        } else if (i == 700) {
            d = doj.a(a(b, i3, i(), getFractionDigitByType(7), i2));
        } else {
            if (i != 1000) {
                return -1.0d;
            }
            d = doj.h(b, i2, i3, aj(), 1);
        }
        return d;
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String[] getStringArrayLevelByType(int i) {
        int e = e();
        byte an = an();
        if (i == 1) {
            double[] c = doj.c(an, e, 1);
            return new String[]{doj.e(c[0]), doj.e(c[1]), doj.e(c[2])};
        }
        if (i == 5) {
            double[] aa = doj.aa();
            return new String[]{doj.e(aa[0]), doj.e(aa[1]), doj.e(aa[2])};
        }
        if (i == 3) {
            double[] f = doj.f(an, e);
            return new String[]{doj.e(f[0]), doj.e(f[1])};
        }
        if (i == 8) {
            double[] e2 = doj.e(an, e);
            return new String[]{doj.e(e2[0]), doj.e(e2[1])};
        }
        if (i != 4) {
            return null;
        }
        double[] b = doj.b(an, e, ax(), 1);
        return new String[]{UnitUtil.e(b[0], 1, 0), UnitUtil.e(b[1], 1, 0)};
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public double[] getDoubleArrayLevelByType(int i) {
        int e = e();
        byte an = an();
        int t = t();
        if (i == 1) {
            return doj.c(an, e, 1);
        }
        if (i == 6) {
            return doj.a(an, e, t, 1);
        }
        if (i == 14) {
            double[] a2 = doj.a(an, e, t, 1);
            double[] e2 = doj.e(an, t, e);
            return new double[]{af(a2[0] + e2[0]), af(a2[1] + e2[1])};
        }
        if (i == 10) {
            return doj.b(an, e, t, 1);
        }
        if (i == 5) {
            return doj.aa();
        }
        if (i == 3) {
            return doj.f(an, e);
        }
        if (i == 8) {
            return doj.e(an, e);
        }
        if (i == 7) {
            return doj.e(an, t, e);
        }
        if (i == 4) {
            return doj.b(an, e, ax(), 1);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0033  */
    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isVisible(int r3, boolean r4) {
        /*
            r2 = this;
            r0 = 31
            if (r3 == r0) goto L3b
            r0 = 0
            switch(r3) {
                case 0: goto Ld;
                case 1: goto L2e;
                case 2: goto Ld;
                case 3: goto L2e;
                case 4: goto L2e;
                case 5: goto L2e;
                case 6: goto L3a;
                case 7: goto L2e;
                case 8: goto L2e;
                case 9: goto L33;
                case 10: goto L2e;
                case 11: goto Ld;
                case 12: goto Ld;
                case 13: goto L33;
                case 14: goto L2e;
                default: goto L8;
            }
        L8:
            switch(r3) {
                case 25: goto L33;
                case 26: goto L2e;
                case 27: goto L29;
                default: goto Lb;
            }
        Lb:
            r3 = 1
            return r3
        Ld:
            java.lang.String r4 = r2.getWeightScaleProductId()
            boolean r4 = defpackage.cpa.v(r4)
            if (r4 == 0) goto L24
            r4 = 11
            if (r3 != r4) goto L24
            int r4 = r2.e()
            r1 = 18
            if (r4 >= r1) goto L24
            return r0
        L24:
            boolean r3 = defpackage.qrp.d(r2, r3)
            return r3
        L29:
            boolean r3 = r2.c(r4)
            return r3
        L2e:
            boolean r3 = r2.e(r3, r4)
            return r3
        L33:
            if (r4 != 0) goto L3a
            boolean r3 = r2.h(r3)
            return r3
        L3a:
            return r0
        L3b:
            boolean r3 = super.isVisible(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qgo.isVisible(int, boolean):boolean");
    }

    private boolean e(int i, boolean z) {
        boolean b;
        String weightScaleProductId = getWeightScaleProductId();
        int e = e();
        if (cpa.v(weightScaleProductId) && !z) {
            if (Utils.o() && !qrp.e(e)) {
                return false;
            }
            b = qrp.d(e);
        } else if ((cpa.v(weightScaleProductId) && z) || cpa.w(weightScaleProductId) || cpa.r(weightScaleProductId)) {
            b = qrp.e(e);
        } else {
            b = qrp.b(e);
        }
        if (b) {
            return qrp.a(this, i);
        }
        LogUtil.a("HagridOrMiniViewBean", "isAboutAgeViewVisible isGone Type", Integer.valueOf(i));
        return false;
    }

    private boolean h(int i) {
        boolean e;
        if (cpa.v(getWeightScaleProductId())) {
            if (i == 25 || i == 9) {
                e = qrp.e(e());
            } else if (Utils.c(BaseApplication.e()) || Utils.a(BaseApplication.e())) {
                e = qrp.e(e());
            } else {
                e = qrp.d(e());
            }
        } else if (cpa.w(getWeightScaleProductId()) || cpa.r(getWeightScaleProductId())) {
            e = qrp.e(e());
        } else {
            e = qrp.b(e());
        }
        if (e) {
            return qrp.e(this, i);
        }
        LogUtil.a("HagridOrMiniViewBean", "isNotOverseaViewVisible isGone Type", Integer.valueOf(i));
        return false;
    }

    private boolean c(boolean z) {
        boolean b;
        if (cpa.v(getWeightScaleProductId()) && aa() == 2) {
            b = qrp.e(e());
        } else {
            b = ("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(getWeightScaleProductId()) && aa() == 2) ? qrp.b(e()) : false;
        }
        boolean z2 = b && dph.j(a());
        if (!z && z2 && dph.c(f())) {
            return true;
        }
        LogUtil.a("HagridOrMiniViewBean", "isBodyShapeVisible isGone");
        return false;
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String getStringLevelByType(int i) {
        return "";
    }
}
