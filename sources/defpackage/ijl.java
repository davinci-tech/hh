package defpackage;

import android.content.Context;
import android.os.Build;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HuaweiHealth;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class ijl {
    public static int d(Context context, int i) {
        return ijz.c().e(iio.c().a(i), 0);
    }

    public static boolean e(int i, int i2, Context context) {
        if (context == null || i <= 0) {
            LogUtil.h("ManagerUtil", "checkUserId context is null or userID <= 0");
            return false;
        }
        String a2 = iio.c().a(i2);
        return a2 != null && a2.equals(ijz.c().b(i));
    }

    public static boolean c(int i, int i2, Context context) {
        if (context == null || i2 < 0) {
            return false;
        }
        return iio.c().e(i2, ijz.c().b(i));
    }

    public static int e(Context context, int i, int i2) {
        if (context == null || i < 0) {
            LogUtil.h("ManagerUtil", "getCheckAppUserId appid or context error");
            return 0;
        }
        iio c = iio.c();
        ijz c2 = ijz.c();
        if (i2 <= 0) {
            LogUtil.c("ManagerUtil", "getCheckAppUserId get main userId");
            return c2.e(c.a(i), 0);
        }
        if (c.e(i, c2.b(i2))) {
            return i2;
        }
        LogUtil.h("ManagerUtil", "getCheckAppUserId no such owner from accountInfo, app is ", Integer.valueOf(i), " owner is ", Integer.valueOf(i2));
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(android.content.Context r3, java.lang.String r4) {
        /*
            ijf r0 = defpackage.ijf.d(r3)
            int r0 = r0.a(r4)
            if (r0 <= 0) goto Lb
            return r0
        Lb:
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            if (r1 != 0) goto L1f
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            goto L20
        L1f:
            r1 = r0
        L20:
            int r3 = e(r3, r4)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L5b
            if (r1 == 0) goto L2d
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L5b
            defpackage.ivu.j(r4, r0)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L5b
        L2d:
            if (r1 == 0) goto L36
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L36:
            return r3
        L37:
            r3 = move-exception
            goto L3e
        L39:
            r3 = move-exception
            r1 = r0
            goto L5c
        L3c:
            r3 = move-exception
            r1 = r0
        L3e:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L5b
            java.lang.String r2 = "getDeviceId: "
            r4[r0] = r2     // Catch: java.lang.Throwable -> L5b
            java.lang.String r3 = health.compact.a.LogAnonymous.b(r3)     // Catch: java.lang.Throwable -> L5b
            r2 = 1
            r4[r2] = r3     // Catch: java.lang.Throwable -> L5b
            java.lang.String r3 = "Debug_ManagerUtil"
            com.huawei.hihealth.util.ReleaseLogUtil.d(r3, r4)     // Catch: java.lang.Throwable -> L5b
            if (r1 == 0) goto L5a
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r3, r0)
        L5a:
            return r0
        L5b:
            r3 = move-exception
        L5c:
            if (r1 == 0) goto L65
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L65:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijl.b(android.content.Context, java.lang.String):int");
    }

    private static int e(Context context, String str) {
        int a2 = ijf.d(context).a(str);
        if (a2 > 0 || !FoundationCommonUtil.getAndroidId(context).equals(str)) {
            return a2;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("execute", "insertLocalPhone");
        ivz.d(context).e(OperationKey.HEALTH_APP_PROBABILITY_PROBLEM_85070032.value(), linkedHashMap, false);
        LogUtil.h("ManagerUtil", "getDeviceID deviceUUID = android_ ");
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(32);
        hiDeviceInfo.setDeviceUniqueCode(str);
        hiDeviceInfo.setDeviceName(Build.MANUFACTURER);
        hiDeviceInfo.setFirmwareVersion(Build.VERSION.RELEASE);
        hiDeviceInfo.setHardwareVersion(Build.VERSION.RELEASE);
        hiDeviceInfo.setSoftwareVersion(Build.VERSION.RELEASE);
        hiDeviceInfo.setManufacturer(Build.PRODUCT);
        hiDeviceInfo.setModel(iik.a());
        hiDeviceInfo.setDeviceSN(Build.SERIAL);
        hiDeviceInfo.setDeviceMac(Build.USER);
        int b = (int) ijf.d(context).b(hiDeviceInfo);
        LogUtil.c("ManagerUtil", "getDeviceID deviceUUID = android_ device = ", Integer.valueOf(b));
        return b;
    }

    public static boolean a(Context context, String str) {
        return str.equals(iio.c().a(iip.b().a(HuaweiHealth.b()))) && str.equals(iio.c().a(iip.b().a(ivw.a(context))));
    }
}
