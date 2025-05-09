package com.huawei.health.knit.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.ui.KnitFragment;

/* loaded from: classes3.dex */
public class KnitSectionConfig implements Parcelable {
    public static final Parcelable.Creator<KnitSectionConfig> CREATOR = new Parcelable.Creator<KnitSectionConfig>() { // from class: com.huawei.health.knit.model.KnitSectionConfig.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: abV_, reason: merged with bridge method [inline-methods] */
        public KnitSectionConfig createFromParcel(Parcel parcel) {
            return new KnitSectionConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public KnitSectionConfig[] newArray(int i) {
            return new KnitSectionConfig[i];
        }
    };
    public static final String SECTION_GROUP_ID_KEY = "groupId";

    @SerializedName("dataUrl")
    @Expose
    private String mDataUrl;

    @SerializedName(KnitFragment.KEY_EXTRA)
    @Expose
    private String mExtra;

    @SerializedName("groupId")
    @Expose
    private int mGroupId;

    @SerializedName("isShare")
    @Expose
    private String mIsShare;

    @SerializedName("priority")
    @Expose
    private int mPriority;

    @SerializedName("template")
    @Expose
    private int mTemplate;

    @SerializedName("versionControl")
    @Expose
    private String mVersionControl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnitSectionConfig(Parcel parcel) {
        this.mTemplate = parcel.readInt();
        this.mDataUrl = parcel.readString();
        this.mGroupId = parcel.readInt();
        this.mExtra = parcel.readString();
        this.mPriority = parcel.readInt();
        this.mVersionControl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mTemplate);
        parcel.writeString(this.mDataUrl);
        parcel.writeInt(this.mGroupId);
        parcel.writeString(this.mExtra);
        parcel.writeInt(this.mPriority);
        parcel.writeString(this.mVersionControl);
    }

    public String getExtra() {
        return this.mExtra;
    }

    public int getTemplate() {
        return this.mTemplate;
    }

    public String toString() {
        return "KnitSectionConfig{mTemplate=" + this.mTemplate + ", mIsShare='" + this.mIsShare + "', mDataUrl='" + this.mDataUrl + "', mGroupId=" + this.mGroupId + ", mExtra='" + this.mExtra + "', mPriority=" + this.mPriority + ", mVersionControl='" + this.mVersionControl + "'}";
    }
}
