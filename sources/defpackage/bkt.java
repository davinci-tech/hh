package defpackage;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.SystemInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class bkt {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f426a = true;
    private static bkt d;
    private static final Object e = new Object();
    private Context j;
    private volatile BluetoothHeadset h = null;
    private volatile BluetoothA2dp b = null;
    private volatile BluetoothProfile f = null;
    private BluetoothAdapter c = null;
    private BluetoothProfile.ServiceListener i = new BluetoothProfile.ServiceListener() { // from class: bkt.5
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogUtil.c("BrDeviceAdapterUtil", "bluetooth profile onServiceConnected: ", Integer.valueOf(i));
            if (i == 1) {
                if (bluetoothProfile == null || !(bluetoothProfile instanceof BluetoothHeadset)) {
                    return;
                }
                bkt.this.h = (BluetoothHeadset) bluetoothProfile;
                return;
            }
            if (i == 2) {
                if (bluetoothProfile == null || !(bluetoothProfile instanceof BluetoothA2dp)) {
                    return;
                }
                bkt.this.b = (BluetoothA2dp) bluetoothProfile;
                return;
            }
            if (i != 4) {
                LogUtil.a("BrDeviceAdapterUtil", "onServiceConnected else status.");
            } else if (bluetoothProfile != null) {
                bkt.this.f = bluetoothProfile;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 1) {
                bkt.this.h = null;
                return;
            }
            if (i == 2) {
                bkt.this.b = null;
            } else if (i == 4) {
                bkt.this.f = null;
            } else {
                LogUtil.a("BrDeviceAdapterUtil", "onServiceDisconnected else status.");
            }
        }
    };

    private bkt() {
    }

    public static bkt e() {
        bkt bktVar;
        synchronized (e) {
            bktVar = d;
            if (bktVar == null) {
                bktVar = new bkt();
                d = bktVar;
                bktVar.d(BaseApplication.e());
            }
        }
        return bktVar;
    }

    private void d(Context context) {
        this.j = context;
        f();
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.c = defaultAdapter;
            if (defaultAdapter == null) {
                LogUtil.e("BrDeviceAdapterUtil", "mAdapter is null.");
                return;
            }
            if (!defaultAdapter.getProfileProxy(context, this.i, 1)) {
                LogUtil.a("BrDeviceAdapterUtil", "Get HFP Profile handle fail.");
            }
            if (!this.c.getProfileProxy(context, this.i, 2)) {
                LogUtil.a("BrDeviceAdapterUtil", "Get A2dp Profile handle fail.");
            }
            if (this.c.getProfileProxy(BaseApplication.e(), this.i, 4)) {
                return;
            }
            LogUtil.a("BrDeviceAdapterUtil", "Get HID Profile handle fail.");
        } catch (SecurityException e2) {
            d = null;
            LogUtil.e("BrDeviceAdapterUtil", "init with exception SecurityException: ", bll.a(e2));
        }
    }

    public boolean sx_(BluetoothDevice bluetoothDevice) {
        try {
            if (this.h != null && bluetoothDevice != null) {
                int connectionState = this.h.getConnectionState(bluetoothDevice);
                LogUtil.c("BrDeviceAdapterUtil", "HFP connect state: ", Integer.valueOf(connectionState));
                if (connectionState == 2) {
                    LogUtil.c("BrDeviceAdapterUtil", "HFP connected.");
                    return true;
                }
            } else {
                LogUtil.a("BrDeviceAdapterUtil", "mHfpService or btDevice is null.");
            }
        } catch (SecurityException e2) {
            LogUtil.e("BrDeviceAdapterUtil", "isHfpConnected SecurityException: ", bll.a(e2));
        }
        return false;
    }

    public boolean sv_(BluetoothDevice bluetoothDevice) {
        boolean z;
        boolean z2 = false;
        if (bluetoothDevice == null) {
            LogUtil.a("BrDeviceAdapterUtil", "btDevice is null");
            return false;
        }
        try {
            if (this.h != null) {
                z = bks.sU_(this.h, bluetoothDevice, 100);
                z2 = bks.sR_(this.h, bluetoothDevice);
            } else {
                z = false;
            }
            return z2 & z;
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            LogUtil.e("BrDeviceAdapterUtil", "connectHFPProfile with exception");
            return false;
        }
    }

    public boolean su_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            LogUtil.a("BrDeviceAdapterUtil", "connectA2dpProfile is null");
            return false;
        }
        try {
            if (this.b == null) {
                return false;
            }
            LogUtil.c("BrDeviceAdapterUtil", "connectA2dpProfile.");
            return bks.sQ_(this.b, bluetoothDevice);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            LogUtil.e("BrDeviceAdapterUtil", "connectA2dpProfile with exception");
            return false;
        }
    }

    public boolean sw_(BluetoothDevice bluetoothDevice) {
        try {
            if (this.h != null && bluetoothDevice != null) {
                int connectionState = this.b.getConnectionState(bluetoothDevice);
                LogUtil.c("BrDeviceAdapterUtil", "A2DP connect state: ", Integer.valueOf(connectionState));
                if (connectionState == 2) {
                    return true;
                }
            } else {
                LogUtil.a("BrDeviceAdapterUtil", "mA2dpService or btDevice is null.");
            }
        } catch (SecurityException e2) {
            LogUtil.e("BrDeviceAdapterUtil", "isA2dpConnected SecurityException:", ExceptionUtils.d(e2));
        }
        return false;
    }

    public void c() {
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter == null || !bluetoothAdapter.isDiscovering()) {
                return;
            }
            this.c.cancelDiscovery();
        } catch (SecurityException e2) {
            LogUtil.e("BrDeviceAdapterUtil", "cancelDiscovery SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void f() {
        if (this.c != null) {
            ReleaseLogUtil.c("DEVMGR_BrDeviceAdapterUtil", "Would never run.");
            this.c.closeProfileProxy(1, this.h);
            this.c.closeProfileProxy(2, this.b);
            this.c = null;
            this.h = null;
            this.i = null;
        }
    }

    public boolean d() {
        if (this.c == null) {
            this.c = BluetoothAdapter.getDefaultAdapter();
        }
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter != null) {
                return bluetoothAdapter.startDiscovery();
            }
            return false;
        } catch (SecurityException unused) {
            LogUtil.e("BrDeviceAdapterUtil", "discoveryBrDevice SecurityException");
            return false;
        }
    }

    public boolean sy_(BroadcastReceiver broadcastReceiver) {
        try {
            if (this.j != null && b()) {
                this.j.registerReceiver(broadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_STARTED"));
                this.j.registerReceiver(broadcastReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
                this.j.registerReceiver(broadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
                return true;
            }
            return false;
        } catch (SecurityException e2) {
            LogUtil.e("BrDeviceAdapterUtil", "registerDeviceReceiver SecurityException:", bll.a(e2));
            return false;
        }
    }

    public void sz_(BroadcastReceiver broadcastReceiver) {
        if (this.j == null) {
            this.j = BaseApplication.e();
        }
        Context context = this.j;
        if (context == null || broadcastReceiver == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
    }

    public boolean b() {
        bkw d2 = bkw.d();
        if (d2 != null) {
            return d2.e();
        }
        return false;
    }

    private void g() {
        boolean z = this.h == null || this.f == null;
        LogUtil.c("BrDeviceAdapterUtil", "initHfpAndHid isNeedInit： ", Boolean.valueOf(z));
        if (z) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException unused) {
                LogUtil.e("BrDeviceAdapterUtil", "Occur exception when thread sleeping");
            }
        }
    }

    public Set<String> a() {
        g();
        Set<String> st_ = st_(this.h);
        Set<String> st_2 = st_(this.f);
        LogUtil.c("BrDeviceAdapterUtil", "HFP connectedDevices size: ", Integer.valueOf(st_.size()), "HID connectedDevices size: ", Integer.valueOf(st_2.size()));
        st_.addAll(st_2);
        return st_;
    }

    private Set<String> st_(BluetoothProfile bluetoothProfile) {
        HashSet hashSet = new HashSet();
        if (bluetoothProfile != null) {
            try {
                List<BluetoothDevice> connectedDevices = bluetoothProfile.getConnectedDevices();
                if (bkz.e(connectedDevices)) {
                    LogUtil.a("BrDeviceAdapterUtil", "getMacs connectedDevices is null or isEmpty");
                    return hashSet;
                }
                Iterator<BluetoothDevice> it = connectedDevices.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().getAddress());
                }
            } catch (SecurityException e2) {
                LogUtil.e("BrDeviceAdapterUtil", "getConnectedDevices SecurityException,", ExceptionUtils.d(e2));
                if (f426a) {
                    f426a = false;
                    StringBuilder sb = new StringBuilder("");
                    SystemInfo.a(sb);
                    LinkedHashMap linkedHashMap = new LinkedHashMap(16);
                    linkedHashMap.put("monitorType", String.valueOf(100072));
                    linkedHashMap.put("SystemInfo", sb.toString());
                    iyv.d("80020005", linkedHashMap);
                }
            }
        }
        return hashSet;
    }
}
