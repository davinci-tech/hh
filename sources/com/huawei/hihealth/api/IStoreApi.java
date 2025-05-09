package com.huawei.hihealth.api;

import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;

/* loaded from: classes.dex */
public interface IStoreApi {
    void aggregateHiHealthData(HiAggregateOption hiAggregateOption, HiAggregateListener hiAggregateListener);

    void insertHiHealthData(HiDataInsertOption hiDataInsertOption, HiDataOperateListener hiDataOperateListener);

    void readHiHealthData(HiDataReadOption hiDataReadOption, HiDataReadResultListener hiDataReadResultListener);
}
