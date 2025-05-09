package com.huawei.operation.h5pro.jsmodules.device;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.devicepair.api.MessageNotificationApi;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bgb;
import defpackage.bgf;
import java.util.List;

/* loaded from: classes.dex */
public class MessageNotificationJsApi extends JsBaseModule {
    @JavascriptInterface
    public void isSupportNotify(long j, String str) {
        MessageNotificationApi b = bgb.b();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.h(this.TAG, "isSupportNotify, device mac is empty ");
            onFailureCallback(j, "isSupportNotify error");
        } else {
            onSuccessCallback(j, Boolean.valueOf(b.isSupportNotify(deviceMac)));
        }
    }

    @JavascriptInterface
    public void queryMessageNotificationStatus(long j, String str) {
        onSuccessCallback(j, Integer.valueOf(bgb.b().queryMessageNotificationStatus()));
    }

    @JavascriptInterface
    public void setMessageNotificationStatus(long j, String str) {
        MessageNotificationApi b = bgb.b();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        String notificationEnable = DevicePairUtils.getNotificationEnable(str);
        if (TextUtils.isEmpty(deviceMac) || TextUtils.isEmpty(notificationEnable)) {
            LogUtil.h(this.TAG, "setMessageNotificationStatus, get deviceMac or enable is error");
            onFailureCallback(j, "get param error");
        } else {
            onSuccessCallback(j, Integer.valueOf(b.setMessageNotificationStatus(this.mContext, deviceMac, Integer.parseInt(notificationEnable))));
        }
    }

    @JavascriptInterface
    public void setNotificationRemindStatus(long j, String str) {
        MessageNotificationApi b = bgb.b();
        String remindMode = DevicePairUtils.getRemindMode(str);
        String remindStatus = DevicePairUtils.getRemindStatus(str);
        if (TextUtils.isEmpty(remindMode) || TextUtils.isEmpty(remindStatus)) {
            onFailureCallback(j, "get param error");
            LogUtil.h(this.TAG, "setNotificationRemindStatus, mode or status is empty");
        } else {
            onSuccessCallback(j, Integer.valueOf(b.setNotificationRemindStatus(this.mContext, Integer.parseInt(remindMode), Integer.parseInt(remindStatus)) ? 1 : 0));
        }
    }

    @JavascriptInterface
    public void queryApplicationInfoList(final long j, String str) {
        MessageNotificationApi b = bgb.b();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.h(this.TAG, "queryApplicationInfoList, device mac is empty ");
            onFailureCallback(j, "queryApplicationInfoList error");
        } else {
            b.queryApplicationInfoList(this.mContext, deviceMac, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.device.MessageNotificationJsApi.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj instanceof List) {
                        MessageNotificationJsApi.this.onSuccessCallback(j, (List) obj);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void setApplicationNotificationStatus(long j, String str) {
        LogUtil.a(this.TAG, "setApplicationNotificationStatus, param: ", str);
        MessageNotificationApi b = bgb.b();
        String pkgName = DevicePairUtils.getPkgName(str);
        String switchMode = DevicePairUtils.getSwitchMode(str);
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(pkgName) || TextUtils.isEmpty(switchMode)) {
            LogUtil.h(this.TAG, "setApplicationNotificationStatus, packageName or status is empty");
            onFailureCallback(j, "setApplicationNotificationStatus error");
        } else {
            onSuccessCallback(j, b.setApplicationNotificationStatus(this.mContext, pkgName, deviceMac, Integer.parseInt(switchMode)));
        }
    }

    @JavascriptInterface
    public void queryNotificationRemind(long j, String str) {
        MessageNotificationApi b = bgb.b();
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.h(this.TAG, "queryNotificationRemind, device mac is empty ");
            onFailureCallback(j, "queryNotificationRemind error");
        } else {
            onSuccessCallback(j, b.queryNotificationRemind(deviceMac));
        }
    }

    @JavascriptInterface
    public void getMessagePermission(long j) {
        onSuccessCallback(j, Boolean.valueOf(bgb.b().isMessagePermission(this.mContext)));
    }

    @JavascriptInterface
    public void isAppNotificationEnabled(long j, String str) {
        LogUtil.a(this.TAG, "isAppNotificationEnabled, param: ", str);
        MessageNotificationApi b = bgb.b();
        bgf bgfVar = (bgf) HiJsonUtil.e(str, bgf.class);
        if (bgfVar == null) {
            onFailureCallback(j, "get messageNotificationApp is error");
        } else {
            onSuccessCallback(j, Boolean.valueOf(b.isAppNotificationEnabled(this.mContext, bgfVar)));
        }
    }

    @JavascriptInterface
    public void enabledAppNotification(long j, String str) {
        LogUtil.a(this.TAG, "enabledAppNotification, param: ", str);
        MessageNotificationApi b = bgb.b();
        bgf bgfVar = (bgf) HiJsonUtil.e(str, bgf.class);
        if (bgfVar == null) {
            onFailureCallback(j, "get messageNotificationApp is error");
        } else {
            b.enabledAppNotification(this.mContext, bgfVar);
        }
    }
}
