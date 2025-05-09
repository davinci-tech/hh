package defpackage;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes3.dex */
public class bks {
    public static boolean sR_(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothHeadset == null || bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothHeadset.getClass().getMethod("connect", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean sQ_(BluetoothA2dp bluetoothA2dp, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothA2dp == null || bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothA2dp.getClass().getMethod("connect", BluetoothDevice.class).invoke(bluetoothA2dp, bluetoothDevice);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean sU_(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice, int i) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothHeadset == null || bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothHeadset.getClass().getMethod("setPriority", BluetoothDevice.class, Integer.TYPE).invoke(bluetoothHeadset, bluetoothDevice, Integer.valueOf(i));
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean sS_(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothDevice.getClass().getMethod("createBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean sT_(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean sO_(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothDevice.getClass().getMethod("cancelBondProcess", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean e() {
        return BaseApplication.e().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BtControlUtil", "cancelBtConfirmDialog mac is null");
            return;
        }
        LogUtil.c("BtControlUtil", "Enter cancelBtConfirmDialog");
        try {
            Class<?> cls = Class.forName("android.bluetooth.BluetoothAdapterEx");
            if (cls != null) {
                cls.getMethod("addPairWhiteList", String.class).invoke(cls, str);
            }
        } catch (ClassNotFoundException e) {
            LogUtil.e("BtControlUtil", "ClassNotFoundException e: ", bll.a(e));
        } catch (IllegalAccessException e2) {
            LogUtil.e("BtControlUtil", "IllegalAccessException e: ", bll.a(e2));
        } catch (NoSuchMethodException e3) {
            LogUtil.e("BtControlUtil", "NoSuchMethodException e: ", bll.a(e3));
        } catch (InvocationTargetException e4) {
            LogUtil.e("BtControlUtil", "InvocationTargetException e: ", bll.a(e4));
        }
    }

    public static boolean d(String str) {
        boolean z;
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BtControlUtil", "isDeviceInBonded mac is null");
            return false;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return false;
        }
        try {
            Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
            if (bondedDevices != null && bondedDevices.size() > 0) {
                Iterator<BluetoothDevice> it = bondedDevices.iterator();
                z = false;
                while (it.hasNext() && !(z = sP_(it.next(), str))) {
                    try {
                    } catch (SecurityException e) {
                        e = e;
                        z2 = z;
                        LogUtil.e("BtControlUtil", "isDeviceInBonded SecurityException", ExceptionUtils.d(e));
                        return z2;
                    }
                }
            } else {
                LogUtil.a("BtControlUtil", "isDeviceInBonded devices is null");
                z = false;
            }
            LogUtil.c("BtControlUtil", "isInBonded: ", Boolean.valueOf(z));
            return z;
        } catch (SecurityException e2) {
            e = e2;
        }
    }

    private static boolean sP_(BluetoothDevice bluetoothDevice, String str) {
        return bluetoothDevice != null && str.equals(bluetoothDevice.getAddress()) && bluetoothDevice.getBondState() == 12;
    }
}
