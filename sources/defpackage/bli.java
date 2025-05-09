package defpackage;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* loaded from: classes3.dex */
public class bli {
    public static boolean a() {
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent("com.huawei.iconnect.action.WEAR_CONNECT_SERVICE"), 0);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            LogUtil.a("DeviceManageCommonUtil", "isIconnectActionExist listInfo is empty.");
            return false;
        }
        ApplicationInfo applicationInfo = null;
        for (ResolveInfo resolveInfo : queryIntentServices) {
            LogUtil.c("DeviceManageCommonUtil", "packageName : ", resolveInfo.serviceInfo.packageName, ", service : ", resolveInfo.serviceInfo.name);
            try {
                applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.a("DeviceManageCommonUtil", "isIconnectActionExist occur NameNotFoundException.");
            }
            if (applicationInfo == null) {
                LogUtil.a("DeviceManageCommonUtil", "isIconnectActionExist applicationInfo is null.");
            } else {
                boolean z = (applicationInfo.flags & 1) != 0;
                LogUtil.c("DeviceManageCommonUtil", "isIconnectActionExist isSystemApp: ", Boolean.valueOf(z));
                if (z) {
                    return true;
                }
                LogUtil.a("DeviceManageCommonUtil", "isIconnectActionExist not system app.");
            }
        }
        return false;
    }

    public static String d() {
        LogUtil.c("DeviceManageCommonUtil", "getThirdPartyPhoneUdid");
        if (TextUtils.isEmpty(bmf.a())) {
            bmf.d(CommonUtil.k(BaseApplication.e()));
            LogUtil.c("DeviceManageCommonUtil", "savePhoneUdidToSharedPreferences");
        }
        return bmf.a();
    }

    public static String c() {
        LogUtil.c("DeviceManageCommonUtil", "getHarmonyEmuiUdid");
        String e = e();
        if (TextUtils.isEmpty(e)) {
            String a2 = bmf.a();
            LogUtil.c("DeviceManageCommonUtil", "getHarmonyEmuiUdid cacheUdid:", blt.b(a2));
            if (TextUtils.isEmpty(a2)) {
                bmf.d(CommonUtil.k(BaseApplication.e()));
            }
            return bmf.a();
        }
        bmf.d(e);
        return e;
    }

    private static String e() {
        try {
            String b = b();
            if (!TextUtils.isEmpty(b)) {
                LogUtil.c("DeviceManageCommonUtil", "getHarmonyUDID deviceUdid");
                return b;
            }
            Object invoke = Class.forName("com.huawei.android.os.BuildEx").getMethod("getUDID", new Class[0]).invoke(null, new Object[0]);
            if (invoke instanceof String) {
                b = (String) invoke;
            }
            LogUtil.c("DeviceManageCommonUtil", "getEmuiDeviceUdid deviceUdid");
            return b;
        } catch (ClassNotFoundException unused) {
            LogUtil.e("DeviceManageCommonUtil", "getEmuiDeviceUdid(),ClassNotFoundException");
            return null;
        } catch (NoSuchMethodException unused2) {
            LogUtil.e("DeviceManageCommonUtil", "getEmuiDeviceUdid(),NoSuchMethodException");
            return null;
        } catch (InvocationTargetException unused3) {
            LogUtil.e("DeviceManageCommonUtil", "getEmuiDeviceUdid(),InvocationTargetException");
            return null;
        } catch (Exception unused4) {
            LogUtil.e("DeviceManageCommonUtil", "getEmuiDeviceUdid(),others:");
            return null;
        }
    }

    private static String b() {
        try {
            Object invoke = Class.forName("com.huawei.android.os.BuildEx").getMethod("getHarmonyUDID", new Class[0]).invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (ClassNotFoundException unused) {
            LogUtil.e("DeviceManageCommonUtil", "getHarmonyUDID(),ClassNotFoundException");
            return null;
        } catch (NoSuchMethodException unused2) {
            LogUtil.e("DeviceManageCommonUtil", "getHarmonyUDID(),NoSuchMethodException");
            return null;
        } catch (InvocationTargetException unused3) {
            LogUtil.e("DeviceManageCommonUtil", "getHarmonyUDID(),InvocationTargetException");
            return null;
        } catch (Exception unused4) {
            LogUtil.e("DeviceManageCommonUtil", "getHarmonyUDID(),others:");
            return null;
        }
    }

    public static int e(String str) {
        return d(str, 16);
    }

    public static int d(String str, int i) {
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            LogUtil.e("DeviceManageCommonUtil", "parseIntByRadix occur NumberFormatException, input is ", str);
            return -1;
        }
    }

    public static long c(String str) {
        try {
            return Long.parseLong(str, 16);
        } catch (NumberFormatException unused) {
            LogUtil.e("DeviceManageCommonUtil", "parseLongByRadix exception");
            return -1L;
        }
    }

    public static String d(String str) {
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        try {
            return new File(a2).getCanonicalPath();
        } catch (IOException e) {
            LogUtil.a("DeviceManageCommonUtil", "filterFilePath IOException: ", bll.a(e));
            return null;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (int i = 0; i < str.length(); i++) {
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(String.valueOf(str.charAt(i)))) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    public static boolean c(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || i >= bArr.length * 8) {
            return false;
        }
        if (i < 0) {
            LogUtil.a("DeviceManageCommonUtil", "isSupport target: ", Integer.valueOf(i));
            return false;
        }
        int i2 = bArr[i / 8];
        int i3 = 1 << (i % 8);
        return (i2 & i3) == i3;
    }

    public static int h(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceManageCommonUtil", "stringToInt str is empty");
            return 0;
        }
        try {
            if (str.indexOf(".") > 0) {
                str = str.substring(0, str.indexOf("."));
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.e("DeviceManageCommonUtil", "stringToInt occur NumberFormatException: ", bll.a(e));
            return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static byte[] b(String str) {
        Throwable th;
        Closeable closeable;
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e;
        FileNotFoundException e2;
        Closeable closeable2;
        Closeable closeable3 = null;
        r2 = null;
        r2 = null;
        byte[] bArr = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            try {
                str = new FileInputStream(new File(bky.d(str)));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e3) {
            e2 = e3;
            str = 0;
            byteArrayOutputStream = null;
        } catch (IOException e4) {
            e = e4;
            str = 0;
            byteArrayOutputStream = null;
        } catch (Exception unused) {
            str = 0;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            closeable = null;
            blv.d(closeable3);
            blv.d(closeable);
            throw th;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = str.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
                closeable2 = str;
            } catch (FileNotFoundException e5) {
                e2 = e5;
                LogUtil.e("DeviceManageCommonUtil", "file2byte FileNotFoundException file: ", bll.a(e2));
                closeable2 = str;
                blv.d(closeable2);
                blv.d(byteArrayOutputStream);
                return bArr;
            } catch (IOException e6) {
                e = e6;
                LogUtil.e("DeviceManageCommonUtil", "file2byte IOException file: ", bll.a(e));
                closeable2 = str;
                blv.d(closeable2);
                blv.d(byteArrayOutputStream);
                return bArr;
            } catch (Exception unused2) {
                LogUtil.e("DeviceManageCommonUtil", "file2byte occur Exception.");
                closeable2 = str;
                blv.d(closeable2);
                blv.d(byteArrayOutputStream);
                return bArr;
            }
        } catch (FileNotFoundException e7) {
            e2 = e7;
            byteArrayOutputStream = null;
        } catch (IOException e8) {
            e = e8;
            byteArrayOutputStream = null;
        } catch (Exception unused3) {
            byteArrayOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            closeable = null;
            closeable3 = str;
            blv.d(closeable3);
            blv.d(closeable);
            throw th;
        }
        blv.d(closeable2);
        blv.d(byteArrayOutputStream);
        return bArr;
    }
}
