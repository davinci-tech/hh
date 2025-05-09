package defpackage;

import com.huawei.hihealth.data.listener.HealthTrendListener;
import com.huawei.hihealthservice.healthtrend.HealthTrendApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import java.util.List;

@ApiDefine(uri = HealthTrendApi.class)
@Singleton
/* loaded from: classes4.dex */
public class ikg implements HealthTrendApi {
    @Override // com.huawei.hihealthservice.healthtrend.HealthTrendApi
    public void getHealthTrend(List<String> list, int[] iArr, int i, HealthTrendListener healthTrendListener) {
        ikf.a(list, iArr, i, healthTrendListener, false);
    }
}
