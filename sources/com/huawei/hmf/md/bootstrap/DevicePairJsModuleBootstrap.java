package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.nzw;
import defpackage.nzx;
import defpackage.nzz;
import defpackage.oaa;

/* loaded from: classes4.dex */
public final class DevicePairJsModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(oaa.class, "com.huawei.devicepair.api.UpdateDeviceApi");
        builder.add(nzz.class, "com.huawei.devicepair.api.BasePairManagerApi");
        builder.add(nzx.class, "com.huawei.devicepair.api.MessageNotificationApi");
        new ModuleProviderWrapper(new nzw(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "DevicePairJs";
    }
}
