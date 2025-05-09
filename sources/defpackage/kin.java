package defpackage;

import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.AndroidRuntimeException;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.watchface.api.WebViewActivity;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.MagicBuild;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class kin extends HwBaseManager implements ParserInterface {
    private static kin e;
    private Context f;
    private boolean g;
    private boolean h;
    private boolean i;
    private ServiceConnection j;
    private String k;
    private DeviceInfo l;
    private Handler n;
    private BroadcastReceiver o;
    private static final Object c = new Object();
    private static final Uri b = Uri.parse("content://com.huawei.dmsdp.provider/camera_remote_ctrl");

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f14398a = Uri.parse("content://com.huawei.camera.remotecallconfirmProvider/device");
    private static final Map<String, Long> d = new ConcurrentHashMap(0);

    private kin() {
        super(BaseApplication.getContext());
        this.h = false;
        this.g = false;
        this.i = false;
        this.n = new Handler(Looper.getMainLooper()) { // from class: kin.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 20000:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_OPEN_CAMERA");
                        kin.this.e();
                        kin.this.e("android.media.action.STILL_IMAGE_CAMERA");
                        break;
                    case 20001:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_OPEN_CAMERA_SECURE");
                        kin.this.e();
                        kin.this.e("android.media.action.STILL_IMAGE_CAMERA_SECURE");
                        break;
                    case 20002:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_TAKE_PICTURE");
                        kin.this.f();
                        break;
                    case 20003:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_UNREGISTER_CAMERA_BROADCAST");
                        kin.this.h();
                        break;
                    case 20004:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_CAMERA_STOP");
                        kin.this.d(2);
                        break;
                    case 20005:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_BACK_TO_HOME");
                        kin.this.e();
                        break;
                    case 20006:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_OPEN_CAMERA_AUTH");
                        kin kinVar = kin.this;
                        kinVar.e(kinVar.k);
                        break;
                    case 20007:
                        LogUtil.a("RemotePhoto", "handleMessage what = HANDLER_ACTION_CAMERA_FORBIDDEN");
                        kin.this.d(3);
                        break;
                    default:
                        LogUtil.a("RemotePhoto", "other handleMessage");
                        break;
                }
            }
        };
        this.o = new BroadcastReceiver() { // from class: kin.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null || context == null) {
                    LogUtil.h("RemotePhoto", "intent or context is null");
                    return;
                }
                LogUtil.a("RemotePhoto", "mRemotePhotoCastReceiver intent:", intent.getAction());
                String action = intent.getAction();
                if (action == null) {
                    LogUtil.h("RemotePhoto", "action is null");
                    return;
                }
                if (action.equals("com.huawei.remote.camera.open")) {
                    kin.d.put(action, Long.valueOf(System.currentTimeMillis()));
                }
                if (action.equals("com.huawei.remotecontrol.stop")) {
                    long currentTimeMillis = System.currentTimeMillis();
                    Long l = (Long) kin.d.get("com.huawei.remote.camera.open");
                    if (l != null && Math.abs(currentTimeMillis - l.longValue()) < 1000 && action.equals("com.huawei.remotecontrol.stop")) {
                        LogUtil.a("RemotePhoto", "The time for enabling and disabling the broadcast is less than 1s.");
                        kin.d.remove("com.huawei.remote.camera.open");
                        return;
                    }
                }
                if (kin.this.h) {
                    e(action);
                } else {
                    b(action);
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            private void b(String str) {
                char c2;
                str.hashCode();
                switch (str.hashCode()) {
                    case -993706567:
                        if (str.equals("com.hihonor.remotecontrol.start")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 11914867:
                        if (str.equals("com.huawei.remotecontrol.stop")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 369347601:
                        if (str.equals("com.huawei.remotecontrol.start")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1353418699:
                        if (str.equals("com.hihonor.remotecontrol.stop")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                if (c2 != 0) {
                    if (c2 != 1) {
                        if (c2 != 2) {
                            if (c2 != 3) {
                                LogUtil.h("RemotePhoto", "Other action");
                                return;
                            }
                        }
                    }
                    LogUtil.a("RemotePhoto", "Camera stop");
                    kin.this.n.removeMessages(20004);
                    kin.this.n.sendEmptyMessageDelayed(20004, 1000L);
                    kin.this.n.removeMessages(20003);
                    kin.this.n.sendEmptyMessageDelayed(20003, PreConnectManager.CONNECT_INTERNAL);
                    return;
                }
                LogUtil.a("RemotePhoto", "Camera start");
                if (kin.this.i) {
                    kin.this.d(1);
                }
                kin.this.i = false;
                kin.this.n.removeMessages(20004);
                kin.this.n.removeMessages(20003);
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            private void e(String str) {
                char c2;
                str.hashCode();
                switch (str.hashCode()) {
                    case -993706567:
                        if (str.equals("com.hihonor.remotecontrol.start")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -75681612:
                        if (str.equals("android.intent.action.FORBIDDEN")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 11914867:
                        if (str.equals("com.huawei.remotecontrol.stop")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 369347601:
                        if (str.equals("com.huawei.remotecontrol.start")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1353418699:
                        if (str.equals("com.hihonor.remotecontrol.stop")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                if (c2 != 0) {
                    if (c2 == 1) {
                        LogUtil.a("RemotePhoto", "Camera forbidden");
                        kin.this.n.sendEmptyMessage(20007);
                    } else if (c2 != 2) {
                        if (c2 != 3) {
                            if (c2 != 4) {
                                LogUtil.h("RemotePhoto", "Other action");
                                return;
                            }
                        }
                    }
                    LogUtil.a("RemotePhoto", "Camera stop");
                    kin.this.g = false;
                    kin.this.i = false;
                    kin.this.n.removeMessages(20004);
                    kin.this.n.sendEmptyMessageDelayed(20004, 1000L);
                    kin.this.n.removeMessages(20003);
                    kin.this.n.sendEmptyMessage(20003);
                    return;
                }
                kin kinVar = kin.this;
                if (!ArrayUtils.contains(new int[]{1, 0}, kinVar.a(kinVar.l.getDeviceUdid()))) {
                    LogUtil.a("RemotePhoto", "can not start remote camera");
                    return;
                }
                LogUtil.a("RemotePhoto", "Camera start");
                kin.this.g = true;
                if (kin.this.i) {
                    kin.this.d(1);
                }
                kin.this.i = false;
                kin.this.n.removeMessages(20004);
                kin.this.n.removeMessages(20003);
            }
        };
        this.j = new ServiceConnection() { // from class: kin.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LogUtil.a("RemotePhoto", "DMSDPService connect success");
                BaseApplication.getContext().unbindService(kin.this.j);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtil.a("RemotePhoto", "DMSDPService connect fail");
            }
        };
        LogUtil.a("RemotePhoto", "enter RemoteCameraManager");
        this.f = BaseApplication.getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (CommonUtil.bi() || CommonUtil.bf()) {
            Intent intent = new Intent("android.intent.action.BLUETOOTH_BUTTON_COMMAND");
            if (MagicBuild.f13130a && MagicBuild.d >= 33) {
                LogUtil.a("RemotePhoto", "new honor os");
                BaseApplication.getContext().sendBroadcast(intent, "com.hihonor.camera.permission.PRIVATE");
                return;
            } else {
                LogUtil.a("RemotePhoto", "hw os");
                BaseApplication.getContext().sendBroadcast(intent, "com.huawei.camera.permission.PRIVATE");
                return;
            }
        }
        LogUtil.a("RemotePhoto", "not more than EMUI 8.0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("RemotePhoto", "backToHome enter");
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("RemotePhoto", "backToHome ActivityNotFoundException");
            sqo.ag("backToHome ActivityNotFoundException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        try {
            if (!CommonUtil.bi() && !CommonUtil.bf()) {
                LogUtil.a("RemotePhoto", "handleOpenCamera not more than EMUI 8.0 and no honor");
                sqo.ag("handleOpenCamera not more than EMUI 8.0 and no honor");
                return;
            }
            Intent intent = new Intent(str);
            intent.addFlags(268435456);
            intent.addFlags(AppRouterExtras.COLDSTART);
            if (this.h) {
                intent.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
                Bundle bundle = new Bundle();
                bundle.putString("udid", this.l.getDeviceUdid());
                bundle.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, this.l.getDeviceName());
                intent.putExtras(bundle);
            } else {
                intent.putExtra("startFrom", "health");
            }
            PackageManager packageManager = this.f.getPackageManager();
            if (packageManager != null) {
                ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
                if (resolveActivity != null && resolveActivity.activityInfo != null) {
                    intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                }
                LogUtil.h("RemotePhoto", "resolveInfo or resolveInfo.activityInfo is null");
                sqo.ag("resolveInfo or resolveInfo.activityInfo is null");
                return;
            }
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException | AndroidRuntimeException unused) {
            LogUtil.b("RemotePhoto", "handleOpenCamera ActivityNotFoundException");
            sqo.ag("handleOpenCamera ActivityNotFoundException");
        }
    }

    private boolean c(String str) {
        LogUtil.a("RemotePhoto", "isSupportAuthAction: ", str);
        Intent intent = new Intent(str);
        PackageManager packageManager = this.f.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                LogUtil.a("RemotePhoto", "resolveInfo or resolveInfo.activityInfo is null, isSupportAuthAction is false");
                return false;
            }
            LogUtil.a("RemotePhoto", "isSupportAuthAction is true");
            return true;
        }
        LogUtil.a("RemotePhoto", "packageManager is null, isSupportAuthAction is false");
        return false;
    }

    private boolean i() {
        if (c("com.huawei.camera.intent.RemoteCall")) {
            this.k = "com.huawei.camera.intent.RemoteCall";
        } else {
            if (!c("android.intent.action.RemoteCall")) {
                return false;
            }
            this.k = "android.intent.action.RemoteCall";
        }
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo.setSportVersion(this.l.getSportVersion());
        deviceInfo.setDeviceMac(this.l.getDeviceIdentify());
        boolean c2 = blo.c(deviceInfo);
        boolean z = this.l.getPowerSaveModel() == 0;
        LogUtil.a("RemotePhoto", "isSmartWatch is ", Boolean.valueOf(c2), ", isPowerSaveNo is ", Boolean.valueOf(z));
        return c2 && z;
    }

    public static kin b() {
        kin kinVar;
        synchronized (c) {
            if (e == null) {
                e = new kin();
            }
            kinVar = e;
        }
        return kinVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 2001;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("RemotePhoto", bArr, "getResult: ");
        if (deviceInfo == null || bArr == null || bArr.length < 2) {
            LogUtil.h("RemotePhoto", "device or dataContents is null");
            return;
        }
        if (!b(deviceInfo)) {
            LogUtil.h("RemotePhoto", "device not support remoteCamera.");
            return;
        }
        String d2 = cvx.d(bArr);
        this.l = deviceInfo;
        if (d2 == null || 4 > d2.length()) {
            return;
        }
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4, d2.length())).e();
            byte b2 = bArr[1];
            if (b2 == 41) {
                e(bArr, e2, deviceInfo);
            } else if (b2 != 42) {
                LogUtil.h("RemotePhoto", "handleOtherDeviceResult default commandId :", Byte.valueOf(b2));
            } else {
                LogUtil.a("RemotePhoto", "COMMAND_ID_REMOTE_CAMERA_STATUS ");
            }
        } catch (cwg unused) {
            LogUtil.b("RemotePhoto", "TlvException");
            sqo.ag("TlvException");
        } catch (IndexOutOfBoundsException unused2) {
            LogUtil.b("RemotePhoto", "IndexOutOfBoundsException");
            sqo.ag("IndexOutOfBoundsException");
        }
    }

    private void i(final DeviceInfo deviceInfo) {
        LogUtil.a("RemotePhoto", "openCamera() enter");
        this.i = true;
        boolean i = i();
        this.h = i;
        LogUtil.a("RemotePhoto", "isNewAppRule is ", Boolean.valueOf(i));
        ThreadPoolManager.d().execute(new Runnable() { // from class: kin.5
            @Override // java.lang.Runnable
            public void run() {
                kin.this.n.removeMessages(20003);
                kin.this.g();
                kin.this.a(100000, deviceInfo);
                Message obtain = Message.obtain();
                if (kin.this.h) {
                    Intent intent = new Intent();
                    intent.setAction("com.huawei.remote.camera.open");
                    kin.this.f.sendBroadcast(intent, WebViewActivity.HW_SIGNATURE_OR_SYSTEM);
                    obtain.what = 20006;
                } else {
                    kin kinVar = kin.this;
                    if (kinVar.b(kinVar.f)) {
                        kin kinVar2 = kin.this;
                        kinVar2.d(kinVar2.f);
                        obtain.what = 20001;
                    } else {
                        obtain.what = 20000;
                    }
                }
                kin.this.n.sendMessage(obtain);
            }
        });
    }

    private void f(final DeviceInfo deviceInfo) {
        LogUtil.a("RemotePhoto", "takePicture() enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: kin.4
            @Override // java.lang.Runnable
            public void run() {
                kin.this.a(100000, deviceInfo);
                Message obtain = Message.obtain();
                obtain.what = 20002;
                kin.this.n.sendMessage(obtain);
            }
        });
    }

    private void c(DeviceInfo deviceInfo) {
        LogUtil.a("RemotePhoto", "closeCamera() enter");
        a(100000, deviceInfo);
        if (this.h) {
            Intent intent = new Intent();
            intent.setAction("com.huawei.remote.camera.exit");
            intent.putExtra("is_control_camera_doing", this.g);
            this.f.sendBroadcast(intent, WebViewActivity.HW_SIGNATURE_OR_SYSTEM);
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 20005;
        this.n.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (CommonUtil.bi() || CommonUtil.bf()) {
            IntentFilter intentFilter = new IntentFilter();
            if (MagicBuild.f13130a && MagicBuild.d >= 33) {
                LogUtil.a("RemotePhoto", "enter new honor");
                intentFilter.addAction("com.hihonor.remotecontrol.start");
                intentFilter.addAction("com.hihonor.remotecontrol.stop");
                BroadcastManagerUtil.bFC_(this.f, this.o, intentFilter, "com.hihonor.camera.permission.REMOTECONTROLLER", null);
                return;
            }
            LogUtil.a("RemotePhoto", "enter hw");
            intentFilter.addAction("com.huawei.remotecontrol.start");
            intentFilter.addAction("com.huawei.remotecontrol.stop");
            intentFilter.addAction("android.intent.action.FORBIDDEN");
            intentFilter.addAction("com.huawei.remote.camera.open");
            intentFilter.addAction("com.huawei.remote.camera.exit");
            BroadcastManagerUtil.bFC_(this.f, this.o, intentFilter, "com.huawei.camera.permission.REMOTECONTROLLER", null);
            return;
        }
        LogUtil.a("RemotePhoto", "not more than EMUI 8.0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("RemotePhoto", "unregisterRemotePhotoCastBroadcast enter");
        try {
            this.f.unregisterReceiver(this.o);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("RemotePhoto", "unregisterRemotePhotoCastBroadcast exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        LogUtil.a("RemotePhoto", "wakeUpPhone enter");
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (!powerManager.isScreenOn()) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(268435466, "RemotePhoto");
            newWakeLock.acquire(3000L);
            newWakeLock.release();
            return;
        }
        LogUtil.a("RemotePhoto", "screen On");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context) {
        LogUtil.a("RemotePhoto", "isScreenClosed enter");
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    private void e(byte[] bArr, List<cwd> list, DeviceInfo deviceInfo) {
        if (bArr == null || list == null) {
            return;
        }
        for (cwd cwdVar : list) {
            try {
                if (CommonUtil.w(cwdVar.e()) == 1) {
                    e(cwdVar, deviceInfo);
                } else {
                    LogUtil.h("RemotePhoto", "Invalid control types");
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("RemotePhoto", "remoteCameraControl NumberFormatException");
                sqo.ag("remoteCameraControl NumberFormatException");
            }
        }
    }

    private void e(cwd cwdVar, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_RemotePhoto", "cameraControl() enter");
        if (cwdVar == null) {
            return;
        }
        int w = CommonUtil.w(cwdVar.c());
        if (w == 1) {
            i(deviceInfo);
            return;
        }
        if (w == 2) {
            f(deviceInfo);
        } else if (w == 3) {
            c(deviceInfo);
        } else {
            LogUtil.h("RemotePhoto", "other tlv value");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("RemotePhoto", "sendCameraControlResponse");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(41);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(127) + cvx.e(4) + cvx.e(i));
        byte[] a2 = cvx.a(stringBuffer.toString());
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("RemotePhoto", "sendCameraStatus :", Integer.valueOf(i));
        DeviceCapability d2 = d(this.l);
        if (d2 == null || !d2.isSupportRemoteCamera()) {
            LogUtil.h("RemotePhoto", "device not support remoteCamera.");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(42);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1) + cvx.e(1) + cvx.e(i));
        byte[] a2 = cvx.a(stringBuffer.toString());
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("RemotePhoto", "isSupportControlCapability deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 28);
    }

    private boolean b(DeviceInfo deviceInfo) {
        DeviceCapability d2 = d(deviceInfo);
        return d2 != null && d2.isSupportRemoteCamera();
    }

    private DeviceCapability d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("RemotePhoto", "getCapability, device is null");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "RemotePhoto");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    public void e(DeviceInfo deviceInfo) {
        boolean e2 = kik.e();
        LogUtil.a("RemotePhoto", "isPullCameraService: ", Boolean.valueOf(e2));
        if (e2) {
            c(deviceInfo, d());
        } else {
            c(deviceInfo, kik.b());
        }
    }

    private boolean d() {
        boolean z;
        Intent intent = new Intent();
        intent.setAction("com.huawei.dmsdp.DMSDP_CAMERA_REMOTE_CTRL");
        intent.setPackage("com.huawei.dmsdp");
        try {
            z = BaseApplication.getContext().bindService(intent, this.j, 1);
        } catch (SecurityException e2) {
            LogUtil.b("RemotePhoto", "bind ServiceStatus exception ", LogAnonymous.b((Throwable) e2));
            z = false;
        }
        boolean c2 = !z ? c() : z;
        LogUtil.a("RemotePhoto", "bindServiceStatus is: ", Boolean.valueOf(z), "isCameraCapability is: ", Boolean.valueOf(c2));
        kik.a(c2);
        kik.b(kik.d());
        return c2;
    }

    public void c(DeviceInfo deviceInfo, boolean z) {
        LogUtil.a("RemotePhoto", "camera capability is", Boolean.valueOf(z));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(42);
        StringBuilder sb = new StringBuilder(16);
        String str = cvx.e(1) + cvx.e(1) + cvx.e(0);
        String str2 = cvx.e(2) + cvx.e(1) + cvx.e(z ? 1 : 0);
        sb.append(str);
        sb.append(str2);
        byte[] a2 = cvx.a(sb.toString());
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        blt.d("RemotePhoto", deviceCommand.getDataContent(), "sendDeviceToCameraCapability command: ");
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private boolean c() {
        try {
            Cursor query = BaseApplication.getContext().getContentResolver().query(b, null, null, null, null);
            boolean z = query != null;
            if (query != null) {
                query.close();
            }
            return z;
        } catch (SQLException unused) {
            LogUtil.b("RemotePhoto", "checkCameraCapability SQLException");
            return false;
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("RemotePhoto", "checkCameraCapability IllegalArgumentException");
            return false;
        } catch (SecurityException unused3) {
            LogUtil.b("RemotePhoto", "checkCameraCapability SecurityException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0051, code lost:
    
        if (r0 != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0073, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
    
        if (0 == 0) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(java.lang.String r12) {
        /*
            r11 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            r1 = -1
            java.lang.String r2 = "RemotePhoto"
            if (r0 == 0) goto L13
            java.lang.String r12 = "isCameraAuthorized deviceUdid is empty"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r12)
            return r1
        L13:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.ContentResolver r3 = r0.getContentResolver()
            r0 = 0
            r9 = 0
            r10 = 1
            android.net.Uri r4 = defpackage.kin.f14398a     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            r5 = 0
            r7 = 0
            r8 = 0
            r6 = r12
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            if (r0 == 0) goto L54
            int r12 = r0.getCount()     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            if (r12 != 0) goto L31
            goto L54
        L31:
            boolean r12 = r0.moveToNext()     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            if (r12 == 0) goto L51
            int r12 = r0.getInt(r10)     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            java.lang.String r4 = "getCameraAuthStatus camera status is "
            r3[r9] = r4     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            r3[r10] = r4     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            com.huawei.hwlogsmodel.LogUtil.a(r2, r3)     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            if (r0 == 0) goto L50
            r0.close()
        L50:
            return r12
        L51:
            if (r0 == 0) goto L73
            goto L70
        L54:
            java.lang.Object[] r12 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            java.lang.String r3 = "isCameraAuthorized cursor is null"
            r12[r9] = r3     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            com.huawei.hwlogsmodel.LogUtil.h(r2, r12)     // Catch: java.lang.Throwable -> L63 android.database.SQLException -> L65
            if (r0 == 0) goto L62
            r0.close()
        L62:
            return r1
        L63:
            r12 = move-exception
            goto L74
        L65:
            java.lang.Object[] r12 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> L63
            java.lang.String r3 = "isCameraAuthorized SQLException"
            r12[r9] = r3     // Catch: java.lang.Throwable -> L63
            com.huawei.hwlogsmodel.LogUtil.b(r2, r12)     // Catch: java.lang.Throwable -> L63
            if (r0 == 0) goto L73
        L70:
            r0.close()
        L73:
            return r1
        L74:
            if (r0 == 0) goto L79
            r0.close()
        L79:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kin.a(java.lang.String):int");
    }
}
