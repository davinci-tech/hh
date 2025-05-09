package defpackage;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jdp {
    public static boolean c(Context context) {
        if (context != null) {
            return GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0;
        }
        LogUtil.h("GoogleServiceUtils", "isInstallGooglePlayServicesNoDialog context is null");
        return false;
    }
}
