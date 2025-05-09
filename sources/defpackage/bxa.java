package defpackage;

import com.huawei.basefitnessadvice.api.BaseSportModelApi;
import com.huawei.basefitnessadvice.callback.DeviceCallback;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;

@ApiDefine(uri = BaseSportModelApi.class)
@Singleton
/* loaded from: classes3.dex */
public class bxa implements BaseSportModelApi {
    @Override // com.huawei.basefitnessadvice.api.BaseSportModelApi
    public void sendCommandToDevice(String str, DeviceCallback deviceCallback) {
        fbb.e().d(str, deviceCallback);
    }

    @Override // com.huawei.basefitnessadvice.api.BaseSportModelApi
    public void sendCommandToDevice(byte[] bArr, int i, DeviceCallback deviceCallback) {
        fbb.e().a(bArr, i, deviceCallback);
    }
}
