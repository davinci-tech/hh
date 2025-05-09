package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class Workout implements Parcelable {
    public static final Parcelable.Creator<Workout> CREATOR = new Parcelable.Creator<Workout>() { // from class: com.huawei.pluginfitnessadvice.Workout.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmh_, reason: merged with bridge method [inline-methods] */
        public Workout createFromParcel(Parcel parcel) {
            return new Workout(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Workout[] newArray(int i) {
            return new Workout[i];
        }
    };
    private static final String TAG = "Workout";

    @SerializedName("calorie")
    private float mCalorie;

    @SerializedName("courseBelongType")
    private int mCourseBelongType;

    @SerializedName("courseCategory")
    private int mCourseCategory;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("id")
    private String mId;

    @SerializedName("modified")
    private long mModified;

    @SerializedName("name")
    private String mName;

    @SerializedName("newUseWorkoutTime")
    private long mNewUseWorkoutTime;

    @SerializedName("publishDate")
    private long mPublishDate;

    @SerializedName("type")
    private int mType;

    @SerializedName("version")
    private String mVersion;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Workout() {
    }

    public Workout(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Workout in == null");
            return;
        }
        this.mId = parcel.readString();
        this.mName = parcel.readString();
        this.mType = parcel.readInt();
        this.mDescription = parcel.readString();
        this.mDuration = parcel.readInt();
        this.mCalorie = parcel.readFloat();
        this.mVersion = parcel.readString();
        this.mPublishDate = parcel.readLong();
        this.mModified = parcel.readLong();
        this.mCourseCategory = parcel.readInt();
        this.mCourseBelongType = parcel.readInt();
        this.mNewUseWorkoutTime = parcel.readLong();
    }

    public String acquireId() {
        return this.mId;
    }

    public void saveId(String str) {
        this.mId = str;
    }

    public String acquireName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String acquireDescription() {
        return this.mDescription;
    }

    public void saveDescription(String str) {
        this.mDescription = str;
    }

    public int acquireDuration() {
        return this.mDuration;
    }

    public void saveDuration(int i) {
        this.mDuration = i;
    }

    public float acquireCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public String accquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public long getPublishDate() {
        return this.mPublishDate;
    }

    public void savePublishDate(long j) {
        this.mPublishDate = j;
    }

    public long getModified() {
        return this.mModified;
    }

    public void saveModified(long j) {
        this.mModified = j;
    }

    public int getCategory() {
        return this.mCourseCategory;
    }

    public void setCategory(int i) {
        this.mCourseCategory = i;
    }

    public int getCourseBelongType() {
        return this.mCourseBelongType;
    }

    public void setCourseBelongType(int i) {
        this.mCourseBelongType = i;
    }

    public long getNewUseWorkoutTime() {
        return this.mNewUseWorkoutTime;
    }

    public void setNewUseWorkoutTime(long j) {
        this.mNewUseWorkoutTime = j;
    }

    public String toString() {
        return new GsonBuilder().serializeSpecialFloatingPointValues().create().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mId);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mDescription);
        parcel.writeInt(this.mDuration);
        parcel.writeFloat(this.mCalorie);
        parcel.writeString(this.mVersion);
        parcel.writeLong(this.mPublishDate);
        parcel.writeLong(this.mModified);
        parcel.writeInt(this.mCourseCategory);
        parcel.writeInt(this.mCourseBelongType);
        parcel.writeLong(this.mNewUseWorkoutTime);
    }
}
