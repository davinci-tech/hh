package defpackage;

import com.huawei.health.huaweihealth.HuaweiHealthApi;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;

@ApiDefine(uri = HuaweiHealthApi.class)
@Singleton
/* loaded from: classes3.dex */
public class fcb implements HuaweiHealthApi {
    @Override // com.huawei.health.huaweihealth.HuaweiHealthApi
    public boolean isHealthLoginInit() {
        return MainInteractors.a();
    }

    @Override // com.huawei.health.huaweihealth.HuaweiHealthApi
    public void updateSyncFinishTime(long j) {
        gou.c(j);
    }
}
