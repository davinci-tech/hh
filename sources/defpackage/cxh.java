package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class cxh extends MeasurableDevice {

    /* renamed from: a, reason: collision with root package name */
    protected BluetoothDevice f11518a;
    private ExtendHandler b;
    private BluetoothGatt e;
    private BluetoothGattCallback i;
    private final Object g = new Object();
    private BluetoothGattCallback d = new d(this);
    private volatile boolean j = false;
    private int c = 0;

    static /* synthetic */ int d(cxh cxhVar) {
        int i = cxhVar.c;
        cxhVar.c = i + 1;
        return i;
    }

    protected cxh() {
    }

    cxh(BluetoothDevice bluetoothDevice) {
        LogUtil.a("BleDevice", CommonUtil.l(bluetoothDevice.getAddress()), " BleDevice is constructed");
        this.f11518a = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bluetoothDevice.getAddress());
        super.setDeviceName(h());
    }

    private cxh(UniteDevice uniteDevice) {
        LogUtil.a("BleDevice", "BleDevice is constructed for uds");
        this.f11518a = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(uniteDevice.getIdentify());
        super.setDeviceName(h());
    }

    private String h() {
        try {
            return this.f11518a.getName();
        } catch (SecurityException e2) {
            LogUtil.b("BleDevice", "get device name:", ExceptionUtils.d(e2));
            return "";
        }
    }

    public void a(String str) {
        this.f11518a = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
    }

    public BluetoothDevice Rb_() {
        return this.f11518a;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return getAddress();
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        if (this.f11518a == null) {
            return "";
        }
        String h2 = h();
        if (!TextUtils.isEmpty(h2)) {
            LogUtil.c("BleDevice", "BleDevice name : ", h2);
            return h2;
        }
        return super.getDeviceName();
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        BluetoothDevice bluetoothDevice = this.f11518a;
        return bluetoothDevice == null ? "" : bluetoothDevice.getAddress();
    }

    public static cxh Ra_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return cxj.c().Rd_(bluetoothDevice);
    }

    public static cxh d(UniteDevice uniteDevice) {
        if (uniteDevice != null) {
            return new cxh(uniteDevice);
        }
        return null;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        iDeviceEventHandler.onStateChanged(7);
        BluetoothDevice bluetoothDevice = this.f11518a;
        if (bluetoothDevice != null) {
            updateDeviceUsedTime(1, bluetoothDevice.getAddress());
        }
        return true;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        Boolean bool;
        BluetoothDevice bluetoothDevice = this.f11518a;
        if (bluetoothDevice != null) {
            updateDeviceUsedTime(2, bluetoothDevice.getAddress());
        }
        try {
            BluetoothDevice bluetoothDevice2 = this.f11518a;
            if (bluetoothDevice2 != null && bluetoothDevice2.getBondState() == 12) {
                Method method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
                if ((method.invoke(this.f11518a, new Object[0]) instanceof Boolean) && (bool = (Boolean) method.invoke(this.f11518a, new Object[0])) != null) {
                    return bool.booleanValue();
                }
                return false;
            }
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            LogUtil.b("BleDevice", "doRemoveBond ", e2.getMessage());
        }
        return false;
    }

    public boolean d() {
        return e(false);
    }

    private void a() {
        try {
            disconnect();
            this.b = HandlerCenter.e("BleDevice");
        } catch (NullPointerException unused) {
            LogUtil.b("BleDevice", "null pointer exception");
        }
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return e(true);
    }

    private boolean e(boolean z) {
        if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(BaseApplication.e(), PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            ReleaseLogUtil.c("R_BleDevice", "no scan permission");
            nrh.b(BaseApplication.e(), R.string._2130846464_res_0x7f022300);
            return false;
        }
        if (this.f11518a == null) {
            return false;
        }
        a();
        String measureKitUuid = getMeasureKitUuid();
        String productId = getProductId();
        String uniqueId = getUniqueId();
        ReleaseLogUtil.e("R_BleDevice", "BleDevice connect. measureKitUuid=", CommonUtil.l(measureKitUuid), ", productId=", CommonUtil.l(productId), ", uniqueId=", CommonUtil.l(uniqueId));
        if (dkq.c().d() || ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isSupportUdsByProductId()) {
            UniteDevice d2 = dkq.c().d(this.f11518a.getAddress(), 2);
            ReleaseLogUtil.e("R_BleDevice", "BleDevice connect by uds");
            ddw.c().e(d2, ConnectMode.TRANSPARENT, 2, false);
            return true;
        }
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isTlvScaleProduct(productId, uniqueId)) {
            ReleaseLogUtil.e("R_BleDevice", "BleDevice connect. isTlvScaleProduct and SDK_INT >= 23");
            this.e = this.f11518a.connectGatt(cpp.a(), false, this.d, 2);
        } else {
            ReleaseLogUtil.e("R_BleDevice", "BleDevice connect by primal");
            try {
                this.e = this.f11518a.connectGatt(cpp.a(), false, this.d);
            } catch (NullPointerException unused) {
                LogUtil.b("BleDevice", "initGattService occur exception.");
            }
        }
        if (z) {
            this.c = 0;
        }
        ReleaseLogUtil.e("R_BleDevice", "connect device:", Integer.valueOf(System.identityHashCode(this)), ",bluetoothGatt:", Integer.valueOf(System.identityHashCode(this.e)), ",try to connect:", Integer.valueOf(this.c));
        return this.e != null;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid parameter of second " + i);
        }
        if (connectAsync(null)) {
            c(TimeUnit.SECONDS.toMillis(i));
        }
        return g();
    }

    private void c(long j) {
        while (j > 0) {
            try {
                Thread.sleep(200L);
                j -= 200;
            } catch (InterruptedException e2) {
                LogUtil.b("BleDevice", "checkConnectStatus ", e2.getMessage());
            }
            if (g()) {
                return;
            }
            if (!dkq.c().d() && !((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isSupportUdsByProductId() && this.i == null) {
                return;
            }
        }
    }

    private boolean g() {
        return this.j || ddw.c().e() == 2;
    }

    public boolean e() {
        return this.j;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void disconnect() {
        if (this.e != null) {
            i();
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e2) {
                LogUtil.b("BleDevice", "disconnect Exception ", e2.getMessage());
            }
            b();
        }
        this.j = false;
        ReleaseLogUtil.e("R_BleDevice", "disconnect(), device:", Integer.valueOf(System.identityHashCode(this)));
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.b.quit(true);
        }
    }

    private void i() {
        synchronized (this.g) {
            BluetoothGatt bluetoothGatt = this.e;
            if (bluetoothGatt != null) {
                try {
                    bluetoothGatt.disconnect();
                } catch (SecurityException e2) {
                    LogUtil.b("BleDevice", "disconnectGatt: ", ExceptionUtils.d(e2));
                }
                LogUtil.a("BleDevice", "disconnectGatt success, ", this.e);
            } else {
                LogUtil.h("BleDevice", "disconnectGatt fail because mBluetoothGatt = null");
            }
        }
    }

    private void b() {
        synchronized (this.g) {
            BluetoothGatt bluetoothGatt = this.e;
            if (bluetoothGatt != null) {
                try {
                    bluetoothGatt.close();
                    this.e = null;
                    LogUtil.a("BleDevice", "closeGatt success");
                } catch (SecurityException e2) {
                    LogUtil.b("BleDevice", "closeGatt SecurityException:", ExceptionUtils.d(e2));
                }
            } else {
                LogUtil.h("BleDevice", "closeGatt fail because mBluetoothGatt = null");
            }
        }
    }

    public void Rc_(BluetoothGattCallback bluetoothGattCallback) {
        this.i = bluetoothGattCallback;
        if (bluetoothGattCallback == null) {
            LogUtil.h("BleDevice", " BleDevice callback is null");
            ExtendHandler extendHandler = this.b;
            if (extendHandler != null) {
                extendHandler.quit(false);
            }
        }
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f11519a;
        private BluetoothGatt b;
        private int d;

        a(BluetoothGatt bluetoothGatt, int i, int i2) {
            this.b = bluetoothGatt;
            this.f11519a = i;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            cxh.this.j = this.d == 2;
            ReleaseLogUtil.e("R_BleDevice", "BleDevice onConnectionStateChange mNewState = ", Integer.valueOf(this.d));
            if (cxh.this.i != null) {
                if ((this.d != 133 && this.f11519a != 133) || cxh.this.c >= 2) {
                    cxh.this.i.onConnectionStateChange(this.b, this.f11519a, this.d);
                    return;
                }
                ReleaseLogUtil.e("R_BleDevice", "BleDevice onConnectionStateChange Run autoReconnect");
                cxh.this.d();
                cxh.d(cxh.this);
            }
        }
    }

    class h implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f11524a;
        private BluetoothGatt c;

        h(BluetoothGatt bluetoothGatt, int i) {
            this.c = bluetoothGatt;
            this.f11524a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cxh.this.i != null) {
                cxh.this.i.onServicesDiscovered(this.c, this.f11524a);
            }
        }
    }

    class c implements Runnable {
        private BluetoothGatt b;
        private int c;
        private int d;
        private BluetoothGattCharacteristic e;

        c(int i, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
            this.d = i;
            this.b = bluetoothGatt;
            this.e = bluetoothGattCharacteristic;
            this.c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cxh.this.i != null) {
                int i = this.d;
                if (i == 1) {
                    cxh.this.i.onCharacteristicChanged(this.b, this.e);
                    return;
                }
                if (i == 2) {
                    cxh.this.i.onCharacteristicRead(this.b, this.e, this.c);
                } else if (i == 3) {
                    cxh.this.i.onCharacteristicWrite(this.b, this.e, this.c);
                } else {
                    LogUtil.c("BleDevice", "CharacteristicHandleTask other mode : ", Integer.valueOf(i));
                }
            }
        }
    }

    class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private BluetoothGatt f11522a;
        private BluetoothGattDescriptor b;
        private int c;
        private int d;

        e(int i, BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
            this.c = i;
            this.f11522a = bluetoothGatt;
            this.b = bluetoothGattDescriptor;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cxh.this.i != null) {
                int i = this.c;
                if (i == 1) {
                    cxh.this.i.onDescriptorRead(this.f11522a, this.b, this.d);
                } else if (i == 2) {
                    cxh.this.i.onDescriptorWrite(this.f11522a, this.b, this.d);
                } else {
                    LogUtil.c("BleDevice", "DescriptorHandleTask other mode : ", Integer.valueOf(i));
                }
            }
        }
    }

    class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f11523a;
        private BluetoothGatt d;

        g(BluetoothGatt bluetoothGatt, int i) {
            this.d = bluetoothGatt;
            this.f11523a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cxh.this.i != null) {
                cxh.this.i.onReliableWriteCompleted(this.d, this.f11523a);
            }
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private BluetoothGatt f11520a;
        private int d;
        private int e;

        b(BluetoothGatt bluetoothGatt, int i, int i2) {
            this.f11520a = bluetoothGatt;
            this.d = i;
            this.e = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cxh.this.i != null) {
                cxh.this.i.onReadRemoteRssi(this.f11520a, this.d, this.e);
            }
        }
    }

    class d extends BluetoothGattCallback {
        private WeakReference<cxh> d;

        d(cxh cxhVar) {
            this.d = new WeakReference<>(cxhVar);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            ReleaseLogUtil.e("R_BleDevice", "onConnectionStateChange status : ", Integer.valueOf(i), ",newState : ", Integer.valueOf(i2), ",device : ", Integer.valueOf(System.identityHashCode(cxhVar)), ",gatt : ", Integer.valueOf(System.identityHashCode(bluetoothGatt)));
            if (i2 == 2) {
                cxh.this.updateDeviceUsedTime(3, cxhVar.getUniqueId());
            }
            cxhVar.b.postTask(cxh.this.new a(bluetoothGatt, i, i2));
            if (i2 == 2) {
                dew.b(cxhVar.getAddress(), 1);
            } else if (i2 == 0) {
                dew.b(cxhVar.getAddress(), 0);
            } else {
                LogUtil.a("BleDevice", "onConnectionStateChange other status.");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            ReleaseLogUtil.e("R_BleDevice", "onServiceDiscovered, device: ", Integer.valueOf(System.identityHashCode(cxhVar)), ", status: ", Integer.valueOf(i));
            cxhVar.b.postTask(cxh.this.new h(bluetoothGatt, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            LogUtil.a("BleDevice", "onCharacteristicChanged this device is : ", Integer.valueOf(System.identityHashCode(cxhVar)), ", gatt:", bluetoothGatt);
            cxhVar.b.postTask(cxh.this.new c(1, bluetoothGatt, bluetoothGattCharacteristic, 0));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            cxhVar.b.postTask(cxh.this.new c(2, bluetoothGatt, bluetoothGattCharacteristic, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            cxhVar.b.postTask(cxh.this.new c(3, bluetoothGatt, bluetoothGattCharacteristic, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            cxhVar.b.postTask(cxh.this.new e(2, bluetoothGatt, bluetoothGattDescriptor, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            cxhVar.b.postTask(cxh.this.new e(1, bluetoothGatt, bluetoothGattDescriptor, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            cxhVar.b.postTask(cxh.this.new g(bluetoothGatt, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            cxh cxhVar = this.d.get();
            if (cxhVar == null || bluetoothGatt == null) {
                return;
            }
            cxhVar.b.postTask(cxh.this.new b(bluetoothGatt, i, i2));
        }
    }
}
