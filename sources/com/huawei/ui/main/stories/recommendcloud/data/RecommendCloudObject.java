package com.huawei.ui.main.stories.recommendcloud.data;

/* loaded from: classes7.dex */
public class RecommendCloudObject {
    String downloadUrl;
    String fileId;
    String ver;

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public String getVer() {
        return this.ver;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public String toString() {
        return "RecommendCloudObject{fileId='" + this.fileId + "', ver='" + this.ver + "', downloadUrl='" + this.downloadUrl + "'}";
    }
}
