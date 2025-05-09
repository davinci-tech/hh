package com.huawei.ui.device.activity.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jqi;
import defpackage.nwy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class NotifyReminderSwitchListener implements CompoundButton.OnCheckedChangeListener {
    private Context b;
    private String d;

    public NotifyReminderSwitchListener(Context context, String str) {
        this.d = str;
        this.b = context;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if ("switch_silent_notify_using_phone".equals(this.d) || "switch_smart_notify_reminder".equals(this.d)) {
            a(this.d, z);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void a(final String str, boolean z) {
        String str2 = z ? "1" : "0";
        LogUtil.a("NotifyReminderSwitchListener", "switchChanged switchType:", str, " switchStatus:", str2);
        jqi.a().setSwitchSetting(str, str2, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.notification.NotifyReminderSwitchListener.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_NotifyReminderSwitchListener", "switchChanged switchType:", str, " errorCode:", Integer.valueOf(i));
                NotifyReminderSwitchListener.this.c();
                if ("switch_smart_notify_reminder".equals(NotifyReminderSwitchListener.this.d)) {
                    nwy.e();
                }
                if ("switch_smart_notify_reminder".equals(NotifyReminderSwitchListener.this.d) && i == 0) {
                    NotificationContentProviderUtil.f();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Intent intent = new Intent(this.b, (Class<?>) HandleIntentService.class);
        intent.setPackage(this.b.getPackageName());
        intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        Bundle bundle = new Bundle();
        bundle.putString("notificationSwitchChangeType", "notification_collaborate_switch_change");
        intent.putExtras(bundle);
        this.b.startService(intent);
    }
}
