package com.huawei.hms.feature.dynamic;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.common.util.ExtractNativeUtils;
import com.huawei.hms.common.util.FileUtils;
import com.huawei.hms.common.util.Logger;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class AssetLoadManager {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4483a = "AssetLoadManager";
    private static final String b = "dynamic_modules";
    private static final String c = ".apk";
    private static final String d = "com.huawei.hms.feature.dynamic.descriptors.";
    private static final String e = ".AssetModuleDescriptor";

    public static Bundle getAssetModuleInfo(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            Logger.w(f4483a, "The context or moduleName is null.");
            return new Bundle();
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(ModuleCopy.getProtectedPath(context));
            String str2 = File.separator;
            sb.append(str2);
            sb.append("dynamic_modules");
            sb.append(str2);
            sb.append(str);
            File file = new File(sb.toString());
            if (file.exists()) {
                Bundle a2 = a(context, file, str);
                if (a2.getInt(b.m) > 0) {
                    Logger.i(f4483a, "Successfully get module info from decompressed asset path.");
                    return a2;
                }
            }
            Bundle b2 = b(context, str);
            if (b2.getInt(b.m) > 0) {
                Logger.i(f4483a, "Successfully get module info from asset.");
                return b2;
            }
        } catch (Exception e2) {
            Logger.i(f4483a, "getDataModuleInfo failed.", e2);
        }
        return new Bundle();
    }

    private static Bundle b(Context context, String str) {
        try {
            String[] list = context.getAssets().list("dynamic_modules" + File.separator + str);
            if (list != null && list.length != 0) {
                String str2 = list[0];
                int a2 = a(context, str);
                String a3 = a(context, str, a2, str2);
                if (!TextUtils.isEmpty(a3) && new File(a3).exists()) {
                    if (!ExtractNativeUtils.a(context, a3) || ExtractNativeUtils.a(new File(a3), FileUtils.c(a3)) == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("module_name", str);
                        bundle.putString("module_path", a3);
                        bundle.putInt(b.m, a2);
                        Logger.i(f4483a, "Get dynamic module info from asset success: ModuleName:" + str + ", ModuleVersion:" + a2 + ", ModulePath:" + a3);
                        return bundle;
                    }
                    Logger.w(f4483a, "Extract native to current dir failed.");
                    return new Bundle();
                }
                Logger.w(f4483a, "Decompress module from assets failed.");
                return new Bundle();
            }
            Logger.w(f4483a, "No module apk in asset path.");
            return new Bundle();
        } catch (Exception e2) {
            Logger.i(f4483a, "getModuleFromAsset failed.", e2);
            return new Bundle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    private static String a(Context context, String str, int i, String str2) {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream2;
        Closeable closeable;
        Closeable closeable2;
        String str3;
        String str4;
        BufferedInputStream bufferedInputStream3 = null;
        try {
            try {
                AssetManager assets = context.getAssets();
                StringBuilder sb = new StringBuilder("dynamic_modules");
                str3 = File.separator;
                sb.append(str3);
                sb.append(str);
                sb.append(str3);
                sb.append(str2);
                str2 = assets.open(sb.toString());
            } catch (Throwable th) {
                th = th;
                bufferedInputStream3 = i;
            }
            try {
                bufferedInputStream2 = new BufferedInputStream(str2);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
                bufferedInputStream2 = null;
                Logger.w(f4483a, "Cannot find module:" + str + " in assets.", e);
                FileUtils.a(bufferedInputStream2);
                FileUtils.a(fileOutputStream);
                closeable2 = str2;
                FileUtils.a(closeable2);
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                closeable = str2;
                FileUtils.a(bufferedInputStream3);
                FileUtils.a(bufferedInputStream);
                FileUtils.a(closeable);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = 0;
        } catch (Throwable th3) {
            th = th3;
            str2 = 0;
        }
        try {
            str4 = ModuleCopy.getProtectedPath(context) + str3 + "dynamic_modules" + str3 + str + str3 + i;
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = bufferedInputStream3;
            bufferedInputStream3 = bufferedInputStream2;
            closeable = str2;
            FileUtils.a(bufferedInputStream3);
            FileUtils.a(bufferedInputStream);
            FileUtils.a(closeable);
            throw th;
        }
        if (!new File(str4).exists() && !new File(str4).mkdirs()) {
            Logger.w(f4483a, "mkdirs local loaderPath failed.");
            FileUtils.a(bufferedInputStream2);
            FileUtils.a((Closeable) null);
            closeable2 = str2;
            FileUtils.a(closeable2);
            return null;
        }
        String str5 = str4 + str3 + str + ".apk";
        fileOutputStream = new FileOutputStream(str5);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = bufferedInputStream2.read(bArr, 0, 4096);
                if (read == -1) {
                    Logger.i(f4483a, "Decompress module:" + str + " from assets success.");
                    FileUtils.a(bufferedInputStream2);
                    FileUtils.a(fileOutputStream);
                    FileUtils.a((Closeable) str2);
                    return str5;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e5) {
            e = e5;
            Logger.w(f4483a, "Cannot find module:" + str + " in assets.", e);
            FileUtils.a(bufferedInputStream2);
            FileUtils.a(fileOutputStream);
            closeable2 = str2;
            FileUtils.a(closeable2);
            return null;
        }
    }

    private static Bundle a(Context context, File file, String str) {
        String[] list = file.list();
        if (list == null || list.length == 0) {
            Logger.w(f4483a, "No version in module path.");
            return new Bundle();
        }
        int i = 0;
        for (String str2 : list) {
            i = Math.max(Integer.parseInt(str2), i);
        }
        if (i == 0) {
            Logger.w(f4483a, "Cannot get module version path.");
            return new Bundle();
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            ModuleCopy.clearLowVersionModule(i, canonicalPath, list, f4483a);
            if (a(context, str) > i) {
                Logger.i(f4483a, "There is a higher loader version in assets.");
                return new Bundle();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(canonicalPath);
            String str3 = File.separator;
            sb.append(str3);
            sb.append(i);
            sb.append(str3);
            sb.append(str);
            sb.append(".apk");
            String sb2 = sb.toString();
            if (!new File(sb2).exists()) {
                Logger.w(f4483a, "Cannot find module apk in asset decompressed path.");
                return new Bundle();
            }
            Bundle bundle = new Bundle();
            bundle.putString("module_name", str);
            bundle.putString("module_path", sb2);
            bundle.putInt(b.m, i);
            Logger.i(f4483a, "Get module info from decompressed asset path success: ModuleName:" + str + ", ModuleVersion:" + i + ", ModulePath:" + sb2);
            return bundle;
        } catch (IOException e2) {
            Logger.w(f4483a, "request modulePath error: " + e2.getMessage());
            return new Bundle();
        }
    }

    private static int a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            Logger.e(f4483a, "Invalid context or moduleName.");
            return 0;
        }
        try {
            return context.getClassLoader().loadClass(d + str + e).getDeclaredField("MODULE_VERSION").getInt(null);
        } catch (ClassNotFoundException unused) {
            Logger.w(f4483a, "Cannot get the class of module descriptor for " + str);
            return 0;
        } catch (Exception e2) {
            Logger.w(f4483a, "Get local asset module info failed.", e2);
            return 0;
        }
    }
}
