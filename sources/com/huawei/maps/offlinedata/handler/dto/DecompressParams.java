package com.huawei.maps.offlinedata.handler.dto;

/* loaded from: classes9.dex */
public class DecompressParams {
    private boolean isDeleteOld;
    private boolean isDeleteZip;
    private String targetPath;
    private String zipFile;

    public String getZipFile() {
        return this.zipFile;
    }

    public void setZipFile(String str) {
        this.zipFile = str;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public void setTargetPath(String str) {
        this.targetPath = str;
    }

    public boolean isDeleteOld() {
        return this.isDeleteOld;
    }

    public void setDeleteOld(boolean z) {
        this.isDeleteOld = z;
    }

    public boolean isDeleteZip() {
        return this.isDeleteZip;
    }

    public void setDeleteZip(boolean z) {
        this.isDeleteZip = z;
    }
}
