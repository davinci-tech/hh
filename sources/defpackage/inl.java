package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealthservice.HiHealthKitApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HuaweiHealth;

@ApiDefine(uri = HiHealthKitApi.class)
@Singleton
/* loaded from: classes4.dex */
public class inl implements HiHealthKitApi {
    @Override // com.huawei.hihealthservice.HiHealthKitApi
    public boolean hasUnSyncSportData() {
        int d = ijl.d(BaseApplication.e(), iip.b().a(HuaweiHealth.b()));
        if (d <= 0) {
            LogUtil.h("HiHealthKitImpl", "userId <= 0");
        }
        return koq.c(iiz.a(BaseApplication.e()).e(iks.e().a(d), 30001, 0, 1));
    }
}
