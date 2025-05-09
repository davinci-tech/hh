package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.cze;
import defpackage.czo;
import defpackage.czp;
import defpackage.czx;

/* loaded from: classes4.dex */
public final class EcologyDeviceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(cze.class, "com.huawei.health.hearratecontrol.HeartRateControlConfigApi");
        builder.add(czx.class, "com.huawei.health.device.api.ResourceManagerApi");
        builder.add(czo.class, "com.huawei.health.device.api.EcologyDeviceApi");
        new ModuleProviderWrapper(new czp(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "EcologyDevice";
    }
}
