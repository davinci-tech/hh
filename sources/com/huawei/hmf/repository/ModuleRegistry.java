package com.huawei.hmf.repository;

import defpackage.byg;

/* loaded from: classes4.dex */
public class ModuleRegistry {
    static byg registryImpl = new byg();

    public static void register(Repository repository) {
        registryImpl.register(repository);
    }

    public static Object getRemoteModuleBootstrap(String str) {
        return registryImpl.getRemoteModuleBootstrap(str);
    }
}
