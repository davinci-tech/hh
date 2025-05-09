package defpackage;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/* loaded from: classes5.dex */
public class izt implements BTDeviceServiceBase {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private int f13695a;
    private BluetoothDevice b;
    private String d;
    private Context g;
    private int h;
    private BluetoothGatt i;
    private BtDeviceStateCallback j;
    private BluetoothGattCharacteristic s;
    private BluetoothGattCharacteristic x;
    private DeviceInfo c = new DeviceInfo();
    private int t = 0;
    private ExtendHandler r = null;
    private boolean n = false;
    private int y = 0;
    private boolean p = false;
    private ExtendHandler q = null;
    private boolean k = false;
    private final Object l = new Object();
    private boolean m = false;
    private int f = 0;
    private final BluetoothGattCallback o = new BluetoothGattCallback() { // from class: izt.4
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            izt.this.bDJ_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            izt.this.e(i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            izt.this.d(i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            izt.this.b(i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            izt.this.bDI_(bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            izt.this.a(i);
        }
    };

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void connectBTDevice(String str) {
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setAwAssetCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
    }

    public izt(Context context, BluetoothDevice bluetoothDevice, String str, BtDeviceStateCallback btDeviceStateCallback) {
        this.g = null;
        this.b = null;
        this.j = null;
        this.d = "";
        if (context != null) {
            this.g = context;
        }
        this.b = bluetoothDevice;
        if (bluetoothDevice != null) {
            try {
                this.d = bluetoothDevice.getName();
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceBLEService", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e2));
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "bluetoothDevice name:", this.d);
            if (TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(str)) {
                this.d = str;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Device name:", this.d);
        }
        this.j = btDeviceStateCallback;
        this.c.setDeviceBluetoothType(2);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bDJ_(BluetoothGatt bluetoothGatt, int i, int i2) {
        LogUtil.c("R_BTDeviceBLEService", "onConnectionStateChange() status:", Integer.valueOf(i), " newState:", Integer.valueOf(i2));
        if (i == 133) {
            iyu.d(System.currentTimeMillis());
            iyv.a();
        }
        if (this.i == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mBluetoothGatt is null");
            this.i = bluetoothGatt;
        }
        if (i2 == 2) {
            iyu.d(0L);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Connected to GATT server.");
            iyl.d().b();
            this.r.removeMessages(4);
            this.r.sendEmptyMessage(2, 1000L);
            return;
        }
        if (i2 == 0) {
            this.r.removeTasksAndMessages();
            int e2 = e();
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Bluetooth switch state:", Integer.valueOf(e2), " Disconnected from GATT server. mIsSetNotificationFlag:", Boolean.valueOf(this.n));
            if (!this.p && e2 == 3) {
                if (this.n) {
                    d(true);
                    return;
                } else {
                    d(false);
                    return;
                }
            }
            LogUtil.c("DEVMGR_SETTING", "BTDeviceBLEService", " Wanted disconnect or bluetooth switch is not on occur, so release.status:", Integer.valueOf(i));
            ize.e(i + 1000000);
            g();
            return;
        }
        LogUtil.a("DEVMGR_SETTING", "BTDeviceBLEService", " enter unknown status");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        BluetoothGattService bluetoothGattService;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "onServicesDiscovered() status:", Integer.valueOf(i));
        if (this.i == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mBluetoothGatt is null");
            d(false);
            return;
        }
        if (i == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Service discover success.");
            this.r.removeMessages(4);
            try {
                bluetoothGattService = this.i.getService(UUID.fromString("0000fe86-0000-1000-8000-00805f9b34fb"));
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceBLEService", "dealWithOnServicesDiscovered BLE_SERVICE SecurityException", bll.a(e2));
                bluetoothGattService = null;
            }
            if (bluetoothGattService != null) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "BLE GATT Service UUID find success.");
                synchronized (this.l) {
                    this.x = bluetoothGattService.getCharacteristic(UUID.fromString("0000fe01-0000-1000-8000-00805f9b34fb"));
                }
                this.s = bluetoothGattService.getCharacteristic(UUID.fromString("0000fe02-0000-1000-8000-00805f9b34fb"));
                f();
                return;
            }
            try {
                bluetoothGattService = this.i.getService(UUID.fromString("00000200-0000-1000-8000-00805F9B34FB"));
            } catch (SecurityException e3) {
                LogUtil.e("BTDeviceBLEService", "isSupportService B0_SERVICE SecurityException", bll.a(e3));
            }
            if (bluetoothGattService != null) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "B0 GATT Service UUID find success.");
                synchronized (this.l) {
                    this.x = bluetoothGattService.getCharacteristic(UUID.fromString("00000203-0000-1000-8000-00805F9B34FB"));
                }
                this.s = bluetoothGattService.getCharacteristic(UUID.fromString("00000202-0000-1000-8000-00805F9B34FB"));
                f();
                return;
            }
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Do not match any Service UUID.");
            bDK_(this.i, 1);
            return;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Service discover fail.");
        bDK_(this.i, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (i != 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceBLEService", "Device-->SDK onCharacteristicRead error status:", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.k = true;
        if (i != 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "SDK-->Device onCharacteristicWrite error status:", Integer.valueOf(i));
        }
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bDI_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceBLEService", "Device-->SDK: ", iyq.d(blq.d(value)));
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = value;
        this.q.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter onDescriptorWrite with status:", Integer.valueOf(i));
        if (i == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Notification set success.");
            c(2);
            this.t = 0;
            iyv.e();
            return;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "refreshResult:", Boolean.valueOf(bDK_(this.i, 4)));
    }

    private void c() {
        this.r = HandlerCenter.yt_(new a(), "BTDeviceBLEService_MSG");
        this.q = HandlerCenter.yt_(new e(), "BTDeviceBLEService_RECEIVE");
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceBLEService", "handleMessage, message is null");
                return false;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "receive message:", Integer.valueOf(message.what));
            switch (message.what) {
                case 1:
                    c();
                    break;
                case 2:
                    i();
                    break;
                case 3:
                    izt.this.g();
                    break;
                case 4:
                    e();
                    break;
                case 5:
                    a();
                    break;
                case 6:
                    b();
                    break;
                case 7:
                    d();
                    break;
                default:
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceBLEService", "not support message type");
                    break;
            }
            return false;
        }

        private void c() {
            izt.this.r.removeMessages(1);
            if (izt.this.i != null) {
                try {
                    izt.this.i.disconnect();
                } catch (SecurityException e) {
                    LogUtil.e("BTDeviceBLEService", "dealWithMsgConnectBleDevice SecurityException:", ExceptionUtils.d(e));
                }
                izt.this.r.sendEmptyMessage(6, 5000L);
                return;
            }
            izt.this.r.sendEmptyMessage(5);
        }

        private void i() {
            boolean z;
            if (izt.this.i != null) {
                izt.this.r.sendEmptyMessage(4, 20000L);
                try {
                    z = izt.this.i.discoverServices();
                } catch (Exception unused) {
                    LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService");
                    z = false;
                }
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Attempting to start service discovery:", Boolean.valueOf(z));
            }
        }

        private void e() {
            izt.this.r.removeMessages(4);
            izt.this.d(true);
        }

        private void a() {
            if (snu.e().getAuthorizationStatus(BaseApplication.e())) {
                izt.this.r.sendEmptyMessage(4, 20000L);
                try {
                    izt iztVar = izt.this;
                    iztVar.i = iztVar.b.connectGatt(izt.this.g, false, izt.this.o);
                } catch (Exception unused) {
                    LogUtil.e("BTDeviceBLEService", "dealWithMsgDirectConnectBle occur exception");
                }
                LogUtil.c("R_BTDeviceBLEService", "connectGatt() from ble service");
                iyg.e(izt.this.d);
                return;
            }
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "ble start connect, but not authorize, so return");
        }

