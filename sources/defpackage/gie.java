package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class gie {
    public static void a(Context context, int i, int i2, float f) {
        LogUtil.a("Track_WarmUpCourseUtils", "gotoWarmUp, sportType = ", Integer.valueOf(i));
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion != null && pluginSuggestion.isInitComplete() && Utils.j()) {
            if (context == null) {
                LogUtil.h("Track_WarmUpCourseUtils", "gotoWarmUp context is null");
                return;
            }
            mmp mmpVar = new mmp(b(i));
            mmpVar.a(false);
            mmpVar.e(1);
            mmpVar.b(i);
            mmpVar.a(i2);
            mmpVar.a(f);
            mmpVar.c(0);
            mmpVar.d(R.anim._2130771981_res_0x7f01000d);
            mmpVar.j(true);
            mod.c(context, mmpVar, d(i));
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            hashMap.put("type", 0);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MOTION_TRACK_1040021.value(), hashMap, 0);
        }
    }

    private static ArrayList<WorkoutRecord> d(int i) {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(null);
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId(b(i));
        workoutRecord.savePlanId("");
        ArrayList<WorkoutRecord> arrayList = new ArrayList<>(1);
        arrayList.add(workoutRecord);
        return arrayList;
    }

    private static String b(int i) {
        if (i != 264) {
            if (i == 283) {
                return "Y044";
            }
            switch (i) {
                case 257:
                    return "R010";
                case 258:
                    break;
                case 259:
                    return "R011";
                default:
                    return "";
            }
        }
        return "R001";
    }
}
