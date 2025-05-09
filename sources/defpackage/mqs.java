package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;

/* loaded from: classes6.dex */
public class mqs {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void cnm_(Activity activity, Intent intent) {
        if (activity == null || intent == null) {
            LogUtil.h("HealthZoneUtils", "goToHealthZoneFromReceiver activity or intent is null");
            return;
        }
        char c = 65535;
        if (intent.getIntExtra("healthZoneNotification", -1) != 1) {
            LogUtil.h("HealthZoneUtils", "goToHealthZoneFromReceiver type is undefined");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            LogUtil.h("HealthZoneUtils", "action == null");
            return;
        }
        LogUtil.a("HealthZoneUtils", "intent.getAction() = ", action);
        action.hashCode();
        switch (action.hashCode()) {
            case -1795172724:
                if (action.equals("health_zone_go_details_setting")) {
                    c = 0;
                    break;
                }
                break;
            case 267939503:
                if (action.equals("health_zone_push_cancel")) {
                    c = 1;
                    break;
                }
                break;
            case 867777382:
                if (action.equals("health_zone_location_source_setting")) {
                    c = 2;
                    break;
                }
                break;
            case 1810494196:
                if (action.equals("health_zone_go_h5")) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c == 0) {
            Intent intent2 = new Intent();
            intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent2.setData(Uri.fromParts("package", BaseApplication.d(), null));
            intent2.setFlags(335544320);
            activity.startActivity(intent2);
            LogUtil.a("HealthZoneUtils", "HEALTH_ZONE_GO_DETAILS_SETTINGS");
        } else if (c == 1) {
            if (intent.hasExtra(HealthZonePushReceiver.MEMBER_HUID)) {
                LogUtil.a("HealthZoneUtils", "memberhuid = ", intent.getStringExtra(HealthZonePushReceiver.MEMBER_HUID));
            }
            mpq.d().setLocationPermission(LoginInit.getInstance(activity).getAccountInfo(1011), 5, null);
            LogUtil.a("HealthZoneUtils", "HEALTH_ZONE_PUSH_CANCEL");
        } else if (c == 2) {
            Intent intent3 = new Intent();
            intent3.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
            intent3.setFlags(335544320);
            activity.startActivity(intent3);
            LogUtil.a("HealthZoneUtils", "HEALTH_ZONE_LOCATION_SOURCE_SETTINGS");
        } else if (c == 3) {
            intent.putExtra("pushType", "3");
            if (intent.hasExtra(HealthZonePushReceiver.MEMBER_HUID)) {
                String stringExtra = intent.getStringExtra(HealthZonePushReceiver.MEMBER_HUID);
                LogUtil.a("HealthZoneUtils", "memberhuid = ", stringExtra);
                intent.putExtra(HealthZonePushReceiver.MEMBER_HUID, stringExtra);
            }
            intent.setClass(activity, FamilyHealthTempActivity.class);
            intent.setFlags(335544320);
            activity.startActivity(intent);
        } else {
            LogUtil.h("HealthZoneUtils", "intent.getAction() = ", action);
        }
        jdh.d().a(20210510);
        activity.finish();
    }
}
