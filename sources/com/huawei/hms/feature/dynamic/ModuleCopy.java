package com.huawei.hms.feature.dynamic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.common.util.FileUtils;
import com.huawei.hms.common.util.Logger;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class ModuleCopy {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4505a = "ModuleCopy";
    private static final int b = 2048;
    private static final int c = 0;
    private static final int d = 1;
    private static final String e = "module_uri_path";
    private static final String f = "loader_uri_path";
    private static final String g = "dynamic_modules";
    private static final String h = ".apk";
    private static final String i = "0";

    public static boolean isPathInvalid(String str) {
        if (!TextUtils.isEmpty(str) && !str.contains("..")) {
            return false;
        }
        Logger.e(f4505a, "The path is invalid.");
        return true;
    }

    public static boolean isLocalModuleFile(Context context, String str) {
        if (context == null) {
            Logger.w(f4505a, "context is null.");
            return false;
        }
        if (isPathInvalid(str)) {
            return false;
        }
        try {
            return new File(str).getCanonicalPath().startsWith(getProtectedPath(context) + File.separator + "dynamic_modules");
        } catch (IOException | IllegalArgumentException unused) {
            return false;
        }
    }

    public static String getProtectedPath(Context context) throws IllegalArgumentException, IOException {
        if (context != null) {
            return context.createDeviceProtectedStorageContext().getDataDir().getCanonicalPath();
        }
        throw new IllegalArgumentException("context is null");
    }

    public static void copyModule(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            Logger.e(f4505a, "The context or module info bundle is null.");
            return;
        }
        boolean a2 = a(bundle);
        a(context, bundle, 0);
        if (a2) {
            bundle.putString("loader_path", bundle.getString("module_path"));
        } else {
            a(context, bundle, 1);
        }
    }

    public static void clearLowVersionModule(int i2, String str, String[] strArr, String str2) {
        c.a(1, str2).execute(new b(strArr, i2, str));
    }

    private static boolean a(Bundle bundle) {
        return TextUtils.equals(bundle.getString("module_path"), bundle.getString("loader_path"));
    }

    private static void a(String str) {
        c.a(1, "DeleteFile").execute(new a(str));
    }

    private static void a(Context context, Bundle bundle, int i2) {
        String string = bundle.getString(i2 == 0 ? "module_uri_path" : f);
        Logger.i(f4505a, "path:" + string);
        String a2 = a(context, bundle, i2, Uri.parse(string));
        if (TextUtils.isEmpty(a2)) {
            Logger.w(f4505a, "checkModulePath failed: null.");
        } else {
            bundle.putString(i2 == 0 ? "module_path" : "loader_path", a2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v8 */
    private static void a(Context context, Uri uri, String str) {
        Throwable th;
        IOException e2;
        FileNotFoundException e3;
        StringBuilder sb;
        String message;
        Closeable closeable;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                context = context.getContentResolver().openInputStream(uri);
                try {
                    if (context == 0) {
                        Logger.w(f4505a, "Get input stream failed: null.");
                        FileUtils.a((Closeable) context);
                        FileUtils.a((Closeable) null);
                        return;
                    }
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str));
                    try {
                        byte[] bArr = new byte[2048];
                        while (true) {
                            int read = context.read(bArr);
                            if (read == -1) {
                                FileUtils.a((Closeable) context);
                                FileUtils.a(bufferedOutputStream2);
                                return;
                            }
                            bufferedOutputStream2.write(bArr, 0, read);
                        }
                    } catch (FileNotFoundException e4) {
                        e3 = e4;
                        bufferedOutputStream = bufferedOutputStream2;
                        sb = new StringBuilder();
                        sb.append("FileNotFoundException:");
                        message = e3.getMessage();
                        closeable = context;
                        sb.append(message);
                        Logger.e(f4505a, sb.toString());
                        FileUtils.a(closeable);
                        FileUtils.a(bufferedOutputStream);
                    } catch (IOException e5) {
                        e2 = e5;
                        bufferedOutputStream = bufferedOutputStream2;
                        sb = new StringBuilder();
                        sb.append("IOException ");
                        message = e2.getMessage();
                        closeable = context;
                        sb.append(message);
                        Logger.e(f4505a, sb.toString());
                        FileUtils.a(closeable);
                        FileUtils.a(bufferedOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        FileUtils.a((Closeable) context);
                        FileUtils.a(bufferedOutputStream);
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    e3 = e6;
                } catch (IOException e7) {
                    e2 = e7;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (FileNotFoundException e8) {
            e3 = e8;
            context = 0;
        } catch (IOException e9) {
            e2 = e9;
            context = 0;
        } catch (Throwable th4) {
            th = th4;
            context = 0;
        }
    }

    private static String a(Context context, String str, Uri uri, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str4 = File.separator;
        sb.append(str4);
        sb.append("0");
        String sb2 = sb.toString();
        String str5 = sb2 + str4 + str2;
        if (!FileUtils.b(sb2)) {
            Logger.e(f4505a, "makeDirectory:tmpModuleFileDir return false.");
            return null;
        }
        a(context, uri, str5);
        if (!com.huawei.hms.feature.dynamic.f.d.b(context, str5)) {
            Logger.w(f4505a, "The coped tmp module apk is invalid.");
            a(str5);
            return null;
        }
        String str6 = str + str4 + str3;
        String str7 = str6 + str4 + str2;
        if (!FileUtils.b(str6)) {
            Logger.w(f4505a, "makeDirectory:finalModuleFileDir return false.");
            a(str5);
            return null;
        }
        try {
            if (new File(str5).renameTo(new File(str7))) {
                return str7;
            }
        } catch (SecurityException unused) {
            Logger.w(f4505a, "rename SecurityException.");
        }
        Logger.w(f4505a, "rename the apk file to the final path failed, should return null.");
        a(str5);
        return null;
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4506a;

        @Override // java.lang.Runnable
        public void run() {
            try {
                FileUtils.a(this.f4506a);
            } catch (SecurityException unused) {
                Logger.w(ModuleCopy.f4505a, "Delete file failed: SecurityException.");
            }
        }

        public a(String str) {
            this.f4506a = str;
        }
    }

    public class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f4507a;
        public final /* synthetic */ int b;
        public final /* synthetic */ String c;

        @Override // java.lang.Runnable
        public void run() {
            for (String str : this.f4507a) {
                if (Integer.parseInt(str) < this.b) {
                    Logger.i(ModuleCopy.f4505a, "Delete low version:" + this.b + " in modulePath.");
                    FileUtils.a(this.c + File.separator + str);
                }
            }
        }

        public b(String[] strArr, int i, String str) {
            this.f4507a = strArr;
            this.b = i;
            this.c = str;
        }
    }

    private static String a(Context context, Bundle bundle, int i2, Uri uri) {
        String str;
        String string;
        String valueOf;
        StringBuilder sb;
        String[] list;
        int a2;
        if (i2 == 0) {
            str = "module_name";
            string = bundle.getString("module_name");
            valueOf = String.valueOf(bundle.getInt("module_version"));
            sb = new StringBuilder();
        } else {
            str = "loader_name";
            string = bundle.getString("loader_name");
            valueOf = String.valueOf(bundle.getInt("loader_version"));
            sb = new StringBuilder();
        }
        sb.append(bundle.getString(str));
        sb.append(".apk");
        String sb2 = sb.toString();
        try {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(getProtectedPath(context));
            String str2 = File.separator;
            sb3.append(str2);
            sb3.append("dynamic_modules");
            sb3.append(str2);
            sb3.append(string);
            String sb4 = sb3.toString();
            if (new File(sb4).exists() && (a2 = a((list = new File(sb4).list()))) >= Integer.parseInt(valueOf)) {
                clearLowVersionModule(a2, sb4, list, f4505a);
                return sb4 + str2 + a2 + str2 + sb2;
            }
            return a(context, sb4, uri, sb2, valueOf);
        } catch (IOException | IllegalArgumentException e2) {
            Logger.w(f4505a, "request modulePath error: " + e2.getMessage());
            return null;
        }
    }

    private static int a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            Logger.i(f4505a, "No version dirs in module path, need mkdir.");
            return 0;
        }
        int i2 = 0;
        for (String str : strArr) {
            i2 = Math.max(Integer.parseInt(str), i2);
        }
        return i2;
    }
}
