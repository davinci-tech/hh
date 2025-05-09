package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiDeviceInfo;
import health.compact.a.util.LogUtil;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class iwv {
    private static boolean c(String str) {
        return str != null;
    }

    public static boolean e(HiDeviceInfo hiDeviceInfo) {
        if (hiDeviceInfo == null) {
            return false;
        }
        boolean c = c(hiDeviceInfo.getDeviceUniqueCode());
        if (b(hiDeviceInfo.getSubProdId())) {
            return c;
        }
        LogUtil.d("HiH_HiDeviceValid", "the subProdId is invalid:", hiDeviceInfo.getSubProdId());
        return false;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (str.length() != 2) {
            return false;
        }
        return Pattern.compile("[0-9A-Fa-f]{2}").matcher(str).find();
    }
}
