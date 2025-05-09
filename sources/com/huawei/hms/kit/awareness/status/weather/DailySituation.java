package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DailySituation implements Parcelable {
    public static final Parcelable.Creator<DailySituation> CREATOR = new Parcelable.Creator<DailySituation>() { // from class: com.huawei.hms.kit.awareness.status.weather.DailySituation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailySituation[] newArray(int i) {
            return new DailySituation[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailySituation createFromParcel(Parcel parcel) {
            return new DailySituation(parcel);
        }
    };
    private int cnWeatherId;
    private int weatherId;
    private String windDir;
    private int windLevel;
    private int windSpeed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.weatherId);
        parcel.writeInt(this.cnWeatherId);
        parcel.writeString(this.windDir);
        parcel.writeInt(this.windLevel);
        parcel.writeInt(this.windSpeed);
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public int getWindLevel() {
        return this.windLevel;
    }

    public String getWindDir() {
        return this.windDir;
    }

    public int getWeatherId() {
        return this.weatherId;
    }

    public int getCnWeatherId() {
        return this.cnWeatherId;
    }

    private DailySituation(Parcel parcel) {
        this.weatherId = parcel.readInt();
        this.cnWeatherId = parcel.readInt();
        this.windDir = parcel.readString();
        this.windLevel = parcel.readInt();
        this.windSpeed = parcel.readInt();
    }
}
