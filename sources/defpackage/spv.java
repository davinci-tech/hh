package defpackage;

import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class spv {
    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("StorageAction", "registerNotificationAction, pkgName or action is null");
        } else {
            blw.e("wearlink_sp_pull_service", str, str2);
            LogUtil.c("StorageAction", "registerNotificationAction end");
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("StorageAction", "queryAction, pkgName is null");
            return "";
        }
        String b = blw.b("wearlink_sp_pull_service", str, "");
        if (!TextUtils.isEmpty(b)) {
            LogUtil.c("StorageAction", "queryAction serviceAction:", b);
            return b;
        }
        return a(str);
    }

    private static String a(String str) {
        Cursor sV_ = blg.sV_(BaseApplication.e(), String.valueOf(1000), "pull_service", "packageName='" + str + "'");
        String str2 = "";
        if (sV_ == null) {
            LogUtil.a("StorageAction", "cursor is null");
            return "";
        }
        if (sV_.moveToNext()) {
            str2 = sV_.getString(sV_.getColumnIndex("serviceAction"));
            LogUtil.c("StorageAction", "getActionByPkgName: ", str2);
        }
        sV_.close();
        return str2;
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("StorageAction", "unregisterNotificationAction, pkgName is null");
            return;
        }
        d(str);
        blw.b("wearlink_sp_pull_service", str);
        LogUtil.c("StorageAction", "unregisterNotificationAction end");
    }

    private static void d(String str) {
        blg.d(BaseApplication.e(), String.valueOf(1000), "pull_service", "packageName='" + str + "'");
    }
}
