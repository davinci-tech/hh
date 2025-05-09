package defpackage;

import android.content.Context;
import android.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class nla {
    public static int e(boolean z, int i) {
        return b(i, (z || !nsn.ag(BaseApplication.e())) ? 4 : i == 3 ? 6 : 8);
    }

    public static int b(int i, int i2) {
        Context wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            wa_ = BaseApplication.e();
        }
        int f = new HealthColumnSystem(wa_, i).f();
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        return (((int) UnitUtil.a(r1.d(Math.min(i2, f)), 0)) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
    }
}
