package defpackage;

import android.app.KeyguardManager;
import android.os.PowerManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class kho {
    private static final Object c = new Object();
    private static kho e;

    /* renamed from: a, reason: collision with root package name */
    private boolean f14373a;
    private boolean b = false;
    private boolean d;
    private KeyguardManager h;
    private PowerManager i;
    private boolean j;

    private kho() {
        this.j = false;
        boolean c2 = khs.c();
        this.j = c2;
        LogUtil.a("NotificationCollaborateManager", "NotificationCollaborateManager init phoneNotificationCollaboration3: ", Boolean.valueOf(c2));
    }

    public static kho b() {
        kho khoVar;
        synchronized (c) {
            if (e == null) {
                e = new kho();
            }
            khoVar = e;
        }
        return khoVar;
    }

    public void f() {
        if (!g() && !khs.j()) {
            LogUtil.h("NotificationCollaborateManager", "onDeviceConnected not support");
        } else {
            c();
            h();
        }
    }

    public boolean e() {
        boolean inKeyguardRestrictedInputMode;
        boolean isInteractive;
        LogUtil.a("NotificationCollaborateManager", "isReminder switch is ", Boolean.valueOf(this.d));
        KeyguardManager keyguardManager = this.h;
        if (keyguardManager != null) {
            inKeyguardRestrictedInputMode = keyguardManager.inKeyguardRestrictedInputMode();
        } else {
            Object systemService = BaseApplication.getContext().getSystemService("keyguard");
            inKeyguardRestrictedInputMode = systemService instanceof KeyguardManager ? ((KeyguardManager) systemService).inKeyguardRestrictedInputMode() : false;
        }
        PowerManager powerManager = this.i;
        if (powerManager != null) {
            isInteractive = powerManager.isInteractive();
        } else {
            Object systemService2 = BaseApplication.getContext().getSystemService("power");
            isInteractive = systemService2 instanceof PowerManager ? ((PowerManager) systemService2).isInteractive() : true;
        }
        ReleaseLogUtil.e("R_NotificationCollaborateManager", "isReminder isLockScreen:", Boolean.valueOf(inKeyguardRestrictedInputMode), " isScreenOn:", Boolean.valueOf(isInteractive));
        if (this.d && this.h != null && this.i != null) {
            return inKeyguardRestrictedInputMode || !isInteractive;
        }
        LogUtil.a("NotificationCollaborateManager", "isReminder switch is false or systemService is null");
        return true;
    }

    public boolean a() {
        LogUtil.a("NotificationCollaborateManager", "packagingReminderHex isJoinHarmonyReminder phoneCollaboration3: ", Boolean.valueOf(this.j));
        if (!this.b) {
            ReleaseLogUtil.d("R_NotificationCollaborateManager", "packagingReminderHex isJoinHarmonyReminder mIsSuccessGetSmartSwitch is false");
            return false;
        }
        if (!khs.j() || !this.j) {
            ReleaseLogUtil.d("R_NotificationCollaborateManager", "packagingReminderHex isJoinHarmonyReminder not support");
            return false;
        }
        ReleaseLogUtil.e("R_NotificationCollaborateManager", "packagingReminderHex isJoinHarmonyReminder join success");
        return true;
    }

    public boolean d() {
        return this.f14373a;
    }

    private void h() {
        if (this.h == null) {
            Object systemService = BaseApplication.getContext().getSystemService("keyguard");
            if (systemService instanceof KeyguardManager) {
                this.h = (KeyguardManager) systemService;
            }
        }
        if (this.i == null) {
            Object systemService2 = BaseApplication.getContext().getSystemService("power");
            if (systemService2 instanceof PowerManager) {
                this.i = (PowerManager) systemService2;
            }
        }
    }

    public boolean g() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotificationCollaborateManager");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("NotificationCollaborateManager", "isSupportNotifyReminderCollaborate deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 33);
    }

    public void c() {
        jqi.a().getSwitchSetting("switch_silent_notify_using_phone", new IBaseResponseCallback() { // from class: khm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                kho.this.b(i, obj);
            }
        });
        jqi.a().getSwitchSetting("switch_smart_notify_reminder", new IBaseResponseCallback() { // from class: khn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                kho.this.d(i, obj);
            }
        });
    }

    /* synthetic */ void b(int i, Object obj) {
        LogUtil.a("NotificationCollaborateManager", "getReminderControlSwitch errorCode:", Integer.valueOf(i));
        if (i == 0) {
            boolean equals = obj instanceof String ? true ^ "0".equals(obj) : true;
            LogUtil.a("NotificationCollaborateManager", "getReminderControlSwitch isChecked:", Boolean.valueOf(equals));
            this.d = equals;
            return;
        }
        i();
    }

    /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("NotificationCollaborateManager", "getReminderControlSwitch harmony errorCode:", Integer.valueOf(i));
        if (i != 0) {
            return;
        }
        if (!(obj instanceof String)) {
            LogUtil.a("NotificationCollaborateManager", "getReminderControlSwitch harmony objectData not instanceof String");
            return;
        }
        this.b = true;
        boolean equals = true ^ "0".equals(obj);
        LogUtil.a("NotificationCollaborateManager", "getReminderControlSwitch harmony isChecked:", Boolean.valueOf(equals));
        this.f14373a = equals;
    }

    private void i() {
        jqi.a().getSwitchSetting("lock_screen_reminder_switch", new IBaseResponseCallback() { // from class: khl
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                kho.this.e(i, obj);
            }
        });
    }

    /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("NotificationCollaborateManager", "getReminderOldSwitch errorCode:", Integer.valueOf(i));
        boolean z = i != 0 && khs.f();
        if (i == 0 && (obj instanceof String)) {
            z = !"0".equals(obj);
            LogUtil.a("NotificationCollaborateManager", "getReminderOldSwitch isChecked:", Boolean.valueOf(z));
        }
        this.d = !z;
    }
}
