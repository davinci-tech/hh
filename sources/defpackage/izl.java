package defpackage;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class izl {

    /* renamed from: a, reason: collision with root package name */
    private int f13684a = 2000;
    private boolean d = true;
    private izi i = null;
    private boolean j = false;
    private DeviceInfo b = null;
    private ExtendHandler g = null;
    private String c = "";
    private BtDeviceDiscoverCallback e = new BtDeviceDiscoverCallback() { // from class: izl.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr) {
            if (izl.this.d || bluetoothDeviceNode == null) {
                return;
            }
            try {
                if (bluetoothDeviceNode.getBtDevice() != null) {
                    BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
                    LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BleReconnectManager", "onDeviceDiscovered name:", btDevice.getName());
                    if (izl.this.c(btDevice.getAddress())) {
                        LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BleReconnectManager", "onDeviceDiscovered with find device but need check.");
                        String address = btDevice.getAddress();
                        izl izlVar = izl.this;
                        izlVar.e(izlVar.b.getDeviceIdentify(), address);
                    } else {
                        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User disabled current device, so do not need to connect wanted device.");
                    }
                }
            } catch (SecurityException e) {
                LogUtil.e("BleReconnectManager", "onDeviceDiscovered SecurityException:", ExceptionUtils.d(e));
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "discovery finished.");
            if (izl.this.b != null) {
                izl izlVar = izl.this;
                if (izlVar.c(izlVar.b.getDeviceIdentify())) {
                    LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BleReconnectManager", "User do not disable current device, so start to connect wanted device.");
                    izl.this.g.removeMessages(1);
                    long d = izl.this.d();
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = izl.this.b;
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Do not find the wanted device so start a new reconnect process with delay Millis: ", Long.valueOf(d));
                    izl.this.g.sendMessage(obtain, d);
                    return;
                }
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User disabled current device, so do not need to connect wanted device.");
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "BLE discovery canceled.");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i, String str) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "BLE discovery failure.");
        }
    };

    public izl() {
        b();
    }

    private void b() {
        this.g = HandlerCenter.yt_(new c(), "BleReconnectManager");
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter tryToReconnectBLE().");
        if (deviceInfo != null) {
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            this.b = deviceInfo;
            if (c(deviceIdentify)) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User do not disconnect device so start to find device.");
                this.d = false;
                e(deviceInfo);
            }
        }
    }

    public void a(String str) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter stopReconnectBle().");
        this.d = true;
        this.f13684a = 2000;
        this.g.removeMessages(1);
        iys.e().a(str);
    }

    private void e(DeviceInfo deviceInfo) {
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter reconnectBleDevice().");
        this.g.removeMessages(1);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = deviceInfo;
        if (bld.d()) {
            this.g.sendMessage(obtain, 4000L);
            return;
        }
        long d = d();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Delay Time is: ", Long.valueOf(d), "ms");
        this.g.sendMessage(obtain, d);
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "mReconnectHandler receive message: ", Integer.valueOf(message.what));
            if (message.what == 1) {
                izl.this.a(message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null);
                return true;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "mReconnectHandler default message.what");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d() {
        if (bld.d()) {
            return 1200000;
        }
        int i = this.f13684a;
        if (i < 256000) {
            this.f13684a = i * 2;
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "getReconnectDelayMillis() delayMillis: ", Integer.valueOf(this.f13684a));
            return this.f13684a;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "getReconnectDelayMillis() delayMillis: ", 256000);
        return 256000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter reconnectBleDeviceDelay() with mIsCancel: ", Boolean.valueOf(this.d));
        if (deviceInfo == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "device is null.");
            return;
        }
        if (this.d) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "device already connected so stop reconnect.");
            a(deviceInfo.getDeviceIdentify());
        } else if (iyl.d().g() == 3) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "BT switch is on.");
            b(deviceInfo);
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "BT switch is not on.");
            a(deviceInfo.getDeviceIdentify());
        }
    }

    private void b(DeviceInfo deviceInfo) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter tryConnectBleDevice().");
        if (deviceInfo == null) {
            LogUtil.a("0xA0200005", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "device is null.");
            return;
        }
        izi iziVar = this.i;
        if (iziVar != null) {
            if (iziVar.j() != 2 && this.i.j() != 1) {
                this.j = false;
                if (c(deviceInfo.getDeviceIdentify())) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User do not disable current device, so start to discover ble device.");
                    iys.e().b(deviceInfo.getDeviceIdentify(), this.e);
                    return;
                } else {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User disabled current device, so do not need to connect wanted device.");
                    return;
                }
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "do not need reconnect with status = " + this.i.j());
            return;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "mSendCommandUtil is null.");
    }

    private void a() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter doConnectBleDevice().");
        izi iziVar = this.i;
        if (iziVar != null) {
            iziVar.a();
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "mSendCommandUtil is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        if (this.j) {
            return;
        }
        if (TextUtils.equals(str2, str)) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Find the wanted device.");
            this.j = true;
            LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BleReconnectManager", "Enter verifySearchDestDeviceToReconnect().");
            iyl.d().b();
            iys.e().a(str2);
            if (c(str2)) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User do not disable current device, so start to connect wanted device.");
                a();
                return;
            } else {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "User disabled current device, so do not need to connect wanted device.");
                return;
            }
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "verifySearchDestDeviceToReconnect(): find different device.");
    }

    public void b(izi iziVar) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter setSendCommandUtilInfo().");
        this.i = iziVar;
        if (iziVar != null) {
            iziVar.g();
        }
    }

    public izi c() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleReconnectManager", "Enter getSendCommandUtilInfo().");
        return this.i;
    }

    public String e() {
        return this.c;
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.c = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        return this.c.equalsIgnoreCase(str);
    }
}
