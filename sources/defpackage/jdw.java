package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jdw {
    public static void bGh_(Intent intent, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (resolveActivity != null) {
                intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                context.startActivity(intent);
                return;
            } else {
                LogUtil.h("SecurityUtils", "startActivitySecurity can not find activity");
                return;
            }
        }
        LogUtil.h("SecurityUtils", "startActivitySecurity can not get packageManager");
    }
}
