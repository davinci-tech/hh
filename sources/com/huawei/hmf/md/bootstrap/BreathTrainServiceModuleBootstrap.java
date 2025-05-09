package com.huawei.hmf.md.bootstrap;

import com.huawei.health.breathtrain.BreathTrainServiceModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.cbp;

/* loaded from: classes4.dex */
public final class BreathTrainServiceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(cbp.class, "com.huawei.health.algorithm.api.BreathTrainApi");
        new ModuleProviderWrapper(new BreathTrainServiceModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "BreathTrainService";
    }
}
