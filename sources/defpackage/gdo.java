package defpackage;

import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class gdo {
    public static void d(WorkoutRecord[] workoutRecordArr) {
        if (workoutRecordArr == null || workoutRecordArr.length == 0) {
            return;
        }
        ffe ffeVar = new ffe(0);
        for (WorkoutRecord workoutRecord : workoutRecordArr) {
            if (workoutRecord != null) {
                b(workoutRecord, ffeVar);
            }
        }
    }

    private static void b(WorkoutRecord workoutRecord, ffe ffeVar) {
        if (c(workoutRecord.acquireWorkoutName())) {
            int h = CommonUtil.h(workoutRecord.acquireWorkoutName());
            workoutRecord.saveWorkoutName(ffeVar.d(h));
            LogUtil.c("ShowPlanHelper", "replacePlanPlaceHodler workoutRecord name:", Integer.valueOf(h), " to ", workoutRecord.acquireWorkoutName());
        }
    }

    private static boolean c(String str) {
        return str != null && Pattern.compile("[0-9]+").matcher(str).matches();
    }
}
