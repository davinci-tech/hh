package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jdm {
    public static boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("CommentUtil", "isInstalled param is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.h("CommentUtil", "isInstalled ,PackageManager is null");
            return false;
        }
        try {
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("CommentUtil", "isInstalled fail ", e.getMessage());
        }
        return packageManager.getPackageInfo(str, 0) != null;
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("CommentUtil", "isSupportCommon param is null");
            return false;
        }
        Uri parse = Uri.parse("market://details?id=com.huawei.health");
        if (parse == null) {
            LogUtil.h("CommentUtil", "uri is null");
            return false;
        }
        new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse).setPackage(str);
        return b(context, str);
    }
}
