package com.huawei.health.ui.notification.sync;

import android.content.Context;
import com.huawei.health.icommon.BaseSyncManager;
import com.huawei.health.ui.notification.manager.IUiNotificationManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.StepNotificationByHardWareUtils;

/* loaded from: classes.dex */
public class UiSyncManager extends BaseSyncManager {
    private Context b;
    private IUiNotificationManager c;

    public UiSyncManager(Context context) {
        super(context);
        this.c = null;
        this.b = context;
    }

    @Override // com.huawei.health.icommon.BaseSyncManager
    public boolean handleWorkerMessage(int i) {
        if (i != 7) {
            return false;
        }
        e();
        return true;
    }

    private void e() {
        if (this.c != null) {
            d();
            c();
        }
    }

    private void c() {
        HiUserPreference userPreference = HiHealthManager.d(this.b).getUserPreference("custom.goal_notification_status");
        if (userPreference == null) {
            LogUtil.h("Step_UiSyncManager", "syncGoalNotification hiUserPreference is null");
            this.c.resetGoalNotification();
            return;
        }
        String value = userPreference.getValue();
        LogUtil.a("Step_UiSyncManager", "syncGoalNotification:", value);
        if (value == null || "".equals(value.trim())) {
            LogUtil.h("Step_UiSyncManager", "syncGoalNotification hiUserPreference no value");
            this.c.resetGoalNotification();
        } else {
            try {
                this.c.changeGoalNotificationStateAsSync(Boolean.parseBoolean(value));
            } catch (NumberFormatException e) {
                LogUtil.h("Step_UiSyncManager", "NumberFormatException", e.getMessage());
            }
        }
    }

    private void d() {
        HiUserPreference userPreference = HiHealthManager.d(this.b).getUserPreference("custom.steps_notification_status");
        if (userPreference == null) {
            LogUtil.h("Step_UiSyncManager", "syncStepsNotification hiUserPreference is null");
            this.c.resetStepsNotification();
            return;
        }
        String value = userPreference.getValue();
        LogUtil.a("Step_UiSyncManager", "syncStepsNotification:", value);
        if (value == null || "".equals(value.trim())) {
            LogUtil.h("Step_UiSyncManager", "syncStepsNotification hiUserPreference no value");
            this.c.resetStepsNotification();
        } else if (!StepNotificationByHardWareUtils.a()) {
            LogUtil.a("Step_UiSyncManager", "syncStepsNotification isMtkPlatform is MTK");
        } else {
            this.c.changeStepsNotificationStateAsSync(Boolean.parseBoolean(value));
        }
    }

    public void c(IUiNotificationManager iUiNotificationManager) {
        if (iUiNotificationManager == null) {
            LogUtil.b("Step_UiSyncManager", "manager is null");
        } else {
            this.c = iUiNotificationManager;
        }
    }
}
