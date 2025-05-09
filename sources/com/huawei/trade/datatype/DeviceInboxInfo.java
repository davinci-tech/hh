package com.huawei.trade.datatype;

import java.util.List;

/* loaded from: classes6.dex */
public class DeviceInboxInfo extends BaseDeviceBenefitInfo {
    private boolean autoActivate;
    private int autoActivateDelay;
    private String cardUrl;
    private String deviceInboxId;
    private List<InboxBenefitInfo> inboxBenefitInfos;
    private String inboxDescription;
    private String inboxName;
    private String inboxSubTitle;
    private String introductionUrl;
    private List<OtherServiceInfo> otherServiceList;

    public String getDeviceInboxId() {
        return this.deviceInboxId;
    }

    public void setDeviceInboxId(String str) {
        this.deviceInboxId = str;
    }

    public String getCardUrl() {
        return this.cardUrl;
    }

    public void setCardUrl(String str) {
        this.cardUrl = str;
    }

    public String getInboxName() {
        return this.inboxName;
    }

    public void setInboxName(String str) {
        this.inboxName = str;
    }

    public String getInboxSubTitle() {
        return this.inboxSubTitle;
    }

    public void setInboxSubTitle(String str) {
        this.inboxSubTitle = str;
    }

    public String getInboxDescription() {
        return this.inboxDescription;
    }

    public void setInboxDescription(String str) {
        this.inboxDescription = str;
    }

    public String getIntroductionUrl() {
        return this.introductionUrl;
    }

    public void setIntroductionUrl(String str) {
        this.introductionUrl = str;
    }

    public List<InboxBenefitInfo> getInboxBenefitInfos() {
        return this.inboxBenefitInfos;
    }

    public void setInboxBenefitInfos(List<InboxBenefitInfo> list) {
        this.inboxBenefitInfos = list;
    }

    public boolean isAutoActivate() {
        return this.autoActivate;
    }

    public void setAutoActivate(boolean z) {
        this.autoActivate = z;
    }

    public int getAutoActivateDelay() {
        return this.autoActivateDelay;
    }

    public void setAutoActivateDelay(int i) {
        this.autoActivateDelay = i;
    }

    public List<OtherServiceInfo> getOtherServiceList() {
        return this.otherServiceList;
    }

    public void setOtherServiceList(List<OtherServiceInfo> list) {
        this.otherServiceList = list;
    }

    public String toString() {
        return "DeviceInboxInfo{deviceInboxId='" + this.deviceInboxId + "', inboxName='" + this.inboxName + "', inboxSubTitle='" + this.inboxSubTitle + "', inboxDescription='" + this.inboxDescription + "', cardUrl='" + this.cardUrl + "', introductionUrl='" + this.introductionUrl + "', inboxBenefitInfos=" + this.inboxBenefitInfos + ", autoActivate=" + this.autoActivate + ", autoActivateDelay=" + this.autoActivateDelay + ", deviceType='" + this.deviceType + "', productId='" + this.productId + "', benefitType=" + this.benefitType + ", linkType=" + this.linkType + ", linkValue='" + this.linkValue + "', timeZone='" + this.timeZone + "', effectiveStartTime=" + this.effectiveStartTime + ", effectiveEndTime=" + this.effectiveEndTime + ", activeStatus=" + this.activeStatus + ", pageTitle='" + this.pageTitle + "', pageDesc='" + this.pageDesc + "', deviceName='" + this.deviceName + "', serviceDescPicture=" + this.serviceDescPicture + ", validateType=" + this.validateType + ", otherServiceList=" + this.otherServiceList + '}';
    }
}
