package com.huawei.trade.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class Product implements Parcelable {
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() { // from class: com.huawei.trade.datatype.Product.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Product createFromParcel(Parcel parcel) {
            return new Product(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Product[] newArray(int i) {
            return new Product[i];
        }
    };
    public static final String MONTH_DURATION_FLAG = "P1M";
    public static int REPEAT_PURCHASE_FLAG = 0;
    public static final String SEASON_DURATION_FLAG = "P3M";
    public static int SUBSCRIPTION_PURCHASE_FLAG = 2;
    public static final String YEAR_DURATION_FLAG = "P1Y";

    @SerializedName("attachType")
    private String attachType;

    @SerializedName(HwPayConstant.KEY_CURRENCY)
    private String currency;

    @SerializedName("duration")
    private String duration;

    @SerializedName("giveawayDesc")
    private String giveawayDesc;

    @SerializedName("giveaways")
    private List<Product> giveaways;

    @SerializedName("giveawaysPrice")
    private int giveawaysPrice;

    @SerializedName("linkType")
    private int linkType;

    @SerializedName("linkValue")
    private String linkValue;

    @SerializedName("memberFlag")
    private boolean memberFlag;

    @SerializedName("memberPriceRetentionDesc")
    private String memberPriceRetentionDesc;

    @SerializedName("memberPriceRetentionVisibility")
    private boolean memberPriceRetentionVisibility;

    @SerializedName("microPrice")
    private long microPrice;

    @SerializedName("nowTime")
    private long nowTime;

    @SerializedName("priority")
    private int priority;

    @SerializedName(HwPayConstant.KEY_PRODUCTDESC)
    private String productDesc;

    @SerializedName("productId")
    private String productId;

    @SerializedName(HwPayConstant.KEY_PRODUCTNAME)
    private String productName;

    @SerializedName("productType")
    private String productType;

    @SerializedName("productUrl")
    private String productUrl;

    @SerializedName("promotion")
    private TimeLimitedPromotion promotion;

    @SerializedName("promotionDesc")
    private String promotionDesc;

    @SerializedName("promotionDescVisibility")
    private boolean promotionDescVisibility;

    @SerializedName("purchasePolicy")
    private int purchasePolicy;

    @SerializedName("resourceType")
    private int resourceType;

    @SerializedName("saleTime")
    private long saleTime;

    @SerializedName("showGiveawayPicFlag")
    private boolean showGiveawayPicFlag;

    @SerializedName("subscriptionPromotion")
    private SubscriptionPromotion subscriptionPromotion;

    @SerializedName("title")
    private String title;

    @SerializedName("titleVisibility")
    private boolean titleVisibility;

    @SerializedName("validityPeriodType")
    private int validityPeriodType;

    @SerializedName("validityPeriodValue")
    private long validityPeriodValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public TimeLimitedPromotion getPromotion() {
        return this.promotion;
    }

    public void setPromotion(TimeLimitedPromotion timeLimitedPromotion) {
        this.promotion = timeLimitedPromotion;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public boolean getMemberFlag() {
        return this.memberFlag;
    }

    public void setMemberFlag(boolean z) {
        this.memberFlag = z;
    }

    public int getPurchasePolicy() {
        return this.purchasePolicy;
    }

    public void setPurchasePolicy(int i) {
        this.purchasePolicy = i;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public boolean isTitleVisibility() {
        return this.titleVisibility;
    }

    public void setTitleVisibility(boolean z) {
        this.titleVisibility = z;
    }

    public boolean isPromotionDescVisibility() {
        return this.promotionDescVisibility;
    }

    public void setPromotionDescVisibility(boolean z) {
        this.promotionDescVisibility = z;
    }

    public String getPromotionDesc() {
        return this.promotionDesc;
    }

    public void setPromotionDesc(String str) {
        this.promotionDesc = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public long getMicroPrice() {
        return this.microPrice;
    }

    public void setMicroPrice(long j) {
        this.microPrice = j;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public long getSaleTime() {
        return this.saleTime;
    }

    public void setSaleTime(long j) {
        this.saleTime = j;
    }

    public boolean isShowGiveawayPicFlag() {
        return this.showGiveawayPicFlag;
    }

    public int getValidityPeriodType() {
        return this.validityPeriodType;
    }

    public void setValidityPeriodType(int i) {
        this.validityPeriodType = i;
    }

    public long getValidityPeriodValue() {
        return this.validityPeriodValue;
    }

    public void setValidityPeriodValue(long j) {
        this.validityPeriodValue = j;
    }

    public List<Product> getGiveaways() {
        return this.giveaways;
    }

    public void setGiveaways(List<Product> list) {
        this.giveaways = list;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public SubscriptionPromotion getSubscriptionPromotion() {
        return this.subscriptionPromotion;
    }

    public void setSubscriptionPromotion(SubscriptionPromotion subscriptionPromotion) {
        this.subscriptionPromotion = subscriptionPromotion;
    }

    public String getGiveawayDesc() {
        return this.giveawayDesc;
    }

    public void setGiveawayDesc(String str) {
        this.giveawayDesc = str;
    }

    public int getGiveawaysPrice() {
        return this.giveawaysPrice;
    }

    public void setGiveawaysPrice(int i) {
        this.giveawaysPrice = i;
    }

    public String getAttachType() {
        return this.attachType;
    }

    public void setAttachType(String str) {
        this.attachType = str;
    }

    public boolean isMemberPriceRetentionVisibility() {
        return this.memberPriceRetentionVisibility;
    }

    public void setMemberPriceRetentionVisibility(boolean z) {
        this.memberPriceRetentionVisibility = z;
    }

    public String getMemberPriceRetentionDesc() {
        String str = this.memberPriceRetentionDesc;
        return str == null ? "" : str;
    }

    public void setMemberPriceRetentionDesc(String str) {
        this.memberPriceRetentionDesc = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public String getLinkValue() {
        String str = this.linkValue;
        return str == null ? "" : str;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String toString() {
        return "Product{productId='" + this.productId + "', productType='" + this.productType + "', memberFlag=" + this.memberFlag + ", purchasePolicy=" + this.purchasePolicy + ", duration='" + this.duration + "', productName='" + this.productName + "', productDesc='" + this.productDesc + "', productUrl='" + this.productUrl + "', title='" + this.title + "', titleVisibility=" + this.titleVisibility + ", promotionDescVisibility=" + this.promotionDescVisibility + ", promotionDesc='" + this.promotionDesc + "', priority=" + this.priority + ", currency='" + this.currency + "', microPrice=" + this.microPrice + ", nowTime=" + this.nowTime + ", saleTime=" + this.saleTime + ", validityPeriodType=" + this.validityPeriodType + ", validityPeriodValue=" + this.validityPeriodValue + ", giveaways=" + this.giveaways + ", promotion=" + this.promotion + ", resourceType=" + this.resourceType + ", giveawayDesc='" + this.giveawayDesc + "', giveawaysPrice=" + this.giveawaysPrice + ", attachType='" + this.attachType + "', subscriptionPromotion=" + this.subscriptionPromotion + ", memberPriceRetentionVisibility=" + this.memberPriceRetentionVisibility + ", memberPriceRetentionDesc='" + this.memberPriceRetentionDesc + "', linkType=" + this.linkType + ", linkValue='" + this.linkValue + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.productId);
        parcel.writeString(this.productType);
        parcel.writeInt(this.memberFlag ? 1 : 0);
        parcel.writeInt(this.purchasePolicy);
        parcel.writeString(this.duration);
        parcel.writeString(this.productName);
        parcel.writeString(this.productDesc);
        parcel.writeString(this.productUrl);
        parcel.writeString(this.title);
        parcel.writeByte(this.titleVisibility ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.priority);
        parcel.writeString(this.currency);
        parcel.writeLong(this.microPrice);
        parcel.writeLong(this.nowTime);
        parcel.writeLong(this.saleTime);
        parcel.writeInt(this.validityPeriodType);
        parcel.writeLong(this.validityPeriodValue);
        parcel.writeList(this.giveaways);
        parcel.writeString(this.giveawayDesc);
        parcel.writeInt(this.giveawaysPrice);
        parcel.writeParcelable(this.promotion, i);
        parcel.writeInt(this.resourceType);
        parcel.writeParcelable(this.subscriptionPromotion, i);
        parcel.writeString(this.attachType);
        parcel.writeByte(this.promotionDescVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.promotionDesc);
        parcel.writeInt(this.memberPriceRetentionVisibility ? 1 : 0);
        parcel.writeString(this.memberPriceRetentionDesc);
        parcel.writeInt(this.linkType);
        parcel.writeString(this.linkValue);
    }

    public Product() {
    }

    protected Product(Parcel parcel) {
        this.productId = parcel.readString();
        this.productType = parcel.readString();
        this.memberFlag = parcel.readInt() != 0;
        this.purchasePolicy = parcel.readInt();
        this.duration = parcel.readString();
        this.productName = parcel.readString();
        this.productDesc = parcel.readString();
        this.productUrl = parcel.readString();
        this.title = parcel.readString();
        this.titleVisibility = parcel.readByte() != 0;
        this.priority = parcel.readInt();
        this.currency = parcel.readString();
        this.microPrice = parcel.readLong();
        this.nowTime = parcel.readLong();
        this.saleTime = parcel.readLong();
        this.validityPeriodType = parcel.readInt();
        this.validityPeriodValue = parcel.readLong();
        this.giveaways = new ArrayList();
        this.giveawayDesc = parcel.readString();
        this.giveawaysPrice = parcel.readInt();
        parcel.readList(this.giveaways, Product.class.getClassLoader());
        this.promotion = (TimeLimitedPromotion) parcel.readParcelable(TimeLimitedPromotion.class.getClassLoader());
        this.resourceType = parcel.readInt();
        this.subscriptionPromotion = (SubscriptionPromotion) parcel.readParcelable(SubscriptionPromotion.class.getClassLoader());
        this.attachType = parcel.readString();
        this.promotionDescVisibility = parcel.readByte() != 0;
        this.promotionDesc = parcel.readString();
        this.memberPriceRetentionVisibility = parcel.readInt() != 0;
        this.memberPriceRetentionDesc = parcel.readString();
        this.linkType = parcel.readInt();
        this.linkValue = parcel.readString();
    }
}
