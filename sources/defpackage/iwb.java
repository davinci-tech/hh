package defpackage;

import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class iwb {
    private static final List<String> c;

    static {
        ArrayList arrayList = new ArrayList();
        c = arrayList;
        arrayList.addAll(Arrays.asList("com.huawei.health", "com.huawei.hmos.health", "com.huawei.hmos.health.core", "com.huawei.iossporthealth", "com.aspiegel.health", BaseApplication.APP_PACKAGE_GOOGLE_HEALTH));
    }

    public static boolean b(int i, int i2) {
        if (i == i2) {
            return true;
        }
        ikv f = iis.d().f(i);
        HiHealthClient hiHealthClient = new HiHealthClient();
        hiHealthClient.setHiAppInfo(iip.b().c(f.e()));
        hiHealthClient.setHiDeviceInfo(ijf.d(com.huawei.haf.application.BaseApplication.e()).a(f.d()));
        ikv f2 = iis.d().f(i2);
        HiHealthClient hiHealthClient2 = new HiHealthClient();
        hiHealthClient2.setHiAppInfo(iip.b().c(f2.e()));
        hiHealthClient2.setHiDeviceInfo(ijf.d(com.huawei.haf.application.BaseApplication.e()).a(f2.d()));
        boolean e = e(hiHealthClient, hiHealthClient2);
        LogUtil.a("HiDataSourceUtil", "clientId1= ", Integer.valueOf(i), " clientId2= ", Integer.valueOf(i2), "isSameDataSource result:", Boolean.valueOf(e));
        return e;
    }

    public static boolean e(HiHealthClient hiHealthClient, HiHealthClient hiHealthClient2) {
        if (hiHealthClient != null && hiHealthClient2 != null) {
            return c(hiHealthClient.getHiAppInfo()) && c(hiHealthClient2.getHiAppInfo()) && b(hiHealthClient.getHiDeviceInfo(), hiHealthClient2.getHiDeviceInfo());
        }
        LogUtil.a("HiDataSourceUtil", "the client is null, return false");
        return false;
    }

    private static boolean c(HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            LogUtil.a("HiDataSourceUtil", "appInfo is null, return false");
            return false;
        }
        return c.contains(hiAppInfo.getPackageName());
    }

    private static boolean b(HiDeviceInfo hiDeviceInfo, HiDeviceInfo hiDeviceInfo2) {
        if (hiDeviceInfo == null || hiDeviceInfo2 == null || hiDeviceInfo.getDeviceUniqueCode() == null || hiDeviceInfo2.getDeviceUniqueCode() == null) {
            return false;
        }
        return hiDeviceInfo.getDeviceUniqueCode().toUpperCase().replaceAll(":", "").replaceAll("#ANDROID21", "").equals(hiDeviceInfo2.getDeviceUniqueCode().toUpperCase().replaceAll(":", "").replaceAll("#ANDROID21", ""));
    }
}
