package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class nxu {
    public static void cSe_(Activity activity, int i) {
        if (activity == null) {
            ReleaseLogUtil.d("DEVMGR_ContactConstantsAndUtils", "startAppSettingActivityForResult, activity is null");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            ReleaseLogUtil.d("DEVMGR_ContactConstantsAndUtils", "startAppSettingActivityForResult, resolveActivity is null");
            return;
        }
        try {
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException unused) {
            ReleaseLogUtil.c("DEVMGR_ContactConstantsAndUtils", "startAppSettingActivityForResult ActivityNotFoundException");
        }
    }
}
