package com.huawei.tabtemplate;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class SportSectionConfig implements Parcelable {
    public static final Parcelable.Creator<SportSectionConfig> CREATOR = new Parcelable.Creator<SportSectionConfig>() { // from class: com.huawei.tabtemplate.SportSectionConfig.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cvf_, reason: merged with bridge method [inline-methods] */
        public SportSectionConfig createFromParcel(Parcel parcel) {
            return new SportSectionConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SportSectionConfig[] newArray(int i) {
            return new SportSectionConfig[i];
        }
    };

    @SerializedName("dataUrl")
    @Expose
    private String mDataProviderUrl;

    @SerializedName("isOnlyOffline")
    @Expose
    private boolean mIsOnlyOffline;

    @SerializedName("priority")
    @Expose
    private int mPriority;

    @SerializedName("template")
    @Expose
    private int mTemplate;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SportSectionConfig(Parcel parcel) {
        this.mTemplate = parcel.readInt();
        this.mDataProviderUrl = parcel.readString();
        this.mPriority = parcel.readInt();
        this.mIsOnlyOffline = parcel.readInt() == 1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mTemplate);
        parcel.writeString(this.mDataProviderUrl);
        parcel.writeInt(this.mPriority);
        parcel.writeInt(this.mIsOnlyOffline ? 1 : 0);
    }
}
