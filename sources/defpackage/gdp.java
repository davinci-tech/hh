package defpackage;

import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class gdp {
    public static void b(List<WorkoutRecord> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_ShowPlanHelper", "workoutRecords == null || workoutRecords.size() == 0");
        } else {
            gdo.d((WorkoutRecord[]) list.toArray(new WorkoutRecord[list.size()]));
        }
    }
}
