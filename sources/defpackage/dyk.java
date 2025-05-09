package defpackage;

import androidx.fragment.app.Fragment;
import com.huawei.health.device.api.DeviceFragmentFactoryApi;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = DeviceFragmentFactoryApi.class)
/* loaded from: classes3.dex */
public class dyk implements DeviceFragmentFactoryApi {
    @Override // com.huawei.health.device.api.DeviceFragmentFactoryApi
    public Fragment getMeasureFragment(String str) {
        return ckq.a(str);
    }

    @Override // com.huawei.health.device.api.DeviceFragmentFactoryApi
    public Fragment getResultFragment(String str) {
        return ckq.b(str);
    }
}
