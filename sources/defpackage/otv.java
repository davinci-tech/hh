package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class otv extends ots {
    @Override // defpackage.ots, defpackage.oth
    public String e() {
        if (UnitUtil.h()) {
            int requestTotalDistance = this.e != null ? this.e.requestTotalDistance() : 0;
            return nsf.a(R.plurals._2130903227_res_0x7f0300bb, requestTotalDistance, UnitUtil.e(requestTotalDistance, 1, 0));
        }
        return nsf.h(R.string._2130841568_res_0x7f020fe0);
    }

    @Override // defpackage.ots, defpackage.oth
    public String b() {
        if (this.e == null) {
            return "";
        }
        int requestTotalDistance = this.e.requestTotalDistance();
        if (requestTotalDistance < 1.0f) {
            return "";
        }
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(requestTotalDistance, 2), 1, 0);
        }
        return UnitUtil.e(requestTotalDistance, 1, 0);
    }

    @Override // defpackage.ots, defpackage.oth
    public String d() {
        if (this.e == null) {
            return "";
        }
        float requestAvgPace = this.e.requestAvgPace() / 10.0f;
        double d = requestAvgPace;
        if (d > 360000.0d || d <= 3.6d) {
            return "";
        }
        if (UnitUtil.h()) {
            requestAvgPace = (float) UnitUtil.d(d, 2);
        }
        return gvv.a(requestAvgPace);
    }

    @Override // defpackage.ots, defpackage.oth
    public String a() {
        if (UnitUtil.h()) {
            return nsf.a(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
        }
        return nsf.a(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
    }
}
