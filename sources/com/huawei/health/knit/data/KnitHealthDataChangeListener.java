package com.huawei.health.knit.data;

import java.util.List;

/* loaded from: classes8.dex */
public interface KnitHealthDataChangeListener {
    void onDataDeleted();

    void onDataInserted(long j);

    void setSubscribeList(List<Integer> list);
}
