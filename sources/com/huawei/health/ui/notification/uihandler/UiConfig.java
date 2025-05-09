package com.huawei.health.ui.notification.uihandler;

import android.content.Context;
import android.os.Bundle;
import com.huawei.android.os.SystemPropertiesEx;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.health.connectivity.standstepcounter.StandStepCounterManager;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.health.ui.notification.constants.UiComponent;
import com.huawei.health.ui.notification.uihandlers.HealthFaHelper;
import com.huawei.health.ui.notification.uihandlers.HealthNotificationHelper;
import com.huawei.health.ui.notification.uihandlers.HealthSmartCoverHelper;
import com.huawei.health.ui.notification.uihandlers.HealthWidgetHelper;
import defpackage.gsn;
import health.compact.a.EmuiBuild;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UiHandler;
import health.compact.a.Utils;

/* loaded from: classes.dex */
public class UiConfig {
    private Context d;

    public UiConfig(Context context) {
        this.d = null;
        if (context == null) {
            LogUtil.e("Step_UIConfig", "context is null in UiConfig");
        } else {
            this.d = context;
        }
    }

    public boolean d() {
        return new StandStepCounterManager(this.d).e(0) != null;
    }

    public void b(UiHandler uiHandler) {
        HealthNotificationHelper healthNotificationHelper = new HealthNotificationHelper(this.d);
        if (uiHandler == null) {
            LogUtil.e("Step_UIConfig", "uiHandler is null");
            return;
        }
        if (d()) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "init");
            bundle.putString("target", "all");
            aNZ_(uiHandler, UiComponent.NOTIFICATION, healthNotificationHelper, bundle);
        }
        aNZ_(uiHandler, UiComponent.SMART_COVER, new HealthSmartCoverHelper(this.d), null);
        aNZ_(uiHandler, UiComponent.WIDGET, new HealthWidgetHelper(this.d), null);
        if (!Utils.o()) {
            aNZ_(uiHandler, UiComponent.WEXIN_STEPS, new gsn(), null);
        }
        if (a()) {
            aNZ_(uiHandler, UiComponent.FA_CARD, new HealthFaHelper(this.d), null);
        }
    }

    private void aNZ_(UiHandler uiHandler, UiComponent uiComponent, IReporter iReporter, Bundle bundle) {
        iReporter.onStart(bundle);
        uiHandler.a(uiComponent, iReporter);
    }

    private boolean a() {
        if (SystemInfo.b()) {
            return true;
        }
        if (EmuiBuild.f13113a < 29) {
            return false;
        }
        boolean z = SystemPropertiesEx.getBoolean("hw_mc.launcher.ability_form_enable", true);
        ReleaseLogUtil.b("Step_UIConfig", "isSupportFaCard() ", Boolean.valueOf(z));
        return z;
    }
}
