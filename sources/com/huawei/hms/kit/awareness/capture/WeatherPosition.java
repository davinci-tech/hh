package com.huawei.hms.kit.awareness.capture;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes4.dex */
public class WeatherPosition implements Parcelable {
    public static final Parcelable.Creator<WeatherPosition> CREATOR = new Parcelable.Creator<WeatherPosition>() { // from class: com.huawei.hms.kit.awareness.capture.WeatherPosition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeatherPosition[] newArray(int i) {
            return new WeatherPosition[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeatherPosition createFromParcel(Parcel parcel) {
            return new WeatherPosition(parcel);
        }
    };
    private String mCity;
    private String mCountry;
    private String mCounty;
    private String mDistrict;
    private String mLocale;
    private String mProvince;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mProvince);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mDistrict);
        parcel.writeString(this.mCounty);
        parcel.writeString(this.mLocale);
    }

    public void setProvince(String str) {
        this.mProvince = str;
    }

    public void setLocale(String str) {
        this.mLocale = str;
    }

    public void setDistrict(String str) {
        this.mDistrict = str;
    }

    public void setCounty(String str) {
        this.mCounty = str;
    }

    public void setCountry(String str) {
        this.mCountry = str;
    }

    public void setCity(String str) {
        this.mCity = str;
    }

    public int hashCode() {
        return Objects.hash(this.mCountry, this.mProvince, this.mCity, this.mDistrict, this.mCounty, this.mLocale);
    }

    public String getProvince() {
        return this.mProvince;
    }

    public String getLocale() {
        return this.mLocale;
    }

    public String getDistrict() {
        return this.mDistrict;
    }

    public String getCounty() {
        return this.mCounty;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String getCity() {
        return this.mCity;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WeatherPosition)) {
            return false;
        }
        WeatherPosition weatherPosition = (WeatherPosition) obj;
        return Objects.equals(this.mCountry, weatherPosition.mCountry) && Objects.equals(this.mProvince, weatherPosition.mProvince) && Objects.equals(this.mCity, weatherPosition.mCity) && Objects.equals(this.mDistrict, weatherPosition.mDistrict) && Objects.equals(this.mCounty, weatherPosition.mCounty) && Objects.equals(this.mLocale, weatherPosition.mLocale);
    }

    public static WeatherPosition build(Address address) {
        WeatherPosition weatherPosition = new WeatherPosition();
        weatherPosition.setCounty(address.getCountryName());
        weatherPosition.setProvince(address.getAdminArea());
        weatherPosition.setCity(address.getLocality());
        weatherPosition.setLocale(address.getLocale().toString());
        return weatherPosition;
    }

    private WeatherPosition(Parcel parcel) {
        this.mCountry = parcel.readString();
        this.mProvince = parcel.readString();
        this.mCity = parcel.readString();
        this.mDistrict = parcel.readString();
        this.mCounty = parcel.readString();
        this.mLocale = parcel.readString();
    }

    public WeatherPosition() {
    }
}
