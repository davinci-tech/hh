package com.huawei.health.h5pro.jsbridge.system.shareplus;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import java.io.IOException;

/* loaded from: classes3.dex */
public class SharePlusEntry extends JsBaseModule {
    public SharePlus b;

    @JavascriptInterface
    public void shareFile(long j, String str) {
        try {
            SharePlusParam sharePlusParam = (SharePlusParam) GsonUtil.parseContainsMapJson(str, SharePlusParam.class);
            if (!TextUtils.isEmpty(sharePlusParam.getUri()) && this.mContext != null) {
                sharePlusParam.setFilePath(StorageUtil.webAppUriToNativePath(this.mContext, b(), sharePlusParam.getUri()));
            }
            SharePlus sharePlus = this.b;
            if (sharePlus == null) {
                LogUtil.w(this.TAG, "sharePlus is null");
                return;
            }
            try {
                sharePlus.shareFile(sharePlusParam);
                onSuccessCallback(j);
            } catch (Exception e) {
                onFailureCallback(j, "share file fail:" + e.getMessage());
            }
        } catch (IOException unused) {
            onFailureCallback(j, "share file fail:param invalid");
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        SharePlus sharePlus = this.b;
        if (sharePlus != null) {
            sharePlus.destroy();
            this.b = null;
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.b = new SharePlusImpl(this.mContext, this.mH5ProInstance);
    }

    private String b() {
        return CommonUtil.getAppId(this.mH5ProInstance);
    }
}
