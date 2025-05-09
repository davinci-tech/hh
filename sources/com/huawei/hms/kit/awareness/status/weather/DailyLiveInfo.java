package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DailyLiveInfo implements Parcelable {
    public static final Parcelable.Creator<DailyLiveInfo> CREATOR = new Parcelable.Creator<DailyLiveInfo>() { // from class: com.huawei.hms.kit.awareness.status.weather.DailyLiveInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailyLiveInfo[] newArray(int i) {
            return new DailyLiveInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailyLiveInfo createFromParcel(Parcel parcel) {
            return new DailyLiveInfo(parcel);
        }
    };
    private long dateTimeStamp;
    private String level;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.level);
        parcel.writeLong(this.dateTimeStamp);
    }

    public String getLevel() {
        return this.level;
    }

    public long getDateTimeStamp() {
        return this.dateTimeStamp;
    }

    private DailyLiveInfo(Parcel parcel) {
        this.level = parcel.readString();
        this.dateTimeStamp = parcel.readLong();
    }
}
