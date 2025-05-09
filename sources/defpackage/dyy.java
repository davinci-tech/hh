package defpackage;

import com.huawei.health.device.api.WeightDataUtilsApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = WeightDataUtilsApi.class)
/* loaded from: classes3.dex */
public class dyy implements WeightDataUtilsApi {
    @Override // com.huawei.health.device.api.WeightDataUtilsApi
    public HiAggregateOption buildHiAggregateOption() {
        return cff.a();
    }

    @Override // com.huawei.health.device.api.WeightDataUtilsApi
    public int getFractionDigitForWeight(double d, int i) {
        return cff.e(d, i);
    }
}
