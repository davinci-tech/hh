package com.huawei.hmf.md.bootstrap;

import com.huawei.health.hwuserlabelmgr.impl.UserLabelMgrModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.dxx;

/* loaded from: classes4.dex */
public final class HWUserLabelMgrModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dxx.class, "com.huawei.health.userlabelmgr.api.UserLabelServiceApi");
        new ModuleProviderWrapper(new UserLabelMgrModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HWUserLabelMgr";
    }
}
