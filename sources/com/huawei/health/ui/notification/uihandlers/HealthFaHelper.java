package com.huawei.health.ui.notification.uihandlers;

import android.content.Context;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.provider.HealthOpenProvider;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dlc;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.StepsRecord;

/* loaded from: classes.dex */
public class HealthFaHelper extends IReporter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3475a;
    private StepsRecord e = new StepsRecord();

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
    }

    public HealthFaHelper(Context context) {
        if (context != null) {
            this.f3475a = context.getApplicationContext();
        } else {
            this.f3475a = BaseApplication.e();
        }
        ReleaseLogUtil.b("Step_HealthFaHelper", "HealthFaHelper create");
        dlc.c().a();
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStart(Bundle bundle) {
        LogUtil.h("Step_HealthFaHelper", "onStart");
        super.onStart(bundle);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        LogUtil.h("Step_HealthFaHelper", "onStop");
        super.onStop();
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(final StepsRecord stepsRecord) {
        if (stepsRecord == null || this.f3475a == null) {
            LogUtil.h("Step_HealthFaHelper", "record == null || mContext == null");
            return;
        }
        if (stepsRecord.equals(this.e)) {
            return;
        }
        this.e.b(stepsRecord);
        dlc.c().d(stepsRecord, false);
        if (dlc.c().b()) {
            return;
        }
        ThreadPoolManager.d().c("Step_HealthFaHelper", new Runnable() { // from class: com.huawei.health.ui.notification.uihandlers.HealthFaHelper.3
            @Override // java.lang.Runnable
            public void run() {
                if (!dlc.c().e()) {
                    ReleaseLogUtil.a("Step_HealthFaHelper", "uri is invalid ");
                } else if (HealthOpenProvider.d()) {
                    dlc.c().d(stepsRecord);
                }
            }
        });
    }
}
