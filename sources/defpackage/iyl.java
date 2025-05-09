package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceBondStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceHfpStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDevicePairCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes.dex */
public class iyl {
    private Handler z;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13664a = new Object();
    private static final int d = Build.VERSION.SDK_INT;
    private static iyl b = null;
    private Context r = null;
    private BtDevicePairCallback o = null;
    private final List<BtSwitchStateCallback> k = new ArrayList(10);
    private BluetoothAdapter c = null;
    private HandlerThread ac = new HandlerThread("scan_thread");
    private BluetoothHeadset q = null;
    private BtDeviceHfpStateCallback l = null;
    private BtDeviceBondStateCallback n = null;
    private BluetoothProfile.ServiceListener y = null;
    private BtDeviceDiscoverCallback m = null;
    private iyh w = null;
    private iyy u = null;
    private List<BluetoothDeviceNode> h = new ArrayList(10);
    private BluetoothManager i = null;
    private boolean e = false;
    private boolean f = false;
    private boolean v = false;
    private d aa = null;
    private BtDeviceResponseCallback t = null;
    private BroadcastReceiver x = new BroadcastReceiver() { // from class: iyl.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            int i;
            if (iyl.this.bCZ_(context, intent)) {
                String action = intent.getAction();
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    try {
                        bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    } catch (BadParcelableException unused) {
                        LogUtil.e("BTDeviceMgrUtil", "mReceiver found BadParcelableException");
                        bluetoothDevice = null;
                    }
                    if (bluetoothDevice == null) {
                        return;
                    }
                    try {
                        i = bluetoothDevice.getType();
                    } catch (SecurityException e) {
                        LogUtil.e("BTDeviceMgrUtil", "BroadcastReceiver SecurityException:", ExceptionUtils.d(e));
                        i = 0;
                    }
                    if (i == 0 || i == 2) {
                        return;
                    }
                    synchronized (iyl.f13664a) {
                        BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
                        bluetoothDeviceNode.setBtDevice(bluetoothDevice);
                        iyl.this.h.add(bluetoothDeviceNode);
                        if (iyl.this.m != null) {
                            iyl.this.m.onDeviceDiscovered(bluetoothDeviceNode, 0, null);
                        }
                    }
                    return;
                }
                if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter ACTION_DISCOVERY_FINISHED.");
                    synchronized (iyl.f13664a) {
                        if (iyl.this.m != null) {
                            iyl.this.m.onDeviceDiscoveryFinished();
                        }
                    }
                    return;
                }
                if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                    iyl.this.bDc_(intent);
                } else {
                    LogUtil.c("BTDeviceMgrUtil", "no new status");
                }
            }
        }
    };
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: iyl.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            int i;
            if (iyl.this.bCZ_(context, intent) && "android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                try {
                    bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                } catch (BadParcelableException unused) {
                    LogUtil.e("BTDeviceMgrUtil", "mBondStateReceiver onReceive BadParcelableException");
                    bluetoothDevice = null;
                }
                if (bluetoothDevice == null) {
                    LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "btDevice is null.");
                    return;
                }
                try {
                    i = bluetoothDevice.getBondState();
                } catch (SecurityException e) {
                    LogUtil.e("BTDeviceMgrUtil", "reportBondState SecurityException:", ExceptionUtils.d(e));
                    i = 0;
                }
                if (i != 0 && i == 10) {
                    Object[] objArr = new Object[4];
                    objArr[0] = 1;
                    objArr[1] = "BTDeviceMgrUtil";
                    objArr[2] = "Device is unPaired, mBtDeviceBondStateCallback :";
                    objArr[3] = Boolean.valueOf(iyl.this.n == null);
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
                    if (iyl.this.n != null) {
                        iyl.this.n.onBtDeviceBondNone(bluetoothDevice.getAddress());
                    }
                }
            }
        }
    };
    private BtDeviceDiscoverCallback s = new BtDeviceDiscoverCallback() { // from class: iyl.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i, String str) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr) {
            if (bluetoothDeviceNode != null) {
                synchronized (iyl.f13664a) {
                    iyl.this.h.add(bluetoothDeviceNode);
                    if (iyl.this.m != null) {
                        iyl.this.m.onDeviceDiscovered(bluetoothDeviceNode, i, bArr);
                    }
                }
            }
        }
    };
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: iyl.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            if (iyl.this.bCZ_(context, intent)) {
                String action = intent.getAction();
                Object[] objArr = new Object[6];
                objArr[0] = 1;
                objArr[1] = "BTDeviceMgrUtil";
                objArr[2] = "EMUI mHFPStateReceiver action :";
                objArr[3] = action;
                objArr[4] = ", mBtDeviceHfpStateCallback :";
                objArr[5] = Boolean.valueOf(iyl.this.l == null);
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
                BluetoothDevice bluetoothDevice2 = null;
                if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action) || "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                    try {
                        bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    } catch (BadParcelableException unused) {
                        LogUtil.e("BTDeviceMgrUtil", "mHFPStateReceiver hfp BadParcelableException");
                        bluetoothDevice = null;
                    }
                    if (bluetoothDevice != null) {
                        if (iyl.this.l != null && intExtra == 2) {
                            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "HFP device connected.");
                            iyl.this.l.onBtDeviceHfpConnected(bluetoothDevice, action);
                        }
                    } else {
                        LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "btDevice is null so return.");
                        return;
                    }
                }
                if ("android.bluetooth.device.action.ACL_CONNECTED".equals(action)) {
                    try {
                        bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    } catch (BadParcelableException unused2) {
                        LogUtil.e("BTDeviceMgrUtil", "mHFPStateReceiver acl BadParcelableException");
                    }
                    if (bluetoothDevice2 != null) {
                        if (iyl.this.l != null) {
                            iyl.this.l.onBtDeviceHfpConnected(bluetoothDevice2, action);
                            return;
                        }
                        return;
                    }
                    LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "btDevice is null so return.");
                }
            }
        }
    };
    private BroadcastReceiver g = new BroadcastReceiver() { // from class: iyl.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (iyl.this.bCZ_(context, intent) && "android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) && iyl.this.c != null) {
                int state = iyl.this.c.getState();
                switch (state) {
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                        synchronized (iyl.this.k) {
                            if (!bkz.e(iyl.this.k)) {
                                for (int i = 0; i < iyl.this.k.size(); i++) {
                                    ((BtSwitchStateCallback) iyl.this.k.get(i)).onBtSwitchStateCallback(iyl.e(state));
                                }
                            }
                        }
                        return;
                    default:
                        return;
                }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void bDc_(Intent intent) {
        BluetoothDevice bluetoothDevice;
        int i;
        try {
            bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        } catch (BadParcelableException unused) {
            LogUtil.e("BTDeviceMgrUtil", "mReceiver bond state BadParcelableException");
            bluetoothDevice = null;
        }
        if (bluetoothDevice == null) {
            return;
        }
        try {
            i = bluetoothDevice.getBondState();
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "reportBondState SecurityException:", ExceptionUtils.d(e));
            i = 0;
        }
        if (i == 0) {
            return;
        }
        if (i == 12) {
            BtDevicePairCallback btDevicePairCallback = this.o;
            if (btDevicePairCallback != null) {
                btDevicePairCallback.onDevicePaired(bluetoothDevice);
            }
            bDa_(this.x, "DeviceBonded");
            return;
        }
        if (i == 11) {
            BtDevicePairCallback btDevicePairCallback2 = this.o;
            if (btDevicePairCallback2 != null) {
                btDevicePairCallback2.onDevicePairing(bluetoothDevice);
                return;
            }
            return;
        }
        if (i == 10) {
            BtDevicePairCallback btDevicePairCallback3 = this.o;
            if (btDevicePairCallback3 != null) {
                btDevicePairCallback3.onDevicePairNone(bluetoothDevice);
                return;
            }
            return;
        }
        LogUtil.a("BTDeviceMgrUtil", "reportBondState on receive else");
    }

    private iyl() {
        this.ac.start();
        this.z = new Handler(this.ac.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bCZ_(Context context, Intent intent) {
        if (intent != null) {
            return true;
        }
        LogUtil.c("BTDeviceMgrUtil", "intent is null");
        return false;
    }

    public static iyl d() {
        if (b == null) {
            b = new iyl();
        }
        return b;
    }

    public void c(Context context) {
        if (context != null) {
            this.r = context;
            Object systemService = context.getSystemService("bluetooth");
            if (systemService instanceof BluetoothManager) {
                this.i = (BluetoothManager) systemService;
            }
            h();
            l();
            if (this.c == null) {
                try {
                    BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                    this.c = defaultAdapter;
                    if (defaultAdapter != null) {
                        if (!defaultAdapter.getProfileProxy(context, this.y, 1)) {
                            LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Get HFP Profile handle fail.");
                        }
                        if (!this.c.getProfileProxy(context, this.y, 4)) {
                            LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Get HID Profile handle fail.");
                        }
                    } else {
                        LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "mAdapter is null.");
                    }
                } catch (SecurityException e) {
                    LogUtil.e("BTDeviceMgrUtil", "get profile failed reason: " + e.getMessage());
                }
            }
            j();
        }
    }

    private void l() {
        if (this.y != null) {
            return;
        }
        this.y = new BluetoothProfile.ServiceListener() { // from class: iyl.6
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                if (i == 1 && (bluetoothProfile instanceof BluetoothHeadset)) {
                    iyl.this.q = (BluetoothHeadset) bluetoothProfile;
                    if (iyl.this.t != null) {
                        iyl.this.t.onResponse(0, "success");
                    }
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                if (i != 1) {
                    return;
                }
                iyl.this.q = null;
                if (iyl.this.t != null) {
                    iyl.this.t.onResponse(-1, "set null");
                }
            }
        };
    }

    public void a(Context context, BtDeviceResponseCallback btDeviceResponseCallback) {
        this.t = btDeviceResponseCallback;
        c(context);
    }

    public void j() {
        if (this.v) {
            LogUtil.c("BTDeviceMgrUtil", "Already registered");
            return;
        }
        if (this.r == null) {
            LogUtil.a("BTDeviceMgrUtil", "mContext is null");
            this.r = BaseApplication.e();
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        BroadcastManagerUtil.bFB_(BaseApplication.e(), this.p, intentFilter);
        BroadcastManagerUtil.bFB_(BaseApplication.e(), this.j, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        BroadcastManagerUtil.bFB_(BaseApplication.e(), this.g, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        LogUtil.c("BTDeviceMgrUtil", "Set already registered");
        this.v = true;
    }

    public void f() {
        bDa_(this.p, "HFPState");
        bDa_(this.j, "BondState");
        bDa_(this.g, "BTSwitch");
        bDa_(this.x, "DiscoveryFinished");
        t();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceMgrUtil", "destroy finish.");
    }

    private static void t() {
        b = null;
    }

    private void bDa_(BroadcastReceiver broadcastReceiver, String str) {
        try {
            BaseApplication.e().unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "destroy error", str);
        }
    }

    public void h() {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.closeProfileProxy(1, this.q);
            this.c = null;
            this.q = null;
            this.y = null;
        }
    }

    public int g() {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.c = defaultAdapter;
            if (defaultAdapter == null) {
                return 1;
            }
            return e(defaultAdapter.getState());
        }
        return e(bluetoothAdapter.getState());
    }

    public void bDe_(BtSwitchStateCallback btSwitchStateCallback, Handler handler) {
        bCY_(btSwitchStateCallback, handler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0033 -> B:10:0x0060). Please report as a decompilation issue!!! */
    public void d(BtSwitchStateCallback btSwitchStateCallback) {
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                c(BaseApplication.e());
                c(btSwitchStateCallback);
                try {
                    Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                    Activity wa_ = BaseApplication.wa_();
                    if (wa_ != null) {
                        wa_.startActivityForResult(intent, 23954);
                        btSwitchStateCallback = btSwitchStateCallback;
                    } else {
                        intent.setFlags(268435456);
                        BaseApplication.e().startActivity(intent);
                        btSwitchStateCallback = btSwitchStateCallback;
                    }
                } catch (ActivityNotFoundException unused) {
                    btSwitchStateCallback.onBtSwitchStateCallback(1);
                    Object[] objArr = {1, "BTDeviceMgrUtil", "btSwitchEnable ActivityNotFoundException"};
                    LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
                    btSwitchStateCallback = objArr;
                }
            } else {
                bCY_(btSwitchStateCallback, null);
                btSwitchStateCallback = btSwitchStateCallback;
            }
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "btSwitchEnable SecurityException:", ExceptionUtils.d(e));
        }
    }

    public static boolean a() {
        return BaseApplication.e().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(BtDeviceDiscoverCallback btDeviceDiscoverCallback, boolean z, boolean z2) {
        boolean a2 = a();
        if (d >= 18 && a2) {
            b(btDeviceDiscoverCallback, z, z2);
        } else {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Android version less than JELLY_BEAN_MR2.");
        }
    }

    public void e(final List<String> list, final int i, final BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        Object[] objArr = new Object[4];
        objArr[0] = 1;
        objArr[1] = "BTDeviceMgrUtil";
        objArr[2] = "Enter startBTDeviceDiscovery(), callback :";
        objArr[3] = Boolean.valueOf(btDeviceDiscoverCallback == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (btDeviceDiscoverCallback == null) {
            return;
        }
        this.z.post(new Runnable() { // from class: iyl.10
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                if (i2 != 1) {
                    if (i2 == 2) {
                        iyl.this.d(btDeviceDiscoverCallback, false, true);
                        return;
                    } else {
                        if (i2 != 4) {
                            return;
                        }
                        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "BT Type is BT_DUAL and not support.");
                        return;
                    }
                }
                List list2 = list;
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceMgrUtil", "start basic rate bluetooth discovery filter name = ", (String) it.next());
                    }
                }
                iyl.this.e(btDeviceDiscoverCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        BtDeviceDiscoverCallback btDeviceDiscoverCallback2;
        d dVar;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter discoverClassicDevice().");
        if (this.c == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.c = defaultAdapter;
            if (defaultAdapter == null) {
                LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "mAdapter is null for br discover.");
                return;
            }
        }
        try {
            if (this.c.isDiscovering()) {
                this.c.cancelDiscovery();
            }
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "discoverClassicDevice SecurityException:", ExceptionUtils.d(e));
        }
        if (this.e && (dVar = this.aa) != null) {
            dVar.a(2);
        }
        iys.e().a("");
        Object obj = f13664a;
        synchronized (obj) {
            this.m = btDeviceDiscoverCallback;
        }
        this.f = true;
        synchronized (obj) {
            this.h.clear();
        }
        if (this.r != null) {
            try {
                BroadcastManagerUtil.bFB_(BaseApplication.e(), this.x, new IntentFilter("android.bluetooth.device.action.FOUND"));
                BroadcastManagerUtil.bFB_(BaseApplication.e(), this.x, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceMgrUtil", "discoverClassicDevice SecurityException:", ExceptionUtils.d(e2));
            }
        }
        synchronized (f13664a) {
            this.h.addAll(o());
            for (BluetoothDeviceNode bluetoothDeviceNode : this.h) {
                if (bluetoothDeviceNode != null && (btDeviceDiscoverCallback2 = this.m) != null) {
                    btDeviceDiscoverCallback2.onDeviceDiscovered(bluetoothDeviceNode, 0, null);
                }
            }
        }
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter == null) {
                LogUtil.a("BTDeviceMgrUtil", "discoverClassicDevice mAdapter is null");
            } else {
                bluetoothAdapter.startDiscovery();
            }
        } catch (SecurityException e3) {
            LogUtil.e("BTDeviceMgrUtil", "discoverClassicDevice SecurityException", bll.a(e3));
        }
    }

    private void b(BtDeviceDiscoverCallback btDeviceDiscoverCallback, boolean z, boolean z2) {
        BtDeviceDiscoverCallback btDeviceDiscoverCallback2;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter discoverBLEDevice().");
        k();
        Object obj = f13664a;
        synchronized (obj) {
            this.m = btDeviceDiscoverCallback;
        }
        try {
            this.e = true;
            synchronized (obj) {
                if (z2) {
                    this.h.clear();
                }
            }
            if (this.i != null && !z) {
                synchronized (obj) {
                    this.h.addAll(m());
                }
            }
            synchronized (obj) {
                if (!z) {
                    this.h.addAll(o());
                    for (BluetoothDeviceNode bluetoothDeviceNode : this.h) {
                        if (bluetoothDeviceNode != null && (btDeviceDiscoverCallback2 = this.m) != null) {
                            btDeviceDiscoverCallback2.onDeviceDiscovered(bluetoothDeviceNode, 0, null);
                        }
                    }
                }
            }
            this.u = new iyy(this.s);
            if (!p()) {
                this.w = null;
                return;
            }
            d dVar = new d();
            this.aa = dVar;
            dVar.start();
        } catch (IllegalThreadStateException e) {
            LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "discoverBLEDevice exception:", ExceptionUtils.d(e));
        }
    }

    private boolean p() {
        BluetoothAdapter bluetoothAdapter;
        boolean z = false;
        for (int i = 0; i < 3 && (bluetoothAdapter = this.c) != null && bluetoothAdapter.isEnabled() && this.c.getState() == 12; i++) {
            try {
                z = r();
            } catch (IllegalStateException unused) {
                LogUtil.e("BTDeviceMgrUtil", "IllegalStateException now version is", Integer.valueOf(Build.VERSION.SDK_INT));
            } catch (NullPointerException unused2) {
                LogUtil.e("BTDeviceMgrUtil", "exception now version is", Integer.valueOf(Build.VERSION.SDK_INT));
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    private boolean r() {
        BluetoothLeScanner bluetoothLeScanner = this.c.getBluetoothLeScanner();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new ScanFilter.Builder().build());
        if (bluetoothLeScanner != null) {
            try {
                ReleaseLogUtil.b("BTSDK_BTDeviceMgrUtil", "startBleScan");
                bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().setScanMode(2).build(), this.u);
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "startBleScan SecurityException:", bll.a(e));
            }
        }
        return true;
    }

    private List<BluetoothDeviceNode> m() {
        ArrayList arrayList = new ArrayList(10);
        try {
            for (BluetoothDevice bluetoothDevice : this.i.getConnectedDevices(7)) {
                BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
                bluetoothDeviceNode.setBtDevice(bluetoothDevice);
                arrayList.add(bluetoothDeviceNode);
            }
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "getDeviceNodesList SecurityException", bll.a(e));
        }
        return arrayList;
    }

    private ArrayList o() {
        Method declaredMethod;
        Set<BluetoothDevice> set;
        boolean z;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        ArrayList arrayList = new ArrayList(10);
        try {
            try {
                declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", new Class[0]);
                declaredMethod.setAccessible(true);
                try {
                    set = defaultAdapter.getBondedDevices();
                } catch (SecurityException e) {
                    LogUtil.e("BTDeviceMgrUtil", "getConnectedDeviceList SecurityException", bll.a(e));
                    set = null;
                }
            } catch (SecurityException unused) {
                LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getConnectedDeviceList SecurityException");
            }
        } catch (IllegalAccessException unused2) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getConnectedDeviceList IllegalAccessException");
        } catch (IllegalArgumentException unused3) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getConnectedDeviceList IllegalArgumentException");
        } catch (NoSuchMethodException unused4) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getConnectedDeviceList NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getConnectedDeviceList InvocationTargetException");
        }
        if (set == null) {
            LogUtil.c("BTDeviceMgrUtil", "getConnectedDeviceList() devices is null.");
            return arrayList;
        }
        for (BluetoothDevice bluetoothDevice : set) {
            Method declaredMethod2 = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod2.invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                z = ((Boolean) invoke).booleanValue();
            } else {
                LogUtil.a("BTDeviceMgrUtil", "connectedState is not boolean");
                z = false;
            }
            LogUtil.c("BTDeviceMgrUtil", "getConnectDevice:" + bluetoothDevice.getName(), ";connectstatus:" + z);
            if (z) {
                BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
                bluetoothDeviceNode.setBtDevice(bluetoothDevice);
                arrayList.add(bluetoothDeviceNode);
            }
        }
        return arrayList;
    }

    private void k() {
        if (this.c == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.c = defaultAdapter;
            if (defaultAdapter == null) {
                LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "mAdapter is null.");
                return;
            }
        }
        try {
            if (this.c.isDiscovering()) {
                this.c.cancelDiscovery();
            }
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "doSearch SecurityException:", ExceptionUtils.d(e));
        }
        d dVar = this.aa;
        if (dVar != null) {
            dVar.a(2);
        }
        iys.e().a("");
    }

    /* loaded from: classes5.dex */
    class d extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private int f13668a = 0;
        private boolean e = false;

        d() {
            super.setName("StopBLEDiscoveryThread");
        }

        public void c() {
            this.f13668a = 0;
        }

        public void e(boolean z) {
            this.e = z;
        }

        public boolean d() {
            return this.e;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.f13668a = 0;
            while (!d()) {
                if (15 > this.f13668a) {
                    try {
                        Thread.sleep(1000L);
                        this.f13668a++;
                    } catch (InterruptedException unused) {
                        LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "stop ble discover with sleep occur exception");
                    }
                } else {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Start to stop ble discover for time arrive.");
                    a(2);
                }
            }
        }

        public void a(int i) {
            iyl.this.e = false;
            this.e = true;
            if (iyl.this.aa != null) {
                iyl.this.aa.c();
                iyl.this.aa.e(true);
            }
            iyl.this.aa = null;
            if (iyl.this.w == null) {
                iyl iylVar = iyl.this;
                iylVar.w = new iyh(iylVar.s);
            }
            if (iyl.this.c == null) {
                iyl.this.c = BluetoothAdapter.getDefaultAdapter();
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Start to stopLeScan.");
            if (iyl.this.c.isEnabled()) {
                try {
                    iyl.this.s();
                } catch (Exception unused) {
                    LogUtil.e("BTDeviceMgrUtil", "unknow Exception");
                }
            }
            synchronized (iyl.f13664a) {
                if (iyl.this.m != null) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Start to report ble discover handleBLEScanType :", Integer.valueOf(i));
                    if (1 == i) {
                        iyl.this.m.onDeviceDiscoveryCanceled();
                    } else if (2 == i) {
                        iyl.this.m.onDeviceDiscoveryFinished();
                    } else {
                        LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "BLE scan handle type is incorrect.");
                    }
                    iyl.this.m = null;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        Object[] objArr = new Object[2];
        objArr[0] = "stopBleScan mScanCallback:";
        objArr[1] = Boolean.valueOf(this.u != null);
        ReleaseLogUtil.b("BTSDK_BTDeviceMgrUtil", objArr);
        BluetoothLeScanner bluetoothLeScanner = this.c.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            try {
                bluetoothLeScanner.stopScan(this.u);
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "stopLeScan SecurityException:", ExceptionUtils.d(e));
            }
        }
        iyy iyyVar = this.u;
        if (iyyVar != null) {
            iyyVar.e();
        }
    }

    public void b() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter cancelBTDeviceDiscovery().");
        this.t = null;
        if (this.f) {
            if (this.r != null) {
                try {
                    BaseApplication.e().unregisterReceiver(this.x);
                } catch (IllegalArgumentException e) {
                    LogUtil.e("BTDeviceMgrUtil", "cancelBTDeviceDiscovery exception : ", e.getMessage());
                }
            }
            try {
                BluetoothAdapter bluetoothAdapter = this.c;
                if (bluetoothAdapter != null && bluetoothAdapter.isDiscovering()) {
                    this.c.cancelDiscovery();
                }
            } catch (SecurityException e2) {
                LogUtil.e("BTDeviceMgrUtil", "cancelBTDeviceDiscovery SecurityException:", ExceptionUtils.d(e2));
            }
            synchronized (f13664a) {
                BtDeviceDiscoverCallback btDeviceDiscoverCallback = this.m;
                if (btDeviceDiscoverCallback != null) {
                    btDeviceDiscoverCallback.onDeviceDiscoveryCanceled();
                    this.m = null;
                }
            }
            this.f = false;
        }
        if (this.e) {
            boolean a2 = a();
            if (d >= 18 && a2) {
                this.e = false;
                d dVar = this.aa;
                if (dVar != null) {
                    dVar.a(1);
                }
            }
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Leave cancelBTDeviceDiscovery().");
    }

    public boolean bDd_(BluetoothDevice bluetoothDevice, BtDevicePairCallback btDevicePairCallback) {
        LogUtil.c("BTDeviceMgrUtil", "Start to Pair device.");
        return bCX_(bluetoothDevice, btDevicePairCallback, "createBond");
    }

    public void b(String str) {
        LogUtil.c("BTDeviceMgrUtil", "Start to cancel bond process device.");
        bCX_(bDg_(str), null, "cancelBondProcess");
    }

    private boolean bCX_(BluetoothDevice bluetoothDevice, BtDevicePairCallback btDevicePairCallback, String str) {
        InvocationTargetException e;
        SecurityException e2;
        NoSuchMethodException e3;
        IllegalArgumentException e4;
        IllegalAccessException e5;
        boolean z = false;
        if (btDevicePairCallback != null) {
            try {
                this.o = btDevicePairCallback;
            } catch (IllegalAccessException e6) {
                e5 = e6;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e5));
                return z;
            } catch (IllegalArgumentException e7) {
                e4 = e7;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e4));
                return z;
            } catch (NoSuchMethodException e8) {
                e3 = e8;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e3));
                return z;
            } catch (SecurityException e9) {
                e2 = e9;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e2));
                return z;
            } catch (InvocationTargetException e10) {
                e = e10;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e));
                return z;
            }
        }
        if (bluetoothDevice != null && !TextUtils.isEmpty(str)) {
            if (TextUtils.equals(str, "createBond")) {
                if (this.r == null) {
                    return false;
                }
                BroadcastManagerUtil.bFB_(BaseApplication.e(), this.x, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
            }
            LogUtil.c("BTDeviceMgrUtil", "bondOperation start");
            boolean bDn_ = iyp.bDn_(bluetoothDevice, str);
            try {
                LogUtil.c("BTDeviceMgrUtil", "bondOperation result: ", Boolean.valueOf(bDn_));
                return bDn_;
            } catch (IllegalAccessException e11) {
                e5 = e11;
                z = bDn_;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e5));
                return z;
            } catch (IllegalArgumentException e12) {
                e4 = e12;
                z = bDn_;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e4));
                return z;
            } catch (NoSuchMethodException e13) {
                e3 = e13;
                z = bDn_;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e3));
                return z;
            } catch (SecurityException e14) {
                e2 = e14;
                z = bDn_;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e2));
                return z;
            } catch (InvocationTargetException e15) {
                e = e15;
                z = bDn_;
                LogUtil.e("BTDeviceMgrUtil", "0xA0200001", " bondOperation exception:", ExceptionUtils.d(e));
                return z;
            }
        }
        return false;
    }

    public boolean bDk_(BluetoothDevice bluetoothDevice) {
        LogUtil.c("BTDeviceMgrUtil", "Start to unPair device with btDevice.");
        return bCX_(bluetoothDevice, null, "removeBond");
    }

    public boolean c(String str) {
        LogUtil.c("BTDeviceMgrUtil", "Start to unPair device with mac address.");
        BluetoothDevice bDg_ = bDg_(str);
        if (bDg_ != null) {
            try {
                if (bDg_.getBondState() != 12) {
                    LogUtil.c("BTDeviceMgrUtil", "device is not bond.");
                    return true;
                }
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "unPairByMacAddress SecurityException:", ExceptionUtils.d(e));
            }
        }
        return bCX_(bDg_, null, "removeBond");
    }

    public boolean bDj_(BluetoothDevice bluetoothDevice) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter isHfpConnected().");
        BluetoothHeadset bluetoothHeadset = this.q;
        if (bluetoothHeadset != null && bluetoothDevice != null) {
            try {
                int connectionState = bluetoothHeadset.getConnectionState(bluetoothDevice);
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "HFP connect state :", Integer.valueOf(connectionState));
                if (2 == connectionState) {
                    return true;
                }
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "isHfpConnected SecurityException:", ExceptionUtils.d(e));
            }
        } else {
            LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "mHfpService or btDevice is null.");
        }
        return false;
    }

    public BluetoothDevice bDi_(List<String> list) {
        BluetoothAdapter bluetoothAdapter;
        Set<BluetoothDevice> set;
        String name;
        Object[] objArr = new Object[4];
        objArr[0] = 1;
        objArr[1] = "BTDeviceMgrUtil";
        objArr[2] = "Enter getHFPConnectedDevice(), mAdapter :";
        objArr[3] = Boolean.valueOf(this.c == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (list == null || list.size() == 0 || (bluetoothAdapter = this.c) == null) {
            return null;
        }
        try {
            set = bluetoothAdapter.getBondedDevices();
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "getHFPConnectedDevice SecurityException", ExceptionUtils.d(e));
            set = null;
        }
        if (set != null && set.size() != 0) {
            for (BluetoothDevice bluetoothDevice : set) {
                if (bDj_(bluetoothDevice)) {
                    for (String str : list) {
                        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "after toUpperCase mNameFilter[] = " + str.toUpperCase(Locale.ENGLISH));
                        if (!TextUtils.isEmpty(str) && (name = bluetoothDevice.getName()) != null && name.toUpperCase(Locale.ENGLISH).contains(str.toUpperCase(Locale.ENGLISH))) {
                            return bluetoothDevice;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<BluetoothDevice> i() {
        Object[] objArr = new Object[4];
        objArr[0] = 1;
        objArr[1] = "BTDeviceMgrUtil";
        objArr[2] = "Enter getHFPConnectedDeviceList(), mAdapter :";
        objArr[3] = Boolean.valueOf(this.c == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        Set<BluetoothDevice> set = null;
        if (this.c == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        try {
            try {
                set = this.c.getBondedDevices();
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "getHFPConnectedDeviceList SecurityException", bll.a(e));
            }
            if (set != null && set.size() > 0) {
                for (BluetoothDevice bluetoothDevice : set) {
                    if (bDj_(bluetoothDevice)) {
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (SecurityException e2) {
            LogUtil.e("BTDeviceMgrUtil", "getHFPConnectedDeviceList SecurityException,", ExceptionUtils.d(e2));
        }
        return arrayList;
    }

    public boolean d(List<String> list) {
        String str;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter isWantedDeviceConnected().");
        boolean z = false;
        if (list == null || list.size() == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "parameter is incorrect.");
            return false;
        }
        List<BluetoothDevice> i = i();
        if (i == null) {
            return false;
        }
        for (BluetoothDevice bluetoothDevice : i) {
            try {
                str = bluetoothDevice.getName();
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "isWantedBRDeviceConnected SecurityException", ExceptionUtils.d(e));
                str = "";
            }
            if (bluetoothDevice == null || TextUtils.isEmpty(str)) {
                break;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "strDeviceName :", str);
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "after toUpperCase mNameFilter[] = " + next.toUpperCase(Locale.ENGLISH));
                if (!"WATCH".equals(next) && !"BAND".equals(next) && !TextUtils.isEmpty(next) && str.toUpperCase(Locale.ENGLISH).contains(next.toUpperCase(Locale.ENGLISH))) {
                    z = true;
                    break;
                }
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    public boolean bDf_(BluetoothDevice bluetoothDevice) {
        boolean z;
        boolean z2 = false;
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            BluetoothHeadset bluetoothHeadset = this.q;
            if (bluetoothHeadset != null) {
                z = iyp.bDp_(bluetoothHeadset, bluetoothDevice, 100);
                z2 = iyp.bDo_(this.q, bluetoothDevice);
            } else {
                z = false;
            }
            return z2 & z;
        } catch (IllegalAccessException e) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "connectHFPProfile with exception:", ExceptionUtils.d(e));
            return false;
        } catch (IllegalArgumentException e2) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "connectHFPProfile with exception:", ExceptionUtils.d(e2));
            return false;
        } catch (NoSuchMethodException e3) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "connectHFPProfile with exception:", ExceptionUtils.d(e3));
            return false;
        } catch (SecurityException e4) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "connectHFPProfile with exception:", ExceptionUtils.d(e4));
            return false;
        } catch (InvocationTargetException e5) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "connectHFPProfile with exception:", ExceptionUtils.d(e5));
            return false;
        }
    }

    private int bDb_(BluetoothDevice bluetoothDevice) {
        ParcelUuid[] parcelUuidArr;
        int i;
        if (bluetoothDevice == null) {
            return -1;
        }
        try {
            parcelUuidArr = bluetoothDevice.getUuids();
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "getDeviceTypeByUUID SecurityException", ExceptionUtils.d(e));
            parcelUuidArr = null;
        }
        if (parcelUuidArr != null) {
            for (ParcelUuid parcelUuid : parcelUuidArr) {
                if ("82ff3820-8411-400c-b85a-55bdb32cf057".equalsIgnoreCase(parcelUuid.toString())) {
                    return 1;
                }
                if ("82ff3820-8411-400c-b85a-55bdb32cf059".equalsIgnoreCase(parcelUuid.toString())) {
                    i = 4;
                } else if ("82ff3820-8411-400c-b85a-55bdb32cf060".equalsIgnoreCase(parcelUuid.toString())) {
                    i = 7;
                } else {
                    LogUtil.c("BTDeviceMgrUtil", "ParcelUuid else");
                }
                return i;
            }
            return -1;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "uuids is null.");
        return -1;
    }

    public int d(String str) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getDeviceTypeByName with name :", str);
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (!TextUtils.isEmpty(upperCase)) {
            if (upperCase.startsWith("HUAWEI B2")) {
                return 1;
            }
            if (i(upperCase)) {
                return 5;
            }
            if (upperCase.equalsIgnoreCase("HUAWEI WATCH")) {
                return 3;
            }
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Do not match product name, may be they are new type device.");
        }
        return -1;
    }

    public boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("BTDeviceMgrUtil", "isSupportHfp deviceInfo is null.");
            return true;
        }
        String manufacture = deviceInfo.getManufacture();
        if (TextUtils.isEmpty(manufacture)) {
            LogUtil.a("BTDeviceMgrUtil", "isSupportHfp manufactureInfo is empty.");
            return true;
        }
        if (!manufacture.contains("010303")) {
            return true;
        }
        LogUtil.c("BTDeviceMgrUtil", "isSupportHfp is false.");
        return false;
    }

    private boolean i(String str) {
        return (str.startsWith("HONOR ZERO") || str.startsWith("HONOR BAND Z1")) || (str.startsWith("HUAWEI B0") || str.startsWith("HUAWEI BAND-"));
    }

    public int bDh_(BluetoothDevice bluetoothDevice) {
        Object[] objArr = new Object[4];
        objArr[0] = 1;
        objArr[1] = "BTDeviceMgrUtil";
        objArr[2] = "Enter getDeviceType(), btDevice :";
        objArr[3] = Boolean.valueOf(bluetoothDevice == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (bluetoothDevice == null) {
            return -1;
        }
        int bDb_ = bDb_(bluetoothDevice);
        if (-1 == bDb_) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Do not get product type by UUID.");
            try {
                bDb_ = d(bluetoothDevice.getName());
            } catch (SecurityException e) {
                LogUtil.e("BTDeviceMgrUtil", "getDeviceType SecurityException", ExceptionUtils.d(e));
            }
            if (-1 == bDb_) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Do not get product type by Device Name.");
            }
        }
        return bDb_;
    }

    public void e() {
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter == null || !bluetoothAdapter.isDiscovering()) {
                return;
            }
            this.c.cancelDiscovery();
        } catch (SecurityException e) {
            LogUtil.e("BTDeviceMgrUtil", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e));
        }
    }

    public BluetoothDevice bDg_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getBluetoothDeviceByMac with parameter incorrect.");
            return null;
        }
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter != null) {
                return bluetoothAdapter.getRemoteDevice(str);
            }
            return null;
        } catch (IllegalArgumentException e) {
            LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "getBluetoothDeviceByMac exception:", ExceptionUtils.d(e));
            return null;
        }
    }

    public void e(BtDeviceHfpStateCallback btDeviceHfpStateCallback) {
        if (btDeviceHfpStateCallback != null) {
            this.l = btDeviceHfpStateCallback;
        }
    }

    public void b(BtDeviceBondStateCallback btDeviceBondStateCallback) {
        if (btDeviceBondStateCallback != null) {
            this.n = btDeviceBondStateCallback;
        }
    }

    private List<BtSwitchStateCallback> n() {
        List<BtSwitchStateCallback> list;
        synchronized (this) {
            list = this.k;
        }
        return list;
    }

    public void c(BtSwitchStateCallback btSwitchStateCallback) {
        synchronized (n()) {
            if (btSwitchStateCallback != null) {
                if (!this.k.contains(btSwitchStateCallback)) {
                    this.k.add(btSwitchStateCallback);
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Register mBtSwitchStateCallbackList size = ", Integer.valueOf(this.k.size()));
                }
            }
        }
    }

    public void e(BtSwitchStateCallback btSwitchStateCallback) {
        synchronized (n()) {
            if (btSwitchStateCallback != null) {
                if (this.k.contains(btSwitchStateCallback)) {
                    this.k.remove(btSwitchStateCallback);
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Unregister mBtSwitchStateCallbackList size = ", Integer.valueOf(this.k.size()));
                }
            }
        }
    }

    public int b(List<String> list) {
        int i = 1;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Enter getDeviceTypeByNameFilter().");
        if (list == null || list.size() == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "parameter is incorrect.");
            return -1;
        }
        Iterator<String> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            String next = it.next();
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "after toUpperCase mNameFilter[] = " + next.toUpperCase(Locale.ENGLISH));
            if (!TextUtils.isEmpty(next)) {
                String upperCase = next.toUpperCase(Locale.ENGLISH);
                if (upperCase.equalsIgnoreCase("HUAWEI B2")) {
                    break;
                }
                if (i(upperCase)) {
                    i = 5;
                    break;
                }
                LogUtil.c("BTDeviceMgrUtil", "getDeviceTypeByNameFilter else");
            }
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceMgrUtil", "Product Type :", Integer.valueOf(i));
        return i;
    }

    public String e(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 4) ? "" : str.substring(str.length() - 4, str.length());
    }

    private void bCY_(BtSwitchStateCallback btSwitchStateCallback, Handler handler) {
        Object[] objArr = new Object[2];
        objArr[0] = "BT_GPS btSwitchEnable, callback :";
        objArr[1] = Boolean.valueOf(btSwitchStateCallback == null);
        LogUtil.c("BTDeviceMgrUtil", objArr);
        if (btSwitchStateCallback == null) {
            return;
        }
        j();
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null) {
            LogUtil.c("BTDeviceMgrUtil", "====BT_GPS mAdapter == NULL");
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.c = defaultAdapter;
            if (defaultAdapter == null) {
                btSwitchStateCallback.onBtSwitchStateCallback(1);
                return;
            }
            if (defaultAdapter.isEnabled()) {
                LogUtil.c("BTDeviceMgrUtil", "====BT_GPS BT is open1");
                btSwitchStateCallback.onBtSwitchStateCallback(3);
                return;
            }
            LogUtil.c("BTDeviceMgrUtil", "====BT_GPS BT is close1");
            c(btSwitchStateCallback);
            if (handler != null) {
                handler.sendEmptyMessage(9);
                return;
            } else {
                LogUtil.c("BTDeviceMgrUtil", "====BT_GPS BT isEnable1 = ", Boolean.valueOf(q()));
                return;
            }
        }
        if (bluetoothAdapter.isEnabled()) {
            LogUtil.c("BTDeviceMgrUtil", "====BT_GPS BT is open2");
            btSwitchStateCallback.onBtSwitchStateCallback(3);
            return;
        }
        LogUtil.c("BTDeviceMgrUtil", "====BT_GPS BT is close2");
        c(btSwitchStateCallback);
        if (handler != null) {
            handler.sendEmptyMessage(9);
        } else {
            LogUtil.c("BTDeviceMgrUtil", "====BT_GPS BT isEnable2 = ", Boolean.valueOf(q()));
        }
    }

    private boolean q() {
        try {
            return this.c.enable();
        } catch (SecurityException unused) {
            LogUtil.e("BTDeviceMgrUtil", "openBluetoothSwitch occur SecurityException");
            return false;
        }
    }
}
