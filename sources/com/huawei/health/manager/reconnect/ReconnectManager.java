package com.huawei.health.manager.reconnect;

import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.bky;
import defpackage.jdh;
import defpackage.tno;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DeviceUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReflectionUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class ReconnectManager {

    /* renamed from: a, reason: collision with root package name */
    private final BleReconnectScanCallback f2796a;
    private final BroadcastReceiver b;
    private final BtDeviceHfpStateCallback c;
    private final ServiceConnection d;
    private volatile boolean e;

    private ReconnectManager() {
        this.e = false;
        this.c = new BtDeviceHfpStateCallback() { // from class: com.huawei.health.manager.reconnect.ReconnectManager.1
            @Override // com.huawei.health.manager.reconnect.BtDeviceHfpStateCallback
            public void onBtDeviceHfpConnected(BluetoothDevice bluetoothDevice, String str) {
                if (ReconnectManager.this.e) {
                    LogUtil.h("ReconnectManager", "onBtDeviceHfpConnected PhoneService running.");
                    return;
                }
                if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
                    LogUtil.h("ReconnectManager", "btDevice is invalid, onBtDeviceHfpConnected fail.");
                    return;
                }
                Map<String, String> map = tno.d().get(bluetoothDevice.getAddress());
                if (map == null) {
                    ReleaseLogUtil.d("DEVMGR_ReconnectManager", "deviceInfo is null, onBtDeviceHfpConnected fail.");
                    return;
                }
                String str2 = map.get("deviceBtType");
                ReleaseLogUtil.e("DEVMGR_ReconnectManager", "Enter onBtDeviceHfpConnected.", CommonUtil.l(bluetoothDevice.getAddress()), ",btType:", str2);
                if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str)) {
                    String str3 = map.get("deviceBtMode");
                    LogUtil.a("ReconnectManager", "onBTDeviceHFPConnected device manufacture:", str3);
                    if (ReconnectManager.this.d(str2, str3)) {
                        ReleaseLogUtil.e("DEVMGR_ReconnectManager", "onBTDeviceHFPConnected connect diana or ble05 device.");
                        ReconnectManager.this.alm_(bluetoothDevice, 1);
                        return;
                    }
                    return;
                }
                if ("1".equals(str2) || "2".equals(str2)) {
                    ReleaseLogUtil.e("DEVMGR_ReconnectManager", "onBTDeviceHFPConnected. type: ", str2);
                    ReconnectManager.this.alm_(bluetoothDevice, 2);
                }
            }
        };
        this.f2796a = new BleReconnectScanCallback() { // from class: com.huawei.health.manager.reconnect.ReconnectManager.2
            @Override // com.huawei.health.manager.reconnect.BleReconnectScanCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                ReleaseLogUtil.e("DEVMGR_ReconnectManager", "Enter onLeScan, find want device.");
                ReconnectManager.this.alm_(bluetoothDevice, 3);
            }
        };
        this.d = new ServiceConnection() { // from class: com.huawei.health.manager.reconnect.ReconnectManager.3
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LogUtil.a("ReconnectManager", "onServiceConnected success!");
                ReconnectManager.this.e = true;
                ReconnectManager.this.d();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtil.a("ReconnectManager", "onServiceDisconnected success!");
                ReconnectManager.this.e = false;
                ReconnectManager.this.a();
            }
        };
        this.b = new BroadcastReceiver() { // from class: com.huawei.health.manager.reconnect.ReconnectManager.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a("ReconnectManager", "mDeviceStatusReceiver intent is ", intent.getAction());
                if (context == null) {
                    LogUtil.h("ReconnectManager", "mDeviceStatusReceiver context is null.");
                    return;
                }
                if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    ReleaseLogUtil.e("DEVMGR_ReconnectManager", "mDeviceStatusReceiver is not stateChange.");
                    return;
                }
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("ReconnectManager", "mDeviceStatusReceiver DeviceInfo error");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                LogUtil.a("ReconnectManager", "mDeviceStatusReceiver deviceName: ", deviceInfo.getDeviceName(), ", state is ", Integer.valueOf(deviceConnectState));
                if (deviceConnectState == 2) {
                    jdh.d().a(10001);
                    ReconnectManager.this.g();
                }
            }
        };
        LogUtil.a("ReconnectManager", "ReconnectManager create");
    }

    public void e() {
        Context e = BaseApplication.e();
        String b = SharedPreferenceManager.b(e, Integer.toString(101010), "FIRST_CREATE_DEMO_SERVICE");
        if (b == null || b.isEmpty() || Boolean.toString(Boolean.TRUE.booleanValue()).equals(b)) {
            LogUtil.a("ReconnectManager", "isFirstInit");
            if (!c(e) && NotificationManagerCompat.from(e).areNotificationsEnabled() && DeviceUtil.a() && !BaseApplication.j()) {
                LogUtil.a("ReconnectManager", "has nearByPermission,", c(e) + "isNotificationEnabled, ", NotificationManagerCompat.from(e).areNotificationsEnabled() + "has deviceInfo,", Boolean.valueOf(DeviceUtil.a()));
                f();
                i(e);
                SharedPreferenceManager.e(e, Integer.toString(101010), "FIRST_CREATE_DEMO_SERVICE", Boolean.toString(Boolean.FALSE.booleanValue()), (StorageParams) null);
            }
        }
        LogUtil.a("ReconnectManager", "ReconnectManager init");
        BtCommonAdapterUtil.d().b(this.c);
        a();
    }

    private void i(Context context) {
        String string = context.getResources().getString(R.string.IDS_device_reconnect_remind);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentText(string);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setStyle(new Notification.BigTextStyle().bigText(string));
        xf_.setContentIntent(aln_(context));
        xf_.setAutoCancel(true);
        jdh.d().xh_(10001, xf_.build());
    }

    private PendingIntent aln_(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        intent.setFlags(536870912);
        intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
        return PendingIntent.getActivity(BaseApplication.e(), 0, intent, AppRouterExtras.COLDSTART);
    }

    private void f() {
        BroadcastManagerUtil.bFC_(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), this.b, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            LogUtil.a("ReconnectManager", "Enter unregisterBindDeviceBroadcast()!");
            com.huawei.hwcommonmodel.application.BaseApplication.getContext().unregisterReceiver(this.b);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("ReconnectManager", "unregisterBindDeviceBroadcast failed");
        }
    }

    public static boolean c(Context context) {
        if (Build.VERSION.SDK_INT <= 30) {
            return true;
        }
        if (bky.a()) {
            return e(context);
        }
        return PermissionUtil.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
    }

    private static boolean e(Context context) {
        PackageInfo packageInfo;
        String packageName = context.getPackageName();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (packageInfo = packageManager.getPackageInfo(packageName, 4096)) == null) {
                return true;
            }
            Object b = ReflectionUtils.b(packageManager, "FLAG_PERMISSION_REVOKE_ON_UPGRADE");
            int intValue = b instanceof Integer ? ((Integer) b).intValue() : 8;
            String[] strArr = packageInfo.requestedPermissions;
            if (strArr == null) {
                return true;
            }
            int i = 0;
            while (true) {
                if (i >= strArr.length) {
                    i = 0;
                    break;
                }
                if (strArr[i].equals("android.permission.BLUETOOTH_SCAN")) {
                    break;
                }
                i++;
            }
            int i2 = packageInfo.requestedPermissionsFlags[i];
            Method method = packageManager.getClass().getMethod("getPermissionFlags", String.class, String.class, UserHandle.class);
            Constructor constructor = UserHandle.class.getConstructor(Integer.TYPE);
            constructor.setAccessible(true);
            Object c = ReflectionUtils.c(method, packageManager, "android.permission.BLUETOOTH_SCAN", packageName, (UserHandle) constructor.newInstance(0));
            if (c instanceof Integer) {
                return (i2 & 2) != 0 && (((Integer) c).intValue() & intValue) == 0;
            }
            return true;
        } catch (PackageManager.NameNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            LogUtil.b("ReconnectManager", "checkScanPermission ex:", ExceptionUtils.d(e));
            return true;
        }
    }

    public void a() {
        if (this.e) {
            LogUtil.a("ReconnectManager", "startReconnect PhoneService running.");
            return;
        }
        if (CommonUtil.ai(BaseApplication.e())) {
            LogUtil.a("ReconnectManager", "startReconnect PhoneService running.");
            d(BaseApplication.e());
        } else if (tno.d().isEmpty()) {
            LogUtil.a("ReconnectManager", "ReconnectManager init devices isEmpty");
        } else if (!BtCommonAdapterUtil.d().c()) {
            LogUtil.a("ReconnectManager", "init bluetooth state not on");
        } else {
            BtCommonAdapterUtil.d().b();
            i();
        }
    }

    public void d() {
        BtCommonAdapterUtil.d().e();
        BleReconnectManager.b().j();
    }

    public static ReconnectManager c() {
        return InstanceHolder.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if ("010303".equals(str2) && "1".equals(str)) {
            return true;
        }
        return "010505".equals(str2) && "2".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void alm_(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.h("ReconnectManager", "btDevice is invalid, connect fail.");
        } else {
            ReleaseLogUtil.e("DEVMGR_ReconnectManager", "ready to connect device is ", CommonUtil.l(bluetoothDevice.getAddress()), "reconnectType: ", Integer.valueOf(i));
            alp_(bluetoothDevice, BaseApplication.e());
        }
    }

    private void i() {
        ReleaseLogUtil.e("DEVMGR_ReconnectManager", "startBleReconnect start reconnect ble device.");
        BleReconnectManager.b().c(this.f2796a);
    }

    Map<String, Map<String, String>> b() {
        return this.e ? Collections.EMPTY_MAP : tno.e();
    }

    public void d(final Context context) {
        if (this.e) {
            LogUtil.a("ReconnectManager", "PhoneService isBound.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.manager.reconnect.ReconnectManager.5
                @Override // java.lang.Runnable
                public void run() {
                    Context context2 = context;
                    context2.bindService(ReconnectManager.this.alo_(context2), ReconnectManager.this.d, 37);
                }
            });
        }
    }

    public void b(Context context) {
        if (this.e) {
            LogUtil.a("ReconnectManager", "PhoneService isBound.");
        } else if (CommonUtil.ai(context)) {
            LogUtil.a("ReconnectManager", "startReconnect PhoneService running.");
            d(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent alo_(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.StartPhoneService");
        intent.setPackage(context.getPackageName());
        return intent;
    }

    private void alp_(BluetoothDevice bluetoothDevice, Context context) {
        if (this.e) {
            LogUtil.h("ReconnectManager", "startPhoneService PhoneService running.");
            return;
        }
        Intent alo_ = alo_(context);
        alo_.putExtra("reconnect", "reconnect");
        try {
            context.startService(alo_);
        } catch (IllegalStateException | SecurityException unused) {
            LogUtil.h("ReconnectManager", "start phone service fail");
        }
    }

    static class InstanceHolder {
        private static final ReconnectManager e = new ReconnectManager();

        private InstanceHolder() {
        }
    }
}
