package defpackage;

import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.plan.model.model.fitness.FitnessHistoryModel;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ApiDefine(uri = RecordApi.class)
@Singleton
/* loaded from: classes3.dex */
public class epi implements RecordApi {
    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireSummaryFitnessRecord(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        FitnessHistoryModel.getInstance().acquireSummaryFitnessRecord(j, j2, iBaseResponseCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireSummaryFitnessRecord(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback) {
        FitnessHistoryModel.getInstance().acquireSummaryFitnessRecord(kwyVar, iBaseResponseCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireAnnualExerciseRecordByAll(ResultCallback resultCallback) {
        FitnessHistoryModel.getInstance().acquireAnnualExerciseRecordByAll(resultCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireExerciseRecordByTimeAndId(long j, long j2, String str, String str2, ResultCallback resultCallback) {
        FitnessHistoryModel.getInstance().acquireExerciseRecordByTimeAndId(j, j2, str, str2, resultCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireExerciseFinished(long j, long j2, String str, ResultCallback resultCallback) {
        FitnessHistoryModel.getInstance().acquireExerciseFinished(j, j2, str, resultCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireFitnessRecordCategoryList(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("RecordImpl", "acquireFitnessRecordCategoryList");
        FitnessHistoryModel.getInstance().acquireFitnessRecordCategoryList(iBaseResponseCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void deleteFitnessRecord(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("RecordImpl", "deleteFitnessRecord");
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void deleteFitnessRecord(String str, long j, IBaseResponseCallback iBaseResponseCallback) {
        FitnessHistoryModel.getInstance().deleteFitnessRecord(str, j, iBaseResponseCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void downloadFitnessRecordFromCloud(ResultCallback resultCallback) {
        ett.i().g();
        FitnessHistoryModel.getInstance().downloadFitnessRecordFromCloud(resultCallback);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void unregisterResultCallback() {
        FitnessHistoryModel.getInstance().unregisterResultCallback();
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public boolean deleteAllWorkoutRecords() {
        return eup.a();
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public boolean insertWorkoutRecord(String str, WorkoutRecord workoutRecord) {
        return eup.b(str, workoutRecord);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public List<Map<String, Object>> getRecordsByDateRange(Date date, Date date2) {
        return etr.b().getRecordsByDateRange(date, date2);
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void deleteAllPlanRecords() {
        etr.b().deleteAllPlanRecords();
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void addRecordFor(String str, WorkoutRecord workoutRecord, boolean z) {
        if (workoutRecord == null) {
            LogUtil.h("RecordImpl", "addRecordFor : record is null");
        } else if (workoutRecord.getSportRecordType() != 0) {
            LogUtil.a("RecordImpl", "addRecordFor : SportRecordType != 0");
        } else {
            ett.i().k().a(str, workoutRecord.acquireExerciseTime(), workoutRecord.acquireActualCalorie(), workoutRecord.getDuration(), workoutRecord.acquireWorkoutId(), z);
        }
    }

    @Override // com.huawei.health.courseplanservice.api.RecordApi
    public void acquireDetailFitnessRecords(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback) {
        FitnessHistoryModel.getInstance().acquireDetailFitnessRecord(kwyVar, iBaseResponseCallback);
    }
}
