package com.huawei.pluginhealthzone.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import defpackage.jdh;
import defpackage.mpq;

/* loaded from: classes6.dex */
public class HealthZonePushButtonReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            LogUtil.h("HealthZonePushButtonReceiver", "action == null");
            return;
        }
        LogUtil.a("HealthZonePushButtonReceiver", "intent.getAction() = ", action);
        action.hashCode();
        if (action.equals("health_zone_push_cancel")) {
            if (HealthZonePushReceiver.getHealthZonePushButtonReceiver() != null) {
                try {
                    context.unregisterReceiver(HealthZonePushReceiver.getHealthZonePushButtonReceiver());
                } catch (IllegalArgumentException unused) {
                    LogUtil.b("HealthZonePushButtonReceiver", "health_zone_push_cancel error");
                }
            }
            if (intent.hasExtra(HealthZonePushReceiver.MEMBER_HUID)) {
                LogUtil.a("HealthZonePushButtonReceiver", "memberhuid = ", intent.getStringExtra(HealthZonePushReceiver.MEMBER_HUID));
            }
            mpq.d().setLocationPermission(LoginInit.getInstance(context).getAccountInfo(1011), 5, null);
            LogUtil.a("HealthZonePushButtonReceiver", "HEALTH_ZONE_PUSH_CANCEL = ", "health_zone_push_cancel");
        } else if (action.equals("health_zone_go_h5")) {
            intent.putExtra("pushType", "3");
            if (intent.hasExtra(HealthZonePushReceiver.MEMBER_HUID)) {
                String stringExtra = intent.getStringExtra(HealthZonePushReceiver.MEMBER_HUID);
                LogUtil.a("HealthZonePushButtonReceiver", "memberhuid = ", stringExtra);
                intent.putExtra(HealthZonePushReceiver.MEMBER_HUID, stringExtra);
            }
            intent.setClass(context, FamilyHealthTempActivity.class);
            intent.setFlags(335544320);
            context.startActivity(intent);
        } else {
            LogUtil.h("HealthZonePushButtonReceiver", "intent.getAction() = ", action);
        }
        jdh.d().a(20210510);
    }
}
