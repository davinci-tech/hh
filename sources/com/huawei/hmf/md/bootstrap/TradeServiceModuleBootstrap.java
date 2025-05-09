package com.huawei.hmf.md.bootstrap;

import com.huawei.health.tradeservice.TradeServiceModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.gkq;
import defpackage.gky;
import defpackage.gpk;

/* loaded from: classes4.dex */
public final class TradeServiceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(gpk.class, "com.huawei.health.vip.api.VipApi");
        builder.add(gkq.class, "com.huawei.trade.TradeViewApi");
        builder.add(gky.class, "com.huawei.trade.PayApi");
        new ModuleProviderWrapper(new TradeServiceModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "TradeService";
    }
}