        private void b() {
            if (izt.this.i != null) {
                try {
                    izt.this.i.close();
                } catch (SecurityException e) {
                    LogUtil.e("BTDeviceBLEService", "dealWithMsgCloseGatt SecurityException:", ExceptionUtils.d(e));
                }
                izt.this.r.sendEmptyMessage(5, 1000L);
            }
        }

        private void d() {
            if (izt.this.f13695a == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "BT Switch off and bt connect state is connected so start to release.");
                izt.this.p = true;
                izt.this.g();
            }
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void connectBTDevice(BluetoothDevice bluetoothDevice) {
        Object[] objArr = new Object[4];
        objArr[0] = "BTDeviceBLEService";
        objArr[1] = "Enter connectBTDevice() with device state:";
        objArr[2] = Integer.valueOf(this.f13695a);
        objArr[3] = Boolean.valueOf(bluetoothDevice == null);
        LogUtil.c("DEVMGR_SETTING", objArr);
        if (bluetoothDevice == null) {
            return;
        }
        int i = this.f13695a;
        c(1);
        if (i == 2) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Device has connected.");
            c(2);
            return;
        }
        try {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Start to connect ble device with name = ", bluetoothDevice.getName());
        } catch (SecurityException e2) {
            LogUtil.e("BTDeviceBLEService", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e2));
        }
        this.f = 0;
        this.b = bluetoothDevice;
        this.p = false;
        ExtendHandler extendHandler = this.r;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(1);
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void disconnectBTDevice() {
        Object[] objArr = new Object[3];
        objArr[0] = "BTDeviceBLEService";
        objArr[1] = " Enter disconnectBTDevice().";
        objArr[2] = Boolean.valueOf(this.i == null);
        LogUtil.c("DEVMGR_SETTING", objArr);
        this.p = true;
        BluetoothGatt bluetoothGatt = this.i;
        if (bluetoothGatt == null) {
            this.r.sendEmptyMessage(3);
            return;
        }
        this.r.sendEmptyMessage(3, 5000L);
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "start to execute gatt disconnect.");
        try {
            bluetoothGatt.disconnect();
        } catch (SecurityException unused) {
            LogUtil.e("BTDeviceBLEService", "disconnectBTDevice bluetoothGatt SecurityException");
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "End disconnectBTDevice().");
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public boolean sendBTDeviceData(byte[] bArr) {
        boolean z = false;
        if (bArr == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Param null");
            return false;
        }
        synchronized (this.l) {
            if (!a()) {
                return false;
            }
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.x;
            if (bluetoothGattCharacteristic != null) {
                bluetoothGattCharacteristic.setValue(bArr);
            }
            this.k = false;
            iyg.a("BTDeviceBLEService", blq.d(bArr));
            if (this.i != null && this.x != null) {
                z = b();
            }
            if (!z) {
                h();
                synchronized (this.l) {
                    if (this.i != null && this.x != null) {
                        z = b();
                    }
                }
            }
            return !this.k ? e(z) : z;
        }
    }

    private boolean e(boolean z) {
        Object obj = e;
        synchronized (obj) {
            this.m = true;
            try {
                obj.wait(300L);
            } catch (InterruptedException e2) {
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "InterruptedException:", ExceptionUtils.d(e2));
            }
            if (this.m) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Wait back, timeout = ", 300);
                if (!z) {
                    synchronized (this.l) {
                        if (this.i != null && this.x != null) {
                            z = b();
                        }
                    }
                }
                this.m = false;
            }
        }
        return z;
    }

    private void h() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "InterruptedException:", ExceptionUtils.d(e2));
        }
    }

    private boolean b() {
        try {
            boolean writeCharacteristic = this.i.writeCharacteristic(this.x);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Service data send for timeout ,dataTempSendResult = ", Boolean.valueOf(writeCharacteristic));
            return writeCharacteristic;
        } catch (SecurityException e2) {
            LogUtil.e("BTDeviceBLEService", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    private boolean a() {
        if (this.x == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mWritePoint is incorrect.");
            return false;
        }
        if (this.i != null) {
            return true;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mBluetoothGatt is incorrect.");
        return false;
    }

    private void j() {
        Object obj = e;
        synchronized (obj) {
            if (this.m) {
                obj.notifyAll();
                this.m = false;
            }
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void onDestroy() {
        this.g = null;
        this.b = null;
        this.j = null;
        ExtendHandler extendHandler = this.r;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.r.quit(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter reConnect() with reConnectFlag = ", Boolean.valueOf(z));
        g();
    }

    private void f() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter setCharacteristicMessage().");
        this.n = true;
        try {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.s;
            if (bluetoothGattCharacteristic != null) {
                if ((bluetoothGattCharacteristic.getProperties() | 16) > 0) {
                    this.i.setCharacteristicNotification(this.s, true);
                    BluetoothGattDescriptor descriptor = this.s.getDescriptor(UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID));
                    if (descriptor != null) {
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "writeDescriptorResult = ", Boolean.valueOf(this.i.writeDescriptor(descriptor)));
                    }
                }
            } else {
                LogUtil.a("0xA0200004", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mNotifyPoint is null.");
                bDK_(this.i, 2);
            }
        } catch (SecurityException e2) {
            LogUtil.e("BTDeviceBLEService", "setCharacteristicMessage SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private boolean bDK_(BluetoothGatt bluetoothGatt, int i) {
        Object[] objArr = new Object[4];
        objArr[0] = 1;
        objArr[1] = "BTDeviceBLEService";
        objArr[2] = "Enter refreshDeviceCache(). BluetoothGatt parameter is null ";
        objArr[3] = Boolean.valueOf(bluetoothGatt == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (bluetoothGatt == null) {
            return false;
        }
        try {
            int i2 = this.t;
            if (i2 != i && i2 != 0) {
                this.y = 0;
            }
            int i3 = this.y;
            if (i3 <= 1) {
                this.y = i3 + 1;
                this.t = i;
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                this.r.sendEmptyMessage(2, 1000L);
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Start to refresh Device Cache.");
                if (method != null) {
                    Object invoke = method.invoke(bluetoothGatt, new Object[0]);
                    boolean booleanValue = (invoke == null || !(invoke instanceof Boolean)) ? false : ((Boolean) invoke).booleanValue();
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "refresh Device Cache invoke result :", Boolean.valueOf(booleanValue));
                    return booleanValue;
                }
            } else {
                LogUtil.a("0xA0200003", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "id =", Integer.valueOf(i), " call over times");
                d(false);
            }
        } catch (IllegalAccessException unused) {
            LogUtil.e("0xA0200003", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "An exception occur while refreshing device:IllegalAccessException");
        } catch (NoSuchMethodException unused2) {
            LogUtil.e("0xA0200003", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "An exception occur while refreshing device:NoSuchMethodException");
        } catch (InvocationTargetException unused3) {
            LogUtil.e("0xA0200003", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "An exception occur while refreshing device:InvocationTargetException");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter release() with state:", Integer.valueOf(this.f13695a));
        synchronized (this.l) {
            if (this.i != null) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Start to close gatt.");
                try {
                    this.i.close();
                } catch (Exception e2) {
                    LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "release exception: ", ExceptionUtils.d(e2));
                }
                this.i = null;
            }
            this.x = null;
        }
        ExtendHandler extendHandler = this.r;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        } else {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mMsgHandler = null so can not remove all message.");
        }
        d();
    }

    private void d() {
        this.t = 0;
        this.y = 0;
        this.n = false;
        this.s = null;
        int i = this.f;
        if (i < 3 && this.f13695a != 2) {
            this.f = i + 1;
            int e2 = e();
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Try connect with BT switch state = ", Integer.valueOf(e2));
            if (!this.p && e2 == 3) {
                ExtendHandler extendHandler = this.r;
                if (extendHandler != null) {
                    extendHandler.sendEmptyMessage(5, 2000L);
                    return;
                } else {
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mMsgHandler = null.");
                    return;
                }
            }
            if (this.f13695a == 1) {
                this.h = 1002001;
                c(4);
                return;
            } else {
                c(3);
                return;
            }
        }
        if (this.f13695a == 1) {
            this.h = 1002001;
            c(4);
        } else {
            c(3);
        }
    }

    protected void c(int i) {
        BluetoothDevice bluetoothDevice;
        this.f13695a = i;
        if (this.j != null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Report BLE connect state = ", Integer.valueOf(i));
            if (this.c == null || (bluetoothDevice = this.b) == null) {
                return;
            }
            try {
                String name = bluetoothDevice.getName();
                if (TextUtils.isEmpty(name)) {
                    name = this.d;
                }
                LogUtil.c("BTDeviceBLEService", "deviceName: ", name);
                if (!TextUtils.isEmpty(name)) {
                    this.c.setDeviceName(name);
                }
                this.c.setDeviceIdentify(this.b.getAddress());
                izn c = izn.c();
                String deviceIdentify = this.c.getDeviceIdentify();
                LogUtil.c("OpAnalyticsImpl", "BLE btConnectState is::", Integer.valueOf(i));
                if (i == 4) {
                    c.a(deviceIdentify, System.currentTimeMillis());
                    c.a(deviceIdentify, this.h);
                    c.c(deviceIdentify, AgdConstant.INSTALL_TYPE_DEFAULT);
                }
                this.j.onDeviceConnectionStateChanged(this.c, this.f13695a, c.e(deviceIdentify));
                return;
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceBLEService", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Client callback is null. ");
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceBLEService", "ReceiveCommandHandler, handleMessage message is null");
                return false;
            }
            if (message.what != 1) {
                return false;
            }
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (izt.this.j != null) {
                    izt.this.j.onDataReceived(izt.this.c, bArr.length, bArr);
                }
            }
            return true;
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public DeviceInfo getDeviceInfo() {
        return this.c;
    }

    private int e() {
        return iyl.d().g();
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void btSwitchChangeInfo(int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter btSwitchChangeInfo() with status = ", Integer.valueOf(i));
        if (i == 1) {
            ExtendHandler extendHandler = this.r;
            if (extendHandler != null) {
                extendHandler.sendEmptyMessage(7, 1000L);
            } else {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "mMsgHandler = null.");
            }
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void sendBTFilePath(String str) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter sendBTFilePath in ble.");
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setFileCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter setFileCallback in ble.");
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void disconnectGMS() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "start to disconnectGMS in ble.");
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void removeV1CheckCommand() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "start to removeV1CheckCommand in ble.");
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setPathExtendNum(int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBLEService", "Enter setPathExtendNum in ble with pathExtendNum = ", Integer.valueOf(i));
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public int getBTDeviceConnectState() {
        return this.f13695a;
    }
}
