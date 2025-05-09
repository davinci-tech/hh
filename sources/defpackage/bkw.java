package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.BtDeviceHfpStateCallback;
import com.huawei.devicesdk.callback.BtDevicePairCallback;
import com.huawei.devicesdk.callback.BtSwitchCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bkw {
    private static bkw c;
    private static final Object e = new Object();
    private Context g;
    private boolean k = false;
    private BluetoothAdapter d = null;
    private ExtendHandler n = null;
    private d l = new d();
    private BtDeviceHfpStateCallback b = null;
    private Map<String, BtDevicePairCallback> f = new ConcurrentHashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private Vector<String> f427a = new Vector<>(16);
    private final List<BtSwitchCallback> i = new ArrayList(10);
    private Map<String, Boolean> m = new ConcurrentHashMap(16);
    private DeviceInfo s = null;
    private Map<String, Boolean> p = new ConcurrentHashMap(16);
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: bkw.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.a("BtCommonAdapterUtil", "mBtSwitchStateReceiver context and intent is null.");
                return;
            }
            if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) || bkw.this.d == null) {
                return;
            }
            int state = bkw.this.d.getState();
            LogUtil.c("BtCommonAdapterUtil", "btSwitchState:", Integer.valueOf(state));
            bmw.e(100053, String.valueOf(state), "", "");
            switch (state) {
                case 10:
                case 11:
                case 12:
                case 13:
                    synchronized (bkw.this.i) {
                        Iterator it = bkw.this.i.iterator();
                        while (it.hasNext()) {
                            ((BtSwitchCallback) it.next()).onBtSwitchStateCallback(bkw.e(state));
                        }
                    }
                    return;
                default:
                    LogUtil.a("BtCommonAdapterUtil", "default branch");
                    return;
            }
        }
    };
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: bkw.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.a("BtCommonAdapterUtil", "context and intent is null.");
                return;
            }
            try {
                if (!"android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                    LogUtil.a("BtCommonAdapterUtil", "this is not bond state change action, and return");
                    return;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bkw.this.sF_(bluetoothDevice)) {
                    return;
                }
                String address = bluetoothDevice.getAddress();
                int bondState = bluetoothDevice.getBondState();
                ReleaseLogUtil.b("DEVMGR_BtCommonAdapterUtil", "ReceiverMac: ", blt.a(address), " bondState: ", Integer.valueOf(bondState));
                bmw.e(100028, bmh.b(bluetoothDevice.getName()), bmh.b(Integer.valueOf(bondState)), "");
                if (bondState == 10) {
                    bkw.this.sI_(bluetoothDevice, 1);
                    return;
                }
                bkw.this.sI_(bluetoothDevice, 2);
                BtDevicePairCallback btDevicePairCallback = (BtDevicePairCallback) bkw.this.f.get(address);
                if (btDevicePairCallback == null) {
                    LogUtil.e("BtCommonAdapterUtil", "get pairCallback is null.");
                    return;
                }
                if (bondState == 12) {
                    btDevicePairCallback.onDevicePaired(bluetoothDevice);
                    bkw.this.h(address);
                } else if (bondState == 11) {
                    btDevicePairCallback.onDevicePairing(bluetoothDevice);
                } else {
                    LogUtil.a("BtCommonAdapterUtil", "unKnown bond state: ", Integer.valueOf(bondState));
                }
            } catch (BadParcelableException | SecurityException e2) {
                LogUtil.e("BtCommonAdapterUtil", "mBtDeviceReceiver onReceive BadParcelableException", ExceptionUtils.d(e2));
            }
        }
    };
    private BroadcastReceiver o = new BroadcastReceiver() { // from class: bkw.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || context == null) {
                LogUtil.a("BtCommonAdapterUtil", "context or intent is null");
                return;
            }
            String action = intent.getAction();
            Object[] objArr = new Object[4];
            objArr[0] = "EMUI mHfpStateReceiver action: ";
            objArr[1] = action;
            objArr[2] = ", mBtDeviceHfpStateCallback: ";
            objArr[3] = Boolean.valueOf(bkw.this.b == null);
            ReleaseLogUtil.b("DEVMGR_BtCommonAdapterUtil", objArr);
            try {
                if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action) || "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice == null) {
                        LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtCommonAdapterUtil", "btDevice is null so return.");
                        return;
                    }
                    DeviceInfo j = bjx.a().j(bluetoothDevice.getAddress());
                    if (j != null) {
                        bkw.this.a(j.getDeviceMac(), false);
                    }
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                    if ("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED".equals(action) && j != null && intExtra == 2) {
                        ReleaseLogUtil.b("DEVMGR_BtCommonAdapterUtil", "hid action occur");
                        bkw.this.a(j.getDeviceMac(), true);
                    }
                    boolean z = bkw.this.s != null && bluetoothDevice.getAddress().equals(bkw.this.s.getDeviceMac());
                    if (j != null || intExtra != 2 || !z) {
                        if (bkw.this.b != null && intExtra == 2) {
                            ReleaseLogUtil.b("DEVMGR_BtCommonAdapterUtil", "HFP device connected.");
                            bmw.e(100031, bmh.b(bluetoothDevice.getName()), "", bmh.b(Integer.valueOf(intExtra)));
                            bkw.this.b.onBtDeviceHfpConnected(bluetoothDevice, action);
                        }
                    } else {
                        LogUtil.c("BtCommonAdapterUtil", "reset factory device HFP connected, try to connect again");
                        bgl.c().connectDevice(bkw.this.s, false, ConnectMode.GENERAL);
                        bkw.this.d(bluetoothDevice.getAddress());
                        return;
                    }
                }
                bkw.this.sH_(intent, action);
            } catch (SecurityException e2) {
                LogUtil.e("BtCommonAdapterUtil", "isDeviceInBonded SecurityException", ExceptionUtils.d(e2));
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(int i) {
        switch (i) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 3;
            case 13:
                return 2;
            default:
                return 0;
        }
    }

    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("BtCommonAdapterUtil", "handleMessage, message is null");
                return false;
            }
            LogUtil.c("BtCommonAdapterUtil", "receive message:", Integer.valueOf(message.what));
            if (message.what != 1 || !(message.obj instanceof BluetoothDevice)) {
                return false;
            }
            bkw.this.sG_((BluetoothDevice) message.obj);
            return true;
        }
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.c("BtCommonAdapterUtil", "addResetFactoryDeviceInfo");
        this.s = deviceInfo;
    }

    public void d(String str) {
        if (this.s == null) {
            return;
        }
        if (!TextUtils.isEmpty(str) && str.equals(this.s.getDeviceMac())) {
            LogUtil.c("BtCommonAdapterUtil", "removeResetFactoryDeviceInfo address: ", blt.a(str));
            this.s = null;
        } else {
            LogUtil.a("BtCommonAdapterUtil", "removeResetFactoryDeviceInfo should not remove device");
        }
    }

    private bkw() {
        b(BaseApplication.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sH_(Intent intent, String str) {
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null) {
                LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtCommonAdapterUtil", "btDevice is null so return.");
                return;
            }
            bmw.e(100029, bmh.b(bluetoothDevice.getName()), "", "");
            BtDeviceHfpStateCallback btDeviceHfpStateCallback = this.b;
            if (btDeviceHfpStateCallback != null) {
                btDeviceHfpStateCallback.onBtDeviceHfpConnected(bluetoothDevice, str);
            }
        }
    }

    public void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.c("BtCommonAdapterUtil", "setIsHidStatus address: ", blt.a(str), " , isHidStatus: ", Boolean.valueOf(z));
            this.m.put(str, Boolean.valueOf(z));
        } else {
            LogUtil.a("BtCommonAdapterUtil", "address is null");
        }
    }

    public void c(String str) {
        this.p.put(str, true);
    }

    public boolean a(String str) {
        if (!this.m.containsKey(str)) {
            LogUtil.a("BtCommonAdapterUtil", "mIsHidStatus is not contains this address.");
            a(str, false);
            return false;
        }
        return this.m.get(str).booleanValue();
    }

    private List<BtSwitchCallback> g() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sI_(BluetoothDevice bluetoothDevice, int i) {
        if (sF_(bluetoothDevice)) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        if (i != 1) {
            if (i == 2) {
                this.f427a.remove(address);
                return;
            } else {
                LogUtil.a("BtCommonAdapterUtil", "unKnown bondType.");
                return;
            }
        }
        this.f427a.add(address);
        Message obtain = Message.obtain();
        obtain.obj = bluetoothDevice;
        obtain.what = 1;
        this.n.sendMessage(obtain, 2000L);
    }

    public Vector<String> b() {
        return this.f427a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sG_(BluetoothDevice bluetoothDevice) {
        boolean z;
        if (sF_(bluetoothDevice)) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        LogUtil.c("BtCommonAdapterUtil", "start deal bondNone device: ", blt.a(address));
        if (!this.f427a.contains(address)) {
            LogUtil.a("BtCommonAdapterUtil", "device again bond");
            return;
        }
        this.f427a.remove(address);
        DeviceInfo j = bjx.a().j(address);
        if (j != null && j.getDeviceBtType() != 2) {
            LogUtil.c("BtCommonAdapterUtil", "deleteStorageDevice getDeviceSn: ", blt.b(j.getDeviceSn()), "getDeviceMac: ", blt.b(j.getDeviceMac()), "getIdToServerType: ", Integer.valueOf(j.getIdToServerType()));
            Intent intent = new Intent("com.huawei.bone.action.SYSTEM_BLUETOOTH_UNBIND_DEVICE");
            intent.putExtra("DEVICE_SECURITY_UUID", e(j.getIdToServerType(), j.getDeviceSn(), j.getDeviceMac()));
            this.g.sendBroadcast(intent, bin.d);
            if (j.getDeviceConnectState() != 1) {
                if (!TextUtils.isEmpty(j.getDeviceMac()) && this.p.containsKey(j.getDeviceMac())) {
                    z = this.p.get(j.getDeviceMac()).booleanValue();
                    if (!z) {
                        bgl.c().unPairDevice(j);
                    } else {
                        this.p.remove(j.getDeviceMac());
                    }
                } else {
                    bgl.c().unPairDevice(j);
                    z = false;
                }
                LogUtil.c("BtCommonAdapterUtil", "dealBondNoneMsg isNotificationHide: ", Boolean.valueOf(z));
            }
        }
        BtDevicePairCallback btDevicePairCallback = this.f.get(address);
        if (btDevicePairCallback == null) {
            LogUtil.a("BtCommonAdapterUtil", "get pairCallback is null.");
        } else {
            btDevicePairCallback.onDevicePairNone(bluetoothDevice);
        }
    }

    public static String e(int i, String str, String str2) {
        return (i == 1 || i == 10) ? i == 1 ? blq.d(str) : str : str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sF_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            LogUtil.a("BtCommonAdapterUtil", "btDevice is null.");
            return true;
        }
        if (!TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            return false;
        }
        LogUtil.a("BtCommonAdapterUtil", "onReceive: deviceIdentify is null.");
        return true;
    }

    public static bkw d() {
        bkw bkwVar;
        synchronized (e) {
            if (c == null) {
                c = new bkw();
            }
            bkwVar = c;
        }
        return bkwVar;
    }

    private void b(Context context) {
        this.g = context;
        this.n = HandlerCenter.yt_(this.l, "BtCommonAdapterUtil");
        this.d = BluetoothAdapter.getDefaultAdapter();
        h();
    }

    private void h() {
        if (this.g == null) {
            LogUtil.a("BtCommonAdapterUtil", "mContext is null, registerBtDeviceReceiver fail.");
            return;
        }
        try {
            this.g.registerReceiver(this.j, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
            LogUtil.c("BtCommonAdapterUtil", "mBtDeviceReceiver already registered");
        } catch (IllegalArgumentException unused) {
            LogUtil.e("BtCommonAdapterUtil", "registerBtDeviceReceiver exception: IllegalArgumentException");
        } catch (SecurityException unused2) {
            LogUtil.e("BtCommonAdapterUtil", "registerBtDeviceReceiver exception: SecurityException");
        }
    }

    public BluetoothDevice sN_(String str) {
        LogUtil.c("BtCommonAdapterUtil", "getBluetoothDeviceByMac");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BtCommonAdapterUtil", "getBluetoothDeviceByMac with parameter incorrect.");
            return null;
        }
        try {
            BluetoothAdapter bluetoothAdapter = this.d;
            if (bluetoothAdapter != null) {
                return bluetoothAdapter.getRemoteDevice(str);
            }
            return null;
        } catch (IllegalArgumentException e2) {
            LogUtil.e("BtCommonAdapterUtil", "getBluetoothDeviceByMac exception: ", e2.getMessage());
            return null;
        }
    }

    public boolean sJ_(BluetoothDevice bluetoothDevice, BtDevicePairCallback btDevicePairCallback) {
        LogUtil.c("BtCommonAdapterUtil", "btDevicePair");
        boolean z = false;
        if (bluetoothDevice == null) {
            return false;
        }
        if (btDevicePairCallback != null) {
            try {
                this.f.put(bluetoothDevice.getAddress(), btDevicePairCallback);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
                LogUtil.e("BtCommonAdapterUtil", "btDevicePair with exception.");
                return z;
            }
        }
        h();
        boolean sS_ = bks.sS_(bluetoothDevice);
        try {
            ReleaseLogUtil.b("DEVMGR_BtCommonAdapterUtil", "btDevicePair result: ", Boolean.valueOf(sS_));
            bmw.e(100025, bmh.b(bluetoothDevice.getName()), bmh.b(Boolean.valueOf(sS_)), "");
            return sS_;
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused2) {
            z = sS_;
            LogUtil.e("BtCommonAdapterUtil", "btDevicePair with exception.");
            return z;
        }
    }

    public void sL_(BluetoothDevice bluetoothDevice) {
        LogUtil.c("BtCommonAdapterUtil", "disconnect device.");
        if (bluetoothDevice == null) {
            return;
        }
        h(bluetoothDevice.getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(String str) {
        LogUtil.c("BtCommonAdapterUtil", "remove pair callback.");
        if (this.f.containsKey(str)) {
            this.f.remove(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean sK_(BluetoothDevice bluetoothDevice) {
        boolean z = false;
        if (bluetoothDevice == null) {
            return false;
        }
        h(bluetoothDevice.getAddress());
        d(bluetoothDevice.getAddress());
        try {
            int bondState = bluetoothDevice.getBondState();
            try {
                if (bondState == 12) {
                    boolean sT_ = bks.sT_(bluetoothDevice);
                    LogUtil.c("BtCommonAdapterUtil", "btDevice.getBondState() is BOND_BONDED");
                    bmw.e(100062, bmh.b(bluetoothDevice.getName()), bmh.b(Boolean.valueOf(sT_)), "");
                    bondState = sT_;
                } else {
                    if (bluetoothDevice.getBondState() == 10) {
                        LogUtil.c("BtCommonAdapterUtil", "btDevice.getBondState() is BOND_NONE");
                        return true;
                    }
                    boolean sO_ = bks.sO_(bluetoothDevice);
                    LogUtil.a("BtCommonAdapterUtil", "btDevice.getBondState() is BOND_BONDING");
                    bmw.e(100063, bmh.b(bluetoothDevice.getName()), bmh.b(Boolean.valueOf(sO_)), "");
                    bondState = sO_;
                }
                return bondState;
            } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
                e = e2;
                z = bondState;
                LogUtil.e("BtCommonAdapterUtil", "btDeviceUnPair Exception: ", ExceptionUtils.d(e));
                return z;
            }
        } catch (IllegalAccessException e3) {
            e = e3;
        } catch (NoSuchMethodException e4) {
            e = e4;
        } catch (SecurityException e5) {
            e = e5;
        } catch (InvocationTargetException e6) {
            e = e6;
        }
    }

    public boolean e() {
        BluetoothAdapter bluetoothAdapter = this.d;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled() && this.d.getState() == 12;
    }

    public BluetoothAdapter sM_() {
        return this.d;
    }

    public void b(BtSwitchCallback btSwitchCallback) {
        if (btSwitchCallback == null || this.i.contains(btSwitchCallback)) {
            return;
        }
        synchronized (g()) {
            this.i.add(btSwitchCallback);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtCommonAdapterUtil", "Register mBtSwitchStateCallbackList size: ", Integer.valueOf(this.i.size()));
        }
    }

    public void b(BtDeviceHfpStateCallback btDeviceHfpStateCallback) {
        if (btDeviceHfpStateCallback != null) {
            this.b = btDeviceHfpStateCallback;
        }
    }

    public void j() {
        if (this.k) {
            LogUtil.a("BtCommonAdapterUtil", "Already registered");
            return;
        }
        if (this.g == null) {
            LogUtil.a("BtCommonAdapterUtil", "mContext is null");
            this.g = BaseApplication.e();
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        try {
            this.g.registerReceiver(this.o, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("BtCommonAdapterUtil", "register HfpStateReceiver: IllegalArgumentException");
        } catch (SecurityException unused2) {
            LogUtil.e("BtCommonAdapterUtil", "register HfpStateReceiver: SecurityException");
        }
        try {
            this.g.registerReceiver(this.h, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        } catch (IllegalArgumentException unused3) {
            LogUtil.e("BtCommonAdapterUtil", "register BtSwitchStateReceiver: IllegalArgumentException");
        } catch (SecurityException unused4) {
            LogUtil.e("BtCommonAdapterUtil", "register BtSwitchStateReceiver: SecurityException");
        }
        LogUtil.c("BtCommonAdapterUtil", "Set already registered");
        this.k = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c() {
        /*
            java.lang.String r0 = "BtCommonAdapterUtil"
            r1 = 0
            r2 = 1
            java.lang.String r3 = "android.os.UserHandle"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Throwable -> L30
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L30
            java.lang.String r5 = "myUserId"
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r5, r4)     // Catch: java.lang.Throwable -> L30
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L30
            r5 = 0
            java.lang.Object r3 = r3.invoke(r5, r4)     // Catch: java.lang.Throwable -> L30
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L30
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L30
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L31
            java.lang.String r5 = "isSubUserId myUserId: "
            r4[r1] = r5     // Catch: java.lang.Throwable -> L31
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L31
            r4[r2] = r5     // Catch: java.lang.Throwable -> L31
            health.compact.a.LogUtil.c(r0, r4)     // Catch: java.lang.Throwable -> L31
            goto L3a
        L30:
            r3 = r1
        L31:
            java.lang.String r4 = "get myUserId reflect failed"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.LogUtil.e(r0, r4)
        L3a:
            if (r3 == 0) goto L3d
            r1 = r2
        L3d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bkw.c():boolean");
    }

    public void e(String str) {
        biw c2 = bjx.a().c(str);
        if (c2 == null) {
            LogUtil.a("BtCommonAdapterUtil", "createBondDevice parameter is null");
            return;
        }
        int e2 = c2.e();
        LogUtil.c("BtCommonAdapterUtil", "createBondDevice parameter:", Integer.valueOf(e2), ",device:", blt.a(str));
        if (e2 == 0) {
            return;
        }
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (remoteDevice == null) {
                LogUtil.a("BtCommonAdapterUtil", "pair device error. mBluetoothDevice is null.");
                return;
            }
            int bondState = remoteDevice.getBondState();
            LogUtil.c("BtCommonAdapterUtil", "smp createBondDevice bondState:", Integer.valueOf(bondState));
            if (bondState == 10) {
                boolean sJ_ = sJ_(remoteDevice, null);
                bmw.e(100088, bmh.b(remoteDevice.getName()), bmh.b(Boolean.valueOf(sJ_)), "");
                LogUtil.c("BtCommonAdapterUtil", "pair device. ", blt.a(str), " result:", Boolean.valueOf(sJ_));
            }
        } catch (SecurityException e3) {
            LogUtil.e("BtCommonAdapterUtil", "isDeviceInBonded SecurityException", ExceptionUtils.d(e3));
        }
    }
}
