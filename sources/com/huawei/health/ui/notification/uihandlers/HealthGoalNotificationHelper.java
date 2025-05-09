package com.huawei.health.ui.notification.uihandlers;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.jdh;
import health.compact.a.LogAnonymous;
import health.compact.a.NotificationHelper;
import health.compact.a.NotificationUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepsRecord;

/* loaded from: classes.dex */
public class HealthGoalNotificationHelper extends IReporter {
    private Context e;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private Notification.Builder f3477a = null;
    private int b = -1;
    private int d = -1;

    public HealthGoalNotificationHelper(Context context) {
        this.e = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x005b  */
    @Override // com.huawei.health.ui.notification.common.IReporter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStart(android.os.Bundle r7) {
        /*
            r6 = this;
            super.onStart(r7)
            android.content.Context r7 = r6.e
            r0 = 1
            health.compact.a.SharedPerferenceUtils.a(r7, r0)
            android.content.Context r7 = r6.e
            java.lang.String[] r7 = health.compact.a.SharedPerferenceUtils.f(r7)
            r1 = 0
            if (r7 == 0) goto L2f
            int r2 = r7.length     // Catch: java.lang.NumberFormatException -> L3b
            r3 = 2
            if (r2 < r3) goto L2f
            r2 = r7[r1]     // Catch: java.lang.NumberFormatException -> L3b
            long r2 = java.lang.Long.parseLong(r2)     // Catch: java.lang.NumberFormatException -> L3b
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.NumberFormatException -> L3b
            boolean r2 = com.huawei.health.manager.util.TimeUtil.b(r2, r4)     // Catch: java.lang.NumberFormatException -> L3b
            if (r2 == 0) goto L2f
            r7 = r7[r0]     // Catch: java.lang.NumberFormatException -> L3b
            boolean r7 = java.lang.Boolean.parseBoolean(r7)     // Catch: java.lang.NumberFormatException -> L3b
            r6.c = r7     // Catch: java.lang.NumberFormatException -> L3b
            goto L57
        L2f:
            r6.c = r1     // Catch: java.lang.NumberFormatException -> L3b
            android.content.Context r7 = r6.e     // Catch: java.lang.NumberFormatException -> L3b
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.NumberFormatException -> L3b
            health.compact.a.SharedPerferenceUtils.d(r7, r2, r1)     // Catch: java.lang.NumberFormatException -> L3b
            goto L57
        L3b:
            r7 = move-exception
            java.lang.String r0 = "numberFormatException"
            java.lang.String r7 = r7.getMessage()
            java.lang.Object[] r7 = new java.lang.Object[]{r0, r7}
            java.lang.String r0 = "Step_HlthGoalNotifHlp"
            com.huawei.hwlogsmodel.LogUtil.h(r0, r7)
            r6.c = r1
            android.content.Context r7 = r6.e
            long r2 = java.lang.System.currentTimeMillis()
            health.compact.a.SharedPerferenceUtils.d(r7, r2, r1)
        L57:
            boolean r7 = r6.c
            if (r7 != 0) goto L5e
            r6.b()
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ui.notification.uihandlers.HealthGoalNotificationHelper.onStart(android.os.Bundle):void");
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(StepsRecord stepsRecord) {
        if (stepsRecord == null) {
            LogUtil.h("Step_HlthGoalNotifHlp", "Invalid param");
            return;
        }
        if (stepsRecord.i <= 0) {
            LogUtil.h("Step_HlthGoalNotifHlp", "Refresh failed,invalidate target");
            return;
        }
        if (this.d == -1) {
            ReleaseLogUtil.b("Step_HlthGoalNotifHlp", "target init target ", Integer.valueOf(stepsRecord.i));
            this.d = stepsRecord.i;
        } else if (stepsRecord.i != this.d) {
            ReleaseLogUtil.b("Step_HlthGoalNotifHlp", "target changed target ", Integer.valueOf(stepsRecord.i));
            this.d = stepsRecord.i;
            this.c = false;
            SharedPerferenceUtils.d(this.e, System.currentTimeMillis(), false);
            b();
        }
        if (stepsRecord.g < this.b) {
            this.c = false;
            ReleaseLogUtil.b("Step_HlthGoalNotifHlp", "next day:", MedalConstants.EVENT_STEPS, LogAnonymous.b(stepsRecord.g), "mLastReport", LogAnonymous.b(this.b));
            SharedPerferenceUtils.d(this.e, System.currentTimeMillis(), false);
            b();
        }
        this.b = stepsRecord.g;
        if (stepsRecord.g < stepsRecord.i || this.c) {
            return;
        }
        this.c = true;
        SharedPerferenceUtils.d(this.e, System.currentTimeMillis(), true);
        aOb_();
        b(stepsRecord.g, stepsRecord.i);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        super.onStop();
        b();
        SharedPerferenceUtils.a(this.e, false);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
        b();
    }

    private void b() {
        LogUtil.a("Step_HlthGoalNotifHlp", "closeNotification...");
        this.b = -1;
        NotificationUtil.a(this.e, 10011);
        jdh.c().a(20210605);
    }

    private Notification aOb_() {
        LogUtil.a("Step_HlthGoalNotifHlp", "createHealthNotification()");
        if (this.f3477a == null) {
            Notification.Builder xf_ = jdh.c().xf_();
            this.f3477a = xf_;
            NotificationHelper.aOt_(this.e, xf_);
            this.f3477a.setShowWhen(true);
            this.f3477a.setContentIntent(jdh.bFr_(this.e));
            this.f3477a.setAutoCancel(true);
        }
        this.f3477a.setWhen(System.currentTimeMillis());
        Notification build = this.f3477a.build();
        build.contentIntent = jdh.bFr_(this.e);
        Intent intent = new Intent("goal_notify_delete");
        intent.setPackage(this.e.getPackageName());
        build.deleteIntent = PendingIntent.getBroadcast(this.e, 10011, intent, 201326592);
        build.priority = 0;
        build.flags |= 16;
        return build;
    }

    public void c(String str, String str2) {
        if (this.f3477a == null) {
            LogUtil.h("Step_HlthGoalNotifHlp", "updateHealthNotification builder null,not update");
            return;
        }
        String b = NotificationUtil.b(this.e, str2);
        this.f3477a.setContentTitle(b).setContentText(d());
        LogUtil.a("Step_HlthGoalNotifHlp", "rebuild notification");
        jdh.c().xh_(10011, this.f3477a.build());
        jdh.c().a(20210605);
    }

    public void b(int i, int i2) {
        ReleaseLogUtil.b("Step_HlthGoalNotifHlp", "updateHealthNotification: ", "steps = ", LogAnonymous.b(i));
        c(i + "", NotificationUtil.a(Integer.valueOf(i2), "###"));
    }

    private String d() {
        Context context = this.e;
        if (context != null) {
            try {
                return context.getString(R.string.IDS_plugindameon_hw_show_goal_completed_string);
            } catch (Resources.NotFoundException e) {
                LogUtil.b("Step_HlthGoalNotifHlp", "loadDayData() Exception", e.getMessage());
            }
        }
        return "";
    }
}
