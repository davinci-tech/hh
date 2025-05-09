package defpackage;

import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class dyh {
    private static int b(int i) {
        if (i != -2 && i != 5) {
            if (i == 7) {
                return 4;
            }
            if (i != 0 && i != 1 && i != 18 && i != 19 && i != 23 && i != 24 && i != 44 && i != 45) {
                switch (i) {
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        break;
                    case 16:
                        return 4;
                    default:
                        return -1;
                }
            }
        }
        return 1;
    }

    private static int d(int i) {
        if (i != 2 && i != 3 && i != 20 && i != 21) {
            if (i != 32) {
                switch (i) {
                    case 8:
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        return -1;
                }
            }
            return 3;
        }
        return 2;
    }

    public static int e(int i) {
        LogUtil.a("DeviceUtil", "getDeviceClassificationForLabel() deviceType :", Integer.valueOf(i));
        int b = b(i);
        if (b == -1) {
            b = d(i);
        }
        if (b == -1) {
            b = c(i);
        }
        LogUtil.a("DeviceUtil", "getDeviceClassificationForLabel deviceClassification:", Integer.valueOf(b));
        return b;
    }

    private static int c(int i) {
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        if (pluginInfoByDeviceType == null || pluginInfoByDeviceType.f() == null) {
            return -1;
        }
        if (pluginInfoByDeviceType.f().ah() == 1) {
            return (pluginInfoByDeviceType.f().ah() == 7 || pluginInfoByDeviceType.f().ah() == 16) ? 4 : 1;
        }
        if (pluginInfoByDeviceType.f().ah() == 2) {
            return 2;
        }
        return pluginInfoByDeviceType.f().ah() == 5 ? 3 : -1;
    }
}
