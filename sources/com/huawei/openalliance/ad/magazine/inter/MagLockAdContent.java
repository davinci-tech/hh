package com.huawei.openalliance.ad.magazine.inter;

import com.huawei.openalliance.ad.hw;

/* loaded from: classes9.dex */
public interface MagLockAdContent {
    String getAdLabelStr();

    String getAdSign();

    int getAppType();

    String getContentId();

    String getContentUrl();

    String getDeeplinkUrl();

    String getDescription();

    long getEndTime();

    long getFileSize();

    String getImagePath();

    int getInteractionType();

    String getLabel();

    String getMetaData();

    String getMonitor();

    String getParamFromServer();

    String getPreviewSha256();

    String getPreviewUrl();

    String getSha256();

    String getTargetAppName();

    String getTargetPkgName();

    String getTitle();

    String getUrl();

    void setContentId(String str);

    void setDescription(String str);

    void setEndTime(long j);

    void setFileSize(long j);

    void setMetaData(String str);

    void setMonitor(String str);

    void setParamFromServer(String str);

    void setPreviewSha256(String str);

    void setPreviewUrl(String str);

    void setSha256(String str);

    void setTitle(String str);

    void setUrl(String str);

    public static final class Builder {
        public final MagLockAdContent build() {
            return new hw();
        }
    }
}
