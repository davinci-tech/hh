package com.huawei.hmf.md.bootstrap;

import com.huawei.health.pluginsleeplabelalgorithm.SleepLabelAlgorithmImpl;
import com.huawei.health.pluginsleeplabelalgorithm.SleepLabelAlgorithmModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;

/* loaded from: classes4.dex */
public final class SleepLabelAlgorithmModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(SleepLabelAlgorithmImpl.class, "com.huawei.pluginsleeplabel.SleepLabelApi");
        new ModuleProviderWrapper(new SleepLabelAlgorithmModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "SleepLabelAlgorithm";
    }
}
