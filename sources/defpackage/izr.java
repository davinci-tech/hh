package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.SmartWatchCallback;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceAWService;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BtDeviceAwHostService;
import health.compact.a.LogUtil;
import java.util.Collection;

/* loaded from: classes5.dex */
public class izr implements BTDeviceServiceBase {
    private static final Object b = new Object();
    private BtDeviceStateCallback d;
    private Context e;
    private String g;
    private ExtendHandler h;
    private GoogleApiClient i;
    private Node f = null;

    /* renamed from: a, reason: collision with root package name */
    private int f13692a = 0;
    private DeviceInfo c = new DeviceInfo();
    private SmartWatchCallback j = new SmartWatchCallback() { // from class: izr.4
        @Override // com.huawei.hwbtsdk.btdatatype.callback.SmartWatchCallback
        public void onAckReceived(String str, int i, byte[] bArr) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.SmartWatchCallback
        public void onDeviceConnectionStateChanged(String str, int i) {
            LogUtil.c("DEVMGR_SETTING", izr.this.c(), "onDeviceConnectionStateChanged:", izr.this.g, ";nodeId:", str);
            if (TextUtils.isEmpty(izr.this.g)) {
                izr.this.g = str;
                BTDeviceAWService.d(izr.this.e).d().put(izr.this.g, izr.this.j);
            }
            synchronized (izr.b) {
                if (i == 2) {
                    if (izr.this.f == null) {
                        izr.this.d();
                    }
                }
                izr.this.a(i);
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.SmartWatchCallback
        public void onDataReceived(String str, int i, byte[] bArr) {
            if (izr.this.d != null) {
                LogUtil.c(izr.this.c(), "onDataReceived, nodeid:", str, ";deviceName:", izr.this.c.getDeviceName());
                izr.this.d.onDataReceived(izr.this.c, i, bArr);
            }
        }
    };

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void connectBTDevice(BluetoothDevice bluetoothDevice) {
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void removeV1CheckCommand() {
    }

    public izr(Context context, String str, BtDeviceStateCallback btDeviceStateCallback) {
        this.g = "";
        this.e = null;
        this.d = null;
        LogUtil.c("DEVMGR_SETTING", c(), "create BTDeviceSmartWatchService", str);
        this.e = context;
        this.g = str;
        this.c.setNodeId(str);
        this.c.setDeviceProtocol(2);
        this.d = btDeviceStateCallback;
        if (str == null) {
            BTDeviceAWService.d(this.e).d(this.j);
            this.c.setDeviceIdentify("AndroidWear");
            this.c.setDeviceBluetoothType(0);
        } else {
            BTDeviceAWService.d(this.e).d().put(this.g, this.j);
            this.c.setDeviceIdentify(this.g + "smart_watch");
            this.c.setDeviceBluetoothType(5);
        }
        g();
    }

    private void g() {
        this.h = HandlerCenter.yt_(new Handler.Callback() { // from class: izr.5
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message.what != 1) {
                    return false;
                }
                izr.this.d();
                return true;
            }
        }, "BTDeviceSmartWatchService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        return "BTDeviceSmartWatchService" + this.g;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void connectBTDevice(String str) {
        LogUtil.c("DEVMGR_SETTING", c(), "Enter connectBTDevice()");
        Context context = this.e;
        if (context == null) {
            a(4);
            return;
        }
        BtDeviceAwHostService.e(context);
        a(1);
        GoogleApiClient b2 = BTDeviceAWService.d(this.e).b();
        this.i = b2;
        if (b2 == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "blockingConnect mGoogleApiClient is null");
            return;
        }
        if (b2.isConnected()) {
            e();
            return;
        }
        if (this.i.isConnecting()) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "GoogleApiClient isConnecting");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.google.android.wearable.app.cn", "com.google.android.gms.wearable.service.WearableService"));
        try {
            this.e.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceSmartWatchService", e.getMessage());
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceSmartWatchService", "started gms service");
        this.i.connect();
        LogUtil.a("0xA0200009", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "GoogleApiClient try connect");
    }

    private void e() {
        LogUtil.c("DEVMGR_SETTING", c(), "Enter connectedSuccess");
        ExtendHandler extendHandler = this.h;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "getConnectedNodes is ", Integer.valueOf(getBTDeviceConnectState()));
        Collection<Node> c = BTDeviceAWService.d(this.e).c();
        if (c == null || c.size() == 0) {
            LogUtil.c("DEVMGR_SETTING", c(), " StateThread no watch in iterator");
            h();
            return;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "StateThread watch names.size() is ", Integer.valueOf(c.size()));
        for (Node node : c) {
            if (TextUtils.isEmpty(this.g)) {
                String a2 = BTDeviceAWService.d(BaseApplication.e()).a(node);
                if (!TextUtils.isEmpty(a2)) {
                    this.g = a2;
                    c(node);
                    BTDeviceAWService.d(this.e).d().put(this.g, this.j);
                    a(2);
                }
            } else if (this.g.equals(node.getId())) {
                c(node);
                a(2);
                return;
            }
        }
        h();
    }

