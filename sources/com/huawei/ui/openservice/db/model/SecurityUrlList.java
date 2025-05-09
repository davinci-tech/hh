package com.huawei.ui.openservice.db.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class SecurityUrlList {
    private List<String> URLList;
    private String serviceID;

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String str) {
        this.serviceID = str;
    }

    public List<String> getURLList() {
        return this.URLList;
    }

    public void setURLList(List<String> list) {
        this.URLList = list;
    }

    public List<SecurityUrl> getSecurityURL() {
        List<String> list;
        if (TextUtils.isEmpty(this.serviceID) || (list = this.URLList) == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.URLList) {
            if (!TextUtils.isEmpty(str)) {
                SecurityUrl securityUrl = new SecurityUrl();
                securityUrl.setUrl(str);
                securityUrl.setServiceID(this.serviceID);
                arrayList.add(securityUrl);
            }
        }
        return arrayList;
    }
}
