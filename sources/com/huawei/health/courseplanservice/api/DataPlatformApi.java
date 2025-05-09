package com.huawei.health.courseplanservice.api;

import android.content.Context;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiHealthData;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface DataPlatformApi {
    void insertCalorieData(Context context, WorkoutRecord workoutRecord);

    void insertCaloriePoints(Context context, Map<Long, HiHealthData> map);

    void insertFitnessIntensityData(Context context, List<Long> list);
}
