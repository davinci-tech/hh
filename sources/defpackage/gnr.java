package defpackage;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class gnr {
    public static boolean b(Context context) {
        return SystemInfo.b(true) || !d(context);
    }

    private static boolean d(Context context) {
        if (context == null) {
            LogUtil.e("Step_StepCounterSupportUtil", "isTelephonyEnable() context is null!!");
            return false;
        }
        Object systemService = context.getSystemService("phone");
        return (systemService instanceof TelephonyManager) && ((TelephonyManager) systemService).getPhoneType() != 0;
    }

    public static boolean a(String str) {
        String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.e());
        if (TextUtils.isEmpty(androidId)) {
            LogUtil.a("Step_StepCounterSupportUtil", " isLocalDevice phoneId is error");
            return false;
        }
        return androidId.equals(str);
    }
}
