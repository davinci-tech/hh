package com.huawei.health.suggestion.customizecourse.manager.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.BaseRecord;
import com.huawei.pluginfitnessadvice.TargetConfig;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseConfig extends BaseRecord {
    public static final Parcelable.Creator<CourseConfig> CREATOR = new Parcelable.Creator<CourseConfig>() { // from class: com.huawei.health.suggestion.customizecourse.manager.model.CourseConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: aye_, reason: merged with bridge method [inline-methods] */
        public CourseConfig createFromParcel(Parcel parcel) {
            return new CourseConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public CourseConfig[] newArray(int i) {
            return new CourseConfig[i];
        }
    };

    @SerializedName(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE)
    private int mCourseType;

    @SerializedName("defaultCombinationActions")
    private List<Attribute> mDefaultCombinationActions;

    @SerializedName("defaultCourseActions")
    private List<Attribute> mDefaultCourseActions;

    @SerializedName("defaultIntensityConfigs")
    private List<TargetConfig> mDefaultIntensityConfigs;

    @SerializedName("defaultNewAction")
    private Attribute mDefaultNewAction;

    @SerializedName("defaultTargetConfigs")
    private List<TargetConfig> mDefaultTargetConfigs;

    @SerializedName("des")
    private String mDes;

    @SerializedName("maxActionNum")
    private int mMaxActionNum;

    @SerializedName("maxCombinationRepeats")
    private int mMaxCombinationRepeats;

    @SerializedName("maxCreateNum")
    private int mMaxCreateNum;

    @SerializedName("maxListActionNum")
    private int mMaxListActionNum;

    @SerializedName("midPictures")
    private List<String> mMidPictures;

    @SerializedName("topicPreviewPics")
    private List<String> mTopicPreviewPics;

    @SerializedName("version")
    private String mVersion;

    @Override // com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected CourseConfig(Parcel parcel) {
        super(parcel);
        this.mDes = parcel.readString();
        this.mMidPictures = parcel.createStringArrayList();
        this.mTopicPreviewPics = parcel.createStringArrayList();
        this.mCourseType = parcel.readInt();
        this.mDefaultCourseActions = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mDefaultNewAction = (Attribute) parcel.readParcelable(Attribute.class.getClassLoader());
        this.mDefaultCombinationActions = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mMaxActionNum = parcel.readInt();
        this.mMaxListActionNum = parcel.readInt();
        this.mMaxCombinationRepeats = parcel.readInt();
        this.mMaxCreateNum = parcel.readInt();
        this.mVersion = parcel.readString();
        this.mDefaultTargetConfigs = parcel.createTypedArrayList(TargetConfig.CREATOR);
        this.mDefaultIntensityConfigs = parcel.createTypedArrayList(TargetConfig.CREATOR);
    }

    @Override // com.huawei.pluginfitnessadvice.BaseRecord, com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mDes);
        parcel.writeStringList(this.mMidPictures);
        parcel.writeStringList(this.mTopicPreviewPics);
        parcel.writeInt(this.mCourseType);
        parcel.writeTypedList(this.mDefaultCourseActions);
        parcel.writeParcelable(this.mDefaultNewAction, i);
        parcel.writeTypedList(this.mDefaultCombinationActions);
        parcel.writeInt(this.mMaxActionNum);
        parcel.writeInt(this.mMaxListActionNum);
        parcel.writeInt(this.mMaxCombinationRepeats);
        parcel.writeInt(this.mMaxCreateNum);
        parcel.writeString(this.mVersion);
        parcel.writeTypedList(this.mDefaultTargetConfigs);
        parcel.writeTypedList(this.mDefaultIntensityConfigs);
    }

    public void readFromParcel(Parcel parcel) {
        this.mDes = parcel.readString();
        this.mMidPictures = parcel.createStringArrayList();
        this.mTopicPreviewPics = parcel.createStringArrayList();
        this.mCourseType = parcel.readInt();
        this.mDefaultCourseActions = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mDefaultNewAction = (Attribute) parcel.readParcelable(Attribute.class.getClassLoader());
        this.mDefaultCombinationActions = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mMaxActionNum = parcel.readInt();
        this.mMaxListActionNum = parcel.readInt();
        this.mMaxCombinationRepeats = parcel.readInt();
        this.mMaxCreateNum = parcel.readInt();
        this.mVersion = parcel.readString();
        this.mDefaultTargetConfigs = parcel.createTypedArrayList(TargetConfig.CREATOR);
        this.mDefaultIntensityConfigs = parcel.createTypedArrayList(TargetConfig.CREATOR);
    }

    public String getDes() {
        return this.mDes;
    }

    public void setDes(String str) {
        this.mDes = str;
    }

    public List<String> getMidPictures() {
        List<String> list = this.mMidPictures;
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(list);
    }

    public void setMidPictures(List<String> list) {
        this.mMidPictures = list;
    }

    public List<String> getTopicPreviewPics() {
        if (this.mMidPictures == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(this.mTopicPreviewPics);
    }

    public void setTopicPreviewPics(List<String> list) {
        this.mTopicPreviewPics = list;
    }

    public int getCourseType() {
        return this.mCourseType;
    }

    public void setCourseType(int i) {
        this.mCourseType = i;
    }

    public List<Attribute> getDefaultCourseActions() {
        return this.mDefaultCourseActions;
    }

    public void setDefaultCourseActions(List<Attribute> list) {
        this.mDefaultCourseActions = list;
    }

    public Attribute getDefaultNewAction() {
        return this.mDefaultNewAction;
    }

    public void setDefaultNewAction(Attribute attribute) {
        this.mDefaultNewAction = attribute;
    }

    public List<Attribute> getDefaultCombinationActions() {
        return this.mDefaultCombinationActions;
    }

    public void setDefaultCombinationActions(List<Attribute> list) {
        this.mDefaultCombinationActions = list;
    }

    public int getMaxActionNum() {
        return this.mMaxActionNum;
    }

    public void setMaxActionNum(int i) {
        this.mMaxActionNum = i;
    }

    public int getMaxListActionNum() {
        return this.mMaxListActionNum;
    }

    public void setMaxListActionNum(int i) {
        this.mMaxListActionNum = i;
    }

    public int getMaxCombinationRepeats() {
        return this.mMaxCombinationRepeats;
    }

    public void setMaxCombinationRepeats(int i) {
        this.mMaxCombinationRepeats = i;
    }

    public int getMaxCreateNum() {
        return this.mMaxCreateNum;
    }

    public void setMaxCreateNum(int i) {
        this.mMaxCreateNum = i;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public List<TargetConfig> getDefaultTargetConfigs() {
        return this.mDefaultTargetConfigs;
    }

    public void setDefaultTargetConfigs(List<TargetConfig> list) {
        this.mDefaultTargetConfigs = list;
    }

    public List<TargetConfig> getDefaultIntensityConfigs() {
        return this.mDefaultIntensityConfigs;
    }

    public void setDefaultIntensityConfigs(List<TargetConfig> list) {
        this.mDefaultIntensityConfigs = list;
    }
}
