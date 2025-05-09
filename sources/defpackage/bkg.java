package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.strategy.CommandTimerTask;
import com.huawei.devicesdk.strategy.ConnectStrategy;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LogUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class bkg extends ConnectStrategy {

    /* renamed from: a, reason: collision with root package name */
    private int f420a;
    private Timer b = new Timer("ConnectStrategyScale");
    private String c;
    private HandshakeCommandBase d;
    private TimerTask e;

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void connect(ConnectMode connectMode, DeviceInfo deviceInfo) {
        super.connect(connectMode, deviceInfo);
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void getConnectStatus(String str) {
        LogUtil.c("ConnectStrategyScale", "getConnectStatus start");
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void startHandshake(DeviceInfo deviceInfo) {
        LogUtil.c("ConnectStrategyScale", "startHandshake enter");
        if (deviceInfo == null) {
            LogUtil.a("ConnectStrategyScale", "ConnectStrategySimple startHandshake deviceInfo parameter exception.");
            return;
        }
        biw biwVar = new biw();
        biwVar.f(20);
        biwVar.j(1024);
        biwVar.g(10);
        bjx.a().a(deviceInfo.getDeviceMac(), biwVar);
        bhm bhmVar = new bhm();
        this.d = bhmVar;
        if (bhmVar instanceof HandshakeSimpleCommandBase) {
            bir prepare = bhmVar.prepare(deviceInfo);
            this.c = bgn.a(prepare.b());
            a(deviceInfo, prepare, false);
        }
        this.f420a = 40;
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac()) || biuVar == null) {
            LogUtil.a("ConnectStrategyScale", "onDataReceived device error");
            return;
        }
        if (!this.c.equals(biuVar.b())) {
            LogUtil.a("ConnectStrategyScale", "on data received is not equal deviceId");
            return;
        }
        a();
        LogUtil.c("ConnectStrategyScale", "on data received. error code: ", Integer.valueOf(i), blt.a(deviceInfo));
        if (i != 0) {
            c(deviceInfo, false);
            return;
        }
        HandshakeCommandBase handshakeCommandBase = this.d;
        if (handshakeCommandBase == null) {
            LogUtil.a("ConnectStrategyScale", "mCurrentCommand is null");
            return;
        }
        this.mConnectStatusMsg = handshakeCommandBase.processReceivedData(deviceInfo, biuVar);
        int b = this.mConnectStatusMsg.b();
        LogUtil.c("ConnectStrategyScale", this.d.getClass().getName(), " data receive finish. status: ", Integer.valueOf(b), blt.a(deviceInfo));
        if (b != 12) {
            c(deviceInfo, false);
            return;
        }
        HandshakeCommandBase handshakeCommandBase2 = this.d;
        if (handshakeCommandBase2 instanceof HandshakeSimpleCommandBase) {
            this.f420a = 41;
            bir end = ((HandshakeSimpleCommandBase) handshakeCommandBase2).end(deviceInfo);
            this.c = bgn.a(end.b());
            a(deviceInfo, end, false);
            return;
        }
        d(deviceInfo);
    }

    private void d(DeviceInfo deviceInfo) {
        HandshakeCommandBase nextCommand = this.d.getNextCommand();
        this.d = nextCommand;
        if (nextCommand == null) {
            LogUtil.c("ConnectStrategyScale", "handshake success.", blt.a(deviceInfo));
            c(deviceInfo, true);
            return;
        }
        if (nextCommand instanceof HandshakeSimpleCommandBase) {
            LogUtil.c("ConnectStrategyScale", "mCurrentCommand instanceof HandshakeSimpleCommandBase");
            this.f420a = 40;
            bir prepare = ((HandshakeSimpleCommandBase) this.d).prepare(deviceInfo);
            this.c = bgn.a(prepare.b());
            a(deviceInfo, prepare, false);
            return;
        }
        LogUtil.c("ConnectStrategyScale", "mCurrentCommand is not HandshakeSimpleCommandBase");
        bir deviceCommand = this.d.getDeviceCommand(deviceInfo);
        this.c = bgn.a(deviceCommand.b());
        a(deviceInfo, deviceCommand, true);
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void onChannelEnable(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("ConnectStrategyScale", "onDataReceived device error");
        }
        LogUtil.c("ConnectStrategyScale", "onChannelEnable errorCode ", Integer.valueOf(i), blt.a(deviceInfo));
        if (i != 0) {
            c(deviceInfo, false);
            return;
        }
        if (this.d == null) {
            LogUtil.a("ConnectStrategyScale", "mCurrentCommand is null");
            return;
        }
        LogUtil.c("ConnectStrategyScale", "mCurrentChannelFlag: ", Integer.valueOf(this.f420a));
        if (this.f420a == 40) {
            bir deviceCommand = this.d.getDeviceCommand(deviceInfo);
            this.c = bgn.a(deviceCommand.b());
            a(deviceInfo, deviceCommand, true);
            return;
        }
        d(deviceInfo);
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void disconnect(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("ConnectStrategyScale", "disconnect error. device info is invalid.");
        } else {
            super.disconnect(deviceInfo);
        }
    }

    private void a(DeviceInfo deviceInfo, bir birVar, boolean z) {
        LogUtil.c("ConnectStrategyScale", "sendDeviceData isNeedCheckOverTime: ", Boolean.valueOf(z));
        if (!d(birVar)) {
            LogUtil.e("ConnectStrategyScale", "construct device command error.", blt.a(deviceInfo));
            c(deviceInfo, false);
            return;
        }
        bjz.b().a(deviceInfo, birVar);
        if (z) {
            c cVar = new c(birVar.b() + Constants.LINK + blt.a(deviceInfo.getDeviceMac()), deviceInfo);
            this.e = cVar;
            this.b.schedule(cVar, PreConnectManager.CONNECT_INTERNAL, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo, boolean z) {
        int d;
        LogUtil.c("ConnectStrategyScale", "callbackHandshakeStatus isHandshakeSuccess: ", Boolean.valueOf(z));
        this.d = null;
        if (this.mHandshakeStatusReporter == null) {
            LogUtil.a("ConnectStrategyScale", "handshake status callback is null ", blt.a(deviceInfo));
            return;
        }
        if (z) {
            this.mHandshakeStatusReporter.onHandshakeFinish(deviceInfo);
            return;
        }
        if (this.mConnectStatusMsg == null) {
            LogUtil.a("ConnectStrategyScale", "callbackHandshakeStatus: mConnectStatusMsg is null.");
            d = 999999;
        } else {
            d = this.mConnectStatusMsg.d();
        }
        this.mHandshakeStatusReporter.onHandshakeFailed(deviceInfo, d);
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void unPairDevice(DeviceInfo deviceInfo, ConnectMode connectMode, boolean z) {
        if (deviceInfo == null) {
            LogUtil.a("ConnectStrategyScale", "ConnectStrategySimple unPairDevice device null");
        } else {
            bib.a().e(deviceInfo, connectMode);
        }
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void destroy(String str) {
        LogUtil.c("ConnectStrategyScale", "destroy enter");
        a();
        bgu.b(str);
    }

    private boolean d(bir birVar) {
        return (birVar == null || birVar.b() == null || birVar.j() == null) ? false : true;
    }

    private void a() {
        if (this.e != null) {
            LogUtil.c("ConnectStrategyScale", "cancel timeout task");
            this.e.cancel();
            this.e = null;
            return;
        }
        LogUtil.a("ConnectStrategyScale", "mTimerTask is empty");
    }

    class c extends CommandTimerTask {
        private DeviceInfo d;

        @Override // com.huawei.devicesdk.strategy.CommandTimerTask
        public void doTaskAction() {
        }

        c(String str, DeviceInfo deviceInfo) {
            super(str, 0);
            this.d = deviceInfo;
        }

        @Override // com.huawei.devicesdk.strategy.CommandTimerTask
        public void doTimeoutAction() {
            bkg.this.c(this.d, false);
        }
    }
}
