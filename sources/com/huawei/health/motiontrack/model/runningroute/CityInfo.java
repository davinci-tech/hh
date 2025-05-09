package com.huawei.health.motiontrack.model.runningroute;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes8.dex */
public class CityInfo implements Parcelable {
    public static final Parcelable.Creator<CityInfo> CREATOR = new Parcelable.Creator<CityInfo>() { // from class: com.huawei.health.motiontrack.model.runningroute.CityInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: aro_, reason: merged with bridge method [inline-methods] */
        public CityInfo createFromParcel(Parcel parcel) {
            return new CityInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CityInfo[] newArray(int i) {
            return new CityInfo[i];
        }
    };

    @SerializedName("cityCenter")
    private GpsPoint cityCenter;

    @SerializedName("cityCode")
    private String cityCode;

    @SerializedName("cityId")
    private String cityId;

    @SerializedName("cityName")
    private String cityName;

    @SerializedName("cityTimeZone")
    private String cityTimeZone;

    @SerializedName("countryId")
    private String countryId;

    @SerializedName("createTime")
    private long createTime;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long modifyTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CityInfo() {
    }

    public CityInfo(Parcel parcel) {
        this.countryId = parcel.readString();
        this.cityId = parcel.readString();
        this.cityCode = parcel.readString();
        this.cityName = parcel.readString();
        this.cityTimeZone = parcel.readString();
        this.createTime = parcel.readLong();
        this.modifyTime = parcel.readLong();
    }

    private CityInfo(e eVar) {
        this.countryId = eVar.j;
        this.cityId = eVar.d;
        this.cityCode = eVar.c;
        this.cityName = eVar.e;
        this.cityTimeZone = eVar.b;
        this.cityCenter = eVar.f2919a;
        this.createTime = eVar.h;
        this.modifyTime = eVar.g;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public CityInfo setCountryId(String str) {
        this.countryId = str;
        return this;
    }

    public String getCityId() {
        return this.cityId;
    }

    public CityInfo setCityId(String str) {
        this.cityId = str;
        return this;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public CityInfo setCityCode(String str) {
        this.cityCode = str;
        return this;
    }

    public String getCityName() {
        return this.cityName;
    }

    public CityInfo setCityName(String str) {
        this.cityName = str;
        return this;
    }

    public String getCityTimeZone() {
        return this.cityTimeZone;
    }

    public CityInfo setCityTimeZone(String str) {
        this.cityTimeZone = str;
        return this;
    }

    public GpsPoint getCityCenter() {
        return this.cityCenter;
    }

    public CityInfo setCityCenter(GpsPoint gpsPoint) {
        this.cityCenter = gpsPoint;
        return this;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public CityInfo setCreateTime(long j) {
        this.createTime = j;
        return this;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public CityInfo setModifyTime(long j) {
        this.modifyTime = j;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.countryId);
        parcel.writeString(this.cityId);
        parcel.writeString(this.cityCode);
        parcel.writeString(this.cityName);
        parcel.writeString(this.cityTimeZone);
        parcel.writeLong(this.createTime);
        parcel.writeLong(this.modifyTime);
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private GpsPoint f2919a;
        private String b;
        private String c;
        private String d;
        private String e;
        private long g;
        private long h;
        private String j;
    }

    public String toString() {
        return "CityInfo{countryId='" + this.countryId + "', cityId='" + this.cityId + "', cityCode='" + this.cityCode + "', cityName='" + this.cityName + "', cityTimeZone='" + this.cityTimeZone + "', cityCenter=" + this.cityCenter + ", createTime=" + this.createTime + ", modifyTime=" + this.modifyTime + '}';
    }
}
