package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class izo {

    /* renamed from: a, reason: collision with root package name */
    private BluetoothDevice f13687a;
    private BtDeviceStateCallback c;
    private DeviceInfo d;
    private Context e;
    private izi l;
    private HandlerThread g = null;
    private Handler k = null;
    private boolean i = false;
    private boolean j = false;
    private boolean h = false;
    private int b = 0;
    private BtDevicePairCallback f = new BtDevicePairCallback() { // from class: izo.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback
        public void onDevicePairing(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                LogUtil.a("BleAuthenticManager", "onDevicePairing device is null");
            } else {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "state: ", Integer.valueOf(bluetoothDevice.getBondState()));
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback
        public void onDevicePaired(BluetoothDevice bluetoothDevice) {
            izo.this.b(2, false);
            Message obtainMessage = izo.this.k.obtainMessage(3);
            obtainMessage.obj = bluetoothDevice;
            izo.this.k.sendMessageDelayed(obtainMessage, 1000L);
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback
        public void onDevicePairNone(BluetoothDevice bluetoothDevice) {
            if (izo.this.h) {
                izo.this.h = false;
                try {
                    izo.this.e();
                    return;
                } catch (SecurityException e) {
                    LogUtil.e("BleAuthenticManager", "onDevicePairNone SecurityException:", ExceptionUtils.d(e));
                    return;
                }
            }
            izo izoVar = izo.this;
            izoVar.bDy_(izoVar.f13687a, izo.this.f);
            izo.this.h = true;
        }
    };

    public izo(izi iziVar, Context context, BluetoothDevice bluetoothDevice, BtDeviceStateCallback btDeviceStateCallback) {
        this.f13687a = null;
        this.l = null;
        if (iziVar != null) {
            this.l = iziVar;
        }
        if (context != null) {
            this.e = context;
        }
        if (bluetoothDevice != null) {
            this.f13687a = bluetoothDevice;
        }
        if (btDeviceStateCallback != null) {
            this.c = btDeviceStateCallback;
        }
        d();
    }

    private void d() {
        HandlerThread handlerThread = new HandlerThread("BleAuthenticManager");
        this.g = handlerThread;
        handlerThread.start();
        this.k = new c(this.g.getLooper());
    }

    public boolean e(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null || bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "processPairResponse() btDeviceInfo or dataContent is null");
            return false;
        }
        this.d = deviceInfo;
        deviceInfo.setDeviceIdentify(deviceInfo.getDeviceIdentify());
        int f = iyo.f(this.e, bArr);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "processPairResponse with mBtDeviceInfo :", Integer.valueOf(this.b), " isAllowBind : ", Integer.valueOf(f));
        if (f == 0) {
            BtDeviceStateCallback btDeviceStateCallback = this.c;
            if (btDeviceStateCallback != null) {
                btDeviceStateCallback.disconnectBluetooth(this.d, 4);
            }
            b(4, false);
            izi iziVar = this.l;
            if (iziVar == null) {
                return true;
            }
            iziVar.c(false, 4);
            this.l.d(false);
            this.l.e();
            return true;
        }
        if (f != 1) {
            return false;
        }
        d(deviceInfo);
        int i = this.b;
        if (i == 0) {
            iyq.b(this.e).e(this.e);
            this.k.sendEmptyMessageDelayed(4, 1000L);
            return true;
        }
        if (i == 1) {
            b(2, false);
            return true;
        }
        if (i == 2) {
            b(2, false);
            return true;
        }
        LogUtil.a("BleAuthenticManager", "switch mBtVersionInfo in default");
        return true;
    }

    private void d(DeviceInfo deviceInfo) {
        if (this.b == 2) {
            String a2 = iyq.b(this.e).a(this.f13687a.getAddress());
            LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BleAuthenticManager", "CommandPackage.authVersion : ", Integer.valueOf(deviceInfo.getAuthVersion()), "CommandPackage.key : ", a2);
            iyq.b(this.e).d(this.f13687a.getAddress(), a2, "btsdk_sharedpreferences_name4", this.e);
        }
    }

    private void e(DeviceInfo deviceInfo, int i) {
        if (i == 12 && deviceInfo.getDeviceProtocol() == 1) {
            bDz_(this.f13687a, true);
        } else {
            a(deviceInfo, iyq.b(this.e).c(this.f13687a.getAddress(), this.e));
            this.k.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    private void e(DeviceInfo deviceInfo, izb izbVar, int i) {
        if (izbVar.e() == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "handleBondState1 deviceBondState: ", Integer.valueOf(i));
            if (i == 12) {
                b(2, false);
                return;
            } else {
                this.k.sendEmptyMessageDelayed(1, 1000L);
                return;
            }
        }
        String c2 = iyq.b(this.e).c(this.f13687a.getAddress(), this.e);
        a(deviceInfo, c2);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "handleBondState1 key is empty: ", Boolean.valueOf(TextUtils.isEmpty(c2)));
        if (c2 != null && !"".equals(c2)) {
            b(2, false);
        } else {
            this.k.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    private void a(DeviceInfo deviceInfo, String str) {
        LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BleAuthenticManager", "Encrypt key : ", str);
        if (this.d == null || str == null || deviceInfo.getDeviceProtocol() != 2 || str.length() != 64) {
            return;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "Need to reset v2 key info empty.");
        iyq.b(this.e).d(this.f13687a.getAddress(), "", "btsdk_sharedpreferences_name4", this.e);
    }

    public boolean d(DeviceInfo deviceInfo, byte[] bArr) {
        Object[] objArr = new Object[6];
        objArr[0] = 1;
        objArr[1] = "BleAuthenticManager";
        objArr[2] = "Enter processBondStatusResponse() btDeviceInfo: ";
        objArr[3] = Boolean.valueOf(deviceInfo == null);
        objArr[4] = " dataContent: ";
        objArr[5] = Boolean.valueOf(bArr == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (deviceInfo == null || bArr == null) {
            return false;
        }
        this.d = deviceInfo;
        izb d = iyo.d(deviceInfo.getDeviceIdentify(), this.e, bArr);
        if (d == null) {
            LogUtil.a("0xA0200007", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "bondState parameter is incorrect.");
            return false;
        }
        this.b = d.e();
        int a2 = d.a();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "mBtVersionInfo : ", Integer.valueOf(this.b), " btServiceMtu: ", Integer.valueOf(a2));
        if (a2 != 0) {
            this.l.b(a2);
        } else {
            this.l.b(128);
        }
        try {
            int bondState = this.f13687a.getBondState();
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "deviceBondState is ", Integer.valueOf(bondState), "; BtBindStatus is", Integer.valueOf(d.b()), " ;btBindStatusInfo is ", Integer.valueOf(d.d()));
            int b = d.b();
            if (b == 0) {
                e(deviceInfo, bondState);
            } else if (b == 1) {
                a(d, deviceInfo, bondState);
            } else {
                LogUtil.a("BleAuthenticManager", "switch bondState.getBtBindStatus() in default");
            }
            return true;
        } catch (SecurityException e) {
            LogUtil.e("BleAuthenticManager", "onDeviceDiscovered SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    private void a(izb izbVar, DeviceInfo deviceInfo, int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "whileCaseOne bondState.getBtBindStatusInfo(): ", Integer.valueOf(izbVar.d()), " mIsGetDeviceBondStateFlag: ", Boolean.valueOf(this.j));
        if (izbVar.d() == 1) {
            e(deviceInfo, izbVar, i);
        } else if (!this.j) {
            this.k.sendEmptyMessage(5);
            this.j = true;
        } else {
            e();
            this.j = false;
        }
    }

    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("BleAuthenticManager", "message == null");
                return;
            }
            super.handleMessage(message);
            Object[] objArr = new Object[6];
            objArr[0] = 1;
            objArr[1] = "BleAuthenticManager";
            objArr[2] = "received message:";
            objArr[3] = Integer.valueOf(message.what);
            objArr[4] = " mBtDeviceInfo is null:";
            objArr[5] = Boolean.valueOf(izo.this.d == null);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
            int i = message.what;
            if (i == 1) {
                izo.this.e.sendBroadcast(new Intent("com.huawei.bone.action.REQUEST_BIND_DEVICE"), bin.d);
                izo.this.l.a(iyo.bDm_(izo.this.e, izo.this.f13687a, izo.this.d, false));
                return;
            }
            if (i == 2) {
                izo.this.l.a();
                return;
            }
            if (i == 4) {
                izo izoVar = izo.this;
                izoVar.bDy_(izoVar.f13687a, izo.this.f);
            } else {
                if (i == 5) {
                    izo.this.l.b(20);
                    if (izo.this.d != null) {
                        izo.this.l.a(iyo.e(izo.this.d.getDeviceProtocol(), izo.this.d));
                        return;
                    }
                    return;
                }
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "SendHandler no this message.what");
            }
        }
    }

    public void bDy_(final BluetoothDevice bluetoothDevice, final BtDevicePairCallback btDevicePairCallback) {
        Object[] objArr = new Object[6];
        objArr[0] = 1;
        objArr[1] = "BleAuthenticManager";
        objArr[2] = "btDevice: ";
        objArr[3] = Boolean.valueOf(bluetoothDevice == null);
        objArr[4] = " devicePairCallback: ";
        objArr[5] = Boolean.valueOf(btDevicePairCallback == null);
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (bluetoothDevice == null || btDevicePairCallback == null) {
            return;
        }
        boolean bDd_ = iyl.d().bDd_(bluetoothDevice, btDevicePairCallback);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "pairDevice(), isResult: ", Boolean.valueOf(bDd_), " mIsPairingDevice is ", Boolean.valueOf(this.h));
        if (bDd_) {
            return;
        }
        if (!this.h) {
            this.k.postDelayed(new Runnable() { // from class: izo.2
                @Override // java.lang.Runnable
                public void run() {
                    boolean bDd_2 = iyl.d().bDd_(bluetoothDevice, btDevicePairCallback);
                    izo.this.h = true;
                    if (!bDd_2) {
                        izo.this.h = false;
                        izo.this.e();
                    }
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "pairDevice() postDelayed, isResult: ", Boolean.valueOf(bDd_2), " pairDeviceFlag : ", Boolean.valueOf(izo.this.h));
                }
            }, 1000L);
        } else {
            this.h = false;
            e();
        }
    }

    public boolean bDz_(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice == null) {
            return false;
        }
        this.i = z;
        boolean bDk_ = iyl.d().bDk_(bluetoothDevice);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "unPairDevice() fail unbind isUnPair: ", Boolean.valueOf(bDk_), " isReconnectFlag : ", Boolean.valueOf(z));
        if (bDk_ && z) {
            e();
            this.k.sendEmptyMessageDelayed(2, 5000L);
        } else {
            if (!bDk_ && z) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "unPairDevice() fail unbind.");
            } else {
                iyq.b(this.e).e(this.e);
            }
            this.i = false;
            e();
        }
        return bDk_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, boolean z) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "Enter reportConnectStatus() status : ", Integer.valueOf(i), " isConnectFlag: ", Boolean.valueOf(z), " mIsUnPairFlag: ", Boolean.valueOf(this.i));
        if (i == 3 || i == 0) {
            if (this.i) {
                return;
            }
        } else if (i == 1 && this.i) {
            return;
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "others");
        }
        DeviceInfo deviceInfo = this.d;
        if (deviceInfo != null) {
            if (this.b == 2) {
                deviceInfo.setEncryptType(1);
            } else {
                deviceInfo.setEncryptType(0);
            }
        }
        if (i == 2) {
            this.l.c(false, 2);
        }
        if (this.d == null || this.c == null) {
            return;
        }
        izn c2 = izn.c();
        String deviceIdentify = this.d.getDeviceIdentify();
        if (i == 4) {
            c2.a(deviceIdentify, System.currentTimeMillis());
            c2.a(deviceIdentify, 2010009);
            c2.c(deviceIdentify, AgdConstant.INSTALL_TYPE_DEFAULT);
        }
        this.c.onDeviceConnectionStateChanged(this.d, i, c2.e(deviceIdentify));
    }

    public void e() {
        if (this.l.i() != null) {
            this.l.i().disconnectBTDevice();
        }
        this.b = 0;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BleAuthenticManager", "disconnect end.");
    }
}
