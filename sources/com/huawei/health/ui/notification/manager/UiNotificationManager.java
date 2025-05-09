package com.huawei.health.ui.notification.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.huawei.health.manager.util.Consts;
import com.huawei.health.ui.notification.constants.UiComponent;
import com.huawei.health.ui.notification.uihandler.UiConfig;
import com.huawei.health.ui.notification.uihandlers.HealthNotificationHelper;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.AsyncInvocationHandler;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.UiHandler;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public class UiNotificationManager implements IUiNotificationManager {
    private UiConfig b;
    private UiHandler c = null;
    private Context d;
    private c e;

    public UiNotificationManager(Context context) {
        this.d = null;
        this.e = null;
        this.b = null;
        if (context != null) {
            this.d = context;
            this.e = new c();
            LogUtil.a("Step_UiNotificationManager", "registerDynamicBroadcastReceiver");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("steps_notify_delete");
            BroadcastManagerUtil.bFA_(this.d, this.e, intentFilter, Consts.f2803a, null);
            this.b = new UiConfig(this.d);
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void resetStepsNotification() {
        String e = HealthNotificationHelper.e();
        try {
            boolean parseBoolean = Boolean.parseBoolean(e);
            LogUtil.a("Step_UiNotificationManager", "resetStepsNotification string:", e, " defaultConfig:", Boolean.valueOf(parseBoolean));
            if (parseBoolean == isGetStepsNotificationState()) {
                return;
            }
            changeStepsNotificationStateAsSync(parseBoolean);
        } catch (NumberFormatException e2) {
            LogUtil.b("Step_UiNotificationManager", "NumberFormatException", e2.getMessage());
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void resetGoalNotification() {
        String a2 = HealthNotificationHelper.a();
        try {
            boolean parseBoolean = Boolean.parseBoolean(a2);
            LogUtil.a("Step_UiNotificationManager", "resetGoalNotification str:", a2, " defaultConfig:", Boolean.valueOf(parseBoolean));
            if (parseBoolean == isGetGoalNotificationState()) {
                return;
            }
            changeGoalNotificationStateAsSync(parseBoolean);
        } catch (NumberFormatException e) {
            LogUtil.b("Step_UiNotificationManager", "NumberFormatException", e.getMessage());
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void changeStepsNotificationStateAsUser(boolean z) {
        LogUtil.a("Step_UiNotificationManager", "changeStepsNotificationStateAsUser:", Boolean.valueOf(z));
        a(z);
        e(z);
        UiHandler uiHandler = this.c;
        if (uiHandler != null) {
            uiHandler.b();
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void changeStepsNotificationStateAsSync(boolean z) {
        LogUtil.a("Step_UiNotificationManager", "changeStepsNotificationStateAsSync:", Boolean.valueOf(z));
        e(z);
        UiHandler uiHandler = this.c;
        if (uiHandler != null) {
            uiHandler.b();
        }
    }

    private void a(boolean z) {
        LogUtil.a("Step_UiNotificationManager", "writeToDbStepsNotificationStatus", Boolean.valueOf(z));
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.steps_notification_status");
        hiUserPreference.setValue(Boolean.toString(z));
        HiHealthManager.d(this.d).setUserPreference(hiUserPreference);
    }

    private void d(boolean z) {
        LogUtil.a("Step_UiNotificationManager", "writeToDbGoalNotificationStatus", Boolean.valueOf(z));
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.goal_notification_status");
        hiUserPreference.setValue(Boolean.toString(z));
        HiHealthManager.d(this.d).setUserPreference(hiUserPreference);
    }

    private void e(boolean z) {
        if (z == isGetStepsNotificationState()) {
            LogUtil.h("Step_UiNotificationManager", "changeStepsNotificationState ambious set:", Boolean.valueOf(z));
            return;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString("action", "start");
            bundle.putString("target", "StepsNotification");
            this.c.aOa_(UiComponent.NOTIFICATION, bundle);
        } else {
            bundle.putString("action", "stop");
            bundle.putString("target", "StepsNotification");
            this.c.aOa_(UiComponent.NOTIFICATION, bundle);
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void changeGoalNotificationStateAsUser(boolean z) {
        LogUtil.a("Step_UiNotificationManager", "changeGoalNotificationStateAsUser:", Boolean.valueOf(z));
        d(z);
        c(z);
        UiHandler uiHandler = this.c;
        if (uiHandler != null) {
            uiHandler.b();
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void changeGoalNotificationStateAsSync(boolean z) {
        LogUtil.a("Step_UiNotificationManager", "changeGoalNotificationStateAsSync", Boolean.valueOf(z));
        c(z);
        UiHandler uiHandler = this.c;
        if (uiHandler != null) {
            uiHandler.b();
        }
    }

    private void c(boolean z) {
        if (z == isGetGoalNotificationState()) {
            LogUtil.h("Step_UiNotificationManager", "changeGoalNotificationState ambious set:", Boolean.valueOf(z));
            return;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString("action", "start");
            bundle.putString("target", "GoalNotification");
            this.c.aOa_(UiComponent.NOTIFICATION, bundle);
        } else {
            bundle.putString("action", "stop");
            bundle.putString("target", "GoalNotification");
            this.c.aOa_(UiComponent.NOTIFICATION, bundle);
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public boolean isGetStepsNotificationState() {
        String s = SharedPerferenceUtils.s(this.d);
        if ("true".equals(s)) {
            return true;
        }
        if ("false".equals(s)) {
            return false;
        }
        if (UserInfomation.BIRTHDAY_UNSETED.equals(s)) {
            LogUtil.b("Step_UiNotificationManager", "isGetStepsNotificationState UNSETED");
            return false;
        }
        LogUtil.b("Step_UiNotificationManager", "isGetStepsNotificationState WORRY");
        LogUtil.b("Step_UiNotificationManager", "isGetStepsNotificationState UNKNOWN");
        return false;
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public boolean isGetGoalNotificationState() {
        String i = SharedPerferenceUtils.i(this.d);
        if ("true".equals(i)) {
            return true;
        }
        if ("false".equals(i)) {
            return false;
        }
        if (UserInfomation.BIRTHDAY_UNSETED.equals(i)) {
            LogUtil.b("Step_UiNotificationManager", "isGetGoalNotificationState UNSETED");
            return false;
        }
        LogUtil.b("Step_UiNotificationManager", "isGetStepsNotificationState WORRY");
        LogUtil.b("Step_UiNotificationManager", "isGetGoalNotificationState UNKNOWN");
        return false;
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public boolean isGetNotificationSupport() {
        UiConfig uiConfig = this.b;
        if (uiConfig != null) {
            return uiConfig.d();
        }
        return false;
    }

    public void c(UiHandler uiHandler) {
        if (uiHandler == null) {
            LogUtil.b("Step_UiNotificationManager", "uiHandler is null");
        } else {
            this.c = uiHandler;
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiNotificationManager
    public void stepsNotificationRemoveAndRebind() {
        if (isGetStepsNotificationState()) {
            e(false);
            e(true);
        }
    }

    public IUiNotificationManager e() {
        Object newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), getClass().getInterfaces(), new AsyncInvocationHandler(this));
        if (newProxyInstance instanceof IUiNotificationManager) {
            return (IUiNotificationManager) newProxyInstance;
        }
        return null;
    }

    class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (action == null || UiNotificationManager.this.e() == null) {
                    LogUtil.h("Step_UiNotificationManager", "action or Proxy is null");
                } else if ("steps_notify_delete".equals(action)) {
                    UiNotificationManager.this.e().stepsNotificationRemoveAndRebind();
                }
            }
        }
    }
}
