package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class WorkoutAction implements Parcelable {
    public static final Parcelable.Creator<WorkoutAction> CREATOR = new Parcelable.Creator<WorkoutAction>() { // from class: com.huawei.pluginfitnessadvice.WorkoutAction.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmi_, reason: merged with bridge method [inline-methods] */
        public WorkoutAction createFromParcel(Parcel parcel) {
            return new WorkoutAction(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public WorkoutAction[] newArray(int i) {
            return new WorkoutAction[i];
        }
    };
    public static final int INTENSITY_TYPE_ABSOLUTE = 2;
    public static final int INTENSITY_TYPE_ABSOLUTE_SPEED = 5;
    public static final int INTENSITY_TYPE_MAF_180 = 9;
    public static final int INTENSITY_TYPE_RELATIVE = 1;
    public static final int INTENSITY_TYPE_RELATIVE_INTERVAL = 4;
    public static final int INTENSITY_TYPE_RELATIVE_INTERVAL_SPEED = 6;
    public static final int INTENSITY_TYPE_RELATIVE_SPEED = 3;
    public static final int INTENSITY_TYPE_RESERVE_HEART_INTERVAL = 8;
    public static final int INTENSITY_TYPE_RESERVE_HEART_RATE = 7;
    public static final int INTENSITY_TYPE_SPEED = 0;
    public static final int INTENSITY_TYPE_TREADMILL_SLOPE = 11;
    public static final int INTENSITY_TYPE_TREADMILL_SPEED = 10;
    public static final int INTENSITY_TYPE_TREADMILL_SPEED_AND_SLOPE = 12;
    public static final int MEASUREMENTTYPE_DISTANCE = 0;
    public static final int MEASUREMENTTYPE_TIME = 1;
    private static final String TAG = "WorkoutAction";

    @SerializedName("absoluteHeartRateH")
    private String mAbsoluteHeartRateHighest;

    @SerializedName("absoluteHeartRateL")
    private String mAbsoluteHeartRateLowest;

    @SerializedName("action")
    private AtomicAction mAction;

    @SerializedName("actionId")
    private String mActionId;

    @SerializedName("actionMusic")
    private Audio mActionMusic;

    @SerializedName("actionStep")
    private String mActionStep;

    @SerializedName("breath")
    private String mBreath;

    @SerializedName("calorie")
    private float mCalorie;

    @SerializedName("commentaryGap")
    private List<Comment> mCommentaryGap;

    @SerializedName("commentaryTraining")
    private List<Comment> mCommentaryTraining;

    @SerializedName("commonError")
    private String mCommonError;

    @SerializedName("distance")
    private int mDistance;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("feeling")
    private String mFeeling;

    @SerializedName("gap")
    private int mGap;

    @SerializedName(MessageConstant.GROUP_MEDAL_TYPE)
    private int mGroup;

    @SerializedName("intensityType")
    private int mIntensityType;

    @SerializedName("introduceLyric")
    private String mIntroduceLyric;

    @SerializedName("mMAF180HeartRateBase")
    private int mMaf180HeartRateBase;

    @SerializedName("mMAF180HeartRateRange")
    private int mMaf180HeartRateRange;

    @SerializedName("measurementType")
    private int mMeasurementType;

    @SerializedName("measurementValue")
    private int mMeasurementValue;

    @SerializedName("pictures")
    private List<Pictures> mPictures;

    @SerializedName("relativeHeartRatePercentH")
    private int mRelativeHeartRatePercentHighest;

    @SerializedName("relativeHeartRatePercentL")
    private int mRelativeHeartRatePercentLowest;

    @SerializedName("relativeHeartRateRangeH")
    private int mRelativeHeartRateRangeHighest;

    @SerializedName("relativeHeartRateRangeL")
    private int mRelativeHeartRateRangeLowest;

    @SerializedName("mReserveHeartRatePercentH")
    private int mReserveHeartRatePercentHighest;

    @SerializedName("mReserveHeartRatePercentL")
    private int mReserveHeartRatePercentLowest;

    @SerializedName("mReserveHeartRateRangeH")
    private int mReserveHeartRateRangeHighest;

    @SerializedName("mReserveHeartRateRangeL")
    private int mReserveHeartRateRangeLowest;

    @SerializedName("mSpecifiedSlope")
    private double mSpecifiedSlope;

    @SerializedName("mSpecifiedSpeed")
    private double mSpecifiedSpeed;

    @SerializedName("speedH")
    private float mSpeedHighest;

    @SerializedName("speedL")
    private float mSpeedLowest;

    @SerializedName("tabloidPicUrl")
    private String mTabloidPicUrl;

    @SerializedName("value")
    private int mValue;

    @SerializedName("videos")
    private List<Video> mVideos;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WorkoutAction() {
    }

    protected WorkoutAction(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "WorkoutAction in == null");
            return;
        }
        this.mActionId = parcel.readString();
        this.mValue = parcel.readInt();
        this.mDistance = parcel.readInt();
        this.mDuration = parcel.readInt();
        this.mGap = parcel.readInt();
        this.mCalorie = parcel.readFloat();
        this.mGroup = parcel.readInt();
        this.mCommentaryTraining = parcel.createTypedArrayList(Comment.CREATOR);
        this.mCommentaryGap = parcel.createTypedArrayList(Comment.CREATOR);
        this.mMeasurementValue = parcel.readInt();
        this.mMeasurementType = parcel.readInt();
        this.mTabloidPicUrl = parcel.readString();
        this.mIntensityType = parcel.readInt();
        this.mSpeedHighest = parcel.readFloat();
        this.mSpeedLowest = parcel.readFloat();
        this.mAbsoluteHeartRateLowest = parcel.readString();
        this.mAbsoluteHeartRateHighest = parcel.readString();
        this.mRelativeHeartRatePercentLowest = parcel.readInt();
        this.mRelativeHeartRatePercentHighest = parcel.readInt();
        this.mRelativeHeartRateRangeLowest = parcel.readInt();
        this.mRelativeHeartRateRangeHighest = parcel.readInt();
        this.mReserveHeartRatePercentHighest = parcel.readInt();
        this.mReserveHeartRatePercentLowest = parcel.readInt();
        this.mReserveHeartRateRangeHighest = parcel.readInt();
        this.mReserveHeartRateRangeLowest = parcel.readInt();
        this.mMaf180HeartRateRange = parcel.readInt();
        this.mMaf180HeartRateBase = parcel.readInt();
        this.mSpecifiedSpeed = parcel.readDouble();
        this.mSpecifiedSlope = parcel.readDouble();
        this.mVideos = parcel.createTypedArrayList(Video.CREATOR);
        this.mActionStep = parcel.readString();
        this.mIntroduceLyric = parcel.readString();
        this.mBreath = parcel.readString();
        this.mFeeling = parcel.readString();
        this.mCommonError = parcel.readString();
        this.mPictures = parcel.createTypedArrayList(Pictures.CREATOR);
        this.mActionMusic = (Audio) parcel.readParcelable(Audio.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mActionId);
        parcel.writeInt(this.mValue);
        parcel.writeInt(this.mDistance);
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mGap);
        parcel.writeFloat(this.mCalorie);
        parcel.writeInt(this.mGroup);
        parcel.writeTypedList(this.mCommentaryTraining);
        parcel.writeTypedList(this.mCommentaryGap);
        parcel.writeInt(this.mMeasurementValue);
        parcel.writeInt(this.mMeasurementType);
        parcel.writeString(this.mTabloidPicUrl);
        parcel.writeInt(this.mIntensityType);
        parcel.writeFloat(this.mSpeedHighest);
        parcel.writeFloat(this.mSpeedLowest);
        parcel.writeString(this.mAbsoluteHeartRateLowest);
        parcel.writeString(this.mAbsoluteHeartRateHighest);
        parcel.writeInt(this.mRelativeHeartRatePercentLowest);
        parcel.writeInt(this.mRelativeHeartRatePercentHighest);
        parcel.writeInt(this.mRelativeHeartRateRangeLowest);
        parcel.writeInt(this.mRelativeHeartRateRangeHighest);
        parcel.writeInt(this.mReserveHeartRatePercentHighest);
        parcel.writeInt(this.mReserveHeartRatePercentLowest);
        parcel.writeInt(this.mReserveHeartRateRangeHighest);
        parcel.writeInt(this.mReserveHeartRateRangeLowest);
        parcel.writeInt(this.mMaf180HeartRateRange);
        parcel.writeInt(this.mMaf180HeartRateBase);
        parcel.writeDouble(this.mSpecifiedSpeed);
        parcel.writeDouble(this.mSpecifiedSlope);
        parcel.writeTypedList(this.mVideos);
        parcel.writeString(this.mActionStep);
        parcel.writeString(this.mIntroduceLyric);
        parcel.writeString(this.mBreath);
        parcel.writeString(this.mFeeling);
        parcel.writeString(this.mCommonError);
        parcel.writeTypedList(this.mPictures);
        parcel.writeParcelable(this.mActionMusic, i);
    }

    public float acquireSpeedH() {
        return this.mSpeedHighest;
    }

    public void saveSpeedH(float f) {
        this.mSpeedHighest = f;
    }

    public float acquireSpeedL() {
        return this.mSpeedLowest;
    }

    public void saveSpeedL(float f) {
        this.mSpeedLowest = f;
    }

    public String acquireAbsoluteHeartRateL() {
        return this.mAbsoluteHeartRateLowest;
    }

    public void saveAbsoluteHeartRateL(String str) {
        this.mAbsoluteHeartRateLowest = str;
    }

    public String acquireAbsoluteHeartRateH() {
        return this.mAbsoluteHeartRateHighest;
    }

    public void saveAbsoluteHeartRateH(String str) {
        this.mAbsoluteHeartRateHighest = str;
    }

    public int acquireRelativeHeartRatePercentL() {
        return this.mRelativeHeartRatePercentLowest;
    }

    public void saveRelativeHeartRatePercentL(int i) {
        this.mRelativeHeartRatePercentLowest = i;
    }

    public int acquireRelativeHeartRatePercentH() {
        return this.mRelativeHeartRatePercentHighest;
    }

    public void saveRelativeHeartRatePercentH(int i) {
        this.mRelativeHeartRatePercentHighest = i;
    }

    public int acquireRelativeHeartRateRangeL() {
        return this.mRelativeHeartRateRangeLowest;
    }

    public void saveRelativeHeartRateRangeL(int i) {
        this.mRelativeHeartRateRangeLowest = i;
    }

    public int acquireRelativeHeartRateRangeH() {
        return this.mRelativeHeartRateRangeHighest;
    }

    public void saveRelativeHeartRateRangeH(int i) {
        this.mRelativeHeartRateRangeHighest = i;
    }

    public int acquireIntensityType() {
        return this.mIntensityType;
    }

    public void saveIntensityType(int i) {
        this.mIntensityType = i;
    }

    public int acquireMeasurementValue() {
        return this.mMeasurementValue;
    }

    public void saveMeasurementValue(int i) {
        this.mMeasurementValue = i;
    }

    public String acquireTabloidPicUrl() {
        return this.mTabloidPicUrl;
    }

    public void saveTabloidPicUrl(String str) {
        this.mTabloidPicUrl = str;
    }

    public int acquireMeasurementType() {
        return this.mMeasurementType;
    }

    public void saveMeasurementType(int i) {
        this.mMeasurementType = i;
    }

    public String getActionId() {
        return this.mActionId;
    }

    public void saveActionId(String str) {
        this.mActionId = str;
    }

    public int getValue() {
        return this.mValue;
    }

    public void saveValue(int i) {
        this.mValue = i;
    }

    public int getDistance() {
        return this.mDistance;
    }

    public void setDistance(int i) {
        this.mDistance = i;
    }

    public int getGroup() {
        return this.mGroup;
    }

    public void saveGroup(int i) {
        this.mGroup = i;
    }

    public float getCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void saveDuration(int i) {
        this.mDuration = i;
    }

    public int getGap() {
        return this.mGap;
    }

    public void saveGap(int i) {
        this.mGap = i;
    }

    public AtomicAction getAction() {
        return this.mAction;
    }

    public void saveAction(AtomicAction atomicAction) {
        this.mAction = atomicAction;
    }

    public List<Comment> getCommentaryTraining() {
        return this.mCommentaryTraining;
    }

    public void saveCommentaryTraining(List<Comment> list) {
        this.mCommentaryTraining = list;
    }

    public List<Comment> acquireCommentaryGap() {
        return this.mCommentaryGap;
    }

    public void saveCommentaryGap(List<Comment> list) {
        this.mCommentaryGap = list;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public int acquireReserveHeartRatePercentH() {
        return this.mReserveHeartRatePercentHighest;
    }

    public void saveReserveHeartRatePercentH(int i) {
        this.mReserveHeartRatePercentHighest = i;
    }

    public int acquireReserveHeartRatePercentL() {
        return this.mReserveHeartRatePercentLowest;
    }

    public void saveReserveHeartRatePercentL(int i) {
        this.mReserveHeartRatePercentLowest = i;
    }

    public int acquireReserveHeartRateRangeH() {
        return this.mReserveHeartRateRangeHighest;
    }

    public void saveReserveHeartRateRangeH(int i) {
        this.mReserveHeartRateRangeHighest = i;
    }

    public int acquireReserveHeartRateRangeL() {
        return this.mReserveHeartRateRangeLowest;
    }

    public void saveReserveHeartRateRangeL(int i) {
        this.mReserveHeartRateRangeLowest = i;
    }

    public int acquireMaf180HeartRateRange() {
        return this.mMaf180HeartRateRange;
    }

    public void saveMaf180HeartRateRange(int i) {
        this.mMaf180HeartRateRange = i;
    }

    public int acquireMaf180HeartRateBase() {
        return this.mMaf180HeartRateBase;
    }

    public void saveMaf180HeartRateBase(int i) {
        this.mMaf180HeartRateBase = i;
    }

    public double acquireSpecifiedSlope() {
        return this.mSpecifiedSlope;
    }

    public void saveSpecifiedSlope(double d) {
        this.mSpecifiedSlope = d;
    }

    public double acquireSpecifiedSpeed() {
        return this.mSpecifiedSpeed;
    }

    public void saveSpecifiedSpeed(double d) {
        this.mSpecifiedSpeed = d;
    }

    public List<Video> getVideos() {
        return this.mVideos;
    }

    public void saveVideos(List<Video> list) {
        this.mVideos = list;
    }

    public String getActionStep() {
        return this.mActionStep;
    }

    public void setActionStep(String str) {
        this.mActionStep = str;
    }

    public String getIntroduceLyric() {
        return this.mIntroduceLyric;
    }

    public void setIntroduceLyric(String str) {
        this.mIntroduceLyric = str;
    }

    public String getBreath() {
        return this.mBreath;
    }

    public void setBreath(String str) {
        this.mBreath = str;
    }

    public String getFeeling() {
        return this.mFeeling;
    }

    public void setFeeling(String str) {
        this.mFeeling = str;
    }

    public String getCommonError() {
        return this.mCommonError;
    }

    public void setCommonError(String str) {
        this.mCommonError = str;
    }

    public List<Pictures> getPictures() {
        return this.mPictures;
    }

    public void setPictures(List<Pictures> list) {
        this.mPictures = list;
    }

    public Audio getActionMusic() {
        return this.mActionMusic;
    }

    public void setActionMusic(Audio audio) {
        this.mActionMusic = audio;
    }
}
