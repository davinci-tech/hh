package com.huawei.watchface.mvp.model.datatype.querysubinfodetail;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class ProductInfo implements Parcelable {
    public static final Parcelable.Creator<ProductInfo> CREATOR = new Parcelable.Creator<ProductInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.querysubinfodetail.ProductInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductInfo createFromParcel(Parcel parcel) {
            return new ProductInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductInfo[] newArray(int i) {
            return new ProductInfo[i];
        }
    };
    private String canRenewFlag;
    private String currency;
    private String discountPrice;
    private String extendInfo;
    private String guideText;
    private String markUrl;
    private String maxAge;
    private String minAge;
    private String price;
    private String priceLevelExtend;
    private String productCode;
    private String productDesc;
    private String productInterpretation;
    private String productName;
    private String productType;
    private String promotiondesc;
    private String promotionurl;
    private String redirectUrl;
    private String relProductCode;
    private String relProductDes;
    private String renewCode;
    private String renewPrice;
    private String sendCount;
    private String symbol;
    private String userType;
    private String validDay;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public String getProductType() {
        return this.productType;
    }

    public String getUserType() {
        return this.userType;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public String getPrice() {
        return this.price;
    }

    public String getDiscountPrice() {
        return this.discountPrice;
    }

    public String getRenewPrice() {
        return this.renewPrice;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getValidDay() {
        return this.validDay;
    }

    public String getCanRenewFlag() {
        return this.canRenewFlag;
    }

    public String getRenewCode() {
        return this.renewCode;
    }

    public String getPromotiondesc() {
        return this.promotiondesc;
    }

    public String getPromotionurl() {
        return this.promotionurl;
    }

    public String getExtendInfo() {
        return this.extendInfo;
    }

    public String getMarkUrl() {
        return this.markUrl;
    }

    public String getSendCount() {
        return this.sendCount;
    }

    public String getRelProductCode() {
        return this.relProductCode;
    }

    public String getRelProductDes() {
        return this.relProductDes;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public String getPriceLevelExtend() {
        return this.priceLevelExtend;
    }

    public String getMinAge() {
        return this.minAge;
    }

    public String getMaxAge() {
        return this.maxAge;
    }

    public String getProductInterpretation() {
        return this.productInterpretation;
    }

    public String getGuideText() {
        return this.guideText;
    }

    public void setProductCode(String str) {
        this.productCode = str;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public void setUserType(String str) {
        this.userType = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setDiscountPrice(String str) {
        this.discountPrice = str;
    }

    public void setRenewPrice(String str) {
        this.renewPrice = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setValidDay(String str) {
        this.validDay = str;
    }

    public void setCanRenewFlag(String str) {
        this.canRenewFlag = str;
    }

    public void setRenewCode(String str) {
        this.renewCode = str;
    }

    public void setPromotiondesc(String str) {
        this.promotiondesc = str;
    }

    public void setPromotionurl(String str) {
        this.promotionurl = str;
    }

    public void setExtendInfo(String str) {
        this.extendInfo = str;
    }

    public void setMarkUrl(String str) {
        this.markUrl = str;
    }

    public void setSendCount(String str) {
        this.sendCount = str;
    }

    public void setRelProductCode(String str) {
        this.relProductCode = str;
    }

    public void setRelProductDes(String str) {
        this.relProductDes = str;
    }

    public void setRedirectUrl(String str) {
        this.redirectUrl = str;
    }

    public void setPriceLevelExtend(String str) {
        this.priceLevelExtend = str;
    }

    public void setMinAge(String str) {
        this.minAge = str;
    }

    public void setMaxAge(String str) {
        this.maxAge = str;
    }

    public void setProductInterpretation(String str) {
        this.productInterpretation = str;
    }

    public void setGuideText(String str) {
        this.guideText = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.productCode);
        parcel.writeString(this.productType);
        parcel.writeString(this.userType);
        parcel.writeString(this.productName);
        parcel.writeString(this.productDesc);
        parcel.writeString(this.price);
        parcel.writeString(this.discountPrice);
        parcel.writeString(this.renewPrice);
        parcel.writeString(this.currency);
        parcel.writeString(this.symbol);
        parcel.writeString(this.validDay);
        parcel.writeString(this.canRenewFlag);
        parcel.writeString(this.renewCode);
        parcel.writeString(this.promotiondesc);
        parcel.writeString(this.promotionurl);
        parcel.writeString(this.extendInfo);
        parcel.writeString(this.markUrl);
        parcel.writeString(this.sendCount);
        parcel.writeString(this.relProductCode);
        parcel.writeString(this.relProductDes);
        parcel.writeString(this.priceLevelExtend);
        parcel.writeString(this.minAge);
        parcel.writeString(this.maxAge);
        parcel.writeString(this.productInterpretation);
        parcel.writeString(this.guideText);
    }

    protected ProductInfo(Parcel parcel) {
        this.productCode = parcel.readString();
        this.productType = parcel.readString();
        this.userType = parcel.readString();
        this.productName = parcel.readString();
        this.productDesc = parcel.readString();
        this.price = parcel.readString();
        this.discountPrice = parcel.readString();
        this.renewPrice = parcel.readString();
        this.currency = parcel.readString();
        this.symbol = parcel.readString();
        this.validDay = parcel.readString();
        this.canRenewFlag = parcel.readString();
        this.renewCode = parcel.readString();
        this.promotiondesc = parcel.readString();
        this.promotionurl = parcel.readString();
        this.extendInfo = parcel.readString();
        this.markUrl = parcel.readString();
        this.sendCount = parcel.readString();
        this.relProductCode = parcel.readString();
        this.priceLevelExtend = parcel.readString();
        this.minAge = parcel.readString();
        this.productInterpretation = parcel.readString();
        this.guideText = parcel.readString();
    }
}
