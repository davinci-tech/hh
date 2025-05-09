package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepAudioGroup implements Parcelable {
    public static final Parcelable.Creator<SleepAudioGroup> CREATOR = new Parcelable.Creator<SleepAudioGroup>() { // from class: com.huawei.pluginfitnessadvice.audio.SleepAudioGroup.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmm_, reason: merged with bridge method [inline-methods] */
        public SleepAudioGroup createFromParcel(Parcel parcel) {
            return new SleepAudioGroup(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SleepAudioGroup[] newArray(int i) {
            return new SleepAudioGroup[i];
        }
    };

    @SerializedName("firstTitle")
    private String mFirstTitle;

    @SerializedName("sleepAudioInfoList")
    private List<SleepAudioInfo> mSleepAudioInfoList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SleepAudioGroup(Parcel parcel) {
        this.mFirstTitle = parcel.readString();
        this.mSleepAudioInfoList = parcel.createTypedArrayList(SleepAudioInfo.CREATOR);
    }

    public SleepAudioGroup() {
    }

    public String getFirstTitle() {
        return this.mFirstTitle;
    }

    public void setFirstTitle(String str) {
        this.mFirstTitle = str;
    }

    public List<SleepAudioInfo> getSleepAudioInfoList() {
        return this.mSleepAudioInfoList;
    }

    public void setSleepAudioInfoList(List<SleepAudioInfo> list) {
        this.mSleepAudioInfoList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mFirstTitle);
        parcel.writeTypedList(this.mSleepAudioInfoList);
    }

    public String toString() {
        return "SleepAudioGroup{mFirstTitle='" + this.mFirstTitle + "', mSleepAudioInfoList=" + this.mSleepAudioInfoList + '}';
    }
}
