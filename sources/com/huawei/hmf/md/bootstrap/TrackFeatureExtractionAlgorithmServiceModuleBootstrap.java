package com.huawei.hmf.md.bootstrap;

import com.huawei.health.trackimport.algorithm.TrackFeatureExtractionServiceModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.gkk;

/* loaded from: classes4.dex */
public final class TrackFeatureExtractionAlgorithmServiceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(gkk.class, "com.huawei.health.algorithm.api.TrackFeatureExtractionApi");
        new ModuleProviderWrapper(new TrackFeatureExtractionServiceModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "TrackFeatureExtractionAlgorithmService";
    }
}
