package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.operation.PluginOperationModule;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderImpl;
import com.huawei.operation.utils.Utils;
import com.huawei.operation.utils.WebViewUtils;

/* loaded from: classes4.dex */
public final class PluginOperationModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(OperationWebActivityIntentBuilderImpl.class, "com.huawei.operation.utils.OperationWebActivityIntentBuilderApi");
        builder.add(WebViewUtils.ActivityHtmlPathImpl.class, "com.huawei.operation.utils.ActivityHtmlPathApi");
        builder.add(Utils.OperationUtilsApiImpl.class, "com.huawei.operation.utils.OperationUtilsApi");
        new ModuleProviderWrapper(new PluginOperationModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginOperation";
    }
}
