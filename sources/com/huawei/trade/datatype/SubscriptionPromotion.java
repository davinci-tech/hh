package com.huawei.trade.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class SubscriptionPromotion implements Parcelable {
    public static final Parcelable.Creator<SubscriptionPromotion> CREATOR = new Parcelable.Creator<SubscriptionPromotion>() { // from class: com.huawei.trade.datatype.SubscriptionPromotion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriptionPromotion createFromParcel(Parcel parcel) {
            return new SubscriptionPromotion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriptionPromotion[] newArray(int i) {
            return new SubscriptionPromotion[i];
        }
    };
    public static final int NORMAL_CONTENT_TYPE = 1;
    public static final String PAY_AS_YOU_GO_MODE = "pay-as-you-go";
    public static final String PROMOTIONAL_TYPE = "promotional";

    @SerializedName("content")
    private String content;

    @SerializedName("contentType")
    private int contentType;

    @SerializedName("defaultRenewPrice")
    private long defaultRenewPrice;

    @SerializedName("descriptionVisibility")
    private boolean descriptionVisibility;

    @SerializedName("duration")
    private String duration;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("microOriginalPrice")
    private long microOriginalPrice;

    @SerializedName("mutualType")
    private String mutualType;

    @SerializedName("numOfPeriods")
    private int numOfPeriods;

    @SerializedName("promoMode")
    private String promoMode;

    @SerializedName("promoType")
    private String promoType;

    @SerializedName("promotionId")
    private String promotionId;

    @SerializedName("promotionName")
    private String promotionName;

    @SerializedName("promotionPolicyId")
    private String promotionPolicyId;

    @SerializedName("startTime")
    private String startTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public String getPromotionName() {
        return this.promotionName;
    }

    public void setPromotionName(String str) {
        this.promotionName = str;
    }

    public String getPromoType() {
        return this.promoType;
    }

    public void setPromoType(String str) {
        this.promoType = str;
    }

    public String getPromoMode() {
        return this.promoMode;
    }

    public void setPromoMode(String str) {
        this.promoMode = str;
    }

    public String getMutualType() {
        return this.mutualType;
    }

    public void setMutualType(String str) {
        this.mutualType = str;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public int getNumOfPeriods() {
        return this.numOfPeriods;
    }

    public void setNumOfPeriods(int i) {
        this.numOfPeriods = i;
    }

    public long getMicroOriginalPrice() {
        return this.microOriginalPrice;
    }

    public void setMicroOriginalPrice(long j) {
        this.microOriginalPrice = j;
    }

    public long getDefaultRenewPrice() {
        return this.defaultRenewPrice;
    }

    public void setDefaultRenewPrice(long j) {
        this.defaultRenewPrice = j;
    }

    public boolean isDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public void setDescriptionVisibility(boolean z) {
        this.descriptionVisibility = z;
    }

    public int getContentType() {
        return this.contentType;
    }

    public void setContentType(int i) {
        this.contentType = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String getPromotionPolicyId() {
        return this.promotionPolicyId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.promotionId);
        parcel.writeString(this.promotionName);
        parcel.writeString(this.promoType);
        parcel.writeString(this.promoMode);
        parcel.writeString(this.mutualType);
        parcel.writeString(this.duration);
        parcel.writeInt(this.numOfPeriods);
        parcel.writeLong(this.microOriginalPrice);
        parcel.writeLong(this.defaultRenewPrice);
        parcel.writeByte(this.descriptionVisibility ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.contentType);
        parcel.writeString(this.content);
        parcel.writeString(this.startTime);
        parcel.writeString(this.endTime);
        parcel.writeString(this.promotionPolicyId);
    }

    public SubscriptionPromotion() {
    }

    protected SubscriptionPromotion(Parcel parcel) {
        this.promotionId = parcel.readString();
        this.promotionName = parcel.readString();
        this.promoType = parcel.readString();
        this.promoMode = parcel.readString();
        this.mutualType = parcel.readString();
        this.duration = parcel.readString();
        this.numOfPeriods = parcel.readInt();
        this.microOriginalPrice = parcel.readLong();
        this.defaultRenewPrice = parcel.readLong();
        this.descriptionVisibility = parcel.readByte() != 0;
        this.contentType = parcel.readInt();
        this.content = parcel.readString();
        this.startTime = parcel.readString();
        this.endTime = parcel.readString();
        this.promotionPolicyId = parcel.readString();
    }
}
