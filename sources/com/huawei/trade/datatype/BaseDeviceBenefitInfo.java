package com.huawei.trade.datatype;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class BaseDeviceBenefitInfo {
    private static final String TAG = "BaseDeviceBenefitInfo";
    protected int activeStatus;
    protected int benefitType;
    protected String deviceName;
    protected String deviceType;
    protected long effectiveEndTime;
    protected long effectiveStartTime;
    protected int linkType;
    protected String linkValue;
    protected String pageDesc;
    protected String pageTitle;
    protected String productId;
    protected List<PictureWithLink> serviceDescPicture;
    protected String timeZone;
    protected int validateType;

    public boolean isBenefitInfoValid() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a(TAG, "pageTitle = ", this.pageTitle, ", benefitType = ", Integer.valueOf(this.benefitType), ", effectiveStartTime = ", Long.valueOf(this.effectiveStartTime), "; effectiveEndTime = ", Long.valueOf(this.effectiveEndTime), "; activeStatus = ", Integer.valueOf(this.activeStatus));
        return currentTimeMillis >= this.effectiveStartTime && currentTimeMillis <= this.effectiveEndTime && this.activeStatus == 1;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public long getEffectiveStartTime() {
        return this.effectiveStartTime;
    }

    public void setEffectiveStartTime(long j) {
        this.effectiveStartTime = j;
    }

    public long getEffectiveEndTime() {
        return this.effectiveEndTime;
    }

    public void setEffectiveEndTime(long j) {
        this.effectiveEndTime = j;
    }

    public int getActiveStatus() {
        return this.activeStatus;
    }

    public void setActiveStatus(int i) {
        this.activeStatus = i;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public int getBenefitType() {
        return this.benefitType;
    }

    public void setBenefitType(int i) {
        this.benefitType = i;
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public void setPageTitle(String str) {
        this.pageTitle = str;
    }

    public String getPageDesc() {
        return this.pageDesc;
    }

    public void setPageDesc(String str) {
        this.pageDesc = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public List<PictureWithLink> getServiceDescPicture() {
        return this.serviceDescPicture;
    }

    public void setServiceDescPicture(List<PictureWithLink> list) {
        this.serviceDescPicture = list;
    }

    public int getValidateType() {
        return this.validateType;
    }

    public void setValidateType(int i) {
        this.validateType = i;
    }
}
