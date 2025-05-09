package defpackage;

import com.huawei.health.device.api.HwWspMeasureControllerApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = HwWspMeasureControllerApi.class)
/* loaded from: classes3.dex */
public class dyx implements HwWspMeasureControllerApi {
    @Override // com.huawei.health.device.api.HwWspMeasureControllerApi
    public void unRegisterCallback(IHealthDeviceCallback iHealthDeviceCallback) {
        cgt.e().b(iHealthDeviceCallback);
    }

    @Override // com.huawei.health.device.api.HwWspMeasureControllerApi
    public int getState() {
        return cgt.e().k();
    }
}
