package com.huawei.health.sport.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.RateInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.gge;
import defpackage.gic;
import defpackage.mmt;
import defpackage.moj;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WorkoutRecord implements Parcelable {
    public static final int COURSE_TYPE_BEITI = 333;
    public static final int COURSE_TYPE_BEIYI = 222;
    public static final int COURSE_TYPE_COMMON = 111;
    public static final int COURSE_TYPE_FREE_RIDE = 777;
    public static final int COURSE_TYPE_FREE_RUN = 666;
    public static final int COURSE_TYPE_SQUARE_DANCE = 555;
    public static final int COURSE_TYPE_YOGA = 444;
    public static final Parcelable.Creator<WorkoutRecord> CREATOR = new Parcelable.Creator<WorkoutRecord>() { // from class: com.huawei.health.sport.model.WorkoutRecord.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: awO_, reason: merged with bridge method [inline-methods] */
        public WorkoutRecord createFromParcel(Parcel parcel) {
            return new WorkoutRecord(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public WorkoutRecord[] newArray(int i) {
            return new WorkoutRecord[i];
        }
    };
    private static final String TAG = "Suggestion_WorkoutRecord";

    @SerializedName("actionSummary")
    private String mActionSummary;

    @SerializedName("actualCalorie")
    private float mActualCalorie;

    @SerializedName("actualDistance")
    private float mActualDistance;

    @SerializedName("bestPace")
    private int mBestPace;

    @SerializedName("calorie")
    private float mCalorie;

    @SerializedName("courseCategory")
    private int mCategory;

    @SerializedName("courseBelongType")
    private int mCourseBelongType;

    @SerializedName("distance")
    private float mDistance;

    @SerializedName("during")
    private int mDuration;

    @SerializedName("exerciseTime")
    private long mExerciseTime;

    @SerializedName("extend")
    private String mExtend;

    @SerializedName("finishRate")
    private float mFinishRate;
    private Map<String, String> mFitExtendMap;
    private boolean mHasConvertToMap;

    @SerializedName("heartRateList")
    private List<HeartRateData> mHeartRateDataList;

    @SerializedName("id")
    private int mId;

    @SerializedName("intensityZone")
    private List<Integer> mIntensityZone;

    @SerializedName("invalidHeartRateList")
    private List<HeartRateData> mInvalidHeartRateList;

    @SerializedName("lowerHeartRate")
    private int mLowerHeartRate;

    @SerializedName("oxygen")
    private double mOxygen;

    @SerializedName("planId")
    private String mPlanId;

    @SerializedName("recordModeType")
    private int mRecordModeType;

    @SerializedName("recordType")
    private int mRecordType;
    private Map<String, Object> mRootExtendMap;
    private Map<String, String> mRunExtendMap;

    @SerializedName(ParsedFieldTag.NPES_TOTAL_SCORE)
    private long mTotalScore;

    @SerializedName(HwExerciseConstants.JSON_NAME_TRAINING_POINTS)
    private int mTrainPoint;

    @SerializedName("trainingLoadPeak")
    private int mTrainingLoadPeak;

    @SerializedName(Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID)
    private String mTrajectoryId;

    @SerializedName("upperHeartRate")
    private int mUpperHeartRate;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("wearType")
    private int mWearType;

    @SerializedName("weekNum")
    private int mWeekNum;

    @SerializedName("workoutDate")
    private String mWorkoutDate;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String mWorkoutId;

    @SerializedName("workoutName")
    private String mWorkoutName;

    @SerializedName("workoutOrder")
    private int mWorkoutOrder;

    @SerializedName("workoutPackageId")
    private String mWorkoutPackageId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* loaded from: classes4.dex */
    public interface Extend {
        public static final String COURSE_DEFINE_TYPE = "courseDefineType";
        public static final String COURSE_MODIFIED_TIME = "courseModifiedTime";
        public static final String COURSE_SPORT_TYPE = "courseSportType";
        public static final String COURSE_TARGET_TYPE = "targetType";
        public static final String COURSE_TARGET_VALUE = "targetValue";
        public static final String FIT_EXTEND_ACTION_COMPLETION_STATUS = "actionCompletionStatus";
        public static final String FIT_EXTEND_AI_MODE = "aiMode";
        public static final String FIT_EXTEND_COURSE_TYPE = "courseType";
        public static final String FIT_EXTEND_PRODUCT_ID = "productId";
        public static final String FIT_EXTEND_SCORE_RADAR = "scoreRadar";
        public static final String FIT_EXTEND_STRETCH_RESULT = "stretchResult";
        public static final String FIT_EXTEND_WEAR_MODE = "wearMode";
        public static final String IS_LINK_WEAR = "isLinkWear";
        public static final String PLAN_TRAIN_DATE = "planTrainDate";
        public static final String TRAIN_FEEL = "trainFeel";

        /* loaded from: classes8.dex */
        public interface RunWorkout {
            public static final String RUN_WORKOUT = "run_workout";
            public static final String RUN_WORKOUT_TRAJECTORY_ID = "trajectoryId";
        }

        public static class e {

            /* renamed from: a, reason: collision with root package name */
            private static final Set<String> f2993a;

            static {
                HashSet hashSet = new HashSet();
                f2993a = hashSet;
                hashSet.add("fit_single");
                hashSet.add("single");
                hashSet.add("times");
                hashSet.add("rateInfo");
                hashSet.add("oneMinBest");
                hashSet.add("heartRateConfig");
                hashSet.add("avgHeartRate");
                hashSet.add("startTime");
                hashSet.add("courseDuration");
                hashSet.add("difficulty");
                hashSet.add("heartRateZoneType");
                hashSet.add("heartRatePostureType");
                hashSet.add("isFitnessRecordFromDevice");
                hashSet.add("courseCategory");
                hashSet.add("sportRecordType");
                hashSet.add("perfectTimes");
                hashSet.add("excellentTimes");
                hashSet.add("goodTimes");
                hashSet.add("curRank");
                hashSet.add("isNewRecord");
                hashSet.add("userFinishRate");
                hashSet.add("aiActionMeasureType");
                hashSet.add("aiActionMeasureValue");
                hashSet.add(HwExerciseConstants.JSON_NAME_ACTIVECALORIE);
            }

            public static boolean c(String str) {
                return f2993a.contains(str);
            }
        }
    }

    public WorkoutRecord() {
        this.mRootExtendMap = new HashMap();
        this.mFitExtendMap = new HashMap();
        this.mRunExtendMap = new HashMap();
        this.mHasConvertToMap = false;
    }

    protected WorkoutRecord(Parcel parcel) {
        this.mRootExtendMap = new HashMap();
        this.mFitExtendMap = new HashMap();
        this.mRunExtendMap = new HashMap();
        this.mHasConvertToMap = false;
        this.mId = parcel.readInt();
        this.mPlanId = parcel.readString();
        this.mWorkoutId = parcel.readString();
        this.mWorkoutPackageId = parcel.readString();
        this.mCourseBelongType = parcel.readInt();
        this.mWorkoutName = parcel.readString();
        this.mWorkoutDate = parcel.readString();
        this.mWeekNum = parcel.readInt();
        this.mExerciseTime = parcel.readLong();
        this.mDuration = parcel.readInt();
        this.mActualCalorie = parcel.readFloat();
        this.mActualDistance = parcel.readFloat();
        this.mCalorie = parcel.readFloat();
        this.mDistance = parcel.readFloat();
        this.mFinishRate = parcel.readFloat();
        this.mWorkoutOrder = parcel.readInt();
        this.mUpperHeartRate = parcel.readInt();
        this.mLowerHeartRate = parcel.readInt();
        this.mBestPace = parcel.readInt();
        this.mOxygen = parcel.readDouble();
        this.mTrainingLoadPeak = parcel.readInt();
        this.mTrajectoryId = parcel.readString();
        this.mRecordType = parcel.readInt();
        this.mVersion = parcel.readString();
        this.mActionSummary = parcel.readString();
        this.mWearType = parcel.readInt();
        this.mExtend = parcel.readString();
        this.mRootExtendMap = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mFitExtendMap = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mRunExtendMap = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mHasConvertToMap = parcel.readByte() != 0;
        ArrayList arrayList = new ArrayList();
        this.mHeartRateDataList = arrayList;
        parcel.readList(arrayList, HeartRateData.class.getClassLoader());
        ArrayList arrayList2 = new ArrayList();
        this.mInvalidHeartRateList = arrayList2;
        parcel.readList(arrayList2, HeartRateData.class.getClassLoader());
        this.mCategory = parcel.readInt();
        ArrayList arrayList3 = new ArrayList();
        this.mIntensityZone = arrayList3;
        parcel.readList(arrayList3, Integer.class.getClassLoader());
        this.mTrainPoint = parcel.readInt();
        this.mTotalScore = parcel.readLong();
        this.mRecordModeType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mPlanId);
        parcel.writeString(this.mWorkoutId);
        parcel.writeString(this.mWorkoutPackageId);
        parcel.writeInt(this.mCourseBelongType);
        parcel.writeString(this.mWorkoutName);
        parcel.writeString(this.mWorkoutDate);
        parcel.writeInt(this.mWeekNum);
        parcel.writeLong(this.mExerciseTime);
        parcel.writeInt(this.mDuration);
        parcel.writeFloat(this.mActualCalorie);
        parcel.writeFloat(this.mActualDistance);
        parcel.writeFloat(this.mCalorie);
        parcel.writeFloat(this.mDistance);
        parcel.writeFloat(this.mFinishRate);
        parcel.writeInt(this.mWorkoutOrder);
        parcel.writeInt(this.mUpperHeartRate);
        parcel.writeInt(this.mLowerHeartRate);
        parcel.writeInt(this.mBestPace);
        parcel.writeDouble(this.mOxygen);
        parcel.writeInt(this.mTrainingLoadPeak);
        parcel.writeString(this.mTrajectoryId);
        parcel.writeInt(this.mRecordType);
        parcel.writeString(this.mVersion);
        parcel.writeString(this.mActionSummary);
        parcel.writeInt(this.mWearType);
        parcel.writeString(this.mExtend);
        checkAndConvertExtendToMap();
        parcel.writeMap(this.mRootExtendMap);
        parcel.writeMap(this.mFitExtendMap);
        parcel.writeMap(this.mRunExtendMap);
        parcel.writeByte(this.mHasConvertToMap ? (byte) 1 : (byte) 0);
        List<HeartRateData> list = this.mHeartRateDataList;
        if (list == null) {
            parcel.writeList(new ArrayList());
        } else {
            parcel.writeList(list);
        }
        List<HeartRateData> list2 = this.mInvalidHeartRateList;
        if (list2 == null) {
            parcel.writeList(new ArrayList());
        } else {
            parcel.writeList(list2);
        }
        parcel.writeInt(this.mCategory);
        List<Integer> list3 = this.mIntensityZone;
        if (list3 == null) {
            parcel.writeList(new ArrayList());
        } else {
            parcel.writeList(list3);
        }
        parcel.writeInt(this.mTrainPoint);
        parcel.writeLong(this.mTotalScore);
        parcel.writeInt(this.mRecordModeType);
    }

    public int acquireId() {
        return this.mId;
    }

    public void saveId(int i) {
        this.mId = i;
    }

    public String acquirePlanId() {
        return this.mPlanId;
    }

    public void savePlanId(String str) {
        this.mPlanId = str;
    }

    public String acquireWorkoutId() {
        return this.mWorkoutId;
    }

    public void saveWorkoutId(String str) {
        this.mWorkoutId = str;
    }

    public String acquireWorkoutPackageId() {
        return this.mWorkoutPackageId;
    }

    public void saveWorkoutPackageId(String str) {
        this.mWorkoutPackageId = str;
    }

    public int acquireCourseBelongType() {
        return this.mCourseBelongType;
    }

    public void saveCourseBelongType(int i) {
        this.mCourseBelongType = i;
    }

    public String acquireWorkoutName() {
        return this.mWorkoutName;
    }

    public void saveWorkoutName(String str) {
        this.mWorkoutName = str;
    }

    public String acquireWorkoutDate() {
        return this.mWorkoutDate;
    }

    public void saveWorkoutDate(String str) {
        this.mWorkoutDate = str;
    }

    public long acquireExerciseTime() {
        return this.mExerciseTime;
    }

    public void saveExerciseTime(long j) {
        this.mExerciseTime = j;
    }

    public int getPlanTrainDate() {
        return gic.e((Object) getExtendProperty(Extend.PLAN_TRAIN_DATE));
    }

    public void setPlanTrainDate(int i) {
        putExtendProperty(Extend.PLAN_TRAIN_DATE, String.valueOf(i));
    }

    public boolean isLinkWear() {
        return Boolean.parseBoolean(getExtendProperty(Extend.IS_LINK_WEAR));
    }

    public void setLinkWear(boolean z) {
        putExtendProperty(Extend.IS_LINK_WEAR, Boolean.toString(z));
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public float acquireActualCalorie() {
        return this.mActualCalorie;
    }

    public void saveActualCalorie(float f) {
        this.mActualCalorie = f;
    }

    public float acquireActualDistance() {
        return this.mActualDistance;
    }

    public void saveActualDistance(float f) {
        this.mActualDistance = f;
    }

    public float acquireFinishRate() {
        return this.mFinishRate;
    }

    public void saveFinishRate(float f) {
        this.mFinishRate = f;
    }

    public int acquireWorkoutOrder() {
        return this.mWorkoutOrder;
    }

    public void saveWorkoutOrder(int i) {
        this.mWorkoutOrder = i;
    }

    public int acquireUpperHeartRate() {
        return this.mUpperHeartRate;
    }

    public void saveUpperHeartRate(int i) {
        this.mUpperHeartRate = i;
    }

    public int acquireLowerHeartRate() {
        return this.mLowerHeartRate;
    }

    public void saveLowerHeartRate(int i) {
        this.mLowerHeartRate = i;
    }

    public int acquireBestPace() {
        return this.mBestPace;
    }

    public void saveBestPace(int i) {
        this.mBestPace = i;
    }

    public double acquireOxygen() {
        return this.mOxygen;
    }

    public void saveOxygen(double d) {
        this.mOxygen = d;
    }

    public int acquireTrainingLoadPeak() {
        return this.mTrainingLoadPeak;
    }

    public void saveTrainingLoadPeak(int i) {
        this.mTrainingLoadPeak = i;
    }

    public int acquireWeekNum() {
        return this.mWeekNum;
    }

    public void saveWeekNum(int i) {
        this.mWeekNum = i;
    }

    public float acquireCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public static Parcelable.Creator<WorkoutRecord> getCREATOR() {
        return CREATOR;
    }

    public float acquireDistance() {
        return this.mDistance;
    }

    public void saveDistance(float f) {
        this.mDistance = f;
    }

    public String acquireTrajectoryId() {
        String str = this.mTrajectoryId;
        if (str != null) {
            return str;
        }
        String acquireRunWorkoutTrajectoryId = acquireRunWorkoutTrajectoryId();
        if (acquireRunWorkoutTrajectoryId != null) {
            this.mTrajectoryId = acquireRunWorkoutTrajectoryId;
        }
        return this.mTrajectoryId;
    }

    public void saveTrajectoryId(String str) {
        this.mTrajectoryId = str;
        saveExtendRunWorkout(str);
    }

    public int acquireRecordType() {
        return this.mRecordType;
    }

    public void saveRecordType(int i) {
        this.mRecordType = i;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public String acquireActionSummary() {
        return this.mActionSummary;
    }

    public void saveActionSummary(String str) {
        this.mActionSummary = str;
    }

    public List<HeartRateData> acquireHeartRateDataList() {
        return this.mHeartRateDataList;
    }

    public void saveInvalidHeartRateList(List<HeartRateData> list) {
        this.mInvalidHeartRateList = list;
    }

    public List<HeartRateData> acquireInvalidHeartRateList() {
        return this.mInvalidHeartRateList;
    }

    public int acquireCategory() {
        return gic.e((Object) getExtendProperty("courseCategory"));
    }

    public void saveCategory(int i) {
        putExtendProperty("courseCategory", String.valueOf(i));
    }

    public int acquireCategoryFromExtend() {
        return acquireCategory();
    }

    public void setCategoryIntoExtend(int i) {
        saveCategory(i);
    }

    public void saveHeartRateDataList(List<HeartRateData> list) {
        this.mHeartRateDataList = list;
    }

    public String acquireExtend() {
        convertMapToString();
        return this.mExtend;
    }

    public void saveExtend(String str) {
        this.mExtend = str;
        convertExtendToMap();
    }

    public List<Integer> getIntensityZone() {
        return this.mIntensityZone;
    }

    public void setIntensityZone(List<Integer> list) {
        this.mIntensityZone = list;
    }

    public int getTrainPoint() {
        return this.mTrainPoint;
    }

    public void setTrainPoint(int i) {
        this.mTrainPoint = i;
    }

    public long getTotalScore() {
        return this.mTotalScore;
    }

    public void saveTotalScore(long j) {
        this.mTotalScore = j;
    }

    public int getRecordModeType() {
        return this.mRecordModeType;
    }

    public void saveRecordModeType(int i) {
        this.mRecordModeType = i;
    }

    public void saveExtend(mmt mmtVar, boolean z) {
        putExtendProperty("heartRateConfig", mmtVar.g());
        putExtendProperty("avgHeartRate", Integer.toString(mmtVar.a()));
        putExtendProperty("startTime", Long.toString(mmtVar.h()));
        putExtendProperty("courseDuration", Integer.toString(mmtVar.c()));
        putExtendProperty("difficulty", Integer.toString(mmtVar.i()));
        putExtendProperty("heartRateZoneType", Integer.toString(mmtVar.f()));
        putExtendProperty("heartRatePostureType", Integer.toString(mmtVar.j()));
        putExtendProperty("isFitnessRecordFromDevice", String.valueOf(z));
        putExtendProperty("courseCategory", Integer.toString(mmtVar.b()));
        if (mmtVar.d() > 0) {
            putExtendProperty(Extend.COURSE_MODIFIED_TIME, String.valueOf(mmtVar.d()));
        }
        if (mmtVar.l() > 0) {
            putExtendProperty(Extend.TRAIN_FEEL, String.valueOf(mmtVar.l()));
        }
        if (mmtVar.e() > 0) {
            putExtendProperty(Extend.COURSE_SPORT_TYPE, String.valueOf(mmtVar.e()));
        }
    }

    @Deprecated
    public void saveExtend(boolean z, int i, int i2, RateInfo rateInfo) {
        putExtendProperty("single", z + "");
        putExtendProperty("times", i + "");
        putExtendProperty("oneMinBest", i2 + "");
        putExtendProperty("rateInfo", moj.e(rateInfo));
    }

    @Deprecated
    public void saveExtend(boolean z, int i, RateInfo rateInfo) {
        putExtendProperty("single", z + "");
        putExtendProperty("times", i + "");
        putExtendProperty("rateInfo", moj.e(rateInfo));
    }

    public int acquireTimes() {
        return gic.c(getExtendProperty("times"));
    }

    public int acquireBestTimes() {
        return gic.c(getExtendProperty("oneMinBest"));
    }

    public boolean isSingle() {
        return gic.d((Object) getExtendProperty("single"));
    }

    public String acquireHeartRateConfig() {
        return getExtendProperty("heartRateConfig");
    }

    public int acquireAvgHeartRate() {
        return gic.c(getExtendProperty("avgHeartRate"));
    }

    public boolean isFitnessRecordFromDevice() {
        return gic.d((Object) getExtendProperty("isFitnessRecordFromDevice"));
    }

    public boolean isFitnessRecordFromTv() {
        return acquireWearType() == 3;
    }

    public boolean isAiWorkout() {
        return acquireExtendInt(Extend.FIT_EXTEND_AI_MODE, 0) == 1;
    }

    public void setAiWorkout(int i) {
        putExtendProperty(Extend.FIT_EXTEND_AI_MODE, String.valueOf(i));
    }

    public boolean isShowScores() {
        return isAiWorkout() && isFitnessRecordFromTv();
    }

    public boolean isSpecialAiWorkout() {
        if (!isAiWorkout()) {
            return false;
        }
        int acquireExtendInt = acquireExtendInt(Extend.FIT_EXTEND_COURSE_TYPE, -1);
        return acquireExtendInt == 222 || acquireExtendInt == 333 || acquireExtendInt == 444 || acquireExtendInt == 555;
    }

    public boolean isBeitiOrYogaAiWorkout() {
        if (!isAiWorkout()) {
            return false;
        }
        int acquireExtendInt = acquireExtendInt(Extend.FIT_EXTEND_COURSE_TYPE, -1);
        return acquireExtendInt == 333 || acquireExtendInt == 444;
    }

    public int acquireCourseType() {
        return acquireExtendInt(Extend.FIT_EXTEND_COURSE_TYPE, -1);
    }

    public int acquireProductId() {
        return acquireExtendInt("productId", -1);
    }

    public int acquireDifficulty() {
        return gic.c(getExtendProperty("difficulty"));
    }

    public int acquireHeartRateZoneType() {
        return gic.c(getExtendProperty("heartRateZoneType"));
    }

    public int acquireHeartRatePostureType() {
        return gic.c(getExtendProperty("heartRatePostureType"));
    }

    public long startTime() {
        String extendProperty = getExtendProperty("startTime");
        long longValue = (extendProperty == null || extendProperty.length() < 13) ? 0L : gic.c((Object) extendProperty).longValue();
        return longValue != 0 ? longValue : acquireExerciseTime() - getDuration();
    }

    public void setStartTime(long j) {
        putExtendProperty("startTime", String.valueOf(j));
    }

    public int acquireCourseDuration() {
        return gic.c(getExtendProperty("courseDuration"));
    }

    public RateInfo acquireRateInfo() {
        String extendProperty = getExtendProperty("rateInfo");
        if (TextUtils.isEmpty(extendProperty)) {
            return null;
        }
        return (RateInfo) moj.e(extendProperty, RateInfo.class);
    }

    private void checkAndConvertExtendToMap() {
        if (this.mHasConvertToMap) {
            return;
        }
        convertExtendToMap();
    }

    private void convertMapToString() {
        this.mRootExtendMap.put("fit_single", moj.e(this.mFitExtendMap));
        this.mRootExtendMap.put(Extend.RunWorkout.RUN_WORKOUT, moj.e(this.mRunExtendMap));
        this.mExtend = moj.e(this.mRootExtendMap);
    }

    private void convertExtendToMap() {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        this.mHasConvertToMap = true;
        if (StringUtils.g(this.mExtend)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.mExtend);
            String optString = jSONObject.optString("fit_single");
            String optString2 = jSONObject.optString(Extend.RunWorkout.RUN_WORKOUT);
            if (StringUtils.g(optString) && (optJSONObject2 = jSONObject.optJSONObject("fit_single")) != null) {
                optString = optJSONObject2.toString();
            }
            if (StringUtils.g(optString2) && (optJSONObject = jSONObject.optJSONObject(Extend.RunWorkout.RUN_WORKOUT)) != null) {
                optString2 = optJSONObject.toString();
            }
            this.mFitExtendMap.putAll(moj.a(optString));
            this.mRunExtendMap.putAll(moj.a(optString2));
            Map<? extends String, ? extends Object> map = (Map) moj.b(this.mExtend, new TypeToken<Map<String, String>>() { // from class: com.huawei.health.sport.model.WorkoutRecord.4
            }.getType());
            if (map != null && !map.isEmpty()) {
                this.mRootExtendMap.putAll(map);
                return;
            }
            LogUtil.b(TAG, "convertExtendToMap tempMap null");
            this.mRootExtendMap.put("fit_single", optString);
            this.mRootExtendMap.put(Extend.RunWorkout.RUN_WORKOUT, optString2);
        } catch (JsonParseException | JSONException e) {
            LogUtil.b(TAG, "getExtendFitSingleMap() error", LogAnonymous.b(e));
        }
    }

    public String acquireExtendString(String str) {
        return StringUtils.c((Object) getExtendProperty(str));
    }

    private int acquireExtendInt(String str, int i) {
        return gic.a(getExtendProperty(str), i);
    }

    public void saveExtendRunWorkout(String str) {
        putExtendProperty(Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID, str);
    }

    public String acquireRunWorkoutTrajectoryId() {
        return getExtendProperty(Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID);
    }

    public boolean isRunWorkout() {
        checkAndConvertExtendToMap();
        return !this.mRunExtendMap.isEmpty();
    }

    public void putExtendProperty(String str, String str2) {
        if (StringUtils.i(str) && StringUtils.i(str2)) {
            checkAndConvertExtendToMap();
            if (Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID.equals(str)) {
                this.mRunExtendMap.put(str, str2);
            } else if (Extend.e.c(str)) {
                this.mFitExtendMap.put(str, str2);
            } else {
                this.mRootExtendMap.put(str, str2);
            }
        }
    }

    public String getExtendProperty(String str) {
        checkAndConvertExtendToMap();
        if (Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID.equals(str)) {
            return this.mRunExtendMap.get(str);
        }
        if (Extend.e.c(str)) {
            return this.mFitExtendMap.get(str);
        }
        return StringUtils.c(this.mRootExtendMap.get(str));
    }

    public int acquireWearType() {
        return this.mWearType;
    }

    public void saveWearType(int i) {
        this.mWearType = i;
    }

    public void getBiJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            LogUtil.h(TAG, "object == null");
            return;
        }
        if (!Utils.o()) {
            jSONObject.put("end_time", acquireExerciseTime());
            jSONObject.put("finish_rate", gge.c(acquireFinishRate()));
        }
        jSONObject.put("workout_name", acquireWorkoutName());
    }

    public int getCourseDefineType() {
        return gic.e((Object) getExtendProperty(Extend.COURSE_DEFINE_TYPE));
    }

    public void setCourseDefineType(int i) {
        putExtendProperty(Extend.COURSE_DEFINE_TYPE, String.valueOf(i));
    }

    public int getCourseSportType() {
        return gic.e((Object) getExtendProperty(Extend.COURSE_SPORT_TYPE));
    }

    public void setCourseSportType(int i) {
        putExtendProperty(Extend.COURSE_SPORT_TYPE, String.valueOf(i));
    }

    public int getTargetType() {
        return gic.a(getExtendProperty(Extend.COURSE_TARGET_TYPE), 255);
    }

    public void setTargetType(int i) {
        putExtendProperty(Extend.COURSE_TARGET_TYPE, String.valueOf(i));
    }

    public long getTargetValue() {
        return gic.c((Object) getExtendProperty(Extend.COURSE_TARGET_VALUE)).longValue();
    }

    public void setTargetValue(long j) {
        putExtendProperty(Extend.COURSE_TARGET_VALUE, String.valueOf(j));
    }

    public void setTrainFeel(int i) {
        putExtendProperty(Extend.TRAIN_FEEL, String.valueOf(i));
    }

    public int getTrainFeel() {
        return gic.e((Object) getExtendProperty(Extend.TRAIN_FEEL));
    }

    public void setCourseModifiedTime(long j) {
        putExtendProperty(Extend.COURSE_MODIFIED_TIME, String.valueOf(j));
    }

    public int getCourseModifiedTime() {
        return gic.e((Object) getExtendProperty(Extend.COURSE_MODIFIED_TIME));
    }

    public int getSportRecordType() {
        return CommonUtil.e(getExtendProperty("sportRecordType"), 0);
    }

    public void setSportRecordType(int i) {
        putExtendProperty("sportRecordType", String.valueOf(i));
    }

    public int getPerfectTimes() {
        return gic.c(getExtendProperty("perfectTimes"));
    }

    public void setPerfectTimes(int i) {
        this.mFitExtendMap.put("perfectTimes", String.valueOf(i));
    }

    public int getExcellentTimes() {
        return gic.c(getExtendProperty("excellentTimes"));
    }

    public void setExcellentTimes(int i) {
        putExtendProperty("excellentTimes", String.valueOf(i));
    }

    public int getGoodTimes() {
        return gic.c(getExtendProperty("goodTimes"));
    }

    public void setGoodTimes(int i) {
        putExtendProperty("goodTimes", String.valueOf(i));
    }

    public int putCurRank() {
        return gic.c(getExtendProperty("curRank"));
    }

    public void setCurRank(int i) {
        putExtendProperty("curRank", String.valueOf(i));
    }

    public boolean getIsRewRecord() {
        return gic.d((Object) getExtendProperty("isNewRecord"));
    }

    public void setIsRewRecord(boolean z) {
        putExtendProperty("isNewRecord", String.valueOf(z));
    }

    public void setUserFinishRate(int i) {
        putExtendProperty("userFinishRate", String.valueOf(i));
    }

    public int getUserFinishRate() {
        return gic.c(getExtendProperty("userFinishRate"));
    }

    public void setTrainMeasureType(int i) {
        putExtendProperty("aiActionMeasureType", String.valueOf(i));
    }

    public int getTrainMeasureType() {
        return gic.c(getExtendProperty("aiActionMeasureType"));
    }

    public void setTrainMeasureValue(int i) {
        putExtendProperty("aiActionMeasureValue", String.valueOf(i));
    }

    public int getTrainMeasureValue() {
        return gic.c(getExtendProperty("aiActionMeasureValue"));
    }

    public void setActiveCalorie(float f) {
        if (f > 0.0f) {
            putExtendProperty(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, String.valueOf(f));
        }
    }

    public float getActiveCalorie() {
        return gic.e(getExtendProperty(HwExerciseConstants.JSON_NAME_ACTIVECALORIE));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("WorkoutRecord{mId=");
        stringBuffer.append(this.mId).append(", mPlanId=").append(this.mPlanId).append(", mWorkoutId=").append(this.mWorkoutId).append(", mWorkoutPackageId=").append(this.mWorkoutPackageId).append(", mCourseBelongType=").append(this.mCourseBelongType).append(", mWorkoutName=").append(this.mWorkoutName).append(", mWorkoutDate=").append(this.mWorkoutDate).append(", mWeekNum=").append(this.mWeekNum).append(", mExerciseTime=").append(this.mExerciseTime).append(", mActualCalorie=").append(this.mActualCalorie).append(", mActualDistance=").append(this.mActualDistance).append(", mDuration=").append(this.mDuration).append(", mCalorie=").append(this.mCalorie).append(", mDistance=").append(this.mDistance).append(", mFinishRate=").append(this.mFinishRate).append(", mWorkoutOrder=").append(this.mWorkoutOrder).append(", mUpperHeartRate=").append(this.mUpperHeartRate).append(", mLowerHeartRate=").append(this.mLowerHeartRate).append(", mBestPace=").append(this.mBestPace).append(", mOxygen=").append(this.mOxygen).append(", mTrainingLoadPeak=").append(this.mTrainingLoadPeak).append(", mTrajectoryId=").append(this.mTrajectoryId).append(", mRecordType=").append(this.mRecordType).append(", mVersion=").append(this.mVersion).append(", mActionSummary=").append(this.mActionSummary).append(", mWearType=").append(this.mWearType).append(", mExtend=").append(this.mExtend).append(", mHeartRateDataList=").append(this.mHeartRateDataList).append(", mInvalidHeartRateList=").append(this.mInvalidHeartRateList).append(", mCategory=").append(this.mCategory).append(", mIntensityZone=").append(this.mIntensityZone).append(", mTrainPoint=").append(this.mTrainPoint).append(", mTotalScore=").append(this.mTotalScore).append(", mRecordModeType=").append(this.mRecordModeType).append('}');
        return stringBuffer.toString();
    }
}