    private void c(Node node) {
        synchronized (this) {
            this.f = node;
        }
    }

    private void h() {
        if (TextUtils.isEmpty(this.g)) {
            return;
        }
        LogUtil.c(c(), "reconnect device node is: ", this.g);
        tsm.c().a(this.g);
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void disconnectBTDevice() {
        LogUtil.c("DEVMGR_SETTING", c(), "start to disconnect.");
        disconnectGMS();
        b();
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public boolean sendBTDeviceData(byte[] bArr) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "sendBTDeviceData");
        BTDeviceAWService.d(this.e).d(this.g, bArr);
        return true;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void onDestroy() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "Enter onDestroy().");
        this.e = null;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public DeviceInfo getDeviceInfo() {
        return this.c;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void btSwitchChangeInfo(int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "Enter btSwitchChangeInfo() with status = ", Integer.valueOf(i));
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void sendBTFilePath(String str) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, c(), "sendBTDeviceAssetData");
        BTDeviceAWService.d(this.e).a(this.g, str);
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setFileCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
        BTDeviceAWService.d(this.e).a(btDeviceResponseCallback);
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void disconnectGMS() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "start to disconnectGMS.");
        b();
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setPathExtendNum(int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "Enter setPathExtendNum ");
        BTDeviceAWService.d(this.e).a(i);
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public int getBTDeviceConnectState() {
        int i;
        synchronized (this) {
            i = this.f13692a;
        }
        return i;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase
    public void setAwAssetCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
        BTDeviceAWService.d(this.e).b(btDeviceResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        DeviceInfo deviceInfo;
        synchronized (this) {
            if (this.d == null || (deviceInfo = this.c) == null) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "reportConnectState error with mBtDeviceStateCallback is null.");
            } else {
                if (i == this.f13692a) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "reportConnectState , status not changed = ", Integer.valueOf(i));
                    return;
                }
                this.f13692a = i;
                if (i != deviceInfo.getDeviceConnectState()) {
                    Node node = this.f;
                    if (node != null) {
                        a(node.getDisplayName());
                    }
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "reportConnectState , awConnectState = ", Integer.valueOf(i));
                    izn c = izn.c();
                    String deviceIdentify = this.c.getDeviceIdentify();
                    if (i == 4) {
                        c.a(deviceIdentify, System.currentTimeMillis());
                        c.c(deviceIdentify, AgdConstant.INSTALL_TYPE_DEFAULT);
                        c.a(deviceIdentify, 1003001);
                    }
                    this.d.onDeviceConnectionStateChanged(this.c, i, c.e(deviceIdentify));
                }
            }
        }
    }

    private void a(String str) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "setName , AndroidWear name = ", str);
        this.c.setDeviceName(str);
    }

    private void b() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, c(), "connectionLost() called");
        this.h.removeTasksAndMessages();
        a(3);
    }
}
