package com.huawei.ui.main.stories.fitness.activity.coresleep.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepCourseBean implements Parcelable {
    public static final Parcelable.Creator<SleepCourseBean> CREATOR = new Parcelable.Creator<SleepCourseBean>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepCourseBean.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: drO_, reason: merged with bridge method [inline-methods] */
        public SleepCourseBean createFromParcel(Parcel parcel) {
            return new SleepCourseBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SleepCourseBean[] newArray(int i) {
            return new SleepCourseBean[i];
        }
    };

    @SerializedName("gridContents")
    private List<SleepSeriesCourseBean> gridContents;

    @SerializedName("name")
    private String name;

    @SerializedName("nameVisibility")
    private boolean nameVisibility;

    @SerializedName("sleepAudioType")
    private int sleepAudioType;

    @SerializedName("subTitle")
    private String subTitle;

    @SerializedName("tabs")
    private List<SingleTab> tabs;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SleepCourseBean(Parcel parcel) {
        this.name = parcel.readString();
        this.nameVisibility = parcel.readByte() != 0;
        this.sleepAudioType = parcel.readInt();
        this.subTitle = parcel.readString();
        this.gridContents = parcel.createTypedArrayList(SleepSeriesCourseBean.CREATOR);
        this.tabs = parcel.createTypedArrayList(SingleTab.CREATOR);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isNameVisibility() {
        return this.nameVisibility;
    }

    public void setNameVisibility(boolean z) {
        this.nameVisibility = z;
    }

    public int getSleepAudioType() {
        return this.sleepAudioType;
    }

    public void setSleepAudioType(int i) {
        this.sleepAudioType = i;
    }

    public List<SleepSeriesCourseBean> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SleepSeriesCourseBean> list) {
        this.gridContents = list;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public List<SingleTab> getTabs() {
        return this.tabs;
    }

    public void setTabs(List<SingleTab> list) {
        this.tabs = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeByte(this.nameVisibility ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.sleepAudioType);
        parcel.writeString(this.subTitle);
        parcel.writeTypedList(this.gridContents);
        parcel.writeTypedList(this.tabs);
    }

    public String toString() {
        return "SleepCourseBean{name='" + this.name + "', subTitle='" + this.subTitle + "', nameVisibility=" + this.nameVisibility + ", sleepAudioType=" + this.sleepAudioType + ", gridContents=" + this.gridContents + ", tabs=" + this.tabs + '}';
    }
}
