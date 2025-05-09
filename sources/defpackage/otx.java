package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;
import java.util.Map;

/* loaded from: classes9.dex */
public class otx extends oth {
    @Override // defpackage.oth
    public int c() {
        return 5;
    }

    @Override // defpackage.oth
    public String b() {
        if (this.e == null) {
            return "";
        }
        int d = nru.d((Map) this.e.requestSportData(), "overall_score", -1);
        if (d < 0 || d > 100) {
            d = 0;
        }
        return UnitUtil.e(d, 1, 0);
    }

    @Override // defpackage.oth
    public String e() {
        return nsf.h(R.string._2130843175_res_0x7f021627);
    }

    @Override // defpackage.oth
    public String a() {
        return this.e == null ? "" : nsf.a(R.plurals._2130903241_res_0x7f0300c9, nru.d((Map) this.e.requestSportData(), "jump_times", -1), new Object[0]);
    }

    @Override // defpackage.oth
    public String d() {
        return this.e == null ? "" : UnitUtil.e(nru.d((Map) this.e.requestSportData(), "jump_times", -1), 1, 0);
    }
}
