package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class HwMediaPosition implements Parcelable {
    public static final Parcelable.Creator<HwMediaPosition> CREATOR = new Parcelable.Creator<HwMediaPosition>() { // from class: com.huawei.android.airsharing.api.HwMediaPosition.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HwMediaPosition[] newArray(int i) {
            return new HwMediaPosition[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dJ_, reason: merged with bridge method [inline-methods] */
        public HwMediaPosition createFromParcel(Parcel parcel) {
            HwMediaPosition hwMediaPosition = new HwMediaPosition();
            hwMediaPosition.setTrackDur(parcel.readString());
            hwMediaPosition.setTrackMetaData(parcel.readString());
            hwMediaPosition.setTrackURI(parcel.readString());
            hwMediaPosition.setRelTime(parcel.readString());
            return hwMediaPosition;
        }
    };
    private String relTime;
    private String trackDur;
    private String trackMetaData;
    private String trackURI;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getTrackDur() {
        return this.trackDur;
    }

    public void setTrackDur(String str) {
        this.trackDur = str;
    }

    public String getTrackMetaData() {
        return this.trackMetaData;
    }

    public void setTrackMetaData(String str) {
        this.trackMetaData = str;
    }

    public String getTrackURI() {
        return this.trackURI;
    }

    public void setTrackURI(String str) {
        this.trackURI = str;
    }

    public String getRelTime() {
        return this.relTime;
    }

    public void setRelTime(String str) {
        this.relTime = str;
    }

    public String toString() {
        return "PositionInfo [trackDur=" + this.trackDur + ", trackMetaData=" + this.trackMetaData + ", trackURI=" + this.trackURI + ", relTime=" + this.relTime + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.trackDur);
        parcel.writeString(this.trackMetaData);
        parcel.writeString(this.trackURI);
        parcel.writeString(this.relTime);
    }
}
