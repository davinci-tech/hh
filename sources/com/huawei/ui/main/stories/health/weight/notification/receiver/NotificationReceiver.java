package com.huawei.ui.main.stories.health.weight.notification.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.gnm;
import defpackage.ixx;
import health.compact.a.AuthorizationUtils;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class NotificationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("HealthWeight_NotificationReceiver", "onReceive currentUserId = " + LoginInit.getInstance(context).getAccountInfo(1011));
        if (!AuthorizationUtils.a(context) || !LoginInit.getInstance(context).getIsLogined() || TextUtils.isEmpty(LoginInit.getInstance(context).getAccountInfo(1011))) {
            LogUtil.a("HealthWeight_NotificationReceiver", "moudle has not init, start app");
            dJc_(context, new Bundle());
        } else {
            if ("fasting_report".equals(intent.getAction())) {
                CardConstants.c(context, "#/summary_report");
                return;
            }
            CardConstants.c(context, "#/plan_setting");
        }
        c(context);
    }

    private void dJc_(Context context, Bundle bundle) {
        Intent intent = new Intent();
        if (context != null) {
            intent.setComponent(new ComponentName(context.getPackageName(), context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName()));
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
            intent.putExtra(KnitFragment.KEY_EXTRA, bundle);
            gnm.aPB_(context, intent);
        }
    }

    private void c(Context context) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(context, AnalyticsValue.FASTING_LITE_NOTIFICATION_CLICKED_2060086.value(), hashMap, 0);
    }
}
