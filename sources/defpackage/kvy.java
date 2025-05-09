package defpackage;

import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class kvy {
    public static final String c = BaseApplication.d();
    private static final int e = Process.myUid();
    private static final int b = Process.myPid();

    private static boolean a() {
        if (Binder.getCallingPid() == b) {
            LogUtil.a("SMART_SmartRemoteFilter", "Binder.getCallingPid() == MY_PID");
            return true;
        }
        if (Binder.getCallingUid() != e) {
            return false;
        }
        LogUtil.a("SMART_SmartRemoteFilter", "Binder.getCallingUid() == MY_UID");
        return true;
    }

    public static boolean b() {
        if (a()) {
            return true;
        }
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        if (packageManager == null) {
            LogUtil.a("SMART_SmartRemoteFilter", "packageManager null not check");
            return false;
        }
        boolean aw = CommonUtil.aw();
        String[] packagesForUid = packageManager.getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null) {
            LogUtil.a("SMART_SmartRemoteFilter", "packages null not check ");
            return false;
        }
        for (String str : packagesForUid) {
            if (c.equals(str)) {
                LogUtil.a("SMART_SmartRemoteFilter", "the process belongs to mime,uid recognized failed,force ret true");
                return true;
            }
            if (aw) {
                if ("com.android.keyguard".equals(str) || "com.android.systemui".equals(str)) {
                    LogUtil.a("SMART_SmartRemoteFilter", "the process belongs to mime,uid true ", str);
                    return true;
                }
                if (Constants.HW_INTELLIEGNT_PACKAGE.equals(str) || "com.huawei.android.launcher".equals(str)) {
                    LogUtil.a("SMART_SmartRemoteFilter", "the INTELLIGENT_PACKAGE belongs to mime,uid true ", str);
                    return true;
                }
                if ("com.huawei.hiboard".equals(str) || "com.huawei.bone".equals(str)) {
                    LogUtil.a("SMART_SmartRemoteFilter", "the INTELLIGENT_PACKAGE belongs to mime,uid true ", str);
                    return true;
                }
                if (!"com.android.gallery3d".equals(str) && !"com.huawei.camera".equals(str)) {
                    return false;
                }
                LogUtil.a("SMART_SmartRemoteFilter", "the INTELLIGENT_PACKAGE belongs to mime,uid true ", str);
                return true;
            }
            if (HsfSignValidator.c(str)) {
                LogUtil.a("SMART_SmartRemoteFilter", "calling check true");
                return true;
            }
        }
        LogUtil.a("SMART_SmartRemoteFilter", "calling check false");
        return false;
    }
}
