package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.Coupon;
import java.util.List;

/* loaded from: classes3.dex */
public class PopUpCombinationTemplate {
    private String buttonName;
    private String buttonPicture;
    private List<Coupon> coupon;
    private String picture;
    private String vibesPicture;
    private String viewButtonName;

    public String getButtonName() {
        return this.buttonName;
    }

    public String getViewButtonName() {
        return this.viewButtonName;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getVibesPicture() {
        return this.vibesPicture;
    }

    public void setButtonName(String str) {
        this.buttonName = str;
    }

    public void setViewButtonName(String str) {
        this.viewButtonName = str;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public void setVibesPicture(String str) {
        this.vibesPicture = str;
    }

    public List<Coupon> getCoupon() {
        return this.coupon;
    }

    public void setCoupon(List<Coupon> list) {
        this.coupon = list;
    }

    public String getButtonPicture() {
        return this.buttonPicture;
    }

    public void setButtonPicture(String str) {
        this.buttonPicture = str;
    }

    public String toString() {
        return "PopUpCombinationTemplate{buttonName='" + this.buttonName + "', viewButtonName='" + this.viewButtonName + "', picture='" + this.picture + "', vibesPicture='" + this.vibesPicture + "', coupon=" + this.coupon + '}';
    }
}
