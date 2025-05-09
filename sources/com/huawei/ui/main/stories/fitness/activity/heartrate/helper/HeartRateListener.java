package com.huawei.ui.main.stories.fitness.activity.heartrate.helper;

import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class HeartRateListener implements HiAggregateListenerEx {
    private static final String TAG = "HeartRateStorageHelper";
    protected ResponseCallback<Map<Long, IStorageModel>> mCallback;

    public abstract void onResultData(SparseArray<List<HiHealthData>> sparseArray, int i, int i2);

    public HeartRateListener(ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        this.mCallback = responseCallback;
    }

    @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
    public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
        onResultData(sparseArray, i, i2);
        LogUtil.h(TAG, "HeartRateAggregateListener onResult ANCHOR", Integer.valueOf(i2));
        if (i2 == 2 || i == 1) {
            this.mCallback = null;
        }
    }
}
