package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Arrays;

/* loaded from: classes.dex */
public class jdi {
    public static boolean c(Context context, String[] strArr) {
        for (String str : strArr) {
            boolean c = jeg.d().c(context, str);
            LogUtil.a("CheckAndRequestPermissionUtil", "permissions = ", str, " , hasPermission = ", Boolean.valueOf(c));
            if (!c) {
                return false;
            }
        }
        return true;
    }

    public static void bFL_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        LogUtil.a("CheckAndRequestPermissionUtil", "enter requestPermission(): activity = ", activity, ",permissions", Arrays.toString(strArr), ",action = ", permissionsResultAction);
        if (activity != null && strArr != null) {
            jeg.d().bGx_(activity, strArr, permissionsResultAction);
            CommonUtil.a(activity, strArr);
        } else {
            if (permissionsResultAction == null) {
                return;
            }
            permissionsResultAction.onDenied("onDenied");
        }
    }

    public static void bFM_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction, int i) {
        LogUtil.a("CheckAndRequestPermissionUtil", "enter requestPermission(): activity = ", activity, ",permissions", Arrays.toString(strArr), ",action = ", permissionsResultAction);
        if (activity == null || strArr == null) {
            LogUtil.b("CheckAndRequestPermissionUtil", "requestPermission null");
            if (permissionsResultAction == null) {
                return;
            }
            permissionsResultAction.onDenied("onDenied");
            return;
        }
        jeg.d().bGy_(activity, strArr, permissionsResultAction, i);
        CommonUtil.a(activity, strArr);
    }
}
