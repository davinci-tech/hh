package com.huawei.health.ui.notification.uihandlers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Bundle;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.StepsRecord;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class HealthSmartCoverHelper extends IReporter {
    private static long c = 0;
    private static boolean e = true;

    /* renamed from: a, reason: collision with root package name */
    private Context f3479a;
    private CoverStateBroadcastReceiver d;

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
    }

    public HealthSmartCoverHelper(Context context) {
        this.f3479a = context;
    }

    private void a(Context context, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putIntArray("cache total data", new int[]{i, i3, i2});
        bundle.putInt(MedalConstants.EVENT_STEPS, i);
        bundle.putInt("cal", i3);
        bundle.putInt("distance", i2);
        Intent intent = new Intent("android.com.huawei.bone.NOTIFY_SPORT_DATA");
        intent.putExtras(bundle);
        long b = LogUtil.b(5000, c);
        if (b != -1) {
            ReleaseLogUtil.e("Step_HealthSmrtCvrHlp", "broadcast step : ", Integer.valueOf(i), " distance ", Integer.valueOf(i2));
            c = b;
        }
        intent.setPackage(CommonUtil.aa());
        aOc_(context, intent, "com.android.keyguard.permission.SEND_STEP_INFO_COUNTER");
        intent.setPackage("com.android.systemui");
        aOc_(context, intent, "com.android.keyguard.permission.SEND_STEP_INFO_COUNTER");
    }

    public static final void aOc_(Context context, Intent intent, String str) {
        if (context == null) {
            LogUtil.b("Step_HealthSmrtCvrHlp", "context is null");
        } else if (intent != null) {
            try {
                context.sendBroadcast(intent, str);
            } catch (RuntimeException e2) {
                LogUtil.b("Step_HealthSmrtCvrHlp", "sendBroadcast exception:", e2.getMessage());
            }
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStart(Bundle bundle) {
        super.onStart(bundle);
        if (this.d == null) {
            this.d = new CoverStateBroadcastReceiver();
        }
        Context context = this.f3479a;
        if (context != null) {
            BroadcastManagerUtil.bFA_(context, this.d, new IntentFilter("com.huawei.android.cover.STATE"), "android.permission.RESET_FINGERPRINT_LOCKOUT", null);
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(StepsRecord stepsRecord) {
        if (stepsRecord == null) {
            LogUtil.b("Step_HealthSmrtCvrHlp", "record is null:");
        } else if ((CommonUtil.ac(this.f3479a) || !e) && ScreenUtil.a()) {
            a(this.f3479a, stepsRecord.g, stepsRecord.f13139a, stepsRecord.d);
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        CoverStateBroadcastReceiver coverStateBroadcastReceiver;
        super.onStop();
        Context context = this.f3479a;
        if (context == null || (coverStateBroadcastReceiver = this.d) == null) {
            return;
        }
        context.unregisterReceiver(coverStateBroadcastReceiver);
        this.d = null;
    }

    public static class CoverStateBroadcastReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null || !"com.huawei.android.cover.STATE".equals(intent.getAction())) {
                return;
            }
            try {
                boolean unused = HealthSmartCoverHelper.e = intent.getBooleanExtra("coverOpen", true);
            } catch (BadParcelableException e) {
                LogUtil.b("Step_HealthSmrtCvrHlp", "BadParcelableException getBooleanExtra:", LogAnonymous.b((Throwable) e));
            }
        }
    }
}
