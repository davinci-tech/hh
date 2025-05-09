package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.text.TextUtils;
import com.huawei.hianalytics.core.transport.Utils;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class tot {
    private static Context b;

    public static void a(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
        }
    }

    public static Context a() {
        return b;
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 255;
    }

    public static boolean a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            tos.e(Utils.TAG, "isValidSrcPkgName srcPkgName is null");
            return false;
        }
        tos.a(Utils.TAG, "client srcPkgNames:" + str);
        String[] packagesForUid = a().getPackageManager().getPackagesForUid(i);
        if (packagesForUid != null) {
            tos.a(Utils.TAG, "calling uid srcPkgNames:" + Arrays.toString(packagesForUid));
            int length = packagesForUid.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = packagesForUid[i2];
                if ("com.huawei.health".equals(str2) || str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int d() {
        return Binder.getCallingUid();
    }

    public static int e() {
        return Binder.getCallingPid();
    }

    public static Intent fcY_(Intent intent) {
        PackageManager packageManager = a().getPackageManager();
        if (packageManager == null) {
            tos.e(Utils.TAG, "getPackageManager is null");
            return null;
        }
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            tos.d(Utils.TAG, "implicitIntent List are null");
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }
}
