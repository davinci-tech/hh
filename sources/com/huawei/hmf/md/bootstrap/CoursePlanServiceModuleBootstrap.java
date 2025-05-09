package com.huawei.hmf.md.bootstrap;

import com.huawei.health.plan.impl.CoursePlanServiceModule;
import com.huawei.health.plan.impl.PlanImpl;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.eou;
import defpackage.epf;
import defpackage.epi;
import defpackage.epk;

/* loaded from: classes4.dex */
public final class CoursePlanServiceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(PlanImpl.class, "com.huawei.health.plan.api.PlanApi");
        builder.add(epk.class, "com.huawei.health.courseplanservice.api.SportServiceApi");
        builder.add(eou.class, "com.huawei.health.course.api.CourseApi");
        builder.add(epi.class, "com.huawei.health.courseplanservice.api.RecordApi");
        builder.add(epf.class, "com.huawei.health.courseplanservice.api.DataPlatformApi");
        new ModuleProviderWrapper(new CoursePlanServiceModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "CoursePlanService";
    }
}
