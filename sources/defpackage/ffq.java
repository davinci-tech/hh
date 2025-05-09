package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.LangFile;
import com.huawei.basefitnessadvice.model.TrainStatistics;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.Audio;
import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.pluginfitnessadvice.Cover;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.Pictures;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.tencent.connect.share.QzonePublish;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ffq {
    public static List<LangFile> c(JSONObject jSONObject) {
        List<LangFile> arrayList = new ArrayList<>(10);
        if (jSONObject == null) {
            return arrayList;
        }
        String optString = jSONObject.optString("langfiles");
        if (!TextUtils.isEmpty(optString)) {
            arrayList = moj.b(optString, LangFile[].class);
        }
        LogUtil.a("Suggestion_DataFitnessConvert", "the langFileList size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static List<Attribute> d(JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        return moj.b(jSONArray.toString(), Attribute[].class);
    }

    private static void d(List<Video> list, JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Video video = new Video();
                video.saveId(optJSONObject.optString("id"));
                video.saveName(optJSONObject.optString("name"));
                video.saveType(optJSONObject.optInt("type"));
                video.saveActionCount(optJSONObject.optInt("actionCount"));
                video.saveDuring(optJSONObject.optInt("during"));
                video.saveGender(optJSONObject.optInt("sex"));
                video.saveLength(optJSONObject.optInt(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE));
                video.saveThumbnail(optJSONObject.optString("thumbnail"));
                video.saveUrl(optJSONObject.optString("videoUrl"));
                video.saveLogoImgUrl(optJSONObject.optString("logoImgUrl"));
                video.setIsLongVideo(optJSONObject.optInt("isLongVideo"));
                video.setFileId(optJSONObject.optString(RecommendConstants.FILE_ID));
                JSONArray optJSONArray = optJSONObject.optJSONArray("videoSegmentList");
                if (optJSONArray != null) {
                    video.setVideoSegmentList(h(optJSONArray));
                }
                list.add(video);
            }
        }
    }

    private static List<VideoSegment> h(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                VideoSegment videoSegment = new VideoSegment();
                videoSegment.setStartTime(optJSONObject.optLong("startTime"));
                videoSegment.setEndTime(optJSONObject.optLong("endTime"));
                videoSegment.setDuration(optJSONObject.optInt("duration"));
                videoSegment.setThumbnail(optJSONObject.optString("thumbnail"));
                arrayList.add(videoSegment);
            }
        }
        return arrayList;
    }

    public static List<WorkoutAction> c(JSONArray jSONArray, int i, int i2) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i3);
            if (optJSONObject != null) {
                WorkoutAction workoutAction = new WorkoutAction();
                JSONArray optJSONArray = optJSONObject.optJSONArray("playAudios");
                if (optJSONArray != null) {
                    workoutAction.saveCommentaryTraining(a(optJSONArray, i));
                }
                c(optJSONObject, workoutAction);
                int e = e(i, i2);
                LogUtil.a("Suggestion_DataFitnessConvert", "DataFitnessConvert toWorkoutActions  gender: ", Integer.valueOf(e));
                if (e == 0) {
                    workoutAction.saveCalorie((float) optJSONObject.optDouble("calorieMan"));
                    workoutAction.saveDuration(optJSONObject.optInt("durationMan"));
                } else {
                    workoutAction.saveCalorie((float) optJSONObject.optDouble("calorie"));
                    workoutAction.saveDuration(optJSONObject.optInt("duration"));
                }
                AtomicAction c = c(optJSONObject, i, i2);
                workoutAction.saveActionId(c.getId());
                workoutAction.saveAction(c);
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("videos");
                if (optJSONArray2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    d(arrayList2, optJSONArray2);
                    workoutAction.saveVideos(arrayList2);
                }
                a(optJSONObject, workoutAction);
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("pictures");
                if (optJSONArray3 != null) {
                    workoutAction.setPictures(j(optJSONArray3));
                }
                arrayList.add(workoutAction);
            }
        }
        return arrayList;
    }

    private static void c(JSONObject jSONObject, WorkoutAction workoutAction) {
        Comment comment = new Comment();
        comment.saveName(jSONObject.optString("textPrompt"));
        comment.saveContent(jSONObject.optString("textPrompt"));
        workoutAction.saveCommentaryGap(new ArrayList(10));
        workoutAction.acquireCommentaryGap().add(comment);
        workoutAction.saveGroup(jSONObject.optInt(MessageConstant.GROUP_MEDAL_TYPE));
        workoutAction.saveGap(jSONObject.optInt("gap"));
        workoutAction.saveValue(jSONObject.optInt("repeatTimes"));
    }

    private static void a(JSONObject jSONObject, WorkoutAction workoutAction) {
        workoutAction.setActionStep(jSONObject.optString("actionStep"));
        workoutAction.setIntroduceLyric(jSONObject.optString("introduceLyric"));
        workoutAction.setBreath(jSONObject.optString("breath"));
        workoutAction.setFeeling(jSONObject.optString("feeling"));
        workoutAction.setCommonError(jSONObject.optString("commonError"));
    }

    private static List<Comment> a(JSONArray jSONArray, int i) {
        ArrayList arrayList = new ArrayList(10);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null && (i != 1 || optJSONObject.optInt("sex") == 1)) {
                Comment comment = new Comment();
                comment.saveId(optJSONObject.optString("audioId"));
                comment.saveName(optJSONObject.optString("audioUrl"));
                comment.saveTime(optJSONObject.optInt("playTime"));
                comment.saveType(optJSONObject.optInt("displayorder"));
                comment.saveLength(optJSONObject.optInt("audioSize"));
                arrayList.add(comment);
            }
        }
        return arrayList;
    }

    public static List<WorkoutAction> e(JSONObject jSONObject, FitWorkout fitWorkout) {
        fitWorkout.saveRunActionNum(jSONObject.optInt("runActionNum"));
        fitWorkout.saveSequenceName(jSONObject.optString("sequenceName"));
        fitWorkout.saveSequenceStage(jSONObject.optString("sequenceStage"));
        JSONObject optJSONObject = jSONObject.optJSONObject("warmUpRunAction");
        JSONArray optJSONArray = jSONObject.optJSONArray("runActionList");
        int optInt = jSONObject.optInt("repeatTimes", 1);
        fitWorkout.saveRepeatTimes(optInt);
        if (optJSONObject != null) {
            WorkoutAction workoutAction = new WorkoutAction();
            a(optJSONObject, workoutAction, fitWorkout.getType());
            fitWorkout.saveWarmUpRunAction(workoutAction);
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < optInt && optJSONArray != null; i++) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    a(optJSONArray.getJSONObject(i2), arrayList, fitWorkout.getType());
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_DataFitnessConvert", "workoutRuntoWorkoutActions exception: ", LogAnonymous.b((Throwable) e));
                }
            }
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("coolDownRunAction");
        if (optJSONObject2 != null) {
            WorkoutAction workoutAction2 = new WorkoutAction();
            a(optJSONObject2, workoutAction2, fitWorkout.getType());
            fitWorkout.saveCoolDownRunAction(workoutAction2);
        }
        return arrayList;
    }

    private static void a(JSONObject jSONObject, List<WorkoutAction> list, int i) {
        WorkoutAction workoutAction = new WorkoutAction();
        if (jSONObject != null) {
            a(jSONObject, workoutAction, i);
            list.add(workoutAction);
        }
    }

    private static void a(JSONObject jSONObject, WorkoutAction workoutAction, int i) {
        JSONArray optJSONArray = jSONObject.optJSONArray("playAudios");
        if (optJSONArray != null) {
            workoutAction.saveCommentaryTraining(d(optJSONArray, i));
        }
        e(jSONObject, workoutAction);
        if (jSONObject.isNull("treadmillSpeed")) {
            workoutAction.saveSpecifiedSpeed(0.0d);
        } else {
            workoutAction.saveSpecifiedSpeed(jSONObject.optDouble("treadmillSpeed"));
        }
        if (jSONObject.isNull("treadmillSlope")) {
            workoutAction.saveSpecifiedSlope(0.0d);
        } else {
            workoutAction.saveSpecifiedSlope(jSONObject.optDouble("treadmillSlope"));
        }
        d(jSONObject, workoutAction);
        JSONObject optJSONObject = jSONObject.optJSONObject("actionMusic");
        if (optJSONObject != null) {
            e(workoutAction, optJSONObject);
        }
        workoutAction.saveAction(h(jSONObject));
    }

    private static void d(JSONObject jSONObject, WorkoutAction workoutAction) {
        workoutAction.saveGroup(jSONObject.optInt(MessageConstant.GROUP_MEDAL_TYPE, 1));
        workoutAction.saveActionId(jSONObject.optString("runActionId"));
        workoutAction.saveMeasurementType(jSONObject.optInt("measurementType"));
        workoutAction.saveMeasurementValue(jSONObject.optInt("measurementValue"));
        workoutAction.saveGap(jSONObject.optInt("restSecond"));
        workoutAction.saveTabloidPicUrl(jSONObject.optString("picture"));
    }

    private static void e(WorkoutAction workoutAction, JSONObject jSONObject) {
        Audio audio = new Audio();
        audio.saveUrl(jSONObject.optString("url"));
        audio.saveLength(jSONObject.optInt("length"));
        audio.setId("");
        audio.setDuring(0);
        audio.setIdentification("");
        audio.saveName("");
        audio.saveGender(0);
        workoutAction.setActionMusic(audio);
    }

    private static void e(JSONObject jSONObject, WorkoutAction workoutAction) {
        workoutAction.saveIntensityType(jSONObject.optInt("intensityType"));
        workoutAction.saveSpeedH(jSONObject.optInt("speedH"));
        workoutAction.saveSpeedL(jSONObject.optInt("speedL"));
        workoutAction.saveAbsoluteHeartRateH(jSONObject.optString("absoluteHeartRateH"));
        workoutAction.saveAbsoluteHeartRateL(jSONObject.optString("absoluteHeartRateL"));
        workoutAction.saveRelativeHeartRatePercentH(jSONObject.optInt("relativeHeartRatePercentH"));
        workoutAction.saveRelativeHeartRatePercentL(jSONObject.optInt("relativeHeartRatePercentL"));
        workoutAction.saveRelativeHeartRateRangeH(jSONObject.optInt("relativeHeartRateRangeH"));
        workoutAction.saveRelativeHeartRateRangeL(jSONObject.optInt("relativeHeartRateRangeL"));
        workoutAction.saveReserveHeartRatePercentH(jSONObject.optInt("heartRRH"));
        workoutAction.saveReserveHeartRatePercentL(jSONObject.optInt("heartRRL"));
        workoutAction.saveReserveHeartRateRangeH(jSONObject.optInt("heartRRIntervalH"));
        workoutAction.saveReserveHeartRateRangeL(jSONObject.optInt("heartRRIntervalL"));
        workoutAction.saveMaf180HeartRateBase(jSONObject.optInt("baseOfMAF180"));
        workoutAction.saveMaf180HeartRateRange(jSONObject.optInt("heartRIntervalofMAF180"));
    }

    private static List<Comment> d(JSONArray jSONArray, int i) {
        ArrayList arrayList = new ArrayList(10);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null && (i != 1 || optJSONObject.optInt("sex") == 1)) {
                Comment comment = new Comment();
                comment.saveId(optJSONObject.optString("audioId"));
                comment.saveName(optJSONObject.optString("audioUrl"));
                comment.saveType(optJSONObject.optInt("displayorder"));
                comment.saveLength(optJSONObject.optInt("audioSize"));
                comment.savePlayValue(optJSONObject.optString("playValue"));
                comment.savePlayType(optJSONObject.optString("playType"));
                arrayList.add(comment);
            }
        }
        return arrayList;
    }

    private static AtomicAction h(JSONObject jSONObject) {
        AtomicAction atomicAction = new AtomicAction();
        atomicAction.setId(jSONObject.optString("runActionId"));
        atomicAction.setName(jSONObject.optString("userRunActionName"));
        atomicAction.setDifficulty(jSONObject.optInt("difficulty"));
        atomicAction.setDefaultTargetType(jSONObject.optInt("measurementType"));
        atomicAction.putExtendProperty("frequency", jSONObject.optString("measurementValue"));
        atomicAction.putExtendProperty("logoImgUrl", jSONObject.optString("logoImgUrl"));
        ArrayList arrayList = new ArrayList(10);
        JSONArray optJSONArray = jSONObject.optJSONArray("voicePrompt");
        LogUtil.c("Suggestion_DataFitnessConvert", "SingleRunActionInfo.VOICEPROMPT:", String.valueOf(optJSONArray));
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Audio audio = new Audio();
                    audio.saveUrl(optJSONObject.optString("audioUrl"));
                    audio.saveName(optJSONObject.optString("audioUrl"));
                    audio.saveGender(optJSONObject.optInt("sex"));
                    audio.saveLength(optJSONObject.optInt("audioSize"));
                    arrayList.add(audio);
                }
            }
        }
        atomicAction.putExtendProperty("audios", arrayList);
        List<Cover> arrayList2 = new ArrayList<>(10);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("pictures");
        LogUtil.c("Suggestion_DataFitnessConvert", "pictureArray:", String.valueOf(optJSONArray2));
        if (optJSONArray2 != null) {
            LogUtil.c("Suggestion_DataFitnessConvert", "pictureArray:", String.valueOf(optJSONArray2));
            StringBuffer e = e(optJSONArray2);
            List<Cover> b = b(optJSONArray2);
            atomicAction.setDescription(e.toString());
            arrayList2 = b;
        }
        atomicAction.setCovers(arrayList2);
        atomicAction.setEquipments(d(jSONObject.optJSONArray("equipments")));
        atomicAction.setTrainingPoints(d(jSONObject.optJSONArray("trainingPoints")));
        return atomicAction;
    }

    private static int e(int i, int i2) {
        int a2 = ggg.a();
        if (i == 1) {
            return i2 != 2 ? i2 : a2;
        }
        return 1;
    }

    private static AtomicAction c(JSONObject jSONObject, int i, int i2) {
        AtomicAction atomicAction = new AtomicAction();
        c(jSONObject, i, i2, atomicAction);
        atomicAction.putExtendProperty("frequency", jSONObject.optString("repeatTimes"));
        atomicAction.setActionSportType(jSONObject.optInt("measurement") + 1);
        atomicAction.setEquipments(d(jSONObject.optJSONArray("equipments")));
        atomicAction.setTrainingPoints(d(jSONObject.optJSONArray("trainingPoints")));
        ArrayList<Video> arrayList = new ArrayList(10);
        JSONArray optJSONArray = jSONObject.optJSONArray("videos");
        if (optJSONArray != null) {
            d(arrayList, optJSONArray);
            if (arrayList.size() == 1) {
                d(arrayList, optJSONArray);
            }
            for (Video video : arrayList) {
                if (video != null) {
                    video.saveIdentification("identification");
                }
            }
        }
        atomicAction.putExtendProperty("actionVideo", arrayList);
        ArrayList arrayList2 = new ArrayList(10);
        c(jSONObject, arrayList2);
        atomicAction.putExtendProperty("audios", arrayList2);
        List<Cover> arrayList3 = new ArrayList<>(10);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("pictures");
        if (optJSONArray2 != null) {
            StringBuffer e = e(optJSONArray2);
            List<Cover> b = b(optJSONArray2);
            atomicAction.setDescription(e.toString());
            arrayList3 = b;
        }
        atomicAction.setCovers(arrayList3);
        return atomicAction;
    }

    private static void c(JSONObject jSONObject, List<Audio> list) {
        JSONArray optJSONArray = jSONObject.optJSONArray("voicePrompt");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Audio audio = new Audio();
                    audio.saveUrl(optJSONObject.optString("audioUrl"));
                    audio.saveName(optJSONObject.optString("audioUrl"));
                    audio.saveGender(optJSONObject.optInt("sex"));
                    audio.saveLength(optJSONObject.optInt("audioSize"));
                    list.add(audio);
                }
            }
        }
    }

    private static void c(JSONObject jSONObject, int i, int i2, AtomicAction atomicAction) {
        atomicAction.saveId(jSONObject.optString("actionId"));
        atomicAction.saveName(jSONObject.optString("name"));
        int e = e(i, i2);
        LogUtil.a("Suggestion_DataFitnessConvert", "DataFitnessConvert toAction gender: ", Integer.valueOf(e));
        if (e == 0) {
            atomicAction.setDifficulty(jSONObject.optInt("difficultyMan"));
        } else {
            atomicAction.setDifficulty(jSONObject.optInt("difficulty"));
        }
        atomicAction.setVersion(jSONObject.optString("version"));
    }

    public static TrainStatistics g(JSONObject jSONObject) {
        JSONObject optJSONObject;
        TrainStatistics trainStatistics = new TrainStatistics();
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("userTrainStatisticsBean")) == null) {
            return trainStatistics;
        }
        trainStatistics.saveCalorie(optJSONObject.optLong("calorie"));
        trainStatistics.saveDuration(optJSONObject.optLong("duration"));
        trainStatistics.saveTotalTimes(optJSONObject.optLong("totalTimes"));
        return trainStatistics;
    }

    public static PlanStatistics b(JSONObject jSONObject) {
        PlanStatistics planStatistics = new PlanStatistics();
        if (jSONObject == null) {
            return planStatistics;
        }
        planStatistics.saveCalorie(jSONObject.optLong("totalCalorie"));
        planStatistics.saveDuration(jSONObject.optLong("totalDuration"));
        planStatistics.setTotalDistance(jSONObject.optDouble(BleConstants.TOTAL_DISTANCE));
        planStatistics.saveTotalPlan(jSONObject.optInt("totalPlan"));
        return planStatistics;
    }

    public static List<PlanStatistics> d(JSONObject jSONObject) {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList(10);
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("userPlanStatisticsList")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                PlanStatistics planStatistics = new PlanStatistics();
                planStatistics.saveType(optJSONObject.optInt("type"));
                if (planStatistics.acquireType() == 0) {
                    planStatistics.saveDuration(optJSONObject.optLong("totalDuration"));
                    planStatistics.setTotalDistance(optJSONObject.optDouble(BleConstants.TOTAL_DISTANCE));
                } else {
                    planStatistics.saveDuration(optJSONObject.optLong("totalDuration"));
                }
                planStatistics.saveCalorie(optJSONObject.optLong("totalCalorie"));
                planStatistics.saveTotalPlan(optJSONObject.optInt("totalPlan"));
                arrayList.add(planStatistics);
            }
        }
        return arrayList;
    }

    public static TrainStatistics a(String str) {
        TrainStatistics trainStatistics = new TrainStatistics();
        if (TextUtils.isEmpty(str)) {
            return trainStatistics;
        }
        try {
            Map map = (Map) new Gson().fromJson(str, new TypeToken<Map<String, Long>>() { // from class: ffq.1
            }.getType());
            if (map == null) {
                return trainStatistics;
            }
            Long l = (Long) map.get("calorie");
            Long l2 = (Long) map.get("duration");
            Long l3 = (Long) map.get("totalTimes");
            if (l == null) {
                l = 0L;
            }
            if (l2 == null) {
                l2 = 0L;
            }
            if (l3 == null) {
                l3 = 0L;
            }
            trainStatistics.saveCalorie(l.longValue());
            trainStatistics.saveDuration(l2.longValue());
            trainStatistics.saveTotalTimes(l3.longValue());
            return trainStatistics;
        } catch (JsonSyntaxException e) {
            LogUtil.b("Suggestion_DataFitnessConvert", "fromTrainStatisticsMap exception: ", LogAnonymous.b((Throwable) e));
            return trainStatistics;
        }
    }

    public static String e(TrainStatistics trainStatistics) {
        if (trainStatistics == null) {
            return null;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("calorie", Long.valueOf(trainStatistics.acquireCalorie()));
        hashMap.put("duration", Long.valueOf(trainStatistics.acquireDuration()));
        hashMap.put("totalTimes", Long.valueOf(trainStatistics.acquireTotalTimes()));
        return new Gson().toJson(hashMap);
    }

    public static String e(PlanStatistics planStatistics) {
        if (planStatistics == null) {
            return null;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("totalCalorie", Long.valueOf(planStatistics.acquireCalorie()));
        hashMap.put("totalDuration", Long.valueOf(planStatistics.acquireDuration()));
        hashMap.put("totalPlan", Integer.valueOf(planStatistics.acquireTotalPlan()));
        hashMap.put(BleConstants.TOTAL_DISTANCE, Double.valueOf(planStatistics.getTotalDistance()));
        hashMap.put("type", Long.valueOf(planStatistics.acquireType()));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        return gsonBuilder.create().toJson(hashMap);
    }

    public static List<TrainAction> b(String str, JSONArray jSONArray) {
        try {
            LogUtil.a("Suggestion_DataFitnessConvert", "parseToTrainActions actionArray");
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(10);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                TrainAction trainAction = new TrainAction();
                trainAction.saveUserId(str);
                trainAction.saveId(jSONObject.optString("actionId"));
                trainAction.saveName(jSONObject.optString("name"));
                trainAction.saveMotionType(e(jSONObject.optInt("measurement")));
                trainAction.saveDifficulty(jSONObject.getInt("difficulty"));
                List<Cover> arrayList2 = new ArrayList<>(10);
                JSONArray optJSONArray = jSONObject.optJSONArray("pictures");
                if (optJSONArray != null) {
                    StringBuffer e = e(optJSONArray);
                    List<Cover> b = b(optJSONArray);
                    trainAction.saveDescription(e.toString());
                    arrayList2 = b;
                }
                trainAction.saveCovers(arrayList2);
                trainAction.saveEquipments(d(jSONObject.optJSONArray("equipments")));
                trainAction.saveTrainingpoints(d(jSONObject.optJSONArray("trainingPoints")));
                ArrayList arrayList3 = new ArrayList(10);
                d(arrayList3, jSONObject.getJSONArray("videos"));
                trainAction.saveVideos(arrayList3);
                trainAction.saveVersion(jSONObject.optString("version"));
                trainAction.saveModified(System.currentTimeMillis());
                arrayList.add(trainAction);
            }
            return arrayList;
        } catch (JSONException e2) {
            LogUtil.b("Suggestion_DataFitnessConvert", "parseToTrainActions exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public static List<AtomicAction> c(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                ArrayList arrayList = new ArrayList(10);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    AtomicAction atomicAction = new AtomicAction();
                    atomicAction.setId(jSONObject.optString("actionId"));
                    atomicAction.saveName(jSONObject.optString("name"));
                    atomicAction.setVersion(jSONObject.optString("version"));
                    ArrayList arrayList2 = new ArrayList(10);
                    d(arrayList2, jSONObject.optJSONArray("actionVideo"));
                    atomicAction.putExtendProperty("actionVideo", arrayList2);
                    atomicAction.setTrainingPoints(f(jSONObject.optJSONArray("trainingPoints")));
                    atomicAction.putExtendProperty("actionStep", jSONObject.optString("actionStep"));
                    atomicAction.putExtendProperty("breath", jSONObject.optString("breath"));
                    atomicAction.putExtendProperty("calorie", jSONObject.optString("calorie"));
                    atomicAction.putExtendProperty("commonError", jSONObject.optString("commonError"));
                    atomicAction.putExtendProperty("feeling", jSONObject.optString("feeling"));
                    atomicAction.putExtendProperty("introduceLyric", jSONObject.optString("introduceLyric"));
                    atomicAction.setShowInActionLibrary(jSONObject.optInt("showInActionLibrary"));
                    arrayList.add(atomicAction);
                }
                return arrayList;
            } catch (JSONException e) {
                LogUtil.b("Suggestion_DataFitnessConvert", "parseToFitnessActionList exception", LogAnonymous.b((Throwable) e));
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List<AtomicAction> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    AtomicAction atomicAction = new AtomicAction();
                    atomicAction.setId(jSONObject.optString("actionId"));
                    atomicAction.saveName(jSONObject.optString("name"));
                    atomicAction.setDescription(jSONObject.optString("actionDesc"));
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_DataFitnessConvert", "parseToCustomActionList exception", LogAnonymous.b((Throwable) e));
                }
            }
        }
        return arrayList;
    }

    public static AtomicAction a(JSONObject jSONObject) {
        AtomicAction atomicAction = new AtomicAction();
        if (jSONObject != null) {
            atomicAction.setId(jSONObject.optString("actionId"));
            atomicAction.setName(jSONObject.optString("name"));
            atomicAction.setVersion(jSONObject.optString("version"));
            ArrayList arrayList = new ArrayList(10);
            d(arrayList, jSONObject.optJSONArray("actionVideo"));
            atomicAction.putExtendProperty("actionVideo", arrayList);
            atomicAction.putExtendProperty("actionStep", jSONObject.optString("actionStep"));
            atomicAction.setTrainingPoints(f(jSONObject.optJSONArray("trainingPoints")));
            atomicAction.putExtendProperty("pictures", j(jSONObject.optJSONArray("pictures")));
            atomicAction.setDifficulty(jSONObject.optInt("difficulty"));
            atomicAction.putExtendProperty("breath", jSONObject.optString("breath"));
            atomicAction.putExtendProperty("calorie", jSONObject.optString("calorie"));
            atomicAction.putExtendProperty("commonError", jSONObject.optString("commonError"));
            atomicAction.putExtendProperty("feeling", jSONObject.optString("feeling"));
            atomicAction.putExtendProperty("introduceLyric", jSONObject.optString("introduceLyric"));
            atomicAction.setShowInActionLibrary(jSONObject.optInt("showInActionLibrary"));
            atomicAction.putExtendProperty("aiFlag", jSONObject.optString("aiFlag"));
            atomicAction.putExtendProperty("completeTimes", jSONObject.optString("completeTimes"));
            atomicAction.putExtendProperty("aiMeasurement", jSONObject.optString("aiMeasurement"));
            atomicAction.putExtendProperty("duration", String.valueOf(jSONObject.optLong("duration")));
        }
        return atomicAction;
    }

    private static List<Attribute> f(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String string = jSONObject.getString("id");
                    String string2 = jSONObject.getString("name");
                    Attribute attribute = new Attribute();
                    attribute.saveId(string);
                    attribute.saveName(string2);
                    arrayList.add(attribute);
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_DataFitnessConvert", "parseToFitnessActions exception", LogAnonymous.b((Throwable) e));
                }
            }
        }
        return arrayList;
    }

    private static List<Pictures> j(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String optString = jSONObject.optString("backMusclePicUrl");
                    String optString2 = jSONObject.optString("displayorder");
                    String optString3 = jSONObject.optString("frontMusclePicUrl");
                    String optString4 = jSONObject.optString("sex");
                    String optString5 = jSONObject.optString("sexId");
                    Pictures pictures = new Pictures();
                    pictures.setBackMusclePicUrl(optString);
                    pictures.setDisplayorder(optString2);
                    pictures.setFrontMusclePicUrl(optString3);
                    pictures.setSex(optString4);
                    pictures.setSexId(optString5);
                    arrayList.add(pictures);
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_DataFitnessConvert", "toPictures exception", LogAnonymous.b((Throwable) e));
                }
            }
        }
        return arrayList;
    }

    private static StringBuffer e(JSONArray jSONArray) {
        StringBuffer stringBuffer = new StringBuffer();
        if (jSONArray == null) {
            return stringBuffer;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && i == 0) {
                stringBuffer.append(optJSONObject.optString("imageDesc"));
            }
        }
        return stringBuffer;
    }

    private static List<Cover> b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Cover cover = new Cover();
                cover.saveUrl(optJSONObject.optString("imageUrl"));
                cover.saveGender(optJSONObject.optInt("sex"));
                arrayList.add(cover);
            }
        }
        return arrayList;
    }

    public static int c() {
        return Utils.o() ? 204 : 4;
    }

    public static LongVideoInfo e(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LongVideoInfo longVideoInfo = new LongVideoInfo();
        longVideoInfo.setUrl(jSONObject.optString("url"));
        longVideoInfo.setSubtitlesUrl(jSONObject.optString("subtitlesUrl"));
        longVideoInfo.setResultCode(jSONObject.optInt("resultCode"));
        longVideoInfo.setResultDesc(jSONObject.optString("resultDesc"));
        return longVideoInfo;
    }

    public static String e(int i) {
        if (i == 1) {
            return "beating";
        }
        return i == 2 ? "timer" : "hotbody";
    }
}
