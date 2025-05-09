package com.huawei.ui.main.stories.privacy.template.model.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class PrivacyDataFragmentConfig implements Parcelable {
    public static final Parcelable.Creator<PrivacyDataFragmentConfig> CREATOR = new Parcelable.Creator<PrivacyDataFragmentConfig>() { // from class: com.huawei.ui.main.stories.privacy.template.model.config.PrivacyDataFragmentConfig.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dQC_, reason: merged with bridge method [inline-methods] */
        public PrivacyDataFragmentConfig createFromParcel(Parcel parcel) {
            return new PrivacyDataFragmentConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public PrivacyDataFragmentConfig[] newArray(int i) {
            return new PrivacyDataFragmentConfig[i];
        }
    };

    @SerializedName("fragment_presenter")
    @Expose
    private String fragmentPresenter;

    @SerializedName("layout_config")
    @Expose
    private PrivacyDataFragmentLayoutConfig layoutConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected PrivacyDataFragmentConfig(Parcel parcel) {
        this.fragmentPresenter = parcel.readString();
        this.layoutConfig = (PrivacyDataFragmentLayoutConfig) parcel.readParcelable(PrivacyDataFragmentLayoutConfig.class.getClassLoader());
    }

    public String getFragmentPresenter() {
        return this.fragmentPresenter;
    }

    public PrivacyDataFragmentLayoutConfig getLayoutConfig() {
        return this.layoutConfig;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fragmentPresenter);
        parcel.writeParcelable(this.layoutConfig, i);
    }
}
