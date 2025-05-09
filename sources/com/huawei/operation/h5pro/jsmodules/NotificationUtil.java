package com.huawei.operation.h5pro.jsmodules;

import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.operation.service.NotificationService;
import com.huawei.operation.utils.Constants;

/* loaded from: classes9.dex */
public class NotificationUtil extends JsBaseModule {
    private volatile boolean isSendNotificationMessage = false;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        stopService();
        super.onDestroy();
    }

    @JavascriptInterface
    public void sendNotificationMessage(String str, String str2) {
        LogUtil.i(this.TAG, "sendNotificationMessage");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.w(this.TAG, "message or url is null");
            return;
        }
        Intent serviceIntent = getServiceIntent();
        serviceIntent.putExtra(Constants.NOTIFICATION_MESSAGE, str);
        serviceIntent.putExtra(Constants.NOTIFICATION_URL, str2);
        this.mContext.startForegroundService(serviceIntent);
        this.isSendNotificationMessage = true;
    }

    @JavascriptInterface
    public void closeNotificationMessage() {
        LogUtil.i(this.TAG, "closeNotificationMessage");
        stopService();
    }

    private void stopService() {
        synchronized (this) {
            LogUtil.i(this.TAG, "stopService: " + this.isSendNotificationMessage);
            if (this.isSendNotificationMessage) {
                this.mContext.stopService(getServiceIntent());
                this.isSendNotificationMessage = false;
            }
        }
    }

    private Intent getServiceIntent() {
        Intent intent = new Intent();
        intent.setClass(this.mContext, NotificationService.class);
        intent.setPackage(this.mContext.getPackageName());
        return intent;
    }
}
