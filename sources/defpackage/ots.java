package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class ots extends oth {
    private boolean d(int i) {
        return i == 259 || i == 265 || i == 260;
    }

    @Override // defpackage.oth
    public int c() {
        return 0;
    }

    @Override // defpackage.oth
    public String b() {
        if (this.e == null) {
            return "";
        }
        double requestTotalDistance = this.e.requestTotalDistance();
        if (UnitUtil.h()) {
            requestTotalDistance = UnitUtil.e(requestTotalDistance, 3);
        }
        return UnitUtil.e(requestTotalDistance / 1000.0d, 1, 2);
    }

    @Override // defpackage.oth
    public String e() {
        return nsf.h(UnitUtil.h() ? R.string._2130841383_res_0x7f020f27 : R.string._2130844082_res_0x7f0219b2);
    }

    @Override // defpackage.oth
    public String a() {
        return this.e == null ? "" : e(this.e.requestSportType());
    }

    @Override // defpackage.oth
    public String d() {
        if (this.e == null) {
            return "";
        }
        float requestAvgPace = this.e.requestAvgPace();
        if (d(this.e.requestSportType())) {
            return requestAvgPace != 0.0f ? e(1, (1.0f / requestAvgPace) * 3600.0f) : "";
        }
        return e(0, requestAvgPace);
    }

    private String e(int i, double d) {
        String d2 = d(d);
        return d2 != null ? d2 : i != 0 ? i != 1 ? "" : UnitUtil.e(kpj.d(false, 3, d), 1, 2) : (d > 360000.0d || d <= 3.6d) ? "" : gvv.a((float) kpj.d(true, 3, d));
    }

    private String e(int i) {
        if (i != 264) {
            if (i != 265) {
                switch (i) {
                    case 257:
                    case 258:
                        break;
                    case 259:
                        break;
                    default:
                        return "";
                }
            }
            return j();
        }
        return i();
    }

    private String j() {
        if (UnitUtil.h()) {
            return nsf.h(R.string._2130844079_res_0x7f0219af);
        }
        return nsf.h(R.string._2130844078_res_0x7f0219ae);
    }

    private String i() {
        if (UnitUtil.h()) {
            return "/" + nsf.h(R.string._2130841383_res_0x7f020f27);
        }
        return "/" + nsf.h(R.string._2130841382_res_0x7f020f26);
    }

    private String d(double d) {
        boolean z = d > -1.0E-4d && d < 1.0E-4d;
        if (d == 0.0d || z) {
            return "";
        }
        return null;
    }
}
