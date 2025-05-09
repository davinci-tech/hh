package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.NotificationUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kjo {
    private static final Object b = new Object();
    private static kjo e;
    private boolean c;
    private e d;
    private NotificationManager h;
    private Context i;
    private DeviceInfo j;

    /* renamed from: a, reason: collision with root package name */
    private Notification.Builder f14420a = null;
    private Notification f = null;

    private boolean b(int i) {
        return i == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(int i) {
        return i == 1 || i == 11;
    }

    class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("KeepDeviceNotificationUtil", "laguage ischange");
            kjo.this.i();
        }
    }

    class e extends BroadcastReceiver {
        private e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null || intent.getAction() == null) {
                LogUtil.h("KeepDeviceNotificationUtil", "NotificationClickReceiver action is null");
                return;
            }
            LogUtil.a("KeepDeviceNotificationUtil", " action: ", intent.getAction());
            if (intent.getAction().equals("com.huawei.plugin.account.login")) {
                kjo.this.b();
                return;
            }
            if (intent.getAction().equals("device_notification_click")) {
                kjo kjoVar = kjo.this;
                if (kjoVar.b(kjoVar.j)) {
                    kjo.this.a();
                    kjo.this.b(context);
                    return;
                }
                kjo kjoVar2 = kjo.this;
                if (kjoVar2.d(kjoVar2.j.getDeviceConnectState())) {
                    kjo.this.b(context);
                    if (kjo.this.j.getDeviceConnectState() == 1 || CommonUtil.h(BaseApplication.getContext(), "com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity") || CommonUtil.h(BaseApplication.getContext(), "com.huawei.ui.device.activity.agreement.EnhancementImprovementActivity")) {
                        return;
                    }
                    kjo.this.j.setDeviceConnectState(3);
                    kjo kjoVar3 = kjo.this;
                    kjoVar3.a(kjoVar3.j);
                    return;
                }
                kjo.this.b(context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        jqi.a().getSwitchSetting("device_notification_key", new IBaseResponseCallback() { // from class: kjo.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                boolean z = true;
                if (i == 0 && (obj instanceof String)) {
                    z = true ^ "0".equals((String) obj);
                }
                LogUtil.a("KeepDeviceNotificationUtil", "checkLoginStatus: ", Boolean.valueOf(z));
                if (z) {
                    return;
                }
                kjo.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context.getPackageName(), packageManager.getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName()));
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
            intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
            intent.putExtra("SHORTCUT", "SC_DEVICE");
            context.startActivity(intent);
        } catch (ActivityNotFoundException | IllegalStateException unused) {
            LogUtil.b("KeepDeviceNotificationUtil", "goHealthApp, exception");
        }
    }

    public static kjo e(Context context) {
        kjo kjoVar;
        synchronized (b) {
            if (e == null) {
                e = new kjo(context);
            }
            kjoVar = e;
        }
        return kjoVar;
    }

    private kjo(Context context) {
        if (context == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "KeepDeviceNotificationUtil context is null");
            this.i = BaseApplication.getContext();
        } else {
            this.i = context;
        }
    }

    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "deviceInfo is null");
            return;
        }
        if (!d(deviceInfo)) {
            LogUtil.h("KeepDeviceNotificationUtil", "this is not wear device");
            return;
        }
        if (c(deviceInfo)) {
            LogUtil.h("KeepDeviceNotificationUtil", "this device is not main device");
            return;
        }
        if (this.j == null) {
            this.j = deviceInfo;
        }
        LogUtil.a("KeepDeviceNotificationUtil", "deviceInfo: ", deviceInfo.getDeviceName(), " ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (d(deviceConnectState) && (b(deviceInfo) || this.c)) {
            return;
        }
        if (c(deviceConnectState)) {
            if (!e(deviceInfo)) {
                return;
            }
            if (b(deviceInfo)) {
                LogUtil.h("KeepDeviceNotificationUtil", "this device is delete");
                a();
                return;
            }
        }
        this.j = deviceInfo;
        c(BaseApplication.getContext());
        j(deviceInfo);
    }

    private boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo.getProductType() == 11) {
            LogUtil.h("KeepDeviceNotificationUtil", "this is not wear device");
            return false;
        }
        int a2 = juu.a(deviceInfo.getProductType()).a();
        return (a2 == 6 || a2 == 3) ? false : true;
    }

    private void j(DeviceInfo deviceInfo) {
        Notification.Builder builder = this.f14420a;
        if (builder == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "updateNotificationView, mDeviceBuilder is null");
            return;
        }
        try {
            String c = c(BaseApplication.getContext(), deviceInfo);
            String a2 = a(deviceInfo.getDeviceConnectState());
            builder.setContentTitle(c);
            builder.setContentText(a2);
            this.f = builder.build();
            kjp.d().a(2021126);
            if (this.i instanceof Service) {
                LogUtil.h("KeepDeviceNotificationUtil", "startForeground notify");
                if (PermissionUtil.c()) {
                    ((Service) this.i).startForeground(2021126, this.f, 16);
                } else {
                    ((Service) this.i).startForeground(2021126, this.f);
                }
            } else {
                LogUtil.h("KeepDeviceNotificationUtil", "updateNotificationView notify");
                kjp.d().xh_(2021126, this.f);
            }
        } catch (Exception e2) {
            LogUtil.b("KeepDeviceNotificationUtil", "updateNotificationView Exception ", ExceptionUtils.d(e2));
        }
    }

    public void a(String str, DeviceInfo deviceInfo) {
        if (TextUtils.isEmpty(str)) {
            g(deviceInfo);
            return;
        }
        if (str.equalsIgnoreCase("1")) {
            j();
            return;
        }
        if (str.equalsIgnoreCase("0")) {
            a();
        } else if (str.equals(String.valueOf(true))) {
            this.c = true;
        } else if (str.equals(String.valueOf(false))) {
            this.c = false;
        }
    }

    private void g(DeviceInfo deviceInfo) {
        if (lcu.e()) {
            if (this.f14420a == null || this.f == null) {
                LogUtil.h("KeepDeviceNotificationUtil", "updateNotifyState, mBuilder or mDeviceNotification is null");
                return;
            }
            if (deviceInfo == null || this.j == null) {
                LogUtil.h("KeepDeviceNotificationUtil", "updateNotifyState, deleteDeviceInfo is null");
                return;
            }
            if (c(deviceInfo)) {
                LogUtil.h("KeepDeviceNotificationUtil", "updateNotifyState, this device is not main device");
                return;
            }
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            String deviceIdentify2 = this.j.getDeviceIdentify();
            if (b(this.j) || deviceIdentify.equalsIgnoreCase(deviceIdentify2)) {
                LogUtil.h("KeepDeviceNotificationUtil", "updateNotifyState, cancel notify");
                a();
            }
        }
    }

    private void j() {
        ArrayList<DeviceInfo> arrayList = new ArrayList(16);
        arrayList.addAll(cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "KeepDeviceNotificationUtil"));
        if (koq.b(arrayList)) {
            LogUtil.h("KeepDeviceNotificationUtil", "app not has wearDevice");
            return;
        }
        Collections.sort(arrayList, new Comparator<DeviceInfo>() { // from class: kjo.5
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
                if (deviceInfo == null || deviceInfo2 == null) {
                    return 1;
                }
                return Long.compare(deviceInfo2.getLastConnectedTime(), deviceInfo.getLastConnectedTime());
            }
        });
        for (DeviceInfo deviceInfo : arrayList) {
            if (!c(deviceInfo)) {
                if (d(deviceInfo)) {
                    a(deviceInfo);
                    return;
                }
                return;
            }
        }
    }

    public void a() {
        Context context = this.i;
        if (context instanceof Service) {
            ((Service) context).stopForeground(true);
        }
        kjp.d().a(2021126);
        this.f14420a = null;
        this.f = null;
        this.j = null;
    }

    private boolean c(DeviceInfo deviceInfo) {
        String relationship = deviceInfo.getRelationship();
        if (!TextUtils.isEmpty(relationship) && "followed_relationship".equals(deviceInfo.getRelationship())) {
            if (cvt.c(deviceInfo.getProductType())) {
                return false;
            }
            LogUtil.h("KeepDeviceNotificationUtil", "isOtherDevice: ", relationship);
            return true;
        }
        if (jta.d().c(deviceInfo.getProductType())) {
            LogUtil.h("KeepDeviceNotificationUtil", "this device is relation ship");
            return true;
        }
        LogUtil.h("KeepDeviceNotificationUtil", "isOtherDevice: ", relationship);
        return false;
    }

    private boolean c(int i) {
        return (d(i) || b(i)) ? false : true;
    }

    private String a(int i) {
        if (b(i)) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_wearhome_connected);
        }
        if (d(i)) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_device_connecting_21);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_device_wear_device_disconnect);
    }

    private void c(Context context) {
        if (this.f14420a == null) {
            d();
            Intent aOv_ = NotificationUtil.aOv_(context);
            aOv_.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
            Notification.Builder ongoing = kjp.d().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setShowWhen(true).setAutoCancel(false).setContentIntent(PendingIntent.getActivity(context, 1001, aOv_, 335544320)).setPriority(0).setOngoing(true);
            this.f14420a = ongoing;
            ongoing.setGroup("KeepDeviceNotificationUtil");
        }
        this.f14420a.setWhen(System.currentTimeMillis()).setTimeoutAfter(14400000L);
    }

    private String c(Context context, DeviceInfo deviceInfo) {
        if (context == null || deviceInfo == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "getTitle, context is null");
            return "";
        }
        String deviceName = deviceInfo.getDeviceName();
        if (TextUtils.isEmpty(deviceName)) {
            return juu.a(deviceInfo.getProductType()).c();
        }
        return ((TextUtils.isEmpty(deviceName) || !TextUtils.equals(deviceName, "PORSCHE DESIGN")) && (TextUtils.isEmpty(deviceInfo.getDeviceModel()) || !TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) ? deviceName : "PORSCHE DESIGN";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "updateNotifyState, mDeviceInfo is null");
            return true;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "KeepDeviceNotificationUtil");
        if (koq.b(deviceList)) {
            LogUtil.h("KeepDeviceNotificationUtil", "deviceInfos is null");
            return true;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (it.hasNext()) {
            if (deviceIdentify.equalsIgnoreCase(it.next().getDeviceIdentify())) {
                return false;
            }
        }
        return true;
    }

    private boolean e(DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2 = this.j;
        if (deviceInfo2 == null) {
            return false;
        }
        if (deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            return true;
        }
        LogUtil.h("KeepDeviceNotificationUtil", "this is not current device");
        return false;
    }

    private void d() {
        try {
            BroadcastManager.wk_(new a());
            this.d = new e();
            IntentFilter intentFilter = new IntentFilter("device_notification_click");
            intentFilter.addAction("com.huawei.plugin.account.login");
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.d, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("KeepDeviceNotificationUtil", "registerReceiver is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.h == null) {
            NotificationManager xB_ = CommonUtils.xB_();
            this.h = xB_;
            if (xB_ == null) {
                LogUtil.h("KeepDeviceNotificationUtil", "updateNotifyLanguage, mManager is null");
                return;
            }
        }
        if (this.j == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "current device is null");
            return;
        }
        StatusBarNotification[] activeNotifications = this.h.getActiveNotifications();
        if (activeNotifications == null) {
            LogUtil.h("KeepDeviceNotificationUtil", "activeNotifications is null");
            return;
        }
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            if (statusBarNotification.getId() == 2021126) {
                j(this.j);
                return;
            }
        }
    }

    public void e() {
        a();
        c();
        try {
            BroadcastManager.wy_(new a());
            BaseApplication.getContext().unregisterReceiver(this.d);
        } catch (IllegalArgumentException unused) {
            LogUtil.h("KeepDeviceNotificationUtil", "onDestroy is exception");
        }
    }

    private static void c() {
        synchronized (b) {
            e = null;
        }
    }
}
