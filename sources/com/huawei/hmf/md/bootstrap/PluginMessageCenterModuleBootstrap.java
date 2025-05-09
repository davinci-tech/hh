package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.pluginmessagecenter.MessageCenterModule;
import com.huawei.pluginmessagecenter.PluginMessageCenterImpl;

/* loaded from: classes4.dex */
public final class PluginMessageCenterModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(PluginMessageCenterImpl.class, "com.huawei.health.messagecenter.api.MessageCenterApi");
        new ModuleProviderWrapper(new MessageCenterModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginMessageCenter";
    }
}
