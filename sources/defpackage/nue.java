package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class nue {
    public static Map<String, Integer> e(int i, boolean z, int i2, boolean z2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("resultCode", Integer.valueOf(i));
        hashMap.put("isFromTag", Integer.valueOf(z ? 1 : 0));
        hashMap.put("selectDeviceProductType", Integer.valueOf(i2));
        hashMap.put("isFinish", Integer.valueOf(z2 ? 1 : 0));
        return hashMap;
    }

    public static void cNU_(Intent intent, Activity activity, Map<String, Integer> map) {
        if (map == null || intent == null || activity == null || map.get("resultCode").intValue() != 2) {
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        boolean booleanExtra = intent.getBooleanExtra("is_support_notification", false);
        NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(BaseApplication.getContext());
        boolean b = notificationPushInteractor.b();
        String switchSettingFromLocal = jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG", 10001);
        boolean z = !b || (b && !Boolean.parseBoolean(switchSettingFromLocal));
        LogUtil.a("AfterPairEnterActivityUtils", "isAuthorizeEnabled:", Boolean.valueOf(b), " value:", switchSettingFromLocal);
        if (!oae.c(BaseApplication.getContext()).h()) {
            LogUtil.a("AfterPairEnterActivityUtils", "not support intelligent");
            notificationPushInteractor.a(bfg.e, 0);
        }
        int intValue = map.get("selectDeviceProductType").intValue();
        ock b2 = ocp.b(jpt.b(stringExtra, "AfterPairEnterActivityUtils"), true);
        boolean z2 = (!b2.d() || b2.e() || b2.b() == 0) ? false : true;
        int intValue2 = map.get("isFromTag").intValue();
        int intValue3 = map.get("isFinish").intValue();
        boolean z3 = intValue2 == 1;
        boolean z4 = intValue3 == 1;
        if (booleanExtra && z && intValue != 32) {
            LogUtil.a("AfterPairEnterActivityUtils", "enterActivity intentNotification");
            cNW_(stringExtra, activity, z3, z4, z2);
        } else if (z2) {
            ocp.cVT_(stringExtra, activity, z3, z4);
        } else {
            LogUtil.a("AfterPairEnterActivityUtils", "enterActivity intentMain");
            cNV_(stringExtra, activity, z3, z4);
        }
    }

    private static void cNV_(String str, Activity activity, boolean z, boolean z2) {
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.homewear21.home.WearHomeActivity");
            intent.putExtra("isFromWear", z);
            intent.putExtra("device_id", str);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("AfterPairEnterActivityUtils", "ActivityNotFoundException e:", e.getMessage());
        }
        if (z2) {
            activity.finish();
        }
    }

    private static void cNW_(String str, Activity activity, boolean z, boolean z2, boolean z3) {
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.notification.NotificationOpenActivity");
            intent.putExtra("isFromWear", z);
            intent.putExtra("isShowWalletOobeActivity", z3);
            intent.putExtra("device_id", str);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("AfterPairEnterActivityUtils", "ActivityNotFoundException e:", e.getMessage());
        }
        if (z2) {
            activity.finish();
        }
    }
}
