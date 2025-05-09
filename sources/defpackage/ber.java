package defpackage;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public class ber {
    public static int b(String str, int i) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", String.class, Integer.class).invoke(cls, str, Integer.valueOf(i));
            if (invoke instanceof Integer) {
                i = ((Integer) invoke).intValue();
            }
            bes.e("HwLowerSdkAdapter", "value: " + i);
        } catch (ClassNotFoundException unused) {
            bes.d("HwLowerSdkAdapter", "getSysProperty ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            bes.d("HwLowerSdkAdapter", "getSysProperty IllegalAccessException");
        } catch (IllegalArgumentException unused3) {
            bes.d("HwLowerSdkAdapter", "getSysProperty IllegalArgumentException");
        } catch (NoSuchMethodException unused4) {
            bes.d("HwLowerSdkAdapter", "getSysProperty NoSuchMethodException");
        } catch (SecurityException unused5) {
            bes.d("HwLowerSdkAdapter", "getSysProperty SecurityException");
        } catch (InvocationTargetException unused6) {
            bes.d("HwLowerSdkAdapter", "getSysProperty InvocationTargetException");
        }
        return i;
    }

    public static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
            if (invoke instanceof String) {
                str2 = (String) invoke;
            }
            bes.e("HwLowerSdkAdapter", "value: " + str2);
        } catch (ClassNotFoundException unused) {
            bes.d("HwLowerSdkAdapter", "getSysProperty ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            bes.d("HwLowerSdkAdapter", "getSysProperty IllegalAccessException");
        } catch (IllegalArgumentException unused3) {
            bes.d("HwLowerSdkAdapter", "getSysProperty IllegalArgumentException");
        } catch (NoSuchMethodException unused4) {
            bes.d("HwLowerSdkAdapter", "getSysProperty NoSuchMethodException");
        } catch (SecurityException unused5) {
            bes.d("HwLowerSdkAdapter", "getSysProperty SecurityException");
        } catch (InvocationTargetException unused6) {
            bes.d("HwLowerSdkAdapter", "getSysProperty InvocationTargetException");
        }
        return str2;
    }
}
