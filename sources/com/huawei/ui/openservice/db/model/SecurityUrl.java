package com.huawei.ui.openservice.db.model;

import java.util.List;

/* loaded from: classes7.dex */
public class SecurityUrl {
    private String serviceID;
    private String url;

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String str) {
        this.serviceID = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public static boolean isEmpty(List<SecurityUrl> list) {
        return list == null || list.isEmpty();
    }
}
