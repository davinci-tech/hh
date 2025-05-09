package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.ui.device.activity.menstrualpredict.MenstrualPredictModule;
import defpackage.nwr;
import defpackage.nzx;
import defpackage.nzz;
import defpackage.oaa;
import defpackage.oap;

/* loaded from: classes4.dex */
public final class MenstrualPredictModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(oap.class, "com.huawei.health.device.api.OfflineMapApi");
        builder.add(oaa.class, "com.huawei.devicepair.api.UpdateDeviceApi");
        builder.add(nzz.class, "com.huawei.devicepair.api.BasePairManagerApi");
        builder.add(nzx.class, "com.huawei.devicepair.api.MessageNotificationApi");
        builder.add(nwr.class, "com.huawei.pluginhealthalgorithm.MenstrualPredictApi");
        new ModuleProviderWrapper(new MenstrualPredictModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "MenstrualPredict";
    }
}
