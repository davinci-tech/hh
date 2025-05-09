package defpackage;

import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class qgq extends cfe {
    @Override // defpackage.cfe
    public double ac() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double af() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double ah() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double ai() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double ak() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double am() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double ao() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double aq() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double ar() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double as() {
        return -1.0d;
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public boolean isNewScaleType() {
        return false;
    }

    @Override // defpackage.cfe
    public double o() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double u() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double v() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double x() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double y() {
        return -1.0d;
    }

    @Override // defpackage.cfe
    public double a() {
        return super.a();
    }

    @Override // defpackage.cfe
    public double c() {
        return super.c();
    }

    @Override // defpackage.cfe
    public double al() {
        return super.al();
    }

    @Override // defpackage.cfe
    public double ap() {
        return super.ap();
    }

    @Override // defpackage.cfe
    public double i() {
        return super.i();
    }

    @Override // defpackage.cfe
    public double j() {
        return super.j();
    }

    @Override // defpackage.cfe
    public double d() {
        return super.d();
    }

    @Override // defpackage.cfe
    public double z() {
        return super.z();
    }

    @Override // defpackage.cfe
    public double ab() {
        return super.ab();
    }

    @Override // defpackage.cfe
    public double h() {
        return super.h();
    }

    @Override // defpackage.cfe
    public double b() {
        return super.b();
    }

    @Override // defpackage.cfe
    public double p() {
        return super.p();
    }

    @Override // defpackage.cfe
    public double ad() {
        return super.ad();
    }

    @Override // defpackage.cfe
    public double aj() {
        return super.aj();
    }

    @Override // defpackage.cfe
    public double g() {
        return super.g();
    }

    @Override // defpackage.cfe
    public double f() {
        return super.f();
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String getStringLevelByType(int i) {
        return qrf.a(i, this);
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public double getDoubleOrIntLevelByType(int i) {
        return qrf.e(i, this);
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public double[] getDoubleArrayLevelByType(int i) {
        return qrf.c(i, this);
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public int getFractionDigitByType(int i) {
        if (i == 7) {
            if (Math.round(Math.pow(10.0d, 2.0d) * UnitUtil.a(i())) % 10 != 0) {
                return 2;
            }
        }
        return 1;
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String getWeightScaleProductId() {
        int l = l();
        if (l == 48) {
            return "7a1063dd-0e0f-4a72-9939-461476ff0259";
        }
        if (l == 57) {
            return "34fa0346-d46c-439d-9cb0-2f696618846b";
        }
        if (l == 82) {
            return "e4b0b1d5-2003-4d88-8b5f-c4f64542040b";
        }
        switch (l) {
            case 84:
            case 86:
                return "a8ba095d-4123-43c4-a30a-0240011c58de";
            case 85:
                return "e4b0b1d5-2003-4d88-8b5f-c4f64542040b";
            default:
                return "";
        }
    }

    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String[] getStringArrayLevelByType(int i) {
        return qrf.d(i, this);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0024  */
    @Override // defpackage.cfe, com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isVisible(int r2, boolean r3) {
        /*
            r1 = this;
            r0 = 31
            if (r2 == r0) goto L2c
            r0 = 0
            switch(r2) {
                case 0: goto L19;
                case 1: goto L1f;
                case 2: goto L19;
                case 3: goto L1f;
                case 4: goto L1f;
                case 5: goto L1f;
                case 6: goto L1f;
                case 7: goto L1f;
                case 8: goto L1f;
                case 9: goto L24;
                case 10: goto L1f;
                case 11: goto L19;
                case 12: goto L19;
                case 13: goto L24;
                case 14: goto Ld;
                default: goto L8;
            }
        L8:
            switch(r2) {
                case 25: goto L24;
                case 26: goto L1f;
                case 27: goto L1e;
                default: goto Lb;
            }
        Lb:
            r2 = 1
            return r2
        Ld:
            java.lang.String r2 = "is old scale type"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "DefaultWeightViewBean"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            return r0
        L19:
            boolean r2 = defpackage.qrp.d(r1, r2)
            return r2
        L1e:
            return r0
        L1f:
            boolean r2 = defpackage.qrp.a(r1, r2)
            return r2
        L24:
            if (r3 != 0) goto L2b
            boolean r2 = defpackage.qrp.e(r1, r2)
            return r2
        L2b:
            return r0
        L2c:
            boolean r2 = super.isVisible(r2, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qgq.isVisible(int, boolean):boolean");
    }
}
