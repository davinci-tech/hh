package com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.cun;
import defpackage.cvt;
import health.compact.a.BroadcastManagerUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ScoUtils {

    /* renamed from: a, reason: collision with root package name */
    private static ScoUtils f6344a;
    private static final Object b = new Object();
    private AudioManager c;
    private BluetoothDevice e;
    private c g;
    private Context h;
    private BluetoothHeadset j;
    private OnScoConnectListener f = null;
    private boolean i = false;
    private int k = 0;
    private BluetoothProfile.ServiceListener d = new BluetoothProfile.ServiceListener() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.ScoUtils.3
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            LogUtil.a("HwVoiceKitManager_ScoUtils", "onServiceDisconnected:", Integer.valueOf(i));
            if (i == 1) {
                if (ScoUtils.this.j != null) {
                    try {
                        ScoUtils.this.j.stopVoiceRecognition(ScoUtils.this.e);
                    } catch (SecurityException e) {
                        LogUtil.b("HwVoiceKitManager_ScoUtils", "onServiceDisconnected SecurityException:", ExceptionUtils.d(e));
                    }
                }
                ScoUtils.this.j = null;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogUtil.a("HwVoiceKitManager_ScoUtils", "onServiceConnected:", Integer.valueOf(i));
            if (i == 1) {
                if (bluetoothProfile instanceof BluetoothHeadset) {
                    try {
                        ScoUtils.this.j = (BluetoothHeadset) bluetoothProfile;
                        DeviceInfo deviceInfo = null;
                        ScoUtils.this.e = null;
                        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwVoiceKitManager_ScoUtils");
                        if (deviceList.size() > 0) {
                            Iterator<DeviceInfo> it = deviceList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                DeviceInfo next = it.next();
                                if (!cvt.c(next.getProductType())) {
                                    deviceInfo = next;
                                    break;
                                }
                            }
                        }
                        if (deviceInfo != null) {
                            List<BluetoothDevice> connectedDevices = ScoUtils.this.j.getConnectedDevices();
                            if (connectedDevices != null && !connectedDevices.isEmpty()) {
                                LogUtil.a("HwVoiceKitManager_ScoUtils", "getConnectedDevices size:", Integer.valueOf(ScoUtils.this.j.getConnectedDevices().size()));
                                for (BluetoothDevice bluetoothDevice : connectedDevices) {
                                    if (bluetoothDevice.getAddress().equals(deviceInfo.getDeviceIdentify())) {
                                        ScoUtils.this.e = bluetoothDevice;
                                    }
                                }
                                if (ScoUtils.this.e != null) {
                                    ScoUtils.this.i();
                                    return;
                                } else {
                                    LogUtil.h("HwVoiceKitManager_ScoUtils", "onServiceConnected mBluetoothDevice is null");
                                    return;
                                }
                            }
                            LogUtil.h("HwVoiceKitManager_ScoUtils", "onServiceConnected connectedDeviceList is null or isEmpty");
                            return;
                        }
                        LogUtil.a("HwVoiceKitManager_ScoUtils", "onServiceConnected deviceInfo is null");
                        return;
                    } catch (SecurityException e) {
                        LogUtil.b("HwVoiceKitManager_ScoUtils", "onServiceConnected SecurityException:", ExceptionUtils.d(e));
                        return;
                    }
                }
                LogUtil.h("HwVoiceKitManager_ScoUtils", "onServiceConnected proxy not instanceof BluetoothHeadset");
            }
        }
    };
    private BroadcastReceiver l = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.ScoUtils.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("HwVoiceKitManager_ScoUtils", "onReceive intent is null");
                return;
            }
            if ("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                LogUtil.a("HwVoiceKitManager_ScoUtils", "onReceive state:", Integer.valueOf(intExtra));
                ScoUtils.this.k = intExtra;
                if (intExtra == 12) {
                    ScoUtils.this.g.removeMessages(1);
                    if (ScoUtils.this.f != null) {
                        ScoUtils.this.f.onConnectSuccess();
                        ScoUtils.this.c.adjustStreamVolume(3, -100, 0);
                        return;
                    }
                    return;
                }
                if (intExtra == 10) {
                    if (ScoUtils.this.f != null) {
                        ScoUtils.this.f.onConnectClose();
                        return;
                    }
                    return;
                }
                LogUtil.h("HwVoiceKitManager_ScoUtils", "scoReceiver other state");
            }
        }
    };

    public interface OnScoConnectListener {
        void connectTimeout();

        void onConnectClose();

        void onConnectSuccess();
    }

    private ScoUtils(Context context) {
        this.h = context;
        Object systemService = context.getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            this.c = (AudioManager) systemService;
        }
        HandlerThread handlerThread = new HandlerThread("HwVoiceKitManager_ScoUtils");
        handlerThread.start();
        this.g = new c(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            Method method = Class.forName("android.bluetooth.BluetoothHeadset").getMethod("setActiveDevice", BluetoothDevice.class);
            method.invoke(this.j, this.e);
            LogUtil.a("HwVoiceKitManager_ScoUtils", "method: ", method);
            h();
            this.g.sendEmptyMessageDelayed(2, 200L);
        } catch (ClassNotFoundException unused) {
            LogUtil.b("HwVoiceKitManager_ScoUtils", "onServiceConnected ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            LogUtil.b("HwVoiceKitManager_ScoUtils", "onServiceConnected IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            LogUtil.b("HwVoiceKitManager_ScoUtils", "onServiceConnected NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            LogUtil.b("HwVoiceKitManager_ScoUtils", "onServiceConnected InvocationTargetException");
        }
    }

    public static ScoUtils e(Context context) {
        ScoUtils scoUtils;
        synchronized (b) {
            if (f6344a == null) {
                f6344a = new ScoUtils(context);
            }
            scoUtils = f6344a;
        }
        return scoUtils;
    }

    private void b() {
        LogUtil.a("HwVoiceKitManager_ScoUtils", "initBlueToothHeadset");
        Object systemService = this.h.getSystemService("bluetooth");
        if (systemService instanceof BluetoothManager) {
            ((BluetoothManager) systemService).getAdapter().getProfileProxy(this.h, this.d, 1);
        } else {
            LogUtil.h("HwVoiceKitManager_ScoUtils", "service is null");
        }
    }

    private void h() {
        LogUtil.a("HwVoiceKitManager_ScoUtils", "registerReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        if (this.i) {
            return;
        }
        BroadcastManagerUtil.bFB_(this.h, this.l, intentFilter);
        this.i = true;
    }

    public void e() {
        if (!this.c.isBluetoothScoAvailableOffCall()) {
            LogUtil.h("HwVoiceKitManager_ScoUtils", "system not support audio");
            return;
        }
        if (d()) {
            LogUtil.a("HwVoiceKitManager_ScoUtils", "openSco isScoOn is true");
            c();
        }
        LogUtil.a("HwVoiceKitManager_ScoUtils", "openSco");
        this.c.setMode(2);
        b();
        this.g.sendEmptyMessageDelayed(1, PreConnectManager.CONNECT_INTERNAL);
    }

    public void b(OnScoConnectListener onScoConnectListener) {
        if (onScoConnectListener != null) {
            this.f = onScoConnectListener;
        }
    }

    public void c() {
        BluetoothDevice bluetoothDevice;
        LogUtil.a("HwVoiceKitManager_ScoUtils", "closeSco mIsRegister: ", Boolean.valueOf(this.i));
        this.g.removeMessages(1);
        if (this.i) {
            this.i = false;
            try {
                this.h.unregisterReceiver(this.l);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("HwVoiceKitManager_ScoUtils", "closeSco IllegalArgumentException");
            }
        }
        try {
            this.c.setMode(0);
            this.c.adjustStreamVolume(3, 100, 0);
            BluetoothHeadset bluetoothHeadset = this.j;
            if (bluetoothHeadset != null && (bluetoothDevice = this.e) != null) {
                bluetoothHeadset.stopVoiceRecognition(bluetoothDevice);
                LogUtil.a("HwVoiceKitManager_ScoUtils", "closeSco stopVoiceRecognition");
            }
            this.k = 0;
            LogUtil.a("HwVoiceKitManager_ScoUtils", "closeSco: isBluetoothScoOn:", Boolean.valueOf(this.c.isBluetoothScoOn()));
        } catch (SecurityException e) {
            LogUtil.b("HwVoiceKitManager_ScoUtils", "closeSco SecurityException:", ExceptionUtils.d(e));
        }
    }

    public boolean d() {
        return this.c.isBluetoothScoOn();
    }

    public int a() {
        return this.k;
    }

    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("HwVoiceKitManager_ScoUtils", "handleMessage msg is null");
                return;
            }
            super.handleMessage(message);
            try {
                int i = message.what;
                if (i == 1) {
                    LogUtil.a("HwVoiceKitManager_ScoUtils", "handleMessage:", Boolean.valueOf(ScoUtils.this.c.isBluetoothScoOn()));
                    if (ScoUtils.this.f != null) {
                        ScoUtils.this.f.connectTimeout();
                    }
                    ScoUtils.this.c();
                    return;
                }
                if (i == 2) {
                    if (ScoUtils.this.j != null && ScoUtils.this.e != null) {
                        if (ScoUtils.this.c != null) {
                            ScoUtils.this.c.setSpeakerphoneOn(false);
                        }
                        if (ScoUtils.this.j != null) {
                            ScoUtils.this.j.startVoiceRecognition(ScoUtils.this.e);
                            LogUtil.a("HwVoiceKitManager_ScoUtils", "startVoiceRecognition");
                            return;
                        }
                        return;
                    }
                    LogUtil.h("HwVoiceKitManager_ScoUtils", "handleMessage START_SCO_WHAT is null");
                    return;
                }
                LogUtil.h("HwVoiceKitManager_ScoUtils", "handleMessage default");
            } catch (SecurityException e) {
                LogUtil.b("HwVoiceKitManager_ScoUtils", "handleMessage SecurityException:", ExceptionUtils.d(e));
            }
        }
    }
}
