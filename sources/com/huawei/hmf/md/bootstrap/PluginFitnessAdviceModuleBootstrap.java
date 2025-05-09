package com.huawei.hmf.md.bootstrap;

import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.PluginSuggestionModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.fic;

/* loaded from: classes4.dex */
public final class PluginFitnessAdviceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(FitnessExternalUtils.class, "com.huawei.health.fitnessadvice.api.FitnessAdviceApi");
        builder.add(fic.class, "com.huawei.health.suggestion.PluginSuggestion");
        new ModuleProviderWrapper(new PluginSuggestionModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginFitnessAdvice";
    }
}
