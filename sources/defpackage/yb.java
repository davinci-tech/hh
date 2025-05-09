package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.common.utils.CommonConstant;
import health.compact.a.LogUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes8.dex */
public final class yb {
    private static Set<String> c;

    public static Set<String> d(Context context) {
        Set<String> e = e(context);
        String[] a2 = a(context);
        if (CollectionUtils.b(a2)) {
            return e;
        }
        if (LogUtil.e()) {
            LogUtil.d("Bundle_InfoProvider", "Module names are: ", Arrays.toString(a2));
        }
        Set<String> d = AppBundleBuildConfig.d();
        for (String str : a2) {
            if (!str.startsWith("config.")) {
                String str2 = str.split("\\.config\\.")[0];
                if (d.contains(str2)) {
                    e.add(str2);
                }
            }
        }
        return e;
    }

    private static Set<String> e(Context context) {
        if (c == null) {
            c = b(context);
        }
        return new HashSet(c);
    }

    private static Set<String> b(Context context) {
        HashSet hashSet = new HashSet();
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                LogUtil.d("Bundle_InfoProvider", "App has no applicationInfo or metaData");
                return hashSet;
            }
            String string = applicationInfo.metaData.getString("shadow.bundletool.com.android.dynamic.apk.fused.modules");
            if (TextUtils.isEmpty(string)) {
                LogUtil.d("Bundle_InfoProvider", "App has no fused modules.");
                return hashSet;
            }
            Collections.addAll(hashSet, string.split(",", -1));
            hashSet.remove("");
            return hashSet;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("Bundle_InfoProvider", "appInfo is not found in PackageManager, ex=", LogUtil.a(e));
            return hashSet;
        }
    }

    private static String[] a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo != null ? packageInfo.splitNames : CommonConstant.e;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("Bundle_InfoProvider", "packageInfo is not found in PackageManager, ex=", LogUtil.a(e));
            return CommonConstant.e;
        }
    }
}
