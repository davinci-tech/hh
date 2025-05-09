package com.huawei.ui.main.stories.template.health.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import defpackage.ryl;

/* loaded from: classes7.dex */
public class HealthNoDeviceConfig implements Parcelable {
    public static final Parcelable.Creator<HealthNoDeviceConfig> CREATOR = new Parcelable.Creator<HealthNoDeviceConfig>() { // from class: com.huawei.ui.main.stories.template.health.config.HealthNoDeviceConfig.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUe_, reason: merged with bridge method [inline-methods] */
        public HealthNoDeviceConfig createFromParcel(Parcel parcel) {
            return new HealthNoDeviceConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HealthNoDeviceConfig[] newArray(int i) {
            return new HealthNoDeviceConfig[i];
        }
    };

    @SerializedName("buy_device_view")
    @Expose
    private HealthCommonExpandViewConfig mBuyDeviceViewConfig;

    @SerializedName("content_presenter")
    @Expose
    private String mContentPresenter;

    @SerializedName("description_view")
    @Expose
    private HealthCommonExpandViewConfig mDesViewConfig;

    @SerializedName("information_view")
    @Expose
    private HealthCommonExpandViewConfig mInfoViewConfig;

    @SerializedName("operation_data")
    @Expose
    private ryl mOperationData;

    @SerializedName("service_view")
    @Expose
    private HealthCommonExpandViewConfig mServiceViewConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HealthNoDeviceConfig(Parcel parcel) {
        this.mContentPresenter = parcel.readString();
        this.mDesViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mBuyDeviceViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mServiceViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mInfoViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mContentPresenter);
        parcel.writeParcelable(this.mDesViewConfig, i);
        parcel.writeParcelable(this.mBuyDeviceViewConfig, i);
        parcel.writeParcelable(this.mServiceViewConfig, i);
        parcel.writeParcelable(this.mInfoViewConfig, i);
    }

    public String getContentPresenter() {
        return this.mContentPresenter;
    }

    public ryl getOperationData() {
        return this.mOperationData;
    }

    public HealthCommonExpandViewConfig getBuyDeviceViewConfig() {
        return this.mBuyDeviceViewConfig;
    }

    public HealthCommonExpandViewConfig getServiceViewConfig() {
        return this.mServiceViewConfig;
    }

    public HealthCommonExpandViewConfig getInfoViewConfig() {
        return this.mInfoViewConfig;
    }
}
