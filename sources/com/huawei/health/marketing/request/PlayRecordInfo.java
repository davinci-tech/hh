package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class PlayRecordInfo implements Parcelable {
    public static final Parcelable.Creator<PlayRecordInfo> CREATOR = new Parcelable.Creator<PlayRecordInfo>() { // from class: com.huawei.health.marketing.request.PlayRecordInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayRecordInfo createFromParcel(Parcel parcel) {
            return new PlayRecordInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayRecordInfo[] newArray(int i) {
            return new PlayRecordInfo[i];
        }
    };
    private int audioId;
    private long duration;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected PlayRecordInfo(Parcel parcel) {
        this.audioId = parcel.readInt();
        this.duration = parcel.readLong();
    }

    public int getAudioId() {
        return this.audioId;
    }

    public void setAudioId(int i) {
        this.audioId = i;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.audioId);
        parcel.writeLong(this.duration);
    }
}
