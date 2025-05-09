package defpackage;

import android.content.Intent;
import android.webkit.JavascriptInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes8.dex */
public class sie extends JsBaseModule {
    @JavascriptInterface
    public void onAuthCodeResponse(String str) {
        if (!CommonUtil.bv()) {
            LogUtil.c(this.TAG, "onAuthCodeResponse serverAuthCode = " + str);
        }
        Intent intent = new Intent("com.huawei.ui.thirdpartservice.SILENT_LOGIN_JS_RESPONSE");
        intent.putExtra("serverAuthCode", str);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c(this.TAG, "onDestroy");
    }
}
