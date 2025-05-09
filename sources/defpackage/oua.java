package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class oua extends oth {
    @Override // defpackage.oth
    public int c() {
        return 9;
    }

    @Override // defpackage.oth
    public String b() {
        if (this.e == null) {
            return "";
        }
        return UnitUtil.e(r0 / 60000.0f, 1, ((this.e.requestTotalTime() % 60000) > 0L ? 1 : ((this.e.requestTotalTime() % 60000) == 0L ? 0 : -1)) == 0 ? 0 : 2);
    }

    @Override // defpackage.oth
    public String e() {
        return this.e == null ? "" : nsf.a(R.plurals._2130903106_res_0x7f030042, (int) (this.e.requestTotalTime() / 60000.0f), "");
    }
}
