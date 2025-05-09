package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class otu extends ots {
    @Override // defpackage.ots, defpackage.oth
    public String d() {
        if (this.e == null) {
            return "";
        }
        float requestCreepingWave = this.e.requestCreepingWave() / 10.0f;
        if (Float.compare(requestCreepingWave, 0.0f) == 0) {
            return "";
        }
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(requestCreepingWave, 1), 1, 2);
        }
        return UnitUtil.e(requestCreepingWave, 1, 1);
    }

    @Override // defpackage.ots, defpackage.oth
    public String a() {
        if (UnitUtil.h()) {
            return nsf.a(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(UnitUtil.e(this.e != null ? this.e.requestCreepingWave() / 10.0f : 0.0f, 1)), new Object[0]);
        }
        return nsf.h(R.string._2130841568_res_0x7f020fe0);
    }
}
