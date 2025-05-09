package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class PortraitImgInfo implements Parcelable {
    public static final Parcelable.Creator<PortraitImgInfo> CREATOR = new Parcelable.Creator<PortraitImgInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.PortraitImgInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PortraitImgInfo createFromParcel(Parcel parcel) {
            return new PortraitImgInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PortraitImgInfo[] newArray(int i) {
            return new PortraitImgInfo[i];
        }
    };
    private String bgImgUrl;
    private String fgImgUrl;
    private String imgUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getFgImgUrl() {
        return this.fgImgUrl;
    }

    public void setFgImgUrl(String str) {
        this.fgImgUrl = str;
    }

    public String getBgImgUrl() {
        return this.bgImgUrl;
    }

    public void setBgImgUrl(String str) {
        this.bgImgUrl = str;
    }

    protected PortraitImgInfo(Parcel parcel) {
        this.imgUrl = parcel.readString();
        this.fgImgUrl = parcel.readString();
        this.bgImgUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.fgImgUrl);
        parcel.writeString(this.bgImgUrl);
    }
}
