package com.huawei.ui.main.stories.fitness.activity.active.writehelper;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.ResponseCallback;
import java.util.List;

/* loaded from: classes6.dex */
public interface ThreeCircleDataInterface {
    void clearData();

    void requestAggregateData(long j, long j2, ResponseCallback<List<HiHealthData>> responseCallback);
}
