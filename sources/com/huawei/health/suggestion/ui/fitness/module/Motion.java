package com.huawei.health.suggestion.ui.fitness.module;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.RecordAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.pluginfitnessadvice.Cover;
import com.huawei.pluginfitnessadvice.Goal;
import com.huawei.pluginfitnessadvice.Pictures;
import com.huawei.pluginfitnessadvice.VideoSegment;
import defpackage.kxe;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class Motion implements Parcelable {
    public static final Parcelable.Creator<Motion> CREATOR = new Parcelable.Creator<Motion>() { // from class: com.huawei.health.suggestion.ui.fitness.module.Motion.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: aDD_, reason: merged with bridge method [inline-methods] */
        public Motion createFromParcel(Parcel parcel) {
            return new Motion(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Motion[] newArray(int i) {
            return new Motion[i];
        }
    };
    private static final String TAG = "Suggestion_Motion";
    private String mActionStep;
    private int mActionTrainTime;
    private String mBreath;
    private float mCalorie;
    private List<Comment> mCommentaryGap;
    private List<Comment> mCommentaryTraining;
    private String mCommonError;
    private Cover mCovers;
    private String mDescription;
    private int mDifficulty;
    private float mDuration;
    private List<Attribute> mEquipments;
    private String mFeeling;
    private int mGap;
    private List<Goal> mGoals;
    private int mGroups;
    private int mGyro;
    private String mId;
    private int mInterval;
    private String mIntroduceLyric;
    private int mLength;
    private int mMeasurementType;
    private int mMeasurementValue;
    private long mModified;
    private String mMotionPath;
    private String mMotionType;
    private String mName;
    private String mNamePath;
    private String mOriginLogo;
    private String mPicUrl;
    private List<Pictures> mPictures;
    private String mPlanId;
    private int mProgress;
    private long mPublishDate;
    private int mRepeat;
    private double mSpecifiedSlope;
    private double mSpecifiedSpeed;
    private String mTrainAudioPath;
    private String mTrainPointPath;
    private List<Attribute> mTrainingPoints;
    private String mVersion;
    private List<VideoSegment> mVideoSegments;
    private String mWorkoutId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Motion() {
    }

    public Motion(String str, String str2, String str3, String str4, int i, int i2, int i3, String str5) {
        this.mMotionType = str5;
        this.mNamePath = str3;
        this.mId = str;
        this.mMotionPath = str4;
        this.mInterval = i3;
        this.mRepeat = i2;
        this.mName = str2;
        this.mGroups = i;
    }

    public Motion(String str, String str2, int i, int i2) {
        this.mId = str;
        this.mRepeat = i2;
        this.mName = str2;
        this.mGroups = i;
    }

    public Motion(String str, String str2, int i, int i2, int i3, String str3) {
        this.mMotionType = str3;
        this.mId = str;
        this.mInterval = i3;
        this.mRepeat = i2;
        this.mName = str2;
        this.mGroups = i;
    }

    public Motion(String str, String str2, String str3, String str4, int i, int i2, String str5, int i3) {
        this.mNamePath = str3;
        this.mId = str;
        this.mMotionType = str5;
        this.mMotionPath = str4;
        this.mInterval = i3;
        this.mName = str2;
        this.mGroups = i;
        this.mRepeat = i2;
        this.mDuration = i3 * i2;
    }

    protected Motion(Parcel parcel) {
        this.mWorkoutId = parcel.readString();
        this.mTrainAudioPath = parcel.readString();
        this.mTrainPointPath = parcel.readString();
        this.mPlanId = parcel.readString();
        this.mGap = parcel.readInt();
        this.mProgress = parcel.readInt();
        this.mNamePath = parcel.readString();
        this.mMotionPath = parcel.readString();
        this.mInterval = parcel.readInt();
        this.mName = parcel.readString();
        this.mId = parcel.readString();
        this.mGroups = parcel.readInt();
        this.mRepeat = parcel.readInt();
        this.mDuration = parcel.readFloat();
        this.mPicUrl = parcel.readString();
        this.mMotionType = parcel.readString();
        this.mCommentaryGap = parcel.createTypedArrayList(Comment.CREATOR);
        this.mDifficulty = parcel.readInt();
        this.mCovers = (Cover) parcel.readParcelable(Cover.class.getClassLoader());
        this.mDescription = parcel.readString();
        this.mGoals = parcel.createTypedArrayList(Goal.CREATOR);
        this.mEquipments = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mTrainingPoints = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mGyro = parcel.readInt();
        this.mCalorie = parcel.readFloat();
        this.mVersion = parcel.readString();
        this.mPublishDate = parcel.readLong();
        this.mModified = parcel.readLong();
        this.mCommentaryTraining = parcel.createTypedArrayList(Comment.CREATOR);
        this.mOriginLogo = parcel.readString();
        this.mLength = parcel.readInt();
        this.mSpecifiedSpeed = parcel.readDouble();
        this.mSpecifiedSlope = parcel.readDouble();
        this.mVideoSegments = parcel.createTypedArrayList(VideoSegment.CREATOR);
        this.mActionTrainTime = parcel.readInt();
        this.mActionStep = parcel.readString();
        this.mIntroduceLyric = parcel.readString();
        this.mBreath = parcel.readString();
        this.mFeeling = parcel.readString();
        this.mCommonError = parcel.readString();
        this.mPictures = parcel.createTypedArrayList(Pictures.CREATOR);
    }

    public int acquireMeasurementValue() {
        return this.mMeasurementValue;
    }

    public void saveMeasurementValue(int i) {
        this.mMeasurementValue = i;
    }

    public int acquireMeasurementType() {
        return this.mMeasurementType;
    }

    public void saveMeasurementType(int i) {
        this.mMeasurementType = i;
    }

    public String acquireNamePath() {
        return this.mNamePath;
    }

    public void saveNamePath(String str) {
        this.mNamePath = str;
    }

    public String acquireMotionPath() {
        return this.mMotionPath;
    }

    public List<VideoSegment> getVideoSegments() {
        return this.mVideoSegments;
    }

    public void setVideoSegments(List<VideoSegment> list) {
        if (list == null) {
            return;
        }
        this.mVideoSegments = new ArrayList(list);
    }

    public void saveMotionPath(String str) {
        this.mMotionPath = str;
    }

    public int acquireInterval() {
        return this.mInterval;
    }

    public void saveInterval(int i) {
        this.mInterval = i;
    }

    public String acquireName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public String acquireId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public int acquireGroups() {
        return this.mGroups;
    }

    public void saveGroups(int i) {
        this.mGroups = i;
    }

    public int acquireRepeat() {
        return this.mRepeat;
    }

    public void saveRepeat(int i) {
        this.mRepeat = i;
    }

    public float acquireDuration() {
        return this.mDuration;
    }

    public float getTotalBeats() {
        return this.mGroups * this.mRepeat;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public String acquireMotionType() {
        return this.mMotionType;
    }

    public void saveMotionType(String str) {
        this.mMotionType = str;
    }

    public int acquireDifficulty() {
        return this.mDifficulty;
    }

    public void saveDifficulty(int i) {
        this.mDifficulty = i;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public List<Goal> getGoals() {
        return this.mGoals;
    }

    public void setGoals(List<Goal> list) {
        this.mGoals = list;
    }

    public List<Attribute> getEquipments() {
        return this.mEquipments;
    }

    public void setEquipments(List<Attribute> list) {
        this.mEquipments = list;
    }

    public List<Attribute> getTrainingPoints() {
        return this.mTrainingPoints;
    }

    public void setTrainingPoints(List<Attribute> list) {
        this.mTrainingPoints = list;
    }

    public int getGyro() {
        return this.mGyro;
    }

    public void setGyro(int i) {
        this.mGyro = i;
    }

    public float acquireCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public long getPublishDate() {
        return this.mPublishDate;
    }

    public void setPublishDate(long j) {
        this.mPublishDate = j;
    }

    public long getModified() {
        return this.mModified;
    }

    public void setModified(long j) {
        this.mModified = j;
    }

    public List<Comment> acquireCommentaryGap() {
        return this.mCommentaryGap;
    }

    public void setCommentaryGap(List<Comment> list) {
        this.mCommentaryGap = list;
    }

    public int acquireGap() {
        return this.mGap;
    }

    public void setGap(int i) {
        this.mGap = i;
    }

    public String acquirePicUrl() {
        return this.mPicUrl;
    }

    public void savePicUrl(String str) {
        this.mPicUrl = str;
    }

    public Cover acquireCovers() {
        return this.mCovers;
    }

    public void saveCovers(Cover cover) {
        this.mCovers = cover;
    }

    public List<Comment> acquireCommentaryTraining() {
        return this.mCommentaryTraining;
    }

    public void setCommentaryTraining(List<Comment> list) {
        this.mCommentaryTraining = list;
    }

    public int acquireProgress() {
        return this.mProgress;
    }

    public void saveProgress(int i) {
        this.mProgress = i;
    }

    public String acquireWorkoutId() {
        return this.mWorkoutId;
    }

    public void saveWorkoutId(String str) {
        this.mWorkoutId = str;
    }

    public String acquirePlanId() {
        return this.mPlanId;
    }

    public void savePlanId(String str) {
        this.mPlanId = str;
    }

    public String getTrainAudioPath() {
        return this.mTrainAudioPath;
    }

    public void setTrainAudioPath(String str) {
        this.mTrainAudioPath = str;
    }

    public String acquireTrainPointPath() {
        return this.mTrainPointPath;
    }

    public void saveTrainPointPath(String str) {
        this.mTrainPointPath = str;
    }

    public String getOriginLogo() {
        return this.mOriginLogo;
    }

    public void setOriginLogo(String str) {
        this.mOriginLogo = str;
    }

    public int acquireLength() {
        return this.mLength;
    }

    public void saveLength(int i) {
        this.mLength = i;
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

    public void saveActionTrainTime(int i) {
        this.mActionTrainTime = i;
    }

    public int acquireActionTrainTime() {
        return this.mActionTrainTime;
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.b(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mWorkoutId);
        parcel.writeString(this.mTrainAudioPath);
        parcel.writeString(this.mTrainPointPath);
        parcel.writeString(this.mPlanId);
        parcel.writeInt(this.mGap);
        parcel.writeInt(this.mProgress);
        parcel.writeString(this.mNamePath);
        parcel.writeString(this.mMotionPath);
        parcel.writeInt(this.mInterval);
        parcel.writeString(this.mName);
        parcel.writeString(this.mId);
        parcel.writeInt(this.mGroups);
        parcel.writeInt(this.mRepeat);
        parcel.writeFloat(this.mDuration);
        parcel.writeString(this.mPicUrl);
        parcel.writeString(this.mMotionType);
        parcel.writeTypedList(this.mCommentaryGap);
        parcel.writeInt(this.mDifficulty);
        parcel.writeParcelable(this.mCovers, i);
        parcel.writeString(this.mDescription);
        parcel.writeTypedList(this.mGoals);
        parcel.writeTypedList(this.mEquipments);
        parcel.writeTypedList(this.mTrainingPoints);
        parcel.writeInt(this.mGyro);
        parcel.writeFloat(this.mCalorie);
        parcel.writeString(this.mVersion);
        parcel.writeLong(this.mPublishDate);
        parcel.writeLong(this.mModified);
        parcel.writeTypedList(this.mCommentaryTraining);
        parcel.writeString(this.mOriginLogo);
        parcel.writeInt(this.mLength);
        parcel.writeDouble(this.mSpecifiedSpeed);
        parcel.writeDouble(this.mSpecifiedSlope);
        parcel.writeTypedList(this.mVideoSegments);
        parcel.writeInt(this.mActionTrainTime);
        parcel.writeString(this.mActionStep);
        parcel.writeString(this.mIntroduceLyric);
        parcel.writeString(this.mBreath);
        parcel.writeString(this.mFeeling);
        parcel.writeString(this.mCommonError);
        parcel.writeTypedList(this.mPictures);
    }

    public RecordAction getRecordAction() {
        if ("timer".equals(acquireMotionType())) {
            return new RecordAction(acquireId(), acquireName(), acquireProgress(), (acquireDuration() * acquireGroups()) / 1000.0f, kxe.e("timer"));
        }
        return new RecordAction(acquireId(), acquireName(), acquireProgress(), getTotalBeats(), kxe.e("beating"));
    }

    public RecordAction getLongRecordAction() {
        if ("timer".equals(acquireMotionType())) {
            return new RecordAction(acquireId(), acquireName(), acquireActionTrainTime(), getVideoSegments().get(0).getDuration(), kxe.e("timer"));
        }
        return new RecordAction(acquireId(), acquireName(), acquireActionTrainTime(), getVideoSegments().get(0).getDuration(), kxe.e("beating"));
    }

    public RecordAction getOneActionLongRecord(int i) {
        if ("timer".equals(acquireMotionType())) {
            return new RecordAction(acquireId(), acquireName(), i, getVideoSegments().get(0).getDuration(), kxe.e("timer"));
        }
        return new RecordAction(acquireId(), acquireName(), i, getVideoSegments().get(0).getDuration(), kxe.e("beating"));
    }
}
