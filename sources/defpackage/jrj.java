package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.EnvironmentInfo;

/* loaded from: classes5.dex */
public class jrj {
    public static void d(final long j, final String str) {
        LogUtil.a("PowerKitUtil", "applyPowerKit applyPowerKitTime ", Long.valueOf(j), " applyPowerOtaReason", str);
        ThreadPoolManager.d().execute(new Runnable() { // from class: jrj.3
            @Override // java.lang.Runnable
            public void run() {
                PowerKitManager.e().d("ota_upgrade_file", 65535, j, str);
                if (EnvironmentInfo.r()) {
                    return;
                }
                PowerKitManager.e().d("ota_upgrade_file", 512, j, str);
            }
        });
    }

    public static void b(String str) {
        LogUtil.a("PowerKitUtil", "unApplyPowerKit applyPowerOtaReason", str);
        PowerKitManager.e().e("ota_upgrade_file", 65535, str);
        if (EnvironmentInfo.r()) {
            return;
        }
        PowerKitManager.e().e("ota_upgrade_file", 512, str);
    }
}
