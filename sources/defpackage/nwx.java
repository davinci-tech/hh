package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import health.compact.a.CommonUtil;
import health.compact.a.MagicBuild;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class nwx {
    private static nwx b;
    private static final Object e = new Object();

    private nwx() {
    }

    public static nwx d() {
        nwx nwxVar;
        synchronized (e) {
            if (b == null) {
                b = new nwx();
            }
            nwxVar = b;
        }
        return nwxVar;
    }

    public void e(String str, int i, String str2) {
        HashMap hashMap = new HashMap(16);
        boolean bh = CommonUtil.bh();
        hashMap.put("click", "1");
        hashMap.put("appName", str);
        hashMap.put("isEMUI", Integer.valueOf(bh ? 1 : 0));
        hashMap.put("deviceName", str2);
        hashMap.put("status", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.NOTIFICATION_APP_1090011.value(), hashMap, 0);
    }

    public void d(String str, final boolean z, final String str2, final NotificationPushInteractor notificationPushInteractor) {
        if (bfg.d.contains(str)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: nwx.1
                @Override // java.lang.Runnable
                public void run() {
                    jqi.a().setSwitchSettingToLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG", "true", 10001);
                    LogUtil.a("NotificationUiPresenter", "recordNotificationToLocal set ture");
                }
            });
        }
        if (bfg.e.equals(str)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: nwx.5
                @Override // java.lang.Runnable
                public void run() {
                    jqi.a().setSwitchSettingToLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG_ADD", "true", 10001);
                    if (MagicBuild.f13130a && !CommonUtil.y(BaseApplication.getContext())) {
                        LogUtil.h("NotificationUiPresenter", "recordNotificationToLocal isNewHonor oversea phone return");
                        return;
                    }
                    if (cvs.e(str2) != null && cvs.e(str2).isSupportMidware()) {
                        jjb.b().b(notificationPushInteractor.b(), z);
                    }
                    LogUtil.a("NotificationUiPresenter", "recordNotificationToLocal intelligent set ture");
                }
            });
        }
    }

    public void c(Context context, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", str);
        String value = AnalyticsValue.SETTING_1090004.value();
        ixx.d().d(context, value, hashMap, 0);
        LogUtil.a("NotificationUiPresenter", "BI save notification click event finish, value: ", value);
    }

    public boolean c(Context context, jje jjeVar, NotificationPushInteractor notificationPushInteractor) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String obj = packageManager.getApplicationLabel(packageManager.getApplicationInfo(bfg.e, 128)).toString();
            int cRn_ = nwy.cRn_(packageManager, bfg.e);
            jjeVar.c(cRn_);
            LogUtil.a("NotificationUiPresenter", "hasIntelligent name:", obj, " uid:", Integer.valueOf(cRn_));
            boolean h = oae.c(BaseApplication.getContext()).h();
            if (!h) {
                LogUtil.a("NotificationUiPresenter", "hasIntelligent not support intelligent");
                notificationPushInteractor.a(bfg.e, 0);
            }
            return h;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.h("NotificationUiPresenter", "hasIntelligent isHasIntelligent is false");
            return false;
        }
    }

    public jje e(Context context, jje jjeVar, NotificationPushInteractor notificationPushInteractor) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String obj = packageManager.getApplicationLabel(packageManager.getApplicationInfo(bfg.e, 128)).toString();
            Drawable applicationIcon = packageManager.getApplicationIcon(packageManager.getApplicationInfo(bfg.e, 128));
            LogUtil.a("NotificationUiPresenter", "insertIntelligentInfo name ", obj, " icon ", applicationIcon);
            jjeVar.c(bfg.e);
            jjeVar.a(obj);
            jjeVar.bHw_(applicationIcon);
            jjeVar.b(notificationPushInteractor.d(bfg.e));
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("NotificationUiPresenter", "insertIntelligentPkg NameNotFoundException");
        }
        return jjeVar;
    }

    public void e(Context context, String str) {
        if (nwy.h()) {
            LogUtil.a("NotificationUiPresenter", "is support notification push icon.");
            Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
            intent.setPackage(context.getPackageName());
            intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
            Bundle bundle = new Bundle();
            bundle.putString("packageName", str);
            bundle.putString("notificationSwitchChangeType", "subNotificationSwitchChangeType");
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public void a(Context context, String str) {
        if (bfg.d.contains(str) || bfg.e.equals(str)) {
            LogUtil.a("NotificationUiPresenter", "enter syncDefaultSwitchStatus");
            Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
            intent.setPackage(context.getPackageName());
            intent.setAction("com.huawei.health.ACTION_NOTIFICATION_ENABLE_PUSH");
            Bundle bundle = new Bundle();
            bundle.putString("notificationEnablePushType", "notificationEnablePushSubSwitch");
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }
}
