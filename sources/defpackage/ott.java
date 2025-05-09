package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class ott extends oth {
    @Override // defpackage.oth
    public int c() {
        return 8;
    }

    @Override // defpackage.oth
    public String b() {
        if (this.e == null) {
            return "";
        }
        double requestTotalDescent = this.e.requestTotalDescent() / 10.0f;
        if (UnitUtil.h()) {
            requestTotalDescent = UnitUtil.e(requestTotalDescent, 1);
        }
        return UnitUtil.e(requestTotalDescent, 1, 1);
    }

    @Override // defpackage.oth
    public String e() {
        if (this.e == null) {
            return "";
        }
        float requestTotalDescent = this.e.requestTotalDescent() / 10.0f;
        if (UnitUtil.h()) {
            return nsf.a(R.plurals._2130903306_res_0x7f03010a, (int) UnitUtil.e(requestTotalDescent, 1), "");
        }
        return nsf.a(R.plurals._2130903307_res_0x7f03010b, (int) requestTotalDescent, "");
    }
}
