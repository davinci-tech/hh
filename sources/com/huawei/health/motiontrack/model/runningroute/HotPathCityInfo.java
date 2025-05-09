package com.huawei.health.motiontrack.model.runningroute;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;

/* loaded from: classes3.dex */
public class HotPathCityInfo extends BaseGroupDataAdapter.d implements Parcelable {
    public static final Parcelable.Creator<HotPathCityInfo> CREATOR = new Parcelable.Creator<HotPathCityInfo>() { // from class: com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: arp_, reason: merged with bridge method [inline-methods] */
        public HotPathCityInfo createFromParcel(Parcel parcel) {
            return new HotPathCityInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HotPathCityInfo[] newArray(int i) {
            return new HotPathCityInfo[i];
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

    @SerializedName("countryName")
    private String countryName;

    @SerializedName("createTime")
    private long createTime;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long modifyTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HotPathCityInfo() {
    }

    public HotPathCityInfo(Parcel parcel) {
        this.countryId = parcel.readString();
        this.countryName = parcel.readString();
        this.cityId = parcel.readString();
        this.cityCode = parcel.readString();
        this.cityName = parcel.readString();
        this.cityTimeZone = parcel.readString();
        this.createTime = parcel.readLong();
        this.modifyTime = parcel.readLong();
        this.cityCenter = (GpsPoint) parcel.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String str) {
        this.countryId = str;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String str) {
        this.countryName = str;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getCityTimeZone() {
        return this.cityTimeZone;
    }

    public void setCityTimeZone(String str) {
        this.cityTimeZone = str;
    }

    public GpsPoint getCityCenter() {
        return this.cityCenter;
    }

    public void setCityCenter(GpsPoint gpsPoint) {
        this.cityCenter = gpsPoint;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(long j) {
        this.modifyTime = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.countryId);
        parcel.writeString(this.countryName);
        parcel.writeString(this.cityId);
        parcel.writeString(this.cityCode);
        parcel.writeString(this.cityName);
        parcel.writeString(this.cityTimeZone);
        parcel.writeLong(this.createTime);
        parcel.writeLong(this.modifyTime);
        parcel.writeParcelable(this.cityCenter, 0);
    }

    public String toString() {
        return "HotPathCityInfo{countryId='" + this.countryId + "', countryName='" + this.countryName + "', cityId='" + this.cityId + "', cityCode='" + this.cityCode + "', cityName='" + this.cityName + "', cityTimeZone='" + this.cityTimeZone + "', cityCenter=" + this.cityCenter + ", modifyTime=" + this.modifyTime + ", createTime=" + this.createTime + '}';
    }
}
