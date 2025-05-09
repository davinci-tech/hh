package com.huawei.hmf.md.bootstrap;

import com.huawei.health.configresource.downloaddevicemanager.DownloadManagerImpl;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.ccw;

/* loaded from: classes4.dex */
public final class DownloadDeviceResourceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(DownloadManagerImpl.class, "com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi");
        new ModuleProviderWrapper(new ccw(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "DownloadDeviceResource";
    }
}
