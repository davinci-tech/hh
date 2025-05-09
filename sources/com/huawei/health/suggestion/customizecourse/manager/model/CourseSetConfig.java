package com.huawei.health.suggestion.customizecourse.manager.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseSetConfig implements Parcelable {
    public static final Parcelable.Creator<CourseSetConfig> CREATOR = new Parcelable.Creator<CourseSetConfig>() { // from class: com.huawei.health.suggestion.customizecourse.manager.model.CourseSetConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: ayf_, reason: merged with bridge method [inline-methods] */
        public CourseSetConfig createFromParcel(Parcel parcel) {
            return new CourseSetConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public CourseSetConfig[] newArray(int i) {
            return new CourseSetConfig[i];
        }
    };

    @SerializedName("configId")
    private String mConfigId;

    @SerializedName("courseConfigs")
    private List<CourseConfig> mCourseConfigs;

    @SerializedName("version")
    private String mVersion;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected CourseSetConfig(Parcel parcel) {
        this.mVersion = parcel.readString();
        this.mConfigId = parcel.readString();
        this.mCourseConfigs = parcel.createTypedArrayList(CourseConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mVersion);
        parcel.writeString(this.mConfigId);
        parcel.writeTypedList(this.mCourseConfigs);
    }

    public void readFromParcel(Parcel parcel) {
        this.mVersion = parcel.readString();
        this.mConfigId = parcel.readString();
        this.mCourseConfigs = parcel.createTypedArrayList(CourseConfig.CREATOR);
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public String getConfigId() {
        return this.mConfigId;
    }

    public void setConfigId(String str) {
        this.mConfigId = str;
    }

    public List<CourseConfig> getCourseConfigs() {
        return this.mCourseConfigs;
    }

    public void setCourseConfigs(List<CourseConfig> list) {
        this.mCourseConfigs = list;
    }
}
