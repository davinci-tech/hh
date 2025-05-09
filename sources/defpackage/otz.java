package defpackage;

import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class otz extends oth {
    @Override // defpackage.oth
    public int c() {
        return 7;
    }

    @Override // defpackage.oth
    public String b() {
        return this.e == null ? "" : UnitUtil.e(this.e.getExtendDataInt("skipNum"), 1, 0);
    }

    @Override // defpackage.oth
    public String e() {
        return this.e == null ? "" : nsf.a(R.plurals._2130903274_res_0x7f0300ea, this.e.getExtendDataInt("skipNum"), "");
    }
}
