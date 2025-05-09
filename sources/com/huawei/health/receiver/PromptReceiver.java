package com.huawei.health.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bfg;
import defpackage.jdx;
import health.compact.a.CommonUtil;
import health.compact.a.DeviceUtil;

/* loaded from: classes8.dex */
public class PromptReceiver extends BroadcastReceiver {
    private boolean d;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogUtil.h("PromptReceiver", "PromptReceiver intent or context is null.");
            return;
        }
        if (!CommonUtil.bd()) {
            LogUtil.h("PromptReceiver", "PromptReceiver is other brand phone");
            return;
        }
        if (d(context)) {
            return;
        }
        try {
            if (!DeviceUtil.a()) {
                LogUtil.a("PromptReceiver", "PromptReceiver not has device");
                return;
            }
            LogUtil.a("PromptReceiver", "PromptReceiver action:", intent.getAction());
            if ("com.huawei.intelligent.action.NOTIFY_MSG".equalsIgnoreCase(intent.getAction())) {
                if (intent.hasExtra("phone_flag")) {
                    boolean booleanExtra = intent.getBooleanExtra("phone_flag", false);
                    this.d = booleanExtra;
                    LogUtil.a("PromptReceiver", "PromptReceiver mIsForbiddenPrompt:", Boolean.valueOf(booleanExtra));
                    return;
                }
                if (this.d) {
                    LogUtil.h("PromptReceiver", "PromptReceiver is synergy return");
                    return;
                }
                String stringExtra = intent.getStringExtra("type");
                String stringExtra2 = intent.getStringExtra("message_short");
                LogUtil.a("PromptReceiver", "PromptReceiver type:", stringExtra, ", messageShort:", stringExtra2);
                if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                    boolean e = NotificationContentProviderUtil.e();
                    boolean d = NotificationContentProviderUtil.d(bfg.e);
                    LogUtil.a("PromptReceiver", "PromptReceiver mHwNotificationManager isAuthorizeEnabled:", Boolean.valueOf(e), " isSwitchEnable:", Boolean.valueOf(d));
                    if (e && d) {
                        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
                            LogUtil.a("PromptReceiver", "PromptReceiver switch not on, not need start service.");
                            return;
                        } else {
                            c(context, stringExtra, stringExtra2);
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("PromptReceiver", "PromptReceiver type or messageShort is empty.");
            }
        } catch (Exception unused) {
            LogUtil.b("PromptReceiver", "onReceive exception");
        }
    }

    private boolean d(Context context) {
        Object obj;
        if (CommonUtil.bh()) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(bfg.e, 128);
                if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("oversea")) != null && TextUtils.equals(obj.toString(), "true")) {
                    LogUtil.a("PromptReceiver", "judgeAppOversea  value is true");
                    return true;
                }
            } catch (Exception unused) {
                LogUtil.b("PromptReceiver", "judgeAppOversea occur exception");
            }
        }
        LogUtil.a("PromptReceiver", "judgeAppOversea app is not oversea version");
        return false;
    }

    private void c(final Context context, final String str, final String str2) {
        jdx.b(new Runnable() { // from class: com.huawei.health.receiver.PromptReceiver.3
            @Override // java.lang.Runnable
            public void run() {
                if (DeviceUtil.a()) {
                    LogUtil.a("PromptReceiver", "startHandleIntentService have device so start PhoneService");
                    Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
                    intent.setAction("com.huawei.intelligent.action.NOTIFY_MSG");
                    intent.putExtra("type", str);
                    intent.putExtra("message_short", str2);
                    try {
                        context.startService(intent);
                    } catch (IllegalStateException | SecurityException e) {
                        LogUtil.b("PromptReceiver", "startHandleIntentService:", ExceptionUtils.d(e));
                    }
                }
            }
        });
    }
}
