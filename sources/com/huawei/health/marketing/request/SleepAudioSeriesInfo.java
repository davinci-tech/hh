package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class SleepAudioSeriesInfo implements Parcelable {
    public static final Parcelable.Creator<SleepAudioSeriesInfo> CREATOR = new Parcelable.Creator<SleepAudioSeriesInfo>() { // from class: com.huawei.health.marketing.request.SleepAudioSeriesInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepAudioSeriesInfo createFromParcel(Parcel parcel) {
            return new SleepAudioSeriesInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepAudioSeriesInfo[] newArray(int i) {
            return new SleepAudioSeriesInfo[i];
        }
    };
    private List<GroupSleepAudio> sleepAudioGroupList;
    private List<SleepAudio> sleepAudioInfoList;
    private SleepAudio sleepAudioSeries;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SleepAudioSeriesInfo(Parcel parcel) {
        this.sleepAudioSeries = (SleepAudio) parcel.readParcelable(SleepAudio.class.getClassLoader());
        if (this.sleepAudioInfoList == null) {
            this.sleepAudioInfoList = new ArrayList();
        }
        parcel.readTypedList(this.sleepAudioInfoList, SleepAudio.CREATOR);
        if (this.sleepAudioGroupList == null) {
            this.sleepAudioGroupList = new ArrayList();
        }
        parcel.readTypedList(this.sleepAudioGroupList, GroupSleepAudio.CREATOR);
    }

    public SleepAudio getSleepAudioSeries() {
        return this.sleepAudioSeries;
    }

    public void setSleepAudioSeries(SleepAudio sleepAudio) {
        this.sleepAudioSeries = sleepAudio;
    }

    public List<SleepAudio> getSleepAudioInfoList() {
        return this.sleepAudioInfoList;
    }

    public void setSleepAudioInfoList(List<SleepAudio> list) {
        this.sleepAudioInfoList = list;
    }

    public List<GroupSleepAudio> getSleepAudioGroupList() {
        return this.sleepAudioGroupList;
    }

    public void setSleepAudioGroupList(List<GroupSleepAudio> list) {
        this.sleepAudioGroupList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.sleepAudioSeries, i);
        parcel.writeTypedList(this.sleepAudioInfoList);
        parcel.writeTypedList(this.sleepAudioGroupList);
    }

    public String toString() {
        return "SleepAudioSeriesInfo{sleepAudioSeries=" + this.sleepAudioSeries + ", sleepAudioInfoList=" + this.sleepAudioInfoList + ", sleepAudioGroupList=" + this.sleepAudioGroupList + '}';
    }
}
