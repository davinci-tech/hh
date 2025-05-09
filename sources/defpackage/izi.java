package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.OperationDeviceInfo;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.WearableSwitchAdapter;
import com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase;
import com.huawei.iconnect.IWearConnectService;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class izi {
    private Context ac;
    private boolean aj;
    private OperationDeviceInfo aq;
    private String ar;
    private izn as;
    private BluetoothDevice i;
    private izo j;
    private izf q;
    private int r;
    private BTDeviceServiceBase s;
    private BtDeviceStateCallback y;
    private int am = -1;
    private int al = -1;
    private int ak = -1;
    private int ag = -1;
    private int f = 0;
    private String g = "";
    private String h = "";
    private String ah = AgdConstant.INSTALL_TYPE_AUTO;
    private String ae = "0100";
    private BTDeviceDataWrapBase o = null;
    private izj aw = new izj();
    private int n = 0;
    private int m = 0;
    private List<izf> l = new ArrayList(128);
    private boolean ax = false;
    private izf t = new izf();
    private int ao = -1;
    private int ay = -1;
    private int au = -1;
    private int bc = -1;
    private int at = -1;
    private int w = -1;
    private final Object c = new Object();
    private final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private boolean f13681a = false;
    private boolean e = false;
    private boolean af = false;
    private iyl k = iyl.d();
    private boolean ai = false;
    private boolean an = false;
    private int x = 1;
    private ExtendHandler ap = null;
    private int u = 0;
    private int v = 0;
    private boolean aa = false;
    private boolean z = true;
    private boolean ab = true;
    private boolean bb = true;
    private boolean av = false;
    private ExecutorService az = Executors.newFixedThreadPool(2);
    private final Object ba = new Object();
    private boolean d = false;
    private ServiceConnection ad = new ServiceConnection() { // from class: izi.5
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.c("BTDeviceSendCommandUtil", "iconnect service connected so start to set service handle .");
            if (iBinder != null) {
                iyo.a(IWearConnectService.Stub.asInterface(iBinder));
            } else {
                LogUtil.a("BTDeviceSendCommandUtil", "service is null");
                iyo.a((IWearConnectService) null);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.c("BTDeviceSendCommandUtil", "iconnect service disconnect so start to set service handle is null.");
            iyo.a((IWearConnectService) null);
        }
    };
    private BtDeviceStateCallback p = new BtDeviceStateCallback() { // from class: izi.2
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void disconnectBluetooth(DeviceInfo deviceInfo, int i) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i, OperationDeviceInfo operationDeviceInfo) {
            izi.this.b(deviceInfo, i, operationDeviceInfo);
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr) {
            izi.this.e(deviceInfo, i, bArr);
        }
    };

    public izi(Context context, izk izkVar) {
        this.ac = null;
        this.r = -1;
        this.ar = "";
        this.i = null;
        this.s = null;
        this.y = null;
        if (izkVar == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "Enter BTDeviceSendCommandUtil() sendCommandUtilInfo is null");
            return;
        }
        int d = izkVar.d();
        BluetoothDevice bDA_ = izkVar.bDA_();
        String a2 = izkVar.a();
        BtDeviceStateCallback b = izkVar.b();
        LogUtil.c("BTDeviceSendCommandUtil", "Enter BTDeviceSendCommandUtil().", a2);
        if (b != null) {
            this.y = b;
        }
        this.r = d;
        this.i = bDA_;
        this.ar = a2;
        if (context != null) {
            this.ac = context;
        }
        String c = izkVar.c();
        int h = izkVar.h();
        BTDeviceServiceBase bDv_ = bDv_(bDA_, c, this.p, h, a2);
        this.s = bDv_;
        if (bDv_ == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " initBTDeviceService with return null.");
        }
        this.as = izn.c();
        bDu_(bDA_, h);
        v();
    }

    public void c(int i) {
        if (this.i != null) {
            LogUtil.d("BTDeviceSendCommandUtil", "confirmOrCancel : " + i);
            if (i == 0) {
                this.aj = false;
            } else {
                this.aj = true;
            }
            this.d = false;
            a(iyo.e(i));
        }
    }

    private void v() {
        this.ap = HandlerCenter.yt_(new a(), "BTDeviceSendCommandUtil");
    }

    private BTDeviceServiceBase bDv_(BluetoothDevice bluetoothDevice, String str, BtDeviceStateCallback btDeviceStateCallback, int i, String str2) {
        LogUtil.c("BTDeviceSendCommandUtil", "initBTDeviceService with btType is ", Integer.valueOf(this.r));
        int i2 = this.r;
        if (-1 == i2) {
            LogUtil.c("BTDeviceSendCommandUtil", "0xA0200008", " initBTDeviceService with btType is unknown");
            return null;
        }
        if (i2 == 0) {
            return WearableSwitchAdapter.a().d(null, btDeviceStateCallback);
        }
        if (i2 == 1) {
            return new izp(this.ac, bluetoothDevice, btDeviceStateCallback, i);
        }
        if (i2 == 2) {
            izt iztVar = new izt(this.ac, bluetoothDevice, str, btDeviceStateCallback);
            this.j = new izo(this, this.ac, this.i, this.y);
            return iztVar;
        }
        if (i2 == 5) {
            return WearableSwitchAdapter.a().d(str2, btDeviceStateCallback);
        }
        LogUtil.a("BTDeviceSendCommandUtil", "initBTDeviceService() default");
        return null;
    }

    private void bDu_(BluetoothDevice bluetoothDevice, int i) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter initBTDeviceLinkLayerBaseInfo() with mBTType:", Integer.valueOf(this.r));
        int i2 = this.r;
        if (i2 == 0 || i2 == 5) {
            this.al = 65535;
            this.ak = 65535;
            this.ag = 0;
            this.am = 2;
            if (this.o == null) {
                this.o = new izs(this.ac, 65535);
                return;
            }
            return;
        }
        if (bluetoothDevice != null) {
            int bDh_ = this.k.bDh_(bluetoothDevice);
            if (-1 == bDh_ && TextUtils.isEmpty(bluetoothDevice.getName()) && i != -1) {
                LogUtil.c("BTDeviceSendCommandUtil", "Use the product Type.");
            } else {
                i = bDh_;
            }
            i(i);
            if (this.o == null) {
                int i3 = this.am;
                if (i3 == 1) {
                    this.o = new izq(this.ac, this.al);
                    return;
                } else if (i3 == 2) {
                    this.o = new izs(this.ac, this.al);
                    return;
                } else {
                    LogUtil.a("BTDeviceSendCommandUtil", "initBTDeviceLinkLayerBaseInfo() default");
                    return;
                }
            }
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "btDevice is null.");
    }

    private void i(int i) {
        LogUtil.c("BTDeviceSendCommandUtil", "Device product type is ", Integer.valueOf(i));
        if (i == 0) {
            this.am = 0;
            this.al = 128;
            this.ak = 128;
            this.ag = 0;
            return;
        }
        if (i == 1) {
            this.am = 1;
            this.al = 128;
            this.ak = 128;
            this.ag = 0;
            return;
        }
        if (i == 5) {
            this.am = 1;
            this.al = 20;
            this.ak = 20;
            this.ag = 10;
            return;
        }
        this.am = 2;
        this.al = 255;
        this.ak = 255;
        this.ag = 10;
    }

    public void b(int i) {
        this.al = i;
    }

    public int f() {
        return this.n;
    }

    public void d(int i) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter setBTDeviceActiveState() with activeState is ", Integer.valueOf(i));
        this.n = i;
    }

    public int j() {
        LogUtil.c("BTDeviceSendCommandUtil", "getBTDeviceConnectState with state is ", Integer.valueOf(this.m));
        return this.m;
    }

    public void a() {
        if (this.s != null) {
            this.ab = true;
            ExtendHandler extendHandler = this.ap;
            if (extendHandler != null) {
                extendHandler.removeMessages(3);
            }
            if (this.r != 1 || this.s.getBTDeviceConnectState() != 2) {
                this.av = true;
                ExtendHandler extendHandler2 = this.ap;
                if (extendHandler2 != null) {
                    extendHandler2.sendEmptyMessage(3, 60000L);
                }
            }
            int i = this.r;
            if (i == 0 || 5 == i) {
                this.s.connectBTDevice(this.ar);
                return;
            } else {
                this.s.connectBTDevice(this.i);
                return;
            }
        }
        LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "connectBTDevice: mBTDeviceServiceBase is null.");
    }

    public void e() {
        BTDeviceServiceBase bTDeviceServiceBase = this.s;
        if (bTDeviceServiceBase != null) {
            bTDeviceServiceBase.disconnectBTDevice();
        } else {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " disconnectBTDevice: mBTDeviceServiceBase is null.");
        }
    }

    private String a(String str) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter getAuthenticTokenValue().");
        if (16 == this.g.length() / 2 && 16 == this.h.length() / 2) {
            byte[] a2 = blq.a(this.g);
            byte[] a3 = blq.a(this.h);
            byte[] bArr = new byte[32];
            System.arraycopy(a2, 0, bArr, 0, 16);
            System.arraycopy(a3, 0, bArr, 16, 16);
            String s = s();
            LogUtil.c("BTDeviceSendCommandUtil", "old strCak is ", s, " after convert old strCak to Hex is ", blq.b(s));
            try {
                return blq.d(iyx.d(iyx.d(blq.a(s + str), bArr), bArr));
            } catch (UnsupportedEncodingException unused) {
                LogUtil.e("BTDeviceSendCommandUtil", "HMac256 occur exception with info is UnsupportedEncodingException");
                return null;
            } catch (InvalidKeyException unused2) {
                LogUtil.e("BTDeviceSendCommandUtil", "HMac256 occur exception with info is InvalidKeyException");
                return null;
            } catch (NoSuchAlgorithmException unused3) {
                LogUtil.e("BTDeviceSendCommandUtil", "HMac256 occur exception with info is NoSuchAlgorithmException");
                return null;
            }
        }
        LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " Authentic Random parameter is incorrect.");
        return null;
    }

    private String s() {
        LogUtil.c("BTDeviceSendCommandUtil", "getCak enter");
        int i = this.f;
        if (i == 2) {
            return bmx.b(bmo.e(1, 31) + bmo.e(1, 1031) + bmo.e(1, 2031));
        }
        if (i != 1) {
            if (i != 3) {
                LogUtil.c("BTDeviceSendCommandUtil", "no support  SECRET_KEY", Integer.valueOf(i));
            } else {
                String e = bmo.e(1, 23);
                String e2 = bmo.e(1, 1023);
                String e3 = bmo.e(1, 2023);
                LogUtil.d("BTDeviceSendCommandUtil", "getCak first is ", e, " getCak second is ", e2, " getCak third is ", e3);
                try {
                    byte[] a2 = bmo.a(Base64.decode(e + e2 + e3, 0));
                    if (a2 != null) {
                        return new String(a2, "UTF-8");
                    }
                    LogUtil.a("BTDeviceSendCommandUtil", "getCak bytes is null.");
                    return "";
                } catch (UnsupportedEncodingException unused) {
                    LogUtil.e("BTDeviceSendCommandUtil", "getCak UnsupportedEncodingException");
                }
            }
            LogUtil.c("BTDeviceSendCommandUtil", "getCak exception, the mAuthenticVersion is ", Integer.valueOf(this.f));
            return "";
        }
        return bmx.b(bmo.e(1, 30) + bmo.e(1, GLMapStaticValue.MAP_PARAMETERNAME_SCENIC) + bmo.e(1, 2030));
    }

    private boolean d(DeviceInfo deviceInfo) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter startAuthenticBTDevice().");
        if (32 != this.g.length()) {
            LogUtil.c("BTDeviceSendCommandUtil", "RandA parameter is incorrect so stop authentic");
            return false;
        }
        try {
            byte[] a2 = iyx.a(16);
            if (a2 == null) {
                LogUtil.a("BTDeviceSendCommandUtil", "generateRandomBytes fail.");
                return false;
            }
            this.h = blq.d(a2);
            String a3 = a(this.ae);
            if (a3 == null) {
                return false;
            }
            b(a3, deviceInfo);
            return true;
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.e("BTDeviceSendCommandUtil", "generateRandomBytes exception with info is NoSuchAlgorithmException");
            return false;
        }
    }

    private void b(String str, DeviceInfo deviceInfo) {
        izf d = iyo.d(this.ac, this.f, str, this.h, deviceInfo);
        LogUtil.c("BTDeviceSendCommandUtil", "Start to request authentic.");
        a(d);
    }

    private boolean d(DeviceInfo deviceInfo, String str) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter checkAuthenticBTDevice().");
        if (16 == this.g.length() / 2 && 16 == this.h.length() / 2) {
            String a2 = a(this.ah);
            if (a2 != null) {
                return str.equalsIgnoreCase(a2);
            }
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " Authentic codeInfoHex is incorrect.");
            return false;
        }
        LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " Authentic Random parameter is incorrect.");
        return false;
    }

    private void d(int i, int i2) {
        if (this.at == i && this.w == i2) {
            this.v = 0;
            aa();
        }
    }

    private void a(DeviceInfo deviceInfo, int i, byte[] bArr) {
        if (d(deviceInfo, bArr)) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " parameter is incorrect.");
            return;
        }
        if (d(bArr)) {
            LogUtil.c("BTDeviceSendCommandUtil", "reconnect, do not resolve before command.");
            d(bArr[0], bArr[1]);
            return;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        if (b != 1) {
            LogUtil.c("BTDeviceSendCommandUtil", "handshake report data with V2.");
            this.y.onDataReceived(deviceInfo, i, bArr);
            return;
        }
        if (b2 == 1) {
            a(deviceInfo, bArr);
            return;
        }
        if (b2 == 19) {
            h(deviceInfo, bArr);
            return;
        }
        if (b2 == 15) {
            i(deviceInfo, bArr);
            return;
        }
        if (b2 == 14) {
            g(deviceInfo, bArr);
        } else if (b2 == 22) {
            j(deviceInfo, bArr);
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "handshake report data with V2.");
            this.y.onDataReceived(deviceInfo, i, bArr);
        }
    }

    private boolean d(DeviceInfo deviceInfo, byte[] bArr) {
        return this.y == null || deviceInfo == null || bArr == null;
    }

    private boolean d(byte[] bArr) {
        if (this.av) {
            return (bArr[0] == 1 && bArr[1] == 1) ? false : true;
        }
        return false;
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "deviceConnectedSuccess deviceInfo is empty!");
            return;
        }
        a(2);
        this.ai = false;
        OperationDeviceInfo e = this.as.e(deviceInfo.getDeviceIdentify());
        this.aq = e;
        BtDeviceStateCallback btDeviceStateCallback = this.y;
        if (btDeviceStateCallback == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "mBtDeviceStateClientCallback  is empty!");
        } else {
            btDeviceStateCallback.onDeviceConnectionStateChanged(deviceInfo, this.m, e);
        }
    }

    public void e(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "connectFail deviceInfo is empty!");
            return;
        }
        this.m = 4;
        this.ai = false;
        OperationDeviceInfo e = e(deviceInfo.getDeviceIdentify(), i);
        this.aq = e;
        BtDeviceStateCallback btDeviceStateCallback = this.y;
        if (btDeviceStateCallback == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "mBtDeviceStateClientCallback  is empty !");
        } else {
            btDeviceStateCallback.onDeviceConnectionStateChanged(deviceInfo, 4, e);
        }
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr) {
        BTDeviceServiceBase bTDeviceServiceBase;
        LogUtil.c("BTDeviceSendCommandUtil", "Capture V2 link connect parameter.");
        this.ap.removeMessages(3);
        this.av = false;
        d(1, 1);
        if (this.r == 0 && (bTDeviceServiceBase = this.s) != null) {
            bTDeviceServiceBase.removeV1CheckCommand();
        }
        izh a2 = iyo.a(this.ac, bArr);
        this.m = 1;
        if (a2 != null) {
            this.am = a2.a();
            int e = a2.e();
            this.al = e;
            if (this.o != null) {
                LogUtil.c("BTDeviceSendCommandUtil", "Device max frame size : ", Integer.valueOf(e));
                this.o.setDeviceMaxFrameSize(this.al);
            }
            this.ak = a2.c();
            LogUtil.c("BTDeviceSendCommandUtil", "sendLinkDataCommand, mInterval : ", Integer.valueOf(this.ag));
            this.ag = a2.d();
            int b = a2.b();
            this.f = b;
            if (b == 0) {
                LogUtil.a("BTDeviceSendCommandUtil", "invalid device version.");
                e(deviceInfo, 2100011);
                return;
            }
            this.g = a2.f();
            deviceInfo.setAuthVersion(this.f);
            if (this.r == 0) {
                LogUtil.c("BTDeviceSendCommandUtil", "Path extend number: ", Integer.valueOf(a2.h()));
                BTDeviceServiceBase bTDeviceServiceBase2 = this.s;
                if (bTDeviceServiceBase2 != null) {
                    bTDeviceServiceBase2.setPathExtendNum(a2.h());
                }
            }
            if (2 == this.r) {
                b(deviceInfo);
                return;
            }
            this.aj = true;
            LogUtil.c("BTDeviceSendCommandUtil", "Start to get device available status.");
            d(a2.g(), deviceInfo);
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "Device Link parameter resolve fail.");
        e(deviceInfo, 2100011);
    }

    private void d(int i, DeviceInfo deviceInfo) {
        a(iyo.d());
    }

    private void b(DeviceInfo deviceInfo) {
        l();
        LogUtil.c("BTDeviceSendCommandUtil", "Device need authentic Application mAuthenticVersion:", Integer.valueOf(this.f));
        if (d(deviceInfo)) {
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "Send Authentic command fail.");
        e(deviceInfo, 2010009);
    }

    private void i(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.c("BTDeviceSendCommandUtil", "Capture V2 receive bond status response.");
        d(1, 15);
        if (2 == this.r) {
            z();
        }
        izo izoVar = this.j;
        if (izoVar == null || izoVar.d(deviceInfo, bArr)) {
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "receive bond status incorrect response.");
        e(deviceInfo, 2010009);
    }

    private void h(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.c("BTDeviceSendCommandUtil", "Capture V2 Authentic parameter.");
        d(1, 19);
        boolean d = d(deviceInfo, iyo.d(bArr));
        LogUtil.c("BTDeviceSendCommandUtil", "Authentic checkResult:", Boolean.valueOf(d));
        if (d) {
            if (2 == this.r) {
                a(iyo.e(2, deviceInfo));
                return;
            } else {
                e(deviceInfo);
                return;
            }
        }
        e();
        e(deviceInfo, 2010009);
    }

    private void g(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.c("BTDeviceSendCommandUtil", "Capture V2 bond request response.");
        d(1, 14);
        izo izoVar = this.j;
        if (izoVar == null || izoVar.e(deviceInfo, bArr)) {
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "receive pair status incorrect response.");
        e(deviceInfo, 2010009);
    }

    private void j(DeviceInfo deviceInfo, byte[] bArr) {
        boolean k = izy.b(this.ac).k();
        izy.b(this.ac).b(false);
        d(1, 22);
        int c = iyo.c(this.ac, bArr);
        LogUtil.c("BTDeviceSendCommandUtil", "isAutoConnect : ", Boolean.valueOf(k), " isWaitUserConfirm : ", Boolean.valueOf(this.d), "Device available status : ", Integer.valueOf(c), "mAuthenticVersion : ", Integer.valueOf(this.f));
        if (-1 == c || c == 0 || 1 == c) {
            if (this.f != 0) {
                if (!d(deviceInfo)) {
                    LogUtil.c("BTDeviceSendCommandUtil", "Send Authentic command fail after check device available.");
                    e(deviceInfo, 2010009);
                }
            } else {
                e(deviceInfo);
            }
            this.z = true;
            return;
        }
        if (2 == c) {
            this.m = 5;
            this.ai = false;
            OperationDeviceInfo e = this.as.e(deviceInfo.getDeviceIdentify());
            this.aq = e;
            this.y.onDeviceConnectionStateChanged(deviceInfo, 5, e);
            this.z = false;
            return;
        }
        if (3 == c) {
            a(k);
        } else if (100000 == c) {
            t();
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "this status not deal with.");
        }
    }

    private void a(boolean z) {
        LogUtil.d("BTDeviceSendCommandUtil", "isAutoConnect : " + z);
        if (z) {
            if (this.d) {
                LogUtil.c("BTDeviceSendCommandUtil", "wait util user click button.");
                return;
            }
            this.m = 4;
            this.ai = false;
            this.z = false;
            this.s.disconnectBTDevice();
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.NotifyActivity"));
        intent.addFlags(268435456);
        intent.addFlags(536870912);
        BluetoothDevice bluetoothDevice = this.i;
        if (bluetoothDevice != null) {
            intent.putExtra("deviceName", bluetoothDevice.getAddress());
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "mBTDevice is null.");
        }
        this.d = true;
        try {
            this.ac.startActivity(intent);
            LogUtil.c("BTDeviceSendCommandUtil", "mContext, startActivity intent = ", intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.e("BTDeviceSendCommandUtil", "startJumpActivity not find JumpActivity");
        }
    }

    private void t() {
        if (this.aj) {
            LogUtil.d("BTDeviceSendCommandUtil", "5.1.22 receive 100000 connect failed");
            this.m = 4;
            this.ai = false;
            this.z = false;
            this.s.disconnectBTDevice();
            return;
        }
        izf a2 = iyo.a(this.r);
        LogUtil.c("BTDeviceSendCommandUtil", "Start to get link parameter.");
        a(a2);
        this.aj = true;
    }

    private void a(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            LogUtil.c("BTDeviceSendCommandUtil", "not 5.1.17 data, return");
            return;
        }
        if (1 == bArr[0] && 17 == bArr[1]) {
            String d = blq.d(bArr);
            if (d.length() < 8) {
                LogUtil.a("BTDeviceSendCommandUtil", "getMtsInterval valid data");
                return;
            }
            try {
                this.ag = Integer.parseInt(d.substring(8, d.length()), 16);
            } catch (NumberFormatException unused) {
                LogUtil.e("BTDeviceSendCommandUtil", "getMTSInterval NumberFormatException");
            }
            LogUtil.c("BTDeviceSendCommandUtil", "getMTSInterval mInterval : ", Integer.valueOf(this.ag));
        }
    }

    private void b(DeviceInfo deviceInfo, int i, byte[] bArr) {
        try {
            if (this.y != null && deviceInfo != null && bArr != null) {
                deviceInfo.setDeviceProtocol(this.am);
                int i2 = this.am;
                if (2 == i2) {
                    a(deviceInfo, bArr, i);
                } else if (1 == i2) {
                    k(deviceInfo, bArr);
                } else if (i2 != 0) {
                    LogUtil.c("BTDeviceSendCommandUtil", "no support this linkVersion ", Integer.valueOf(i2));
                } else {
                    f(deviceInfo, bArr);
                }
            } else {
                LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", " reportReceivedData: parameter is not correct.");
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.e("BTDeviceSendCommandUtil", "ArrayIndexOutOfBoundsException ERROR. ");
        }
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr, int i) {
        if (bArr != null) {
            if (bArr.length < 2) {
                LogUtil.a("BTDeviceSendCommandUtil", "dealWithV2 invalid data");
                return;
            } else if (bArr[0] == 1 && bArr[1] == 14) {
                this.ai = true;
            }
        }
        if (this.ai) {
            a(deviceInfo, i, bArr);
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "not handshake report data with V2.");
        byte[] b = iyo.b(this.ac, bArr, deviceInfo);
        if (b == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "checkedContent is null.");
            b = bArr;
        }
        e(b);
        if (bArr != null) {
            d(bArr[0], bArr[1]);
        }
        this.y.onDataReceived(deviceInfo, b.length, b);
        a(b);
    }

    private void k(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr.length > 3 && bArr[2] == 0) {
            l(deviceInfo, bArr);
            return;
        }
        int i = this.x;
        if (1 == i) {
            int i2 = bArr[0];
            LogUtil.c("BTDeviceSendCommandUtil", "before requestId : ", Integer.valueOf(i2));
            if (i2 < 0) {
                i2 += 256;
            }
            if (i2 != this.ao) {
                LogUtil.a("BTDeviceSendCommandUtil", "error report data, requestId:", Integer.valueOf(i2), " mRequestID:", Integer.valueOf(this.ao));
                return;
            }
            bArr = iyt.a(this.ay, this.au, this.bc, bArr);
        } else if (2 == i) {
            int i3 = this.at;
            bArr[0] = (byte) i3;
            LogUtil.c("BTDeviceSendCommandUtil", "reportReceivedData, OTA report business cmd, need add Service id, mServiceID : ", Integer.valueOf(i3));
        } else if (3 == i) {
            LogUtil.c("BTDeviceSendCommandUtil", "reportReceivedData, OTA report file data, do nothing...");
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "reportReceivedData, OTA report wrong data, do nothing...");
        }
        LogUtil.c("BTDeviceSendCommandUtil", "reportReceivedData, receive data finish so need to reset resend timer and unLockBT.");
        this.v = 0;
        aa();
        if (bArr == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "btV2Command is null.");
        } else if (this.ai) {
            o(deviceInfo, bArr);
        } else {
            c(deviceInfo, bArr);
        }
    }

    private void l(DeviceInfo deviceInfo, byte[] bArr) {
        String d = blq.d(bArr);
        LogUtil.c("BTDeviceSendCommandUtil", "Notification info: ", d);
        if (c(bArr)) {
            bArr = iyt.a(bArr[1], bArr[2], bArr[3], blq.a(d));
            blt.d("BTDeviceSendCommandUtil", bArr, "after V1--->V2 Notification info: ");
        }
        if (bArr != null) {
            this.y.onDataReceived(deviceInfo, bArr.length, bArr);
        } else {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "notificationData is null.");
        }
    }

    private void o(DeviceInfo deviceInfo, byte[] bArr) {
        if (2 == this.r) {
            byte b = bArr[0];
            if (1 == b && 15 == bArr[1]) {
                LogUtil.c("BTDeviceSendCommandUtil", "receive bond status response.");
                izo izoVar = this.j;
                if (izoVar == null || izoVar.d(deviceInfo, bArr)) {
                    return;
                }
                LogUtil.c("BTDeviceSendCommandUtil", "receive bond status incorrect response.");
                return;
            }
            if (1 == b && 14 == bArr[1]) {
                LogUtil.c("BTDeviceSendCommandUtil", "receive request bond response.");
                izo izoVar2 = this.j;
                if (izoVar2 == null || izoVar2.e(deviceInfo, bArr)) {
                    return;
                }
                LogUtil.c("BTDeviceSendCommandUtil", "receive pair status incorrect response.");
                return;
            }
            LogUtil.c("BTDeviceSendCommandUtil", "handshake report data with V1.");
            this.y.onDataReceived(deviceInfo, bArr.length, bArr);
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "handshake report data with V1 and not BLE.");
        this.y.onDataReceived(deviceInfo, bArr.length, bArr);
    }

    private void c(DeviceInfo deviceInfo, byte[] bArr) {
        int i = this.at;
        if (7 == i && 9 == this.w) {
            LogUtil.c("BTDeviceSendCommandUtil", "Take wanted message so need change command ID for set userInfo.");
            bArr[1] = 2;
        } else if (2 == i && 3 == this.w) {
            LogUtil.c("BTDeviceSendCommandUtil", "Take wanted message so need change command ID for send message.");
            bArr[1] = 1;
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "dealWithNotHandshake() else");
        }
        this.y.onDataReceived(deviceInfo, bArr.length, bArr);
    }

    private void f(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.c("BTDeviceSendCommandUtil", "receive V0 data info.");
        this.v = 0;
        aa();
        LogUtil.c("BTDeviceSendCommandUtil", "not handshake report data with V1.");
        int length = bArr.length;
        int i = length + 2;
        byte[] bArr2 = new byte[i];
        bArr2[0] = (byte) this.at;
        bArr2[1] = (byte) this.w;
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2 + 2] = bArr[i2];
        }
        this.y.onDataReceived(deviceInfo, i, bArr2);
    }

    private void e(byte[] bArr) {
        LogUtil.d("BTDeviceSendCommandUtil", "enter reSend");
        if (this.q == null) {
            LogUtil.d("BTDeviceSendCommandUtil", "mBtDeviceCommand is null");
            return;
        }
        if (bArr != null && bArr.length > 4 && bArr[2] == Byte.MAX_VALUE) {
            String d = blq.d(bArr);
            try {
                List<bmi> b = new bmn().c(d.substring(4, d.length())).b();
                if (b != null && b.size() == 1) {
                    b(bArr, b.get(0));
                } else {
                    LogUtil.d("BTDeviceSendCommandUtil", "wrong cmd ,no need resend.");
                }
            } catch (bmk e) {
                LogUtil.e("BTDeviceSendCommandUtil", e.getMessage());
            }
        } else {
            LogUtil.d("BTDeviceSendCommandUtil", "no need resend.");
        }
        this.q = null;
    }

    private void b(byte[] bArr, bmi bmiVar) {
        if (bli.e(bmiVar.e()) == 127) {
            if (bli.e(bmiVar.c()) == 100014) {
                LogUtil.d("BTDeviceSendCommandUtil", "pre resend device command.");
                d(bArr[0], bArr[1]);
                j(this.q);
                return;
            }
            return;
        }
        LogUtil.a("BTDeviceSendCommandUtil", "wrong tlv");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, int i, OperationDeviceInfo operationDeviceInfo) {
        if (this.y == null || deviceInfo == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "Parameter is incorrect.");
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "DeviceProtocol:", Integer.valueOf(deviceInfo.getDeviceProtocol()), "device state changed, btState:", Integer.valueOf(i));
        if (1 == i) {
            q();
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "Start to remove connect timeout message.");
            this.ap.removeMessages(2);
        }
        if (2 != i) {
            e(deviceInfo, i, operationDeviceInfo);
            return;
        }
        if (2 == this.m) {
            LogUtil.c("BTDeviceSendCommandUtil", "Already finish handshake and repeat report connected.");
            return;
        }
        if (this.ai) {
            LogUtil.c("BTDeviceSendCommandUtil", "Already Start handshake.");
            return;
        }
        this.ai = true;
        this.ap.removeMessages(3);
        this.av = true;
        this.ap.sendEmptyMessage(3, 60000L);
        int i2 = this.r;
        if (i2 == 0 || i2 == 5) {
            deviceInfo.setDeviceProtocol(2);
            this.am = 2;
            LogUtil.c("BTDeviceSendCommandUtil", "Set init AW Device Protocol : ", 2);
        }
        d(deviceInfo, i, operationDeviceInfo);
    }

    private void d(DeviceInfo deviceInfo, int i, OperationDeviceInfo operationDeviceInfo) {
        izf a2 = iyo.a(this.r);
        LogUtil.c("BTDeviceSendCommandUtil", "Start to get link parameter.");
        a(a2);
    }

    private void q() {
        Message obtain = Message.obtain();
        obtain.what = 2;
        LogUtil.c("BTDeviceSendCommandUtil", "Start to create connect timeout info msg.");
        this.ap.sendMessage(obtain, 60000L);
    }

    private void e(DeviceInfo deviceInfo, int i, OperationDeviceInfo operationDeviceInfo) {
        BTDeviceServiceBase bTDeviceServiceBase;
        this.ai = false;
        if (3 == i) {
            List<izf> list = this.l;
            if (list != null && list.size() != 0) {
                LogUtil.c("BTDeviceSendCommandUtil", "Start to clear command list for disconnect");
                synchronized (this.ba) {
                    this.l.clear();
                }
            }
            this.ap.removeMessages(1);
            this.v = this.u;
            if (deviceInfo.getDeviceBluetoothType() == 0 && 1 == this.am) {
                LogUtil.c("BTDeviceSendCommandUtil", "Need reset reSendCounter and unLockAW.");
                this.az.execute(new Runnable() { // from class: izi.4
                    @Override // java.lang.Runnable
                    public void run() {
                        izi.this.ad();
                    }
                });
            } else {
                LogUtil.c("BTDeviceSendCommandUtil", "Need reset reSendCounter and unlockBT.");
                this.az.execute(new Runnable() { // from class: izi.1
                    @Override // java.lang.Runnable
                    public void run() {
                        izi.this.aa();
                    }
                });
            }
        }
        if (2 == this.am) {
            LogUtil.c("BTDeviceSendCommandUtil", "Reset V2 package info.");
            this.o.resetPackageInfo();
            ab();
        }
        if (this.r == 0 && (bTDeviceServiceBase = this.s) != null) {
            bTDeviceServiceBase.removeV1CheckCommand();
        }
        deviceInfo.setDeviceProtocol(this.am);
        this.m = i;
        this.y.onDeviceConnectionStateChanged(deviceInfo, i, operationDeviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo, int i, byte[] bArr) {
        boolean z = (bArr == null || this.y == null) ? false : true;
        boolean z2 = this.r == 0 || this.o != null;
        if (z && z2) {
            int i2 = this.am;
            if (2 == i2) {
                String spliceMTUPackage = this.o.spliceMTUPackage(i, bArr);
                if (spliceMTUPackage == null) {
                    LogUtil.a("BTDeviceSendCommandUtil", "strDataContent is null.");
                    return;
                }
                if (spliceMTUPackage.length() != 0) {
                    byte[] a2 = blq.a(spliceMTUPackage);
                    List<izj> parseResponsePacket = this.o.parseResponsePacket(a2.length, a2);
                    if (parseResponsePacket == null) {
                        return;
                    }
                    d(parseResponsePacket, deviceInfo);
                    return;
                }
                LogUtil.c("BTDeviceSendCommandUtil", "BLE package and length less than MTU threshold.");
                return;
            }
            if (1 != i2) {
                if (i2 == 0) {
                    c(deviceInfo, i, bArr);
                    return;
                } else {
                    LogUtil.c("BTDeviceSendCommandUtil", "Do not support this link protocol.");
                    return;
                }
            }
            LogUtil.c("BTDeviceSendCommandUtil", "Current is V1 Protocol.");
            if (this.r == 0) {
                b(deviceInfo, bArr.length, bArr);
                ad();
            } else {
                e(deviceInfo, bArr);
            }
        }
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        String d = blq.d(bArr);
        if (d.contains("AA0100496C")) {
            String replace = d.replace("AA0100496C", "");
            if (!TextUtils.isEmpty(replace)) {
                LogUtil.c("BTDeviceSendCommandUtil", "Receive ACK info and Response at the same time.");
                bArr = blq.a(replace);
            }
        }
        String d2 = blq.d(bArr);
        if (d2.equals("AA0100496C") || d2.equals("AA01000049") || d2.equals("AA0C010000")) {
            LogUtil.c("BTDeviceSendCommandUtil", "Receive ACK info, mIsLastCommandPackage is ", Boolean.valueOf(this.af));
            if (!this.af) {
                aa();
                return;
            } else {
                LogUtil.c("BTDeviceSendCommandUtil", "The last CommandPackage,do unLockBT() need wait response in reportReceivedData()");
                return;
            }
        }
        if (d2.equals("AA0102692E")) {
            LogUtil.c("BTDeviceSendCommandUtil", "device tell sdk receive a incorrect command.");
        } else {
            b(deviceInfo, bArr);
        }
    }

    private void d(List<izj> list, DeviceInfo deviceInfo) {
        for (izj izjVar : list) {
            LogUtil.c("BTDeviceSendCommandUtil", "slice info : ", Boolean.valueOf(izjVar.d()), ". success info : ", Boolean.valueOf(izjVar.c()), ". receivedAll info : ", Boolean.valueOf(izjVar.b()));
            if (!izjVar.d() && izjVar.c()) {
                b(deviceInfo, izjVar.a(), izjVar.e());
            } else {
                if (this.aw.a() != 0) {
                    byte[] bArr = new byte[this.aw.e().length + izjVar.e().length];
                    System.arraycopy(this.aw.e(), 0, bArr, 0, this.aw.e().length);
                    System.arraycopy(izjVar.e(), 0, bArr, this.aw.e().length, izjVar.e().length);
                    this.aw.d(bArr);
                } else {
                    this.aw.d(izjVar.e());
                }
                this.aw.c(izjVar.a() + this.aw.a());
                this.aw.a(izjVar.c());
                this.aw.b(izjVar.b());
                if (this.aw.b()) {
                    b(deviceInfo, this.aw.a(), this.aw.e());
                    ab();
                }
            }
        }
    }

    private void c(DeviceInfo deviceInfo, int i, byte[] bArr) {
        LogUtil.c("BTDeviceSendCommandUtil", "Current is V0 Protocol.");
        List<izj> parseResponsePacket = this.o.parseResponsePacket(i, bArr);
        if (parseResponsePacket == null || parseResponsePacket.size() == 0) {
            LogUtil.a("BTDeviceSendCommandUtil", "Parse response packet fail.");
            return;
        }
        izj izjVar = parseResponsePacket.get(0);
        if (!izjVar.d()) {
            b(deviceInfo, izjVar.a(), izjVar.e());
            return;
        }
        if (izjVar.c()) {
            BTDeviceServiceBase bTDeviceServiceBase = this.s;
            if (bTDeviceServiceBase != null) {
                bTDeviceServiceBase.sendBTDeviceData(bArr);
            }
            if (this.aw.a() != 0) {
                byte[] bArr2 = new byte[this.aw.e().length + izjVar.e().length];
                System.arraycopy(this.aw.e(), 0, bArr2, 0, this.aw.e().length);
                System.arraycopy(izjVar.e(), 0, bArr2, this.aw.e().length, izjVar.e().length);
                this.aw.d(bArr2);
            } else {
                this.aw.d(izjVar.e());
            }
            this.aw.c(izjVar.e().length + this.aw.a());
            this.aw.a(izjVar.c());
            this.aw.b(izjVar.b());
            if (izjVar.b()) {
                b(deviceInfo, this.aw.a(), this.aw.e());
                ab();
                return;
            }
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "Receive incorrect data from device.");
    }

    private void b(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "dealWithOnDataReceivedV1NotAwParse dataPayload is null");
            return;
        }
        if (blq.d(bArr).startsWith("cc") || blq.d(bArr).startsWith("CC")) {
            b(deviceInfo, bArr.length, bArr);
            return;
        }
        List<izj> parseResponsePacket = this.o.parseResponsePacket(bArr.length, bArr);
        if (parseResponsePacket == null || parseResponsePacket.isEmpty()) {
            LogUtil.a("BTDeviceSendCommandUtil", "Parse response packet fail.");
            return;
        }
        izj izjVar = parseResponsePacket.get(0);
        if (!izjVar.d()) {
            BTDeviceServiceBase bTDeviceServiceBase = this.s;
            if (bTDeviceServiceBase != null) {
                bTDeviceServiceBase.sendBTDeviceData(blq.a("AA0101594D"));
            }
            LogUtil.c("BTDeviceSendCommandUtil", "Report V1 not sliced data.");
            b(deviceInfo, izjVar.a(), izjVar.e());
            return;
        }
        if (this.aw.a() != 0) {
            if (this.aw.e() != null && izjVar.e() != null) {
                byte[] bArr2 = new byte[this.aw.e().length + izjVar.e().length];
                System.arraycopy(this.aw.e(), 0, bArr2, 0, this.aw.e().length);
                System.arraycopy(izjVar.e(), 0, bArr2, this.aw.e().length, izjVar.e().length);
                this.aw.d(bArr2);
            } else {
                LogUtil.c("BTDeviceSendCommandUtil", "data array is null.");
            }
        } else {
            this.aw.d(izjVar.e());
        }
        this.aw.c(izjVar.a() + this.aw.a());
        this.aw.a(izjVar.c());
        this.aw.b(izjVar.b());
        BTDeviceServiceBase bTDeviceServiceBase2 = this.s;
        if (bTDeviceServiceBase2 != null) {
            bTDeviceServiceBase2.sendBTDeviceData(blq.a("AA0101594D"));
        }
        if (izjVar.b()) {
            LogUtil.c("BTDeviceSendCommandUtil", "Report V1 sliced data.");
            b(deviceInfo, this.aw.a(), this.aw.e());
            ab();
        }
    }

    private void ab() {
        this.aw.b(false);
        this.aw.c(0);
        this.aw.d(null);
        this.aw.e(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        synchronized (this.b) {
            if (this.e) {
                this.b.notifyAll();
                this.e = false;
            }
            this.ax = false;
            LogUtil.c("BTDeviceSendCommandUtil", " unLockAW, mSendingV1Command set false.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        synchronized (this.c) {
            if (this.f13681a) {
                this.c.notifyAll();
                this.f13681a = false;
            }
            this.ax = false;
            LogUtil.c("BTDeviceSendCommandUtil", " unLockBT, mSendingV1Command set false.");
        }
    }

    private boolean g(byte[] bArr) {
        int length = bArr.length;
        ArrayList arrayList = new ArrayList(12);
        int i = this.ak;
        if (length <= i) {
            arrayList.add(bArr);
        } else {
            int i2 = length % i > 0 ? (length / i) + 1 : length / i;
            int i3 = 0;
            while (i3 < i2) {
                int i4 = this.ak;
                int i5 = i3 == i2 + (-1) ? length - (i3 * i4) : i4;
                int i6 = i4 * i3;
                arrayList.add(Arrays.copyOfRange(bArr, i6, i5 + i6));
                i3++;
            }
        }
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!this.s.sendBTDeviceData((byte[]) it.next())) {
                LogUtil.c("BTDeviceSendCommandUtil", "sendLinkDataCommond has write false");
                z = false;
            }
            if (1 == this.am) {
                try {
                    int i7 = this.ag;
                    if (i7 > 0) {
                        Thread.sleep(i7);
                    }
                } catch (InterruptedException unused) {
                    LogUtil.e("BTDeviceSendCommandUtil", "0xA0200006", "sendLinkDataCommand InterruptedException");
                }
            }
        }
        if (2 == this.am) {
            try {
                int i8 = this.ag;
                if (i8 > 0) {
                    Thread.sleep(i8);
                }
            } catch (InterruptedException unused2) {
                LogUtil.e("BTDeviceSendCommandUtil", "0xA0200006", "sendLinkDataCommand2 InterruptedException:");
            }
        }
        return z;
    }

    private void k() {
        int i = this.ao + 1;
        this.ao = i;
        if (256 == i) {
            this.ao = 0;
        }
    }

    protected byte[] b(byte[] bArr) {
        if (bArr == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "transferV2ToV1Protocol with parameter is null.");
            return new byte[0];
        }
        byte[] c = iyt.c(bArr);
        if (c == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "transferV2ToV1Protocol with commandData is null.");
            return new byte[0];
        }
        k();
        if (c.length > 4) {
            c[0] = (byte) this.ao;
            this.ay = c[1];
            this.au = c[2];
            byte b = c[3];
            this.bc = b;
            if (b < 0) {
                this.bc = b + 128;
            }
        }
        return c;
    }

    private int y() {
        boolean z;
        List<izf> list = this.l;
        int i = -1;
        if (list == null) {
            return -1;
        }
        Iterator<izf> it = list.iterator();
        izf izfVar = null;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            i++;
            izf next = it.next();
            if (next instanceof izf) {
                izfVar = next;
            }
            if (izfVar != null && 2 == izfVar.j()) {
                z = true;
                break;
            }
        }
        if (z) {
            return i;
        }
        return 0;
    }

    private void w() {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter reportTimeoutInfo() with ServiceID is ", Integer.valueOf(this.at), " CommandID : ", Integer.valueOf(this.w));
        String c = iyo.c(this.at, this.w, this.am, this.ao, this.ay);
        if (c.length() != 0) {
            byte[] a2 = blq.a(c);
            LogUtil.c("BTDeviceSendCommandUtil", "Timeout Info : ", c);
            b(g(), a2.length, a2);
        }
    }

    private void u() {
        DeviceInfo deviceInfo;
        try {
            BTDeviceServiceBase bTDeviceServiceBase = this.s;
            if (bTDeviceServiceBase != null) {
                deviceInfo = bTDeviceServiceBase.getDeviceInfo();
                this.s.disconnectGMS();
            } else {
                deviceInfo = null;
            }
            if (deviceInfo == null) {
                deviceInfo = new DeviceInfo();
                if (this.r == 0) {
                    deviceInfo.setDeviceIdentify("AndroidWear");
                    deviceInfo.setDeviceName("HUAWEI WATCH");
                } else {
                    BluetoothDevice bluetoothDevice = this.i;
                    if (bluetoothDevice != null) {
                        deviceInfo.setDeviceIdentify(bluetoothDevice.getAddress());
                        deviceInfo.setDeviceName(this.i.getName());
                    } else {
                        LogUtil.c("BTDeviceSendCommandUtil", "mBTDevice is null");
                    }
                }
            }
            deviceInfo.setDeviceConnectState(4);
            e(deviceInfo, 2010000);
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceSendCommandUtil", "reportConnectFailed SecurityException:", ExceptionUtils.d(e));
        }
    }

    private boolean j(izf izfVar) {
        if (2 != p()) {
            this.ax = false;
            if (this.ai) {
                this.ai = false;
                u();
            }
            LogUtil.c("BTDeviceSendCommandUtil", "base Service connect state is not connected so return.");
            return false;
        }
        if (this.l.size() == 0) {
            LogUtil.c("BTDeviceSendCommandUtil", "mBTDeviceCommandList size : 0, so cancel send wrap data.");
            return false;
        }
        if (izfVar != null) {
            byte[] d = izfVar.d();
            if (izfVar.c() == 0 && 1 == this.am) {
                if (!e(d, izfVar)) {
                    return false;
                }
            } else if (!e(izfVar, d)) {
                return false;
            }
        }
        return this.bb;
    }

    private boolean e(byte[] bArr, izf izfVar) {
        BTDeviceServiceBase bTDeviceServiceBase = this.s;
        if (bTDeviceServiceBase != null && this.b != null) {
            try {
                bTDeviceServiceBase.sendBTDeviceData(bArr);
                synchronized (this.b) {
                    this.e = true;
                    if (this.ai) {
                        LogUtil.c("BTDeviceSendCommandUtil", "BTDeviceAWService Enter Lock of sendBTDeviceWrapData Handshake, timeout : ", 30000);
                        this.u = 1;
                        if (2 != p()) {
                            this.e = false;
                            return false;
                        }
                        this.b.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                    } else {
                        LogUtil.c("BTDeviceSendCommandUtil", "BTDeviceAWService Enter Lock of sendBTDeviceWrapData, timeout : ", 10000);
                        if (2 != p()) {
                            this.e = false;
                            return false;
                        }
                        this.b.wait(PreConnectManager.CONNECT_INTERNAL);
                    }
                    if (this.e) {
                        g(izfVar);
                    }
                }
            } catch (InterruptedException unused) {
                LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", "dwsbw InterruptedException");
            }
        }
        return true;
    }

    private void g(izf izfVar) {
        int i = this.v + 1;
        this.v = i;
        if (this.u == i) {
            if (this.ai) {
                this.ai = false;
                BTDeviceServiceBase bTDeviceServiceBase = this.s;
                DeviceInfo deviceInfo = bTDeviceServiceBase != null ? bTDeviceServiceBase.getDeviceInfo() : null;
                if (deviceInfo == null) {
                    deviceInfo = new DeviceInfo();
                    deviceInfo.setDeviceIdentify("AndroidWear");
                    deviceInfo.setDeviceName("HUAWEI WATCH");
                }
                deviceInfo.setDeviceConnectState(4);
                LogUtil.c("BTDeviceSendCommandUtil", "Start to report aw v1 protocol connect fail.");
                e(deviceInfo, 2010009);
            } else if (izfVar.f()) {
                w();
            }
            this.ax = false;
            LogUtil.a("BTDeviceSendCommandUtil", "BTDeviceAWService Set Sending flag false for AW timeout.");
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "BTDeviceAWService Command send timeout but counter not arrived : ", Integer.valueOf(i));
        }
        this.e = false;
    }

    private boolean e(izf izfVar, byte[] bArr) {
        ArrayList<byte[]> wrapCommandPackets;
        if (this.o != null) {
            int e = izfVar.e();
            if (3 == izfVar.b()) {
                LogUtil.c("BTDeviceSendCommandUtil", "OTA send bytes of file data.");
                wrapCommandPackets = e(e, bArr);
            } else {
                wrapCommandPackets = this.o.wrapCommandPackets(e, bArr);
            }
            if (wrapCommandPackets == null) {
                return true;
            }
            izg izgVar = new izg(wrapCommandPackets, izfVar.f());
            if (this.s == null) {
                return true;
            }
            this.af = false;
            return e(izgVar, izfVar);
        }
        LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", "disconnectBTDevice: mBTDeviceServiceBase is null.");
        return true;
    }

    private boolean e(izg izgVar, izf izfVar) {
        Object obj;
        int i = 0;
        while (true) {
            if (i >= izgVar.a().size()) {
                break;
            }
            byte[] bArr = izgVar.a().get(i);
            LogUtil.c("BTDeviceSendCommandUtil", "wrapData i: ", Integer.valueOf(i), ",size: ", Integer.valueOf(izgVar.a().size()));
            if (i == izgVar.a().size() - 1) {
                this.af = true;
            }
            try {
            } catch (InterruptedException unused) {
                LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", " dwsbwdopf InterruptedException");
            }
            if (3 != izfVar.b()) {
                if (c(izfVar) && (obj = this.c) != null) {
                    synchronized (obj) {
                        this.f13681a = true;
                        boolean g = g(bArr);
                        this.bb = g;
                        LogUtil.c("BTDeviceSendCommandUtil", "sendLinkDataCommand result:", Boolean.valueOf(g));
                        if (!b(izfVar)) {
                            return false;
                        }
                        if (this.f13681a) {
                            e(izfVar);
                        }
                    }
                    break;
                }
                boolean g2 = g(bArr);
                this.bb = g2;
                LogUtil.c("BTDeviceSendCommandUtil", "sendLinkData result:", Boolean.valueOf(g2));
                i++;
            } else {
                this.bb = this.s.sendBTDeviceData(bArr);
                try {
                    Thread.sleep(this.ag);
                } catch (InterruptedException unused2) {
                    LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", "Sleep InterruptedException with exception.");
                }
                this.ax = false;
                LogUtil.c("BTDeviceSendCommandUtil", "Set Sending flag false for OTA transfer file...");
                i++;
            }
            LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", " dwsbwdopf InterruptedException");
            i++;
        }
        return true;
    }

    private boolean b(izf izfVar) throws InterruptedException {
        if (1 == izfVar.g() && 14 == izfVar.a()) {
            LogUtil.c("BTDeviceSendCommandUtil", "Lock of sendBTDeviceWrapData with device pair. timeout : ", 30000);
            this.u = 1;
            if (2 != p()) {
                this.f13681a = false;
                return false;
            }
            this.c.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
        } else if (this.r == 0 && 1 == izfVar.g() && 1 == izfVar.a()) {
            this.u = 1;
            LogUtil.c("BTDeviceSendCommandUtil", "BTDeviceAWService Enter Lock of sendBTDeviceWrapData. timeout : ", 30000);
            if (2 != p()) {
                this.f13681a = false;
                return false;
            }
            this.c.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "Enter Lock of sendBTDeviceWrapData. timeout : ", 10000);
            if (2 != p()) {
                this.f13681a = false;
                return false;
            }
            this.c.wait(PreConnectManager.CONNECT_INTERNAL);
        }
        return true;
    }

    private boolean c(izf izfVar) {
        return r() || (2 == this.am && (this.af && izfVar.f()));
    }

    private boolean r() {
        int i = this.am;
        return 1 == i || i == 0;
    }

    private void e(izf izfVar) {
        int i = this.v + 1;
        this.v = i;
        if (this.u == i) {
            LogUtil.c("BTDeviceSendCommandUtil", "Command send timeout and counter arrived.");
            if (this.ai) {
                if (this.y != null) {
                    this.ai = false;
                    LogUtil.c("DEVMGR_SETTING", "BTDeviceSendCommandUtil", " shake failed due to timeout");
                    ize.e(2010000);
                    u();
                }
            } else if (izfVar.f()) {
                w();
            }
            this.ax = false;
            LogUtil.c("BTDeviceSendCommandUtil", "Set Sending flag false for BT timeout.");
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "Command send timeout but counter not arrived : ", Integer.valueOf(i));
        }
        this.f13681a = false;
    }

    private ArrayList<byte[]> e(int i, byte[] bArr) {
        if (i <= 0 || bArr == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "wrapOTAFilePackets error, dataContent is null.");
            return null;
        }
        ArrayList<byte[]> arrayList = new ArrayList<>(12);
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr);
        allocate.flip();
        arrayList.add(allocate.array());
        return arrayList;
    }

    private byte[] e(byte[] bArr, int i) {
        if (bArr == null) {
            LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", "otaCmdForDeviceCommand with parameter deviceData is null.");
            return new byte[0];
        }
        LogUtil.c("BTDeviceSendCommandUtil", "otaCmdForDeviceCommand , type : ", Integer.valueOf(i));
        if (3 == i) {
            return bArr;
        }
        byte[] bArr2 = new byte[bArr.length + 1];
        k();
        bArr2[0] = (byte) this.ao;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        return bArr2;
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (izi.this.s == null || izi.this.s.getBTDeviceConnectState() == 2 || izi.this.l == null || izi.this.l.size() == 0) {
                    izi.this.o();
                    return true;
                }
                LogUtil.c("BTDeviceSendCommandUtil", "send command but link is not connected");
                synchronized (izi.this.ba) {
                    izi.this.l.clear();
                }
                return true;
            }
            if (i == 2) {
                if (izi.this.s != null) {
                    LogUtil.c("BTDeviceSendCommandUtil", "Receive CONNECT_TIMEOUT_COMMAND Message.Start to disconnect service.");
                    ize.e(2010001);
                    izi.this.s.disconnectBTDevice();
                }
                return true;
            }
            if (i == 3) {
                LogUtil.c("BTDeviceSendCommandUtil", "reconnect timeout.no get 5.1.1 data.");
                izi.this.av = false;
                return true;
            }
            LogUtil.c("BTDeviceSendCommandUtil", "unknown what : ", Integer.valueOf(message.what));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        int y;
        izf izfVar;
        LogUtil.c("BTDeviceSendCommandUtil", "sendResult : ", Boolean.valueOf(this.bb), " size:", Integer.valueOf(this.l.size()));
        if (this.l.size() != 0) {
            synchronized (this.ba) {
                y = y();
                izfVar = y < this.l.size() ? this.l.get(y) : null;
            }
            if (izfVar != null) {
                if (2 == this.am) {
                    c(izfVar, y);
                    return;
                } else {
                    b(izfVar, y);
                    return;
                }
            }
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "mBTDeviceCommandList has no command.");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(defpackage.izf r6, int r7) {
        /*
            r5 = this;
            boolean r0 = r5.ax
            if (r0 != 0) goto Ld0
            int r0 = r6.g()
            r5.at = r0
            int r0 = r6.a()
            r5.w = r0
            java.lang.String r0 = "ServiceID : "
            int r1 = r5.at
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = " CommandID : "
            int r3 = r5.w
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3}
            java.lang.String r1 = "BTDeviceSendCommandUtil"
            health.compact.a.LogUtil.c(r1, r0)
            java.lang.String r0 = r6.h()
            int r1 = r5.at
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L3f
            int r1 = r5.w
            r4 = 5
            if (r1 != r4) goto L3f
            izf r6 = defpackage.iyo.e(r2)
            r6.e(r0)
        L3f:
            int r1 = r5.at
            if (r1 != r3) goto L50
            int r1 = r5.w
            r4 = 50
            if (r1 != r4) goto L50
            izf r6 = defpackage.iyo.o()
            r6.e(r0)
        L50:
            boolean r0 = r6.f()
            if (r0 == 0) goto L66
            java.lang.String r0 = "Command need ack."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "BTDeviceSendCommandUtil"
            health.compact.a.LogUtil.c(r1, r0)
            r5.ax = r3
            r0 = 3
            r5.u = r0
        L66:
            com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase r0 = r5.s
            if (r0 == 0) goto L80
            boolean r0 = r6.i()
            if (r0 == 0) goto L75
            boolean r0 = r5.x()
            goto L81
        L75:
            java.lang.String r0 = "do not need encrpt"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "BTDeviceSendCommandUtil"
            health.compact.a.LogUtil.c(r1, r0)
        L80:
            r0 = r2
        L81:
            r5.d(r6, r0)
            boolean r0 = r6.f()
            if (r0 == 0) goto L8e
            r5.d(r6)
            goto L94
        L8e:
            boolean r6 = r5.j(r6)
            r5.bb = r6
        L94:
            java.lang.Object r6 = r5.ba
            monitor-enter(r6)
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r1 = "rm V2 cmd from list."
            r0[r2] = r1     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r1 = "BTDeviceSendCommandUtil"
            health.compact.a.LogUtil.c(r1, r0)     // Catch: java.lang.Throwable -> Lcd
            java.util.List<izf> r0 = r5.l     // Catch: java.lang.Throwable -> Lcd
            int r0 = r0.size()     // Catch: java.lang.Throwable -> Lcd
            if (r7 >= r0) goto Lb3
            if (r7 < 0) goto Lb3
            java.util.List<izf> r0 = r5.l     // Catch: java.lang.Throwable -> Lcd
            r0.remove(r7)     // Catch: java.lang.Throwable -> Lcd
            goto Lcb
        Lb3:
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r0 = "Remove fail for index is not in rang, mBTDeviceCommandList.size() : "
            r7[r2] = r0     // Catch: java.lang.Throwable -> Lcd
            java.util.List<izf> r0 = r5.l     // Catch: java.lang.Throwable -> Lcd
            int r0 = r0.size()     // Catch: java.lang.Throwable -> Lcd
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lcd
            r7[r3] = r0     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r0 = "BTDeviceSendCommandUtil"
            health.compact.a.LogUtil.a(r0, r7)     // Catch: java.lang.Throwable -> Lcd
        Lcb:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lcd
            goto Ld0
        Lcd:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lcd
            throw r7
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.izi.c(izf, int):void");
    }

    private boolean x() {
        DeviceInfo deviceInfo = this.s.getDeviceInfo();
        if (deviceInfo == null || deviceInfo.getDeviceBluetoothType() != 2) {
            return false;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "Current device type is ble or isHiChain");
        int i = this.at;
        if (i == 9 || i == 10) {
            return false;
        }
        if (i == 1) {
            int i2 = this.w;
            boolean z = (i2 == 1 || i2 == 14) ? false : true;
            boolean z2 = (i2 == 15 || i2 == 19 || i2 == 40 || i2 == 44) ? false : true;
            boolean z3 = (i2 == 22 || i2 == 45 || i2 == 51 || i2 == 52) ? false : true;
            if (!z || !z2 || !z3) {
                return false;
            }
        }
        return true;
    }

    private void d(izf izfVar, boolean z) {
        byte[] bArr;
        BTDeviceServiceBase bTDeviceServiceBase;
        if (z) {
            if (this.i != null && (bTDeviceServiceBase = this.s) != null) {
                bArr = iyo.c(this.ac, izfVar.d(), izfVar.h(), this.bb, bTDeviceServiceBase.getDeviceInfo());
            } else {
                LogUtil.c("BTDeviceSendCommandUtil", "mBTDevice is null");
                bArr = null;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "Get encryptData info:";
            objArr[1] = Boolean.valueOf(bArr != null);
            LogUtil.c("BTDeviceSendCommandUtil", objArr);
            if (bArr != null) {
                izfVar.e(bArr.length);
                izfVar.e(bArr);
            } else {
                izfVar.e(izfVar.d().length);
                izfVar.e(izfVar.d());
            }
        }
    }

    private void d(izf izfVar) {
        int i;
        byte[] bArr;
        BTDeviceServiceBase bTDeviceServiceBase;
        this.v = 0;
        LogUtil.d("BTDeviceSendCommandUtil", "mBtDeviceCommand : btDeviceCommand");
        this.q = izfVar;
        int i2 = 0;
        do {
            i2++;
            LogUtil.c("BTDeviceSendCommandUtil", "Send V2 Command timeout with ReSendTime : ", Integer.valueOf(this.v));
            if (this.v != 0 && 1 == this.at && 5 == this.w) {
                izf e = iyo.e(false);
                if (this.i != null && (bTDeviceServiceBase = this.s) != null) {
                    bArr = iyo.c(this.ac, e.d(), izfVar.h(), this.bb, bTDeviceServiceBase.getDeviceInfo());
                } else {
                    LogUtil.a("BTDeviceSendCommandUtil", "0xA0200006", "mBTDevice is null");
                    bArr = null;
                }
                if (bArr != null) {
                    izfVar.e(bArr);
                    izfVar.e(bArr.length);
                } else {
                    LogUtil.c("BTDeviceSendCommandUtil", "encryptData is null.");
                }
            }
            this.bb = j(izfVar);
            if (i2 > 5) {
                Log.i("BTDeviceSendCommandUtil", "repeat send error");
                return;
            } else {
                i = this.v;
                if (i == 0) {
                    return;
                }
            }
        } while (this.u > i);
    }

    private void b(izf izfVar, int i) {
        byte[] a2;
        if (this.ax || this.t == null) {
            return;
        }
        this.ax = true;
        this.u = 3;
        LogUtil.c("BTDeviceSendCommandUtil", "Set Sending flag true.");
        byte[] d = izfVar.d();
        this.x = izfVar.b();
        if (1 == this.am) {
            a2 = c(izfVar, d);
        } else {
            a2 = a(izfVar, d);
        }
        a(izfVar, i, a2);
    }

    private byte[] c(izf izfVar, byte[] bArr) {
        this.at = izfVar.g();
        this.w = izfVar.a();
        LogUtil.c("BTDeviceSendCommandUtil", "SEND_COMMAND Msg with mServiceID : ", Integer.valueOf(this.at), " mCommandID : ", Integer.valueOf(this.w));
        if (this.x != 1) {
            return e(izfVar.d(), izfVar.b());
        }
        if (izfVar.i()) {
            byte b = bArr[0];
            if (7 == b && 2 == bArr[1]) {
                LogUtil.c("BTDeviceSendCommandUtil", "Need change command ID for set userInfo.");
                bArr[1] = 9;
                this.w = 9;
            } else if (2 == b && 1 == bArr[1]) {
                LogUtil.c("BTDeviceSendCommandUtil", "Need change command ID for send message.");
                bArr[1] = 3;
                this.w = 3;
            } else {
                LogUtil.c("BTDeviceSendCommandUtil", "dealWithHandMessageSendCommandNotV2DealV1() else");
            }
        }
        return b(bArr);
    }

    private byte[] a(izf izfVar, byte[] bArr) {
        try {
            Thread.sleep(5L);
        } catch (InterruptedException unused) {
            LogUtil.e("BTDeviceSendCommandUtil", "Exception is InterruptedException.");
        }
        this.at = bArr[0];
        this.w = bArr[1];
        int e = izfVar.e() - 2;
        byte[] bArr2 = new byte[e];
        for (int i = 0; i < e; i++) {
            bArr2[i] = bArr[i + 2];
        }
        return bArr2;
    }

    private void a(izf izfVar, int i, byte[] bArr) {
        int i2;
        byte[] b;
        BTDeviceServiceBase bTDeviceServiceBase;
        if (bArr != null) {
            LogUtil.c("BTDeviceSendCommandUtil", "Is command need encrypt ?: ", Boolean.valueOf(izfVar.i()));
            if (izfVar.i()) {
                if (this.i != null && (bTDeviceServiceBase = this.s) != null) {
                    bArr = iyo.d(this.ac, bArr, this.i.getAddress(), bTDeviceServiceBase.getDeviceInfo());
                } else {
                    LogUtil.c("BTDeviceSendCommandUtil", "mBTDevice is null.");
                }
            }
            blt.d("BTDeviceSendCommandUtil", bArr, "After encryptTLVs & transferV2ToV1, btV1Command : ");
            b(izfVar, bArr);
            this.v = 0;
            do {
                LogUtil.c("BTDeviceSendCommandUtil", "Send V1 Command timeout with ReSendTime : ", Integer.valueOf(this.v));
                if (this.v != 0 && 1 == this.at && 5 == this.w && (b = b(iyo.e(false).d())) != null) {
                    this.t.e(b);
                    this.t.e(b.length);
                }
                j(this.t);
                i2 = this.v;
                if (i2 == 0) {
                    break;
                }
            } while (this.u > i2);
            synchronized (this.ba) {
                LogUtil.c("BTDeviceSendCommandUtil", "Remove V1 Command from list,iCommandIndex : ", Integer.valueOf(i));
                if (i >= this.l.size() || i < 0) {
                    LogUtil.a("BTDeviceSendCommandUtil", "Remove fail for index is not in rang, size() : ", Integer.valueOf(this.l.size()));
                } else {
                    this.l.remove(i);
                }
            }
        }
    }

    private void b(izf izfVar, byte[] bArr) {
        this.t.e(izfVar.f());
        this.t.e(izfVar.h());
        this.t.c(izfVar.c());
        this.t.b(izfVar.j());
        this.t.e(bArr);
        this.t.e(bArr.length);
        this.t.a(izfVar.b());
        this.t.a(izfVar.i());
        this.t.i(izfVar.g());
        this.t.d(izfVar.a());
    }

    public void a(izf izfVar) {
        if (izfVar == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "btDeviceCommand is null.");
            return;
        }
        if (this.l != null) {
            synchronized (this.ba) {
                this.l.add(izfVar);
                ExtendHandler extendHandler = this.ap;
                if (extendHandler != null) {
                    extendHandler.sendEmptyMessage(1);
                    LogUtil.c("BTDeviceSendCommandUtil", "insert queue.");
                } else {
                    LogUtil.a("BTDeviceSendCommandUtil", "sendBTDeviceData mSendHandler is null");
                }
            }
            return;
        }
        LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", "mBTDeviceCommandList is null.");
    }

    public void d(String str) {
        if (str == null) {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "filePath is null.");
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "sendAssetData sendBTDeviceAssetData filePath : ", str);
            this.s.sendBTFilePath(str);
        }
    }

    public void d(BtDeviceResponseCallback btDeviceResponseCallback) {
        if (btDeviceResponseCallback == null) {
            LogUtil.e("BTDeviceSendCommandUtil", "0xA0200008", "callback is null.");
        } else {
            LogUtil.c("BTDeviceSendCommandUtil", "setFileCallback callback : ", btDeviceResponseCallback);
            this.s.setFileCallback(btDeviceResponseCallback);
        }
    }

    public void b(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter setAWAssetCallback");
        if (btDeviceResponseCallback == null) {
            LogUtil.c("BTDeviceSendCommandUtil", "setAWAssetCallback callback is null");
        } else {
            this.s.setAwAssetCallback(btDeviceResponseCallback);
        }
    }

    public BTDeviceServiceBase i() {
        return this.s;
    }

    public void c(boolean z, int i) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter setBLEHandshakeStatus() with needHandshake : ", Boolean.valueOf(z));
        this.ai = z;
        a(i);
    }

    private void a(int i) {
        if (i == 2) {
            i = p() == 2 ? 2 : 3;
        }
        this.m = i;
    }

    public void e(boolean z) {
        this.an = z;
    }

    public boolean n() {
        return this.an;
    }

    public DeviceInfo g() {
        BTDeviceServiceBase bTDeviceServiceBase = this.s;
        if (bTDeviceServiceBase != null) {
            return bTDeviceServiceBase.getDeviceInfo();
        }
        LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "getDeviceInfo: mBTDeviceServiceBase is null.");
        return null;
    }

    private boolean c(byte[] bArr) {
        return (4 == bArr[1] && bArr[2] == 0 && 8 == bArr[3]) ? false : true;
    }

    public void c(boolean z) {
        this.aa = z;
    }

    public boolean d() {
        return this.aa;
    }

    public boolean m() {
        return this.z;
    }

    public void b(boolean z) {
        this.z = z;
    }

    public void e(int i) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter btSwitchChangeInfo() with status : ", Integer.valueOf(i));
        BTDeviceServiceBase bTDeviceServiceBase = this.s;
        if (bTDeviceServiceBase != null) {
            bTDeviceServiceBase.btSwitchChangeInfo(i);
        } else {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "mBTDeviceServiceBase is null.");
        }
    }

    public void d(boolean z) {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter setBLEReconnectEnableFlag() with status : ", Boolean.valueOf(z));
        this.ab = z;
    }

    public boolean c() {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter getBLEReconnectEnableFlag() with status : ", Boolean.valueOf(this.ab));
        return this.ab;
    }

    public void b() {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter disconnectGMS()");
        BTDeviceServiceBase bTDeviceServiceBase = this.s;
        if (bTDeviceServiceBase != null) {
            bTDeviceServiceBase.disconnectGMS();
        } else {
            LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "mBTDeviceServiceBase is null.");
        }
    }

    private void l() {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter bindIConnectService.");
        if (bld.d() && iyo.b()) {
            if (this.ac != null) {
                Intent intent = new Intent("com.huawei.iconnect.action.WEAR_CONNECT_SERVICE");
                intent.setPackage("com.huawei.iconnect");
                LogUtil.c("BTDeviceSendCommandUtil", "start to bind iconnect service.");
                try {
                    if (this.ac.getApplicationContext().bindService(intent, this.ad, 1)) {
                        return;
                    }
                } catch (SecurityException unused) {
                    LogUtil.e("BTDeviceSendCommandUtil", "bindIConnectService SecurityException");
                }
                LogUtil.a("BTDeviceSendCommandUtil", "bind iconnect fail so set service handle is null.");
                iyo.a((IWearConnectService) null);
                return;
            }
            LogUtil.a("BTDeviceSendCommandUtil", "mContext is null so set service handle is null.");
            iyo.a((IWearConnectService) null);
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "iconnect do not exist or do not have iconnect action.");
    }

    private void z() {
        LogUtil.c("BTDeviceSendCommandUtil", "Enter unBindIConnectService.");
        if (bld.d() && iyo.b()) {
            if (this.ac != null) {
                try {
                    iyo.a((IWearConnectService) null);
                    this.ac.getApplicationContext().unbindService(this.ad);
                    return;
                } catch (IllegalArgumentException unused) {
                    LogUtil.e("BTDeviceSendCommandUtil", "unBindIConnectService with IllegalArgumentException.");
                    return;
                }
            }
            LogUtil.a("BTDeviceSendCommandUtil", "mContext is null.");
            return;
        }
        LogUtil.c("BTDeviceSendCommandUtil", "iconnect do not exist or do not have iconnect action.");
    }

    private int p() {
        BTDeviceServiceBase bTDeviceServiceBase = this.s;
        if (bTDeviceServiceBase != null) {
            return bTDeviceServiceBase.getBTDeviceConnectState();
        }
        return 3;
    }

    private OperationDeviceInfo e(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("BTDeviceSendCommandUtil", "setConnectFailedParms mac is null");
            return null;
        }
        izn iznVar = this.as;
        if (iznVar == null) {
            LogUtil.c("BTDeviceSendCommandUtil", "setConnectFailedParms mOpAnalyticsManager is null");
            return null;
        }
        iznVar.a(str, System.currentTimeMillis());
        this.as.c(str, iyg.c(this.at, this.w));
        this.as.a(str, i);
        return this.as.e(str);
    }

    public int h() {
        return this.r;
    }
}
