package com.huawei.health.knit.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class KnitPageGroupConfig implements Parcelable {
    public static final Parcelable.Creator<KnitPageGroupConfig> CREATOR = new Parcelable.Creator<KnitPageGroupConfig>() { // from class: com.huawei.health.knit.model.KnitPageGroupConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: abU_, reason: merged with bridge method [inline-methods] */
        public KnitPageGroupConfig createFromParcel(Parcel parcel) {
            return new KnitPageGroupConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public KnitPageGroupConfig[] newArray(int i) {
            return new KnitPageGroupConfig[i];
        }
    };

    @SerializedName("category")
    @Expose
    private String mCategory;

    @SerializedName("pages")
    @Expose
    private ArrayList<KnitPageConfig> mPagesConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnitPageGroupConfig(Parcel parcel) {
        this.mCategory = parcel.readString();
        this.mPagesConfig = parcel.createTypedArrayList(KnitPageConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCategory);
        parcel.writeTypedList(this.mPagesConfig);
    }

    public ArrayList<KnitPageConfig> getPagesConfig() {
        return this.mPagesConfig;
    }

    public String toString() {
        return "KnitPageGroupConfig{mCategory='" + this.mCategory + "', mPagesConfig=" + this.mPagesConfig + '}';
    }
}
