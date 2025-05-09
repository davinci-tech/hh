package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/* loaded from: classes4.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4399a = "ads_ModuleCopy";
    private static final int b = 2048;
    private static final String c = "file://";

    private static boolean b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            af.b(f4399a, "remote path is null.");
            return false;
        }
        if (!str.startsWith("file://")) {
            str = "file://".concat(String.valueOf(str));
        }
        return a(context, Uri.parse(str), str2);
    }

    private static boolean b(Context context, Bundle bundle) {
        try {
            String a2 = a(context, bundle, bundle.getString("module_path"), bundle.getString(com.huawei.hms.ads.dynamicloader.b.k));
            if (TextUtils.isEmpty(a2)) {
                af.c(f4399a, "check Module Path failed: null.");
                return false;
            }
            bundle.putString("module_path", a2);
            return true;
        } catch (Throwable th) {
            af.c(f4399a, "copyOneModule err: " + th.getClass().getSimpleName());
            return false;
        }
    }

    private static boolean a(String str, String str2) {
        Object th;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    aj.a(bufferedInputStream);
                    aj.a(bufferedOutputStream);
                    return true;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream2 = bufferedOutputStream;
            try {
                af.d(f4399a, "ModuleFromPath exception:" + th.getClass().getSimpleName());
                return false;
            } finally {
                aj.a(bufferedInputStream);
                aj.a(bufferedOutputStream2);
            }
        }
    }

    private static boolean a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return a(context, Uri.parse(str), str2);
        }
        af.b(f4399a, "remote uri is null.");
        return false;
    }

    public static boolean a(Context context, Bundle bundle) {
        if (context != null && bundle != null) {
            return b(context, bundle);
        }
        af.d(f4399a, "The context or module info bundle is null.");
        return false;
    }

    private static boolean a(Context context, Uri uri, String str) {
        Object th;
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream = null;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            try {
                if (openInputStream == null) {
                    af.c(f4399a, "get input stream failed: null.");
                    aj.a(openInputStream);
                    aj.a(null);
                    return false;
                }
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read == -1) {
                            aj.a(openInputStream);
                            aj.a(bufferedOutputStream);
                            return true;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = openInputStream;
                    try {
                        af.d(f4399a, "ModuleFromUri exception:" + th.getClass().getSimpleName());
                        return false;
                    } finally {
                        aj.a(inputStream);
                        aj.a(bufferedOutputStream);
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
        }
    }

    private static void a(Context context, String str, String str2, String str3) {
        boolean a2 = a(context, str2, str3);
        af.b(f4399a, "fromUri result:".concat(String.valueOf(a2)));
        if (a2) {
            return;
        }
        boolean a3 = a(str, str3);
        af.b(f4399a, "fromPath result:".concat(String.valueOf(a3)));
        if (a3) {
            return;
        }
        af.b(f4399a, "FromUriWithPrefix result:".concat(String.valueOf(b(context, str, str3))));
    }

    private static String a(Context context, String str, String str2, String str3, String str4) {
        String str5;
        if (ad.a(str)) {
            a(context, str2, str3, str4);
            if (TextUtils.isEmpty(str4) || !new File(str4).exists()) {
                str5 = "ModuleFile filePath doesn't exist, or not accessible.";
            } else {
                if (!ac.a(context, str4) || ac.a(new File(str4), ad.c(str4)) == 0) {
                    return str4;
                }
                str5 = "Extract native to current dir failed.";
            }
        } else {
            str5 = "makeDirectory return false";
        }
        af.d(f4399a, str5);
        return null;
    }

    private static String a(Context context, Bundle bundle, String str, String str2) {
        try {
            String string = bundle.getString("module_name");
            String valueOf = String.valueOf(bundle.getInt("module_version"));
            String str3 = string + ".apk";
            String str4 = ad.a(context) + File.separator + com.huawei.hms.ads.dynamicloader.b.f4307a + File.separator + string;
            String str5 = str4 + File.separator + valueOf;
            String str6 = str5 + File.separator + str3;
            if (!new File(str4).exists()) {
                af.b(f4399a, "checkModulePath file not exists.");
                return a(context, str5, str, str2, str6);
            }
            String[] list = new File(str4).list();
            int a2 = a(list);
            af.b(f4399a, "checkModulePath maxVersion:" + a2 + ", version:" + valueOf);
            if (a2 < Integer.parseInt(valueOf)) {
                return a(context, str5, str, str2, str6);
            }
            String str7 = str4 + File.separator + a2 + File.separator + str3;
            if (!new File(str7).exists()) {
                return a(context, str5, str, str2, str7);
            }
            w.a(a2, str4, list, f4399a);
            return str7;
        } catch (Throwable th) {
            af.c(f4399a, "checkModulePath err: " + th.getClass().getSimpleName());
            return "";
        }
    }

    private static int a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            af.b(f4399a, "No version dirs in module path, need mkdir.");
            return 0;
        }
        int i = 0;
        for (String str : strArr) {
            if (Integer.parseInt(str) > i) {
                i = Integer.parseInt(str);
            }
        }
        return i;
    }
}
