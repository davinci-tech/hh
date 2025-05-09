package com.huawei.phoneservice.feedbackcommon.entity;

/* loaded from: classes9.dex */
public class FeedbackZipBean {
    private String filename;
    private String url;

    public void setUrl(String str) {
        this.url = str;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFilename() {
        return this.filename;
    }

    public FeedbackZipBean(String str, String str2) {
        this.filename = str;
        this.url = str2;
    }
}
