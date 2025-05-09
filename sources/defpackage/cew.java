package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
class cew extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private BluetoothHeadset f668a;
    private IDeviceEventHandler b;
    private BluetoothDevice c;
    private ceu d;
    private BluetoothDevice e;
    private BluetoothProfile.ServiceListener h = new BluetoothProfile.ServiceListener() { // from class: cew.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogUtil.c("ClassicDeviceHelper", "ClassicDevice onServiceConnected");
            if (i == 1) {
                LogUtil.c("ClassicDeviceHelper", "ClassicDevice onServiceConnected HEADSET");
                if (bluetoothProfile instanceof BluetoothHeadset) {
                    cew.this.f668a = (BluetoothHeadset) bluetoothProfile;
                }
                cew cewVar = cew.this;
                LogUtil.c("ClassicDeviceHelper", "ClassicDevice connectHsp returnValue is ", Boolean.valueOf(cewVar.Ep_(cewVar.f668a, cew.this.e)));
            }
            BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, cew.this.f668a);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 1) {
                cew.this.f668a = null;
            }
        }
    };

    cew(ceu ceuVar) {
        if (ceuVar == null) {
            throw new IllegalArgumentException("ClassicDevice cannot null");
        }
        this.c = ceuVar.b;
        this.d = ceuVar;
    }

    public String e() {
        String name;
        BluetoothDevice bluetoothDevice = this.c;
        if (bluetoothDevice == null) {
            return null;
        }
        try {
            name = bluetoothDevice.getName();
        } catch (SecurityException e) {
            LogUtil.b("ClassicDeviceHelper", "getDeviceName SecurityException:", ExceptionUtils.d(e));
        }
        if (name != null) {
            return name;
        }
        return null;
    }

    public void a(IDeviceEventHandler iDeviceEventHandler) {
        this.b = iDeviceEventHandler;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("ClassicDeviceHelper", "ClassicDevice receive broadcast ", intent.getAction());
        try {
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") instanceof BluetoothDevice ? (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") : null;
                if (bluetoothDevice != null && bluetoothDevice.getName() != null) {
                    if (bluetoothDevice.getName().equals(e()) && this.b != null) {
                        int bondState = bluetoothDevice.getBondState();
                        if (bondState == 12) {
                            LogUtil.a("ClassicDeviceHelper", "ClassicDevice is bonded");
                            this.d.updateDeviceUsedTime(1, bluetoothDevice.getAddress());
                            this.e = this.c;
                            LogUtil.c("ClassicDeviceHelper", "ClassicDevice getProfileProxy result ", Boolean.valueOf(BluetoothAdapter.getDefaultAdapter().getProfileProxy(cpp.a(), this.h, 1)));
                            cpp.e(new d(), 500L);
                            return;
                        }
                        if (bondState != 10) {
                            LogUtil.h("ClassicDeviceHelper", "ClassicDevice is other bondState:", Integer.valueOf(bondState));
                            return;
                        } else {
                            LogUtil.a("ClassicDeviceHelper", "ClassicDevice is bond_none");
                            this.b.onStateChanged(8);
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("ClassicDeviceHelper", "ClassicDevice BroadcastReceiver device = null");
            }
        } catch (RuntimeException e) {
            LogUtil.b("ClassicDeviceHelper", "ClassicDevice RuntimeException:", ExceptionUtils.d(e));
        }
    }

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            cew.this.b.onStateChanged(7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Ep_(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (bluetoothHeadset != null) {
            try {
                Method method = bluetoothHeadset.getClass().getMethod("connect", BluetoothDevice.class);
                Boolean bool = method.invoke(bluetoothHeadset, bluetoothDevice) instanceof Boolean ? (Boolean) method.invoke(bluetoothHeadset, bluetoothDevice) : null;
                if (bool != null) {
                    if (bool.booleanValue()) {
                        return true;
                    }
                }
                return false;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                LogUtil.b("ClassicDeviceHelper", "connectBluetoothHeadset ", e.getMessage());
                return false;
            }
        }
        LogUtil.h("ClassicDeviceHelper", "ClassicDevice connectBluetoothHeadset bluetoothHeadset is null");
        return false;
    }
}
