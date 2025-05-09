package defpackage;

import com.huawei.health.device.api.DataBaseapiApi;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = DataBaseapiApi.class)
/* loaded from: classes3.dex */
public class dyj implements DataBaseapiApi {
    @Override // com.huawei.health.device.api.DataBaseapiApi
    public String getWiFiDeviceProductId(String str) {
        return ctq.i(str);
    }
}
