package defpackage;

import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes4.dex */
public class feg {
    public static boolean a() {
        if (CommonUtil.bv()) {
            return true;
        }
        return SharedPreferenceManager.a(Integer.toString(CapabilityStatus.AWA_CAP_CODE_DARK_MODE), "share_level", true);
    }

    public static boolean e() {
        if (CommonUtil.bv()) {
            return true;
        }
        return SharedPreferenceManager.a(Integer.toString(CapabilityStatus.AWA_CAP_CODE_DARK_MODE), "share_medal", true);
    }
}
