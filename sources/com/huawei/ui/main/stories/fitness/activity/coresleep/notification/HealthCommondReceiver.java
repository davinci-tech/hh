package com.huawei.ui.main.stories.fitness.activity.coresleep.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.jdh;
import defpackage.nsn;
import defpackage.ppk;
import defpackage.qhc;
import defpackage.qkc;

/* loaded from: classes6.dex */
public class HealthCommondReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        LogUtil.a("HealthCommondReceiver", "onReceive: ", action);
        if ("start".equals(action)) {
            ppk.drQ_(intent);
            return;
        }
        if ("end".equals(action)) {
            ppk.a();
            return;
        }
        if ("endTheDay".equals(action)) {
            ppk.a();
            ppk.b(System.currentTimeMillis());
            return;
        }
        if ("BLOOD_PRESSURE_MEASURE_PLAN_ACTION".equals(action.substring(0, action.lastIndexOf("_")))) {
            int intExtra = intent.getIntExtra("BLOOD_PRESSURE_MEASURE_PLAN_ID", -1);
            int intExtra2 = intent.getIntExtra("BLOOD_PRESSURE_MEASURE_PLAN_HOUR_OF_DAY", -1);
            int intExtra3 = intent.getIntExtra("BLOOD_PRESSURE_MEASURE_PLAN_MINUTE", -1);
            if (intExtra >= 0) {
                LogUtil.a("HealthCommondReceiver", "planId=", Integer.valueOf(intExtra));
                qhc.dDE_(intent, intExtra, intExtra2, intExtra3);
                return;
            }
            return;
        }
        if ("ACTION_BLOOD_SUGAR_SYNC".equals(action)) {
            LogUtil.a("HealthCommondReceiver", "ACTION_BLOOD_SUGAR_SYNC");
            qkc.a();
            qkc.c();
        } else {
            if ("ACTION_EFFICIENT_REST".equals(action)) {
                int intExtra4 = intent.getIntExtra("type", -1);
                LogUtil.a("HealthCommondReceiver", "ACTION_EFFICIENT_REST, type = ", Integer.valueOf(intExtra4));
                d(intExtra4);
                return;
            }
            LogUtil.h("HealthCommondReceiver", "action undefined");
        }
    }

    private void d(int i) {
        if (i == -1) {
            return;
        }
        if (i == 1) {
            jdh.c().a(20200320);
            return;
        }
        jdh.c().a(20200320);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        Context e = BaseApplication.e();
        Intent intent = new Intent();
        intent.setClassName(e, ComponentInfo.PluginSleepBriefs_A_1);
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.addFlags(536870912);
        intent.setPackage(BaseApplication.d());
        intent.putExtra(ArkUIXConstants.FROM_TYPE, 2);
        xf_.setContentTitle(e.getString(R$string.IDS_rest_efficient_notify)).setContentIntent(PendingIntent.getActivity(e, 0, intent, 201326592));
        xf_.setAutoCancel(true);
        jdh.c().xh_(20200320, xf_.build());
    }
}
