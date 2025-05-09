package com.huawei.operation.js;

import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.VmallLoginCallback;
import com.huawei.operation.adapter.VmallLoginResultEntity;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class InJavaScriptLocalObj {
    private static final String TAG = "PluginOperation_[Operation Version 1.2]InJavaScriptLocalObj";
    private VmallLoginCallback mVmallLoginCallback;

    public void setVmallLoginCallback(VmallLoginCallback vmallLoginCallback) {
        this.mVmallLoginCallback = vmallLoginCallback;
    }

    @JavascriptInterface
    public void showSource(String str) {
        VmallLoginResultEntity vmallLoginResultEntity = (VmallLoginResultEntity) new Gson().fromJson(CommonUtil.z(str), VmallLoginResultEntity.class);
        if (vmallLoginResultEntity == null) {
            LogUtil.b(TAG, "login cas entity is null!");
            return;
        }
        if (!vmallLoginResultEntity.isSuccess()) {
            LogUtil.b(TAG, "login cas error");
            return;
        }
        LogUtil.a(TAG, "login cas isSuccess");
        VmallLoginCallback vmallLoginCallback = this.mVmallLoginCallback;
        if (vmallLoginCallback != null) {
            vmallLoginCallback.onVmallLoginCallback(0, "");
        }
    }
}
