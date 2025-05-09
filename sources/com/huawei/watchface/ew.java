package com.huawei.watchface;

import android.content.Context;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class ew {
    public static boolean a(Context context, String[] strArr) {
        for (String str : strArr) {
            boolean a2 = ey.a().a(context, str);
            HwLog.i("CheckAndRequestPermissionUtil", "permissions = " + str + "， hasPermission = " + a2);
            if (!a2) {
                return false;
            }
        }
        return true;
    }
}
