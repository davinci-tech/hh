package com.huawei.ui.main.stories.template.health.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import defpackage.ryl;

/* loaded from: classes7.dex */
public class HealthHasDataConfig implements Parcelable {
    public static final Parcelable.Creator<HealthHasDataConfig> CREATOR = new Parcelable.Creator<HealthHasDataConfig>() { // from class: com.huawei.ui.main.stories.template.health.config.HealthHasDataConfig.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUd_, reason: merged with bridge method [inline-methods] */
        public HealthHasDataConfig createFromParcel(Parcel parcel) {
            return new HealthHasDataConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HealthHasDataConfig[] newArray(int i) {
            return new HealthHasDataConfig[i];
        }
    };

    @SerializedName("content_presenter")
    @Expose
    private String mContentPresenter;

    @SerializedName("day_fragment")
    @Expose
    private HealthDateFragmentConfig mDayFragmentConfig;

    @SerializedName("month_fragment")
    @Expose
    private HealthDateFragmentConfig mMonthFragmentConfig;

    @SerializedName("operation_data")
    @Expose
    private ryl mOperationData;

    @SerializedName("week_fragment")
    @Expose
    private HealthDateFragmentConfig mWeekFragmentConfig;

    @SerializedName("year_fragment")
    @Expose
    private HealthDateFragmentConfig mYearFragmentConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HealthHasDataConfig(Parcel parcel) {
        this.mContentPresenter = parcel.readString();
        this.mDayFragmentConfig = (HealthDateFragmentConfig) parcel.readParcelable(HealthDateFragmentConfig.class.getClassLoader());
        this.mWeekFragmentConfig = (HealthDateFragmentConfig) parcel.readParcelable(HealthDateFragmentConfig.class.getClassLoader());
        this.mMonthFragmentConfig = (HealthDateFragmentConfig) parcel.readParcelable(HealthDateFragmentConfig.class.getClassLoader());
        this.mYearFragmentConfig = (HealthDateFragmentConfig) parcel.readParcelable(HealthDateFragmentConfig.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mContentPresenter);
        parcel.writeParcelable(this.mDayFragmentConfig, i);
        parcel.writeParcelable(this.mWeekFragmentConfig, i);
        parcel.writeParcelable(this.mMonthFragmentConfig, i);
        parcel.writeParcelable(this.mYearFragmentConfig, i);
    }

    public String getContentPresenter() {
        return this.mContentPresenter;
    }

    public ryl getOperationData() {
        return this.mOperationData;
    }

    public HealthDateFragmentConfig getDayFragmentConfig() {
        return this.mDayFragmentConfig;
    }

    public HealthDateFragmentConfig getWeekFragmentConfig() {
        return this.mWeekFragmentConfig;
    }

    public HealthDateFragmentConfig getMonthFragmentConfig() {
        return this.mMonthFragmentConfig;
    }

    public HealthDateFragmentConfig getYearFragmentConfig() {
        return this.mYearFragmentConfig;
    }
}
