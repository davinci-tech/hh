package com.huawei.watchface;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.ArrayUtils;
import java.util.List;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10881a = "com.huawei.watchface.a";

    public static boolean a(String str) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = Environment.getApplicationContext().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (ArrayUtils.isEmpty(queryIntentActivities)) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo != null && resolveInfo.activityInfo != null && TextUtils.equals(str, resolveInfo.activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }
}
