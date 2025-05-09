package com.huawei.health.ui.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.health.icommon.LocalStepDataReport;
import com.huawei.health.ui.notification.manager.IUiNotificationManager;
import com.huawei.health.ui.notification.manager.UiNotificationManager;
import com.huawei.health.ui.notification.manager.UiWidgetManager;
import com.huawei.health.ui.notification.sync.UiSyncManager;
import com.huawei.health.ui.notification.uihandler.UiConfig;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.AsyncInvocationHandler;
import health.compact.a.StepsRecord;
import health.compact.a.UiHandler;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public class UiManager implements IUiManager {
    private UiHandler c;
    private Context d;
    private BroadcastReceiver e = null;

    /* renamed from: a, reason: collision with root package name */
    private UiSyncManager f3472a = null;
    private IUiNotificationManager b = null;
    private UiWidgetManager g = null;

    public UiManager(Context context) {
        this.c = null;
        this.d = context;
        this.c = new UiHandler(new UiConfig(context));
    }

    public IUiManager d() {
        Object newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), getClass().getInterfaces(), new AsyncInvocationHandler(this));
        if (!(newProxyInstance instanceof IUiManager)) {
            LogUtil.a("Step_UiManager", "getProxy object is not instanceof IUiManager");
            return null;
        }
        return (IUiManager) newProxyInstance;
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public void release() {
        BroadcastReceiver broadcastReceiver;
        if (this.d == null || (broadcastReceiver = this.e) == null) {
            return;
        }
        BroadcastManager.wy_(broadcastReceiver);
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public void changeStepsNotificationStateAsUser(boolean z) {
        IUiNotificationManager iUiNotificationManager = this.b;
        if (iUiNotificationManager != null) {
            iUiNotificationManager.changeStepsNotificationStateAsUser(z);
        }
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public void changeGoalNotificationStateAsUser(boolean z) {
        IUiNotificationManager iUiNotificationManager = this.b;
        if (iUiNotificationManager != null) {
            iUiNotificationManager.changeGoalNotificationStateAsUser(z);
        }
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public boolean isGetStepsNotificationState() {
        IUiNotificationManager iUiNotificationManager = this.b;
        if (iUiNotificationManager != null) {
            return iUiNotificationManager.isGetStepsNotificationState();
        }
        return false;
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public boolean isGetGoalNotificationState() {
        IUiNotificationManager iUiNotificationManager = this.b;
        if (iUiNotificationManager != null) {
            return iUiNotificationManager.isGetGoalNotificationState();
        }
        return false;
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public boolean isGetNotificationSupport() {
        IUiNotificationManager iUiNotificationManager = this.b;
        if (iUiNotificationManager != null) {
            return iUiNotificationManager.isGetNotificationSupport();
        }
        return false;
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public void localChanged() {
        UiHandler uiHandler = this.c;
        if (uiHandler != null) {
            uiHandler.a();
            this.c.b();
        }
    }

    class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (action == null || UiManager.this.d() == null) {
                    LogUtil.h("Step_UiManager", "action or Proxy is null");
                } else if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
                    UiManager.this.d().localChanged();
                }
            }
        }
    }

    public LocalStepDataReport c() {
        UiHandler uiHandler = this.c;
        if (uiHandler == null) {
            LogUtil.h("Step_UiManager", "mUiHandler is null");
            return null;
        }
        return new b(uiHandler);
    }

    public void b() {
        UiNotificationManager uiNotificationManager = new UiNotificationManager(this.d);
        uiNotificationManager.c(this.c);
        this.b = uiNotificationManager.e();
        UiWidgetManager uiWidgetManager = new UiWidgetManager(this.d);
        this.g = uiWidgetManager;
        uiWidgetManager.b(this.c);
        this.e = new c();
        LogUtil.a("Step_UiManager", "registerDynamicBroadcastReceiver");
        if (this.d != null) {
            BroadcastManager.wk_(this.e);
            UiSyncManager uiSyncManager = new UiSyncManager(this.d);
            this.f3472a = uiSyncManager;
            uiSyncManager.c(this.b);
        }
    }

    @Override // com.huawei.health.ui.notification.IUiManager
    public void updateStepRecord(StepsRecord stepsRecord) {
        UiHandler uiHandler = this.c;
        if (uiHandler != null) {
            uiHandler.d(stepsRecord);
        }
    }

    class b implements LocalStepDataReport {
        b(UiHandler uiHandler) {
            UiManager.this.c = uiHandler;
        }

        @Override // com.huawei.health.icommon.LocalStepDataReport
        public void report(StepsRecord stepsRecord) {
            if (stepsRecord == null || UiManager.this.d() == null) {
                LogUtil.a("Step_UiManager", "record or Proxy is null");
            } else {
                UiManager.this.d().updateStepRecord(stepsRecord);
            }
        }
    }
}
