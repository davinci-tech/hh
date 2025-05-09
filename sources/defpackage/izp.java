package defpackage;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.y1;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes5.dex */
public class izp implements BTDeviceServiceBase {
    private int c;
    private BluetoothDevice d;
    private e f;
    private iyl g;
    private BtDeviceStateCallback h;
    private c j;
    private int n;
    private int o;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13689a = new Object();
    private boolean l = false;
    private DeviceInfo e = new DeviceInfo();
    private BtDevicePairCallback i = new BtDevicePairCallback() { // from class: izp.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback
        public void onDevicePairing(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                LogUtil.a("BTDeviceBRService", "onDevicePairing btDevice is null");
                return;
            }
            try {
                LogUtil.c("BTDeviceBRService", "onDevicePairing state: ", Integer.valueOf(bluetoothDevice.getBondState()));
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceBRService", "mBtDevicePairCallback onDevicePairing SecurityException:", ExceptionUtils.d(e2));
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback
        public void onDevicePaired(BluetoothDevice bluetoothDevice) {
            LogUtil.c("BTDeviceBRService", "btDevice paired.");
            try {
                if (izp.this.bDR_(bluetoothDevice)) {
                    izp.this.l = false;
                    if (!izp.this.g.bDj_(bluetoothDevice)) {
                        LogUtil.c("BTDeviceBRService", "Need to connect hfp profile.");
                        izp.this.l = true;
                        izp.this.g.bDf_(bluetoothDevice);
                    }
                    LogUtil.c("BTDeviceBRService", "Start to connect btDevice.");
                    izp.this.bDQ_(bluetoothDevice);
                    return;
                }
                LogUtil.a("BTDeviceBRService", "not current device.");
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceBRService", "mBtDevicePairCallback onDevicePaired:", ExceptionUtils.d(e2));
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback
        public void onDevicePairNone(BluetoothDevice bluetoothDevice) {
            try {
                if (!izp.this.bDR_(bluetoothDevice)) {
                    LogUtil.a("BTDeviceBRService", "onDevicePairNone not current device.");
                    return;
                }
                LogUtil.c("DEVMGR_SETTING", "BTDeviceBRService", " btDevice pair fail, so connect btDevice fail.");
                ize.e(1001001);
                izp.this.o = 1001001;
                izp.this.c(4);
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceBRService", "mBtDevicePairCallback onDevicePairNone:", ExceptionUtils.d(e2));
            }
        }
    };

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void connectBTDevice(String str) {
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setAwAssetCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
    }

    public izp(Context context, BluetoothDevice bluetoothDevice, BtDeviceStateCallback btDeviceStateCallback, int i) {
        if (context == null) {
            LogUtil.a("BTDeviceBRService", "DEVMGR_SETTING", "context is null.");
        }
        if (bluetoothDevice != null) {
            this.d = bluetoothDevice;
        }
        if (btDeviceStateCallback != null) {
            this.h = btDeviceStateCallback;
        }
        this.g = iyl.d();
        this.e.setDeviceBluetoothType(1);
        this.n = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bDR_(BluetoothDevice bluetoothDevice) {
        BluetoothDevice bluetoothDevice2 = this.d;
        if (bluetoothDevice2 == null || TextUtils.isEmpty(bluetoothDevice2.getAddress())) {
            LogUtil.a("BTDeviceBRService", "mBTDevice is invalid");
            return false;
        }
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.a("BTDeviceBRService", "btDevice is invalid");
            return false;
        }
        return this.d.getAddress().equals(bluetoothDevice.getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bDQ_(BluetoothDevice bluetoothDevice) {
        LogUtil.c("BTDeviceBRService", "Enter connectBTDeviceThread().");
        if (!snu.e().getAuthorizationStatus(BaseApplication.e())) {
            LogUtil.a("BTDeviceBRService", "br start connect, but not authorize, so return");
            return;
        }
        synchronized (c()) {
            e eVar = this.f;
            if (eVar != null) {
                eVar.e();
                this.f = null;
            }
            LogUtil.c("BTDeviceBRService", "Start ConnectThread.");
            e eVar2 = new e(bluetoothDevice);
            this.f = eVar2;
            eVar2.start();
        }
        synchronized (b()) {
            c cVar = this.j;
            if (cVar != null) {
                cVar.d();
                this.j = null;
            }
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void connectBTDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            LogUtil.a("BTDeviceBRService", "0xA0200006", "btDevice is null.");
            return;
        }
        LogUtil.c("DEVMGR_SETTING", "BTDeviceBRService", "Start to report connecting state.");
        if (this.c == 2) {
            return;
        }
        c(1);
        try {
            iyg.e(bluetoothDevice.getName());
            if (12 != bluetoothDevice.getBondState()) {
                LogUtil.c("BTDeviceBRService", "Need to pair btDevice.");
                if (this.g.bDd_(bluetoothDevice, this.i)) {
                    return;
                }
                LogUtil.c("R_BTDeviceBRService", "btDevice pair failed, so connect btDevice fail.");
                ize.e(1001001);
                this.o = 1001001;
                c(4);
                return;
            }
            LogUtil.c("BTDeviceBRService", "Do not need to pair btDevice, so connect btDevice directly.");
            bDQ_(bluetoothDevice);
        } catch (SecurityException e2) {
            LogUtil.e("BTDeviceBRService", "connectBTDevice SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bDS_(BluetoothSocket bluetoothSocket) {
        synchronized (b()) {
            c cVar = this.j;
            if (cVar != null) {
                cVar.d();
                this.j = null;
            }
            c cVar2 = new c(bluetoothSocket);
            this.j = cVar2;
            cVar2.start();
        }
        LogUtil.c("BTDeviceBRService", "Connect success, so report state.");
        c(2);
        iyv.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.c("R_BTDeviceBRService", "connectionLost");
        c(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.c("R_BTDeviceBRService", "connectionFail:", 1001002);
        ize.e(1001002);
        this.o = 1001002;
        c(4);
    }

    protected void c(int i) {
        if (i != this.c) {
            this.c = i;
            if (this.h != null) {
                LogUtil.c("BTDeviceBRService", "report connect state : ", Integer.valueOf(i));
                DeviceInfo deviceInfo = this.e;
                if (deviceInfo != null) {
                    try {
                        deviceInfo.setDeviceName(this.d.getName());
                        this.e.setDeviceIdentify(this.d.getAddress());
                    } catch (SecurityException e2) {
                        LogUtil.e("BTDeviceBRService", "setState SecurityException:", ExceptionUtils.d(e2));
                    }
                    izn c2 = izn.c();
                    String deviceIdentify = this.e.getDeviceIdentify();
                    if (i == 4) {
                        c2.a(deviceIdentify, System.currentTimeMillis());
                        c2.c(deviceIdentify, AgdConstant.INSTALL_TYPE_DEFAULT);
                        c2.a(deviceIdentify, this.o);
                    }
                    this.h.onDeviceConnectionStateChanged(this.e, this.c, c2.e(deviceIdentify));
                }
            }
        }
    }

    class e extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private final BluetoothSocket f13691a;
        private String d;

        private e(BluetoothDevice bluetoothDevice) {
            UUID fromString;
            this.d = y1.f5579a;
            LogUtil.c("BTDeviceBRService", "Enter ConnectThread.");
            BluetoothSocket bluetoothSocket = null;
            if (bluetoothDevice != null) {
                izp.this.d = bluetoothDevice;
                int bDh_ = izp.this.g.bDh_(bluetoothDevice);
                if (-1 == bDh_) {
                    try {
                        if (TextUtils.isEmpty(bluetoothDevice.getName()) && -1 != izp.this.n) {
                            bDh_ = izp.this.n;
                        }
                    } catch (IOException | SecurityException e) {
                        LogUtil.e("BTDeviceBRService", "0xA0200000", "create socket exception with info : ", e.getMessage());
                        LogUtil.e("R_BTDeviceBRService", "create socket exception.");
                    }
                }
                if (bDh_ == 1) {
                    fromString = UUID.fromString("82ff3820-8411-400c-b85a-55bdb32cf057");
                } else if (bDh_ == 7) {
                    fromString = UUID.fromString("82ff3820-8411-400c-b85a-55bdb32cf060");
                } else {
                    fromString = bDT_(bluetoothDevice);
                }
                if (fromString != null) {
                    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(fromString);
                } else {
                    LogUtil.e("R_BTDeviceBRService", "secureSocketUUID is null.");
                }
                this.f13691a = bluetoothSocket;
                return;
            }
            LogUtil.a("BTDeviceBRService", "0xA0200006", "ConnectThread: device parameter is null.");
            this.f13691a = null;
            izp.this.d = null;
        }

        private UUID bDT_(BluetoothDevice bluetoothDevice) {
            LogUtil.a("BTDeviceBRService", "Do not find suitable UUID info.");
            try {
                if (!TextUtils.isEmpty(bluetoothDevice.getName())) {
                    return UUID.fromString("82ff3820-8411-400c-b85a-55bdb32cf060");
                }
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceBRService", "onDeviceNull SecurityException:", ExceptionUtils.d(e));
            }
            return null;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("ConnectThread" + this.d);
            try {
                Thread.sleep(500L);
                if (izp.this.l) {
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                LogUtil.e("BTDeviceBRService", "0xA0200006", "InterruptedException : ", e.getMessage());
            }
            izp.this.g.e();
            BluetoothSocket bluetoothSocket = this.f13691a;
            if (bluetoothSocket == null) {
                LogUtil.a("BTDeviceBRService", "0xA0200006", "mBTSocket is null.");
                izp.this.d();
                return;
            }
            try {
                try {
                    bluetoothSocket.connect();
                    c();
                    izp.this.bDS_(this.f13691a);
                } catch (IOException e2) {
                    LogUtil.e("BTDeviceBRService", "0xA0200000", " mBTSocket connect IOException with info : ", e2.getMessage());
                    izp.this.d();
                }
            } catch (IOException e3) {
                try {
                    LogUtil.e("BTDeviceBRService", "0xA0200000", " mBTSocket connect IOException1 with info : ", e3.getMessage());
                    izp.this.b(1011002);
                    this.f13691a.close();
                } catch (IOException e4) {
                    LogUtil.e("BTDeviceBRService", "0xA0200000", " mBTSocket connect IOException2 with info : ", e4.getMessage());
                }
                izp.this.d();
            } catch (Exception unused) {
                LogUtil.e("BTDeviceBRService", "0xA0200000", " mBTSocket connect NullPointerException1 with info");
                this.f13691a.close();
                izp.this.d();
            }
        }

        private void c() {
            LogUtil.c("BTDeviceBRService", "Start DataTransferThread.");
        }

        public void e() {
            try {
                BluetoothSocket bluetoothSocket = this.f13691a;
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
            } catch (IOException e) {
                LogUtil.e("BTDeviceBRService", "0xA0200000", " Close socket exception with info : ", e.getMessage());
            }
        }
    }

    class c extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private final BluetoothSocket f13690a;
        private final OutputStream d;
        private final InputStream e;

        private c(BluetoothSocket bluetoothSocket) {
            InputStream inputStream;
            this.f13690a = bluetoothSocket;
            OutputStream outputStream = null;
            try {
                inputStream = bluetoothSocket.getInputStream();
            } catch (IOException e) {
                e = e;
                inputStream = null;
            }
            try {
                outputStream = bluetoothSocket.getOutputStream();
            } catch (IOException e2) {
                e = e2;
                LogUtil.e("BTDeviceBRService", "0xA0200006", "Get Input Stream Handle exception with info : ", e.getMessage());
                this.e = inputStream;
                this.d = outputStream;
            }
            this.e = inputStream;
            this.d = outputStream;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            byte[] bArr = new byte[1032];
            while (true) {
                try {
                    Arrays.fill(bArr, (byte) 0);
                    int read = this.e.read(bArr);
                    byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, read);
                    blt.d("BTDeviceBRService", copyOfRange, 0, "BTDeviceBRService", "MessageAPI Device-->SDK: ");
                    if (izp.this.h != null && izp.this.e != null) {
                        izp.this.e.setDeviceName(izp.this.d.getName());
                        izp.this.e.setDeviceIdentify(izp.this.d.getAddress());
                        LogUtil.c("BTDeviceBRService", "dataLen : ", Integer.valueOf(read));
                        izp.this.h.onDataReceived(izp.this.e, read, copyOfRange);
                    }
                } catch (IOException | SecurityException e) {
                    LogUtil.e("BTDeviceBRService", "0xA0200006", " SPP Socket read occur IOException with info : ", e.getMessage());
                    izp.this.a();
                    izp.this.b(1021002);
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(byte[] bArr) {
            try {
                if (this.d != null) {
                    iyg.a("BTDeviceBRService", blq.d(bArr));
                    this.d.write(bArr);
                } else {
                    LogUtil.a("BTDeviceBRService", "Send BT Data with mBTOutStream is null.");
                }
            } catch (IOException e) {
                LogUtil.e("BTDeviceBRService", "0xA0200006", " SPP Socket send occur IOException with info : ", e.getMessage());
                izp.this.a();
                izp.this.b(1031002);
            }
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0019 -> B:6:0x0026). Please report as a decompilation issue!!! */
        public void d() {
            try {
                InputStream inputStream = this.e;
                if (inputStream != null) {
                    inputStream.close();
                } else {
                    LogUtil.a("BTDeviceBRService", "Cancel Spp Socket with mBTInStream is null.");
                }
            } catch (IOException e) {
                LogUtil.e("BTDeviceBRService", "0xA0200000", "In Stream close occur IOException with info : ", e.getMessage());
            }
            try {
                OutputStream outputStream = this.d;
                if (outputStream != null) {
                    outputStream.close();
                } else {
                    LogUtil.a("BTDeviceBRService", "Cancel Spp Socket with mBTOutStream is null.");
                }
            } catch (IOException e2) {
                LogUtil.e("BTDeviceBRService", "0xA0200000", "Out Stream close occur IOException with info : ", e2.getMessage());
            }
            try {
                this.f13690a.close();
            } catch (IOException e3) {
                LogUtil.e("BTDeviceBRService", "0xA0200000", "Socket close occur IOException with info : ", e3.getMessage());
            }
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void disconnectBTDevice() {
        LogUtil.c("DEVMGR_SETTING", "BTDeviceBRService", "Enter disconnectBTDevice.");
        synchronized (c()) {
            e eVar = this.f;
            if (eVar != null) {
                eVar.e();
                this.f = null;
            }
        }
        synchronized (b()) {
            c cVar = this.j;
            if (cVar != null) {
                cVar.d();
                this.j = null;
            }
        }
        c(3);
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public boolean sendBTDeviceData(byte[] bArr) {
        c cVar;
        if (bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceBRService", "btDeviceData is null");
            return false;
        }
        synchronized (this) {
            if (2 != this.c) {
                LogUtil.a("BTDeviceBRService", "Connect State is not connect.");
                return false;
            }
            synchronized (b()) {
                cVar = this.j;
            }
            if (cVar != null) {
                cVar.d(bArr);
                return true;
            }
            LogUtil.a("BTDeviceBRService", "dataTransferThread is null.");
            return false;
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void onDestroy() {
        this.d = null;
        this.h = null;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public DeviceInfo getDeviceInfo() {
        return this.e;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void btSwitchChangeInfo(int i) {
        LogUtil.c("BTDeviceBRService", "Enter btSwitchChangeInfo() with status : ", Integer.valueOf(i));
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void sendBTFilePath(String str) {
        LogUtil.c("BTDeviceBRService", "Enter sendBTFilePath in br");
        if (str == null) {
            LogUtil.a("BTDeviceBRService", "path is null");
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setFileCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("BTDeviceBRService", "Enter setFileCallback in br.");
        if (btDeviceResponseCallback == null) {
            LogUtil.a("BTDeviceBRService", "callback is null");
        }
    }

    private static Object b() {
        Object obj;
        synchronized (izp.class) {
            obj = f13689a;
        }
        return obj;
    }

    private static Object c() {
        Object obj;
        synchronized (izp.class) {
            obj = b;
        }
        return obj;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void disconnectGMS() {
        LogUtil.c("BTDeviceBRService", "start to disconnectGMS in br.");
        this.c = 3;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void removeV1CheckCommand() {
        LogUtil.c("BTDeviceBRService", "start to removeV1CheckCommand in br.");
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setPathExtendNum(int i) {
        LogUtil.c("BTDeviceBRService", "Enter setPathExtendNum in br with pathExtendNum : ", Integer.valueOf(i));
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public int getBTDeviceConnectState() {
        int i;
        synchronized (this) {
            i = this.c;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (this.g.bDj_(this.d)) {
            iyv.a(i);
        }
    }
}
