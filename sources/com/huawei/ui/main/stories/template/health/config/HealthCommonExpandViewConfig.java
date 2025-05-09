package com.huawei.ui.main.stories.template.health.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.alipay.sdk.m.p.e;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.ui.main.stories.template.ComponentParam;
import java.util.List;

/* loaded from: classes7.dex */
public class HealthCommonExpandViewConfig implements Parcelable {
    public static final Parcelable.Creator<HealthCommonExpandViewConfig> CREATOR = new Parcelable.Creator<HealthCommonExpandViewConfig>() { // from class: com.huawei.ui.main.stories.template.health.config.HealthCommonExpandViewConfig.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUa_, reason: merged with bridge method [inline-methods] */
        public HealthCommonExpandViewConfig createFromParcel(Parcel parcel) {
            return new HealthCommonExpandViewConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HealthCommonExpandViewConfig[] newArray(int i) {
            return new HealthCommonExpandViewConfig[i];
        }
    };

    @SerializedName("component")
    @Expose
    private String mComponent;

    @SerializedName("new_component")
    @Expose
    private String mNewComponent;

    @SerializedName("oversea")
    @Expose
    private int mOversea;

    @SerializedName(e.n)
    @Expose
    private List<ComponentParam> mParams;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HealthCommonExpandViewConfig(Parcel parcel) {
        this.mComponent = parcel.readString();
        this.mNewComponent = parcel.readString();
        this.mParams = parcel.createTypedArrayList(ComponentParam.CREATOR);
        this.mOversea = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mComponent);
        parcel.writeString(this.mNewComponent);
        parcel.writeTypedList(this.mParams);
        parcel.writeInt(this.mOversea);
    }

    public List<ComponentParam> getParams() {
        return this.mParams;
    }

    public String getNewComponent() {
        return this.mNewComponent;
    }
}
