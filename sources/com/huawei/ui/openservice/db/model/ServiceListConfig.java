package com.huawei.ui.openservice.db.model;

import java.util.List;

/* loaded from: classes7.dex */
public class ServiceListConfig {
    private List<ChildService> childServiceList;
    private String module_name;
    private int module_type;
    private String module_ver;
    private List<HomePageServiceOrder> serviceHomePageCard;
    private List<OpenService> serviceList;
    private String serviceRes;
    private String serviceRoot;
    private List<OpenServiceGroup> serviceTypeList;

    public void setModuleName(String str) {
        this.module_name = str;
    }

    public String getModuleName() {
        return this.module_name;
    }

    public void setModuleVer(String str) {
        this.module_ver = str;
    }

    public String getModuleVer() {
        return this.module_ver;
    }

    public void setModuleType(int i) {
        this.module_type = i;
    }

    public int getModuleType() {
        return this.module_type;
    }

    public String getServiceRoot() {
        return this.serviceRoot;
    }

    public void setServiceRoot(String str) {
        this.serviceRoot = str;
    }

    public String getServiceRes() {
        return this.serviceRes;
    }

    public void setServiceRes(String str) {
        this.serviceRes = str;
    }

    public List<OpenServiceGroup> getServiceTypeList() {
        return this.serviceTypeList;
    }

    public void setServiceTypeList(List<OpenServiceGroup> list) {
        this.serviceTypeList = list;
    }

    public List<OpenService> getServiceList() {
        return this.serviceList;
    }

    public void setServiceList(List<OpenService> list) {
        this.serviceList = list;
    }

    public List<HomePageServiceOrder> getServiceHomePageCard() {
        return this.serviceHomePageCard;
    }

    public void setServiceHomePageCard(List<HomePageServiceOrder> list) {
        this.serviceHomePageCard = list;
    }

    public List<ChildService> getChildServiceList() {
        return this.childServiceList;
    }

    public void setChildServiceList(List<ChildService> list) {
        this.childServiceList = list;
    }
}
