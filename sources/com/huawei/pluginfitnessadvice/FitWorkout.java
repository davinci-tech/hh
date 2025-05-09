package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import defpackage.koq;
import defpackage.moj;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FitWorkout extends Workout implements Serializable {
    public static final int CATEGORY_DEFAULT = 0;
    public static final int CATEGORY_REAL_SCENE = 284;
    public static final int CATEGORY_YOGA = 137;
    public static final Parcelable.Creator<FitWorkout> CREATOR = new Parcelable.Creator<FitWorkout>() { // from class: com.huawei.pluginfitnessadvice.FitWorkout.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clS_, reason: merged with bridge method [inline-methods] */
        public FitWorkout createFromParcel(Parcel parcel) {
            return new FitWorkout(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public FitWorkout[] newArray(int i) {
            return new FitWorkout[i];
        }
    };
    public static final int STAGE_NEW = 0;
    public static final int SUPPORT_DEVICE_YES = 0;
    private static final String TAG = "Fitness_FitWorkout";
    public static final int TYPE_FIT = 1;
    public static final int TYPE_MEASUREMENT_DURATION = 1;
    public static final int TYPE_RUN_AC = 2;
    public static final int TYPE_RUN_SE = 3;
    public static final int WORKOUT_TYPE_TREADMILL = 3;
    private static final long serialVersionUID = 7627297693591029156L;

    @SerializedName("transactionStatus")
    private int mAuthResult;

    @SerializedName("belongs")
    private List<BelongInfo> mBelongInfoList;

    @SerializedName("buyNotesTitle")
    private String mBuyNotesTitle;

    @SerializedName("buyNotesUrl")
    private String mBuyNotesUrl;

    @SerializedName("classes")
    private List<Attribute> mClasses;

    @SerializedName("commodityFlag")
    private int mCommodityFlag;

    @SerializedName("coolDownRunAction")
    private WorkoutAction mCoolDownRunAction;

    @SerializedName("cornerImgDisplay")
    private int mCornerImgDisplay;

    @SerializedName("courseActions")
    private List<ChoreographedMultiActions> mCourseActions;

    @SerializedName("courseAttr")
    private int mCourseAttr;

    @SerializedName(WorkoutRecord.Extend.COURSE_DEFINE_TYPE)
    private int mCourseDefineType;

    @SerializedName("courseLibraryFlag")
    private int mCourseLibraryFlag;

    @SerializedName("difficulty")
    private int mDifficulty;

    @SerializedName("distance")
    private double mDistance;

    @SerializedName("equipmentWorkoutAction")
    private EquipmentWorkoutAction mEquipmentWorkoutAction;

    @SerializedName("equipments")
    private List<Attribute> mEquipments;

    @SerializedName("exerciseTimes")
    private int mExerciseTimes;

    @SerializedName("extendProperty")
    private Map<String, String> mExtendProperty;

    @SerializedName("mExtendSeaMap")
    private String mExtendSeaMap;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int mGender;

    @SerializedName("intervals")
    private int mIntervals;

    @SerializedName("isAi")
    private int mIsAi;

    @SerializedName("isSupportDevice")
    private int mIsSupportDevice;

    @SerializedName("lan")
    private String mLan;

    @SerializedName("extendTemplateInfo")
    private LayoutTemplateInfo mLayoutTemplateInfo;

    @SerializedName("mListRelativeWorkouts")
    private List<String> mListRelativeWorkouts;

    @SerializedName("measurementType")
    private int mMeasurementType;

    @SerializedName("midPicture")
    private String mMidPicture;

    @SerializedName("musicRunFlag")
    private int mMusicRunFlag;

    @SerializedName("narrowDesc")
    private String mNarrowDesc;

    @SerializedName("narrowPicture")
    private String mNarrowPicture;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("previewVideoUrl")
    private String mPreviewVideoUrl;

    @SerializedName("cornerList")
    private List<PriceTagBean> mPriceTagBeanList;

    @SerializedName("primaryClassify")
    private List<Classify> mPrimaryClassify;

    @SerializedName("repeatTimes")
    private int mRepeatTimes;

    @SerializedName("runActionNum")
    private int mRunActionNum;

    @SerializedName("secondClassify")
    private List<Classify> mSecondClassify;

    @SerializedName("sequenceName")
    private String mSequenceName;

    @SerializedName("sequenceStage")
    private String mSequenceStage;

    @SerializedName("stage")
    private int mStage;

    @SerializedName("supplierLogoUrl")
    private String mSupplierLogoUrl;

    @SerializedName("topicPreviewPicUrl")
    private String mTopicPreviewPicUrl;

    @SerializedName("trainingpoints")
    private List<Attribute> mTrainingPoints;

    @SerializedName("users")
    private int mUsers;

    @SerializedName("warmUpRunAction")
    private WorkoutAction mWarmUpRunAction;

    @SerializedName("workoutActions")
    private List<WorkoutAction> mWorkoutActions;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)
    private int mWorkoutType;

    @SerializedName("portraitUrlList")
    private List<String> portraitUrlList;

    /* loaded from: classes6.dex */
    public static final class b {
        public static boolean b(int i) {
            return i == 2;
        }

        public static boolean d(int i) {
            return i == 3 || i == 2;
        }

        public static boolean e(int i) {
            return i == 3;
        }
    }

    /* loaded from: classes6.dex */
    public static final class e {
        public static boolean d(int i) {
            return i == 1 || i == 2 || i == 3 || i == 4 || i == 5;
        }
    }

    @Override // com.huawei.pluginfitnessadvice.Workout, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FitWorkout() {
        this.mCourseDefineType = 0;
        this.mExtendProperty = new HashMap();
        this.mIntervals = -4;
        this.mMusicRunFlag = 0;
        this.mCourseLibraryFlag = 0;
    }

    protected FitWorkout(Parcel parcel) {
        super(parcel);
        this.mCourseDefineType = 0;
        this.mExtendProperty = new HashMap();
        this.mIntervals = -4;
        this.mMusicRunFlag = 0;
        this.mCourseLibraryFlag = 0;
        if (parcel == null) {
            LogUtil.h(TAG, "FitWorkout in == null");
            return;
        }
        this.mEquipments = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mTrainingPoints = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mDifficulty = parcel.readInt();
        this.mPicture = parcel.readString();
        this.mMidPicture = parcel.readString();
        this.mGender = parcel.readInt();
        this.mIsSupportDevice = parcel.readInt();
        this.mWorkoutActions = parcel.createTypedArrayList(WorkoutAction.CREATOR);
        this.mEquipmentWorkoutAction = (EquipmentWorkoutAction) parcel.readParcelable(EquipmentWorkoutAction.class.getClassLoader());
        this.mDifficulty = parcel.readInt();
        this.mNarrowPicture = parcel.readString();
        this.mNarrowDesc = parcel.readString();
        this.mExerciseTimes = parcel.readInt();
        this.mStage = parcel.readInt();
        this.mMeasurementType = parcel.readInt();
        this.mDistance = parcel.readDouble();
        this.mRunActionNum = parcel.readInt();
        this.mSequenceName = parcel.readString();
        this.mSequenceStage = parcel.readString();
        this.mRepeatTimes = parcel.readInt();
        this.mListRelativeWorkouts = parcel.createStringArrayList();
        this.portraitUrlList = parcel.createStringArrayList();
        this.mExtendSeaMap = parcel.readString();
        readPartOfProperty(parcel);
    }

    private void readPartOfProperty(Parcel parcel) {
        this.mIntervals = parcel.readInt();
        this.mPrimaryClassify = parcel.createTypedArrayList(Classify.CREATOR);
        this.mTopicPreviewPicUrl = parcel.readString();
        this.mSupplierLogoUrl = parcel.readString();
        this.mWorkoutType = parcel.readInt();
        this.mMusicRunFlag = parcel.readInt();
        this.mCourseAttr = parcel.readInt();
        this.mCourseLibraryFlag = parcel.readInt();
        this.mCourseDefineType = parcel.readInt();
        this.mLan = parcel.readString();
        this.mExtendProperty = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mCornerImgDisplay = parcel.readInt();
        this.mCommodityFlag = parcel.readInt();
        this.mIsAi = parcel.readInt();
        this.mAuthResult = parcel.readInt();
        this.mPriceTagBeanList = parcel.createTypedArrayList(PriceTagBean.CREATOR);
        this.mBuyNotesTitle = parcel.readString();
        this.mBuyNotesUrl = parcel.readString();
        this.mLayoutTemplateInfo = (LayoutTemplateInfo) parcel.readParcelable(LayoutTemplateInfo.class.getClassLoader());
        this.mPreviewVideoUrl = parcel.readString();
        this.mBelongInfoList = parcel.createTypedArrayList(BelongInfo.CREATOR);
    }

    @Override // com.huawei.pluginfitnessadvice.Workout, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.mEquipments);
        parcel.writeTypedList(this.mTrainingPoints);
        parcel.writeInt(this.mDifficulty);
        parcel.writeString(this.mPicture);
        parcel.writeString(this.mMidPicture);
        parcel.writeInt(this.mGender);
        parcel.writeInt(this.mIsSupportDevice);
        parcel.writeTypedList(this.mWorkoutActions);
        parcel.writeParcelable(this.mEquipmentWorkoutAction, i);
        parcel.writeInt(this.mDifficulty);
        parcel.writeString(this.mNarrowPicture);
        parcel.writeString(this.mNarrowDesc);
        parcel.writeInt(this.mExerciseTimes);
        parcel.writeInt(this.mStage);
        parcel.writeInt(this.mMeasurementType);
        parcel.writeDouble(this.mDistance);
        parcel.writeInt(this.mRunActionNum);
        parcel.writeString(this.mSequenceName);
        parcel.writeString(this.mSequenceStage);
        parcel.writeInt(this.mRepeatTimes);
        parcel.writeStringList(this.mListRelativeWorkouts);
        parcel.writeStringList(this.portraitUrlList);
        parcel.writeString(this.mExtendSeaMap);
        writePartOfProperty(parcel, i);
    }

    private void writePartOfProperty(Parcel parcel, int i) {
        parcel.writeInt(this.mIntervals);
        parcel.writeTypedList(this.mPrimaryClassify);
        parcel.writeString(this.mTopicPreviewPicUrl);
        parcel.writeString(this.mSupplierLogoUrl);
        parcel.writeInt(this.mWorkoutType);
        parcel.writeInt(this.mMusicRunFlag);
        parcel.writeInt(this.mCourseAttr);
        parcel.writeInt(this.mCourseLibraryFlag);
        parcel.writeInt(this.mCourseDefineType);
        parcel.writeString(this.mLan);
        parcel.writeMap(this.mExtendProperty);
        parcel.writeInt(this.mCornerImgDisplay);
        parcel.writeInt(this.mCommodityFlag);
        parcel.writeInt(this.mIsAi);
        parcel.writeInt(this.mAuthResult);
        parcel.writeTypedList(this.mPriceTagBeanList);
        parcel.writeString(this.mBuyNotesTitle);
        parcel.writeString(this.mBuyNotesUrl);
        parcel.writeParcelable(this.mLayoutTemplateInfo, i);
        parcel.writeString(this.mPreviewVideoUrl);
        parcel.writeTypedList(this.mBelongInfoList);
    }

    public List<String> getPortraitUrlList() {
        return this.portraitUrlList;
    }

    public void setPortraitUrlList(List<String> list) {
        this.portraitUrlList = list;
    }

    public int acquireCommodityFlag() {
        return this.mCommodityFlag;
    }

    public void setCommodityFlag(int i) {
        this.mCommodityFlag = i;
    }

    public int getIsAi() {
        return this.mIsAi;
    }

    public void setIsAi(int i) {
        this.mIsAi = i;
    }

    public int acquireAuthResult() {
        return this.mAuthResult;
    }

    public void setAuthResult(int i) {
        this.mAuthResult = i;
    }

    public List<PriceTagBean> acquirePriceTagBeanList() {
        return this.mPriceTagBeanList;
    }

    public void setPriceTagBeanList(List<PriceTagBean> list) {
        this.mPriceTagBeanList = list;
    }

    public int getCornerImgDisplay() {
        return this.mCornerImgDisplay;
    }

    public void setCornerImgDisplay(int i) {
        this.mCornerImgDisplay = i;
    }

    public String getBuyNotesTitle() {
        return this.mBuyNotesTitle;
    }

    public void setBuyNotesTitle(String str) {
        this.mBuyNotesTitle = str;
    }

    public String getBuyNotesUrl() {
        return this.mBuyNotesUrl;
    }

    public void setBuyNotesUrl(String str) {
        this.mBuyNotesUrl = str;
    }

    public int acquireRunActionNum() {
        return this.mRunActionNum;
    }

    public void saveRunActionNum(int i) {
        this.mRunActionNum = i;
    }

    public WorkoutAction acquireWarmUpRunAction() {
        return this.mWarmUpRunAction;
    }

    public void saveWarmUpRunAction(WorkoutAction workoutAction) {
        this.mWarmUpRunAction = workoutAction;
    }

    public WorkoutAction acquireCoolDownRunAction() {
        return this.mCoolDownRunAction;
    }

    public void saveCoolDownRunAction(WorkoutAction workoutAction) {
        this.mCoolDownRunAction = workoutAction;
    }

    public String acquireSequenceName() {
        return this.mSequenceName;
    }

    public void saveSequenceName(String str) {
        this.mSequenceName = str;
    }

    public int acquireRepeatTimes() {
        return this.mRepeatTimes;
    }

    public void saveRepeatTimes(int i) {
        this.mRepeatTimes = i;
    }

    public String acquireSequenceStage() {
        return this.mSequenceStage;
    }

    public void saveSequenceStage(String str) {
        this.mSequenceStage = str;
    }

    public int acquireMeasurementType() {
        return this.mMeasurementType;
    }

    public void saveMeasurementType(int i) {
        this.mMeasurementType = i;
    }

    public double acquireDistance() {
        return this.mDistance;
    }

    public void saveDistance(double d) {
        this.mDistance = d;
    }

    public int acquireDifficulty() {
        return this.mDifficulty;
    }

    public void saveDifficulty(int i) {
        this.mDifficulty = i;
    }

    public String acquireComeFrom() {
        return acquireComeFrom(acquireId());
    }

    public static String acquireComeFrom(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 4) ? "" : str.substring(4);
    }

    public static String acquireComeFrom(String str, int i) {
        return i == 1 ? "R" : acquireComeFrom(str);
    }

    public List<Attribute> acquireEquipments() {
        return this.mEquipments;
    }

    public void saveEquipments(List<Attribute> list) {
        this.mEquipments = list;
    }

    public List<Attribute> acquireTrainingpoints() {
        return this.mTrainingPoints;
    }

    public void saveTrainingpoints(List<Attribute> list) {
        this.mTrainingPoints = list;
    }

    public String getNarrowPicture() {
        return this.mNarrowPicture;
    }

    public void saveNarrowPicture(String str) {
        this.mNarrowPicture = str;
    }

    public String acquireNarrowDesc() {
        return this.mNarrowDesc;
    }

    public void saveNarrowDesc(String str) {
        this.mNarrowDesc = str;
    }

    public List<Attribute> acquireClasses() {
        return this.mClasses;
    }

    public void saveClasses(List<Attribute> list) {
        this.mClasses = list;
    }

    public List<Classify> acquireClassifyInfo() {
        return this.mSecondClassify;
    }

    public void saveClassifyInfo(List<Classify> list) {
        this.mSecondClassify = list;
    }

    public String acquirePicture() {
        return this.mPicture;
    }

    public void savePicture(String str) {
        this.mPicture = str;
    }

    public int acquireIsSupportDevice() {
        return this.mIsSupportDevice;
    }

    public void saveIsSupportDevice(int i) {
        this.mIsSupportDevice = i;
    }

    public int acquireStage() {
        return this.mStage;
    }

    public void saveStage(int i) {
        this.mStage = i;
    }

    public int acquireUsers() {
        return this.mUsers;
    }

    public void saveUsers(int i) {
        this.mUsers = i;
    }

    public List<WorkoutAction> acquireWorkoutActions() {
        return this.mWorkoutActions;
    }

    public void saveWorkoutActions(List<WorkoutAction> list) {
        this.mWorkoutActions = list;
    }

    public List<ChoreographedMultiActions> getCourseActions() {
        return this.mCourseActions;
    }

    public void setCourseActions(List<ChoreographedMultiActions> list) {
        this.mCourseActions = list;
    }

    public EquipmentWorkoutAction getEquipmentWorkoutAction() {
        return this.mEquipmentWorkoutAction;
    }

    public void setEquipmentWorkoutAction(EquipmentWorkoutAction equipmentWorkoutAction) {
        this.mEquipmentWorkoutAction = equipmentWorkoutAction;
    }

    public String acquireMidPicture() {
        return this.mMidPicture;
    }

    public int hashCode() {
        return nullToStr(acquireId()).hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof FitWorkout) {
            return nullToStr(acquireId()).equals(nullToStr(((FitWorkout) obj).acquireId()));
        }
        return false;
    }

    public void saveMidPicture(String str) {
        this.mMidPicture = str;
    }

    public int acquireExerciseTimes() {
        return this.mExerciseTimes;
    }

    public void saveExerciseTimes(int i) {
        this.mExerciseTimes = i;
    }

    public static String nullToStr(Object obj) {
        return (obj == null || obj.toString().equals(Constants.NULL)) ? "" : obj.toString().trim();
    }

    public List<String> acquireListRelativeWorkouts() {
        return this.mListRelativeWorkouts;
    }

    public void saveListRelativeWorkouts(List<String> list) {
        this.mListRelativeWorkouts = list;
    }

    public int getIntervals() {
        return this.mIntervals;
    }

    public void setIntervals(int i) {
        this.mIntervals = i;
    }

    public String acquireExtendSeaMap() {
        return this.mExtendSeaMap;
    }

    public void saveExtendSeaMap(String str) {
        this.mExtendSeaMap = str;
    }

    public void setPrimaryClassify(List<Classify> list) {
        this.mPrimaryClassify = list;
    }

    public void setTopicPreviewPicUrl(String str) {
        this.mTopicPreviewPicUrl = str;
    }

    public void setVideoProperty(int i) {
        this.mExtendProperty.put("videoProperty", String.valueOf(i));
    }

    public void setWorkoutActionProperty(int i) {
        this.mExtendProperty.put("workoutActionProperty", String.valueOf(i));
    }

    public void setSupplierLogoUrl(String str) {
        this.mSupplierLogoUrl = str;
    }

    public void setShowCalories(int i) {
        this.mExtendProperty.put("showCalories", String.valueOf(i));
    }

    public void setShowCountdown(int i) {
        this.mExtendProperty.put("showCountdown", String.valueOf(i));
    }

    public void setShowHeartRate(int i) {
        this.mExtendProperty.put("showHeartRate", String.valueOf(i));
    }

    public void setTimbre(String str) {
        this.mExtendProperty.put("timbre", str);
    }

    public List<Classify> getPrimaryClassify() {
        return this.mPrimaryClassify;
    }

    public String getTopicPreviewPicUrl() {
        return this.mTopicPreviewPicUrl;
    }

    public int getVideoProperty() {
        return getExtendPropertyInt("videoProperty");
    }

    public int getWorkoutActionProperty() {
        return getExtendPropertyInt("workoutActionProperty");
    }

    public String getSupplierLogoUrl() {
        return this.mSupplierLogoUrl;
    }

    public int getShowCalories() {
        return getExtendPropertyInt("showCalories");
    }

    public int getShowCountdown() {
        return getExtendPropertyInt("showCountdown");
    }

    public int getShowHeartRate() {
        return getExtendPropertyInt("showHeartRate");
    }

    public String getTimbre() {
        return getExtendPropertyString("timbre");
    }

    public int getWorkoutType() {
        return this.mWorkoutType;
    }

    public void setWorkoutType(int i) {
        this.mWorkoutType = i;
    }

    public String getFitnessGoal() {
        return getExtendPropertyString("fitnessGoal");
    }

    public void setFitnessGoal(String str) {
        this.mExtendProperty.put("fitnessGoal", str);
    }

    public int getSmartScreeFlag() {
        return getExtendPropertyInt("smartScreenFlag");
    }

    public void setSmartScreeFlag(int i) {
        this.mExtendProperty.put("smartScreenFlag", String.valueOf(i));
    }

    public int getTemplateType() {
        return getExtendPropertyInt("extendTemplateType");
    }

    public void setTemplateType(int i) {
        this.mExtendProperty.put("extendTemplateType", String.valueOf(i));
    }

    public int getAntiScreenRecording() {
        return getExtendPropertyInt("antiScreenRecording");
    }

    public void setAntiScreenRecording(int i) {
        this.mExtendProperty.put("antiScreenRecording", String.valueOf(i));
    }

    public int getCourseLibraryFlag() {
        return this.mCourseLibraryFlag;
    }

    public void setCourseLibraryFlag(int i) {
        this.mCourseLibraryFlag = i;
    }

    public boolean isCourseLibraryShow() {
        return this.mCourseLibraryFlag == 1;
    }

    public int getCourseAttr() {
        return this.mCourseAttr;
    }

    public void setCourseAttr(int i) {
        this.mCourseAttr = i;
    }

    public String getPreviewVideoUrl() {
        return this.mPreviewVideoUrl;
    }

    public void setPreviewVideoUrl(String str) {
        this.mPreviewVideoUrl = str;
    }

    public List<BelongInfo> getBelongInfoList() {
        return this.mBelongInfoList;
    }

    public void setBelongInfoList(List<BelongInfo> list) {
        this.mBelongInfoList = list;
    }

    public void setResourceTypeListToExtend(List<ResourceType> list) {
        try {
            this.mExtendProperty.put("resourceType", moj.e(list));
        } catch (NullPointerException unused) {
            ReleaseLogUtil.c(TAG, "resourceTypeList=", list);
        }
    }

    public List<ResourceType> getResourceTypeListFromExtend() {
        return moj.b(getExtendPropertyString("resourceType"), ResourceType[].class);
    }

    public BelongInfo getCourseBelongInfoByType(int i) {
        if (koq.b(this.mBelongInfoList)) {
            return null;
        }
        for (BelongInfo belongInfo : this.mBelongInfoList) {
            if (belongInfo.getType() == i) {
                return belongInfo;
            }
        }
        return null;
    }

    public boolean isNewRunCourse() {
        return getType() == 4;
    }

    public boolean isEquipmentCourse() {
        return getType() == 5;
    }

    public int getMusicRunFlag() {
        return this.mMusicRunFlag;
    }

    public void setMusicRunFlag(int i) {
        this.mMusicRunFlag = i;
    }

    public boolean isMusicRun() {
        return this.mMusicRunFlag == 1;
    }

    public int getCourseDefineType() {
        return this.mCourseDefineType;
    }

    public void setCourseDefineType(int i) {
        this.mCourseDefineType = i;
    }

    public boolean isCustomCourse() {
        return this.mCourseDefineType == 1;
    }

    public String getLan() {
        return this.mLan;
    }

    public void setLan(String str) {
        this.mLan = str;
    }

    public List<ShowLayout> getShowLayouts() {
        return Collections.emptyList();
    }

    public LayoutTemplateInfo getLayoutTemplateInfo() {
        return this.mLayoutTemplateInfo;
    }

    public void setLayoutTemplateInfo(LayoutTemplateInfo layoutTemplateInfo) {
        this.mLayoutTemplateInfo = layoutTemplateInfo;
    }

    public Map<String, String> getExtendProperty() {
        return this.mExtendProperty;
    }

    public void setExtendProperty(Map<String, String> map) {
        if (map != null) {
            this.mExtendProperty.clear();
            this.mExtendProperty.putAll(map);
        }
    }

    public void putExtendProperty(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.mExtendProperty.put(str, str2);
    }

    public void putExtendProperty(String str, Object obj) {
        if (str == null || obj == null) {
            return;
        }
        this.mExtendProperty.put(str, moj.e(obj));
    }

    public <T> List<T> getExtendPropertyList(String str, Class<T[]> cls) {
        return moj.b(this.mExtendProperty.get(str), (Class) cls);
    }

    public <T> T getExtendProperty(String str, Class<T> cls) {
        return (T) moj.e(this.mExtendProperty.get(str), cls);
    }

    public int getExtendPropertyInt(String str) {
        return getExtendPropertyInt(str, 0);
    }

    public int getExtendPropertyInt(String str, int i) {
        return CommonUtil.e(this.mExtendProperty.get(str), i);
    }

    public long getExtendPropertyLong(String str) {
        return getExtendPropertyLong(str, 0L);
    }

    public long getExtendPropertyLong(String str, long j) {
        return CommonUtil.b(this.mExtendProperty.get(str), j);
    }

    public String getExtendPropertyString(String str) {
        return getExtendPropertyString(str, null);
    }

    public String getExtendPropertyString(String str, String str2) {
        String str3 = this.mExtendProperty.get(str);
        return str3 != null ? str3 : str2;
    }

    public float getExtendPropertyFloat(String str) {
        return getExtendPropertyFloat(str, 0.0f);
    }

    public float getExtendPropertyFloat(String str, float f) {
        return CommonUtil.c(this.mExtendProperty.get(str), f);
    }

    public double getExtendPropertyDouble(String str) {
        return getExtendPropertyDouble(str, 0.0d);
    }

    public double getExtendPropertyDouble(String str, double d) {
        return CommonUtil.b(this.mExtendProperty.get(str), d);
    }

    public boolean isRunModelCourse() {
        return getType() == 4 || getType() == 2 || getType() == 3 || isWalkCourse();
    }

    public boolean isWalkCourse() {
        return getCategory() == 257;
    }

    public boolean isLongVideoCourse() {
        return b.d(getVideoProperty());
    }

    public boolean isLongExplainVideoCourse() {
        return b.e(getVideoProperty());
    }

    public boolean isRealSceneCourse() {
        return getCategory() == 284 && koq.c(getRoadBookList());
    }

    public List<RoadBook> getRoadBookList() {
        return getExtendPropertyList("roadBooks", RoadBook[].class);
    }

    public void setRoadBookList(List<RoadBook> list) {
        if (koq.b(list)) {
            return;
        }
        putExtendProperty("roadBooks", moj.e(list));
    }

    public String getBackgroundMusic() {
        return getExtendPropertyString("backgroundMusic", "");
    }

    public void setBackgroundMusic(String str) {
        putExtendProperty("backgroundMusic", str);
    }

    public int getCalorieStartTime() {
        return getExtendPropertyInt("calorieStartTime", 0);
    }

    public void setCalorieStartTime(int i) {
        this.mExtendProperty.put("calorieStartTime", String.valueOf(i));
    }

    public int getUpperHeartRateAdjust() {
        return getExtendPropertyInt("upperHeartRateAdjust");
    }

    public void setUpperHeartRateAdjust(int i) {
        this.mExtendProperty.put("upperHeartRateAdjust", String.valueOf(i));
    }

    public int getLowerHeartRateAdjust() {
        return getExtendPropertyInt("lowerHeartRateAdjust");
    }

    public void setLowerHeartRateAdjust(int i) {
        this.mExtendProperty.put("lowerHeartRateAdjust", String.valueOf(i));
    }
}
