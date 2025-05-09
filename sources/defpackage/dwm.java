package defpackage;

import android.app.ActivityManager;
import android.os.Binder;
import com.huawei.android.app.ActivityManagerEx;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class dwm {
    public static void b(boolean z) {
        int c = due.a().c();
        LogUtil.a("HWhealthLinkage_", "trackState = ", Integer.valueOf(c));
        if (!z || c == 1 || c == 2) {
            c(z);
        }
    }

    public static void c(boolean z) {
        if (!bky.b()) {
            LogUtil.a("HWhealthLinkage_", "The device is not provided by Harmony.");
            return;
        }
        int c = c(ProcessUtil.b(":PhoneService"));
        if (c != 0) {
            ReleaseLogUtil.e("HWhealthLinkage_", "PhoneService set Processing ：", Boolean.valueOf(z));
            try {
                ActivityManagerEx.setProcessForeground(new Binder(), c, z);
                LogUtil.a("HWhealthLinkage_", "setProcessForeground end.");
            } catch (Exception e) {
                LogUtil.b("HWhealthLinkage_", "setProcessForeground fail ", e.getMessage());
                sqo.w("setProcessForeground fail " + e.getMessage());
            }
        }
    }

    private static int c(String str) {
        List<ActivityManager.RunningAppProcessInfo> c = c();
        int i = 0;
        if (c == null) {
            LogUtil.a("HWhealthLinkage_", "processes is null");
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
