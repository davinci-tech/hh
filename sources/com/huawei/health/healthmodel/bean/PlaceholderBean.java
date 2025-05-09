package com.huawei.health.healthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class PlaceholderBean implements Parcelable {
    public static final Parcelable.Creator<PlaceholderBean> CREATOR = new Parcelable.Creator<PlaceholderBean>() { // from class: com.huawei.health.healthmodel.bean.PlaceholderBean.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZG_, reason: merged with bridge method [inline-methods] */
        public PlaceholderBean createFromParcel(Parcel parcel) {
            return new PlaceholderBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public PlaceholderBean[] newArray(int i) {
            return new PlaceholderBean[i];
        }
    };

    @SerializedName("resourcesId")
    private String mResourcesId;

    @SerializedName("type")
    private String mType;

    @SerializedName("value")
    private String mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlaceholderBean() {
    }

    public PlaceholderBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mType = parcel.readString();
        this.mValue = parcel.readString();
        this.mResourcesId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mType);
        parcel.writeString(this.mValue);
        parcel.writeString(this.mResourcesId);
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setValue(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public void setResourcesId(String str) {
        this.mResourcesId = str;
    }

    public String getResourcesId() {
        return this.mResourcesId;
    }

    public String toString() {
        return "PlaceholderBean{mType=" + this.mType + ", mValue=" + this.mValue + ", mResourcesId=" + this.mResourcesId + "}";
    }
}
