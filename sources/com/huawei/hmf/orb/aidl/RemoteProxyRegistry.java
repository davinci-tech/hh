package com.huawei.hmf.orb.aidl;

import com.huawei.hmf.orb.RemoteModuleBootstrap;
import com.huawei.hmf.repository.ModuleRegistry;
import com.huawei.hmf.services.ApiSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class RemoteProxyRegistry {
    private static Map<String, RemoteProxyRegistry> mMap = new HashMap();
    private final ApiSet mProxyApiSet;

    public static RemoteProxyRegistry getRegistry(String str) {
        synchronized (RemoteProxyRegistry.class) {
            RemoteProxyRegistry remoteProxyRegistry = mMap.get(str);
            if (remoteProxyRegistry == null) {
                RemoteModuleBootstrap remoteModuleBootstrap = (RemoteModuleBootstrap) ModuleRegistry.getRemoteModuleBootstrap(str);
                if (remoteModuleBootstrap == null) {
                    return null;
                }
                RemoteProxyRegistry remoteProxyRegistry2 = new RemoteProxyRegistry(remoteModuleBootstrap);
                mMap.put(str, remoteProxyRegistry2);
                remoteProxyRegistry = remoteProxyRegistry2;
            }
            return remoteProxyRegistry;
        }
    }

    private RemoteProxyRegistry(RemoteModuleBootstrap remoteModuleBootstrap) {
        this.mProxyApiSet = remoteModuleBootstrap.getProxy();
    }

    public ApiSet getProxy() {
        return this.mProxyApiSet;
    }
}
