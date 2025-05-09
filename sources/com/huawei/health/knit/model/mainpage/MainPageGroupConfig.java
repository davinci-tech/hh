package com.huawei.health.knit.model.mainpage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class MainPageGroupConfig implements Parcelable {
    public static final Parcelable.Creator<MainPageGroupConfig> CREATOR = new Parcelable.Creator<MainPageGroupConfig>() { // from class: com.huawei.health.knit.model.mainpage.MainPageGroupConfig.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: abY_, reason: merged with bridge method [inline-methods] */
        public MainPageGroupConfig createFromParcel(Parcel parcel) {
            return new MainPageGroupConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MainPageGroupConfig[] newArray(int i) {
            return new MainPageGroupConfig[i];
        }
    };

    @SerializedName("category")
    @Expose
    private String mCategory;

    @SerializedName("pages")
    @Expose
    private ArrayList<MainPageConfig> mPagesConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected MainPageGroupConfig(Parcel parcel) {
        this.mCategory = parcel.readString();
        this.mPagesConfig = parcel.createTypedArrayList(MainPageConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCategory);
        parcel.writeTypedList(this.mPagesConfig);
    }

    public ArrayList<MainPageConfig> getPagesConfig() {
        return this.mPagesConfig;
    }
}
