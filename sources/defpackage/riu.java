package defpackage;

import android.os.Looper;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class riu {
    public static Map<String, String> c() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtil.h("AdvertisingUtils", "this is main thread, getUserAdvertisingInfo error.");
            throw new IllegalThreadStateException("getUserAdvertisingInfo not allow InMainThread, please use this in new thread!");
        }
        HashMap hashMap = new HashMap(2);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(BaseApplication.getContext());
            if (advertisingIdInfo != null) {
                LogUtil.a("AdvertisingUtils", "info id=" + advertisingIdInfo.getId() + ", isLimitAdTrackingEnabled=" + advertisingIdInfo.isLimitAdTrackingEnabled());
                hashMap.put("OAID", advertisingIdInfo.getId());
                hashMap.put("IS_LIMIT_AD_TRACKING_ENABLED", advertisingIdInfo.isLimitAdTrackingEnabled() + "");
            }
        } catch (IOException unused) {
            LogUtil.b("AdvertisingUtils", "getAdvertisingIdInfo Exception");
        }
        return hashMap;
    }
}
