package com.huawei.health.marketing.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SingleGridContent extends CornerTemplate {
    public static final Parcelable.Creator<SingleGridContent> CREATOR = new Parcelable.Creator<SingleGridContent>() { // from class: com.huawei.health.marketing.datatype.SingleGridContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SingleGridContent createFromParcel(Parcel parcel) {
            return new SingleGridContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SingleGridContent[] newArray(int i) {
            return new SingleGridContent[i];
        }
    };
    private static final String TAG = "SingleGridContent";
    private String auditionDetail;
    private String auditionDetailUrl;
    private List<String> auditionLabel;
    private String auditionLink;
    private boolean auditionVisibility;
    private List<String> avatarUrlList;
    private String backgroundColor;
    private String beginDate;
    private int costTime;
    private boolean couponIdVisibility;
    private String description;
    private boolean descriptionVisibility;
    private String detailPicture;
    private String dynamicDataId;
    private String endDate;
    private long favoriteTime;
    private int heatCount;
    private String infoType;
    private int isPay;
    private String itemCategory;
    private long itemEffectiveTime;
    private long itemExpirationTime;
    private int itemPosition;
    private String labelGroupId;
    private int labelType;
    private String linkValue;
    private String logicControlCharacter;
    private String marketingIcon;
    private int messageCount;
    private int numberOfPeople;
    private long participantCount;
    private String picture;
    private String playPercent;
    private String price;
    private int priority;
    private int questionNumber;
    private String recommendCard;
    private String redDotFlag;
    private boolean showLogicSwitch;
    private String statistic;
    private String tahitiPicture;
    private String theme;
    private boolean themeVisibility;
    private int timePeriodIdx;
    private String timePeriodInfo;
    private String type;
    private int vip;

    public List<String> getAvatarUrlList() {
        return this.avatarUrlList;
    }

    public void setAvatarUrlList(List<String> list) {
        this.avatarUrlList = list;
    }

    public String getStatistic() {
        return this.statistic;
    }

    public void setStatistic(String str) {
        this.statistic = str;
    }

    public String getTimePeriodInfo() {
        return this.timePeriodInfo;
    }

    public void setTimePeriodInfo(String str) {
        this.timePeriodInfo = str;
    }

    public List<TimePeriod> getTimePeriodList() {
        if (this.timePeriodInfo == null) {
            return new ArrayList();
        }
        try {
            return (List) new Gson().fromJson(this.timePeriodInfo, TypeToken.getParameterized(List.class, TimePeriod.class).getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.a(TAG, "Json parse failed, source: " + this.timePeriodInfo);
            return null;
        }
    }

    public SingleGridContent(Builder builder) {
        super(builder);
        this.priority = builder.priority;
        this.theme = builder.theme;
        this.themeVisibility = builder.themeVisibility;
        this.description = builder.description;
        this.descriptionVisibility = builder.descriptionVisibility;
        this.dynamicDataId = builder.dynamicDataId;
        this.picture = builder.picture;
        this.tahitiPicture = builder.tahitiPicture;
        this.linkValue = builder.linkValue;
        this.marketingIcon = builder.marketingIcon;
        this.labelGroupId = builder.labelGroupId;
        this.itemCategory = builder.itemCategory;
        this.itemEffectiveTime = builder.itemEffectiveTime;
        this.itemExpirationTime = builder.itemExpirationTime;
        this.extend = builder.extend;
        this.detailPicture = builder.detailPicture;
        this.questionNumber = builder.questionNumber;
        this.costTime = builder.costTime;
        this.participantCount = builder.participantCount;
        this.messageCount = builder.messageCount;
        this.redDotFlag = builder.redDotFlag;
        this.backgroundColor = builder.backgroundColor;
        this.price = builder.price;
        this.playPercent = builder.playPercent;
        this.isPay = builder.isPay;
        this.infoType = builder.infoType;
        this.couponIdVisibility = builder.couponIdVisibility;
        this.recommendCard = builder.recommendCard;
    }

    protected SingleGridContent(Parcel parcel) {
        super(parcel);
        this.priority = parcel.readInt();
        this.theme = parcel.readString();
        this.themeVisibility = parcel.readByte() != 0;
        this.description = parcel.readString();
        this.descriptionVisibility = parcel.readByte() != 0;
        this.dynamicDataId = parcel.readString();
        this.picture = parcel.readString();
        this.tahitiPicture = parcel.readString();
        this.detailPicture = parcel.readString();
        this.linkValue = parcel.readString();
        this.marketingIcon = parcel.readString();
        this.labelGroupId = parcel.readString();
        this.itemCategory = parcel.readString();
        this.questionNumber = parcel.readInt();
        this.costTime = parcel.readInt();
        this.participantCount = parcel.readLong();
        this.itemEffectiveTime = parcel.readLong();
        this.itemExpirationTime = parcel.readLong();
        this.beginDate = parcel.readString();
        this.endDate = parcel.readString();
        this.numberOfPeople = parcel.readInt();
        this.heatCount = parcel.readInt();
        this.labelType = parcel.readInt();
        this.vip = parcel.readInt();
        this.auditionVisibility = parcel.readByte() != 0;
        this.auditionLink = parcel.readString();
        this.auditionLabel = parcel.createStringArrayList();
        this.auditionDetailUrl = parcel.readString();
        this.auditionDetail = parcel.readString();
        this.favoriteTime = parcel.readLong();
        this.messageCount = parcel.readInt();
        this.redDotFlag = parcel.readString();
        this.backgroundColor = parcel.readString();
        this.type = parcel.readString();
        this.price = parcel.readString();
        this.timePeriodIdx = parcel.readInt();
        this.playPercent = parcel.readString();
        this.infoType = parcel.readString();
        this.isPay = parcel.readInt();
        this.couponIdVisibility = parcel.readByte() != 0;
        this.showLogicSwitch = parcel.readByte() != 0;
        this.logicControlCharacter = parcel.readString();
        this.corner = parcel.readString();
        this.recommendCard = parcel.readString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SingleGridContent)) {
            return false;
        }
        SingleGridContent singleGridContent = (SingleGridContent) obj;
        return getPriority() == singleGridContent.getPriority() && isThemeVisibility() == singleGridContent.isThemeVisibility() && getDescriptionVisibility() == singleGridContent.getDescriptionVisibility() && getQuestionNumber() == singleGridContent.getQuestionNumber() && getCostTime() == singleGridContent.getCostTime() && getParticipantCount() == singleGridContent.getParticipantCount() && getItemEffectiveTime() == singleGridContent.getItemEffectiveTime() && getItemExpirationTime() == singleGridContent.getItemExpirationTime() && getNumberOfPeople() == singleGridContent.getNumberOfPeople() && getHeatCount() == singleGridContent.getHeatCount() && getLabelType() == singleGridContent.getLabelType() && getVip() == singleGridContent.getVip() && isAuditionVisibility() == singleGridContent.isAuditionVisibility() && getFavoriteTime() == singleGridContent.getFavoriteTime() && getMessageCount() == singleGridContent.getMessageCount() && getTimePeriodIdx() == singleGridContent.getTimePeriodIdx() && getIsPay() == singleGridContent.getIsPay() && isCouponIdVisibility() == singleGridContent.isCouponIdVisibility() && Objects.equals(getTheme(), singleGridContent.getTheme()) && Objects.equals(getDescription(), singleGridContent.getDescription()) && Objects.equals(getDynamicDataId(), singleGridContent.getDynamicDataId()) && Objects.equals(getPicture(), singleGridContent.getPicture()) && Objects.equals(getDetailPicture(), singleGridContent.getDetailPicture()) && Objects.equals(getLinkValue(), singleGridContent.getLinkValue()) && Objects.equals(getMarketingIcon(), singleGridContent.getMarketingIcon()) && Objects.equals(getLabelGroupId(), singleGridContent.getLabelGroupId()) && Objects.equals(getItemCategory(), singleGridContent.getItemCategory()) && Objects.equals(getItemId(), singleGridContent.getItemId()) && Objects.equals(getBeginDate(), singleGridContent.getBeginDate()) && Objects.equals(getEndDate(), singleGridContent.getEndDate()) && Objects.equals(getAuditionLink(), singleGridContent.getAuditionLink()) && Objects.equals(getAuditionLabel(), singleGridContent.getAuditionLabel()) && Objects.equals(getAuditionDetailUrl(), singleGridContent.getAuditionDetailUrl()) && Objects.equals(getAuditionDetail(), singleGridContent.getAuditionDetail()) && Objects.equals(getRedDotFlag(), singleGridContent.getRedDotFlag()) && Objects.equals(getBackgroundColor(), singleGridContent.getBackgroundColor()) && Objects.equals(getType(), singleGridContent.getType()) && Objects.equals(getPrice(), singleGridContent.getPrice()) && Objects.equals(getPlayPercent(), singleGridContent.getPlayPercent()) && Objects.equals(getInfoType(), singleGridContent.getInfoType()) && Objects.equals(getRecommendCard(), singleGridContent.getRecommendCard());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getPriority()), getTheme(), Boolean.valueOf(isThemeVisibility()), getDescription(), Boolean.valueOf(getDescriptionVisibility()), getDynamicDataId(), getPicture(), getDetailPicture(), getLinkValue(), getMarketingIcon(), getLabelGroupId(), getItemCategory(), Integer.valueOf(getQuestionNumber()), Integer.valueOf(getCostTime()), Long.valueOf(getParticipantCount()), Long.valueOf(getItemEffectiveTime()), Long.valueOf(getItemExpirationTime()), getItemId(), getBeginDate(), getEndDate(), Integer.valueOf(getNumberOfPeople()), Integer.valueOf(getHeatCount()), Integer.valueOf(getLabelType()), Integer.valueOf(getVip()), Boolean.valueOf(isAuditionVisibility()), getAuditionLink(), getAuditionLabel(), getAuditionDetailUrl(), getAuditionDetail(), Long.valueOf(getFavoriteTime()), Integer.valueOf(getMessageCount()), getRedDotFlag(), getBackgroundColor(), getType(), getPrice(), Integer.valueOf(getTimePeriodIdx()), getPlayPercent(), getInfoType(), Integer.valueOf(getIsPay()), Boolean.valueOf(isCouponIdVisibility()), getRecommendCard());
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SingleGridContent m425clone() {
        return new Builder().setBackgroundColor(this.backgroundColor).setCostTime(this.costTime).setCouponIdVisibility(this.couponIdVisibility).setDescription(this.description).setDescriptionVisibility(Boolean.valueOf(this.descriptionVisibility)).setDetailPicture(this.detailPicture).setDynamicDataId(this.dynamicDataId).setExtend(this.extend).setInfoType(this.infoType).setPriority(this.priority).setIsPay(this.isPay).setItemCategory(this.itemCategory).setItemEffectiveTime(this.itemEffectiveTime).setItemExpirationTime(this.itemExpirationTime).setItemId(getItemId()).setLabelGroupId(this.labelGroupId).setLinkValue(this.linkValue).setMarketingIcon(this.marketingIcon).setParticipantCount(this.participantCount).setPicture(this.picture).setTahitiPicture(this.tahitiPicture).setPlayPercent(this.playPercent).setPrice(this.price).setQuestionNumber(this.questionNumber).setTheme(this.theme).setThemeVisibility(this.themeVisibility).setType(this.type).setRedDotFlag(this.redDotFlag).setMessageCount(this.messageCount).setRecommendCard(this.recommendCard).build();
    }

    @Override // com.huawei.health.marketing.datatype.templates.CornerTemplate, com.huawei.health.marketing.datatype.ColumRecommendInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.priority);
        parcel.writeString(this.theme);
        parcel.writeByte(this.themeVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.description);
        parcel.writeByte(this.descriptionVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.dynamicDataId);
        parcel.writeString(this.picture);
        parcel.writeString(this.tahitiPicture);
        parcel.writeString(this.detailPicture);
        parcel.writeString(this.linkValue);
        parcel.writeString(this.marketingIcon);
        parcel.writeString(this.labelGroupId);
        parcel.writeString(this.itemCategory);
        parcel.writeInt(this.questionNumber);
        parcel.writeInt(this.costTime);
        parcel.writeLong(this.participantCount);
        parcel.writeLong(this.itemEffectiveTime);
        parcel.writeLong(this.itemExpirationTime);
        parcel.writeString(this.beginDate);
        parcel.writeString(this.endDate);
        parcel.writeInt(this.numberOfPeople);
        parcel.writeInt(this.heatCount);
        parcel.writeInt(this.labelType);
        parcel.writeInt(this.vip);
        parcel.writeByte(this.auditionVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.auditionLink);
        parcel.writeStringList(this.auditionLabel);
        parcel.writeString(this.auditionDetailUrl);
        parcel.writeString(this.auditionDetail);
        parcel.writeLong(this.favoriteTime);
        parcel.writeInt(this.messageCount);
        parcel.writeString(this.redDotFlag);
        parcel.writeString(this.backgroundColor);
        parcel.writeString(this.type);
        parcel.writeString(this.price);
        parcel.writeInt(this.timePeriodIdx);
        parcel.writeString(this.playPercent);
        parcel.writeString(this.infoType);
        parcel.writeInt(this.isPay);
        parcel.writeByte(this.couponIdVisibility ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showLogicSwitch ? (byte) 1 : (byte) 0);
        parcel.writeString(this.logicControlCharacter);
        parcel.writeString(this.corner);
        parcel.writeString(this.recommendCard);
    }

    public static class Builder extends ColumRecommendInfo.Builder {
        private String auditionDetailUrl;
        private String auditionLink;
        private String backgroundColor;
        private int costTime;
        private boolean couponIdVisibility;
        private String description;
        private boolean descriptionVisibility;
        private String detailPicture;
        private String dynamicDataId;
        private String extend;
        private String infoType;
        private int isPay;
        private String itemCategory;
        private long itemEffectiveTime;
        private long itemExpirationTime;
        private String labelGroupId;
        private String linkValue;
        private String marketingIcon;
        private int messageCount;
        private long participantCount;
        private String picture;
        private String playPercent;
        private String price;
        private int priority;
        private int questionNumber;
        private String recommendCard;
        private String redDotFlag;
        private String tahitiPicture;
        private String theme;
        private boolean themeVisibility;
        private String type;

        public Builder setPriority(int i) {
            this.priority = i;
            return this;
        }

        public Builder setTheme(String str) {
            this.theme = str;
            return this;
        }

        public Builder setAuditionDetailUrl(String str) {
            this.auditionDetailUrl = str;
            return this;
        }

        public Builder setThemeVisibility(boolean z) {
            this.themeVisibility = z;
            return this;
        }

        public Builder setInfoType(String str) {
            this.infoType = str;
            return this;
        }

        public Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder setDescriptionVisibility(Boolean bool) {
            this.descriptionVisibility = bool.booleanValue();
            return this;
        }

        public Builder setDynamicDataId(String str) {
            this.dynamicDataId = str;
            return this;
        }

        public Builder setPicture(String str) {
            this.picture = str;
            return this;
        }

        public Builder setTahitiPicture(String str) {
            this.tahitiPicture = str;
            return this;
        }

        public Builder setLinkValue(String str) {
            this.linkValue = str;
            return this;
        }

        public Builder setPrice(String str) {
            this.price = str;
            return this;
        }

        public Builder setPlayPercent(String str) {
            this.playPercent = str;
            return this;
        }

        public Builder setIsPay(int i) {
            this.isPay = i;
            return this;
        }

        public Builder setAuditionLink(String str) {
            this.auditionLink = str;
            return this;
        }

        public Builder setMarketingIcon(String str) {
            this.marketingIcon = str;
            return this;
        }

        public Builder setLabelGroupId(String str) {
            this.labelGroupId = str;
            return this;
        }

        public Builder setItemCategory(String str) {
            this.itemCategory = str;
            return this;
        }

        public Builder setItemEffectiveTime(long j) {
            this.itemEffectiveTime = j;
            return this;
        }

        public Builder setItemExpirationTime(long j) {
            this.itemExpirationTime = j;
            return this;
        }

        @Override // com.huawei.health.marketing.datatype.ColumRecommendInfo.Builder
        public Builder setItemId(String str) {
            super.setItemId(str);
            return this;
        }

        public Builder setExtend(String str) {
            this.extend = str;
            return this;
        }

        public Builder setDetailPicture(String str) {
            this.detailPicture = str;
            return this;
        }

        public Builder setQuestionNumber(int i) {
            this.questionNumber = i;
            return this;
        }

        public Builder setCostTime(int i) {
            this.costTime = i;
            return this;
        }

        public Builder setParticipantCount(long j) {
            this.participantCount = j;
            return this;
        }

        public Builder setMessageCount(int i) {
            this.messageCount = i;
            return this;
        }

        public Builder setRedDotFlag(String str) {
            this.redDotFlag = str;
            return this;
        }

        public Builder setBackgroundColor(String str) {
            this.backgroundColor = str;
            return this;
        }

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder setCouponIdVisibility(boolean z) {
            this.couponIdVisibility = z;
            return this;
        }

        public Builder setRecommendCard(String str) {
            this.recommendCard = str;
            return this;
        }

        @Override // com.huawei.health.marketing.datatype.ColumRecommendInfo.Builder
        public SingleGridContent build() {
            return new SingleGridContent(this);
        }
    }

    public int getPriority() {
        return this.priority;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public boolean getDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public String getInfoType() {
        return this.infoType;
    }

    public void setInfoType(String str) {
        this.infoType = str;
    }

    public String getDynamicDataId() {
        return this.dynamicDataId;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getMarketingIcon() {
        return this.marketingIcon;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getLabelGroupId() {
        return this.labelGroupId;
    }

    public String getItemCategory() {
        return this.itemCategory;
    }

    public long getItemEffectiveTime() {
        return this.itemEffectiveTime;
    }

    public long getItemExpirationTime() {
        return this.itemExpirationTime;
    }

    public String getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(String str) {
        this.beginDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int i) {
        this.numberOfPeople = i;
    }

    public int getHeatCount() {
        return this.heatCount;
    }

    public void setHeatCount(int i) {
        this.heatCount = i;
    }

    public int getLabelType() {
        return this.labelType;
    }

    public void setLabelType(int i) {
        this.labelType = i;
    }

    public int getVip() {
        return this.vip;
    }

    public void setVip(int i) {
        this.vip = i;
    }

    public boolean isAuditionVisibility() {
        return this.auditionVisibility;
    }

    public String getAuditionLink() {
        return this.auditionLink;
    }

    public void setAuditionLink(String str) {
        this.auditionLink = str;
    }

    public List<String> getAuditionLabel() {
        return this.auditionLabel;
    }

    public String getAuditionDetailUrl() {
        return this.auditionDetailUrl;
    }

    public void setAuditionDetailUrl(String str) {
        this.auditionDetailUrl = str;
    }

    public String getAuditionDetail() {
        return this.auditionDetail;
    }

    public long getFavoriteTime() {
        return this.favoriteTime;
    }

    public void setFavoriteTime(long j) {
        this.favoriteTime = j;
    }

    public String getDetailPicture() {
        return this.detailPicture;
    }

    public int getQuestionNumber() {
        return this.questionNumber;
    }

    public int getCostTime() {
        return this.costTime;
    }

    public void setCostTime(int i) {
        this.costTime = i;
    }

    public long getParticipantCount() {
        return this.participantCount;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getPlayPercent() {
        return this.playPercent;
    }

    public void setPlayPercent(String str) {
        this.playPercent = str;
    }

    public int getIsPay() {
        return this.isPay;
    }

    public void setIsPay(int i) {
        this.isPay = i;
    }

    public void setMessageCount(int i) {
        this.messageCount = i;
    }

    public int getMessageCount() {
        return this.messageCount;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getTimePeriodIdx() {
        return this.timePeriodIdx;
    }

    public void setTimePeriodIdx(int i) {
        this.timePeriodIdx = i;
    }

    public void setRedDotFlag(String str) {
        this.redDotFlag = str;
    }

    public String getRedDotFlag() {
        return this.redDotFlag;
    }

    public boolean isCouponIdVisibility() {
        return this.couponIdVisibility;
    }

    public int getItemPosition() {
        return this.itemPosition;
    }

    public void setItemPosition(int i) {
        this.itemPosition = i;
    }

    public String getTahitiPicture() {
        return this.tahitiPicture;
    }

    public void setTahitiPicture(String str) {
        this.tahitiPicture = str;
    }

    public boolean isShowLogicSwitch() {
        return this.showLogicSwitch;
    }

    public String getLogicControlCharacter() {
        return this.logicControlCharacter;
    }

    public String getRecommendCard() {
        return this.recommendCard;
    }

    @Override // com.huawei.health.marketing.datatype.templates.CornerTemplate, com.huawei.health.marketing.datatype.ColumRecommendInfo
    public String toString() {
        return "SingleGridContent{priority=" + this.priority + ", theme='" + this.theme + "', themeVisibility=" + this.themeVisibility + ", description='" + this.description + "', descriptionVisibility=" + this.descriptionVisibility + ", dynamicDataId='" + this.dynamicDataId + "', picture='" + this.picture + "', tahitiPicture='" + this.tahitiPicture + "', detailPicture='" + this.detailPicture + "', linkValue='" + this.linkValue + "', marketingIcon='" + this.marketingIcon + "', labelGroupId='" + this.labelGroupId + "', itemCategory='" + this.itemCategory + "', questionNumber=" + this.questionNumber + ", costTime=" + this.costTime + ", participantCount=" + this.participantCount + ", itemEffectiveTime=" + this.itemEffectiveTime + ", itemExpirationTime=" + this.itemExpirationTime + ", beginDate='" + this.beginDate + "', endDate='" + this.endDate + "', numberOfPeople=" + this.numberOfPeople + ", heatCount=" + this.heatCount + ", labelType=" + this.labelType + ", vip=" + this.vip + ", auditionVisibility=" + this.auditionVisibility + ", auditionUrl='" + this.auditionLink + "', auditionLabel=" + this.auditionLabel + ", auditionDetailUrl='" + this.auditionDetailUrl + "', auditionDetail='" + this.auditionDetail + "', favoriteTime=" + this.favoriteTime + ", messageCount=" + this.messageCount + ", redDotFlag='" + this.redDotFlag + "', backgroundColor='" + this.backgroundColor + "', type='" + this.type + "', price='" + this.price + "', timePeriodIdx=" + this.timePeriodIdx + ", playPercent='" + this.playPercent + "', infoType='" + this.infoType + "', isPay=" + this.isPay + ", couponIdVisibility=" + this.couponIdVisibility + ", showLogicSwitch=" + this.showLogicSwitch + ", logicControlCharacter=" + this.logicControlCharacter + ", recommendCard='" + this.recommendCard + '\'' + super.toString() + '}';
    }
}
