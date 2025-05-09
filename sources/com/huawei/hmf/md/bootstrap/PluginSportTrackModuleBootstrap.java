package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.gsw;

/* loaded from: classes4.dex */
public final class PluginSportTrackModuleBootstrap {
    public static final void register(Repository repository) {
        new ModuleProviderWrapper(new gsw(), 1).bootstrap(repository, name(), ApiSet.builder().build());
    }

    public static final String name() {
        return "PluginSportTrack";
    }
}
