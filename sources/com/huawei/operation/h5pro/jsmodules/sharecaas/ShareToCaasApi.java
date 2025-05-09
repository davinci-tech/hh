package com.huawei.operation.h5pro.jsmodules.sharecaas;

import android.webkit.JavascriptInterface;
import com.google.gson.JsonSyntaxException;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.socialshare.model.CaasShareCallBack;
import health.compact.a.Services;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class ShareToCaasApi extends JsBaseModule {
    private SocialShareCenterApi mSocialShareCenterApi = null;
    private int mRetCode = -1;
    private long mSendCallbackId = 0;

    @JavascriptInterface
    public void caasInit(long j) {
        LogUtil.i(this.TAG, "caasInit start");
        if (this.mContext == null) {
            LogUtil.i(this.TAG, "context is null");
            return;
        }
        if (this.mSocialShareCenterApi == null) {
            this.mSocialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        }
        this.mSocialShareCenterApi.initCaas(this.mContext, new CaasShareCallBackImpl(this, j));
    }

    @JavascriptInterface
    public void sendShareMsgInfo(long j, String str) {
        LogUtil.i(this.TAG, "sendShareMsgInfo start " + this.mRetCode);
        this.mSendCallbackId = j;
        if (this.mSocialShareCenterApi == null || this.mRetCode != 0) {
            onFailureCallback(j, "Invoke interface 'caasInit' first.");
            return;
        }
        try {
            HwShareMsgInfo initHwShareMsgInfo = ShareToCaasUtil.initHwShareMsgInfo(str);
            if (initHwShareMsgInfo == null) {
                LogUtil.e(this.TAG, "hwShareMsgInfo is null");
                onFailureCallback(j, "Failed to parse the parameter.");
            } else if (this.mRetCode == 0) {
                this.mSocialShareCenterApi.sendShareMsgInfo(1, initHwShareMsgInfo);
            } else {
                onFailureCallback(j, "Invoke interface 'caasInit' first.");
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.e(this.TAG, "sendShareMsgInfo: JsonSyntaxException");
            onFailureCallback(j, "Failed to parse the parameter.");
        }
    }

    @JavascriptInterface
    public void release(long j) {
        LogUtil.i(this.TAG, "release start");
        SocialShareCenterApi socialShareCenterApi = this.mSocialShareCenterApi;
        if (socialShareCenterApi != null) {
            socialShareCenterApi.releaseCaas();
            this.mSocialShareCenterApi = null;
        }
        this.mRetCode = -1;
        onSuccessCallback(j);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        SocialShareCenterApi socialShareCenterApi = this.mSocialShareCenterApi;
        if (socialShareCenterApi != null) {
            socialShareCenterApi.releaseCaas();
            this.mSocialShareCenterApi = null;
        }
    }

    static class CaasShareCallBackImpl implements CaasShareCallBack {
        private static final String TAG = "CaasShareCallBackImpl";
        private final long mCallbackId;
        private final WeakReference<ShareToCaasApi> mWrShareToCaasApi;

        CaasShareCallBackImpl(ShareToCaasApi shareToCaasApi, long j) {
            this.mWrShareToCaasApi = new WeakReference<>(shareToCaasApi);
            this.mCallbackId = j;
        }

        @Override // com.huawei.health.socialshare.model.CaasShareCallBack
        public void onInitCallback(int i) {
            LogUtil.i(TAG, "onInitCallback: " + i);
            ShareToCaasApi shareToCaasApi = (ShareToCaasApi) GeneralUtil.getReferent(this.mWrShareToCaasApi);
            if (shareToCaasApi != null) {
                shareToCaasApi.mRetCode = i;
                shareToCaasApi.onSuccessCallback(this.mCallbackId, Integer.valueOf(i));
            } else {
                LogUtil.i(TAG, "onInitCallback: shareToCaasApi is null");
            }
        }

        @Override // com.huawei.health.socialshare.model.CaasShareCallBack
        public void onSendCallback(int i) {
            LogUtil.i(TAG, "onSendCallback: " + i);
            ShareToCaasApi shareToCaasApi = (ShareToCaasApi) GeneralUtil.getReferent(this.mWrShareToCaasApi);
            if (shareToCaasApi == null) {
                LogUtil.i(TAG, "onInitCallback: shareToCaasApi is null");
            } else if (i == 0) {
                shareToCaasApi.onSuccessCallback(shareToCaasApi.mSendCallbackId, Integer.valueOf(i));
            } else {
                shareToCaasApi.onFailureCallback(shareToCaasApi.mSendCallbackId, "Sending failed.", i);
            }
        }
    }
}
