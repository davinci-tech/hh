package defpackage;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes5.dex */
public class iyp {
    public static boolean bDn_(BluetoothDevice bluetoothDevice, String str) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothDevice.getClass().getMethod(str, new Class[0]).invoke(bluetoothDevice, new Object[0]);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean bDo_(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothHeadset == null || bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothHeadset.getClass().getMethod("connect", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }

    public static boolean bDp_(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice, int i) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothHeadset == null || bluetoothDevice == null) {
            return false;
        }
        Object invoke = bluetoothHeadset.getClass().getMethod("setPriority", BluetoothDevice.class, Integer.TYPE).invoke(bluetoothHeadset, bluetoothDevice, Integer.valueOf(i));
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }
}
