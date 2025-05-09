package defpackage;

import android.content.Context;
import com.huawei.health.courseplanservice.api.DataPlatformApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import java.util.List;
import java.util.Map;

@ApiDefine(uri = DataPlatformApi.class)
@Singleton
/* loaded from: classes3.dex */
public class epf implements DataPlatformApi {
    @Override // com.huawei.health.courseplanservice.api.DataPlatformApi
    public void insertCalorieData(Context context, WorkoutRecord workoutRecord) {
        etr.b().insertCalorieData(context, workoutRecord);
    }

    @Override // com.huawei.health.courseplanservice.api.DataPlatformApi
    public void insertCaloriePoints(Context context, Map<Long, HiHealthData> map) {
        etr.b().insertCaloriePoints(context, map);
    }

    @Override // com.huawei.health.courseplanservice.api.DataPlatformApi
    public void insertFitnessIntensityData(Context context, List<Long> list) {
        etr.b().insertFitnessIntensityData(context, list);
    }
}
