package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;

/* loaded from: classes6.dex */
public class AwardRecordsBean {
    private String activityCode;
    private String activityId;
    private String activityName;
    private String awardId;
    private String awardName;
    private String awardType;
    private String description;
    private String detailUrl;
    private long effectiveEndTime;
    private long effectiveStartTime;
    private String exchangeCode;
    private String exchangeStatus;
    private long expireTime;
    private boolean isExpand;
    private int isExpire;
    private MaillingBean mailling;
    private PicturesBean pictures;
    private int source;
    private int sourceDetail;
    private String ticketType;
    private long wonTime;

    public String getActivityCode() {
        return this.activityCode;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public String getAwardId() {
        return this.awardId;
    }

    public String getAwardName() {
        return this.awardName;
    }

    public String getAwardType() {
        return this.awardType;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public long getEffectiveEndTime() {
        return this.effectiveEndTime;
    }

    public long getEffectiveStartTime() {
        return this.effectiveStartTime;
    }

    public String getExchangeStatus() {
        return this.exchangeStatus;
    }

    public String getExchangeCode() {
        return this.exchangeCode;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public int getIsExpire() {
        return this.isExpire;
    }

    public int getSource() {
        return this.source;
    }

    public String getTicketType() {
        return this.ticketType;
    }

    public long getWonTime() {
        return this.wonTime;
    }

    public int getSourceDetail() {
        return this.sourceDetail;
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    public MaillingBean getMailling() {
        return this.mailling;
    }

    public PicturesBean getPictures() {
        return this.pictures;
    }

    public void setMailling(MaillingBean maillingBean) {
        this.mailling = maillingBean;
    }

    public void setExchangeStatus(String str) {
        this.exchangeStatus = str;
    }

    public void setPictures(PicturesBean picturesBean) {
        this.pictures = picturesBean;
    }

    public void setExpand(boolean z) {
        this.isExpand = z;
    }

    /* loaded from: classes8.dex */
    public static class MaillingBean {
        private String address;
        private String name;
        private String remark;
        private String telephone;

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getTelephone() {
            return this.telephone;
        }

        public void setTelephone(String str) {
            this.telephone = str;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class PicturesBean {

        @SerializedName("BIG")
        private String big;

        @SerializedName(CommonConstant.StartQrLoginQrValue.QRSCENE_DEFAULT)
        private String defaultImg;

        @SerializedName("MIDDLE")
        private String middle;

        @SerializedName("SMALL")
        private String small;

        public String getBig() {
            return this.big;
        }

        public void setBig(String str) {
            this.big = str;
        }

        public String getSmall() {
            return this.small;
        }

        public void setSmall(String str) {
            this.small = str;
        }

        public String getMiddle() {
            return this.middle;
        }

        public void setMiddle(String str) {
            this.middle = str;
        }

        public String getDefaultImg() {
            return this.defaultImg;
        }

        public void setDefaultImg(String str) {
            this.defaultImg = str;
        }
    }

    public String toString() {
        return "AwardRecordsBean{activityCode='" + this.activityCode + "', activityId='" + this.activityId + "', activityName='" + this.activityName + "', awardId='" + this.awardId + "', awardName='" + this.awardName + "', awardType='" + this.awardType + "', description='" + this.description + "', detailUrl='" + this.detailUrl + "', effectiveEndTime=" + this.effectiveEndTime + ", effectiveStartTime=" + this.effectiveStartTime + ", exchangeStatus='" + this.exchangeStatus + "', exchangeCode='" + this.exchangeCode + "', expireTime=" + this.expireTime + ", isExpire=" + this.isExpire + ", source=" + this.source + ", ticketType='" + this.ticketType + "', wonTime=" + this.wonTime + ", sourceDetail=" + this.sourceDetail + ", isExpand=" + this.isExpand + '}';
    }
}
