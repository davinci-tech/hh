package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public class beq {
    public static String c() {
        PackageInfo packageInfo;
        Context e = bec.e();
        try {
            packageInfo = e.getPackageManager().getPackageInfo(e.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            bes.d("OsInfoUtil", "getAppVersionName: " + e2.getMessage());
            packageInfo = null;
        }
        String str = packageInfo != null ? packageInfo.versionName : "";
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String a() {
        return Build.MODEL;
    }

    public static String e() {
        return Build.VERSION.RELEASE;
    }

    public static String d() {
        return bet.b() ? "EmotionUI_11.1.0" : beo.b(bet.d() ? "ro.build.version.magic" : "ro.build.version.emui", "");
    }

    public static String b() {
        return bec.e().getPackageName();
    }

    public static String f() {
        if (TextUtils.isEmpty(bew.d("CodeValuePlatform_deviceUuid", ""))) {
            bew.c("CodeValuePlatform_deviceUuid", UUID.randomUUID().toString().replace(Constants.LINK, "").toLowerCase(Locale.ROOT));
        }
        return bew.d("CodeValuePlatform_deviceUuid", "");
    }
}
