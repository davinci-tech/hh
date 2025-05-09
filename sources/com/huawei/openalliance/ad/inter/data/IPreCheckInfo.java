package com.huawei.openalliance.ad.inter.data;

import java.util.Map;

/* loaded from: classes5.dex */
public interface IPreCheckInfo {
    String getContentId();

    Map<String, String> getExt();

    int getPreCheckResult();

    String getSlotId();

    boolean isStrategyFiltered();
}
