package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.pei;
import defpackage.pej;

/* loaded from: classes4.dex */
public final class DevicePairPermissionsJsModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(pej.class, "com.huawei.devicepair.api.PermissionsApi");
        new ModuleProviderWrapper(new pei(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "DevicePairPermissionsJs";
    }
}
