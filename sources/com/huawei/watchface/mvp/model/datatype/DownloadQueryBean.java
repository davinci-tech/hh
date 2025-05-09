package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DownloadQueryBean implements Parcelable {
    public static final Parcelable.Creator<DownloadQueryBean> CREATOR = new Parcelable.Creator<DownloadQueryBean>() { // from class: com.huawei.watchface.mvp.model.datatype.DownloadQueryBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadQueryBean createFromParcel(Parcel parcel) {
            return new DownloadQueryBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadQueryBean[] newArray(int i) {
            return new DownloadQueryBean[i];
        }
    };
    public static final String FREE_RESOURCE = "1";
    public static final String PAY_RESOURCE = "2";
    private String cnTitle;
    private String dialType;
    private String fileSize;
    private String hitopId;
    private boolean isOneClick;
    private boolean isUpdate;
    private boolean isVipFreeWatchFace;
    private boolean isZip;
    private String productId;
    private String title;
    private int type;
    private String version;
    private String watchScreen;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isVipFreeWatchFace() {
        return this.isVipFreeWatchFace;
    }

    public void setVipFreeWatchFace(boolean z) {
        this.isVipFreeWatchFace = z;
    }

    public String getHitopId() {
        return this.hitopId;
    }

    public void setHitopId(String str) {
        this.hitopId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getScreen() {
        return this.watchScreen;
    }

    public void setScreen(String str) {
        this.watchScreen = str;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

    public boolean isUpdate() {
        return this.isUpdate;
    }

    public void setUpdate(boolean z) {
        this.isUpdate = z;
    }

    public boolean isOneClick() {
        return this.isOneClick;
    }

    public void setOneClick(boolean z) {
        this.isOneClick = z;
    }

    public String getDialType() {
        return this.dialType;
    }

    public void setDialType(String str) {
        this.dialType = str;
    }

    public boolean isZip() {
        return this.isZip;
    }

    public void setZip(boolean z) {
        this.isZip = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCnTitle() {
        return this.cnTitle;
    }

    public void setCnTitle(String str) {
        this.cnTitle = str;
    }

    protected DownloadQueryBean(Parcel parcel) {
        this.hitopId = parcel.readString();
        this.productId = parcel.readString();
        this.type = parcel.readInt();
        this.version = parcel.readString();
        this.watchScreen = parcel.readString();
        this.fileSize = parcel.readString();
        this.isUpdate = parcel.readByte() != 0;
        this.dialType = parcel.readString();
        this.isZip = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.hitopId);
        parcel.writeString(this.productId);
        parcel.writeInt(this.type);
        parcel.writeString(this.version);
        parcel.writeString(this.watchScreen);
        parcel.writeString(this.fileSize);
        parcel.writeByte(this.isUpdate ? (byte) 1 : (byte) 0);
        parcel.writeString(this.dialType);
        parcel.writeByte(this.isZip ? (byte) 1 : (byte) 0);
    }
}
