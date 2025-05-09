package com.huawei.watchface;

import com.huawei.android.os.SystemPropertiesEx;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class dq {
    public static String a(String str, String str2) {
        try {
            return SystemPropertiesEx.get(str, str2);
        } catch (Throwable th) {
            HwLog.e("SystemPropertiesExHelper", "get error " + HwLog.printException(th));
            return null;
        }
    }

    public static int a(String str, int i) {
        try {
            return SystemPropertiesEx.getInt(str, i);
        } catch (Throwable th) {
            HwLog.e("SystemPropertiesExHelper", "getInt error " + HwLog.printException(th));
            return 0;
        }
    }
}
