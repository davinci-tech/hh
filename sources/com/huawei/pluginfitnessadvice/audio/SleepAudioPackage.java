package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepAudioPackage implements Parcelable {
    public static final Parcelable.Creator<SleepAudioPackage> CREATOR = new Parcelable.Creator<SleepAudioPackage>() { // from class: com.huawei.pluginfitnessadvice.audio.SleepAudioPackage.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmo_, reason: merged with bridge method [inline-methods] */
        public SleepAudioPackage createFromParcel(Parcel parcel) {
            return new SleepAudioPackage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SleepAudioPackage[] newArray(int i) {
            return new SleepAudioPackage[i];
        }
    };

    @SerializedName("sleepAudioGroupList")
    private List<SleepAudioGroup> mSleepAudioGroupList;

    @SerializedName("sleepAudioInfoList")
    private List<SleepAudioInfo> mSleepAudioInfoList;

    @SerializedName("sleepAudioSeries")
    private SleepAudioSeries mSleepAudioSeries;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SleepAudioPackage() {
    }

    protected SleepAudioPackage(Parcel parcel) {
        this.mSleepAudioSeries = (SleepAudioSeries) parcel.readParcelable(SleepAudioSeries.class.getClassLoader());
        this.mSleepAudioInfoList = parcel.createTypedArrayList(SleepAudioInfo.CREATOR);
        this.mSleepAudioGroupList = parcel.createTypedArrayList(SleepAudioGroup.CREATOR);
    }

    public SleepAudioSeries getSleepAudioSeries() {
        return this.mSleepAudioSeries;
    }

    public void setSleepAudioSeries(SleepAudioSeries sleepAudioSeries) {
        this.mSleepAudioSeries = sleepAudioSeries;
    }

    public List<SleepAudioInfo> getSleepAudioInfoList() {
        return this.mSleepAudioInfoList;
    }

    public void setSleepAudioInfoList(List<SleepAudioInfo> list) {
        this.mSleepAudioInfoList = list;
    }

    public List<SleepAudioGroup> getSleepAudioGroupList() {
        return this.mSleepAudioGroupList;
    }

    public void setSleepAudioGroupList(List<SleepAudioGroup> list) {
        this.mSleepAudioGroupList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mSleepAudioSeries, i);
        parcel.writeTypedList(this.mSleepAudioInfoList);
        parcel.writeTypedList(this.mSleepAudioGroupList);
    }

    public String toString() {
        return "SleepAudioPackage{mSleepAudioSeries=" + this.mSleepAudioSeries + ", mSleepAudioInfoList=" + this.mSleepAudioInfoList + ", mSleepAudioGroupList=" + this.mSleepAudioGroupList + '}';
    }
}
