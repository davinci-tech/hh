package com.huawei.basichealthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ConfigDetailBean implements Parcelable {
    public static final Parcelable.Creator<ConfigDetailBean> CREATOR = new Parcelable.Creator<ConfigDetailBean>() { // from class: com.huawei.basichealthmodel.bean.ConfigDetailBean.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: jO_, reason: merged with bridge method [inline-methods] */
        public ConfigDetailBean createFromParcel(Parcel parcel) {
            return new ConfigDetailBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ConfigDetailBean[] newArray(int i) {
            return new ConfigDetailBean[i];
        }
    };
    private String mBackgroundImage;
    private String mBackgroundImageForTahiti;
    private String mDescriptionResourceId;
    private String mH5PackageName;
    private String mUri;
    private String mUrl;
    private String[] mUrlArray;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ConfigDetailBean() {
    }

    private ConfigDetailBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mUri = parcel.readString();
        this.mUrl = parcel.readString();
        this.mH5PackageName = parcel.readString();
        this.mUrlArray = parcel.createStringArray();
        this.mBackgroundImage = parcel.readString();
        this.mBackgroundImageForTahiti = parcel.readString();
        this.mDescriptionResourceId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mUri);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mH5PackageName);
        parcel.writeStringArray(this.mUrlArray);
        parcel.writeString(this.mBackgroundImage);
        parcel.writeString(this.mBackgroundImageForTahiti);
        parcel.writeString(this.mDescriptionResourceId);
    }

    public String getUri() {
        return this.mUri;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getH5PackageName() {
        return this.mH5PackageName;
    }
}
