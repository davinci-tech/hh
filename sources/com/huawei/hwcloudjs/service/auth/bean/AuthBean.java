package com.huawei.hwcloudjs.service.auth.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AuthBean implements Serializable {
    private static final long serialVersionUID = 6321099514219022763L;
    private String appId;
    private long timestamp;
    private List<String> urlList = new ArrayList();
    private List<String> permissionList = new ArrayList();

    public void setUrlList(List<String> list) {
        this.urlList = list;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setPermissionList(List<String> list) {
        this.permissionList = list;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public List<String> getUrlList() {
        return this.urlList;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public List<String> getPermissionList() {
        return this.permissionList;
    }

    public String getAppId() {
        return this.appId;
    }
}
