package com.huawei.health.courseplanservice.api;

import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import defpackage.kwy;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface RecordApi {
    void acquireAnnualExerciseRecordByAll(ResultCallback resultCallback);

    void acquireDetailFitnessRecords(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback);

    void acquireExerciseFinished(long j, long j2, String str, ResultCallback resultCallback);

    void acquireExerciseRecordByTimeAndId(long j, long j2, String str, String str2, ResultCallback resultCallback);

    void acquireFitnessRecordCategoryList(IBaseResponseCallback iBaseResponseCallback);

    @Deprecated
    void acquireSummaryFitnessRecord(long j, long j2, IBaseResponseCallback iBaseResponseCallback);

    void acquireSummaryFitnessRecord(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback);

    void addRecordFor(String str, WorkoutRecord workoutRecord, boolean z);

    void deleteAllPlanRecords();

    boolean deleteAllWorkoutRecords();

    void deleteFitnessRecord(int i, IBaseResponseCallback iBaseResponseCallback);

    void deleteFitnessRecord(String str, long j, IBaseResponseCallback iBaseResponseCallback);

    void downloadFitnessRecordFromCloud(ResultCallback resultCallback);

    List<Map<String, Object>> getRecordsByDateRange(Date date, Date date2);

    boolean insertWorkoutRecord(String str, WorkoutRecord workoutRecord);

    void unregisterResultCallback();
}
