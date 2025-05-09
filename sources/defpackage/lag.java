package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.btsportdevice.callback.CallbackBetweenClientAndController;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.ecologydevice.callback.BroadcastObserver;
import com.huawei.health.ecologydevice.fitness.BroadcastSubscribeCenter;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineStatus;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import defpackage.lag;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class lag extends AbstractFitnessClient implements BroadcastObserver {
    private static final Object b = new Object();
    private BluetoothGatt c;
    BluetoothAdapter d;
    BluetoothDevice e;
    private int f;
    private BluetoothManager g;
    private String i;
    private lak k;
    private lal l;
    private int q;
    private boolean t;

    /* renamed from: a, reason: collision with root package name */
    String f14721a = "";
    private boolean r = false;
    private String n = "";
    private ExtendHandler m = null;
    private boolean s = false;
    private int ac = 0;
    private boolean z = false;
    private boolean x = false;
    private String j = "";
    private volatile boolean y = false;
    private volatile boolean v = false;
    private boolean p = false;
    private boolean w = false;
    private boolean u = false;
    private boolean ad = false;
    private final BluetoothGattCallback o = new BluetoothGattCallback() { // from class: lag.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            lag.this.bUk_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            lag.this.bUe_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a("Track_IDEQ_FitnessClient", "onCharacteristicRead");
            if (i == 0) {
                lag.this.r();
                lag.this.l.bUZ_(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            lag.this.r();
            lag.this.l.bUY_(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            lag.this.l.bVa_(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LogUtil.c("Track_IDEQ_FitnessClient", "onDescriptorRead");
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LogUtil.c("Track_IDEQ_FitnessClient", "onDescriptorWrite");
            lag.this.l.bUX_(bluetoothGatt, bluetoothGattDescriptor, i);
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            LogUtil.c("Track_IDEQ_FitnessClient", "onReadRemoteRssi");
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            LogUtil.c("Track_IDEQ_FitnessClient", "onReliableWriteCompleted");
            super.onReliableWriteCompleted(bluetoothGatt, i);
        }
    };
    private ScanCallback aa = new ScanCallback() { // from class: lag.1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            LogUtil.c("Track_IDEQ_FitnessClient", "onLeScan");
            if (scanResult != null) {
                try {
                    if (scanResult.getDevice() == null) {
                        return;
                    }
                    boolean z = false;
                    LogUtil.a("Track_IDEQ_FitnessClient", "get device in scanning: ", scanResult.getDevice().getName());
                    boolean z2 = !TextUtils.isEmpty(lag.this.j) && lag.this.j.equals(scanResult.getDevice().getName());
                    if (!TextUtils.isEmpty(lag.this.f14721a) && lag.this.f14721a.equals(scanResult.getDevice().getAddress())) {
                        z = true;
                    }
                    if (z2 || z) {
                        lag lagVar = lag.this;
                        lagVar.ad = lagVar.bUc_(scanResult);
                        lag.this.bUl_(scanResult.getDevice());
                    }
                } catch (SecurityException e) {
                    LogUtil.b("Track_IDEQ_FitnessClient", "onScanResult SecurityException:", ExceptionUtils.d(e));
                }
            }
        }
    };
    private CallbackBetweenClientAndController h = new CallbackBetweenClientAndController() { // from class: lag.2
        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void setBreakBySelf(boolean z) {
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void onDisconnect() {
            lag.this.disconnect(false);
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void onStopByUser() {
            if (lag.this.m != null) {
                lag.this.m.removeMessages(6);
            }
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void notifyStateChanged(String str) {
            if (lag.this.k != null) {
                lag.this.k.b(str);
            }
        }

        @Override // com.huawei.btsportdevice.callback.CallbackBetweenClientAndController
        public void notifyDataChange(int i, Bundle bundle) {
            if (lag.this.k != null) {
                lag.this.k.bUy_(i, bundle);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i) {
        return i == 1;
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setFitnessMachineControl(int i, int i2, int[] iArr) {
    }

    public void i() {
        this.k = new lak();
        t();
        BroadcastSubscribeCenter.e().b(Arrays.asList(BroadcastObserver.BLUE_STATE_TYPE), "Track_IDEQ_FitnessClient", this);
        BroadcastSubscribeCenter.e().a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeMessages(6);
            this.m.sendEmptyMessage(6, 6000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUe_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.a("Track_IDEQ_FitnessClient", "onServicesDiscovered status: ", Integer.valueOf(i));
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        try {
            if (i == 0) {
                LogUtil.h("Track_IDEQ_FitnessClient", "onServicesDiscovered GATT_SUCCESS received: ", Integer.valueOf(i));
                if (this.y && this.r) {
                    this.y = false;
                    lal lalVar = this.l;
                    if (lalVar != null) {
                        lalVar.bVc_(bluetoothGatt, i);
                    }
                } else {
                    lal lalVar2 = this.l;
                    if (lalVar2 != null) {
                        lalVar2.bVb_(bluetoothGatt, i);
                    }
                }
                this.r = true;
                return;
            }
            BluetoothGatt bluetoothGatt2 = this.c;
            if (bluetoothGatt2 == null || bluetoothGatt2.getDevice().getUuids() != null) {
                return;
            }
            LogUtil.h("Track_IDEQ_FitnessClient", "onServicesDiscovered received: ", Integer.valueOf(i));
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "doServicesDiscovered SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0032 A[Catch: SecurityException -> 0x0026, TryCatch #0 {SecurityException -> 0x0026, blocks: (B:24:0x0017, B:26:0x001d, B:5:0x002b, B:7:0x0032, B:11:0x0038, B:15:0x003f, B:19:0x0051, B:21:0x0060), top: B:23:0x0017 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bUk_(android.bluetooth.BluetoothGatt r4, int r5, int r6) {
        /*
            r3 = this;
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r0 = " NewStates:"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            java.lang.String r2 = "oldStatus:"
            java.lang.Object[] r5 = new java.lang.Object[]{r2, r5, r0, r1}
            java.lang.String r0 = "Track_IDEQ_FitnessClient"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            if (r4 == 0) goto L28
            android.bluetooth.BluetoothDevice r5 = r4.getDevice()     // Catch: java.lang.SecurityException -> L26
            if (r5 == 0) goto L28
            android.bluetooth.BluetoothDevice r5 = r4.getDevice()     // Catch: java.lang.SecurityException -> L26
            java.lang.String r5 = r5.getName()     // Catch: java.lang.SecurityException -> L26
            goto L2a
        L26:
            r4 = move-exception
            goto L64
        L28:
            java.lang.String r5 = "-"
        L2a:
            r1 = 0
            r3.x = r1     // Catch: java.lang.SecurityException -> L26
            r3.f = r6     // Catch: java.lang.SecurityException -> L26
            r2 = 2
            if (r6 != r2) goto L36
            r3.bUh_(r4)     // Catch: java.lang.SecurityException -> L26
            goto L72
        L36:
            if (r6 != 0) goto L3c
            r3.i(r5)     // Catch: java.lang.SecurityException -> L26
            goto L72
        L3c:
            r4 = 1
            if (r6 != r4) goto L4e
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.SecurityException -> L26
            java.lang.String r5 = "connecting from GATT server."
            r4[r1] = r5     // Catch: java.lang.SecurityException -> L26
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)     // Catch: java.lang.SecurityException -> L26
            java.lang.String r4 = "com.huawei.btsportdevice.ACTION_GATT_STATE_CONNECTING"
            r3.e(r4)     // Catch: java.lang.SecurityException -> L26
            goto L72
        L4e:
            r2 = 3
            if (r6 != r2) goto L60
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.SecurityException -> L26
            java.lang.String r5 = "disconnecting from GATT server."
            r4[r1] = r5     // Catch: java.lang.SecurityException -> L26
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)     // Catch: java.lang.SecurityException -> L26
            java.lang.String r4 = "com.huawei.btsportdevice.ACTION_GATT_STATE_DISCONNECTING"
            r3.e(r4)     // Catch: java.lang.SecurityException -> L26
            goto L72
        L60:
            r3.i(r5)     // Catch: java.lang.SecurityException -> L26
            goto L72
        L64:
            java.lang.String r5 = "updateConnectionState SecurityException:"
            java.lang.String r4 = com.huawei.haf.common.exception.ExceptionUtils.d(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r5, r4}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lag.bUk_(android.bluetooth.BluetoothGatt, int, int):void");
    }

    public final void d(String str, MessageOrStateCallback messageOrStateCallback) {
        LogUtil.a("Track_IDEQ_FitnessClient", "callbackKey = " + str);
        lak lakVar = this.k;
        if (lakVar != null) {
            lakVar.a(str, messageOrStateCallback);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setDeviceType(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.n = str;
        } else {
            LogUtil.b("Track_IDEQ_FitnessClient", "setDeviceType deviceType is null");
        }
    }

    private void i(String str) {
        LogUtil.a("Track_IDEQ_FitnessClient", "Disconnected from GATT server.");
        lal lalVar = this.l;
        if (lalVar != null) {
            lalVar.a(false);
        }
        if (this.v) {
            LogUtil.a("Track_IDEQ_FitnessClient", "reConnection timed out. Releasing resources.");
            return;
        }
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeMessages(6);
        }
        ab();
        this.y = true;
        this.p = false;
        if (this.t) {
            return;
        }
        if (this.ac <= 2) {
            a(str);
        } else {
            c(str);
        }
    }

    private void a(String str) {
        LogUtil.a("Track_IDEQ_FitnessClient", "The connection fails. The reconnection is initiated. The number of reConnections is ", Integer.valueOf(this.ac));
        e(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING);
        lao laoVar = new lao();
        laoVar.f("Yes");
        laoVar.c(this.ac);
        if (e(this.q)) {
            str = Constants.LINK;
        }
        laoVar.j(str);
        laoVar.c(this.i);
        lbv.c(HuaweiHealth.a(), laoVar);
        c();
        v();
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(3, 1000L);
        }
    }

    private void c(String str) {
        LogUtil.a("Track_IDEQ_FitnessClient", "The number of reConnections exceeds the maximum.");
        e(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED);
        lao laoVar = new lao();
        laoVar.f("No");
        laoVar.c(this.ac);
        if (e(this.q)) {
            str = Constants.LINK;
        }
        laoVar.j(str);
        laoVar.c(this.i);
        lbv.c(HuaweiHealth.a(), laoVar);
        e();
        this.ac = 0;
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        if (this.l != null) {
            LogUtil.a("Track_IDEQ_FitnessClient", "Reconnection failed.");
            c(false, true, false, false);
        }
    }

    private void bUh_(BluetoothGatt bluetoothGatt) {
        if (this.p) {
            LogUtil.a("Track_IDEQ_FitnessClient", "stateIsConnect bt has connected");
            return;
        }
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        this.c = bluetoothGatt;
        lal lalVar = this.l;
        if (lalVar != null) {
            lalVar.bUV_(bluetoothGatt);
        }
        this.ac = 0;
        this.p = true;
        LogUtil.a("Track_IDEQ_FitnessClient", "Connected to GATT server.");
        if (this.y) {
            e(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTED);
        } else {
            e(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED);
        }
        if (this.u) {
            return;
        }
        m();
    }

    public void m() {
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(5, PreConnectManager.CONNECT_INTERNAL);
            this.m.sendEmptyMessage(0);
        }
    }

    public DeviceInformation f() {
        lal lalVar = this.l;
        if (lalVar != null) {
            return lalVar.g();
        }
        return null;
    }

    public void l() {
        lal lalVar = this.l;
        if (lalVar != null) {
            lalVar.i();
        }
    }

    public void o() {
        if (!BleConstants.SPORT_TYPE_BIKE.equals(this.n)) {
            LogUtil.h("Track_IDEQ_FitnessClient", "deviceType is ", this.n);
            return;
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "sendResitanceRange");
        if (this.k != null) {
            lal lalVar = this.l;
            if (lalVar instanceof lai) {
                ((lai) lalVar).a();
            }
        }
    }

    public void p() {
        lak lakVar = this.k;
        if (lakVar != null) {
            lakVar.a();
        }
    }

    public void g() {
        lak lakVar = this.k;
        if (lakVar != null) {
            lakVar.bUy_(TypedValues.Custom.TYPE_REFERENCE, null);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setHeartRateFromWearable(int i) {
        lal lalVar = this.l;
        if (lalVar != null) {
            lalVar.b(i);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setRealStartWorkout() {
        lal lalVar = this.l;
        if (lalVar != null) {
            lalVar.j();
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public String getCurrentMacAddress() {
        return this.i;
    }

    private boolean t() {
        if (this.g == null && (HuaweiHealth.a().getSystemService("bluetooth") instanceof BluetoothManager)) {
            this.g = (BluetoothManager) HuaweiHealth.a().getSystemService("bluetooth");
        }
        BluetoothManager bluetoothManager = this.g;
        if (bluetoothManager == null) {
            LogUtil.b("Track_IDEQ_FitnessClient", "Unable to initialize BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.d = adapter;
        if (adapter == null) {
            LogUtil.b("Track_IDEQ_FitnessClient", "Unable to obtain BluetoothAdapter.");
            return false;
        }
        this.m = HandlerCenter.yt_(new d(), "Track_IDEQ_FitnessClient");
        this.q = 1;
        return true;
    }

    public void n() {
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.quit(false);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void connectByMac(boolean z, String str) {
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessClient", "in connectByMac：", CommonUtil.l(str));
        if (this.d == null || TextUtils.isEmpty(str)) {
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter not initialized or unspecified address.");
            this.e = null;
        } else {
            if (str.equals(this.i) && this.c != null) {
                LogUtil.c("Track_IDEQ_FitnessClient", "Trying to use an existing mBluetoothGatt for connection.");
                return;
            }
            c(z);
            this.f14721a = str;
            LogUtil.c("Track_IDEQ_FitnessClient", "in connectByMac,mac:", str);
            ac();
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public boolean reConnect() {
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessClient", "reconnect!");
        ExtendHandler extendHandler = this.m;
        if (extendHandler == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "Handler is null");
            return false;
        }
        extendHandler.removeMessages(6);
        if (this.d == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        try {
            if (!TextUtils.isEmpty(this.i)) {
                this.e = this.d.getRemoteDevice(this.i);
            } else {
                LogUtil.a("Track_IDEQ_FitnessClient", "in reConnect, mac addr is empty");
                this.e = null;
            }
            if (this.e == null) {
                LogUtil.h("Track_IDEQ_FitnessClient", "Device not found. Unable to connect.");
                return false;
            }
            BluetoothGatt bluetoothGatt = this.c;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
                this.c = null;
            }
            ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessClient", "Record the number of reConnections : ", Integer.valueOf(this.ac));
            this.c = this.e.connectGatt(HuaweiHealth.a(), false, this.o);
            this.v = false;
            if (this.ac == 0) {
                ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessClient", "Start counting down 30 seconds.");
                this.m.sendEmptyMessage(5, OpAnalyticsConstants.H5_LOADING_DELAY);
            }
            this.ac++;
            return true;
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "reConnect SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    public void a(String str, String str2) {
        this.u = true;
        if (!TextUtils.isEmpty(str2)) {
            connectByMac(true, str2);
        } else {
            connectByName(true, str);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void connectByName(boolean z, String str) {
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessClient", "in connectByName ", str);
        if (this.d == null || TextUtils.isEmpty(str)) {
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter not initialized or unspecified name.");
            return;
        }
        c(z);
        this.j = str;
        LogUtil.a("Track_IDEQ_FitnessClient", "in connectByName and now will start to scan devices");
        ac();
    }

    private void c(boolean z) {
        if (this.m == null) {
            LogUtil.b("Track_IDEQ_FitnessClient", "initConnectPrepare,mExtendHandler is null,create");
            this.m = HandlerCenter.yt_(new d(), "Track_IDEQ_FitnessClient");
        }
        if ("31".equals(this.n)) {
            this.m.sendEmptyMessage(5, PreConnectManager.CONNECT_INTERNAL);
        } else {
            this.m.sendEmptyMessage(5, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        this.q = z ? 1 : 0;
        if (this.z) {
            this.z = false;
            this.m.removeMessages(7);
            try {
                BluetoothLeScanner bluetoothLeScanner = this.d.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(this.aa);
                }
            } catch (SecurityException e) {
                LogUtil.b("Track_IDEQ_FitnessClient", "initConnectPrepare SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUd_(BluetoothDevice bluetoothDevice) {
        String address;
        LogUtil.a("Track_IDEQ_FitnessClient", "in connectDevice");
        if (bluetoothDevice != null) {
            if (this.m == null) {
                this.m = HandlerCenter.yt_(new d(), "Track_IDEQ_FitnessClient");
            }
            if (this.d == null) {
                LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter not initialized (in connectDevice)");
                return;
            }
            try {
                address = bluetoothDevice.getAddress();
            } catch (SecurityException e) {
                LogUtil.b("Track_IDEQ_FitnessClient", "connectDevice SecurityException:", ExceptionUtils.d(e));
            }
            if (address.equals(this.i) && this.c != null) {
                LogUtil.c("Track_IDEQ_FitnessClient", "Trying to use an existing mBluetoothGatt for connection (in connectDevice)");
                return;
            }
            this.i = address;
            if (e(this.q)) {
                this.l = new lai(this.c, this.h, b);
            } else {
                this.l = new lac(this.c, this.h, b);
            }
            this.l.e(this.n);
            BluetoothGatt bluetoothGatt = this.c;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
                this.c = null;
            }
            this.ac = 0;
            this.v = false;
            this.p = false;
            this.t = false;
            this.c = bluetoothDevice.connectGatt(HuaweiHealth.a(), false, this.o);
            LogUtil.c("Track_IDEQ_FitnessClient", "Trying to create new connection (in connectDevice)");
            this.m.sendEmptyMessage(5, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    public boolean bUm_(Bundle bundle) {
        if (!(this.l instanceof lai) || bundle == null || !(bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA") instanceof HashMap)) {
            return false;
        }
        MachineStatus machineStatus = new MachineStatus();
        machineStatus.setFitnessHashMap((HashMap) bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA"));
        return ((lai) this.l).b(machineStatus);
    }

    public boolean bUn_(Bundle bundle) {
        if (!(this.l instanceof lai) || bundle == null || !(bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA") instanceof HashMap)) {
            return false;
        }
        MachineStatus machineStatus = new MachineStatus();
        machineStatus.setFitnessHashMap((HashMap) bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA"));
        return ((lai) this.l).c(machineStatus);
    }

    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            lag.this.bUi_(message);
            int i = message.what;
            if (i == 6) {
                LogUtil.a("Track_IDEQ_FitnessClient", "DISCONNECT_WHEN_6S_NO_DATA_REV");
                lag lagVar = lag.this;
                if (!lagVar.e(lagVar.q) && (lag.this.l instanceof lac) && ((lac) lag.this.l).a()) {
                    return true;
                }
                lag.this.h();
                return true;
            }
            if (i == 7) {
                LogUtil.a("Track_IDEQ_FitnessClient", "STOP_SCAN");
                lag.this.aa();
                return true;
            }
            if (i == 8) {
                LogUtil.a("Track_IDEQ_FitnessClient", "STOP_SCAN_AND_CONNECT_DEVICE_BY_NAME");
                lag.this.aa();
                ThreadPoolManager.d().execute(new Runnable() { // from class: laf
                    @Override // java.lang.Runnable
                    public final void run() {
                        lag.d.this.d();
                    }
                });
                return true;
            }
            if (i != 10) {
                return false;
            }
            LogUtil.a("Track_IDEQ_FitnessClient", "CONNECT_DEVICE_BY_NAME");
            ThreadPoolManager.d().execute(new Runnable() { // from class: lae
                @Override // java.lang.Runnable
                public final void run() {
                    lag.d.this.b();
                }
            });
            return true;
        }

        /* synthetic */ void d() {
            lag lagVar = lag.this;
            lagVar.bUd_(lagVar.e);
        }

        /* synthetic */ void b() {
            lag lagVar = lag.this;
            lagVar.bUd_(lagVar.e);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void disconnect(boolean z) {
        if (z) {
            h();
            return;
        }
        synchronized (b) {
            if (this.d != null && this.c != null) {
                this.ac = 3;
                LogUtil.c("Track_IDEQ_FitnessClient", "disconnect has been called");
                q();
                this.i = null;
                this.j = "";
                this.f14721a = "";
                return;
            }
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter not initialized or this mBluetoothGatt has been disconnected already");
        }
    }

    public void h() {
        synchronized (b) {
            if (this.d != null && this.c != null) {
                LogUtil.c("Track_IDEQ_FitnessClient", "disconnectAsUnexpectedInterrupt has been called");
                q();
                return;
            }
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter not initialized or this mBluetoothGatt has been disconnected already");
        }
    }

    public void d() {
        synchronized (b) {
            this.ac = 0;
            e(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED);
            e();
            LogUtil.a("Track_IDEQ_FitnessClient", "closeAsUnexpectedInterrupt");
            c(false, true, false, false);
        }
    }

    public void a() {
        if (e(this.q) || !(this.l instanceof lac)) {
            return;
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "Clean Something For Unexpected Unbind");
        ((lac) this.l).e();
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void stopThreadsInCsafeController() {
        if (e(this.q) || !(this.l instanceof lac)) {
            return;
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "stop Threads In Csafe Controller");
        ((lac) this.l).f();
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void removeMessageOrStateCallback(String str, boolean z) {
        lak lakVar = this.k;
        if (lakVar != null) {
            LogUtil.a("Track_IDEQ_FitnessClient", "releaseResource, isNoneCallbackLeft = ", Boolean.valueOf(lakVar.d(str)));
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void releaseResource() {
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessClient", "releaseResource");
        BroadcastSubscribeCenter.e().a(Arrays.asList(BroadcastObserver.BLUE_PAIR_STATE_TYPE, BroadcastObserver.BLUE_STATE_TYPE), "Track_IDEQ_FitnessClient");
        a();
        g();
        p();
        e();
        n();
    }

    private void v() {
        if (e(this.q) || this.l == null) {
            return;
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "pause Work Threads In Csafe Controller");
        ((lac) this.l).b();
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setHasExperiencedStateOfStartForFtmp(boolean z) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "set HasExperiencedStateOfStart For FTMP");
            ((lai) this.l).d(z);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void sendByteToEquip(byte[] bArr) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "sendByteToEquip For FTMP");
            ((lai) this.l).b(bArr);
        }
    }

    public void e(int[] iArr) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "setUnlockCode For FTMP");
            ((lai) this.l).d(iArr);
        }
    }

    public void b(Map<String, String> map) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "setExtensionCollectionControl For FTMP");
            ((lai) this.l).b(map);
        }
    }

    public void e(int i, int[] iArr) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "sendTargetToDevice For FTMP");
            ((lai) this.l).a(i, iArr);
        }
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void setFitnessMachineControl(int i, int[] iArr) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "setFitnessMachineControl For FTMP");
            ((lai) this.l).b(i, iArr);
        }
    }

    public boolean b(int i, int i2) {
        if (!e(this.q) || !(this.l instanceof lai)) {
            return false;
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "adjustingIndoorBikeResistance");
        return ((lai) this.l).e(i, i2);
    }

    public void b(boolean z) {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "setSuppressPause");
            ((lai) this.l).e(z);
        }
    }

    public void s() {
        LogUtil.a("Track_IDEQ_FitnessClient", "stopSportByUser For FTMP");
        setFitnessMachineControl(8, new int[]{1});
    }

    private void ab() {
        if (e(this.q) && (this.l instanceof lai)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "setControlAllowed For FTMP");
            ((lai) this.l).d();
        }
    }

    public void e() {
        synchronized (b) {
            this.x = false;
            this.r = false;
            if (this.z) {
                ExtendHandler extendHandler = this.m;
                if (extendHandler != null) {
                    extendHandler.sendEmptyMessage(7);
                } else {
                    LogUtil.a("Track_IDEQ_FitnessClient", "mExtendHandler is null");
                }
            }
            try {
                if (this.c != null) {
                    LogUtil.a("Track_IDEQ_FitnessClient", "start to close gatt...");
                    q();
                    this.c.close();
                    this.c = null;
                }
                BluetoothDevice bluetoothDevice = this.e;
                if (bluetoothDevice != null && bluetoothDevice.getBondState() == 12) {
                    LogUtil.a("Track_IDEQ_FitnessClient", "start remove pair");
                    bUf_(this.e);
                }
                if (this.l != null && !e(this.q)) {
                    this.l.c();
                }
                x();
                this.i = null;
                this.j = "";
                this.f14721a = "";
                this.u = false;
                this.f = 0;
                if (this.m != null) {
                    LogUtil.a("Track_IDEQ_FitnessClient", "msgHandler will removeCallbacksAndMessages");
                    this.m.removeTasksAndMessages();
                    this.m.quit(false);
                }
                this.e = null;
            } catch (SecurityException e) {
                LogUtil.b("Track_IDEQ_FitnessClient", "closeBluetoothGattAndRemoveMsg SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    private void q() {
        try {
            this.c.disconnect();
        } catch (SecurityException e) {
            ReleaseLogUtil.c("R_EcologyDevice_Track_IDEQ_FitnessClient", "disconnectGatt, ", ExceptionUtils.d(e));
        }
    }

    private void x() {
        lal lalVar = this.l;
        if (lalVar == null || !(lalVar instanceof lai)) {
            return;
        }
        ((lai) lalVar).e();
    }

    public void c() {
        synchronized (b) {
            try {
                if (this.c != null) {
                    LogUtil.a("Track_IDEQ_FitnessClient", "start to close gatt...");
                    this.c.close();
                    this.c = null;
                }
                if (this.l != null && !e(this.q)) {
                    this.l.c();
                }
                x();
            } catch (SecurityException e) {
                LogUtil.b("Track_IDEQ_FitnessClient", "closeBluetoothGattBeforeReconnect SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    protected void e(String str) {
        lak lakVar = this.k;
        if (lakVar != null) {
            lakVar.b(str);
        }
    }

    protected void c(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.k != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_ALLOWED_TO_SHOW_UI", z2);
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_GOTO_START_FROM_FINISH", z3);
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_HAS_NOT_DISCONNECTED", z);
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_PRESS_ON_STOP_BTN", z4);
            this.k.bUy_(905, bundle);
        }
    }

    private boolean w() {
        return PermissionUtil.e(BaseApplication.wa_(), PermissionDialogHelper.d()) == PermissionUtil.PermissionResult.GRANTED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        if (this.d == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter is null");
            return;
        }
        this.z = false;
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeMessages(7);
        } else {
            LogUtil.h("Track_IDEQ_FitnessClient", "mExtendHandler = null");
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "now scanner stop scan");
        try {
            BluetoothLeScanner bluetoothLeScanner = this.d.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.aa);
            } else {
                LogUtil.h("Track_IDEQ_FitnessClient", "null = scanner");
            }
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "stopScanBle SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void ac() {
        if (this.d == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "BluetoothAdapter is null");
            return;
        }
        if (!w()) {
            LogUtil.h("Track_IDEQ_FitnessClient", "No location permission.");
            return;
        }
        if (TextUtils.isEmpty(this.j) && TextUtils.isEmpty(this.f14721a)) {
            LogUtil.h("Track_IDEQ_FitnessClient", "deviceName or macAddress is invalid");
            return;
        }
        this.z = true;
        if (this.m != null) {
            if ("31".equals(this.n)) {
                this.m.sendEmptyMessage(7, 9000L);
            } else {
                this.m.sendEmptyMessage(7, 29000L);
            }
        } else {
            LogUtil.h("Track_IDEQ_FitnessClient", "mExtendHandler = null");
        }
        LogUtil.a("Track_IDEQ_FitnessClient", "now scanner start scan");
        try {
            BluetoothLeScanner bluetoothLeScanner = this.d.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(2).build(), this.aa);
            } else {
                LogUtil.h("Track_IDEQ_FitnessClient", "scanner = null");
            }
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "startScanBle SecurityException:", ExceptionUtils.d(e));
        }
    }

    void bUl_(BluetoothDevice bluetoothDevice) {
        LogUtil.a("Track_IDEQ_FitnessClient", "now will distinguish device.");
        if (bluetoothDevice == null || this.x) {
            return;
        }
        try {
            if ((TextUtils.isEmpty(this.j) || !this.j.equals(bluetoothDevice.getName())) && (TextUtils.isEmpty(this.f14721a) || !this.f14721a.equals(bluetoothDevice.getAddress()))) {
                return;
            }
            LogUtil.a("Track_IDEQ_FitnessClient", "found the device we need successfully");
            this.j = bluetoothDevice.getName();
            this.x = true;
            this.e = bluetoothDevice;
            if (this.m == null) {
                return;
            }
            LogUtil.c("Track_IDEQ_FitnessClient", "mDeviceType:", this.n, ",ble mac:", bluetoothDevice.getAddress());
            this.m.removeMessages(5);
            if (lbv.b() && "31".equals(this.n) && !this.ad) {
                LogUtil.h("Track_IDEQ_FitnessClient", "ACTION_PAIR_UNSUPPORTED");
                e(AbstractFitnessClient.ACTION_PAIR_UNSUPPORTED);
            } else if (!lbv.b() && "31".equals(this.n)) {
                this.m.sendEmptyMessage(8);
            } else {
                this.m.sendEmptyMessage(9);
            }
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "distinguishDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void bUb_(BluetoothDevice bluetoothDevice) {
        LogUtil.a("Track_IDEQ_FitnessClient", "Entry beginPair");
        if (bluetoothDevice == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "btDevice is null, inner error");
            return;
        }
        try {
            if (bluetoothDevice.getBondState() != 12) {
                LogUtil.a("Track_IDEQ_FitnessClient", "begin pair");
                this.w = false;
                BroadcastSubscribeCenter.e().b(Arrays.asList(BroadcastObserver.BLUE_PAIR_STATE_TYPE), "Track_IDEQ_FitnessClient", this);
                BroadcastSubscribeCenter.e().d();
                if (bluetoothDevice.createBond()) {
                    LogUtil.a("Track_IDEQ_FitnessClient", "createBond success");
                    this.m.sendEmptyMessage(11, PreConnectManager.CONNECT_INTERNAL);
                }
            } else {
                LogUtil.a("Track_IDEQ_FitnessClient", "already paired");
                b();
            }
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "beginPair SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void bUf_(BluetoothDevice bluetoothDevice) {
        LogUtil.a("Track_IDEQ_FitnessClient", "handleMobiPairStatus");
        try {
            if (bluetoothDevice.getBondState() != 12) {
                return;
            }
            if (f() != null && "MRH3208A".equals(f().getModelString())) {
                bUg_(bluetoothDevice);
                return;
            }
            MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(bluetoothDevice.getAddress(), false);
            if (bondedDeviceByUniqueId == null || !"054d0093-9ea5-40fe-adc0-1ebc41095660".equals(bondedDeviceByUniqueId.getProductId())) {
                return;
            }
            bUg_(bluetoothDevice);
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "handleMobiPairStatus SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void b() {
        if (!TextUtils.isEmpty(this.j) || !TextUtils.isEmpty(this.f14721a)) {
            LogUtil.a("Track_IDEQ_FitnessClient", "send CONNECT_DEVICE_BY_NAME");
            BroadcastSubscribeCenter.e().a(Arrays.asList(BroadcastObserver.BLUE_PAIR_STATE_TYPE), "Track_IDEQ_FitnessClient");
            ExtendHandler extendHandler = this.m;
            if (extendHandler != null) {
                extendHandler.removeMessages(5);
                this.m.removeMessages(11);
                this.m.sendEmptyMessage(10);
                return;
            }
            return;
        }
        LogUtil.h("Track_IDEQ_FitnessClient", "connectAfterBonded param error");
        c(this.j);
    }

    @Override // com.huawei.health.device.base.AbstractFitnessClient
    public void cancelPairing() {
        try {
            BluetoothDevice bluetoothDevice = this.e;
            if (bluetoothDevice == null || bluetoothDevice.getBondState() != 11) {
                return;
            }
            LogUtil.a("Track_IDEQ_FitnessClient", "invoke cancelBondProcess:");
            this.e.getClass().getMethod("cancelBondProcess", null).invoke(this.e, null);
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            LogUtil.h("Track_IDEQ_FitnessClient", "cancelPairing Exception:", ExceptionUtils.d(e));
        }
    }

    public void b(String str) {
        LogUtil.a("Track_IDEQ_FitnessClient", "enter unPair");
        if (!TextUtils.isEmpty(str)) {
            d(str);
        } else {
            z();
        }
    }

    private void d(String str) {
        LogUtil.a("Track_IDEQ_FitnessClient", "removeBondByMacAddress");
        BluetoothAdapter bluetoothAdapter = this.d;
        if (bluetoothAdapter == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "mBluetoothAdapter is null");
            return;
        }
        try {
            BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
            if (remoteDevice != null && remoteDevice.getBondState() == 12) {
                bUg_(remoteDevice);
                return;
            }
            LogUtil.h("Track_IDEQ_FitnessClient", "The device does not have pairing relationship.");
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "removeBondByMacAddress SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void z() {
        LogUtil.a("Track_IDEQ_FitnessClient", "removeBondByTraverse");
        BluetoothAdapter bluetoothAdapter = this.d;
        if (bluetoothAdapter == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "mBluetoothAdapter is null");
            return;
        }
        try {
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            if (bondedDevices == null) {
                return;
            }
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if ((!TextUtils.isEmpty(this.j) && this.j.equals(bluetoothDevice.getName())) || (!TextUtils.isEmpty(this.f14721a) && this.f14721a.equals(bluetoothDevice.getAddress()))) {
                    bUg_(bluetoothDevice);
                }
            }
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "removeBondByTraverse SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void bUg_(BluetoothDevice bluetoothDevice) {
        LogUtil.a("Track_IDEQ_FitnessClient", "enter removeBond");
        if (bluetoothDevice == null) {
            return;
        }
        try {
            bluetoothDevice.getClass().getMethod("removeBond", null).invoke(bluetoothDevice, null);
        } catch (IllegalAccessException e) {
            LogUtil.h("Track_IDEQ_FitnessClient", "IllegalAccessException:", e.getMessage());
        } catch (NoSuchMethodException e2) {
            LogUtil.h("Track_IDEQ_FitnessClient", "NoSuchMethodException:", e2.getMessage());
        } catch (InvocationTargetException e3) {
            LogUtil.h("Track_IDEQ_FitnessClient", "InvocationTargetException:", e3.getMessage());
        }
    }

    private void y() {
        LogUtil.a("Track_IDEQ_FitnessClient", "notifyMessagesPairFailed");
        this.m.removeMessages(11);
        this.m.sendEmptyMessage(12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bUc_(ScanResult scanResult) {
        LogUtil.a("Track_IDEQ_FitnessClient", "check is need pair");
        ScanRecord scanRecord = scanResult.getScanRecord();
        if (scanRecord == null) {
            return false;
        }
        Map<ParcelUuid, byte[]> serviceData = scanRecord.getServiceData();
        List<ParcelUuid> serviceUuids = scanRecord.getServiceUuids();
        if (serviceUuids == null || serviceData == null) {
            LogUtil.h("Track_IDEQ_FitnessClient", "serviceUuids == null || serviceDataMap == null");
            return false;
        }
        for (ParcelUuid parcelUuid : serviceUuids) {
            if ("00001826-0000-1000-8000-00805f9b34fb".equalsIgnoreCase(parcelUuid.getUuid().toString()) && serviceData.containsKey(parcelUuid)) {
                return d(serviceData.get(parcelUuid));
            }
        }
        return false;
    }

    private boolean d(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            return (bArr[0] & 128) != 0;
        }
        LogUtil.h("Track_IDEQ_FitnessClient", "serviceData.length <= FLAG_INDEX");
        return false;
    }

    public boolean k() {
        return e(this.q);
    }

    public boolean j() {
        LogUtil.a("Track_IDEQ_FitnessClient", "mCurrentDeviceStatus is ", Integer.valueOf(this.f));
        return this.f == 2;
    }

    @Override // com.huawei.health.ecologydevice.callback.BroadcastObserver
    public void onNotify(int i, BluetoothDevice bluetoothDevice) {
        if (i == 1) {
            LogUtil.a("Track_IDEQ_FitnessClient", "BluetoothAdapter.STATE_ON");
            return;
        }
        if (i == 2) {
            LogUtil.a("Track_IDEQ_FitnessClient", "BluetoothAdapter.STATE_OFF");
            ExtendHandler extendHandler = this.m;
            if (extendHandler != null) {
                extendHandler.sendEmptyMessage(13);
                return;
            }
            return;
        }
        bUj_(i, bluetoothDevice);
    }

    private void bUj_(int i, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            LogUtil.a("Track_IDEQ_FitnessClient", "ReceiverCallback other receiverStatus: ", Integer.valueOf(i));
            return;
        }
        try {
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            if (name == null) {
                if (address == null || !address.equals(this.f14721a)) {
                    LogUtil.h("Track_IDEQ_FitnessClient", "BroadcastReceiverForBlePair name is null,and address not equal");
                    return;
                }
            } else if (!name.equals(this.j) && !address.equals(this.f14721a)) {
                LogUtil.a("Track_IDEQ_FitnessClient", "BroadcastReceiverForBlePair name and address is not equal");
                return;
            }
            if (i == 3) {
                LogUtil.a("Track_IDEQ_FitnessClient", name, " pair success");
                if (this.w) {
                    this.w = false;
                }
                b();
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    LogUtil.h("Track_IDEQ_FitnessClient", name, " other status：", Integer.valueOf(bluetoothDevice.getBondState()));
                    return;
                } else {
                    this.w = true;
                    LogUtil.a("Track_IDEQ_FitnessClient", name, " is pairing......");
                    return;
                }
            }
            LogUtil.a("Track_IDEQ_FitnessClient", name, " pair Failed");
            if (this.w) {
                this.w = false;
                y();
            }
        } catch (SecurityException e) {
            LogUtil.b("Track_IDEQ_FitnessClient", "switchPair SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUi_(Message message) {
        int i = message.what;
        if (i == 0) {
            BluetoothGatt bluetoothGatt = this.c;
            if (bluetoothGatt != null) {
                this.s = false;
                try {
                    bluetoothGatt.discoverServices();
                } catch (SecurityException e) {
                    ReleaseLogUtil.c("R_EcologyDevice_Track_IDEQ_FitnessClient", "discoverServices, ", ExceptionUtils.d(e));
                }
                LogUtil.a("Track_IDEQ_FitnessClient", "Attempting to start service discovery:", Boolean.valueOf(this.s));
            }
            return;
        }
        if (i == 3) {
            LogUtil.a("Track_IDEQ_FitnessClient", "MSG_RECONNECT: now will reconnect");
            this.y = true;
            reConnect();
            return;
        }
        if (i == 5) {
            LogUtil.a("Track_IDEQ_FitnessClient", "DISCONNECT_WHEN_TIMEOUT");
            this.v = true;
            d();
            return;
        }
        if (i == 9) {
            LogUtil.a("Track_IDEQ_FitnessClient", "STOP_SCAN_AND_PAIR");
            BroadcastSubscribeCenter.e().a(Arrays.asList(BroadcastObserver.BLUE_PAIR_STATE_TYPE), "Track_IDEQ_FitnessClient");
            aa();
            bUb_(this.e);
            return;
        }
        switch (i) {
            case 11:
                LogUtil.h("Track_IDEQ_FitnessClient", "PAIR_TIMEOUT continue connect");
                BroadcastSubscribeCenter.e().a(Arrays.asList(BroadcastObserver.BLUE_PAIR_STATE_TYPE), "Track_IDEQ_FitnessClient");
                cancelPairing();
                u();
                break;
            case 12:
                LogUtil.h("Track_IDEQ_FitnessClient", "PAIR_FAILED continue connect");
                BroadcastSubscribeCenter.e().a(Arrays.asList(BroadcastObserver.BLUE_PAIR_STATE_TYPE), "Track_IDEQ_FitnessClient");
                u();
                break;
            case 13:
                LogUtil.h("Track_IDEQ_FitnessClient", "BLUETOOTH_TURN_OFF");
                this.t = true;
                ab();
                d();
                break;
        }
    }

    private void u() {
        this.x = false;
        lbv.b();
        LogUtil.h("Track_IDEQ_FitnessClient", "Oversea Equipment doesn't allow to connect");
        e(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED);
    }
}
