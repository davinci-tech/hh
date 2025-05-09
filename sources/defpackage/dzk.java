package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes3.dex */
public class dzk {
    private static final String[] c = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f11910a = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE"};

    public static boolean b(Context context, String str) {
        try {
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("Login_PermissionCheck", e.getMessage());
        }
        return (context.getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0;
    }

    public static boolean d() {
        try {
            Class<?> cls = Class.forName("android.telephony.HwTelephonyManager");
            Field declaredField = cls.getDeclaredField("SUPPORT_SYSTEMAPP_GET_DEVICEID");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return declaredField.getInt(cls) == 1;
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            LogUtil.b("Login_PermissionCheck", "supportNewPermissionCheck", e.getMessage());
        }
        return false;
    }

    public static boolean c() {
        String str;
        ReflectiveOperationException reflectiveOperationException;
        String str2;
        String str3;
        str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("get", String.class);
            Object invoke = declaredMethod.invoke(cls.newInstance(), "ro.product.locale.language");
            str3 = invoke instanceof String ? (String) invoke : "";
            try {
                LogUtil.c("Login_PermissionCheck", "isChinaRom: langObject ", invoke);
                Object invoke2 = declaredMethod.invoke(cls.newInstance(), "ro.product.locale.region");
                str = invoke2 instanceof String ? (String) invoke2 : "";
                LogUtil.c("Login_PermissionCheck", "isChinaRom: regionObject ", invoke2);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                reflectiveOperationException = e;
                str2 = str;
                str = str3;
                LogUtil.b("Login_PermissionCheck", "isChinaRom", reflectiveOperationException.getMessage());
                str3 = str;
                str = str2;
                return !MLAsrConstants.LAN_ZH.equalsIgnoreCase(str3) ? false : false;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            reflectiveOperationException = e2;
            str2 = "";
        }
        if (!MLAsrConstants.LAN_ZH.equalsIgnoreCase(str3) && "cn".equalsIgnoreCase(str)) {
            return true;
        }
    }

    public static boolean b() {
        if (PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            return false;
        }
        return a(oxa.a().i());
    }

    private static boolean a(List<DeviceInfo> list) {
        if (list == null) {
            return false;
        }
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && deviceInfo.getDeviceBluetoothType() == 2) {
                return true;
            }
        }
        return false;
    }

    public static String[] a(boolean z) {
        if (z) {
            return (String[]) c.clone();
        }
        return (String[]) f11910a.clone();
    }
}
