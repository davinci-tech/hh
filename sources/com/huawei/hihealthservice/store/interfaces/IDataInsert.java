package com.huawei.hihealthservice.store.interfaces;

import com.huawei.hihealth.HiHealthData;
import defpackage.igo;
import defpackage.ikv;
import java.util.List;

/* loaded from: classes7.dex */
public interface IDataInsert {
    int bulkSaveDetailHiHealthData(List<HiHealthData> list, ikv ikvVar, int i);

    void doAsyncHealthDataStat();

    void doRealTimeHealthDataStat();

    void doRealTimeHealthDataStat(boolean z);

    int insertBatchDayStatTable(List<igo> list);

    int insertBatchHealthDetailData(List<HiHealthData> list, int i);

    void onDestroy();

    void prepareAsyncHealthDataStat(List<HiHealthData> list);

    void prepareRealTimeHealthDataStat(List<HiHealthData> list);

    void prepareRealTimeHealthDataStat(List<HiHealthData> list, boolean z);

    boolean saveOneSyncHealthDetailData(HiHealthData hiHealthData, int i, List<Integer> list);

    int saveSyncHealthDetailData(List<HiHealthData> list, int i);

    int transferHealthDetailData(List<HiHealthData> list, int i);

    int transferHealthStatData(List<igo> list);
}
