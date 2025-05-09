package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class shx {
    private gmz d = gmz.d();

    public boolean b(int i) {
        boolean equals = "true".equals(this.d.c(i));
        LogUtil.a("HealthKitManager", "isLinked:", Boolean.valueOf(equals));
        return equals;
    }

    public void b(int i, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.a("HealthKitManager", "setLinked privacyId = ", Integer.valueOf(i), ", isLinked = ", Boolean.valueOf(z));
        this.d.c(i, z, String.valueOf(i), iBaseResponseCallback);
    }
}
