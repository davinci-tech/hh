package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kae {
    public static boolean e() {
        return true;
    }

    public static boolean d() {
        return a("com.huawei.hwvoipservice.PRIVACY_READ") || a("com.huawei.meetime.PRIVACY_READ");
    }

    public static boolean c() {
        return a("android.permission.READ_CONTACTS");
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 29 && CommonUtil.bh();
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactPermissionUtils", "isPermissionGranted: permission is null or empty.");
            return false;
        }
        Context context = BaseApplication.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.checkPermission(str, context.getPackageName()) == 0;
        }
        LogUtil.h("ContactPermissionUtils", "isPermissionGranted: packageManager is null or empty.");
        return false;
    }
}
