package com.huawei.health.h5pro.jsbridge.system.share;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.share.Share;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.io.IOException;

/* loaded from: classes.dex */
public class ShareEntry extends JsBaseModule {
    public static Share e;

    /* renamed from: a, reason: collision with root package name */
    public Share f2421a;

    @JavascriptInterface
    public void shareText(final long j, String str) {
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "share content is empty");
            return;
        }
        Share share = this.f2421a;
        if (share == null) {
            LogUtil.w(this.TAG, "shareText: share impl is null");
        } else {
            share.shareText(str, new Share.ShareCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.share.ShareEntry.2
                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onSuccess() {
                    ShareEntry.this.onSuccessCallback(j);
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onFailure(int i, String str2) {
                    LogUtil.w(ShareEntry.this.TAG, "shareText(onFailure): " + i + " - " + str2);
                    ShareEntry.this.onFailureCallback(j, str2, i);
                }
            });
        }
    }

    @JavascriptInterface
    public void shareLink(final long j, String str) {
        LogUtil.i(this.TAG, "shareLink start");
        ShareLinkObj shareLinkObj = (ShareLinkObj) GsonUtil.parseContainsMapJson(str, ShareLinkObj.class);
        if (shareLinkObj == null) {
            LogUtil.w(this.TAG, "shareLink: shareLinkObj is null");
            return;
        }
        Share share = this.f2421a;
        if (share == null) {
            LogUtil.w(this.TAG, "shareLink: share impl is null");
        } else {
            share.shareLink(shareLinkObj, new Share.ShareCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.share.ShareEntry.4
                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onSuccess() {
                    ShareEntry.this.onSuccessCallback(j);
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onFailure(int i, String str2) {
                    ShareEntry.this.onFailureCallback(j, str2, i);
                }
            });
        }
    }

    @JavascriptInterface
    public void shareImage(final long j, String str) {
        LogUtil.i(this.TAG, "shareImage start");
        ShareImageObj shareImageObj = (ShareImageObj) GsonUtil.parseContainsMapJson(str, ShareImageObj.class);
        if (shareImageObj == null) {
            LogUtil.w(this.TAG, "shareImage: shareImageObj is null");
            return;
        }
        try {
            String webAppUriToNativePath = StorageUtil.webAppUriToNativePath(this.mContext, CommonUtil.getAppId(this.mH5ProInstance), shareImageObj.getUri());
            LogUtil.d(this.TAG, "shareImage: filePath -> " + webAppUriToNativePath);
            shareImageObj.setFilePath(webAppUriToNativePath);
            Share share = this.f2421a;
            if (share == null) {
                LogUtil.w(this.TAG, "shareImage: share impl is null");
            } else {
                share.shareImage(shareImageObj, new Share.ShareCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.share.ShareEntry.3
                    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                    public void onSuccess() {
                        ShareEntry.this.onSuccessCallback(j);
                    }

                    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                    public void onFailure(int i, String str2) {
                        LogUtil.w(ShareEntry.this.TAG, "shareImage(onFailure): " + i + " - " + str2);
                        ShareEntry.this.onFailureCallback(j, str2, i);
                    }
                });
            }
        } catch (IOException e2) {
            LogUtil.e(this.TAG, "shareImage: exception -> " + e2.getMessage());
            onFailureCallback(j, e2.getMessage());
        }
    }

    @JavascriptInterface
    public void share(final long j, String str) {
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "share: param is empty");
        } else if (!(this.f2421a instanceof ShareExtApi)) {
            LogUtil.w(this.TAG, "share: ShareExtApi Not Implemented");
        } else {
            H5ProInstance h5ProInstance = this.mH5ProInstance;
            ((ShareExtApi) this.f2421a).share(this.mContext, h5ProInstance == null ? null : h5ProInstance.getAppInfo(), str, new Share.ShareCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.share.ShareEntry.1
                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onSuccess() {
                    ShareEntry.this.onSuccessCallback(j);
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onFailure(int i, String str2) {
                    ShareEntry.this.onFailureCallback(j, str2, i);
                }
            });
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.f2421a = null;
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        Share share = e;
        if (share == null) {
            share = new AndroidShare(this.mContext);
        }
        this.f2421a = share;
    }

    public static void setShareImpl(Share share) {
        e = share;
    }

    public static Share getShareImpl() {
        return e;
    }
}
