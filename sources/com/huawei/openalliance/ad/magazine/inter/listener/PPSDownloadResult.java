package com.huawei.openalliance.ad.magazine.inter.listener;

/* loaded from: classes9.dex */
public class PPSDownloadResult {
    private String contentId;
    private String path;
    private boolean result;

    public void setResult(boolean z) {
        this.result = z;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setContentId(String str) {
        this.contentId = str;
    }

    public boolean isResult() {
        return this.result;
    }

    public String getPath() {
        return this.path;
    }

    public String getContentId() {
        return this.contentId;
    }

    public PPSDownloadResult(String str, boolean z, String str2) {
        this.contentId = str;
        this.result = z;
        this.path = str2;
    }

    public PPSDownloadResult() {
    }
}
