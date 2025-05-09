package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.systemmanager.notificationmanager.HwNotificationManagerEx;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class nwy {
    private static boolean c = false;

    public static boolean d(jje jjeVar) {
        if (jjeVar == null) {
            LogUtil.h("NotificationUiUtil", "isSystemNotificationOpen appInfo is null");
            return true;
        }
        if (jjeVar.b() != -1) {
            return d(jjeVar.g(), jjeVar.b());
        }
        return true;
    }

    public static boolean d(String str, int i) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            z = HwNotificationManagerEx.getNotificationManager().areNotificationsEnabledForPackage(str, i);
        } catch (RemoteException | SecurityException unused) {
            LogUtil.b("NotificationUiUtil", "checkAuthorityInSystem RemoteException exception");
        }
        LogUtil.a("NotificationUiUtil", str, " whose packageUid is: ", Integer.valueOf(i), ",status in system is: ", Boolean.valueOf(z));
        return z;
    }

    public static int cRn_(PackageManager packageManager, String str) {
        if (packageManager == null || TextUtils.isEmpty(str)) {
            LogUtil.h("NotificationUiUtil", "getUidByPackageName error");
            return -1;
        }
        if (!CommonUtil.ar()) {
            return -1;
        }
        try {
            return packageManager.getPackageUid(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("NotificationUiUtil", "PackageManager NameNotFoundException");
            return -1;
        }
    }

    public static boolean d(Context context, int i, jje jjeVar, HealthSwitchButton healthSwitchButton) {
        LogUtil.a("NotificationUiUtil", "getUidByPackageName system is:", Integer.valueOf(EmuiBuild.f13113a));
        if (context == null || jjeVar == null || healthSwitchButton == null) {
            LogUtil.h("NotificationUiUtil", "remindOpenSystemAuthority error");
            return false;
        }
        if (!CommonUtil.ar() || i != 1 || jjeVar.b() == -1 || d(jjeVar.g(), jjeVar.b())) {
            return false;
        }
        b(context, jjeVar.g(), jjeVar.b(), healthSwitchButton);
        LogUtil.a("NotificationUiUtil", "remindOpenSystemAuthority show notification dialog");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, int i) {
        try {
            HwNotificationManagerEx.getNotificationManager().setNotificationsEnabledForPackage(str, i, true);
        } catch (RemoteException | SecurityException e) {
            LogUtil.b("NotificationUiUtil", "openSystemAuthority exception: ", LogAnonymous.b(e));
        }
    }

    private static void b(final Context context, final String str, final int i, final HealthSwitchButton healthSwitchButton) {
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(context).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: nwy.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nwy.a(str, i);
                healthSwitchButton.setChecked(true);
                nwx.d().a(context, str);
                nwy.d(context, "1");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string.IDS_device_open_later_button, new View.OnClickListener() { // from class: nwy.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HealthSwitchButton.this.setChecked(false);
                nwy.d(context, "0");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        czz_.e(context.getString(R.string.IDS_device_system_notify_open_remind));
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("status", str);
        ixx.d().d(context, AnalyticsValue.NOTIFY_BUTTON_CLICKED_2129004.value(), hashMap, 2);
    }

    public static String c(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(str, 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("NotificationUiUtil", "getAppName NameNotFoundException");
            return "";
        }
    }

    public static List<jje> e(List<jje> list) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        int size = bfg.f348a.size();
        for (int i = 0; i < list.size(); i++) {
            int i2 = 0;
            while (true) {
                if (i2 < size) {
                    if (bfg.f348a.get(i2).equals(list.get(i).g())) {
                        arrayList.add(list.get(i));
                        break;
                    }
                    i2++;
                } else {
                    arrayList2.add(Integer.valueOf(i));
                    break;
                }
            }
        }
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            arrayList.add(list.get(((Integer) arrayList2.get(i3)).intValue()));
        }
        arrayList2.clear();
        return arrayList;
    }

    public static boolean j() {
        DeviceInfo d = jpt.d("NotificationUiUtil");
        if (d == null) {
            LogUtil.h("NotificationUiUtil", "isSupportNotifyReminderCollaborate deviceInfo is null");
            return false;
        }
        return cwi.c(d, 33);
    }

    public static boolean f() {
        DeviceInfo d = jpt.d("NotificationUiUtil");
        if (d == null) {
            LogUtil.h("NotificationUiUtil", "isSupportNotifyReminderSwitchClose deviceInfo is null");
            return false;
        }
        return cwi.c(d, 121);
    }

    public static boolean h() {
        return n() || g();
    }

    public static boolean n() {
        DeviceInfo d = jpt.d("NotificationUiUtil");
        if (d == null) {
            LogUtil.h("NotificationUiUtil", "isSupportSyncIconOld deviceInfo is null");
            return false;
        }
        return cwi.c(d, 17);
    }

    public static boolean g() {
        DeviceInfo d = jpt.d("NotificationUiUtil");
        if (d == null) {
            LogUtil.h("NotificationUiUtil", "isSupportAddIconTimestamp deviceInfo is null");
            return false;
        }
        return cwi.c(d, 77);
    }

    public static boolean i() {
        DeviceInfo d = jpt.d("NotificationUiUtil");
        if (d == null) {
            LogUtil.h("NotificationUiUtil", "isSupportHarmonyNotificationCollaboration deviceInfo is null");
            return false;
        }
        return cwi.c(d, 119);
    }

    public static boolean c() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.action.NOTIFICATION_DECISION_CENTER");
        List<ResolveInfo> queryIntentContentProviders = BaseApplication.getContext().getPackageManager().queryIntentContentProviders(intent, 0);
        if (queryIntentContentProviders != null && !queryIntentContentProviders.isEmpty()) {
            return true;
        }
        LogUtil.h("NotificationUiUtil", "isPhoneHarmonyNotificationCollaboration resolveInfoList is empty");
        return false;
    }

    public static void e() {
        c = false;
        jqi.a().getSwitchSetting("switch_silent_notify_using_phone", new IBaseResponseCallback() { // from class: nwy.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("NotificationUiUtil", "getNotificationRedDotStatus errorCode:", Integer.valueOf(i));
                if (i != 0) {
                    nwy.o();
                    return;
                }
                if (!(obj instanceof String)) {
                    LogUtil.a("NotificationUiUtil", "getNotificationRedDotStatus objectData not instanceof String");
                    return;
                }
                boolean z = !"0".equals(obj);
                LogUtil.a("NotificationUiUtil", "getNotificationRedDotStatus isNewSwitchEnable:", Boolean.valueOf(z));
                if (z) {
                    nwy.k();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o() {
        jqi.a().getSwitchSetting("lock_screen_reminder_switch", new IBaseResponseCallback() { // from class: nwy.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("NotificationUiUtil", "getReminderOldSwitch errorCode:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    boolean equals = "0".equals(obj);
                    LogUtil.a("NotificationUiUtil", "getReminderOldSwitch isNewSwitchEnable:", Boolean.valueOf(equals));
                    if (equals) {
                        nwy.k();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void k() {
        jqi.a().getSwitchSetting("switch_smart_notify_reminder", new IBaseResponseCallback() { // from class: nwy.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("NotificationUiUtil", "getSmartSwitchStatus errorCode:", Integer.valueOf(i));
                if (i != 0) {
                    boolean unused = nwy.c = true;
                }
            }
        });
    }

    public static boolean b() {
        return c && c() && i();
    }
}
