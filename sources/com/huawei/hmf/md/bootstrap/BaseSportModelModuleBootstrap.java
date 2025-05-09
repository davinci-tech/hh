package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.bxa;
import defpackage.bxb;
import defpackage.cct;
import defpackage.dtd;
import defpackage.fhv;

/* loaded from: classes4.dex */
public final class BaseSportModelModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dtd.class, "com.huawei.basefitnessadvice.api.HeartRateControlApi");
        builder.add(fhv.class, "com.huawei.health.suggestion.FitnessNavigationApi");
        builder.add(bxa.class, "com.huawei.basefitnessadvice.api.BaseSportModelApi");
        builder.add(cct.class, "com.huawei.caloriecalculate.ActivityCaloriesCalculateApi");
        new ModuleProviderWrapper(new bxb(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "BaseSportModel";
    }
}
