package defpackage;

import com.huawei.ads.adsrec.IUtilCallback;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;

/* loaded from: classes2.dex */
public class vs {
    public static boolean e() {
        IUtilCallback d = uw.d();
        return d != null && "1".equals(d.getConfig(ConfigMapKeys.BACK_FLOW_SWITCH));
    }
}
