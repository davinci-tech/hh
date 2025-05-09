package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.hihealthkit.context.OutOfBandData;
import com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public class iov {
    private static int b(OutOfBandData outOfBandData, Context context, int i, boolean z) {
        LogUtil.a("AppAuthUtil", "enter h5 engine");
        if (!(outOfBandData instanceof H5ProAppInfo)) {
            ReleaseLogUtil.c("HiH_AppAuthUtil", "invalid app type");
            return 2;
        }
        H5ProAppInfo h5ProAppInfo = (H5ProAppInfo) outOfBandData;
        boolean f = SharedPreferenceManager.f(context, "THIRD_H5_KIT_AUTH_KEY");
        if (cez.c() && !f) {
            ReleaseLogUtil.e("HiH_AppAuthUtil", "is beta, test or third version and close auth");
            return 0;
        }
        if (bzs.e().isSuperTrustedMiniProgram(h5ProAppInfo.getPkgName(), h5ProAppInfo.getCertPrint())) {
            ReleaseLogUtil.e("HiH_AppAuthUtil", "default allowed h5 app");
            return 0;
        }
        if ((i == 101204 || i == 101201) && iqw.d(h5ProAppInfo)) {
            ReleaseLogUtil.e("HiH_AppAuthUtil", "device control permission allows the trustlist to be used");
            return 0;
        }
        int a2 = ipl.c(context).a(h5ProAppInfo.getAccessToken(), h5ProAppInfo.getAppId(), i, z);
        ReleaseLogUtil.e("HiH_AppAuthUtil", "validateH5AppResult: sampleType = ", Integer.valueOf(i), ", isRead = ", Boolean.valueOf(z), ", auth result = ", Integer.valueOf(a2));
        if (a2 == 0) {
            return 8;
        }
        return a2;
    }

    public static int b(Context context, OutOfBandData outOfBandData, AppStatusHelper appStatusHelper, int i, int i2) throws RemoteException {
        if (iqw.c(context) || iqw.b(context)) {
            return a(context, outOfBandData, appStatusHelper, i, i2);
        }
        if (iqw.d(context)) {
            return c(context, outOfBandData, i, i2);
        }
        ReleaseLogUtil.c("HiH_AppAuthUtil", "checkAuthPermission, unmatched app type, param invalid");
        return 2;
    }

    private static int a(Context context, OutOfBandData outOfBandData, AppStatusHelper appStatusHelper, int i, int i2) {
        int b = b(outOfBandData, context, i, i2 == 1);
        if (b == 8) {
            return d(outOfBandData, appStatusHelper, i, i2);
        }
        ReleaseLogUtil.e("HiH_AppAuthUtil", "H5 auth result: ", Integer.valueOf(b));
        return b;
    }

    public static int c(Context context, OutOfBandData outOfBandData, int i, int i2) throws RemoteException {
        return new inr(context, i, i2).c(outOfBandData);
    }

    public static Set<String> d() {
        HashSet hashSet = new HashSet();
        hashSet.add("deleteSample");
        hashSet.add("deleteSamples");
        hashSet.add("saveSample");
        hashSet.add("saveSamples");
        hashSet.add("sendDeviceCommand");
        hashSet.add("getSwitch");
        hashSet.add("pushMsgToWearable");
        hashSet.add("writeToWearable");
        hashSet.add("sendDeviceControlinstruction");
        return hashSet;
    }

    private static int d(OutOfBandData outOfBandData, AppStatusHelper appStatusHelper, int i, int i2) {
        if (!(outOfBandData instanceof H5ProAppInfo)) {
            ReleaseLogUtil.d("HiH_AppAuthUtil", "invalid app type");
            return 2;
        }
        return appStatusHelper.c(((H5ProAppInfo) outOfBandData).getAppId(), i2, i);
    }

    public static boolean a(OutOfBandData outOfBandData, Context context, AppStatusHelper appStatusHelper) {
        if (iqw.c(context) || iqw.b(context)) {
            return a(context, outOfBandData, appStatusHelper, 101201, 1) == 0;
        }
        return iqw.d(context);
    }
}
