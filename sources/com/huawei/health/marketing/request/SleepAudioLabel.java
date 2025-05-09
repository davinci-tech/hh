package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class SleepAudioLabel implements Parcelable {
    public static final Parcelable.Creator<SleepAudioLabel> CREATOR = new Parcelable.Creator<SleepAudioLabel>() { // from class: com.huawei.health.marketing.request.SleepAudioLabel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepAudioLabel createFromParcel(Parcel parcel) {
            return new SleepAudioLabel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepAudioLabel[] newArray(int i) {
            return new SleepAudioLabel[i];
        }
    };
    private int audioId;
    private String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SleepAudioLabel(Parcel parcel) {
        this.audioId = parcel.readInt();
        this.name = parcel.readString();
    }

    public int getAudioId() {
        return this.audioId;
    }

    public void setAudioId(int i) {
        this.audioId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.audioId);
        parcel.writeString(this.name);
    }
}
