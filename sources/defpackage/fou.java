package defpackage;

import android.content.Context;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fou {
    public static void e(Context context, WorkoutRecord workoutRecord) {
        FitWorkout workout;
        if (context == null || workoutRecord == null) {
            LogUtil.h("Suggestion_LongCoachDataHelper", "saveFitnessDataToOdmf context or record is null");
            return;
        }
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi == null || (workout = sportServiceApi.getWorkout(workoutRecord.acquireWorkoutId())) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        Iterator<Attribute> it = workout.acquireClasses().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            sb.append(",");
        }
        if (sb.length() >= 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        StringBuilder sb2 = new StringBuilder(16);
        Iterator<Attribute> it2 = workout.acquireTrainingpoints().iterator();
        while (it2.hasNext()) {
            sb2.append(it2.next().getName());
            sb2.append(",");
        }
        if (sb2.length() >= 1) {
            sb2.deleteCharAt(sb2.length() - 1);
        }
        int duration = workoutRecord.getDuration() / 1000;
        String b = DateFormatUtil.b(workoutRecord.acquireExerciseTime() - (duration * 1000), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT);
        int ceil = (int) Math.ceil(workoutRecord.acquireActualCalorie() / 1000.0d);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", 1001);
            jSONObject.put("SportStartTime", b);
            jSONObject.put("SportDuration", duration);
            jSONObject.put("HeatQuantity", ceil);
            jSONObject.put("BodyBuildingType", sb.toString());
            jSONObject.put("BodyBuildingPart", sb2.toString());
            ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).saveTrackDataToOdmf(jSONObject.toString());
        } catch (JSONException e) {
            LogUtil.b("Suggestion_LongCoachDataHelper", "Jsons parse error:", LogAnonymous.b((Throwable) e));
        }
    }
}
