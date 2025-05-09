package defpackage;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.BtSwitchCallback;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.ConnectStatusInterface;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.strategy.ConnectStrategy;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class bjv implements ConnectStatusInterface, MessageReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private volatile ConnectFilter f413a;
    private final BtSwitchCallback c;
    protected DeviceStatusChangeCallback e;
    private CountDownLatch f;
    private Handler g;
    private bkw h;
    private HandlerThread i;
    private Map<String, String> j;
    private static Map<String, ConnectStrategy> d = new ConcurrentHashMap(16);
    private static Map<String, Boolean> b = new ConcurrentHashMap(16);

    /* JADX INFO: Access modifiers changed from: private */
    public int d(int i) {
        if (i == 32) {
            return 60032;
        }
        return i == 34 ? 70034 : 100000;
    }

    private bjv() {
        this.j = new HashMap(16);
        BtSwitchCallback btSwitchCallback = new BtSwitchCallback() { // from class: bjv.4
            @Override // com.huawei.devicesdk.callback.BtSwitchCallback
            public void onBtSwitchStateCallback(int i) {
                if (i != 1) {
                    if (i == 2) {
                        ReleaseLogUtil.b("DEVMGR_ConnectManage", "BLUETOOTH_STATE_TURNING_OFF!");
                        return;
                    } else {
                        if (i != 3) {
                            if (i != 4) {
                                return;
                            }
                            ReleaseLogUtil.b("DEVMGR_ConnectManage", "BLUETOOTH_STATE_TURNING_ON!");
                            return;
                        }
                        ReleaseLogUtil.b("DEVMGR_ConnectManage", "BLUETOOTH_STATE_ON!");
                        return;
                    }
                }
                ReleaseLogUtil.b("DEVMGR_ConnectManage", "BLUETOOTH_STATE_OFF!");
                for (DeviceInfo deviceInfo : bjx.a().d().values()) {
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    if (deviceConnectState != 1 && deviceConnectState != 2 && deviceConnectState != 13 && deviceConnectState != 14) {
                        switch (deviceConnectState) {
                        }
                    }
                    bjx.a().a(deviceInfo.getDeviceMac(), 3);
                    bjv.this.d(deviceInfo);
                    bjv bjvVar = bjv.this;
                    bjvVar.d(deviceInfo, 3, bjvVar.d(3));
                }
            }
        };
        this.c = btSwitchCallback;
        c();
        bkw d2 = bkw.d();
        this.h = d2;
        d2.b(btSwitchCallback);
        this.h.j();
        h();
    }

    public ConnectFilter b() {
        return this.f413a;
    }

    private void c() {
        bib.a().a(this);
        bjz.b().d(this);
    }

    public static bjv e() {
        return d.b;
    }

    public void e(ConnectMode connectMode, final DeviceInfo deviceInfo, boolean z) {
        if (!bkd.c()) {
            bkd.e(true);
        }
        LogUtil.c("ConnectManage", "isHiChain3FirstTag: ", Boolean.valueOf(bkd.c()));
        if (connectMode == null || deviceInfo == null || !snu.e().getAuthorizationStatus(BaseApplication.e())) {
            LogUtil.a("ConnectManage", "connect device failed, param is error.");
            d(deviceInfo, 4, bln.e(7, 303));
            return;
        }
        if (Build.VERSION.SDK_INT >= 33 && !bma.a(BaseApplication.e(), new String[]{"android.permission.BLUETOOTH_CONNECT"})) {
            LogUtil.a("ConnectManage", "connect device failed, no permission");
            return;
        }
        if (bky.a()) {
            ThreadPoolManager.d().d("cancelBtConfirmDialog", new Runnable() { // from class: bjt
                @Override // java.lang.Runnable
                public final void run() {
                    bjv.b(DeviceInfo.this);
                }
            });
        }
        blz.a("device_is_reconnect", String.valueOf(z));
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "start to connect. connectMode: ", connectMode, blt.b(deviceInfo), " reconnect: ", Boolean.valueOf(z));
        this.j.put(deviceInfo.getDeviceMac(), z ? "2" : "1");
        b(deviceInfo.getDeviceMac(), 200L);
        ConnectStrategy d2 = d(connectMode, deviceInfo.getDeviceMac());
        if (d2 == null) {
            LogUtil.e("ConnectManage", "obtain connect strategy error.", blt.a(deviceInfo));
            d(deviceInfo, 4, bln.e(7, 303));
            return;
        }
        d2.registerHandshakeStatusReporter(this);
        if (this.f413a != null) {
            d2.registerHandshakeFilter(deviceInfo, this.f413a);
        }
        if (!c(deviceInfo, z)) {
            LogUtil.c("ConnectManage", "device is not need to connect.");
            return;
        }
        e(connectMode);
        bmw.e(100056, deviceInfo.getDeviceName(), String.valueOf(z), "");
        d(deviceInfo, 1, 100000);
        d2.connect(connectMode, deviceInfo);
        bjy.d().c(deviceInfo);
        bjy.d().d(deviceInfo.getDeviceMac());
    }

    static /* synthetic */ void b(DeviceInfo deviceInfo) {
        boolean d2 = bks.d(deviceInfo.getDeviceMac());
        LogUtil.c("ConnectManage", "isDeviceInBonded: ", Boolean.valueOf(d2));
        bmw.e(100096, deviceInfo.getDeviceName(), "", Boolean.toString(d2));
        if (d2) {
            return;
        }
        bks.e(deviceInfo.getDeviceMac());
    }

    private void b(String str, long j) {
        Boolean bool = b.get(str);
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "waiting for completion.");
        a();
        a(j);
    }

    private void e(ConnectMode connectMode) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("action", String.valueOf(connectMode.value()));
        iyv.d("89010001", linkedHashMap);
    }

    private boolean c(DeviceInfo deviceInfo, boolean z) {
        synchronized (this) {
            if (!c(deviceInfo)) {
                return false;
            }
            deviceInfo.setUsing(true);
            deviceInfo.setDeviceConnectState(1);
            deviceInfo.setReconnect(z);
            bjx.a().b(deviceInfo);
            bjx.a().c(deviceInfo);
            return true;
        }
    }

    private boolean c(DeviceInfo deviceInfo) {
        String deviceMac = deviceInfo.getDeviceMac();
        DeviceInfo e = bjx.a().e(deviceMac);
        if (e == null) {
            e = bjx.a().j(deviceMac);
        }
        if (e != null) {
            int deviceConnectState = e.getDeviceConnectState();
            if (deviceConnectState == 2) {
                d(deviceInfo, 2, 100000);
                return false;
            }
            if (deviceConnectState == 1 || deviceConnectState == 9 || deviceConnectState == 13 || deviceConnectState == 10 || deviceConnectState == 14 || deviceConnectState == 11) {
                ReleaseLogUtil.b("DEVMGR_ConnectManage", "device is connecting. ", blt.b(deviceInfo));
                return false;
            }
            Boolean bool = b.get(deviceMac);
            if (bool != null && bool.booleanValue()) {
                ReleaseLogUtil.b("DEVMGR_ConnectManage", "device is clearing. ", blt.b(e));
                return false;
            }
            ReleaseLogUtil.b("DEVMGR_ConnectManage", "device need to connect.", blt.b(e));
        }
        return true;
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("ConnectManage", "device info is null");
            return;
        }
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "start to disconnect.", blt.b(deviceInfo));
        bjx.a().e(deviceInfo.getDeviceMac(), false);
        ConnectStrategy a2 = a(deviceInfo.getDeviceMac());
        if (a2 == null) {
            LogUtil.a("ConnectManage", "strategy is null.", blt.a(deviceInfo));
        } else {
            bjy.d().a(deviceInfo);
            a2.disconnect(deviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("ConnectManage", "clearDeviceCache: device is invalid.");
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        LogUtil.c("ConnectManage", "clearDeviceCache. identify: ", blt.a(deviceMac));
        bjx.a().c(deviceMac, false);
        bjx.a().d(deviceMac);
        bjz.b().e(deviceInfo);
        bjz.b().c(deviceMac);
        bje.c().clearDevice(deviceMac);
        ConnectStrategy connectStrategy = d.get(deviceMac);
        if (connectStrategy != null) {
            connectStrategy.destroy(deviceMac);
            d.remove(deviceMac);
        }
        bib.a().e(deviceMac);
    }

    private ConnectStrategy a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return d.get(str);
    }

    private ConnectStrategy d(ConnectMode connectMode, String str) {
        if (TextUtils.isEmpty(str) || connectMode == null) {
            LogUtil.a("ConnectManage", "obtainConnectStrategy param is invalid.");
            return null;
        }
        ConnectStrategy connectStrategy = d.get(str);
        if (connectStrategy != null) {
            return connectStrategy;
        }
        Object d2 = new bkp().d(connectMode);
        if (!(d2 instanceof ConnectStrategy)) {
            return connectStrategy;
        }
        ConnectStrategy connectStrategy2 = (ConnectStrategy) d2;
        d.put(str, connectStrategy2);
        return connectStrategy2;
    }

    @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
    public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null || biuVar == null) {
            LogUtil.a("ConnectManage", "onConnectStatusChanged device error", blt.a(deviceInfo));
            return;
        }
        LogUtil.c("ConnectManage", "onDataReceived", blt.a(deviceInfo));
        ConnectStrategy a2 = a(deviceInfo.getDeviceMac());
        if (a2 != null) {
            a2.onDataReceived(deviceInfo, biuVar, i);
        }
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onPhysicalLayerConnected(DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onPhysicalLayerConnected error: ", blt.a(deviceInfo));
            return;
        }
        bix h = bjx.a().h(deviceInfo.getDeviceMac());
        ConnectStrategy d2 = d(h == null ? null : h.b(), deviceInfo.getDeviceMac());
        if (d2 == null) {
            LogUtil.a("ConnectManage", "onPhysicalLayerConnected strategy is null");
            return;
        }
        bjz.b().a(deviceInfo);
        bjx.a().c(deviceInfo.getDeviceMac(), true);
        g(deviceInfo);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "start handshake.", blt.b(deviceInfo));
        d2.startHandshake(deviceInfo);
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onPhysicalLayerConnectFailed(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onPhysicalLayerConnectFailed error: ", blt.a(deviceInfo));
            return;
        }
        i(deviceInfo);
        b.put(deviceInfo.getDeviceMac(), true);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "onPhysicalLayerConnectFailed: ", blt.b(deviceInfo));
        bjx.a().a(deviceInfo.getDeviceMac(), 4);
        d(deviceInfo);
        d(deviceInfo, i);
        d(deviceInfo, 4, i);
        bjy.d().d(deviceInfo, i);
        b.put(deviceInfo.getDeviceMac(), false);
        j();
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onPhysicalLayerDisconnected(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onPhysicalLayerDisconnected error: ", blt.a(deviceInfo));
            return;
        }
        i(deviceInfo);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "onPhysicalLayerDisconnected: ", blt.b(deviceInfo));
        String deviceMac = deviceInfo.getDeviceMac();
        d(deviceInfo);
        if (a(deviceInfo)) {
            LogUtil.c("ConnectManage", "onPhysicalLayerDisconnected processBondStateKeyMiss");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 3) {
            bjx.a().a(deviceMac, 3);
            d(deviceInfo, 3, i);
        }
        bjy.d().d(deviceInfo, i);
        this.j.remove(deviceInfo.getDeviceMac());
        this.h.a(deviceInfo.getDeviceMac(), false);
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onHandshakeStartOobe(DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onHandshakeStartOobe error: ", blt.a(deviceInfo));
        } else {
            i(deviceInfo);
        }
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onHandshakeFinish(DeviceInfo deviceInfo) {
        String a2;
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onHandshakeFinish error: ", blt.a(deviceInfo));
            return;
        }
        i(deviceInfo);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "onHandshakeFinish: ", blt.b(deviceInfo));
        String deviceMac = deviceInfo.getDeviceMac();
        bjx.a().d(deviceMac, deviceInfo);
        bjx.a().a(deviceMac, 2);
        bjx.a().d(deviceInfo);
        bjx.a().d(deviceMac);
        boolean c2 = blo.c(deviceInfo);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "isSmartWatch: ", Boolean.valueOf(c2));
        if (c2) {
            a2 = blo.d(blq.d(deviceInfo.getDeviceSn()));
        } else {
            a2 = blo.a(deviceMac, blq.d(deviceInfo.getDeviceSn()));
        }
        bmw.e(100051, deviceInfo.getDeviceName(), "", a2 + "&" + this.j.get(deviceInfo.getDeviceMac()));
        bjx.a().c(deviceMac, false);
        deviceInfo.setUdid(blo.a(deviceMac, blq.d(deviceInfo.getDeviceSn())));
        d(deviceInfo, 2, 100000);
        bkc.b();
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onHandshakeFailed(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onHandshakeFailed error: ", blt.a(deviceInfo));
            return;
        }
        i(deviceInfo);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "onHandshakeFailed: ", blt.b(deviceInfo));
        bjx.a().a(deviceInfo.getDeviceMac(), 4);
        d(deviceInfo);
        d(deviceInfo, i);
        d(deviceInfo, 4, i);
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onDeviceBond(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "onDeviceBond error", blt.a(deviceInfo));
        } else {
            LogUtil.c("ConnectManage", "onDeviceBond: ", blt.a(deviceInfo), " status: ", Integer.valueOf(i));
            d(deviceInfo, i, d(i));
        }
    }

    @Override // com.huawei.devicesdk.callback.ConnectStatusInterface
    public void onCapabilityChanged(DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", " deviceInfo or mac is null");
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        bjx.a().d(deviceMac, bjx.a().a(deviceMac));
    }

    private void d(DeviceInfo deviceInfo, int i) {
        bmw.e(100109, deviceInfo.getDeviceName(), String.valueOf(i), "&" + this.j.get(deviceInfo.getDeviceMac()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo, int i, int i2) {
        if (deviceInfo == null) {
            LogUtil.a("ConnectManage", "can not notify device status to client, device info is null");
            return;
        }
        if (this.e == null) {
            LogUtil.a("ConnectManage", "not find callback, device ", blt.a(deviceInfo.getDeviceMac()));
            return;
        }
        b(deviceInfo.getDeviceMac(), i);
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "notifyStatusToClient, [ERROR_CODE]-->", Integer.valueOf(i2));
        LogUtil.c("ConnectManage", "notifyStatusToClient: ", blt.a(deviceInfo));
        bmc.d(deviceInfo.getDeviceMac(), i);
        int i3 = (i == 4 && i2 == 100000) ? 888888 : i2;
        String str = String.valueOf(i) + "&" + this.j.get(deviceInfo.getDeviceMac());
        if (blo.c(deviceInfo)) {
            str = str + "&" + deviceInfo.getPowerSaveMode();
        }
        bmw.e(100050, deviceInfo.getDeviceName(), String.valueOf(i3), str);
        this.e.onConnectStatusChanged(deviceInfo, i, i2);
    }

    private void b(String str, int i) {
        LogUtil.c("ConnectManage", "updateSocketService enter.");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i == 3 || i == 4) {
            LogUtil.c("ConnectManage", "deviceStatus value disconnected");
            bgz.f369a.remove(str);
            bgz.d.remove(str);
            bgz.i.remove(str);
            bgz.c.remove(str);
            bgz.e.remove(str);
            blk.e().d();
        }
    }

    @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
    public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
        if (deviceInfo == null) {
            return;
        }
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "onChannelEnable", " uuid ", str, " errorCode ", Integer.valueOf(i), blt.b(deviceInfo));
        ConnectStrategy a2 = a(deviceInfo.getDeviceMac());
        if (a2 != null) {
            a2.onChannelEnable(deviceInfo, i);
        }
    }

    public void b(ConnectFilter connectFilter) {
        if (connectFilter == null) {
            LogUtil.a("ConnectManage", "registerHandshakeFilter error");
        } else {
            this.f413a = connectFilter;
        }
    }

    public void b(DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.e = deviceStatusChangeCallback;
    }

    public void d() {
        this.e = null;
    }

    public void a(DeviceInfo deviceInfo, boolean z) {
        ConnectStrategy d2;
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("ConnectManage", "unPairDevice error. input param is invalid.");
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        this.h.d(deviceMac);
        bjy.d().a(deviceInfo);
        DeviceInfo j = bjx.a().j(deviceMac);
        DeviceInfo e = bjx.a().e(deviceMac);
        if (j == null && e == null) {
            LogUtil.a("ConnectManage", "unPairDevice local device not exist");
            return;
        }
        bix h = bjx.a().h(deviceMac);
        ConnectMode b2 = h == null ? null : h.b();
        bjx.a().a(deviceInfo);
        ConnectStrategy a2 = a(deviceMac);
        if (a2 != null) {
            a2.unPairDevice(deviceInfo, b2, z);
        } else {
            if (b2 == null || (d2 = d(b2, deviceMac)) == null) {
                return;
            }
            d2.unPairDevice(deviceInfo, b2, z);
        }
    }

    public void e(DeviceInfo deviceInfo, ConnectMode connectMode) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null || connectMode == null) {
            LogUtil.a("ConnectManage", "pair device error. input param is invalid.");
            return;
        }
        ConnectStrategy d2 = d(connectMode, deviceInfo.getDeviceMac());
        if (d2 != null) {
            d2.pairDevice(deviceInfo, connectMode);
        }
    }

    private void a() {
        if (this.f == null) {
            this.f = new CountDownLatch(1);
        }
    }

    private void a(long j) {
        LogUtil.c("ConnectManage", "waitClearingProcessEnd: start");
        if (this.f == null) {
            LogUtil.a("ConnectManage", "mClearingProcessLatch is invalid.");
            return;
        }
        try {
            if (!r0.await(j, TimeUnit.MILLISECONDS)) {
                LogUtil.a("ConnectManage", "await timeout, waitClearingProcessEnd failed");
            }
        } catch (InterruptedException unused) {
            LogUtil.e("ConnectManage", "waitAuthFinish: InterruptedException");
        }
    }

    private void j() {
        CountDownLatch countDownLatch = this.f;
        if (countDownLatch == null) {
            LogUtil.a("ConnectManage", "releaseLock: clearingProcessLatch is null");
            return;
        }
        LogUtil.c("ConnectManage", "releaseLock: countdown");
        countDownLatch.countDown();
        this.f = null;
    }

    private boolean a(DeviceInfo deviceInfo) {
        String deviceMac;
        biw c2;
        if (deviceInfo.getDeviceBtType() != 2 || (c2 = bjx.a().c((deviceMac = deviceInfo.getDeviceMac()))) == null) {
            return false;
        }
        int e = c2.e();
        boolean h = c2.h();
        if (e != 2 || !h) {
            return false;
        }
        ReleaseLogUtil.b("DEVMGR_ConnectManage", "processBondStateKeyMiss");
        bjx.a().a(deviceMac, 3);
        bix h2 = bjx.a().h(deviceInfo.getDeviceMac());
        e(h2 == null ? null : h2.b(), deviceInfo, true);
        return true;
    }

    private void h() {
        HandlerThread handlerThread = new HandlerThread("ConnectManage");
        this.i = handlerThread;
        handlerThread.start();
        this.g = new c(this.i.getLooper());
    }

    private void g(DeviceInfo deviceInfo) {
        Message obtainMessage = this.g.obtainMessage(12);
        obtainMessage.obj = deviceInfo.getDeviceMac().intern();
        this.g.sendMessageDelayed(obtainMessage, 60000L);
    }

    private void i(DeviceInfo deviceInfo) {
        this.g.removeMessages(12, deviceInfo.getDeviceMac().intern());
    }

    static class d {
        private static bjv b = new bjv();
    }

    static class c extends Handler {
        private c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.e("ConnectManage", "handleMessage scanUtil is null");
                return;
            }
            DeviceInfo e = bjx.a().e(String.valueOf(message.obj));
            LogUtil.a("ConnectManage", "Handshake timeout: ", blt.a(e));
            bjv.e().onHandshakeFailed(e, bln.e(5, 306));
        }
    }
}
