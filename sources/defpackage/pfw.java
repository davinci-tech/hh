package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class pfw {
    public static String d(int i, String str, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append("_");
        stringBuffer.append(str);
        stringBuffer.append("_");
        stringBuffer.append(i2);
        return stringBuffer.toString();
    }

    public static Bitmap doA_(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        LogUtil.b("AppUtil", "getBitmapByBytes data == null");
        return null;
    }

    public static String c(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("AppUtil", "getAppName NameNotFoundException");
            return "";
        }
    }

    public static int b(Context context, String str) {
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("com.huawei.hms.client.appid")) != null) {
                String valueOf = String.valueOf(obj);
                if (valueOf.startsWith("appid=")) {
                    return Integer.parseInt(valueOf.substring(6));
                }
                return Integer.parseInt(valueOf);
            }
        } catch (PackageManager.NameNotFoundException | NumberFormatException unused) {
        }
        return 0;
    }

    public static int e(Context context, String str) {
        if (context == null) {
            LogUtil.h("AppUtil", "getMetaDataApiLevel context is null");
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.h("AppUtil", "getMetaDataApiLevel PackageManager is null");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                int i = applicationInfo.metaData.getInt("com.huawei.wearengine.sdk.api_level");
                LogUtil.a("AppUtil", "getMetaDataApiLevel apiLevel:" + i);
                return i;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("AppUtil", "getMetaDataApiLevel PackageManager.NameNotFoundException");
        }
        return 0;
    }
}
