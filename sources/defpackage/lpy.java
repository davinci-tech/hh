package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudGzipFactory;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.EUICCDeviceInfo;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class lpy {
    public static void e(EUICCDeviceInfo eUICCDeviceInfo, ResultCallback<String> resultCallback) {
        if (resultCallback == null) {
            ReleaseLogUtil.c("DEVMGR_CloudSimDeviceInfoManager", "resultCallback is null.");
            return;
        }
        if (eUICCDeviceInfo == null) {
            ReleaseLogUtil.c("DEVMGR_CloudSimDeviceInfoManager", "euiccDeviceInfo is null.");
            return;
        }
        HealthDataCloudGzipFactory healthDataCloudGzipFactory = new HealthDataCloudGzipFactory(BaseApplication.e());
        String str = GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/commonAbility/eSimDevice/getESimDeviceInfo";
        LogUtil.a("CloudSimDeviceInfoManager", "url = ", str);
        HashMap hashMap = new HashMap();
        hashMap.put("eSimDeviceInfo", eUICCDeviceInfo);
        String b = lql.b(healthDataCloudGzipFactory.getBody(hashMap));
        LogUtil.a("CloudSimDeviceInfoManager", "body = ", b);
        lqi.d().b(str, d(healthDataCloudGzipFactory), b, String.class, resultCallback);
    }

    public static void e(lpx lpxVar, ResultCallback<String> resultCallback) {
        HealthDataCloudGzipFactory healthDataCloudGzipFactory = new HealthDataCloudGzipFactory(BaseApplication.e());
        HashMap hashMap = new HashMap();
        String str = GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/commonAbility/eSimDevice/getESimProfile";
        LogUtil.a("CloudSimDeviceInfoManager", "url = ", str);
        hashMap.put("encESimProfileInfo", lpxVar);
        String b = lql.b(healthDataCloudGzipFactory.getBody(hashMap));
        LogUtil.a("CloudSimDeviceInfoManager", "body = ", b);
        lqi.d().b(str, d(healthDataCloudGzipFactory), b, String.class, resultCallback);
    }

    private static Map<String, String> d(HealthDataCloudFactory healthDataCloudFactory) {
        Map<String, String> headers = healthDataCloudFactory.getHeaders();
        headers.put("Content-Encoding", HealthEngineRequestManager.CONTENT_TYPE);
        return headers;
    }
}
