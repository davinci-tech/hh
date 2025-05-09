package com.huawei.ui.openservice.db.model;

import android.text.TextUtils;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hihealth.util.HiJsonUtil;

/* loaded from: classes7.dex */
public class ChildService {
    private AuthTypeList authType;
    private String description;
    private int endDate;
    private int hmsAuth;
    private String imageUrl;
    private String location;
    private int position;
    private String serviceID;
    private String serviceName;
    private int startDate;
    private String subType;
    private String url;

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String str) {
        this.serviceID = (String) ResultUtils.a(str);
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public void setStartDate(int i) {
        this.startDate = i;
    }

    public int getEndDate() {
        return this.endDate;
    }

    public void setEndDate(int i) {
        this.endDate = i;
    }

    public int getHmsAuth() {
        return this.hmsAuth;
    }

    public void setHmsAuth(int i) {
        this.hmsAuth = i;
    }

    public void setAuthType(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.authType = (AuthTypeList) HiJsonUtil.e(str, AuthTypeList.class);
    }

    public String getAuthTypeStr() {
        AuthTypeList authTypeList = this.authType;
        if (authTypeList == null) {
            return null;
        }
        return HiJsonUtil.e(authTypeList);
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = (String) ResultUtils.a(str);
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String str) {
        this.subType = str;
    }
}
