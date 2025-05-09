package defpackage;

import android.content.Context;
import android.os.UserManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jzz {
    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.a("ContactSync", 1, "CommonUtilMethods", "[isRebootLocked] context is null.");
            return false;
        }
        if (context.getSystemService("user") instanceof UserManager) {
            return !((UserManager) r4).isUserUnlocked();
        }
        LogUtil.h("CommonUtilMethods", "object not instanceof UserManager");
        return false;
    }
}
