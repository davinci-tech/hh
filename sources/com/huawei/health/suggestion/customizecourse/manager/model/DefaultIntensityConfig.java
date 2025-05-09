package com.huawei.health.suggestion.customizecourse.manager.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes8.dex */
public class DefaultIntensityConfig extends TargetConfig {
    public static final Parcelable.Creator<DefaultIntensityConfig> CREATOR = new Parcelable.Creator<DefaultIntensityConfig>() { // from class: com.huawei.health.suggestion.customizecourse.manager.model.DefaultIntensityConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: ayg_, reason: merged with bridge method [inline-methods] */
        public DefaultIntensityConfig createFromParcel(Parcel parcel) {
            return new DefaultIntensityConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public DefaultIntensityConfig[] newArray(int i) {
            return new DefaultIntensityConfig[i];
        }
    };

    @SerializedName("rangeH")
    private double mRangeH;

    @SerializedName("rangeL")
    private double mRangeL;

    @SerializedName("unitType")
    private String mUnitType;

    @Override // com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected DefaultIntensityConfig(Parcel parcel) {
        super(parcel);
        this.mRangeH = parcel.readDouble();
        this.mRangeL = parcel.readDouble();
        this.mUnitType = parcel.readString();
    }

    public void readFromParcel(Parcel parcel) {
        this.mRangeH = parcel.readDouble();
        this.mRangeL = parcel.readDouble();
        this.mUnitType = parcel.readString();
    }

    @Override // com.huawei.pluginfitnessadvice.TargetConfig, com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.mRangeH);
        parcel.writeDouble(this.mRangeL);
        parcel.writeString(this.mUnitType);
    }

    public double getRangeH() {
        return this.mRangeH;
    }

    public void setRangeH(double d) {
        this.mRangeH = d;
    }

    public double getRangeL() {
        return this.mRangeL;
    }

    public void setRangeL(double d) {
        this.mRangeL = d;
    }

    public String getUnitType() {
        return this.mUnitType;
    }

    public void setUnitType(String str) {
        this.mUnitType = str;
    }
}
