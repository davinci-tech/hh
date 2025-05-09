package com.huawei.health.knit.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class KnitPageConfig implements Parcelable {
    public static final Parcelable.Creator<KnitPageConfig> CREATOR = new Parcelable.Creator<KnitPageConfig>() { // from class: com.huawei.health.knit.model.KnitPageConfig.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: abT_, reason: merged with bridge method [inline-methods] */
        public KnitPageConfig createFromParcel(Parcel parcel) {
            return new KnitPageConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public KnitPageConfig[] newArray(int i) {
            return new KnitPageConfig[i];
        }
    };

    @SerializedName("noDataPage")
    @Expose
    private KnitSubPageConfig mNoDataPageConfig;

    @SerializedName("page_type")
    @Expose
    private int mPageType;

    @SerializedName("subPages")
    @Expose
    private ArrayList<KnitSubPageConfig> mSubPagesConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnitPageConfig(Parcel parcel) {
        this.mPageType = parcel.readInt();
        this.mSubPagesConfig = parcel.createTypedArrayList(KnitSubPageConfig.CREATOR);
        this.mNoDataPageConfig = (KnitSubPageConfig) parcel.readTypedObject(KnitSubPageConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPageType);
        parcel.writeTypedList(this.mSubPagesConfig);
        parcel.writeTypedObject(this.mNoDataPageConfig, i);
    }

    public ArrayList<KnitSubPageConfig> getSubPagesConfig() {
        return this.mSubPagesConfig;
    }

    public int getPageType() {
        return this.mPageType;
    }

    public KnitSubPageConfig getNoDataPageConfig() {
        return this.mNoDataPageConfig;
    }

    public String toString() {
        return "KnitPageConfig{mPageType=" + this.mPageType + ", mSubPageConfigs=" + this.mSubPagesConfig + '}';
    }
}
