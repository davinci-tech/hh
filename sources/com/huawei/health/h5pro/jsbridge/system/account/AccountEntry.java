package com.huawei.health.h5pro.jsbridge.system.account;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hwcloudjs.JsClientApi;
import com.huawei.hwcloudjs.api.IAccessToken;
import com.huawei.hwcloudjs.api.TaskCallBack;
import com.huawei.hwcloudjs.service.hms.HmsCoreApi;
import com.huawei.hwcloudjs.service.hms.HmsLiteCoreApi;
import com.huawei.hwcloudjs.service.hms.bean.AccessTokenInfo;
import com.huawei.hwcloudjs.service.hms.bean.Status;

/* loaded from: classes3.dex */
public class AccountEntry extends JsBaseModule implements H5ProInstance.JsBridgeCleaner {
    public WebView c;
    public String e;

    @JavascriptInterface
    public void setAppId(String str) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "setAppId: mH5ProInstance is null");
        } else {
            h5ProInstance.getAppInfo().setAppId(str);
        }
    }

    @JavascriptInterface
    public void setAccessToken(String str) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "setAccessToken: mH5ProInstance is null");
        } else {
            h5ProInstance.setAccessToken(str);
        }
    }

    @JavascriptInterface
    public void removeAccessToken() {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "removeAccessToken: mH5ProInstance is null");
        } else {
            h5ProInstance.removeAccessToken();
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        Context context;
        int i;
        super.onCreate(h5ProBundle);
        JsClientApi.setGrsAppName(EnvironmentHelper.getInstance().getAccountGrsAppName());
        this.c = this.mH5ProInstance.getWebView();
        this.e = JsClientApi.createApi(this.c, new JsClientApi.SdkOpt.Builder().setShowAuthDlg(false).build());
        if (CommonUtil.isTestVersion()) {
            LogUtil.i(this.TAG, "test version");
            context = this.mContext;
            i = R.string._2130851627_res_0x7f02372b;
        } else {
            context = this.mContext;
            i = R.string._2130851626_res_0x7f02372a;
        }
        JsClientApi.setJsUrl(context.getString(i));
        if (EnvironmentHelper.getInstance().isUseHmsLite()) {
            JsClientApi.registerJsApi(HmsLiteCoreApi.class);
            JsClientApi.registerIAccessTokenClass(new IAccessToken() { // from class: com.huawei.health.h5pro.jsbridge.system.account.AccountEntry.1
                @Override // com.huawei.hwcloudjs.api.IAccessToken
                public void getAccessToken(TaskCallBack taskCallBack) {
                    String hostAt = EnvironmentHelper.getInstance().getHostAt();
                    Status status = new Status(0, "success");
                    status.setSuccessFlag(true);
                    taskCallBack.onResult(new AccessTokenInfo(hostAt, status));
                }
            });
        } else {
            JsClientApi.registerJsApi(HmsCoreApi.class);
        }
        this.mH5ProInstance.registerCleaner(this);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance.JsBridgeCleaner
    public void destroy() {
        try {
            JsClientApi.destroyApi(this.e);
        } catch (Throwable th) {
            LogUtil.e(this.TAG, th.getMessage());
        }
        this.c = null;
    }
}
