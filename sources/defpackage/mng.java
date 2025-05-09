package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.BelongInfo;
import com.huawei.pluginfitnessadvice.Classify;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LayoutTemplateInfo;
import com.huawei.pluginfitnessadvice.ResourceType;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mng implements CloudDataParse<FitWorkout> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<FitWorkout> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            FitWorkout parse = parse(str, jSONArray.optJSONObject(i));
            if (parse != null) {
                arrayList.add(parse);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public FitWorkout parse(String str, JSONObject jSONObject) {
        FitWorkout fitWorkout = new FitWorkout();
        fitWorkout.saveId(jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID));
        fitWorkout.setType(jSONObject.optInt("type"));
        fitWorkout.saveDistance(jSONObject.optDouble("distance"));
        fitWorkout.saveMeasurementType(jSONObject.optInt("measurementType"));
        fitWorkout.saveName(jSONObject.optString("name"));
        if (TextUtils.isEmpty(jSONObject.optString("version"))) {
            fitWorkout.saveVersion("2.0");
        } else {
            fitWorkout.saveVersion(jSONObject.optString("version"));
        }
        fitWorkout.setCourseAttr(jSONObject.optInt("courseAttr", 2));
        fitWorkout.saveDuration(jSONObject.optInt("duration"));
        fitWorkout.saveCalorie(jSONObject.optInt("calorie"));
        fitWorkout.saveMidPicture(jSONObject.optString("midPicture"));
        fitWorkout.saveDescription(jSONObject.optString("description"));
        fitWorkout.saveModified(jSONObject.optLong(ParsedFieldTag.TASK_MODIFY_TIME));
        fitWorkout.savePublishDate(jSONObject.optLong("createTime"));
        fitWorkout.saveDifficulty(jSONObject.optInt("difficulty"));
        fitWorkout.saveIsSupportDevice(jSONObject.optInt("supportWear"));
        fitWorkout.savePicture(jSONObject.optString("picture"));
        fitWorkout.saveEquipments(mnh.a(jSONObject.optJSONArray("equipments"), Attribute[].class));
        fitWorkout.saveUsers(jSONObject.optInt("users"));
        fitWorkout.saveExerciseTimes(jSONObject.optInt("exerciseTimes"));
        fitWorkout.saveTrainingpoints(mnh.a(jSONObject.optJSONArray("trainingPoints"), Attribute[].class));
        fitWorkout.saveNarrowPicture(jSONObject.optString("narrowPicture"));
        fitWorkout.saveNarrowDesc(a(jSONObject.optString("narrowDesc"), jSONObject.optString("extendMap")));
        fitWorkout.setIntervals(jSONObject.optInt("intervals", -4));
        fitWorkout.saveListRelativeWorkouts(moj.b(jSONObject.optString("narrowDesc"), String[].class));
        fitWorkout.saveExtendSeaMap(jSONObject.optString("extendSeaMap"));
        fitWorkout.saveStage(jSONObject.optInt("stage"));
        JSONArray optJSONArray = jSONObject.optJSONArray("secondClassify");
        if (optJSONArray != null) {
            fitWorkout.saveClassifyInfo(mnh.a(optJSONArray, Classify[].class));
        }
        fitWorkout.saveClasses(mnh.a(jSONObject.optJSONArray("classList"), Attribute[].class));
        fitWorkout.setWorkoutType(jSONObject.optInt(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE));
        JSONArray optJSONArray2 = jSONObject.optJSONArray("primaryClassify");
        if (optJSONArray2 != null) {
            fitWorkout.setPrimaryClassify(mnh.a(optJSONArray2, Classify[].class));
        }
        fitWorkout.setTopicPreviewPicUrl(jSONObject.optString("topicPreviewPicUrl"));
        fitWorkout.setSmartScreeFlag(jSONObject.optInt("smartScreenFlag"));
        fitWorkout.setTemplateType(jSONObject.optInt("extendTemplateType"));
        JSONObject optJSONObject = jSONObject.optJSONObject("extendTemplateInfo");
        if (optJSONObject != null) {
            fitWorkout.setLayoutTemplateInfo((LayoutTemplateInfo) new Gson().fromJson(optJSONObject.toString(), LayoutTemplateInfo.class));
        }
        LogUtil.a("Suggestion_CourseWorkoutParse", "fitWorkout templateInfo: ", fitWorkout.getLayoutTemplateInfo());
        fitWorkout.setCommodityFlag(jSONObject.optInt("commodityFlag"));
        fitWorkout.setIsAi(jSONObject.optInt("isAi"));
        fitWorkout.setAuthResult(jSONObject.optInt("transactionStatus"));
        fitWorkout.setCornerImgDisplay(jSONObject.optInt("cornerImgDisplay"));
        fitWorkout.setBuyNotesTitle(jSONObject.optString("buyNotesTitle"));
        fitWorkout.setBuyNotesUrl(jSONObject.optString("buyNotesUrl"));
        JSONArray optJSONArray3 = jSONObject.optJSONArray("cornerList");
        if (optJSONArray3 != null) {
            fitWorkout.setPriceTagBeanList(mnh.a(optJSONArray3, PriceTagBean[].class));
        }
        fitWorkout.setPreviewVideoUrl(jSONObject.optString("previewVideoUrl"));
        fitWorkout.setSupplierLogoUrl(jSONObject.optString("supplierLogoUrl"));
        fitWorkout.setCategory(jSONObject.optInt("courseCategory", 0));
        fitWorkout.setCourseLibraryFlag(jSONObject.optInt("courseLibraryFlag"));
        fitWorkout.setCourseDefineType(jSONObject.optInt("userDefinedType"));
        fitWorkout.setAntiScreenRecording(jSONObject.optInt("antiScreenRecording"));
        JSONArray optJSONArray4 = jSONObject.optJSONArray("belongs");
        if (optJSONArray4 != null) {
            fitWorkout.setBelongInfoList(mnh.a(optJSONArray4, BelongInfo[].class));
        }
        JSONArray optJSONArray5 = jSONObject.optJSONArray("resourceType");
        if (optJSONArray5 != null) {
            fitWorkout.setResourceTypeListToExtend(mnh.a(optJSONArray5, ResourceType[].class));
        }
        fitWorkout.setTimbre(jSONObject.optString("timbre"));
        fitWorkout.setBackgroundMusic(jSONObject.optString("backgroundMusic"));
        fitWorkout.setCalorieStartTime(jSONObject.optInt("calorieStartTime"));
        return fitWorkout;
    }

    private static String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("recommendCourses", str);
            jSONObject.putOpt("extendMap", str2);
            return jSONObject.toString();
        } catch (JSONException e) {
            LogUtil.b("Suggestion_CourseWorkoutParse", "toDetailInfoJson JSONException:", LogAnonymous.b((Throwable) e));
            return null;
        }
    }
}
