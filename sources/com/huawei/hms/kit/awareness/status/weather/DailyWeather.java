package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DailyWeather implements Parcelable {
    public static final Parcelable.Creator<DailyWeather> CREATOR = new Parcelable.Creator<DailyWeather>() { // from class: com.huawei.hms.kit.awareness.status.weather.DailyWeather.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailyWeather[] newArray(int i) {
            return new DailyWeather[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailyWeather createFromParcel(Parcel parcel) {
            return new DailyWeather(parcel);
        }
    };
    private int aqiValue;
    private long dateTimeStamp;
    private long maxTempC;
    private long maxTempF;
    private long minTempC;
    private long minTempF;
    private long moonRise;
    private long moonSet;
    private String moonphase;
    private DailySituation situationDay;
    private DailySituation situationNight;
    private long sunRise;
    private long sunSet;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.moonphase);
        parcel.writeLong(this.moonRise);
        parcel.writeLong(this.moonSet);
        parcel.writeLong(this.sunRise);
        parcel.writeLong(this.sunSet);
        parcel.writeLong(this.maxTempC);
        parcel.writeLong(this.minTempC);
        parcel.writeLong(this.maxTempF);
        parcel.writeLong(this.minTempF);
        parcel.writeInt(this.aqiValue);
        parcel.writeLong(this.dateTimeStamp);
        parcel.writeParcelable(this.situationDay, i);
        parcel.writeParcelable(this.situationNight, i);
    }

    public long getSunSet() {
        return this.sunSet;
    }

    public long getSunRise() {
        return this.sunRise;
    }

    public DailySituation getSituationNight() {
        return this.situationNight;
    }

    public DailySituation getSituationDay() {
        return this.situationDay;
    }

    public String getMoonphase() {
        return this.moonphase;
    }

    public long getMoonSet() {
        return this.moonSet;
    }

    public long getMoonRise() {
        return this.moonRise;
    }

    public long getMinTempF() {
        return this.minTempF;
    }

    public long getMinTempC() {
        return this.minTempC;
    }

    public long getMaxTempF() {
        return this.maxTempF;
    }

    public long getMaxTempC() {
        return this.maxTempC;
    }

    public long getDateTimeStamp() {
        return this.dateTimeStamp;
    }

    public int getAqiValue() {
        return this.aqiValue;
    }

    private DailyWeather(Parcel parcel) {
        this.moonphase = parcel.readString();
        this.moonRise = parcel.readLong();
        this.moonSet = parcel.readLong();
        this.sunRise = parcel.readLong();
        this.sunSet = parcel.readLong();
        this.maxTempC = parcel.readLong();
        this.minTempC = parcel.readLong();
        this.maxTempF = parcel.readLong();
        this.minTempF = parcel.readLong();
        this.aqiValue = parcel.readInt();
        this.dateTimeStamp = parcel.readLong();
        this.situationDay = (DailySituation) parcel.readParcelable(DailySituation.class.getClassLoader());
        this.situationNight = (DailySituation) parcel.readParcelable(DailySituation.class.getClassLoader());
    }
}
