package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes9.dex */
public class CouponsByproductBean {
    private List<CouponAndPrice> couponAndPrice;
    private String resultcode;
    private String resultinfo;

    public void setResultcode(String str) {
        this.resultcode = str;
    }

    public String getResultcode() {
        return this.resultcode;
    }

    public void setResultinfo(String str) {
        this.resultinfo = str;
    }

    public String getResultinfo() {
        return this.resultinfo;
    }

    public void setCouponAndPrice(List<CouponAndPrice> list) {
        this.couponAndPrice = list;
    }

    public List<CouponAndPrice> getCouponAndPrice() {
        return this.couponAndPrice;
    }

    public class CouponMap {
        private String beginTime;
        private String couponId;
        private String couponIsAvailable;
        private String couponPrice;
        private String description;
        private String descriptionEN;
        private String discount;
        private String discountType;
        private String endTime;
        private String expiredTime;
        private String fullPirce;
        private String imageUrl;
        private String leftTime;
        private String promotionId;
        private String promotionNameCN;
        private String promotionNameEN;
        private String receiveTime;
        private String serviceStartTime;
        private String status;
        private String symbol;
        private String upperValue;

        public CouponMap() {
        }

        public void setCouponId(String str) {
            this.couponId = str;
        }

        public String getCouponId() {
            return this.couponId;
        }

        public void setReceiveTime(String str) {
            this.receiveTime = str;
        }

        public String getReceiveTime() {
            return this.receiveTime;
        }

        public void setExpiredTime(String str) {
            this.expiredTime = str;
        }

        public String getExpiredTime() {
            return this.expiredTime;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescriptionEN(String str) {
            this.descriptionEN = str;
        }

        public String getDescriptionEN() {
            return this.descriptionEN;
        }

        public void setSymbol(String str) {
            this.symbol = str;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public void setPromotionNameCN(String str) {
            this.promotionNameCN = str;
        }

        public String getPromotionNameCN() {
            return this.promotionNameCN;
        }

        public void setPromotionNameEN(String str) {
            this.promotionNameEN = str;
        }

        public String getPromotionNameEN() {
            return this.promotionNameEN;
        }

        public void setBeginTime(String str) {
            this.beginTime = str;
        }

        public String getBeginTime() {
            return this.beginTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setServiceStartTime(String str) {
            this.serviceStartTime = str;
        }

        public String getServiceStartTime() {
            return this.serviceStartTime;
        }

        public void setCouponIsAvailable(String str) {
            this.couponIsAvailable = str;
        }

        public String getCouponIsAvailable() {
            return this.couponIsAvailable;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setPromotionId(String str) {
            this.promotionId = str;
        }

        public String getPromotionId() {
            return this.promotionId;
        }

        public void setDiscountType(String str) {
            this.discountType = str;
        }

        public String getDiscountType() {
            return this.discountType;
        }

        public void setDiscount(String str) {
            this.discount = str;
        }

        public String getDiscount() {
            return this.discount;
        }

        public void setCouponPrice(String str) {
            this.couponPrice = str;
        }

        public String getCouponPrice() {
            return this.couponPrice;
        }

        public void setLeftTime(String str) {
            this.leftTime = str;
        }

        public String getLeftTime() {
            return this.leftTime;
        }

        public void setFullPirce(String str) {
            this.fullPirce = str;
        }

        public String getFullPirce() {
            return this.fullPirce;
        }

        public void setUpperValue(String str) {
            this.upperValue = str;
        }

        public String getUpperValue() {
            return this.upperValue;
        }
    }

    public class CouponAndPrice {
        private CouponMap couponMap;
        private String originalPrice;
        private String realPrice;

        public CouponAndPrice() {
        }

        public void setCouponMap(CouponMap couponMap) {
            this.couponMap = couponMap;
        }

        public CouponMap getCouponMap() {
            return this.couponMap;
        }

        public void setRealPrice(String str) {
            this.realPrice = str;
        }

        public String getRealPrice() {
            return this.realPrice;
        }

        public void setOriginalPrice(String str) {
            this.originalPrice = str;
        }

        public String getOriginalPrice() {
            return this.originalPrice;
        }
    }
}
