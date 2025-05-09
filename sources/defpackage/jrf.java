package defpackage;

import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jrf {
    private static final String d = "HealthCloudUtil";

    public static int b(DeviceInfo deviceInfo) {
        int i;
        if (deviceInfo == null) {
            LogUtil.e(d, "getHealthCloudId deviceInfo is null");
            return -1;
        }
        if (CompileParameterUtil.a("IS_RELEASE_VERSION", false) || deviceInfo.getProductType() < 55) {
            LogUtil.c(d, "getHealthCloudId productType:", Integer.valueOf(deviceInfo.getProductType()));
            return 0;
        }
        String e = e(deviceInfo.getProductType());
        boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(e);
        String str = d;
        LogUtil.c(str, "getHealthCloudId uuid:", e, "; isPluginAvailable", Boolean.valueOf(isResourcesAvailable));
        if (!isResourcesAvailable) {
            return -1;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(e);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.a(str, "getHealthCloudId info or WearDeviceInfo is null");
            return -1;
        }
        cvj f = pluginInfoByUuid.f();
        int au = f.au();
        try {
            String string = new JSONObject(f.b()).getString("ai_tips_product_" + deviceInfo.getHiLinkDeviceId());
            LogUtil.c(str, "getHealthCloudId hiLinkDeviceInfo is:", string);
            JSONObject jSONObject = new JSONObject(string);
            if (!jSONObject.has("health_cloud_productId")) {
                return au;
            }
            int i2 = jSONObject.getInt("health_cloud_productId");
            try {
                LogUtil.c(str, "healthCloudId is ", Integer.valueOf(i2));
                return i2;
            } catch (JSONException unused) {
                i = i2;
                LogUtil.e(d, "getIntelligentHomeSwitchFromCloud JSONException");
                return i;
            }
        } catch (JSONException unused2) {
            i = au;
        }
    }

    private static String e(int i) {
        String str = "";
        for (Map.Entry<String, Integer> entry : cup.b().entrySet()) {
            if (entry != null && entry.getKey() != null) {
                str = entry.getKey();
                if ((cup.b().get(str) != null ? cup.b().get(str).intValue() : 0) == i) {
                    break;
                }
            }
        }
        return str;
    }
}
