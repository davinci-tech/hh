package com.huawei.hmf.orb;

import com.huawei.hmf.annotation.ModuleExport;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.ModuleProvider;
import com.huawei.hmf.services.ModuleProviderWrapper;

/* loaded from: classes8.dex */
class RemoteModuleProviderWrapper extends ModuleProviderWrapper {
    private RemoteInvoker mInvoker;

    static class DummyModule extends ModuleProvider {
        private DummyModule() {
        }
    }

    RemoteModuleProviderWrapper(RemoteInvoker remoteInvoker) {
        super(new DummyModule(), ModuleExport.Type.REMOTE.getValue());
        this.mInvoker = remoteInvoker;
    }

    @Override // com.huawei.hmf.services.ModuleProviderWrapper
    public Module createModule(String str, ApiSet apiSet) {
        return new RemoteModule(str, apiSet, this.mInvoker);
    }
}
