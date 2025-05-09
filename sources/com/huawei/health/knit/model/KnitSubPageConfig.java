package com.huawei.health.knit.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class KnitSubPageConfig implements Parcelable {
    public static final Parcelable.Creator<KnitSubPageConfig> CREATOR = new Parcelable.Creator<KnitSubPageConfig>() { // from class: com.huawei.health.knit.model.KnitSubPageConfig.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: abW_, reason: merged with bridge method [inline-methods] */
        public KnitSubPageConfig createFromParcel(Parcel parcel) {
            return new KnitSubPageConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public KnitSubPageConfig[] newArray(int i) {
            return new KnitSubPageConfig[i];
        }
    };

    @SerializedName("resPosId")
    @Expose
    private int mResPosId;

    @SerializedName("resVersionType")
    @Expose
    private String mResVersionType;

    @SerializedName("section_list")
    @Expose
    private ArrayList<KnitSectionConfig> mSectionList;

    @SerializedName("subTitle")
    @Expose
    private String mSubTitle;

    @SerializedName("subTitleRes")
    @Expose
    private String mSubTitleRes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnitSubPageConfig(Parcel parcel) {
        this.mSubTitleRes = parcel.readString();
        this.mSubTitle = parcel.readString();
        this.mResPosId = parcel.readInt();
        this.mResVersionType = parcel.readString();
        this.mSectionList = parcel.createTypedArrayList(KnitSectionConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSubTitleRes);
        parcel.writeString(this.mSubTitle);
        parcel.writeInt(this.mResPosId);
        parcel.writeString(this.mResVersionType);
        parcel.writeTypedList(this.mSectionList);
    }

    public String getSubTitleRes() {
        return this.mSubTitleRes;
    }

    public String getSubTitle() {
        return this.mSubTitle;
    }

    public int getResPosId() {
        return this.mResPosId;
    }

    public String getResVersionType() {
        return this.mResVersionType;
    }

    public ArrayList<KnitSectionConfig> getSectionList() {
        return this.mSectionList;
    }

    public String toString() {
        return "KnitSubPageConfig{mSubTitleRes='" + this.mSubTitleRes + "', mSubTitle='" + this.mSubTitle + "', mResPosId=" + this.mResPosId + ", mResVersionType='" + this.mResVersionType + "', mSectionList=" + this.mSectionList + '}';
    }
}
