package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.btsportdevice.callback.CallbackBetweenClientAndController;
import com.huawei.btsportdevice.callback.DataLifecycle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.R;
import com.huawei.health.device.PluginDeviceAdapter;
import com.huawei.health.device.api.PluginDeviceApi;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.callback.BroadcastObserver;
import com.huawei.health.ecologydevice.fitness.BroadcastSubscribeCenter;
import com.huawei.health.ecologydevice.fitness.ConnectionController;
import com.huawei.health.ecologydevice.fitness.ScannerController;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.dlb;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class cxz extends AbstractFitnessClient implements BroadcastObserver {

    /* renamed from: a, reason: collision with root package name */
    private BluetoothManager f11539a;
    private ConnectionController b;
    private RopeStateMonitor c;
    private BluetoothAdapter d;
    private String g;
    private String h;
    private int j;
    private c o;
    private ScannerController p;
    private String q;
    private cxw r;
    private final Object m = new Object();
    private final Object l = new Object();
    private String f = "";
    private ExtendHandler i = null;
    private volatile boolean k = false;
    private SkippingTargetMode s = new SkippingTargetMode();
    private CallbackBetweenClientAndController e = new CallbackBetweenClientAndController() { // from class: cxz.4
        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void onDisconnect() {
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void onStopByUser() {
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void setBreakBySelf(boolean z) {
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void notifyStateChanged(String str) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = str;
            cxz.this.i.sendMessage(obtain);
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void notifyDataChange(int i, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = 6;
            obtain.arg1 = i;
            obtain.setData(bundle);
            cxz.this.i.sendMessage(obtain);
        }
    };
    private czc n = new czc();

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void cancelPairing() {
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void disconnect(boolean z) {
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public boolean reConnect() {
        return false;
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void sendByteToEquip(byte[] bArr) {
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setHasExperiencedStateOfStartForFtmp(boolean z) {
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setHeartRateFromWearable(int i) {
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setRealStartWorkout() {
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void stopThreadsInCsafeController() {
    }

    public cxz() {
        f();
        o();
        h();
    }

    private void f() {
        if (this.d != null) {
            LogUtil.a("PDROPE_RopeFitnessClient", "Init already.");
            return;
        }
        if (this.f11539a == null && (BaseApplication.getContext().getSystemService("bluetooth") instanceof BluetoothManager)) {
            this.f11539a = (BluetoothManager) BaseApplication.getContext().getSystemService("bluetooth");
        }
        BluetoothManager bluetoothManager = this.f11539a;
        if (bluetoothManager == null) {
            LogUtil.b("PDROPE_RopeFitnessClient", "Unable to initAdapter BluetoothManager.");
            return;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.d = adapter;
        if (adapter == null) {
            LogUtil.b("PDROPE_RopeFitnessClient", "Unable to obtain BluetoothAdapter.");
        }
    }

    private void o() {
        if (this.i == null) {
            this.i = HandlerCenter.yt_(new d(), "PDROPE_RopeFitnessClient");
        }
    }

    private void h() {
        BroadcastSubscribeCenter.e().b(Arrays.asList(BroadcastObserver.BLUE_STATE_TYPE), "PDROPE_RopeFitnessClient", this);
        BroadcastSubscribeCenter.e().a();
    }

    public cxz c(String str, MessageOrStateCallback messageOrStateCallback, boolean z, List<Integer> list) {
        synchronized (this.m) {
            LogUtil.a("PDROPE_RopeFitnessClient", "new context: ", str);
            czc czcVar = this.n;
            if (czcVar != null) {
                czcVar.e(str, messageOrStateCallback, list);
            }
            if (z) {
                n();
            }
        }
        return this;
    }

    private void n() {
        PluginDeviceAdapter a2;
        if (this.o == null) {
            this.o = new c();
        }
        PluginDeviceApi pluginDeviceApi = (PluginDeviceApi) Services.c("PluginDevice", PluginDeviceApi.class);
        PluginBaseAdapter adapter = pluginDeviceApi.getAdapter();
        if (adapter instanceof PluginDeviceAdapter) {
            a2 = (PluginDeviceAdapter) adapter;
        } else {
            pluginDeviceApi.init(BaseApplication.getContext());
            pluginDeviceApi.setAdapter(czs.a());
            a2 = czs.a();
        }
        if (a2 == null) {
            LogUtil.h("PDROPE_RopeFitnessClient", "PluginDeviceAdapter is null, DataLifecycle will not work");
        } else {
            a2.registerStatusFromService(this.o);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void connectByMac(boolean z, final String str) {
        synchronized (this) {
            ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeFitnessClient", "in connectByMac:", CommonUtil.l(str));
            if (c(str)) {
                ScannerController scannerController = this.p;
                if (scannerController != null) {
                    scannerController.a();
                }
                cxw cxwVar = new cxw(this.e, this.l);
                this.r = cxwVar;
                ConnectionController connectionController = new ConnectionController(cxwVar, this.l);
                this.b = connectionController;
                connectionController.c(new ConnectionController.ConnectResultCallback() { // from class: cxx
                    @Override // com.huawei.health.ecologydevice.fitness.ConnectionController.ConnectResultCallback
                    public final void onResult(int i) {
                        cxz.this.c(str, i);
                    }
                });
                this.k = false;
                this.j = 5;
                this.r.b(str);
                this.b.a(str);
                this.h = str;
            }
        }
    }

    /* synthetic */ void c(String str, int i) {
        cxw cxwVar;
        LogUtil.a("PDROPE_RopeFitnessClient", "Result code = ", Integer.valueOf(i));
        if (i != 1) {
            if (i == 2) {
                this.i.sendEmptyMessage(2);
                return;
            }
            if (i != 4) {
                switch (i) {
                    case 10:
                        this.j = 6;
                        this.k = false;
                        ConnectionController connectionController = this.b;
                        if (connectionController != null && (cxwVar = this.r) != null) {
                            cxwVar.Rf_(connectionController.Rt_());
                        }
                        this.n.a(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED);
                        q();
                        this.g = str;
                        break;
                    case 12:
                        this.j = 5;
                        this.n.a(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING);
                        this.k = false;
                        break;
                }
            }
        }
        g();
    }

    private boolean c(String str) {
        if (this.d == null || TextUtils.isEmpty(str)) {
            LogUtil.h("PDROPE_RopeFitnessClient", "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        int i = this.j;
        boolean z = i == 5 || i == 6;
        if (!str.equals(this.h) || !z) {
            return true;
        }
        LogUtil.c("PDROPE_RopeFitnessClient", "Trying to use an existing mBluetoothGatt for connection.");
        return false;
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void removeMessageOrStateCallback(String str, boolean z) {
        LogUtil.a("PDROPE_RopeFitnessClient", "releaseResource, isNoneCallbackLeft = ", Boolean.valueOf(a(str)));
        if (z) {
            r();
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void releaseResource() {
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeFitnessClient", "releaseResource closeBluetoothGatt");
        czc czcVar = this.n;
        if (czcVar != null) {
            czcVar.a(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED);
            this.n.c();
        }
        BroadcastSubscribeCenter.e().a(Arrays.asList(BroadcastObserver.BLUE_STATE_TYPE), "PDROPE_RopeFitnessClient");
        r();
        j();
        p();
    }

    private void r() {
        if (this.o != null) {
            this.o = null;
        }
    }

    private boolean a(String str) {
        czc czcVar = this.n;
        if (czcVar == null) {
            return true;
        }
        czcVar.e(str);
        return this.n.e();
    }

    private void j() {
        czc czcVar = this.n;
        if (czcVar != null) {
            czcVar.Se_(TypedValues.Custom.TYPE_REFERENCE, null);
        }
    }

    private void p() {
        k();
        i();
    }

    private void k() {
        RopeStateMonitor ropeStateMonitor = this.c;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
            this.c = null;
        }
    }

    private void i() {
        synchronized (this.m) {
            ScannerController scannerController = this.p;
            if (scannerController != null) {
                scannerController.a();
                this.p.a(null);
            }
            if (!this.k) {
                this.k = true;
                this.n.a(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED);
            }
            ConnectionController connectionController = this.b;
            if (connectionController != null) {
                connectionController.e();
            }
            this.r = null;
            this.h = "";
            this.g = "";
            this.j = 8;
            m();
        }
    }

    private void m() {
        if (this.i != null) {
            LogUtil.a("PDROPE_RopeFitnessClient", "msgHandler will removeCallbacksAndMessages");
            this.i.removeTasksAndMessages();
            this.i.quit(false);
        }
    }

    public void b(SkippingTargetMode skippingTargetMode) {
        this.s = skippingTargetMode;
    }

    public void d(String str) {
        if (this.j != 6) {
            return;
        }
        cxw cxwVar = this.r;
        if (cxwVar == null) {
            LogUtil.a("PDROPE_RopeFitnessClient", "mFitnessController is null, device information has not been obtained yet.");
            return;
        }
        DeviceInformation d2 = cxwVar.d();
        if (d2 != null && this.n != null) {
            LogUtil.a("PDROPE_RopeFitnessClient", "Send device information to newly registered callback.");
            Bundle bundle = new Bundle();
            bundle.putInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE", d2.getFitnessDataType());
            bundle.putSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA", d2.getFitnessHashMap());
            this.n.Sf_(str, 902, bundle);
            return;
        }
        b();
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setFitnessMachineControl(int i, int[] iArr) {
        if (l()) {
            LogUtil.a("PDROPE_RopeFitnessClient", "setFitnessMachineControl For FTMP");
            this.r.a(i, iArr);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setFitnessMachineControl(int i, int i2, int[] iArr) {
        if (l()) {
            LogUtil.a("PDROPE_RopeFitnessClient", "setFitnessMachineControl For FTMP");
            this.r.c(i, i2, iArr);
        }
    }

    public void b(int i, int i2, int[] iArr) {
        if (l()) {
            LogUtil.a("PDROPE_RopeFitnessClient", "setFitnessRopeConfig For Rope");
            this.r.d(i, i2, iArr);
        }
    }

    private boolean l() {
        return this.r != null && this.j == 6;
    }

    public boolean b() {
        if (!l()) {
            return false;
        }
        LogUtil.a("PDROPE_RopeFitnessClient", "getDeviceInfo");
        return this.r.c(4, 0, new int[]{0});
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public String getCurrentMacAddress() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        synchronized (this.m) {
            nrh.d(HuaweiHealth.a(), HuaweiHealth.a().getResources().getString(R.string.IDS_device_switch_device_connect_fail));
            i();
            LogUtil.a("PDROPE_RopeFitnessClient", "connectionFailed");
        }
    }

    public int d() {
        return this.j;
    }

    public String c() {
        return this.g;
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setDeviceType(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
        } else {
            LogUtil.b("PDROPE_RopeFitnessClient", "setDeviceType deviceType is null");
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void connectByName(boolean z, String str) {
        LogUtil.a("PDROPE_RopeFitnessClient", "in connectByName");
    }

    public String e() {
        return this.q;
    }

    public void e(String str) {
        this.q = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.r == null || TextUtils.isEmpty(this.q)) {
            LogUtil.a("PDROPE_RopeFitnessClient", "mFitnessController is null, device information has not been obtained yet.");
            return;
        }
        DeviceInformation d2 = this.r.d();
        if (d2 == null || TextUtils.isEmpty(d2.getSerialNumber())) {
            return;
        }
        new dlb.a().c(d2.getModelString()).a(d2.getHardwareVersion()).b(d2.getManufacturerString()).f(d2.getSoftwareVersion()).d(d2.getSerialNumber()).e(this.q).c().a();
    }

    private void q() {
        dcz d2 = ResourceManager.e().d(this.q);
        if (d2 == null) {
            return;
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("kind_name", d2.l() != null ? d2.l().name() : "");
        String e = dij.e(this.q);
        hashMap.put("smartProductId", e);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.a(this.q));
        hashMap.put("data_type", 283);
        hashMap.put("prodId", e);
        hashMap.put("macAfterSha256", dis.b(dis.a(this.h)));
        hashMap.put("macAfterSha256_2", dis.b(this.h));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_GYM_EQUIP_CONNECT_RESULT_2170003.value(), hashMap, 0);
    }

    @Override // com.huawei.health.ecologydevice.callback.BroadcastObserver
    public void onNotify(int i, BluetoothDevice bluetoothDevice) {
        if (i == 1) {
            LogUtil.a("PDROPE_RopeFitnessClient", "BluetoothAdapter.BLUETOOTH_SWITCH_ON");
        } else {
            if (i != 2) {
                return;
            }
            LogUtil.a("PDROPE_RopeFitnessClient", "BluetoothAdapter.BLUETOOTH_SWITCH_OFF");
            this.i.sendEmptyMessage(4);
        }
    }

    class c implements DataLifecycle {
        private c() {
        }

        @Override // com.huawei.btsportdevice.callback.DataLifecycle
        public void init() {
            LogUtil.a("PDROPE_RopeFitnessClient", "RopeLifecycle.init()");
            cxz.this.v();
        }

        @Override // com.huawei.btsportdevice.callback.DataLifecycle
        public void onPause() {
            LogUtil.a("PDROPE_RopeFitnessClient", "RopeLifecycle.onPause()");
            cxz.this.setFitnessMachineControl(4, null);
        }

        @Override // com.huawei.btsportdevice.callback.DataLifecycle
        public void onResume() {
            LogUtil.a("PDROPE_RopeFitnessClient", "RopeLifecycle.onResume()");
            cxz.this.setFitnessMachineControl(5, null);
        }

        @Override // com.huawei.btsportdevice.callback.DataLifecycle
        public void onStart() {
            LogUtil.a("PDROPE_RopeFitnessClient", "RopeLifecycle.onStart()");
            if (cxz.this.s == null || cxz.this.r == null || cxz.this.j != 6) {
                return;
            }
            int startMode = cxz.this.s.getStartMode();
            int goal = cxz.this.s.getGoal();
            if ((startMode != 5 && startMode != 0 && startMode != 11) || goal > 0) {
                if (!cxz.this.c(startMode, goal) || cxz.this.n == null) {
                    return;
                }
                cxz.this.n.Se_(901, null);
                cxz.this.n.Se_(1000, null);
                return;
            }
            LogUtil.a("PDROPE_RopeFitnessClient", "Currently in ", "startMode = ", Integer.valueOf(startMode), ", you need to set the target first.");
        }

        @Override // com.huawei.btsportdevice.callback.DataLifecycle
        public void onDestroy() {
            LogUtil.a("PDROPE_RopeFitnessClient", "RopeLifecycle.onDestroy()");
            cxz.this.setFitnessMachineControl(6, null);
            if (cxz.this.n != null) {
                cxz.this.n.Se_(1001, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i, int i2) {
        if (i == 0) {
            return this.r.c(3, 2, new int[]{i2});
        }
        if (i == 5) {
            return this.r.c(3, 3, new int[]{i2});
        }
        switch (i) {
            case 7:
                return this.r.c(3, 9, null);
            case 8:
                return t();
            case 9:
                return this.r.c(3, 11, new int[]{65535});
            case 10:
                return this.r.c(3, 11, new int[]{i2});
            case 11:
                return this.r.c(3, 12, new int[]{i2});
            default:
                return this.r.c(3, 1, null);
        }
    }

    private boolean t() {
        int[] iArr = new int[4];
        IntermitentJumpData intermitentJumpData = this.s.getIntermitentJumpData();
        if (intermitentJumpData == null) {
            return false;
        }
        iArr[0] = intermitentJumpData.getIntermittentJumpExerciseTime();
        iArr[1] = intermitentJumpData.getIntermittentJumpExerciseNum();
        iArr[2] = intermitentJumpData.getIntermittentJumpBreakTime();
        iArr[3] = intermitentJumpData.getIntermittentJumpGroups();
        return this.r.c(3, 10, iArr);
    }

    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeFitnessClient", "DISCONNECT_WHEN_TIMEOUT");
                    cxz.this.g();
                    break;
                case 2:
                    cxz.this.s();
                    break;
                case 3:
                    cxz.this.i.removeTasksAndMessages();
                    if (cxz.this.b != null) {
                        cxz.this.b.e(1);
                        cxz.this.b.a(cxz.this.h);
                        break;
                    }
                    break;
                case 4:
                    LogUtil.h("PDROPE_RopeFitnessClient", "BLUETOOTH_SWITCH_OFF");
                    cxz.this.g();
                    break;
                case 5:
                    if (cxz.this.n != null) {
                        cxz.this.n.a(String.valueOf(message.obj));
                        break;
                    }
                    break;
                case 6:
                    if (cxz.this.n != null) {
                        cxz.this.n.Se_(message.arg1, message.getData());
                        break;
                    }
                    break;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("PDROPE_RopeFitnessClient", "startScannerDevice");
        ScannerController scannerController = new ScannerController(this.h);
        this.p = scannerController;
        scannerController.a(new ScannerController.ScanResultCallback() { // from class: cxv
            @Override // com.huawei.health.ecologydevice.fitness.ScannerController.ScanResultCallback
            public final void onResult(int i, HealthDevice healthDevice) {
                cxz.this.c(i, healthDevice);
            }
        });
        this.p.c();
    }

    /* synthetic */ void c(int i, HealthDevice healthDevice) {
        ScannerController scannerController = this.p;
        if (scannerController != null) {
            scannerController.a();
        }
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeFitnessClient", "ScannerController code = ", Integer.valueOf(i));
        if (i == 1 && this.b != null && healthDevice != null) {
            this.i.sendEmptyMessage(3);
        } else if (i == 2 || i == 3) {
            g();
        } else {
            LogUtil.c("PDROPE_RopeFitnessClient", "other branch");
            g();
        }
        this.p = null;
    }

    public DeviceInformation a() {
        cxw cxwVar = this.r;
        return (cxwVar == null || cxwVar.d() == null) ? new DeviceInformation() : this.r.d();
    }
}
