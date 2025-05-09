package com.amap.api.services.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class LocalDayWeatherForecast implements Parcelable {
    public static final Parcelable.Creator<LocalDayWeatherForecast> CREATOR = new Parcelable.Creator<LocalDayWeatherForecast>() { // from class: com.amap.api.services.weather.LocalDayWeatherForecast.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ LocalDayWeatherForecast[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ LocalDayWeatherForecast createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static LocalDayWeatherForecast a(Parcel parcel) {
            return new LocalDayWeatherForecast(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1599a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LocalDayWeatherForecast() {
    }

    public String getDate() {
        return this.f1599a;
    }

    public void setDate(String str) {
        this.f1599a = str;
    }

    public String getWeek() {
        return this.b;
    }

    public void setWeek(String str) {
        this.b = str;
    }

    public String getDayWeather() {
        return this.c;
    }

    public void setDayWeather(String str) {
        this.c = str;
    }

    public String getNightWeather() {
        return this.d;
    }

    public void setNightWeather(String str) {
        this.d = str;
    }

    public String getDayTemp() {
        return this.e;
    }

    public void setDayTemp(String str) {
        this.e = str;
    }

    public String getNightTemp() {
        return this.f;
    }

    public void setNightTemp(String str) {
        this.f = str;
    }

    public String getDayWindDirection() {
        return this.g;
    }

    public void setDayWindDirection(String str) {
        this.g = str;
    }

    public String getNightWindDirection() {
        return this.h;
    }

    public void setNightWindDirection(String str) {
        this.h = str;
    }

    public String getDayWindPower() {
        return this.i;
    }

    public void setDayWindPower(String str) {
        this.i = str;
    }

    public String getNightWindPower() {
        return this.j;
    }

    public void setNightWindPower(String str) {
        this.j = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1599a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
    }

    public LocalDayWeatherForecast(Parcel parcel) {
        this.f1599a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
    }
}
