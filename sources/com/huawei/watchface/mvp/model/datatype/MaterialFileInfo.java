package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes7.dex */
public class MaterialFileInfo {
    private String appContentId;
    private int contentType;
    private String fileName;
    private String fileSha256;
    private int fileSize;
    private String index;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String str) {
        this.index = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getAppContentId() {
        return this.appContentId;
    }

    public void setAppContentId(String str) {
        this.appContentId = str;
    }

    public String getFileSha256() {
        return this.fileSha256;
    }

    public void setFileSha256(String str) {
        this.fileSha256 = str;
    }

    public int getContentType() {
        return this.contentType;
    }

    public void setContentType(int i) {
        this.contentType = i;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }
}
