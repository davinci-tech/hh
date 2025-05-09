package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.ui.thirdpartservice.FeatureDataOpenModule;
import com.huawei.ui.thirdpartservice.syncdata.komoot.KomootProviderApiImpl;
import defpackage.sjf;
import defpackage.sju;

/* loaded from: classes4.dex */
public final class FeatureDataOpenModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(sjf.class, "com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi");
        builder.add(sju.class, "com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi");
        builder.add(KomootProviderApiImpl.class, "com.huawei.thirdpartservice.komootapi.KomootProviderApi");
        new ModuleProviderWrapper(new FeatureDataOpenModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "FeatureDataOpen";
    }
}
