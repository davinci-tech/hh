package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class Situation implements Parcelable {
    public static final Parcelable.Creator<Situation> CREATOR = new Parcelable.Creator<Situation>() { // from class: com.huawei.hms.kit.awareness.status.weather.Situation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Situation[] newArray(int i) {
            return new Situation[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Situation createFromParcel(Parcel parcel) {
            return new Situation(parcel);
        }
    };
    private int cnWeatherId;
    private String humidity;
    private long pressure;
    private long realFeelC;
    private long realFeelF;
    private long temperatureC;
    private long temperatureF;
    private long updateTime;
    private int uvIndex;
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
        parcel.writeString(this.humidity);
        parcel.writeLong(this.temperatureC);
        parcel.writeLong(this.temperatureF);
        parcel.writeLong(this.realFeelC);
        parcel.writeLong(this.realFeelF);
        parcel.writeString(this.windDir);
        parcel.writeInt(this.windLevel);
        parcel.writeInt(this.windSpeed);
        parcel.writeLong(this.updateTime);
        parcel.writeLong(this.pressure);
        parcel.writeInt(this.uvIndex);
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

    public int getUvIndex() {
        return this.uvIndex;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public long getTemperatureF() {
        return this.temperatureF;
    }

    public long getTemperatureC() {
        return this.temperatureC;
    }

    public long getRealFeelF() {
        return this.realFeelF;
    }

    public long getRealFeelC() {
        return this.realFeelC;
    }

    public long getPressure() {
        return this.pressure;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public int getCnWeatherId() {
        return this.cnWeatherId;
    }

    private Situation(Parcel parcel) {
        this.weatherId = parcel.readInt();
        this.cnWeatherId = parcel.readInt();
        this.humidity = parcel.readString();
        this.temperatureC = parcel.readLong();
        this.temperatureF = parcel.readLong();
        this.realFeelC = parcel.readLong();
        this.realFeelF = parcel.readLong();
        this.windDir = parcel.readString();
        this.windLevel = parcel.readInt();
        this.windSpeed = parcel.readInt();
        this.updateTime = parcel.readLong();
        this.pressure = parcel.readLong();
        this.uvIndex = parcel.readInt();
    }
}
