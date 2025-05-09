package com.huawei.hihealth.data.listener;

import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import java.util.List;

/* loaded from: classes.dex */
public interface HiAggregateListenerEx {
    void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2);
}
