package defpackage;

import com.huawei.devicepair.api.BasePairManagerApi;
import com.huawei.devicepair.api.MessageNotificationApi;
import com.huawei.devicepair.api.PermissionsApi;
import com.huawei.devicepair.api.UpdateDeviceApi;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class bgb {
    public static BasePairManagerApi d() {
        LogUtil.a("PairApiManager", "getConnectApi");
        return (BasePairManagerApi) Services.a("DevicePairJs", BasePairManagerApi.class);
    }

    public static MessageNotificationApi b() {
        LogUtil.a("PairApiManager", "getMessageNotificationApi");
        return (MessageNotificationApi) Services.a("DevicePairJs", MessageNotificationApi.class);
    }

    public static PermissionsApi a() {
        LogUtil.a("PairApiManager", "getPermissionsApi");
        return (PermissionsApi) Services.a("DevicePairPermissionsJs", PermissionsApi.class);
    }

    public static UpdateDeviceApi e() {
        LogUtil.a("PairApiManager", "getUpdateDeviceApi");
        return (UpdateDeviceApi) Services.a("DevicePairJs", UpdateDeviceApi.class);
    }

    public static DownloadManagerApi c() {
        LogUtil.a("PairApiManager", "getDownloadManagerApi");
        return (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
    }
}
