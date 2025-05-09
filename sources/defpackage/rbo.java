package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class rbo {
    private static int d = -1;

    public static int d() {
        int i = d;
        if (i > 0) {
            return i;
        }
        a();
        return d;
    }

    private static void a() {
        Context context = BaseApplication.getContext();
        try {
            if (context.getPackageManager().getPackageInfo(context.getPackageName(), 0) != null) {
                d = 20504000;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            d = -1;
            LogUtil.h("VersionInfo", "PackageManager.NameNotFoundException");
        }
    }
}
