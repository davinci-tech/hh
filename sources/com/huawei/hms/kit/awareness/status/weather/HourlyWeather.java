package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class HourlyWeather implements Parcelable {
    public static final Parcelable.Creator<HourlyWeather> CREATOR = new Parcelable.Creator<HourlyWeather>() { // from class: com.huawei.hms.kit.awareness.status.weather.HourlyWeather.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HourlyWeather[] newArray(int i) {
            return new HourlyWeather[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HourlyWeather createFromParcel(Parcel parcel) {
            return new HourlyWeather(parcel);
        }
    };
    private int cnWeatherId;
    private long dateTimeStamp;
    private boolean isDayNight;
    private long rainprobability;
    private long tempC;
    private long tempF;
    private int weatherId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.weatherId);
        parcel.writeInt(this.cnWeatherId);
        parcel.writeLong(this.dateTimeStamp);
        parcel.writeLong(this.tempC);
        parcel.writeLong(this.tempF);
        parcel.writeLong(this.rainprobability);
        parcel.writeByte(this.isDayNight ? (byte) 1 : (byte) 0);
    }

    public boolean isDayNight() {
        return this.isDayNight;
    }

    public int getWeatherId() {
        return this.weatherId;
    }

    public long getTempF() {
        return this.tempF;
    }

    public long getTempC() {
        return this.tempC;
    }

    public long getRainprobability() {
        return this.rainprobability;
    }

    public long getDateTimeStamp() {
        return this.dateTimeStamp;
    }

    public int getCnWeatherId() {
        return this.cnWeatherId;
    }

    private HourlyWeather(Parcel parcel) {
        this.weatherId = parcel.readInt();
        this.cnWeatherId = parcel.readInt();
        this.dateTimeStamp = parcel.readLong();
        this.tempC = parcel.readLong();
        this.tempF = parcel.readLong();
        this.rainprobability = parcel.readLong();
        this.isDayNight = parcel.readByte() != 0;
    }
}
