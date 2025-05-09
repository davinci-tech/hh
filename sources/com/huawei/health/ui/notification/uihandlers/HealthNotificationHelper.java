package com.huawei.health.ui.notification.uihandlers;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepsRecord;

/* loaded from: classes.dex */
public class HealthNotificationHelper extends IReporter {
    private static final String c = Boolean.TRUE.toString();
    private static final String e = Boolean.FALSE.toString();

    /* renamed from: a, reason: collision with root package name */
    private Context f3478a;
    private IReporter b;
    private IReporter d;

    public HealthNotificationHelper(Context context) {
        this.b = null;
        this.d = null;
        this.f3478a = null;
        context = context == null ? BaseApplication.getContext() : context;
        this.f3478a = context;
        this.b = HealthStepsNotificationHelper.d(context);
        this.d = new HealthGoalNotificationHelper(this.f3478a);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStart(Bundle bundle) {
        super.onStart(bundle);
        if (bundle == null) {
            return;
        }
        String string = bundle.getString("action");
        String string2 = bundle.getString("target");
        LogUtil.c("Step_HealthNotificationHelper", "action:", string, " target:", string2);
        if ("init".equals(string)) {
            c();
            return;
        }
        if ("start".equals(string)) {
            if ("GoalNotification".equals(string2)) {
                this.d.onStart(null);
                return;
            } else if ("StepsNotification".equals(string2)) {
                this.b.onStart(null);
                return;
            } else {
                LogUtil.c("Step_HealthNotificationHelper", "there is none match");
                return;
            }
        }
        if ("stop".equals(string)) {
            if ("GoalNotification".equals(string2)) {
                this.d.onStop();
                return;
            } else if ("StepsNotification".equals(string2)) {
                this.b.onStop();
                return;
            } else {
                LogUtil.c("Step_HealthNotificationHelper", "there is none match");
                return;
            }
        }
        LogUtil.c("Step_HealthNotificationHelper", "there is none match");
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(StepsRecord stepsRecord) {
        try {
            this.b.onUpdate(stepsRecord);
        } catch (Exception unused) {
            LogUtil.h("Step_HealthNotificationHelper", "stepsNotification refresh exception!!!");
        }
        try {
            this.d.onUpdate(stepsRecord);
        } catch (Exception unused2) {
            LogUtil.h("Step_HealthNotificationHelper", "goalNotification refresh exception!!!");
        }
    }

    private void c() {
        LogUtil.c("Step_HealthNotificationHelper", "initNotification ...");
        String s = SharedPerferenceUtils.s(this.f3478a);
        String i = SharedPerferenceUtils.i(this.f3478a);
        LogUtil.c("Step_HealthNotificationHelper", "default statusStepsNotification:", s, " statusGoalNotification:", i);
        if (UserInfomation.BIRTHDAY_UNSETED.equals(s) && UserInfomation.BIRTHDAY_UNSETED.equals(i)) {
            s = e();
            i = a();
            try {
                SharedPerferenceUtils.e(this.f3478a, Boolean.parseBoolean(s));
                SharedPerferenceUtils.a(this.f3478a, Boolean.parseBoolean(i));
            } catch (NumberFormatException e2) {
                LogUtil.c("Step_HealthNotificationHelper", "NumberFormatException", e2.getMessage());
            }
        }
        String str = c;
        if (str.equals(s)) {
            this.b.onStart(null);
        }
        if (str.equals(i)) {
            this.d.onStart(null);
        }
    }

    public static String e() {
        if (!CommonUtil.ck() && CommonUtil.bh()) {
            return c;
        }
        return e;
    }

    public static String a() {
        if (CommonUtil.ck()) {
            return c;
        }
        return e;
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        super.onStop();
        if (this.b.isRunning()) {
            this.b.onStop();
        }
        if (this.d.isRunning()) {
            this.d.onStop();
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
        try {
            this.b.onLanguageChanged();
            this.d.onLanguageChanged();
        } catch (Exception unused) {
            LogUtil.h("Step_HealthNotificationHelper", "languageChanged refresh exception!!!");
        }
    }
}
