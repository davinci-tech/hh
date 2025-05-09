package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.brv;
import defpackage.brw;
import defpackage.bry;
import defpackage.buu;

/* loaded from: classes4.dex */
public final class module_routeModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(buu.class, "com.huawei.health.todo.api.TodoDataApi");
        builder.add(brv.class, "com.huawei.health.userprofilemgr.api.UserProfileMgrApi");
        builder.add(bry.class, "com.huawei.health.userprofilemgr.api.RouteApi");
        new ModuleProviderWrapper(new brw(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "module_route";
    }
}
