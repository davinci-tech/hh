package com.huawei.ui.openservice.db.model;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class SecurityUrlConfig {
    private String module_name;
    private int module_type;
    private String module_ver;
    private List<SecurityUrlList> securityURLList;

    public String getModuleName() {
        return this.module_name;
    }

    public void setModuleName(String str) {
        this.module_name = str;
    }

    public String getModuleVer() {
        return this.module_ver;
    }

    public void setModuleVer(String str) {
        this.module_ver = str;
    }

    public int getModuleType() {
        return this.module_type;
    }

    public void setModuleType(int i) {
        this.module_type = i;
    }

    public List<SecurityUrlList> getSecurityUrlList() {
        return this.securityURLList;
    }

    public void setSecurityUrlList(List<SecurityUrlList> list) {
        this.securityURLList = list;
    }

    public List<SecurityUrl> getSecurityUrl() {
        List<SecurityUrl> securityURL;
        List<SecurityUrlList> list = this.securityURLList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (SecurityUrlList securityUrlList : this.securityURLList) {
            if (securityUrlList != null && (securityURL = securityUrlList.getSecurityURL()) != null && !securityURL.isEmpty()) {
                arrayList.addAll(securityURL);
            }
        }
        return arrayList;
    }
}
