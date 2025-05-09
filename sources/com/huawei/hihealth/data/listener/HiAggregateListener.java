package com.huawei.hihealth.data.listener;

import com.huawei.hihealth.HiHealthData;
import java.util.List;

/* loaded from: classes.dex */
public interface HiAggregateListener {
    void onResult(List<HiHealthData> list, int i, int i2);

    void onResultIntent(int i, List<HiHealthData> list, int i2, int i3);
}
