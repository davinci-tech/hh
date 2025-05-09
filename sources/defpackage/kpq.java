package defpackage;

import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import java.util.List;

@ApiDefine(uri = HealthDataMgrApi.class)
@Singleton
/* loaded from: classes5.dex */
public class kpq implements HealthDataMgrApi {
    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void getLastVo2max(IBaseResponseCallback iBaseResponseCallback) {
        kor.a().e(iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void requestTrackSimplifyData(long j, long j2, int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        kpm.c().c(j, j2, i, z, iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void requestTotalSportTimes(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        kpm.c().a(j, j2, i, iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public HeartZoneConf getHeartZoneConf() {
        return kor.a().g();
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void getCoreSleepDetail(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        kor.a().c(j, i, i2, i3, iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void getTodayFitnessTotalData(IBaseResponseCallback iBaseResponseCallback) {
        kor.a().c(iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public HeartRateZoneMgr getHeartRateZoneMgr() {
        return kox.e().d();
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public HeartRateZoneMgr getHeartRateZoneMgrByCache() {
        return kox.e().b();
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public HeartZoneConf getHeartZoneConfForBluetoothSend() {
        return kor.a().h();
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void getLastVo2maxForMaxMet(IBaseResponseCallback iBaseResponseCallback) {
        kow.e().a(iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public StudentHeartRateZoneMgr getStudentHeartRateData() {
        return kox.e().a().c();
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public int getStudentAge() {
        return kox.e().a().e();
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void resetHeartZoneData() {
        kox.e().a().d(kox.e().a().e());
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void getUserPressureAdjustDatas(IBaseResponseCallback iBaseResponseCallback) {
        kpl.c().a(iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void setStressData(String str, List<HiStressMetaData> list, IBaseResponseCallback iBaseResponseCallback) {
        kpl.c().d(str, list, iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void requestTrackSimplifyData(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        kor.a().c(j, j2, i, iBaseResponseCallback);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public String convertHiDataToTrackData(HiHealthData hiHealthData, MotionPathSimplify motionPathSimplify) {
        return kpt.a(hiHealthData, motionPathSimplify);
    }

    @Override // com.huawei.health.healthdatamgr.api.HealthDataMgrApi
    public void requestTrackDetailData(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        hps.b(j, j2, iBaseResponseCallback);
    }
}
