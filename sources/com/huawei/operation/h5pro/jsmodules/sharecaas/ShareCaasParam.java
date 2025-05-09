package com.huawei.operation.h5pro.jsmodules.sharecaas;

/* loaded from: classes9.dex */
class ShareCaasParam {
    public static final String SHARE_TYPE_WEBPAGE = "webPage";
    private String description;
    private String filePath;
    private String multiUrl;
    private String shareType;
    private String title;
    private String url;

    ShareCaasParam() {
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setMultiUrl(String str) {
        this.multiUrl = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setShareType(String str) {
        this.shareType = str;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMultiUrl() {
        return this.multiUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getShareType() {
        return this.shareType;
    }
}
