package defpackage;

import com.huawei.health.device.api.MeasureKitManagerApi;
import com.huawei.hihealth.device.open.MeasureKit;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.util.LogUtil;

@ApiDefine(uri = MeasureKitManagerApi.class)
/* loaded from: classes3.dex */
public class dyu implements MeasureKitManagerApi {
    @Override // com.huawei.health.device.api.MeasureKitManagerApi
    public void registerExternalKit(String str, String str2) {
        LogUtil.d("MeasureKitManagerImpl", "registerExternalKit");
        cfl.a().e(str, str2);
    }

    @Override // com.huawei.health.device.api.MeasureKitManagerApi
    public MeasureKit getHealthDeviceKitUniversal(String str) {
        LogUtil.d("MeasureKitManagerImpl", "getHealthDeviceKitUniversal");
        return cfl.a().c(str);
    }

    @Override // com.huawei.health.device.api.MeasureKitManagerApi
    public com.huawei.health.device.open.MeasureKit getHealthDeviceKit(String str) {
        return cfl.a().e(str);
    }
}
