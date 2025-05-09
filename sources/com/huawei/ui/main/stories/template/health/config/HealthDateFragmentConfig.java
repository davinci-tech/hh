package com.huawei.ui.main.stories.template.health.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class HealthDateFragmentConfig implements Parcelable {
    public static final Parcelable.Creator<HealthDateFragmentConfig> CREATOR = new Parcelable.Creator<HealthDateFragmentConfig>() { // from class: com.huawei.ui.main.stories.template.health.config.HealthDateFragmentConfig.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUb_, reason: merged with bridge method [inline-methods] */
        public HealthDateFragmentConfig createFromParcel(Parcel parcel) {
            return new HealthDateFragmentConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HealthDateFragmentConfig[] newArray(int i) {
            return new HealthDateFragmentConfig[i];
        }
    };

    @SerializedName("fragment_presenter")
    @Expose
    private String mFragmentPresenter;

    @SerializedName("layout_config")
    @Expose
    private HealthDateFragmentLayoutConfig mLayoutConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HealthDateFragmentConfig(Parcel parcel) {
        this.mFragmentPresenter = parcel.readString();
        this.mLayoutConfig = (HealthDateFragmentLayoutConfig) parcel.readParcelable(HealthDateFragmentLayoutConfig.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mFragmentPresenter);
        parcel.writeParcelable(this.mLayoutConfig, i);
    }

    public String getFragmentPresenter() {
        return this.mFragmentPresenter;
    }

    public HealthDateFragmentLayoutConfig getLayoutConfig() {
        return this.mLayoutConfig;
    }
}
