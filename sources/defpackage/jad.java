package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;

/* loaded from: classes5.dex */
public class jad {
    public static boolean d(int i) {
        boolean z = true;
        if (Utils.o() ? jab.b.get(Integer.valueOf(i)).intValue() != 1 : izw.b.get(Integer.valueOf(i)).intValue() != 1) {
            z = false;
        }
        LogUtil.a("VersionConfig", "isFeatureSupport(): featureId is ", Integer.valueOf(i), ",isSupport is ", Boolean.valueOf(z));
        return z;
    }
}
