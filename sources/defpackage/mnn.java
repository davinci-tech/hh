package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnn extends mng {
    @Override // defpackage.mng, com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d */
    public FitWorkout parse(String str, JSONObject jSONObject) {
        FitWorkout parse = super.parse(str, jSONObject);
        int a2 = ggg.a();
        if (parse.getCourseAttr() != 2) {
            a2 = parse.getCourseAttr();
        }
        LogUtil.a("Suggestion_VideoCourseWorkoutParse", "DataFitnessConvert toWorkout  CourseAttr:", Integer.valueOf(parse.getCourseAttr()), "workout:", parse.acquireName());
        if (a2 == 0) {
            parse.saveDuration(jSONObject.optInt("durationMan"));
            parse.saveCalorie(jSONObject.optInt("calorieMan"));
            parse.saveDifficulty(jSONObject.optInt("difficultyMan"));
        }
        parse.setVideoProperty(jSONObject.optInt("videoProperty"));
        parse.setWorkoutActionProperty(jSONObject.optInt("workoutActionProperty"));
        parse.setShowCalories(jSONObject.optInt("showCalories"));
        parse.setShowCountdown(jSONObject.optInt("showCountdown"));
        parse.setShowHeartRate(jSONObject.optInt("showHeartRate"));
        parse.setFitnessGoal(jSONObject.optString("fitnessGoal"));
        return parse;
    }
}
