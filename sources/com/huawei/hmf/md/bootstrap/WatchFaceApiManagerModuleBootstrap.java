package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceApiImpl;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceApiModule;

/* loaded from: classes4.dex */
public final class WatchFaceApiManagerModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(WatchFaceApiImpl.class, "com.huawei.watchface.WatchFaceApi");
        new ModuleProviderWrapper(new WatchFaceApiModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "WatchFaceApiManager";
    }
}
