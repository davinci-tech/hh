package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class GroupSleepAudio implements Parcelable {
    public static final Parcelable.Creator<GroupSleepAudio> CREATOR = new Parcelable.Creator<GroupSleepAudio>() { // from class: com.huawei.health.marketing.request.GroupSleepAudio.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroupSleepAudio createFromParcel(Parcel parcel) {
            return new GroupSleepAudio(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroupSleepAudio[] newArray(int i) {
            return new GroupSleepAudio[i];
        }
    };
    private String firstTitle;
    private List<SleepAudio> sleepAudioInfoList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected GroupSleepAudio(Parcel parcel) {
        this.firstTitle = parcel.readString();
        if (this.sleepAudioInfoList == null) {
            this.sleepAudioInfoList = new ArrayList();
        }
        parcel.readTypedList(this.sleepAudioInfoList, SleepAudio.CREATOR);
    }

    public String getFirstTitle() {
        return this.firstTitle;
    }

    public void setFirstTitle(String str) {
        this.firstTitle = str;
    }

    public List<SleepAudio> getSleepAudioInfoList() {
        return this.sleepAudioInfoList;
    }

    public void setSleepAudioInfoList(List<SleepAudio> list) {
        this.sleepAudioInfoList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstTitle);
        parcel.writeTypedList(this.sleepAudioInfoList);
    }

    public String toString() {
        return "GroupSleepAudio{firstTitle='" + this.firstTitle + "', sleepAudioInfoList=" + this.sleepAudioInfoList + '}';
    }
}
