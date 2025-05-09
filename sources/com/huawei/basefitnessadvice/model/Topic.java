package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.List;

/* loaded from: classes3.dex */
public class Topic implements Parcelable {
    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() { // from class: com.huawei.basefitnessadvice.model.Topic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Topic createFromParcel(Parcel parcel) {
            return new Topic(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Topic[] newArray(int i) {
            return new Topic[i];
        }
    };

    @SerializedName("courseCategory")
    private int mCourseCategory;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("displayorder")
    private int mDisplayOrder;

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("nameId")
    private int mNameId;

    @SerializedName("serialNum")
    private String mSerialNum;

    @SerializedName("subTitle")
    private String mSubtitle;

    @SerializedName("topicBackImgUrl")
    private String mTopicBackImgUrl;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("workoutList")
    private List<FitWorkout> mWorkoutList;

    @SerializedName("workoutPreviewNum")
    private int mWorkoutPreviewNum;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Topic() {
    }

    protected Topic(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mName = parcel.readString();
        this.mNameId = parcel.readInt();
        this.mVersion = parcel.readString();
        this.mSerialNum = parcel.readString();
        this.mWorkoutPreviewNum = parcel.readInt();
        this.mDisplayOrder = parcel.readInt();
        this.mWorkoutList = parcel.createTypedArrayList(FitWorkout.CREATOR);
        this.mSubtitle = parcel.readString();
        this.mTopicBackImgUrl = parcel.readString();
        this.mDescription = parcel.readString();
        this.mCourseCategory = parcel.readInt();
    }

    public int acquireId() {
        return this.mId;
    }

    public void saveId(int i) {
        this.mId = i;
    }

    public String acquireName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public int acquireNameId() {
        return this.mNameId;
    }

    public void saveNameId(int i) {
        this.mNameId = i;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public String acquireSerialNum() {
        return this.mSerialNum;
    }

    public void saveSerialNum(String str) {
        this.mSerialNum = str;
    }

    public int acquireWorkoutPreviewNum() {
        return this.mWorkoutPreviewNum;
    }

    public void saveWorkoutPreviewNum(int i) {
        this.mWorkoutPreviewNum = i;
    }

    public int acquireDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void saveDisplayOrder(int i) {
        this.mDisplayOrder = i;
    }

    public List<FitWorkout> acquireWorkoutList() {
        return this.mWorkoutList;
    }

    public void saveWorkoutList(List<FitWorkout> list) {
        this.mWorkoutList = list;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public String getTopicBackImgUrl() {
        return this.mTopicBackImgUrl;
    }

    public void setTopicBackImgUrl(String str) {
        this.mTopicBackImgUrl = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public int getCourseCategory() {
        return this.mCourseCategory;
    }

    public void setCourseCategory(int i) {
        this.mCourseCategory = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mNameId);
        parcel.writeString(this.mVersion);
        parcel.writeString(this.mSerialNum);
        parcel.writeInt(this.mWorkoutPreviewNum);
        parcel.writeInt(this.mDisplayOrder);
        parcel.writeTypedList(this.mWorkoutList);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mTopicBackImgUrl);
        parcel.writeString(this.mDescription);
        parcel.writeInt(this.mCourseCategory);
    }
}
