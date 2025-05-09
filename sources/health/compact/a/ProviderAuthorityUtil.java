package health.compact.a;

import android.content.Context;
import com.huawei.hihealthservice.hmsauth.HmsAuthApi;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.ezc;

/* loaded from: classes.dex */
public class ProviderAuthorityUtil {
    private ProviderAuthorityUtil() {
    }

    public static boolean b(Context context, String str) {
        if (context == null || str == null) {
            com.huawei.hwlogsmodel.LogUtil.b("ProviderAuthorityUtil", "context or callingPackage is null");
            return false;
        }
        String c = ezc.c(context, str);
        if ((Constants.HW_INTELLIEGNT_PACKAGE.equals(str) && ("3dc48303b51039c862bbbaff279d47bfdd44a3af7919356dbe212fdd3f1c884b".equals(c) || "b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05".equals(c) || "1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c".equals(c))) || (("com.huawei.bone".equals(str) && "9dc745a5f9f60b611ab03a19166ee505834026f4f4a971aa704094f2a2c0072d".equals(c)) || ("com.huawei.camera".equals(str) && ("fac41ea711512ad971465bacb63d269cd2fc90c7ff5c37d043d3fdd89a9e6009".equals(c) || "4e8442c0a958e311edb4c08f3c2fd5654a274aa31ae07fdaed6baea739c8e639".equals(c))))) {
            return true;
        }
        return b(str, c);
    }

    private static boolean b(String str, String str2) {
        if ((ezc.d.equals(str) && ("3dc48303b51039c862bbbaff279d47bfdd44a3af7919356dbe212fdd3f1c884b".equals(str2) || "50787dff857ccc7423352c5273275ad14b21f2b977ca3c124cf4684c1a9bc05c".equals(str2) || "ab6fc17da5e9341ad643d711f64c4c763799cc8d11791170692fb7942c7149a6".equals(str2))) || (("com.android.keyguard".equals(str) && "b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05".equals(str2)) || ("com.android.systemui".equals(str) && "b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05".equals(str2)))) {
            return true;
        }
        return c(str, str2);
    }

    private static boolean c(String str, String str2) {
        return ("com.huawei.android.launcher".equals(str) && "1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c".equals(str2)) || ("com.huawei.hiboard".equals(str) && "b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05".equals(str2)) || (("com.huawei.ohos.health".equals(str) || "com.huawei.ohos.healthservice".equals(str)) && "b2881b2ee3d4efa03342ae07dafc0466b63ede959ec2e2f58c05a54266f45748".equals(str2));
    }

    public static boolean e(String str, int i, boolean z) {
        HmsAuthApi hmsAuthApi = (HmsAuthApi) Services.a("HealthKit", HmsAuthApi.class);
        if (hmsAuthApi != null) {
            int hmsAuth = hmsAuthApi.hmsAuth(str, i, z);
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("ProviderAuthorityUtil", "hmsAuth code = ", Integer.valueOf(hmsAuth));
            if (hmsAuth == 0) {
                return true;
            }
        }
        return false;
    }
}
