package com.huawei.health.marketing.views;

import com.huawei.health.marketing.datatype.ResourceBriefInfo;

/* loaded from: classes3.dex */
public interface ResourceBriefInfoOwnable {
    int getResourceBriefPriority();

    boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo);
}
