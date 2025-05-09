package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class pet {
    public static boolean e(Context context) {
        boolean z = PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED;
        LogUtil.a("CheckGpsPermissionUtil", "isHaveFrontPermission :", Boolean.valueOf(z));
        return z;
    }

    public static boolean c(Context context) {
        boolean z = PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) == PermissionUtil.PermissionResult.GRANTED;
        LogUtil.a("CheckGpsPermissionUtil", "isHaveBackGroundPermission :", Boolean.valueOf(z));
        return z;
    }
}
