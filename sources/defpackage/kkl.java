package defpackage;

import android.app.ActivityManager;
import android.os.Binder;
import com.huawei.android.app.ActivityManagerEx;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class kkl {
    public static void a(boolean z) {
        if (!CommonUtil.az()) {
            LogUtil.a("ProcessManagerUtil", "The device is not provided by Harmony.");
            return;
        }
        int c = c(ProcessUtil.b(":PhoneService"));
        if (c != 0) {
            ReleaseLogUtil.e("R_ProcessManagerUtil", "PhoneService set Processing: ", Boolean.valueOf(z));
            try {
                ActivityManagerEx.setProcessForeground(new Binder(), c, z);
            } catch (Exception e) {
                LogUtil.b("ProcessManagerUtil", "setProcessForeground fail ", e.getMessage());
            }
        }
    }

    private static int c(String str) {
        List<ActivityManager.RunningAppProcessInfo> c = c();
        int i = 0;
        if (c == null) {
            LogUtil.a("ProcessManagerUtil", "processes is null");
            return 0;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : c) {
            if (runningAppProcessInfo.processName.equals(str)) {
                i = runningAppProcessInfo.pid;
            }
        }
        return i;
    }

    private static List<ActivityManager.RunningAppProcessInfo> c() {
        ActivityManager xx_ = CommonUtils.xx_();
        if (xx_ != null) {
            return xx_.getRunningAppProcesses();
        }
        return null;
    }
}
