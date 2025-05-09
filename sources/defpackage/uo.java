package defpackage;

import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.plugindevice.UploadSmartLifeApi;
import health.compact.a.CommonUtil;

@ApiDefine(uri = UploadSmartLifeApi.class)
/* loaded from: classes2.dex */
public class uo implements UploadSmartLifeApi {
    @Override // com.huawei.plugindevice.UploadSmartLifeApi
    public void upload(String str, String str2, MeasurableDevice measurableDevice) {
        LogUtil.a("UploadSmartLifeApiImpl", "enter uploadDeviceToCloud");
        ceo.d().a(str, str2, measurableDevice);
    }

    @Override // com.huawei.plugindevice.UploadSmartLifeApi
    public void upload(String str, String str2, String str3, MeasurableDevice measurableDevice) {
        LogUtil.a("UploadSmartLifeApiImpl", "enter uploadDeviceToCloud:", CommonUtil.l(str3));
        ceo.d().a(str, str2, str3, measurableDevice);
    }

    @Override // com.huawei.plugindevice.UploadSmartLifeApi
    public void delete(String str, String str2) {
        dew.e(str, str2);
    }
}
