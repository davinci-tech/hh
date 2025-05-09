package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class Aqi implements Parcelable {
    public static final Parcelable.Creator<Aqi> CREATOR = new Parcelable.Creator<Aqi>() { // from class: com.huawei.hms.kit.awareness.status.weather.Aqi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Aqi[] newArray(int i) {
            return new Aqi[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Aqi createFromParcel(Parcel parcel) {
            return new Aqi(parcel);
        }
    };
    private int aqiValue;
    private long co;
    private long no2;
    private long o3;
    private long pm10;
    private long pm25;
    private long so2;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.co);
        parcel.writeLong(this.no2);
        parcel.writeLong(this.o3);
        parcel.writeLong(this.pm10);
        parcel.writeLong(this.pm25);
        parcel.writeLong(this.so2);
        parcel.writeInt(this.aqiValue);
    }

    public long getSo2() {
        return this.so2;
    }

    public long getPm25() {
        return this.pm25;
    }

    public long getPm10() {
        return this.pm10;
    }

    public long getO3() {
        return this.o3;
    }

    public long getNo2() {
        return this.no2;
    }

    public long getCo() {
        return this.co;
    }

    public int getAqiValue() {
        return this.aqiValue;
    }

    private Aqi(Parcel parcel) {
        this.co = parcel.readLong();
        this.no2 = parcel.readLong();
        this.o3 = parcel.readLong();
        this.pm10 = parcel.readLong();
        this.pm25 = parcel.readLong();
        this.so2 = parcel.readLong();
        this.aqiValue = parcel.readInt();
    }
}
