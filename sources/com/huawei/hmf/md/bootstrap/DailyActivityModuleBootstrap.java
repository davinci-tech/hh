package com.huawei.hmf.md.bootstrap;

import com.huawei.health.dailyactivity.DailyActivityServiceModule;
import com.huawei.health.dailyactivity.impl.ThreeCircleImpl;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;

/* loaded from: classes4.dex */
public final class DailyActivityModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(ThreeCircleImpl.class, "com.huawei.threecircle.api.ThreeCircleApi");
        new ModuleProviderWrapper(new DailyActivityServiceModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "DailyActivity";
    }
}
