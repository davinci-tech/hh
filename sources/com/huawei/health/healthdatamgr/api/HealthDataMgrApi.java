package com.huawei.health.healthdatamgr.api;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import java.util.List;

/* loaded from: classes.dex */
public interface HealthDataMgrApi {
    String convertHiDataToTrackData(HiHealthData hiHealthData, MotionPathSimplify motionPathSimplify);

    void getCoreSleepDetail(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback);

    HeartRateZoneMgr getHeartRateZoneMgr();

    HeartRateZoneMgr getHeartRateZoneMgrByCache();

    HeartZoneConf getHeartZoneConf();

    HeartZoneConf getHeartZoneConfForBluetoothSend();

    void getLastVo2max(IBaseResponseCallback iBaseResponseCallback);

    void getLastVo2maxForMaxMet(IBaseResponseCallback iBaseResponseCallback);

    int getStudentAge();

    StudentHeartRateZoneMgr getStudentHeartRateData();

    void getTodayFitnessTotalData(IBaseResponseCallback iBaseResponseCallback);

    void getUserPressureAdjustDatas(IBaseResponseCallback iBaseResponseCallback);

    void requestTotalSportTimes(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback);

    void requestTrackDetailData(long j, long j2, IBaseResponseCallback iBaseResponseCallback);

    void requestTrackSimplifyData(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback);

    void requestTrackSimplifyData(long j, long j2, int i, boolean z, IBaseResponseCallback iBaseResponseCallback);

    void resetHeartZoneData();

    void setStressData(String str, List<HiStressMetaData> list, IBaseResponseCallback iBaseResponseCallback);
}
