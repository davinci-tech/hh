package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class oak {
    private static oak c;
    private static final Object d = new Object();

    private oak() {
    }

    public static oak b() {
        oak oakVar;
        LogUtil.a("WearOsConnectionInteractors", "getInstance");
        synchronized (d) {
            LogUtil.a("WearOsConnectionInteractors", "getInstance() LOCK");
            if (c == null) {
                c = new oak();
            }
            LogUtil.a("WearOsConnectionInteractors", "getInstance");
            oakVar = c;
        }
        return oakVar;
    }

    public String c() {
        PackageInfo packageInfo;
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app.cn", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("WearOsConnectionInteractors", "com.google.android.wearable.app.cn not installed");
            try {
                packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app", 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                LogUtil.a("WearOsConnectionInteractors", "com.google.android.wearable.app not installed");
                packageInfo = null;
            }
        }
        return packageInfo == null ? "" : packageInfo.packageName;
    }
}
