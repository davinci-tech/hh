package com.huawei.health.h5pro.jsbridge.system.servicebus;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;

/* loaded from: classes3.dex */
public class ServiceBusEntry extends JsBaseModule {
    @JavascriptInterface
    public String invoke(String str, String str2, String[] strArr) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null || h5ProInstance.getAppInfo() == null) {
            LogUtil.w(this.TAG, "invoke: h5ProInstance or h5ProAppInfo is null");
            return "{}";
        }
        try {
            return H5ProServiceManager.getInstance().invoke(this.mH5ProInstance.getAppInfo(), str, str2, strArr);
        } catch (Exception e) {
            LogUtil.e(this.TAG, e.getMessage());
            throw e;
        }
    }

    @JavascriptInterface
    public void asyncInvoke(long j, String str, String str2, String[] strArr) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null || h5ProInstance.getAppInfo() == null) {
            LogUtil.w(this.TAG, "asyncInvoke: h5ProInstance or h5ProAppInfo is null");
            return;
        }
        try {
            H5ProServiceManager.getInstance().asyncInvoke(this.mH5ProInstance.getAppInfo(), this.mH5ProInstance.getJsCbkInvoker(), j, str, str2, strArr);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }
}
