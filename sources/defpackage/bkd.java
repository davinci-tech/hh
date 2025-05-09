package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.ConnectStatusInterface;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.hichain.HiChainAuthManager;
import com.huawei.devicesdk.strategy.CommandTimerTask;
import com.huawei.devicesdk.strategy.ConnectStrategy;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class bkd extends ConnectStrategy {
    private static final Object b = new Object();
    private static int c = 0;
    private static boolean d = true;
    private static List<String> e;

    /* renamed from: a, reason: collision with root package name */
    private ConnectFilter f419a;
    private DeviceInfo g;
    private TimerTask i;
    private boolean f = false;
    private HandshakeCommandBase j = null;
    private Timer h = new Timer("ConnectStrategyGeneral");

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void getConnectStatus(String str) {
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void onChannelEnable(DeviceInfo deviceInfo, int i) {
    }

    static {
        ArrayList arrayList = new ArrayList(16);
        e = arrayList;
        c = 4;
        arrayList.add("1A05");
        e.add("1A06");
        e.add("2601");
        e.add("0131");
        e.add("0130");
    }

    public static boolean c() {
        return d;
    }

    public static void e(boolean z) {
        d = z;
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void disconnect(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("ConnectStrategyGeneral", "disconnect error. deivce info is invalid.");
        } else {
            super.disconnect(deviceInfo);
        }
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void unPairDevice(DeviceInfo deviceInfo, ConnectMode connectMode, boolean z) {
        if (z) {
            bib.a().e(deviceInfo, connectMode);
        }
        if (deviceInfo != null && bjk.c(deviceInfo.getDeviceMac())) {
            bjl.e().b(deviceInfo.getDeviceMac());
        }
        if (deviceInfo != null) {
            bmf.a(deviceInfo.getDeviceMac(), "");
        }
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void destroy(String str) {
        a();
        bgm.b(str);
        bjp.d().d(str);
        HiChainAuthManager.d().a(str);
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
        if (deviceInfo == null || biuVar == null) {
            LogUtil.a("ConnectStrategyGeneral", "onDataReceived deviceInfo dataContents parameter exception.");
            return;
        }
        if (i != 0) {
            LogUtil.a("ConnectStrategyGeneral", "this command is not send sucess");
            return;
        }
        LogUtil.c("ConnectStrategyGeneral", "onDataReceived");
        a(biuVar);
        if (this.f) {
            String d2 = blq.d(biuVar.a());
            int length = d2.length();
            int i2 = c;
            if (length < i2) {
                return;
            }
            if (!e.contains(d2.substring(0, i2))) {
                LogUtil.a("ConnectStrategyGeneral", "is not customization handshake command, return.");
                return;
            } else {
                b(deviceInfo, d2);
                return;
            }
        }
        biy a2 = a(deviceInfo, biuVar);
        if (a2 == null || a2.b() != 53) {
            return;
        }
        if (this.mHandshakeStatusReporter != null) {
            this.mHandshakeStatusReporter.onHandshakeStartOobe(deviceInfo);
        }
        this.f = true;
        b(deviceInfo, "service_capability_success");
    }

    private void a(biu biuVar) {
        byte[] a2 = biuVar.a();
        if (a2 == null || a2.length < 2) {
            LogUtil.a("ConnectStrategyGeneral", "handleKeyLost is not valid array: ", a2);
            return;
        }
        if (a2[0] == 1 && a2[1] == 52) {
            LogUtil.c("ConnectStrategyGeneral", "Enter onDataReceivedMethod() key lost 5.1.52:");
            if (bjn.d(a2) == 1) {
                this.mConnectStatusMsg.a(HiHealthStatusCodes.BLUETOOTH_FORBIDDEN_ERROR);
                this.mConnectStatusMsg.c(11);
                e(this.mConnectStatusMsg);
            }
        }
    }

    private boolean b(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("ConnectStrategyGeneral", "device is invalid.");
            return false;
        }
        if (this.f419a == null) {
            LogUtil.a("ConnectStrategyGeneral", "setPreDataCache error ConnectFilter null");
            return false;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        String d2 = bhh.d(i);
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(deviceMac);
        uniteDevice.setDeviceInfo(deviceInfo);
        bjx.a().b(deviceMac, (biz) new Gson().fromJson(this.f419a.preProcess(uniteDevice, d2), biz.class));
        return true;
    }

    private biy a(DeviceInfo deviceInfo, biu biuVar) {
        LogUtil.c("ConnectStrategyGeneral", "processCommonCommandMessage");
        if (!c(biuVar)) {
            if (this.mConnectStatusMsg == null) {
                this.mConnectStatusMsg = d();
            }
            this.mConnectStatusMsg.c(16);
            return this.mConnectStatusMsg;
        }
        a();
        this.mConnectStatusMsg = this.j.processReceivedData(deviceInfo, biuVar);
        int b2 = this.mConnectStatusMsg.b();
        ReleaseLogUtil.b("DEVMGR_ConnectStrategyGeneral", this.j.getTag(), " data receive finish. status: ", Integer.valueOf(b2), blt.b(deviceInfo));
        if (b2 == 3) {
            if (deviceInfo.isReconnect()) {
                if (Boolean.parseBoolean(blz.d("isUserConnect", ""))) {
                    LogUtil.c("ConnectStrategyGeneral", "updateResult:", Boolean.valueOf(b(deviceInfo, b2)));
                    blz.c("isUserConnect");
                } else {
                    LogUtil.c("ConnectStrategyGeneral", "can not reconnect device in double phone situation.", blt.a(deviceInfo));
                    bjy.d().a(deviceInfo);
                    this.mConnectStatusMsg.c(11);
                    e(this.mConnectStatusMsg);
                    return this.mConnectStatusMsg;
                }
            } else {
                boolean b3 = b(deviceInfo, b2);
                biz i = bjx.a().i(deviceInfo.getDeviceMac());
                if (!b3 || i == null || !i.c()) {
                    this.mConnectStatusMsg.c(11);
                    e(this.mConnectStatusMsg);
                    LogUtil.e("ConnectStrategyGeneral", "processCommonCommandMessage: pre device cache error.");
                    return this.mConnectStatusMsg;
                }
            }
        }
        if (b2 == 15) {
            b(deviceInfo, b2);
        }
        return e(this.mConnectStatusMsg, deviceInfo, b2);
    }

    private biy e(biy biyVar, DeviceInfo deviceInfo, int i) {
        if (i == 12 || i == 15 || i == 3) {
            HandshakeCommandBase nextCommand = this.j.getNextCommand();
            this.j = nextCommand;
            e(nextCommand);
            return biyVar;
        }
        if (i == 17) {
            return biyVar;
        }
        if (i == 56) {
            LogUtil.a("ConnectStrategyGeneral", "onDataReceived: connectStatus keyMiss");
            return biyVar;
        }
        if (i != 53) {
            e(biyVar);
            LogUtil.e("ConnectStrategyGeneral", "onDataReceived: get next command fail");
        }
        if (i == 55) {
            disconnect(deviceInfo);
            deviceInfo.setUsing(false);
            deviceInfo.setDeviceConnectState(4);
            bjx.a().c(deviceInfo);
        }
        return biyVar;
    }

    private boolean c(biu biuVar) {
        HandshakeCommandBase handshakeCommandBase = this.j;
        if (handshakeCommandBase instanceof HandshakeGeneralCommandBase) {
            String tag = ((HandshakeGeneralCommandBase) handshakeCommandBase).getTag();
            byte[] a2 = biuVar.a();
            if (a2 == null || a2.length < 2) {
                LogUtil.c("ConnectStrategyGeneral", "isValidCommand srcArray: ", a2);
            } else {
                byte[] bArr = new byte[2];
                System.arraycopy(a2, 0, bArr, 0, 2);
                if (!TextUtils.isEmpty(tag) && tag.equalsIgnoreCase(blq.d(bArr))) {
                    LogUtil.c("ConnectStrategyGeneral", "isValidCommand dstArray = ", bArr, "tag = ", tag);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void registerHandshakeFilter(DeviceInfo deviceInfo, ConnectFilter connectFilter) {
        super.registerHandshakeFilter(deviceInfo, connectFilter);
        this.f419a = connectFilter;
    }

    private bir c(DeviceInfo deviceInfo) {
        bir birVar = new bir();
        if (deviceInfo == null) {
            LogUtil.a("ConnectStrategyGeneral", "device info is empty when buildDeviceCommand");
            return birVar;
        }
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        bir.a aVar = new bir.a();
        aVar.a(3);
        return aVar.b(birVar);
    }

    private void b(DeviceInfo deviceInfo, String str) {
        if (this.f419a == null) {
            LogUtil.a("ConnectStrategyGeneral", "mConnectFilter is null.");
            ConnectFilter b2 = bjv.e().b();
            this.f419a = b2;
            if (b2 == null) {
                LogUtil.c("ConnectStrategyGeneral", "no filter, so connect success.");
                this.mConnectStatusMsg.c(10);
                a(this.mConnectStatusMsg);
                return;
            }
        }
        bir c2 = c(deviceInfo);
        bir birVar = new bir();
        UniteDevice a2 = a(deviceInfo);
        c(deviceInfo, c2, birVar, a2, this.f419a.onFilter(a2, str, birVar));
    }

    private void c(DeviceInfo deviceInfo, bir birVar, bir birVar2, UniteDevice uniteDevice, int i) {
        if (i == 54) {
            LogUtil.e("ConnectStrategyGeneral", "reply is not current command, abandon this.");
            return;
        }
        if (i == 50) {
            b(deviceInfo, birVar, birVar2, uniteDevice);
            return;
        }
        if (i == 51) {
            this.f = false;
            this.mConnectStatusMsg.c(51);
            this.mConnectStatusMsg.a(bln.e(5, i));
            e(this.mConnectStatusMsg);
            return;
        }
        if (i == 57 || i == 58) {
            this.f = false;
            this.mConnectStatusMsg.c(i);
            this.mConnectStatusMsg.a(bln.e(5, i));
            e(this.mConnectStatusMsg);
            return;
        }
        if (i == 52) {
            bjx.a().b(uniteDevice);
            ConnectStatusInterface connectStatusInterface = this.mHandshakeStatusReporter;
            if (connectStatusInterface != null) {
                connectStatusInterface.onCapabilityChanged(deviceInfo);
            } else {
                LogUtil.a("ConnectStrategyGeneral", "onCapabilityChanged invoke exception.");
            }
            a(this.mConnectStatusMsg);
        }
    }

    private void b(DeviceInfo deviceInfo, bir birVar, bir birVar2, UniteDevice uniteDevice) {
        if (birVar2.e() != null && birVar2.e().length > 0) {
            bjx.a().b(uniteDevice);
            ConnectStatusInterface connectStatusInterface = this.mHandshakeStatusReporter;
            if (connectStatusInterface != null) {
                connectStatusInterface.onCapabilityChanged(deviceInfo);
            } else {
                LogUtil.a("ConnectStrategyGeneral", "onCapabilityChanged invoke exception.");
            }
            birVar.e(birVar2.e());
            bjz.b().a(this.g, birVar);
            return;
        }
        LogUtil.a("ConnectStrategyGeneral", "SendCommandManage getCommand is null ");
    }

    private UniteDevice a(DeviceInfo deviceInfo) {
        UniteDevice uniteDevice = new UniteDevice();
        if (deviceInfo != null) {
            uniteDevice.setIdentify(deviceInfo.getDeviceMac());
            uniteDevice.setCapability(bjx.a().a(deviceInfo.getDeviceMac()));
            uniteDevice.setDeviceInfo(deviceInfo);
        }
        return uniteDevice;
    }

    private void a(biy biyVar) {
        this.f = false;
        biyVar.c(10);
        e(biyVar);
    }

    private void a() {
        synchronized (b) {
            TimerTask timerTask = this.i;
            if (timerTask != null) {
                timerTask.cancel();
                this.i = null;
            } else {
                LogUtil.a("ConnectStrategyGeneral", "mTimerTask is empty:", this);
            }
        }
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void startHandshake(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("ConnectStrategyGeneral", "device info is empty.");
            return;
        }
        this.j = new bhg(deviceInfo);
        this.g = deviceInfo;
        bmw.e(100089, bmh.b(deviceInfo.getDeviceName()), "", "");
        e(this.j);
    }

    private void e(HandshakeCommandBase handshakeCommandBase) {
        if (handshakeCommandBase == null) {
            LogUtil.c("ConnectStrategyGeneral", "hand shake successed.");
            this.mConnectStatusMsg.c(10);
            e(this.mConnectStatusMsg);
            return;
        }
        LogUtil.c("ConnectStrategyGeneral", "sendDeviceData");
        bir deviceCommand = handshakeCommandBase.getDeviceCommand(this.g);
        if (deviceCommand == null || deviceCommand.e() == null) {
            LogUtil.e("ConnectStrategyGeneral", "construct command message error.");
            this.mConnectStatusMsg.c(11);
            e(this.mConnectStatusMsg);
            this.j = null;
            return;
        }
        deviceCommand.e(b(deviceCommand.e()));
        if (bjx.a().e(this.g.getDeviceMac()) == null) {
            LogUtil.a("ConnectStrategyGeneral", "sendDeviceData deviceInfo is removed");
        } else if (deviceCommand.f() > 0) {
            b(deviceCommand);
        } else {
            c(deviceCommand);
            bjz.b().a(this.g, deviceCommand);
        }
    }

    private int b(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return 0;
        }
        return (bArr[0] * 100) + bArr[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(biy biyVar) {
        if (biyVar == null) {
            LogUtil.e("ConnectStrategyGeneral", "callbackHandshakeStatus: connectStatusMsg is null.");
            this.mHandshakeStatusReporter.onHandshakeFailed(this.g, 999999);
        } else if (this.mHandshakeStatusReporter != null) {
            if (biyVar.b() == 10) {
                this.mHandshakeStatusReporter.onHandshakeFinish(this.g);
            } else {
                this.mHandshakeStatusReporter.onHandshakeFailed(this.g, biyVar.d());
            }
        }
    }

    private void b(bir birVar) {
        int f = birVar.f();
        bjz.b().a(this.g, birVar);
        try {
            a aVar = new a(birVar.b() + Constants.LINK + blt.a(this.g.getDeviceMac()), birVar, f, birVar.a());
            this.i = aVar;
            long j = 10000;
            this.h.schedule(aVar, j, j);
        } catch (IllegalStateException e2) {
            LogUtil.e("ConnectStrategyGeneral", "sendDeviceDataByAck() IllegalStateException:", e2.getMessage());
        }
    }

    private void c(bir birVar) {
        String tag = ((HandshakeGeneralCommandBase) this.j).getTag();
        boolean z = tag.equalsIgnoreCase("0128") && d;
        if (this.g.getDeviceBtType() == 2) {
            if (tag.equalsIgnoreCase("010E")) {
                e(birVar);
            } else if (z) {
                LogUtil.c("ConnectStrategyGeneral", "sendDeviceDataNeedWait-isHiChain3FirstTag: ", Boolean.valueOf(d));
                d = false;
                e(birVar);
            }
        }
        LogUtil.a("ConnectStrategyGeneral", "mIsHiChain3FirstTag:", Boolean.valueOf(d));
    }

    private void e(bir birVar) {
        synchronized (b) {
            a aVar = new a(birVar.b() + Constants.LINK + blt.a(this.g.getDeviceMac()), birVar, birVar.f(), birVar.a());
            this.i = aVar;
            long j = (long) 30000;
            this.h.schedule(aVar, j, j);
        }
    }

    class a extends CommandTimerTask {
        private int b;
        private bir e;

        a(String str, bir birVar, int i, int i2) {
            super(str, i);
            this.e = birVar;
            this.b = i2;
        }

        @Override // com.huawei.devicesdk.strategy.CommandTimerTask
        public void doTaskAction() {
            LogUtil.c("ConnectStrategyGeneral", "doTaskAction:", bkd.this);
            bir birVar = this.e;
            if (birVar == null) {
                LogUtil.a("ConnectStrategyGeneral", "mCommandMessage is null.");
                return;
            }
            if ("0101".equals(bly.e(birVar.e()))) {
                if (bkd.this.g != null) {
                    biw c = bjx.a().c(bkd.this.g.getDeviceMac());
                    LogUtil.c("ConnectStrategyGeneral", "doTaskAction retry linkNegotiationCommand:", c);
                    if (c != null) {
                        cancel();
                        return;
                    }
                } else {
                    LogUtil.a("ConnectStrategyGeneral", "mDeviceInfo is null.");
                    return;
                }
            }
            bjz.b().a(bkd.this.g, this.e);
        }

        @Override // com.huawei.devicesdk.strategy.CommandTimerTask
        public void doTimeoutAction() {
            LogUtil.c("ConnectStrategyGeneral", "doTimeoutAction");
            if (bkd.this.mConnectStatusMsg == null) {
                bkd bkdVar = bkd.this;
                bkdVar.mConnectStatusMsg = bkdVar.d();
            }
            bkd.this.mConnectStatusMsg.c(11);
            bkd.this.mConnectStatusMsg.a(bln.e(8, this.b));
            bkd bkdVar2 = bkd.this;
            bkdVar2.e(bkdVar2.mConnectStatusMsg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public biy d() {
        biy biyVar = new biy();
        HandshakeCommandBase handshakeCommandBase = this.j;
        if (handshakeCommandBase != null) {
            try {
                biyVar.a(Integer.parseInt(handshakeCommandBase.getTag()));
            } catch (NumberFormatException unused) {
                LogUtil.e("ConnectStrategyGeneral", "NumberFormatException exception");
            }
        }
        return biyVar;
    }
}
