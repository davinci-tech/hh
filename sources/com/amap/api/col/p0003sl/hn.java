package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class hn {

    /* renamed from: a, reason: collision with root package name */
    static String f1131a = null;
    static boolean b = false;
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.toCharArray();
        for (char c2 : str.toCharArray()) {
            if (('A' > c2 || c2 > 'z') && (('0' > c2 || c2 > ':') && c2 != '.')) {
                try {
                    iv.b(ia.a(), str, "errorPackage");
                } catch (Throwable unused) {
                }
                return false;
            }
        }
        return true;
    }

    static boolean a() {
        if (b) {
            return true;
        }
        if (c(f1131a)) {
            b = true;
            return true;
        }
        if (!TextUtils.isEmpty(f1131a)) {
            b = false;
            f1131a = null;
            return false;
        }
        if (c(d)) {
            b = true;
            return true;
        }
        if (!TextUtils.isEmpty(d)) {
            b = false;
            d = null;
            return false;
        }
        return true;
    }

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f;
        }
    }

    public static String b(Context context) {
        try {
        } catch (Throwable th) {
            is.a(th, "AI", "gAN");
        }
        if (!"".equals(c)) {
            return c;
        }
        PackageManager packageManager = context.getPackageManager();
        c = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return c;
    }

    public static void a(String str) {
        d = str;
    }

    public static String c(Context context) {
        String str;
        try {
            str = d;
        } catch (Throwable th) {
            is.a(th, "AI", "gpck");
        }
        if (str != null && !"".equals(str)) {
            return d;
        }
        String packageName = context.getPackageName();
        d = packageName;
        if (!c(packageName)) {
            d = context.getPackageName();
        }
        return d;
    }

    public static String d(Context context) {
        try {
        } catch (Throwable th) {
            is.a(th, "AI", "gAV");
        }
        if (!"".equals(e)) {
            return e;
        }
        e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        String str = e;
        return str == null ? "" : str;
    }

    public static String e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance(ia.c("IU0hBMQ")).digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                String upperCase = Integer.toHexString(b2 & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
                stringBuffer.append(":");
            }
            String str = packageInfo.packageName;
            if (c(str)) {
                str = packageInfo.packageName;
            }
            if (!TextUtils.isEmpty(d)) {
                str = c(context);
            }
            stringBuffer.append(str);
            String stringBuffer2 = stringBuffer.toString();
            f1131a = stringBuffer2;
            return stringBuffer2;
        } catch (Throwable th) {
            is.a(th, "AI", "gsp");
            return f1131a;
        }
    }

    static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f = str;
    }

    static void a(final Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f = str;
        if (context != null) {
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.hn.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    Throwable th;
                    FileOutputStream fileOutputStream;
                    try {
                        File file = new File(it.c(context, "k.store"));
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            fileOutputStream.write(ia.a(str));
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            try {
                                is.a(th, "AI", "stf");
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Throwable th4) {
                                        th4.printStackTrace();
                                    }
                                }
                            } catch (Throwable th5) {
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Throwable th6) {
                                        th6.printStackTrace();
                                    }
                                }
                                throw th5;
                            }
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        fileOutputStream = null;
                    }
                }
            });
        }
    }

    public static String f(Context context) {
        try {
            ho.a(context);
        } catch (Throwable unused) {
        }
        try {
            return h(context);
        } catch (Throwable th) {
            is.a(th, "AI", "gKy");
            return f;
        }
    }

    private static String g(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        File file = new File(it.c(context, "k.store"));
        if (!file.exists()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String a2 = ia.a(bArr);
                String str = a2.length() == 32 ? a2 : "";
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
                try {
                    is.a(th, "AI", "gKe");
                    try {
                        if (file.exists()) {
                            file.delete();
                        }
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                    return "";
                } finally {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th6) {
            th = th6;
            fileInputStream = null;
        }
    }

    private static String h(Context context) throws PackageManager.NameNotFoundException {
        String str = f;
        if (str == null || str.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f;
            }
            String string = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            f = string;
            if (string == null) {
                f = g(context);
            }
        }
        return f;
    }
}
