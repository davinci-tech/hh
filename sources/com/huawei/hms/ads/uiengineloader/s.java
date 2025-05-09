package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.ads.dynamic.IDynamicLoader;
import java.io.File;

/* loaded from: classes4.dex */
public final class s implements u {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4392a = "DecompressedLdStrategy";
    private static final String b = "loader";
    private static final String c = "com.huawei.hms.kit.type";
    private static final String d = "armeabi_type";

    @Override // com.huawei.hms.ads.uiengineloader.u
    public final y a(Context context, String str, String str2) {
        return b(context, str, str2);
    }

    @Override // com.huawei.hms.ads.uiengineloader.u
    public final Context a(Context context, y yVar) {
        String str;
        if (yVar == null) {
            str = "moduleInfo is null.";
        } else {
            String str2 = yVar.b;
            if (TextUtils.isEmpty(str2)) {
                str = "modulePath is invalid.";
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("module_path", str2);
                bundle.putString("loader_version_type", yVar.f);
                bundle.putString("module_name", yVar.f4400a);
                af.b(f4392a, "loaderVersionType is : " + yVar.f);
                try {
                    if (!TextUtils.equals(a(context, str2, bundle), b)) {
                        com.huawei.hms.ads.dynamicloader.h.a(context);
                        return com.huawei.hms.ads.dynamicloader.h.a(context, bundle);
                    }
                    af.b(f4392a, "The module is a loader, use it to load first.");
                    yVar.e = str2;
                    IDynamicLoader asInterface = IDynamicLoader.Stub.asInterface(w.a(context, yVar.e));
                    if (asInterface == null) {
                        af.c(f4392a, "Get iDynamicLoader failed: null.");
                        return null;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("module_name", yVar.f4400a);
                    bundle2.putString("loader_path", yVar.e);
                    bundle2.putInt("module_version", yVar.d);
                    bundle2.putString("loader_version_type", yVar.f);
                    return w.a(context, yVar.f4400a, bundle2, asInterface);
                } catch (Exception e) {
                    str = "Get local assets module context failed, " + e.getClass().getSimpleName();
                }
            }
        }
        af.c(f4392a, str);
        return null;
    }

    private static y b(Context context, String str, String str2) {
        y yVar = new y();
        if (context == null || TextUtils.isEmpty(str)) {
            af.c(f4392a, "The context or moduleName is null.");
            return yVar;
        }
        try {
            yVar = a(context, str);
            if (yVar.d > 0) {
                af.b(f4392a, "Successfully get module info from decompressed asset path.");
                v.a(context, str, yVar.d, str2);
                return yVar;
            }
        } catch (Exception e) {
            af.b(f4392a, "getDataModuleInfo failed." + e.getClass().getSimpleName());
        }
        return yVar;
    }

    private static Context b(Context context, y yVar) throws com.huawei.hms.ads.dynamicloader.j {
        IDynamicLoader asInterface = IDynamicLoader.Stub.asInterface(w.a(context, yVar.e));
        if (asInterface == null) {
            af.c(f4392a, "Get iDynamicLoader failed: null.");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("module_name", yVar.f4400a);
        bundle.putString("loader_path", yVar.e);
        bundle.putInt("module_version", yVar.d);
        bundle.putString("loader_version_type", yVar.f);
        return w.a(context, yVar.f4400a, bundle, asInterface);
    }

    private static String a(Context context, String str, Bundle bundle) {
        String str2;
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 128);
        String str3 = null;
        if (packageArchiveInfo == null || packageArchiveInfo.applicationInfo == null) {
            str2 = "The packageInfo is null.";
        } else {
            Bundle bundle2 = packageArchiveInfo.applicationInfo.metaData;
            if (bundle2 == null) {
                str2 = "Get meta-data failed.";
            } else {
                try {
                    for (String str4 : bundle2.keySet()) {
                        if (str4.startsWith(c)) {
                            str3 = bundle2.getString(str4);
                        }
                        if (str4.startsWith(d)) {
                            int i = bundle2.getInt(str4);
                            af.b(f4392a, "The module defined the armeabiType:".concat(String.valueOf(i)));
                            bundle.putInt("armeabiType", i);
                        }
                    }
                    af.c(f4392a, "The moduleType is:".concat(String.valueOf(str3)));
                    return str3;
                } catch (Throwable th) {
                    str2 = "getModuleMetaInfo err: " + th.getClass().getSimpleName();
                }
            }
        }
        af.c(f4392a, str2);
        return str3;
    }

    private static y a(File file, String str) {
        String[] list = file.list();
        y yVar = new y();
        if (list == null || list.length == 0) {
            af.c(f4392a, "No version in module path.");
            return yVar;
        }
        int i = 0;
        for (String str2 : list) {
            if (Integer.parseInt(str2) > i) {
                i = Integer.parseInt(str2);
            }
        }
        if (i == 0) {
            af.c(f4392a, "Cannot get module version path.");
            return yVar;
        }
        w.a(i, ad.a(file), list, f4392a);
        File file2 = new File(ad.a(file) + File.separator + i + File.separator + str + ".apk");
        if (!file2.exists()) {
            af.c(f4392a, "Cannot find module apk int local path.");
            return yVar;
        }
        String a2 = ad.a(file2);
        yVar.f4400a = str;
        yVar.b = a2;
        yVar.d = i;
        af.b(f4392a, "Get module info from decompressed asset path success: ModuleName:" + str + ", ModuleVersion:" + i);
        return yVar;
    }

    public static y a(Context context, String str) {
        File file = new File(ad.a(context) + File.separator + com.huawei.hms.ads.dynamicloader.b.f4307a + File.separator + str);
        return file.exists() ? a(file, str) : new y();
    }
}
