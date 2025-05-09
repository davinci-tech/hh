package com.huawei.hms.kit.awareness.status.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class City implements Parcelable {
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() { // from class: com.huawei.hms.kit.awareness.status.weather.City.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public City[] newArray(int i) {
            return new City[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public City createFromParcel(Parcel parcel) {
            return new City(parcel);
        }
    };
    private String cityCode;
    private String name;
    private String provinceName;
    private String timeZone;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cityCode);
        parcel.writeString(this.provinceName);
        parcel.writeString(this.timeZone);
        parcel.writeString(this.name);
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public String getName() {
        return this.name;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    private City(Parcel parcel) {
        this.cityCode = parcel.readString();
        this.provinceName = parcel.readString();
        this.timeZone = parcel.readString();
        this.name = parcel.readString();
    }
}
