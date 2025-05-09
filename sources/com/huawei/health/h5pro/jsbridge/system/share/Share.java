package com.huawei.health.h5pro.jsbridge.system.share;

/* loaded from: classes.dex */
public interface Share {

    /* loaded from: classes8.dex */
    public interface ShareCallback {
        void onFailure(int i, String str);

        void onSuccess();
    }

    void shareImage(ShareImageObj shareImageObj, ShareCallback shareCallback);

    void shareLink(ShareLinkObj shareLinkObj, ShareCallback shareCallback);

    void shareText(String str, ShareCallback shareCallback);
}
