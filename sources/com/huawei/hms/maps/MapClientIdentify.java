package com.huawei.hms.maps;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.internal.ICreator;
import com.huawei.hms.maps.internal.IMapClientIdentity;
import com.huawei.hms.maps.utils.AgcCoreUtil;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes4.dex */
public class MapClientIdentify {

    /* renamed from: a, reason: collision with root package name */
    private static Context f4931a;
    private static String b;
    private static String c;
    private static long d;
    private static String e;
    private IMapClientIdentity f;

    public boolean regestIdentity(Context context, ICreator iCreator) {
        LogM.d("MapClientIdentify", "sdk start regestIdentity to provider ");
        if (this.f != null) {
            LogM.d("MapClientIdentify", "already invoked.");
            return true;
        }
        if (iCreator == null) {
            LogM.d("MapClientIdentify", "creator is null.");
            return false;
        }
        String packageName = context.getApplicationContext().getPackageName();
        String appName = getAppName(context, packageName);
        if ("".equals(c)) {
            LogM.e("MapClientIdentify", "AppId is null. Please check if agconnect-services.json file is is added in app project.");
        }
        com.huawei.hms.maps.model.maa g = new com.huawei.hms.maps.model.maa().b(c).c(packageName).d(appName).e(getInstalledAppHash(context, packageName)).f(b).a(d).a(getPackageCode(context)).g(e);
        try {
            IMapClientIdentity newMapClientIdentity = iCreator.newMapClientIdentity(ObjectWrapper.wrap(com.huawei.hms.maps.internal.mab.d(context)));
            this.f = newMapClientIdentity;
            newMapClientIdentity.regestToProvierIdentity(g);
            LogM.d("MapClientIdentify", "Identity param regestToProvier success");
            return true;
        } catch (RemoteException unused) {
            LogM.e("MapClientIdentify", "regestIdentity RemoteException");
            return false;
        }
    }

    public static void setMapAuthStartTime(long j) {
        d = j;
    }

    public static void setCountryCode(String str) {
        e = str;
    }

    public static void setAppId(String str) {
        c = str;
    }

    public static void setAppContext(Context context) {
        f4931a = context;
    }

    public static void setApiKey(String str) {
        b = str;
    }

    public static void initApiKey(Context context) {
        if (TextUtils.isEmpty(b)) {
            b = AgcCoreUtil.getString(context, AgConnectInfo.AgConnectKey.API_KEY);
        }
    }

    public static String getPackageCode(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            LogM.e("MapClientIdentify", "getPackageCode failed NameNotFoundException ");
            str = "";
        }
        LogM.d("MapClientIdentify", "app version = " + str);
        return String.valueOf(str);
    }

    public static long getMapAuthStartTime() {
        return d;
    }

    public static String getInstalledAppHash(Context context, String str) {
        byte[] installedAPPSignature = getInstalledAPPSignature(context, str);
        return (installedAPPSignature == null || installedAPPSignature.length <= 0) ? "" : a(installedAPPSignature);
    }

    public static byte[] getInstalledAPPSignature(Context context, String str) {
        PackageInfo packageInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            LogM.e("HiPkgSignManager", "packageName is null or context is null");
            return new byte[0];
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null) {
                return packageInfo.signatures[0].toByteArray();
            }
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            LogM.e("HiPkgSignManager", "PackageManager.NameNotFoundException : ");
        }
        return new byte[0];
    }

    public static String getCountryCode() {
        return e;
    }

    public static String getAppName(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            LogM.e("MapClientIdentify", "In getAppName, Failed to get app name.");
            return "";
        }
    }

    public static String getAppId() {
        return c;
    }

    public static Context getAppContext() {
        return f4931a;
    }

    public static String getApiKey() {
        return b;
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i] & 255;
            stringBuffer.append(i2 < 16 ? "0" + Integer.toHexString(bArr[i] & 255) : Integer.toHexString(i2));
        }
        return stringBuffer.toString().toUpperCase(Locale.ENGLISH);
    }

    private static String a(byte[] bArr) {
        try {
            return b(MessageDigest.getInstance("SHA-256").digest(bArr));
        } catch (NoSuchAlgorithmException unused) {
            LogM.e("MapClientIdentify", "NoSuchAlgorithmException");
            return "";
        }
    }
}
