package defpackage;

import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class cus {
    private static cus c;
    private static final Object d = new Object();

    public static cus e() {
        cus cusVar;
        synchronized (d) {
            if (c == null) {
                c = new cus();
            }
            cusVar = c;
        }
        return cusVar;
    }

    public boolean d(int i) {
        if (i <= 45) {
            return false;
        }
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        if (pluginInfoByDeviceType == null || pluginInfoByDeviceType.f() == null) {
            return true;
        }
        return pluginInfoByDeviceType.f().bw();
    }
}
