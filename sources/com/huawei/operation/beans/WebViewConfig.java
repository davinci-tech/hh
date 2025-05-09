package com.huawei.operation.beans;

import java.util.List;

/* loaded from: classes5.dex */
public class WebViewConfig {
    private List<TitleBean> titleBeanConfigs;
    private UrlBean urlBean;
    private String version;

    public UrlBean getUrlBean() {
        return this.urlBean;
    }

    public void setUrlBean(UrlBean urlBean) {
        this.urlBean = urlBean;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public List<TitleBean> getTitleBeanConfigs() {
        return this.titleBeanConfigs;
    }

    public void setTitleBeanConfigs(List<TitleBean> list) {
        this.titleBeanConfigs = list;
    }
}
