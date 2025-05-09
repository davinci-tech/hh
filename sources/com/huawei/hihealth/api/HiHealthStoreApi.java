package com.huawei.hihealth.api;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;

/* loaded from: classes.dex */
public class HiHealthStoreApi implements IStoreApi {
    private static Context e;
    private HiHealthNativeApi c;

    private HiHealthStoreApi() {
        this.c = HiHealthNativeApi.a(e);
    }

    @Override // com.huawei.hihealth.api.IStoreApi
    public void aggregateHiHealthData(HiAggregateOption hiAggregateOption, HiAggregateListener hiAggregateListener) {
        this.c.aggregateHiHealthData(hiAggregateOption, hiAggregateListener);
    }

    @Override // com.huawei.hihealth.api.IStoreApi
    public void insertHiHealthData(HiDataInsertOption hiDataInsertOption, HiDataOperateListener hiDataOperateListener) {
        this.c.insertHiHealthData(hiDataInsertOption, hiDataOperateListener);
    }

    @Override // com.huawei.hihealth.api.IStoreApi
    public void readHiHealthData(HiDataReadOption hiDataReadOption, HiDataReadResultListener hiDataReadResultListener) {
        this.c.readHiHealthData(hiDataReadOption, hiDataReadResultListener);
    }

    static class Instance {
        public static final HiHealthStoreApi c = new HiHealthStoreApi();

        private Instance() {
        }
    }
}
