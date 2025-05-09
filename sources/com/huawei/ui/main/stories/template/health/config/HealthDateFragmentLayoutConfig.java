package com.huawei.ui.main.stories.template.health.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class HealthDateFragmentLayoutConfig implements Parcelable {
    public static final Parcelable.Creator<HealthDateFragmentLayoutConfig> CREATOR = new Parcelable.Creator<HealthDateFragmentLayoutConfig>() { // from class: com.huawei.ui.main.stories.template.health.config.HealthDateFragmentLayoutConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUc_, reason: merged with bridge method [inline-methods] */
        public HealthDateFragmentLayoutConfig createFromParcel(Parcel parcel) {
            return new HealthDateFragmentLayoutConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HealthDateFragmentLayoutConfig[] newArray(int i) {
            return new HealthDateFragmentLayoutConfig[i];
        }
    };

    @SerializedName("analysis_view")
    @Expose
    private HealthCommonExpandViewConfig mAnalysisViewConfig;

    @SerializedName("chart_view")
    @Expose
    private HealthCommonExpandViewConfig mChartView;

    @SerializedName("recommend_service_view")
    @Expose
    private HealthCommonExpandViewConfig mHealthRecommendServiceViewConfig;

    @SerializedName("service_view")
    @Expose
    private HealthCommonExpandViewConfig mHealthServicesDisplayViewConfig;

    @SerializedName("information_view")
    @Expose
    private HealthCommonExpandViewConfig mInformationViewConfig;

    @SerializedName("recommend_activity_view")
    @Expose
    private HealthCommonExpandViewConfig mRecommendActivityViewConfig;

    @SerializedName("statistics_view")
    @Expose
    private HealthCommonExpandViewConfig mStatisticsViewConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HealthDateFragmentLayoutConfig(Parcel parcel) {
        this.mChartView = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mStatisticsViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mAnalysisViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mHealthRecommendServiceViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mHealthServicesDisplayViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mRecommendActivityViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
        this.mInformationViewConfig = (HealthCommonExpandViewConfig) parcel.readParcelable(HealthCommonExpandViewConfig.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mChartView, i);
        parcel.writeParcelable(this.mStatisticsViewConfig, i);
        parcel.writeParcelable(this.mAnalysisViewConfig, i);
        parcel.writeParcelable(this.mHealthRecommendServiceViewConfig, i);
        parcel.writeParcelable(this.mHealthServicesDisplayViewConfig, i);
        parcel.writeParcelable(this.mRecommendActivityViewConfig, i);
        parcel.writeParcelable(this.mInformationViewConfig, i);
    }

    public HealthCommonExpandViewConfig getChartView() {
        return this.mChartView;
    }

    public HealthCommonExpandViewConfig getStatisticsViewConfig() {
        return this.mStatisticsViewConfig;
    }

    public HealthCommonExpandViewConfig getAnalysisViewConfig() {
        return this.mAnalysisViewConfig;
    }

    public HealthCommonExpandViewConfig getHealthRecommendServiceViewConfig() {
        return this.mHealthRecommendServiceViewConfig;
    }

    public HealthCommonExpandViewConfig getHealthServicesDisplayViewConfig() {
        return this.mHealthServicesDisplayViewConfig;
    }

    public HealthCommonExpandViewConfig getRecommendActivityViewConfig() {
        return this.mRecommendActivityViewConfig;
    }

    public HealthCommonExpandViewConfig getInformationViewConfig() {
        return this.mInformationViewConfig;
    }
}
