package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes6.dex */
public class qpm {
    public static void e(long j) {
        SharedPreferenceManager.e(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, "data_read_times", j);
    }

    public static long b() {
        long b = SharedPreferenceManager.b(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, "data_read_times", 0L);
        return b == 0 ? BaseApplication.getContext().getSharedPreferences(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, 0).getLong("data_read_times", 0L) : b;
    }

    public static void d(boolean z) {
        SharedPreferenceManager.e(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, "has_remind_data", z);
    }

    public static boolean d() {
        boolean a2 = SharedPreferenceManager.a(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, "has_remind_data", false);
        return !a2 ? BaseApplication.getContext().getSharedPreferences(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, 0).getBoolean("has_remind_data", false) : a2;
    }
}
