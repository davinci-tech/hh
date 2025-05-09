package com.huawei.hmf.repository.impl;

import com.huawei.hmf.repository.ModuleRegistry;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.ModuleProviderWrapper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class RepositoryImpl implements Repository {
    private Map<String, ModuleProviderWrapper> mModuleProviderWrapperMap = new HashMap();

    public RepositoryImpl(boolean z) {
        if (z) {
            ModuleRegistry.register(this);
        }
    }

    @Override // com.huawei.hmf.repository.Repository
    public Module lookup(String str) {
        ModuleProviderWrapper moduleProviderWrapper = this.mModuleProviderWrapperMap.get(str);
        if (moduleProviderWrapper != null) {
            return moduleProviderWrapper.getModule();
        }
        return null;
    }

    @Override // com.huawei.hmf.repository.Repository
    public void register(String str, ModuleProviderWrapper moduleProviderWrapper) {
        if (this.mModuleProviderWrapperMap.get(str) == null) {
            this.mModuleProviderWrapperMap.put(str, moduleProviderWrapper);
        }
    }

    public ModuleProviderWrapper getModuleProviderWrapper(String str) {
        return this.mModuleProviderWrapperMap.get(str);
    }

    protected void clearRegister() {
        this.mModuleProviderWrapperMap.clear();
    }
}
