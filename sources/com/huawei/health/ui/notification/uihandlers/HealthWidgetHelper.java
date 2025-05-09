package com.huawei.health.ui.notification.uihandlers;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.health.ui.widget.HealthSportWidget;
import com.huawei.health.ui.widget.HonorWidgetProvider;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.NotificationUtil;
import health.compact.a.StepsRecord;

/* loaded from: classes.dex */
public class HealthWidgetHelper extends IReporter {
    private Context b;
    private int e = 2;

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
    }

    public HealthWidgetHelper(Context context) {
        this.b = context;
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStart(Bundle bundle) {
        super.onStart(bundle);
        if (this.b == null) {
            LogUtil.b("Step_HealthWidgetHelper", " mContext is null");
            return;
        }
        if (NotificationUtil.a()) {
            this.e = Settings.Secure.getInt(this.b.getContentResolver(), "launcher_background_color", 2);
        } else {
            this.e = Settings.System.getInt(this.b.getContentResolver(), "launcher_background_color", 2);
        }
        LogUtil.a("Step_HealthWidgetHelper", " mBackgroundColorType : ", Integer.valueOf(this.e));
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(StepsRecord stepsRecord) {
        if (stepsRecord == null) {
            return;
        }
        e(stepsRecord);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        super.onStop();
    }

    private void e(StepsRecord stepsRecord) {
        if (stepsRecord == null) {
            LogUtil.h("Step_HealthWidgetHelper", "record is null");
            return;
        }
        Bundle aaD_ = stepsRecord.aaD_();
        aaD_.putInt("KEY_BG_COLOR_TYPE", this.e);
        if (this.b != null) {
            synchronized (HealthWidgetHelper.class) {
                LogUtil.a("Step_HealthWidgetHelper", "Data Changed! refreshWidget start! bundle= ", aaD_);
                HealthSportWidget.aOB_(this.b, aaD_);
                HonorWidgetProvider.aOI_(this.b, aaD_);
            }
        }
    }
}
