package com.huawei.haf.dynamic;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.common.utils.HexUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class DynamicApkValidator {

    /* renamed from: a, reason: collision with root package name */
    private static Signature[] f2107a;
    private static final List<String> b = Arrays.asList(AppBundleBuildConfig.h());
    private static boolean c = true;

    private DynamicApkValidator() {
    }

    public static boolean d(Context context, File file, String str) {
        if (!FileUtils.c(file)) {
            LogUtil.e("HAF_ApkValidator", "module ", file.getPath(), " is illegal.");
            return false;
        }
        Signature[] xU_ = xU_(context, file);
        if (CollectionUtils.b(xU_)) {
            LogUtil.e("HAF_ApkValidator", "module ", file.getPath(), " is not signed.");
            return false;
        }
        if (yb_(context, xU_) || yc_(str, xU_)) {
            return true;
        }
        LogUtil.a("HAF_ApkValidator", "module ", file.getPath(), " signature illegal, ", ya_(xU_));
        return false;
    }

    private static boolean yb_(Context context, Signature[] signatureArr) {
        Signature[] xV_ = xV_(context);
        if (CollectionUtils.b(xV_)) {
            LogUtil.e("HAF_ApkValidator", "No certificates found for app.");
            return false;
        }
        for (Signature signature : signatureArr) {
            for (Signature signature2 : xV_) {
                if (signature.equals(signature2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean yc_(String str, Signature[] signatureArr) {
        if (!TextUtils.isEmpty(str) && !b.isEmpty()) {
            for (Signature signature : signatureArr) {
                if (b.contains(str + ":" + b(signature.toByteArray()))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Signature[] xV_(Context context) {
        if (f2107a == null) {
            f2107a = xX_(context, null);
        }
        return f2107a;
    }

    private static Signature[] xU_(Context context, File file) {
        return xX_(context, file);
    }

    private static Signature[] xX_(Context context, File file) {
        PackageInfo xW_ = xW_(context, file);
        if (xW_ == null) {
            return xZ_(file);
        }
        return xY_(xW_);
    }

    private static Signature[] xY_(PackageInfo packageInfo) {
        SigningInfo signingInfo;
        Signature[] signatureArr = packageInfo.signatures;
        if (Build.VERSION.SDK_INT < 28 || (signingInfo = packageInfo.signingInfo) == null) {
            return signatureArr;
        }
        if (signingInfo.hasMultipleSigners()) {
            return signingInfo.getApkContentsSigners();
        }
        return signingInfo.getSigningCertificateHistory();
    }

    private static Signature[] xZ_(File file) {
        if (file == null) {
            return null;
        }
        Object e = ReflectionUtils.e("android.content.pm.PackageParser");
        if (e == null) {
            LogUtil.a("HAF_ApkValidator", "packageParser == null");
            return null;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return AndroidPlatformV28.ye_(e, file);
        }
        return AndroidPlatformV21.yd_(e, file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object a(Object obj, File file, int i) {
        if (!c) {
            return null;
        }
        try {
            return ReflectionUtils.e(obj, "parseApkLite", (Class<?>[]) new Class[]{File.class, Integer.TYPE}).invoke(obj, file, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.a("HAF_ApkValidator", "getApkLite ex=", LogUtil.a(e));
            c = false;
            return null;
        }
    }

    private static PackageInfo xW_(Context context, File file) {
        PackageManager packageManager = context.getPackageManager();
        int i = Build.VERSION.SDK_INT >= 28 ? AMapEngineUtils.HALF_MAX_P20_WIDTH : 64;
        if (file != null) {
            return packageManager.getPackageArchiveInfo(file.getAbsolutePath(), i);
        }
        try {
            return packageManager.getPackageInfo(context.getPackageName(), i);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("HAF_ApkValidator", "Cannot get app signature. ex=", LogUtil.a(e));
            return null;
        }
    }

    private static String ya_(Signature[] signatureArr) {
        if (signatureArr.length == 1) {
            return b(signatureArr[0].toByteArray());
        }
        StringBuffer stringBuffer = new StringBuffer(128);
        for (Signature signature : signatureArr) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(b(signature.toByteArray()));
        }
        return stringBuffer.toString();
    }

    private static String b(byte[] bArr) {
        return b(bArr, "SHA-256");
    }

    private static String b(byte[] bArr, String str) {
        try {
            return HexUtils.d(SecurityUtils.c(bArr, str));
        } catch (NoSuchAlgorithmException e) {
            LogUtil.e("HAF_ApkValidator", "toShaForHex ex=", LogUtil.a(e));
            return "";
        }
    }

    static final class AndroidPlatformV28 {
        private AndroidPlatformV28() {
        }

        static Signature[] ye_(Object obj, File file) {
            Object a2 = DynamicApkValidator.a(obj, file, 32);
            if (a2 == null) {
                a2 = b(obj, file);
            }
            if (a2 != null) {
                Object b = ReflectionUtils.b(ReflectionUtils.b(a2, "signingDetails"), "signatures");
                if (b instanceof Signature[]) {
                    return (Signature[]) b;
                }
            }
            return new Signature[0];
        }

        private static Object b(Object obj, File file) {
            Object obj2 = null;
            try {
                obj2 = ReflectionUtils.e(obj, "parsePackage", (Class<?>[]) new Class[]{File.class, Integer.TYPE}).invoke(obj, file, Integer.valueOf(AMapEngineUtils.HALF_MAX_P20_WIDTH));
                ReflectionUtils.e(obj, "collectCertificates", (Class<?>[]) new Class[]{obj2.getClass(), Boolean.TYPE}).invoke(obj, obj2, false);
                return obj2;
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LogUtil.a("HAF_ApkValidator", "getPackage ex=", LogUtil.a(e));
                return obj2;
            }
        }
    }

    static final class AndroidPlatformV21 {
        private AndroidPlatformV21() {
        }

        static Signature[] yd_(Object obj, File file) {
            Object a2 = DynamicApkValidator.a(obj, file, 256);
            if (a2 == null) {
                a2 = d(obj, file);
            }
            if (a2 != null) {
                Object b = ReflectionUtils.b(a2, "signatures");
                if (b instanceof Signature[]) {
                    return (Signature[]) b;
                }
            }
            return new Signature[0];
        }

        private static Object d(Object obj, File file) {
            Object obj2 = null;
            try {
                obj2 = ReflectionUtils.e(obj, "parsePackage", (Class<?>[]) new Class[]{File.class, Integer.TYPE}).invoke(obj, file, 64);
                ReflectionUtils.e(obj, "collectCertificates", (Class<?>[]) new Class[]{obj2.getClass(), Integer.TYPE}).invoke(obj, obj2, 64);
                return obj2;
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LogUtil.a("HAF_ApkValidator", "getPackage ex=", LogUtil.a(e));
                return obj2;
            }
        }
    }
}
