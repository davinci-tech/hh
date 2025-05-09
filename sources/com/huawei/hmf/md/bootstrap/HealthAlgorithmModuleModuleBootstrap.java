package com.huawei.hmf.md.bootstrap;

import com.huawei.health.pluginalgorithm.HealthAlgorithmModule;
import com.huawei.health.pluginalgorithm.emotionStress.EmotionStressAlgorithmIml;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;

/* loaded from: classes4.dex */
public final class HealthAlgorithmModuleModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(EmotionStressAlgorithmIml.class, "com.huawei.pluginalgorithms.EmotionStressAdviceApi");
        new ModuleProviderWrapper(new HealthAlgorithmModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HealthAlgorithmModule";
    }
}
