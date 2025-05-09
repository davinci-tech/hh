package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes9.dex */
public class LiveInfo implements Parcelable {
    public static final Parcelable.Creator<LiveInfo> CREATOR = new Parcelable.Creator<LiveInfo>() { // from class: com.huawei.hms.kit.awareness.status.weather.LiveInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveInfo[] newArray(int i) {
            return new LiveInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveInfo createFromParcel(Parcel parcel) {
            return new LiveInfo(parcel);
        }
    };
    public static final String UNKNOWN = "unknown";
    private String code;
    private List<DailyLiveInfo> levelList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeTypedList(this.levelList);
    }

    public List<DailyLiveInfo> getLevelList() {
        return this.levelList;
    }

    public String getCode() {
        return this.code;
    }

    private LiveInfo(Parcel parcel) {
        this.code = parcel.readString();
        this.levelList = parcel.createTypedArrayList(DailyLiveInfo.CREATOR);
    }
}
