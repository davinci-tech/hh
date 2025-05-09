package com.huawei.health.knit.model.mainpage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.model.KnitSectionConfig;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class MainPageConfig implements Parcelable {
    public static final Parcelable.Creator<MainPageConfig> CREATOR = new Parcelable.Creator<MainPageConfig>() { // from class: com.huawei.health.knit.model.mainpage.MainPageConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: abX_, reason: merged with bridge method [inline-methods] */
        public MainPageConfig createFromParcel(Parcel parcel) {
            return new MainPageConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public MainPageConfig[] newArray(int i) {
            return new MainPageConfig[i];
        }
    };

    @SerializedName("page_type")
    @Expose
    private int mPageType;

    @SerializedName("position")
    @Expose
    private int mPosition;

    @SerializedName("resPosId")
    @Expose
    private int mResPosId;

    @SerializedName("section_list")
    @Expose
    private ArrayList<KnitSectionConfig> mSectionList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected MainPageConfig(Parcel parcel) {
        this.mPageType = parcel.readInt();
        this.mPosition = parcel.readInt();
        this.mResPosId = parcel.readInt();
        this.mSectionList = parcel.createTypedArrayList(KnitSectionConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPageType);
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.mResPosId);
        parcel.writeTypedList(this.mSectionList);
    }

    public int getPageType() {
        return this.mPageType;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public int getResPosId() {
        return this.mResPosId;
    }

    public ArrayList<KnitSectionConfig> getSectionList() {
        return this.mSectionList;
    }
}
