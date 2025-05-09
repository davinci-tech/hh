package com.huawei.hmf.repository;

import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.RemoteModuleBootstrap;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class ModuleRegistryBase implements IMessageEntity {
    private Map<String, RemoteModuleBootstrap> mRemoteModuleBootstrapMap;

    public abstract void register(Repository repository);

    protected abstract void registerRemoteModule(Map map);

    public Object getRemoteModuleBootstrap(String str) {
        RemoteModuleBootstrap remoteModuleBootstrap;
        synchronized (this) {
            if (this.mRemoteModuleBootstrapMap == null) {
                HashMap hashMap = new HashMap();
                this.mRemoteModuleBootstrapMap = hashMap;
                registerRemoteModule(hashMap);
            }
            remoteModuleBootstrap = this.mRemoteModuleBootstrapMap.get(str);
        }
        return remoteModuleBootstrap;
    }
}
