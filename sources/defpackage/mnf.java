package defpackage;

import com.google.gson.JsonParseException;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class mnf {
    public static List<Workout> e(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new ArrayList(0);
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("itemList");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("workoutPackageList");
            List<FitWorkout> b = b(jSONObject);
            ArrayList arrayList = new ArrayList();
            if (optJSONArray == null) {
                LogUtil.a("Suggestion_CloudDataParseUtil", "itemList is invalid");
                return arrayList;
            }
            List<mmv> parse = mnc.a().parse(CommonUtil.x(), optJSONArray);
            for (int i = 0; i < parse.size(); i++) {
                int intValue = parse.get(i).a().intValue();
                int intValue2 = parse.get(i).e().intValue();
                long longValue = parse.get(i).d().longValue();
                if (intValue == 0) {
                    FitWorkout fitWorkout = b.get(intValue2);
                    fitWorkout.setNewUseWorkoutTime(longValue);
                    arrayList.add(fitWorkout);
                } else if (intValue == 2 && optJSONArray2 != null) {
                    WorkoutPackageInfo workoutPackageInfo = mnc.e().parse(CommonUtil.x(), optJSONArray2).get(intValue2);
                    workoutPackageInfo.setNewUseWorkoutTime(longValue);
                    arrayList.add(workoutPackageInfo);
                } else {
                    LogUtil.a("Suggestion_CloudDataParseUtil", "type is invalid");
                }
            }
            return arrayList;
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseWorkouts failed with JsonParseException", e.getMessage());
            return new ArrayList(0);
        }
    }

    public static List<FitWorkout> b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return Collections.EMPTY_LIST;
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("workoutList");
            if (optJSONArray == null) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList = new ArrayList(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                FitWorkout c = c(CommonUtil.x(), optJSONArray.optJSONObject(i));
                if (c != null) {
                    arrayList.add(c);
                }
            }
            return arrayList;
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkouts failed with JsonParseException", e.getMessage());
            return Collections.emptyList();
        }
    }

    public static FitWorkout c(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkout failed with null input, lan: ", str);
            return null;
        }
        int optInt = jSONObject.optInt("type");
        if (!FitWorkout.e.d(optInt)) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkout failed with type error, lan: ", str, " type:", Integer.valueOf(optInt));
            return null;
        }
        try {
            return mnc.c(optInt).parse(str, jSONObject);
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkout failed with JsonParseException", e.getMessage());
            return null;
        }
    }

    public static FitWorkout b(String str, JSONObject jSONObject) {
        if (jSONObject == null || str == null) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkoutDetail failed with null input, lan: ", str);
            return null;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("workoutInfo");
            int optInt = optJSONObject.optInt("type");
            JSONArray optJSONArray = jSONObject.optJSONArray("actionList");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("actionComposition");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("userWorkoutRunActionInfo");
            JSONObject optJSONObject3 = jSONObject.optJSONObject("userWorkoutRunSequenceInfo");
            JSONObject optJSONObject4 = jSONObject.optJSONObject("equipmentWorkoutAction");
            JSONArray optJSONArray3 = jSONObject.optJSONArray("portraitUrlList");
            JSONArray optJSONArray4 = jSONObject.optJSONArray("roadBook");
            FitWorkout parse = mnc.c(optInt).parse(str, optJSONObject);
            parse.setPortraitUrlList(c(optJSONArray3));
            if (optInt == 1 && optJSONArray != null) {
                parse.saveWorkoutActions(ffq.c(optJSONArray, optInt, parse.getCourseAttr()));
            } else if (optInt == 2 && optJSONObject2 != null) {
                parse.saveWorkoutActions(ffq.e(optJSONObject2, parse));
            } else if (optInt == 3 && optJSONObject3 != null) {
                parse.saveWorkoutActions(ffq.e(optJSONObject3, parse));
            } else if (optInt == 4 && optJSONArray2 != null) {
                parse.setCourseActions(mnc.a(optInt).parse(str, optJSONArray2));
            } else if (optInt == 5 && optJSONObject4 != null) {
                parse.setEquipmentWorkoutAction(mnc.b().parse(str, optJSONObject4));
                parse.setRoadBookList(mnc.c().parse(str, optJSONArray4));
                LogUtil.a("Suggestion_CloudDataParseUtil", "the equipmentActionInfo json : ", parse.getEquipmentWorkoutAction());
            } else {
                LogUtil.a("Suggestion_CloudDataParseUtil", "type is invalid");
            }
            return parse;
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseTopicList failed with JsonParseException", e.getMessage());
            return null;
        }
    }

    private static List<String> c(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.optString(i));
            } catch (Exception e) {
                LogUtil.a("Suggestion_CloudDataParseUtil", "optString from JsonArray failed... e:", e);
            }
        }
        return arrayList;
    }

    public static List<FitWorkout> a(String str, JSONObject jSONObject) {
        if (jSONObject == null || str == null) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkoutsDetail failed with null input, lan: ", str);
            return null;
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("workoutDetails");
            if (optJSONArray == null) {
                ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "workoutDetailsArray is null");
                return null;
            }
            ArrayList arrayList = new ArrayList(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(b(str, optJSONArray.optJSONObject(i)));
            }
            return arrayList;
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseCourseWorkoutsDetail failed with JsonParseException", e.getMessage());
            return null;
        }
    }

    public static List<Topic> c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return Collections.emptyList();
        }
        try {
            return mnc.d().parse(CommonUtil.x(), jSONObject.optJSONArray("topicList"));
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("Suggestion_CloudDataParseUtil", "parseTopicList failed with JsonParseException", e.getMessage());
            return Collections.emptyList();
        }
    }
}
