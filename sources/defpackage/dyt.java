package defpackage;

import com.huawei.health.device.api.HwChMeasureControllerApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = HwChMeasureControllerApi.class)
/* loaded from: classes3.dex */
public class dyt implements HwChMeasureControllerApi {
    @Override // com.huawei.health.device.api.HwChMeasureControllerApi
    public void unRegisterCallback(IHealthDeviceCallback iHealthDeviceCallback) {
        cgk.d().e(iHealthDeviceCallback);
    }
}
