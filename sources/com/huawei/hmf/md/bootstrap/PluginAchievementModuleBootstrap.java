package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.pluginachievement.PluginAchievementModule;
import defpackage.mcp;
import defpackage.mcw;

/* loaded from: classes4.dex */
public final class PluginAchievementModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(mcw.class, "com.huawei.pluginachievement.AchieveNavigationApi");
        builder.add(mcp.class, "com.huawei.pluginachievement.AchieveDataApi");
        new ModuleProviderWrapper(new PluginAchievementModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginAchievement";
    }
}
