package com.huawei.watchface.mvp.model.datatype.querysubinfodetail;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;

/* loaded from: classes7.dex */
public class SubInfo implements Parcelable {
    public static final Parcelable.Creator<SubInfo> CREATOR = new Parcelable.Creator<SubInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.querysubinfodetail.SubInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubInfo createFromParcel(Parcel parcel) {
            return new SubInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubInfo[] newArray(int i) {
            return new SubInfo[i];
        }
    };
    private String currentDate;
    private String nextRenewTime;
    private ProductInfo productInfo;
    private String renewFlag;
    private String startDate;
    private String validDate;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProductInfo getProductInfo() {
        return this.productInfo;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getValidDate() {
        return this.validDate;
    }

    public String getCurrentDate() {
        return this.currentDate;
    }

    public String getRenewFlag() {
        return this.renewFlag;
    }

    public String getNextRenewTime() {
        return this.nextRenewTime;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setValidDate(String str) {
        this.validDate = str;
    }

    public void setCurrentDate(String str) {
        this.currentDate = str;
    }

    public void setRenewFlag(String str) {
        this.renewFlag = str;
    }

    public void setNextRenewTime(String str) {
        this.nextRenewTime = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.productInfo, i);
        parcel.writeString(this.startDate);
        parcel.writeString(this.validDate);
        parcel.writeString(this.currentDate);
        parcel.writeString(this.renewFlag);
        parcel.writeString(this.nextRenewTime);
    }

    protected SubInfo(Parcel parcel) {
        this.productInfo = (ProductInfo) new Gson().fromJson(parcel.readString(), ProductInfo.class);
        this.startDate = parcel.readString();
        this.validDate = parcel.readString();
        this.currentDate = parcel.readString();
        this.renewFlag = parcel.readString();
        this.nextRenewTime = parcel.readString();
    }
}
