package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.indoorequip.device.IndoorEquipManagerImpl;
import defpackage.laq;
import defpackage.lar;

/* loaded from: classes4.dex */
public final class PluginLinkIndoorEquipModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(laq.class, "com.huawei.health.device.api.BackgroundSportApi");
        builder.add(IndoorEquipManagerImpl.class, "com.huawei.health.device.api.IndoorEquipManagerApi");
        new ModuleProviderWrapper(new lar(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginLinkIndoorEquip";
    }
}
