package com.huawei.health.marketrouter.api;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import java.util.Map;

/* loaded from: classes.dex */
public interface MarketRouterApi {
    Map<String, String> getLastMarketSource();

    void router(String str);

    void router(String str, IBaseResponseCallback iBaseResponseCallback);
}
