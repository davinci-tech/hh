package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class ceu extends MeasurableDevice {
    protected BluetoothDevice b;
    private IDeviceEventHandler c;
    private cew e;

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        return true;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void disconnect() {
    }

    private ceu(BluetoothDevice bluetoothDevice) {
        LogUtil.a("ClassicDevice", "ClassicDevice is constructed");
        this.b = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bluetoothDevice.getAddress());
        this.e = new cew(this);
    }

    public BluetoothDevice Ek_() {
        return this.b;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        BluetoothDevice bluetoothDevice = this.b;
        if (bluetoothDevice == null) {
            return "";
        }
        try {
            String name = bluetoothDevice.getName();
            return name != null ? name : super.getDeviceName();
        } catch (SecurityException e) {
            LogUtil.b("ClassicDevice", "getDeviceName SecurityException:", ExceptionUtils.d(e));
            return "";
        }
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        BluetoothDevice bluetoothDevice = this.b;
        return bluetoothDevice != null ? bluetoothDevice.getAddress() : "";
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return getAddress();
    }

    public static ceu Ej_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            return new ceu(bluetoothDevice);
        }
        return null;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        this.c = iDeviceEventHandler;
        this.e.a(iDeviceEventHandler);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            cpp.a().registerReceiver(this.e, intentFilter);
        } catch (SecurityException e) {
            LogUtil.b("ClassicDevice", "doCreateBond SecurityException:", ExceptionUtils.d(e));
        }
        return Ei_(this.b);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(8:5|6|(1:12)|13|14|15|16|(1:18)(1:36))|(4:23|24|25|26)|33|34|24|25|26) */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0091, code lost:
    
        r1 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a1, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.b("ClassicDevice", "createBond method ", com.huawei.haf.common.exception.ExceptionUtils.d(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0095, code lost:
    
        r1 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008f, code lost:
    
        r1 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0093, code lost:
    
        r1 = e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean Ei_(android.bluetooth.BluetoothDevice r10) {
        /*
            r9 = this;
            java.lang.String r0 = "createBond method "
            java.lang.String r1 = "ClassicDevice createBond"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "ClassicDevice"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            r1 = 0
            if (r10 != 0) goto L11
            return r1
        L11:
            java.lang.Class r3 = r10.getClass()     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.String r5 = "removeBond"
            java.lang.reflect.Method r4 = r3.getMethod(r5, r4)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Object r5 = r4.invoke(r10, r5)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            boolean r5 = r5 instanceof java.lang.Boolean     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            r6 = 1
            if (r5 == 0) goto L41
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Object r4 = r4.invoke(r10, r5)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            if (r4 == 0) goto L38
            boolean r4 = r4.booleanValue()     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            if (r4 != 0) goto L41
        L38:
            java.lang.Object[] r4 = new java.lang.Object[r6]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.String r5 = "Unpairing failed."
            r4[r1] = r5     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            com.huawei.hwlogsmodel.LogUtil.h(r2, r4)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
        L41:
            r4 = 200(0xc8, double:9.9E-322)
            r7 = 2
            java.lang.Thread.sleep(r4)     // Catch: java.lang.InterruptedException -> L48 java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            goto L56
        L48:
            r4 = move-exception
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            r5[r1] = r0     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            r5[r6] = r4     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            com.huawei.hwlogsmodel.LogUtil.b(r2, r5)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
        L56:
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.String r5 = "createBond"
            java.lang.reflect.Method r3 = r3.getMethod(r5, r4)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            java.lang.Object r10 = r3.invoke(r10, r4)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            boolean r3 = r10 instanceof java.lang.Boolean     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            if (r3 == 0) goto L6b
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            goto L6c
        L6b:
            r10 = 0
        L6c:
            if (r10 == 0) goto L77
            boolean r10 = r10.booleanValue()     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            if (r10 != 0) goto L75
            goto L77
        L75:
            r10 = r6
            goto L7f
        L77:
            com.huawei.health.device.callback.IDeviceEventHandler r10 = r9.c     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            r3 = 8
            r10.onStateChanged(r3)     // Catch: java.lang.SecurityException -> L97 java.lang.IllegalAccessException -> L99 java.lang.reflect.InvocationTargetException -> L9b java.lang.NoSuchMethodException -> L9d
            r10 = r1
        L7f:
            java.lang.Object[] r3 = new java.lang.Object[r7]     // Catch: java.lang.SecurityException -> L8f java.lang.IllegalAccessException -> L91 java.lang.reflect.InvocationTargetException -> L93 java.lang.NoSuchMethodException -> L95
            java.lang.String r4 = "ClassicDevice returnValue is "
            r3[r1] = r4     // Catch: java.lang.SecurityException -> L8f java.lang.IllegalAccessException -> L91 java.lang.reflect.InvocationTargetException -> L93 java.lang.NoSuchMethodException -> L95
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.SecurityException -> L8f java.lang.IllegalAccessException -> L91 java.lang.reflect.InvocationTargetException -> L93 java.lang.NoSuchMethodException -> L95
            r3[r6] = r1     // Catch: java.lang.SecurityException -> L8f java.lang.IllegalAccessException -> L91 java.lang.reflect.InvocationTargetException -> L93 java.lang.NoSuchMethodException -> L95
            com.huawei.hwlogsmodel.LogUtil.c(r2, r3)     // Catch: java.lang.SecurityException -> L8f java.lang.IllegalAccessException -> L91 java.lang.reflect.InvocationTargetException -> L93 java.lang.NoSuchMethodException -> L95
            goto Lac
        L8f:
            r1 = move-exception
            goto La1
        L91:
            r1 = move-exception
            goto La1
        L93:
            r1 = move-exception
            goto La1
        L95:
            r1 = move-exception
            goto La1
        L97:
            r10 = move-exception
            goto L9e
        L99:
            r10 = move-exception
            goto L9e
        L9b:
            r10 = move-exception
            goto L9e
        L9d:
            r10 = move-exception
        L9e:
            r8 = r1
            r1 = r10
            r10 = r8
        La1:
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        Lac:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ceu.Ei_(android.bluetooth.BluetoothDevice):boolean");
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        BluetoothDevice bluetoothDevice = this.b;
        if (bluetoothDevice == null) {
            LogUtil.h("ClassicDevice", "doRemoveBond mDevice is null");
            return false;
        }
        updateDeviceUsedTime(2, bluetoothDevice.getAddress());
        if (this.b.getBondState() == 12) {
            try {
                Method method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
                Boolean bool = method.invoke(this.b, new Object[0]) instanceof Boolean ? (Boolean) method.invoke(this.b, new Object[0]) : null;
                if (bool == null) {
                    return false;
                }
                return bool.booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                LogUtil.b("ClassicDevice", "doRemoveBond ", ExceptionUtils.d(e));
            }
        }
        return false;
    }

    public void e() {
        this.c = null;
    }
}
